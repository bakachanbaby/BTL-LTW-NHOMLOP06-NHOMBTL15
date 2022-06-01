import { Button, DatePicker, Form, Input, Modal, notification } from "antd";
import moment from "moment";
import { useState } from "react";
import { getstaff, poststaff, putstaff } from "../../apis/staffApi";

const ModalStaff = (props) => {
  const [addModal, setAddModal] = useState(false);

  const onFinishModal = (staff) => {
    if (addModal) {
      setAddModal(false);
      poststaff(staff)
        .then(() => displayStaff())
        .catch(() => {
          notification["error"]({
            message: "Thêm nhân viên thất bại",
            placement: "topRight",
          });
        });
    }
    if (props.editModal) {
      putstaff(staff, props.editModal.id)
        .then(() => displayStaff(staff))
        .catch(() => {
          notification["error"]({
            message: "Sửa nhân viên thất bại",
            placement: "topRight",
          });
        });
    }
  };

  const displayStaff = () => {
    getstaff()
      .then((response) => {
        let tmp = response.data;
        let res = [];
        tmp.map((item) => {
          res.push({
            ...item,
            ngaysinh: moment(new Date(item.ngaysinh)).format("YYYY-MM-DD"),
          });
        });
        props.setStaffs(res);
        notification["success"]({
          message: addModal
            ? "Thêm nhân viên thành công"
            : "Sửa nhân viên thành công",
          placement: "topRight",
        });
        addModal ? setAddModal(false) : props.setEditModal(null);
      })
      .catch((error) => console.log(error));
  };

  const onCancelModal = () => {
    setAddModal(false);
    props.setEditModal(null);
  };

  return (
    <div>
      <Button
        type="primary"
        style={{ margin: "10px" }}
        onClick={() => setAddModal(true)}
      >
        Thêm nhân viên
      </Button>
      <Modal
        title={addModal ? "Thêm nhân viên" : "Sửa nhân viên"}
        visible={addModal || props.editModal}
        onCancel={onCancelModal} // Ham onCancelModal se duoc goi khi nguoi dung bam nut tat hoac cancel
        destroyOnClose={true}
        footer={null}
      >
        <Form
          name="nest-messages"
          labelCol={{ span: 5 }}
          wrapperCol={{ span: 16 }}
          onFinish={onFinishModal}
          initialValues={props.editModal}
        >
          <Form.Item
            label="Mã"
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
            label="Tên"
            name="ten"
            rules={[
              { required: true, message: "Vui lòng nhập tên nhân viên!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="CCCD"
            name="cccd"
            rules={[{ required: true, message: "Vui lòng nhập cccd!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Ngày sinh"
            name="ngaysinh" 
            rules={[{ required: true, message: "Vui lòng nhập ngày sinh!" }]}
          >
            <Input />
            {/* <DatePicker 
            // selected={moment(this.props.)}
            // onChange={this.handleChangeEnd}
            // defaultValue={moment(props.editModal.ngaysinh)}
            /> */}
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

          <Form.Item
            label="Vị trí"
            name="vitri"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập vị trí của nhân viên!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item wrapperCol={{ offset: 8, span: 16 }} className="form-btn">
            <Button style={{ marginRight: 10 }} onClick={onCancelModal}>
              Đóng
            </Button>
            <Button type="primary" htmlType="submit" className="btn-submit">
              {addModal ? "Thêm" : "Sửa"}
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default ModalStaff;
