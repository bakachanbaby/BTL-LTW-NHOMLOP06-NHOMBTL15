import axios from "axios";
 
axios.defaults.baseURL = "http://localhost:8080/api/v1";

// Lấy danh sách hóa đơn của các công ty
export function getbill() {
  return axios.get("/congty/hoadon");
}

// Lấy danh sách hóa đơn của công ty theo id
export function getbillbyid(id) {
  return axios.get(`/congty/hoadon/${id}`);
}
