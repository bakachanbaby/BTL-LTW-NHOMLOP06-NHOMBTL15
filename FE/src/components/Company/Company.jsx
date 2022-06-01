import { DownOutlined, SearchOutlined } from "@ant-design/icons";
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
import { useEffect, useRef, useState } from "react";
import Highlighter from "react-highlight-words";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { deletecompany, getcompany } from "../../apis/companyApi";
import ModalCompany from "./ModalCompany";

const Container = styled.div`
  margin: 20px;
`;

const TitleAndSearch = styled.div`
  display: flex;
  justify-content: space-between;
  width: 86.5vw;
`;

const Content = styled.div``;

const CompanyTable = styled.div`
  margin: 10px;
`;

const Action = styled.div`
  width: 7vw;
`;

const Company = () => {
  const [companies, setCompanies] = useState([]);
  const [editModal, setEditModal] = useState(null);
  const [wantDelete, setWantDelete] = useState(null);
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);

  useEffect(() => {
    getcompany()
      .then((response) => {
        setCompanies(response.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const handleSearch = (selectedKeys, confirm, dataIndex) => {
    confirm();
    setSearchText(selectedKeys[0]);
    setSearchedColumn(dataIndex);
  };

  const handleReset = (clearFilters) => {
    clearFilters();
    setSearchText("");
  };

  const onConfirmDelete = () => {
    deletecompany(wantDelete)
      .then(() => {
        displayCompany();
      })
      .catch(() => {
        notification["error"]({
          message: "Xóa công ty thất bại",
          placement: "topRight",
        });
      });
  };

  const displayCompany = () => {
    getcompany()
      .then((response) => {
        setCompanies(response.data);
        notification["success"]({
          message: "Xóa công ty thành công",
          placement: "topRight",
        });
      })
      .catch((error) => {
        console.log(error);
      });
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
      width: "4vw",
    },
    {
      title: "Mã công ty",
      dataIndex: "ma",
      key: "ma",
      width: "8vw",
      ...getColumnSearchProps("ma"),
    },
    {
      title: "Tên công ty",
      dataIndex: "ten",
      key: "ten",
      width: "17vw",
      ...getColumnSearchProps("ten"),
    },
    {
      title: "Lĩnh vực hoạt động",
      dataIndex: "linhvuchoatdong",
      key: "linhvuchoatdong",
    },
    {
      title: "Địa chỉ",
      dataIndex: "diachitrongtoanha",
      key: "diachitrongtoanha",
      width: "8vw",
    },
    {
      title: "Số điện thoại",
      dataIndex: "sdt",
      key: "sdt",
      width: "8vw",
    },
    {
      title: "Diện tích thuê",
      dataIndex: "dientichthue",
      key: "dientichthue",
      width: "8vw",
      sorter: (a, b) => a.dientichthue - b.dientichthue,
      sortDirections: ["descend", "ascend"],
    },
    {
      title: "Tùy chọn",
      key: "action",
      width: "10vw",
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

  const actionMenu = (record) => (
    <Menu>
      <Menu.Item key="1">
        <a onClick={() => setEditModal(record)}>Sửa</a>
      </Menu.Item>
      <Menu.Item key="2">
        <Popconfirm
          title="Bạn có muốn xóa công ty này không?"
          onConfirm={onConfirmDelete}
          okText="Có"
          cancelText="Không"
        >
          <a onClick={() => setWantDelete(record.id)}>Xóa</a>
        </Popconfirm>
      </Menu.Item>
      <Menu.Item key="3">
        <Link
          to={`/congty/${parseInt(record.id)}/${record.ma}/${record.ten}/${
            record.linhvuchoatdong
          }/${record.diachitrongtoanha}/${record.sdt}/${parseInt(
            record.dientichthue
          )}/nhanvien`}
        >
          Danh sách nhân viên
        </Link>
      </Menu.Item>
      <Menu.Item key="4">
        <Link
          to={`/congty/${parseInt(record.id)}/${record.ma}/${record.ten}/${
            record.linhvuchoatdong
          }/${record.diachitrongtoanha}/${record.sdt}/${parseInt(
            record.dientichthue
          )}/dichvu`}
        >
          Dịch vụ đang thuê
        </Link>
      </Menu.Item>
    </Menu>
  );
  return (
    <div style={{ backgroundColor: "#F3F2F2", width: "90vw" }}>
      <Container>
        <TitleAndSearch>
          <div>
            <h1>QUẢN LÝ CÔNG TY THUÊ MẶT BẰNG</h1>
          </div>
        </TitleAndSearch>
        <Content>
          <div>
            <h2>Danh sách công ty</h2>
          </div>
          <ModalCompany
            editModal={editModal}
            setEditModal={setEditModal}
            companies={companies}
            setCompanies={setCompanies}
          />
          <CompanyTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={companies}
              columns={columns}
              scroll={{ y: 400 }}
              pagination={{
                defaultPageSize: 10,
                showSizeChanger: true,
                pageSizeOptions: ["10", "20", "30"],

              }}
            ></Table>
          </CompanyTable>
        </Content>
      </Container>
    </div>
  );
};

export default Company;
