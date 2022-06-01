import { useEffect, useRef, useState } from "react";
import { DownOutlined, SearchOutlined } from "@ant-design/icons";
import {
  Button,
  Dropdown,
  Input,
  Menu,
  notification,
  Popconfirm,
  Space,
  Table,
} from "antd";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import {
  deleteemployee,
  getemployee,
} from "../../apis/employeeApi";
import ModalEmployee from "./ModalEmployee";
import Highlighter from "react-highlight-words";
import moment from "moment";

const { Search } = Input;
const { Column } = Table;

const Container = styled.div`
  margin: 20px;
`;

const TitleAndSearch = styled.div`
  display: flex;
  justify-content: space-between;
  width: 88.5vw;
`;

const Content = styled.div``;

const EmployeeTable = styled.div`
  margin: 10px;
`;

const TableFooter = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px;
`;
const Action = styled.div`
  width: 3vw;
`;
const Employee = () => {
  const [employees, setEmployees] = useState();
  const [idCompany, setIdCompany] = useState();
  const [wantDelete, setWantDelete] = useState();
  const [editModal, setEditModal] = useState();
  const companyParam = useParams();
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);
  
  useEffect(() => {
    setIdCompany(companyParam.id);
    getemployee(idCompany)
      .then((response) => {
        let tmp = response.data;
        let res = [];
        tmp.map((item) => {
          res.push({
            ...item,
            ngaysinh: moment(new Date(item.ngaysinh)).format("YYYY-MM-DD"),
          });
        });
        setEmployees(res);
      })
      .catch((error) => console.log(error));
  });

  const onConfirmDelete = () => {
    deleteemployee(wantDelete)
      .then(() => displayEmployee())
      .catch(() => {
        notification["error"]({
          message: "Xóa nhân viên của công ty thất bại",
          placement: "topRight",
        });
      });
  };

  const displayEmployee = () => {
    getemployee(companyParam.id)
      .then((response) => {
        setEmployees(response.data);
        notification["success"]({
          message: "Xóa nhân viên của công ty thành công",
          placement: "topRight",
        });
      })
      .catch((error) => console.log(error));
  };
  const actionMenu = (record) => (
    <Menu>
      <Menu.Item key="2">
        <a
          onClick={() => {
            setEditModal(record);
          }}
        >
          Sửa
        </a>
      </Menu.Item>
      <Menu.Item key="3">
        <Popconfirm
          title="Bạn có muốn xóa nhân viên này không?"
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
      title: "Mã nhân viên",
      dataIndex: "ma",
      key: "ma",
      width: "6vw",
      ...getColumnSearchProps("ma"),
    },
    {
      title: "Tên nhân viên",
      dataIndex: "ten",
      key: "ten",
      width: "7vw",
      ...getColumnSearchProps("ten"),
    },
    {
      title: "CCCD",
      dataIndex: "cccd",
      key: "cccd",
      width: "7vw",
    },
    {
      title: "Ngày sinh",
      dataIndex: "ngaysinh",
      key: "ngaysinh",
      width: "8vw",
    },
    {
      title: "Số điện thoại",
      dataIndex: "sdt",
      key: "sdt",
      width: "7vw",
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
    <div style={{ backgroundColor: "#F3F2F2", width: "89.8vw" }}>
      <Container>
        <TitleAndSearch>
          <div>
            <h1>QUẢN LÝ NHÂN VIÊN CỦA CÔNG TY</h1>
          </div>
        </TitleAndSearch>
        <Content>
          <div>
            <h2>Danh sách nhân viên công ty</h2>
          </div>
          <ModalEmployee
            company={companyParam}
            editModal={editModal}
            setEditModal={setEditModal}
            employees={employees}
            setEmployees={setEmployees}
          />
          <EmployeeTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={employees}
              columns={columns}
              scroll={{ y: 400 }}
              pagination={{
                defaultPageSize: 10,
                showSizeChanger: true,
                pageSizeOptions: ["10", "20", "30"],
              }}
            >
              
            </Table>
          </EmployeeTable>

        </Content>
      </Container>
    </div>
  );
};

export default Employee;
