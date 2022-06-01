import { Modal, Table } from "antd";
import { useEffect, useState } from "react";
import { getbillbyid } from "../../apis/billApi";

const ModalBill = (props) => {
  const { idBill } = props;
  const [billList, setBillList] = useState();

  useEffect(() => {
    getbillbyid(idBill)
      .then((response) => {
        setBillList(response.data);
      })
      .catch((error) => console.log(error));
  });

  const onCancelModal = () => {
    props.setIdBill("");
  };


  const columns = [
    {
      title: "Tháng",
      dataIndex: "thang",
      key: "thang",
    },
    {
      title: "Tiền thuê mặt bằng",
      dataIndex: "tienthuematbang",
      key: "tienthuematbang",
      sorter: (a, b) => a.tienthuematbang - b.tienthuematbang,
      sortDirections: ['descend', 'ascend'],
    },
    {
      title: "Tổng phí dịch vụ",
      dataIndex: "tongphidichvu",
      key: "tongphidichvu",
      sorter: (a, b) => a.tongphidichvu - b.tongphidichvu,
      sortDirections: ['descend', 'ascend'],
    },
    {
      title:"Tổng tiền thuê",
      dataIndex:"tongtienthue",
      key:"tongtienthue",
      sorter: (a, b) => a.tongtienthue - b.tongtienthue,
      sortDirections: ['descend', 'ascend'],
    },
  ];

  return (
    <div>
      <Modal
        title={"Xem hóa đơn công ty này theo tháng"}
        visible={idBill}
        footer={""}
        onCancel={onCancelModal}
        destroyOnClose={true}
      >
        <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
          dataSource={billList}
          columns={columns}
          scroll={{ y: 400 }}
          pagination={{
            defaultPageSize: 10,
            showSizeChanger: true,
            pageSizeOptions: ["10", "20", "30"],
          }}
        >
          
        </Table>
      </Modal>
    </div>
  );
};

export default ModalBill;
