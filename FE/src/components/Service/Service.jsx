import { DownOutlined, SearchOutlined } from "@ant-design/icons";
import { useEffect, useRef, useState } from "react";
import Highlighter from "react-highlight-words";
import styled from "styled-components";

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

import { deleteservice, getservice } from "../../apis/serviceApi";
import ModalService from "./ModalService";

const Container = styled.div`
  margin: 20px;
`;

const TitleAndSearch = styled.div`
  display: flex;
  justify-content: space-between;
  width: 88.5vw;
`;

const Content = styled.div``;

const ServiceTable = styled.div`
  margin: 10px;
`;

const Action = styled.div`
  width: 1vw;
`;
const Service = () => {
  const [services, setServices] = useState([]);
  const [editModal, setEditModal] = useState(null);
  const [wantDelete, setWantDelete] = useState(null);
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);
  useEffect(() => {
    getservice()
      .then((response) => {
        setServices(response.data);
        console.log(response.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const onConfirmDelete = () => {
    deleteservice(wantDelete)
      .then(() => displayService())
      .catch(() => {
        notification["error"]({
          message: "Xóa dịch vụ thất bại",
          placement: "topRight",
        });
      });
  };

  const displayService = () => {
    getservice()
      .then((response) => {
        setServices(response.data);
        notification["success"]({
          message: "Xóa dịch vụ thành công",
          placement: "topRight",
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const actionMenu = (record) => (
    <Menu>
      <Menu.Item key="2">
        <a onClick={() => setEditModal(record)}>Sửa</a>
      </Menu.Item>
      <Menu.Item key="3">
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
    { title: "Index", dataIndex: "id", key: "id", width: "3vw" },
    {
      title: "Mã",
      dataIndex: "ma",
      key: "ma",
      width: "3vw",
      ...getColumnSearchProps("ma"),
    },
    {
      title: "Tên",
      dataIndex: "ten",
      key: "ten",
      width: "7vw",
      ...getColumnSearchProps("ten"),
    },

    {
      title: "Loại dịch vụ",
      dataIndex: "loaidichvu",
      key: "loaidichvu",
      width: "8vw",
      ...getColumnSearchProps("loaidichvu"),
    },

    {
      title: "Đơn giá",
      dataIndex: "dongia",
      key: "dongia",
      width: "4vw",
      sorter: (a, b) => a.dongia - b.dongia,
      sortDirections: ["descend", "ascend"],
    },

    {
      title: "Mô tả",
      dataIndex: "mota",
      key: "mota",
      width: "15vw",
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
            <h1>QUẢN LÝ DỊCH VỤ</h1>
          </div>
        </TitleAndSearch>
        <Content>
          <div>
            <h2>Danh sách dịch vụ</h2>
          </div>
          <ModalService
            editModal={editModal}
            setEditModal={setEditModal}
            services={services}
            setServices={setServices}
          />
          <ServiceTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={services}
              columns={columns}
              scroll={{ y: 400 }}
              pagination={{
                defaultPageSize: 10,
                showSizeChanger: true,
                pageSizeOptions: ["10", "20", "30"],
              }}
            ></Table>
          </ServiceTable>
        </Content>
      </Container>
    </div>
  );
};

export default Service;
