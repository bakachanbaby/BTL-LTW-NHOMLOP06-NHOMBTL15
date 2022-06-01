import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  notification,
  Select,
} from "antd";
import { useEffect, useState } from "react";
import {
  getcompanyservice,
  postcompanyservice,
  putcompanyservice,
} from "../../apis/companyServiceApi";
import { getservice, getservicebyid } from "../../apis/serviceApi";
const { Option } = Select;

const ModalCompanyService = (props) => {
  const company = props.company;
  const [services, setservices] = useState([]);
  const [addModal, setAddModal] = useState(false);

  useEffect(() => {
    getservice()
      .then((response) => {
        setservices(response.data);
        console.log(response.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const onCancelModal = () => {
    setAddModal(false);
    props.setEditModal(null);
  };

  const onFinishModal = async (companyServices) => {
    const servicechoice = await getservicebyid(companyServices.service);

    if (addModal) {
      setAddModal(false);
      postcompanyservice(companyServices, company, servicechoice.data)
        .then(() => displayData())
        .catch(() => {
          notification["error"]({
            message: "Thêm dịch vụ cho công ty thất bại",
            placement: "topRight",
          });
        });
    }
    if (props.editModal) {
      props.setEditModal(null);
      putcompanyservice(
        companyServices,
        company,
        servicechoice.data,
        props.editModal.id
      ) // employee la thong tin cua cong ty nguoi dung muon sua o form ben duoi, props.editModal.id la id cua cong ty muon edit
        .then(() => displayData())
        .catch(() => {
          notification["error"]({
            message: "Sửa dịch vụ cho công ty thất bại",
            placement: "topRight",
          });
        });
    }
  };

  const displayData = () => {
    getcompanyservice(company.id)
      .then((response) => {
        props.setCompanyServices(response.data);
        notification["success"]({
          message: addModal
            ? "Thêm dịch vụ cho công ty thành công"
            : "Sửa dịch vụ cho công ty thành công",
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
        Thêm dịch vụ cho công ty
      </Button>
      <Modal
        title={addModal ? "Thêm dịch vụ" : "Sửa dịch vụ"}
        visible={addModal || props.editModal}
        onCancel={onCancelModal}
        footer={""}
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
            label="Tháng thuê"
            name="thangthue"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập tháng thuê!",
              },
            ]}
          >
            <InputNumber
              min={1}
              max={12}
              defaultValue={1}
              style={{ width: "18vw" }}
            />
          </Form.Item>
          <Form.Item
            label="Năm thuê"
            name="namthue"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập năm thuê!",
              },
            ]}
          >
            <InputNumber
              min={1990}
              max={2022}
              defaultValue={1990}
              style={{ width: "18vw" }}
            />
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
            <Button style={{ marginRight: 10 }} onClick={onCancelModal}>
              Hủy
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

export default ModalCompanyService;
