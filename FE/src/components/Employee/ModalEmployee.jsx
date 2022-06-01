import { Button, Form, Input, Modal, notification } from "antd";
import moment from "moment";
import { useState } from "react";
import { getemployee, postemployee, putemployee } from "../../apis/employeeApi";

const ModalEmployee = (props) => {
  const { company } = props;
  const [addModal, setAddModal] = useState(false);

  const onCancelModal = () => {
    setAddModal(false);
    props.setEditModal(null);
  };

  const onFinishModal = (employee) => {
    if (addModal) {
      console.log(employee);
      setAddModal(false);
      postemployee(employee, company)
        .then(() => displayData())
        .catch((error) => {
          notification["error"]({
            message: "Thêm nhân viên công ty thất bại!",
            placement: "topRight",
          });
        });
    }
    if (props.editModal) {
      props.setEditModal(null);
      putemployee(employee, company, props.editModal.id) // employee la thong tin cua cong ty nguoi dung muon sua o form ben duoi, props.editModal.id la id cua cong ty muon edit
        .then(() => displayData())
        .catch((error) => {
          notification["error"]({
            message: "Sửa nhân viên công ty thất bại!",
            placement: "topRight",
          });
        });
    }
  };

  const displayData = () => {
    console.log("hum");
    getemployee(company.id)
      .then((response) => {
        let tmp = response.data;
        let res = [];
        tmp.map((item) => {
          res.push({
            ...item,
            ngaysinh: moment(new Date(item.ngaysinh)).format("YYYY-MM-DD"),
          });
        });
        props.setEmployees(res);
        notification["success"]({
          message: addModal
            ? "Thêm nhân viên công ty thành công"
            : "Sửa nhân viên công ty thành công",
          placement: "topRight",
        });
        addModal ? setAddModal(false) : props.setEditModal(null);
      })
      .catch((error) => console.log(error));
  };

  return (
    <div>
      <Button
        type="primary"
        style={{ margin: "10px" }}
        onClick={() => {
          setAddModal(true);
        }}
      >
        Thêm nhân viên công ty
      </Button>
      <Modal
        title={addModal ? "Thêm nhân viên" : "Sửa nhân viên"}
        visible={addModal || props.editModal}
        onCancel={onCancelModal}
        footer={null}
        destroyOnClose={true}
      >
        <Form
          name="nest-messages"
          labelCol={{ span: 5 }}
          wrapperCol={{ span: 16 }}
          onFinish={onFinishModal}
          initialValues={props.editModal}
        >

          <Form.Item
            label="Mã NV"
            name="ma"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập mã nhân viên!",
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Tên NV"
            name="ten"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập tên nhân viên công ty!",
              },
            ]}
          >
            <Input
              onClick={() => {
                console.log(props.editModal.id);
              }}
            />
          </Form.Item>
          <Form.Item
            label="CCCD"
            name="cccd"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập căn cước công dân!",
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Ngày sinh"
            name="ngaysinh"
            rules={[{ required: true, message: "Vui lòng nhập ngày sinh!" }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="SĐT"
            name="sdt"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập số điện thoại!",
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item label="Công ty">
            <Input defaultValue={company.ten} disabled />
          </Form.Item>
          <Form.Item wrapperCol={{ offset: 8, span: 16 }} className="form-btn">
            <Button style={{ marginRight: 10 }} onClick={onCancelModal}>
              Close
            </Button>
            <Button type="primary" htmlType="submit" className="btn-submit">
              {addModal ? "Thêm" : "Lưu"}
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default ModalEmployee;
