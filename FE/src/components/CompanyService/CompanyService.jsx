import { DownOutlined, SearchOutlined } from "@ant-design/icons";
import { useEffect, useRef, useState } from "react";

import {
  Button,
  Dropdown,
  Input,
  Menu,
  notification,
  Popconfirm,
  Space,
  Table
} from "antd";

import Highlighter from "react-highlight-words";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import {
  deletecompanyservice,
  getcompanyservice
} from "../../apis/companyServiceApi";
import ModalCompanyService from "./ModalCompanyService";

const Container = styled.div`
  margin: 20px;
`;

const TitleAndSearch = styled.div`
  display: flex;
  justify-content: space-between;
  width: 88.5vw;
`;

const Content = styled.div``;

const CompanyTable = styled.div`
  margin: 10px;
`;

const Action = styled.div`
  width: 2vw;
`;
const CompanyService = () => {
  const [companyServices, setCompanyServices] = useState([]);
  const [editModal, setEditModal] = useState(null);
  const [wantDelete, setWantDelete] = useState(null);
  const [idCompany, setIdCompany] = useState(null);
  const companyParam = useParams();
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);

  useEffect(() => {
    setIdCompany(companyParam.id);
    
    getcompanyservice(idCompany)
      .then((response) => {
        setCompanyServices(response.data);
      })
      .catch((error) => console.log(error));
  });

  const onConfirmDelete = () => {
    deletecompanyservice(wantDelete)
      .then(() => displayCompanyService())
      .catch(() => {
        notification["error"]({
          message: "Xóa dịch vụ thất bại",
          placement: "topRight",
        });
      });
  };

  const displayCompanyService = () => {
    getcompanyservice(idCompany)
      .then((response) => {
        setCompanyServices(response.data);
        notification["success"]({
          message: "Xóa dịch vụ thành công",
          placement: "topRight",
        });
      })
      .catch((error) => console.log(error));
  };
  const actionMenu = (record) => (
    <Menu>
      <Menu.Item key="1">
        <a onClick={() => setEditModal(record)}>Sửa</a>
      </Menu.Item>
      <Menu.Item key="2">
        <Popconfirm
          title="Bạn có muốn xóa dịch vụ này không?"
          onConfirm={onConfirmDelete}
          okText="Có"
          cancelText="Không"
        >
          <a onClick={() => setWantDelete(record.id)}>Xóa</a>
        </Popconfirm>
      </Menu.Item>
    </Menu>
  );

  const handleSearch = (selectedKeys, confirm, dataIndex) => {
    confirm();
    setSearchText(selectedKeys[0]);
    setSearchedColumn(dataIndex);
  };

  const handleReset = (clearFilters) => {
    clearFilters();
    setSearchText("");
  };
  const getColumnSearchProps = (dataIndex) => ({
    filterDropdown: ({
      setSelectedKeys,
      selectedKeys,
      confirm,
      clearFilters,
    }) => (
      <div
        style={{
          padding: 8,
        }}
      >
        <Input
          ref={searchInput}
          placeholder={`Search ${dataIndex}`}
          value={selectedKeys[0]}
          onChange={(e) =>
            setSelectedKeys(e.target.value ? [e.target.value] : [])
          }
          onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
          style={{
            marginBottom: 8,
            display: "block",
          }}
        />
        <Space>
          <Button
            type="primary"
            onClick={() => handleSearch(selectedKeys, confirm, dataIndex)}
            icon={<SearchOutlined />}
            size="small"
            style={{
              width: 90,
            }}
          >
            Search
          </Button>
          <Button
            onClick={() => clearFilters && handleReset(clearFilters)}
            size="small"
            style={{
              width: 90,
            }}
          >
            Reset
          </Button>
          <Button
            type="link"
            size="small"
            onClick={() => {
              confirm({
                closeDropdown: false,
              });
              setSearchText(selectedKeys[0]);
              setSearchedColumn(dataIndex);
            }}
          >
            Filter
          </Button>
        </Space>
      </div>
    ),
    filterIcon: (filtered) => (
      <SearchOutlined
        style={{
          color: filtered ? "#1890ff" : undefined,
        }}
      />
    ),
    onFilter: (value, record) =>
      record[dataIndex].toString().toLowerCase().includes(value.toLowerCase()),
    onFilterDropdownVisibleChange: (visible) => {
      if (visible) {
        setTimeout(() => searchInput.current?.select(), 100);
      }
    },
    render: (text) =>
      searchedColumn === dataIndex ? (
        <Highlighter
          highlightStyle={{
            backgroundColor: "#ffc069",
            padding: 0,
          }}
          searchWords={[searchText]}
          autoEscape
          textToHighlight={text ? text.toString() : ""}
        />
      ) : (
        text
      ),
  });
  const columns = [
    {
      title: "Index",
      dataIndex: "id",
      key: "id",
      width: "3vw",
    },
    {
      title: "Mã dịch vụ",
      dataIndex: "ma",
      key: "ma",
      width: "5vw",
      ...getColumnSearchProps("ma"),
    },
    {
      title: "Tên Dịch vụ",
      dataIndex: "ten",
      key: "ten",
      width: "5vw",
      ...getColumnSearchProps("ten"),
    },
    {
      title: "Loại",
      dataIndex: "loaidichvu",
      key: "loaidichvu",
      width: "7vw",
    },
    {
      title: "Tháng thuê",
      dataIndex: "thangthue",
      key: "thangthue",
      width: "4vw",
    },
    {
      title: "Năm thuê",
      dataIndex: "namthue",
      key: "namthue",
      width: "3vw",
    },
    {
      title: "Đơn giá",
      dataIndex: "dongia",
      key: "dongia",
      width: "3vw",
      sorter: (a, b) => a.dongia - b.dongia,
      sortDirections: ['descend', 'ascend'],
    },

    {
      title: "Mô tả",
      dataIndex: "mota",
      key: "mota",
      width: "12vw",
    },

    {
      title: "Tùy chọn",
      key: "action",
      width: "5vw",
      render: (record) => (
        <Action>
          <Dropdown overlay={actionMenu(record)}>
            <Button>
              <Space>
                Tùy chọn
                <DownOutlined />
              </Space>
            </Button>
          </Dropdown>
        </Action>
      ),
    },
  ];


  return (
    <div style={{ backgroundColor: "#F3F2F2" ,width: "89.8vw"}}>
      <Container>
        <TitleAndSearch>
          <div>
            <h1>QUẢN LÝ CÁC DỊCH VỤ CÔNG TY ĐANG THUÊ</h1>
          </div>

        </TitleAndSearch>
        <Content>
          <div>
            <h2>Danh sách dịch vụ</h2>
          </div>
          <ModalCompanyService
            company={companyParam}
            editModal={editModal}
            setEditModal={setEditModal}
            companyServices={companyServices}
            setCompanyServices={setCompanyServices}
          />
          <CompanyTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={companyServices}
              columns={columns}
              scroll={{ y: 400 }}
              pagination={{
                defaultPageSize: 10,
                showSizeChanger: true,
                pageSizeOptions: ["10", "20", "30"],
              }}
            >

            </Table>
          </CompanyTable>

        </Content>
      </Container>
    </div>
  );
};

export default CompanyService;
