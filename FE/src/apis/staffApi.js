import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

// Lấy danh sách nhân viên tòa nhà
export function getstaff() {
  return axios.get("/nhanvientoanha");
}

// Thêm nhân viên tòa nhà
export function poststaff(data) {
  data.ngaysinh = new Date(data.ngaysinh)
  return axios.post("/nhanvientoanha", {
    ma: data.ma,
    ten: data.ten,
    cccd: data.cccd,
    ngaysinh: data.ngaysinh,
    sdt: data.sdt,
    vitri: data.vitri,
  });
}

// Sửa nhân viên tòa nhà
export function putstaff(data, id) {
  return axios.put(`/nhanvientoanha`, {
    id: id,
    ma: data.ma,
    ten: data.ten,
    cccd: data.cccd,
    ngaysinh: data.ngaysinh,
    sdt: data.sdt,
    vitri: data.vitri,
  });
}

// Xóa nhân viên tòa nhà
export function deletestaff(id) {
  return axios.delete(`/nhanvientoanha/${id}`);
}
