
import { FundViewOutlined, SearchOutlined } from "@ant-design/icons";
import { useEffect, useRef, useState } from "react";
import Highlighter from "react-highlight-words";
import styled from "styled-components";

import { getsalary } from "../../apis/salaryApi";
import ModalSalary from "./ModalSalary";

import {
  Button, Input, Space,
  Table
} from "antd";

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

const Salary = () => {
  const [salaries, setSalaries] = useState();
  const [idStaff, setIdStaff] = useState();
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);
  useEffect(() => {
    getsalary()
      .then((response) => setSalaries(response.data))
      .catch((error) => console.log(error));
  });


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
      width: "4vw",
    },
    {
      title: "Mã nhân viên",
      dataIndex: "manhanvien",
      key: "manhanvien",
      width: "12vw",
      ...getColumnSearchProps("manhanvien"),
    },
    {
      title: "Tên nhân viên",
      dataIndex: "tennhanvien",
      key: "tennhanvien",
      width: "12vw",
      ...getColumnSearchProps("tennhanvien"),
    },
    {
      title: "Vị trí",
      dataIndex: "vitri",
      key: "vitri",
      width: "12vw",
    },
    {
      title: "Tổng tiền lương",
      dataIndex: "tongtienluong",
      key: "tongtienluong",
      width: "12vw",
      sorter: (a, b) => a.tongtienluong - b.tongtienluong,
      sortDirections: ['descend', 'ascend'],
    },
   
    
    {
      title: "Tùy chọn",
      key: "action",
      width: "10vw",
      style: { backgroundColor: "black", width: "88.8vw" },
      render:(record) => (
        <Button
          type="primary"
          icon={<FundViewOutlined />}
          onClick={() => {
            setIdStaff(record.id);
          }}
        >
          Xem chi tiết
        </Button>
      ),
    },
  ];


  return (
    <div style={{ backgroundColor: "#F3F2F2", width: "89.8vw" }}>

      <Container>
        <TitleAndSearch>
          <div>
            <h1>XEM LƯƠNG CỦA NHÂN VIÊN TÒA NHÀ</h1>
          </div>
          <div></div>
        </TitleAndSearch>
        <Content>
          <div>
            <h2>Danh sách lương</h2>
          </div>
          <ModalSalary idStaff={idStaff} setIdStaff={setIdStaff} />
          <CompanyTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={salaries}
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

export default Salary;
