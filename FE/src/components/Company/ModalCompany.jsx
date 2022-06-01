import { Button, Form, Input, InputNumber, Modal, notification } from "antd";
import { useState } from "react";
import { getcompany, postcompany, putcompany } from "../../apis/companyApi";

const ModalCompany = (props) => {
  const [addModal, setAddModal] = useState(false);

  const onCancelModal = () => {
    setAddModal(false);
    props.setEditModal(null);
  };

  const onFinishModal = (company) => {
    if (addModal) {
      setAddModal(false);
      postcompany(company)
        .then(() => displayData())
        .catch(() => {
          notification["error"]({
            message: "Thêm công ty thất bại",
            placement: "topRight",
          });
        });
    }
    if (props.editModal) {
      props.setEditModal(null);
      putcompany(company, props.editModal.id) // company la thong tin cua cong ty nguoi dung muon sua o form ben duoi, props.editModal.id la id cua cong ty muon edit
        .then(() => displayData())
        .catch(() => {
          notification["error"]({
            message: "Sửa công ty thất bại",
            placement: "topRight",
          });
        });
    }
  };

  const displayData = () => {
    getcompany()
      .then((response) => {
        props.setCompanies(response.data);
        notification["success"]({
          message: addModal
            ? "Thêm công ty thành công"
            : "Sửa công ty thành công",
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
        Thêm công ty
      </Button>
      <Modal
        title={addModal ? "Thêm công ty" : "Sửa công ty"}
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
            label="Mã công ty"
            name="ma" // Thuoc tinh cua doi tuong duoc truyen vao. O day la props.editModal
            rules={[{ required: true, message: "Vui lòng nhập mã công ty!" }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Tên công ty"
            name="ten"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập tên công ty!",
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Lĩnh vực"
            name="linhvuchoatdong"
            rules={[
              { required: true, message: "Vui lòng nhập lĩnh vực hoạt động!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Địa chỉ"
            name="diachitrongtoanha"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập địa chỉ!",
              },
            ]}
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
          <Form.Item
            label="DT thuê"
            name="dientichthue"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập diện tích thuê!",
              },
            ]}
          >
            <InputNumber min={1} max={1000} defaultValue={1} style={{ width: '18vw' }} />
          </Form.Item>
          <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
            <Button style={{ marginRight: 10 }} onClick={onCancelModal}>
              Close
            </Button>
            <Button type="primary" htmlType="submit">
              {addModal ? "Thêm" : "Lưu"}
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default ModalCompany;
