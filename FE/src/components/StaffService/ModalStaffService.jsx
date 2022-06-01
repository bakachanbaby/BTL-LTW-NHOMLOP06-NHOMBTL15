import { Button, Form, Input, InputNumber, Modal, notification, Select } from "antd";
import "antd/dist/antd.css";
import { useEffect, useState } from "react";
import { getservice, getservicebyid } from "../../apis/serviceApi";
import { poststaffservice, putstaffservice, getstaffservice } from "../../apis/staffServiceApi";
const { Option } = Select;



const Modalstaffservice = (props) => {
  const [addModal, setAddModal] = useState(false);
  const [services, setservices] = useState([]);
  const { staff } = props;

  useEffect(() => {
    getservice()
      .then((response) => {
        setservices(response.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const onFinishModal = async (staffservice) => {
    const servicechoice = await getservicebyid(staffservice.service);
    console.log(servicechoice.data);
    console.log(staffservice);
    console.log(staff);
    if (addModal) {
      setAddModal(false);
      poststaffservice(staffservice, staff, servicechoice.data)
        .then(() => displayData())
        .catch((error) => {
          notification["error"]({
            message: "Thêm dịch vụ thất bại",
            placement: "topRight",
          });
          console.log(error);
        });
    }
    if (props.editModal) {
      props.setEditModal(null);
      putstaffservice(
        staffservice,
        staff,
        servicechoice.data,
        props.editModal.id
      ) // company la thong tin cua cong ty nguoi dung muon sua o form ben duoi, props.editModal.id la id cua cong ty muon edit
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
    getstaffservice(staff.id)
      .then((response) => {
        props.setStaffservice(response.data);
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

  const onCancelModal = () => {
    setAddModal(false);
    props.setEditModal(null);
  };

  return (
    <>
      <Button
        type="primary"
        style={{ margin: "10px" }}
        onClick={() => {
          setAddModal(true);
          
        }}
      >
        Thêm dịch vụ mới cho nhân viên
      </Button>
      <Modal
        title={addModal ? "Thêm dịch vụ mới" : "Sửa dịch vụ"}
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
            label="Mức lương"
            name="mucluong"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập mức lương!",
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Tháng làm"
            name="thanglam"
            rules={[
              { required: true, message: "Vui lòng nhập tháng làm!" },
            ]}
          >
            <InputNumber min={1} max={12} defaultValue={1}  style={{ width: '18vw' }} />
          </Form.Item>
          <Form.Item
            label="Năm làm"
            name="namlam"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập năm làm!",
              },
            ]}
          >
            <InputNumber min={1900} max={2022} defaultValue={2022}  style={{ width: '18vw' }} />
          </Form.Item>

          <Form.Item
            label="Dịch vụ"
            name="service"
            rules={[{ required: true, message: "Vui lòng chọn dịch vụ!" }]}
          >
            <Select defaultValue="Lựa chọn dịch vụ" style={{ width: "10vw" }}>
              {services.map((province) => (
                <Option key={province.id}>{province.ten}</Option>
              ))}
            </Select>
          </Form.Item>

          <Form.Item wrapperCol={{ offset: 8, span: 16 }} className="form-btn">
            <Button style={{ marginRight: 10 }} onClick={()=>{
              onCancelModal();
             
            }}>
              Đóng
            </Button>
            <Button type="primary" htmlType="submit" className="btn-submit">
              {addModal ? "Thêm" : "Sửa"}
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};

export default Modalstaffservice;
