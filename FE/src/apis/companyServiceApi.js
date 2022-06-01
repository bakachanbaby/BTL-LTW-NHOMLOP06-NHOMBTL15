import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

// Lấy dịch vụ công ty theo id công ty
export function getcompanyservice(id) {
  return axios.get(`congty/dichvu/${id}`);
}

// Thêm dịch vụ công ty của công ty
export function postcompanyservice(data, congty, dichvu) {
  console.log(congty);
  return axios.post("/congty/dichvu", {
    congty: congty,
    dichvu: dichvu,
    thangthue: data.thangthue,
    namthue: data.namthue,
  });
}

// Sửa dịch vụ công ty của công ty
export function putcompanyservice(data, congty, dichvu, id) {
  data.thangthue = parseInt(data.thangthue);
  console.log(data);
  return axios.put(`/congty/dichvu`, {
    id: id,
    congty: congty,
    dichvu: dichvu,
    thangthue: data.thangthue,
    namthue: data.namthue,
  });
}

// Xóa dịch vụ công ty của id công ty
export function deletecompanyservice(id) {
  return axios.delete(`/congty/dichvu/${id}`);
}
