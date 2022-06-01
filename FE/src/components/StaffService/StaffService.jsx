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
import { useParams } from "react-router-dom";
import styled from "styled-components";
import {
  deletestaffservice,
  getstaffservice
} from "../../apis/staffServiceApi";
import ModelStaffService from "./ModalStaffService";

const Container = styled.div`
  margin: 20px;
`;

const TitleAndSearch = styled.div`
  display: flex;
  justify-content: space-between;
  width: 88.5vw;
`;

const Content = styled.div``;

const StaffServiceTable = styled.div`
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
const StaffService = () => {
  const [staffservice, setStaffservice] = useState([]);
  const [idStaff, setIdStaff] = useState();
  const [wantDelete, setWantDelete] = useState();
  const [editModal, setEditModal] = useState();
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const [selectedService,setSelectedService] = useState("");
  const staffParam = useParams();
  
  
  const searchInput = useRef(null);

  useEffect(() => {
    setIdStaff(staffParam.id);
    getstaffservice(staffParam.id)
      .then((response) => {
        setStaffservice(response.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const onConfirmDelete = () => {
    deletestaffservice(wantDelete)
      .then(() => displaystaffservice())
      .catch(() => {
        notification["error"]({
          message: "Xóa nhân viên dịch vụ thất bại",
          placement: "topRight",
        });
      });
  };

  const displaystaffservice = () => {
    getstaffservice(idStaff)
      .then((response) => {
        setStaffservice(response.data);
        notification["success"]({
          message: "Xóa nhân viên dịch vụ thành công",
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
          title="Bạn có muốn xóa nhân viên dịch vụ này?"
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
      title: "Id",
      dataIndex: "id",
      key: "id",
      width: "4vw",
    },
    {
      title: "Mã dịch vụ",
      dataIndex: "madichvu",
      key: "madichvu",
      width: "6vw",
      ...getColumnSearchProps("madichvu"),
    },
    {
      title: "Tên dịch vụ",
      dataIndex: "tendichvu",
      key: "tendichvu",
      width: "17vw",
      ...getColumnSearchProps("tendichvu"),
    },
    {
      title: "Mức lương",
      dataIndex: "mucluong",
      key: "mucluong",
      width: "17vw",
    },
    {
      title: "Tháng làm",
      dataIndex: "thanglam",
      key: "thanglam",
      width: "8vw",
    },
    {
      title: "Năm làm",
      dataIndex: "namlam",
      key: "namlam",
      width: "8vw",
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

  return (
    <div style={{ backgroundColor: "#F3F2F2", width: "90vw"}}>
      <Container>
        <TitleAndSearch>
          <div>
            <h1>QUẢN LÝ DỊCH VỤ CỦA NHÂN VIÊN</h1>
          </div>
        </TitleAndSearch>
        <Content>
          <div>
            <h2>DANH SÁCH DỊCH VỤ</h2>
          </div>
          <ModelStaffService
            staff={staffParam}
            editModal={editModal}
            setEditModal={setEditModal}
            staffservice={staffservice}
            setStaffservice={setStaffservice}
            // selectedService={selectedService}
          />
          <StaffServiceTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={staffservice}
              columns={columns}
              scroll={{ y: 400 }}
              pagination={{
                defaultPageSize: 10,
                showSizeChanger: true,
                pageSizeOptions: ["10", "20", "30"],
              }}
            >
            </Table>
          </StaffServiceTable>

        </Content>
      </Container>
    </div>
  );
};

export default StaffService;
