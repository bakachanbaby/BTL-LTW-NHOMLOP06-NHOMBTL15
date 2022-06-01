import { Modal, Table } from "antd";
import { useEffect, useState } from "react";
import { getsalarybyid } from "../../apis/salaryApi";

const ModalEmployee = (props) => {
  const { idStaff } = props;
  const [salaryList, setSalaryList] = useState();

  useEffect(() => {
    getsalarybyid(idStaff)
      .then((response) => {
        setSalaryList(response.data);
      })
      .catch((error) => console.log(error));
  });

  const onCancelModal = () => {
    props.setIdStaff("");
  };
  const columns = [
    {
      title: "Tháng",
      dataIndex: "thang",
      key: "thang",
    },
    {
      title: "Tổng tiền lương",
      dataIndex: "tongtienluong",
      key: "tongtienluong",
      sorter: (a, b) => a.tongtienluong - b.tongtienluong,
      sortDirections: ["descend", "ascend"],
    },
  ];
  return (
    <div>
      <Modal
        title={"Xem lương của nhân viên này theo tháng"}
        visible={idStaff}
        footer={""}
        onCancel={onCancelModal}
        destroyOnClose={true}
      >
        <Table //dataIndex se duoc su dung nhu la ten cua 1 thuoc tinh cua doi tuong nam trong 1 ban ghi tren bang
          dataSource={salaryList}
          scroll={{ y: 400 }}
          columns={columns}
          pagination={{
            defaultPageSize: 10,
            showSizeChanger: true,
            pageSizeOptions: ["10", "20", "30"],
          }}
        ></Table>
      </Modal>
    </div>
  );
};

export default ModalEmployee;
