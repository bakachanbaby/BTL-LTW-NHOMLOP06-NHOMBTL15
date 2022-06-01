import { Button, Form, Input, InputNumber, Modal, notification } from "antd";
import { useState } from "react";
import { getservice, postservice, putservice } from "../../apis/serviceApi";

const ModalService = (props) => {
  const [addModal, setAddModal] = useState(false);

  const onCancelModal = () => {
    setAddModal(false);
    props.setEditModal(null);
  };

  const onFinishModal = (service) => {
    if (addModal) {
      setAddModal(false);
      postservice(service)
        .then(() => displayData())
        .catch(() => {
          notification["error"]({
            message: "Thêm dịch vụ thất bại",
            placement: "topRight",
          });
        });
    }
    if (props.editModal) {
      props.setEditModal(null);
      putservice(service, props.editModal.id) // company la thong tin cua cong ty nguoi dung muon sua o form ben duoi, props.editModal.id la id cua cong ty muon edit
        .then(() => displayData())
        .catch(() => {
          notification["error"]({
            message: "Sửa dịch vụ thất bại",
            placement: "topRight",
          });
        });
    }
  };

  const displayData = () => {
    getservice()
      .then((response) => {
        props.setServices(response.data);
        notification["success"]({
          message: addModal
            ? "Thêm dịch vụ thành công"
            : "Sửa dịch vụ thành công",
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
        onClick={() => setAddModal(true)}
      >
        Thêm dịch vụ
      </Button>
      <Modal
        title={addModal ? "Thêm dịch vụ" : "Sửa dịch vụ"}
        visible={addModal || props.editModal}
        onCancel={onCancelModal} // Ham onCancelModal se duoc goi khi nguoi dung bam nut tat hoac cancel
        destroyOnClose={true}
        footer={null}
      >
        <Form //Khi hoan tat form va submit, tat ca du lieu se duoc goi vao 1 doi tuong va chui vao function onFinishModal(). Moi mot doi tuong se co thuoc tinh duoc dat sau prop 'name' cua form item
          name="nest-messages"
          labelCol={{ span: 5 }}
          wrapperCol={{ span: 16 }}
          onFinish={onFinishModal}
          initialValues={props.editModal}
        >
          <Form.Item
            label="Mã"
            name="ma" // Thuoc tinh cua doi tuong duoc truyen vao. O day la props.editModal
            rules={[{ required: true, message: "Vui lòng nhập mã dịch vụ!" }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Tên"
            name="ten"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập tên dịch vụ!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Loại"
            name="loaidichvu"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập loại dịch vụ!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Đơn giá"
            name="dongia"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập đơn giá dịch vụ!",
              },
            ]}
          >
            <InputNumber min={1} max={10000000} defaultValue={1}  style={{ width: '18vw' }} />
          </Form.Item>

          <Form.Item
            label="Mô tả"
            name="mota"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập mô tả dịch vụ!",
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

export default ModalService;
