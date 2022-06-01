import axios from "axios";
 
axios.defaults.baseURL = "http://localhost:8080/api/v1";
 
// Lấy danh sách lương của nhân viên tòa nha
export function getsalary() {
  return axios.get("/nhanvientoanha/tienluong");
}

// Lấy lương của 1 nhân viên tòa nhà theo id nhân viên tòa nhà
export function getsalarybyid(id) {
  return axios.get(`/nhanvientoanha/tienluong/${id}`);
}
