import { SearchOutlined } from "@ant-design/icons";
import { useEffect, useRef, useState } from "react";

import { FundViewOutlined } from "@ant-design/icons";
import {
  Button, Input, Space,
  Table
} from "antd";
import Highlighter from "react-highlight-words";
import styled from "styled-components";
import { getbill } from "../../apis/billApi";
import ModalBill from "./ModalBill";


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

const Bill = () => {
  const [bills, setBills] = useState();
  const [idBill, setIdBill] = useState();
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);

  useEffect(() => {
    getbill()
      .then((response) => setBills(response.data))
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
          placeholder={`Tìm theo ${dataIndex}`}
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
      title: "Mã công ty",
      dataIndex: "macongty",
      key: "macongty",
       width: "7vw",
      ...getColumnSearchProps("macongty"),
    },
    {
      title: "Tên công ty",
      dataIndex: "tencongty",
      key: "tencongty",
      width: "12vw",
      ...getColumnSearchProps("tencongty"),
    },
    {
      title: "Tiền thuê mặt bằng",
      dataIndex: "tienthuematbang",
      key: "tienthuematbang",
      width: "10vw",
      sorter: (a, b) => a.tienthuematbang - b.tienthuematbang,
      sortDirections: ['descend', 'ascend'],
    },
    {
      title: "Tổng phí dịch vụ",
      dataIndex: "tongphidichvu",
      key: "tongphidichvu",
      width: "10vw",
      sorter: (a, b) => a.tongphidichvu - b.tongphidichvu,
      sortDirections: ['descend', 'ascend'],
    },
    {
      title: "Tổng tiền thuê",
      dataIndex: "tongtienthue",
      key: "tongtienthue",
      sorter: (a, b) => a.tongtienthue - b.tongtienthue,
      sortDirections: ['descend', 'ascend'],
      width: "8vw",
    },
 
    {
      title: "Tùy chọn",
      key: "action",
      width: "7vw",
      style: { backgroundColor: "black", width: "88.8vw" },
      render:(record) => (
        <Button
          type="primary"
          icon={<FundViewOutlined />}
          onClick={() => {
            setIdBill(record.id);
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
            <h1>XEM TẤT CẢ HÓA ĐƠN</h1>
          </div>
          <div></div>
        </TitleAndSearch>
        <Content>
          <div>
            <h2>Danh sách hóa đơn</h2>
          </div>
          <ModalBill idBill={idBill} setIdBill={setIdBill} />
          <CompanyTable>
            <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
              dataSource={bills}
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

export default Bill;
