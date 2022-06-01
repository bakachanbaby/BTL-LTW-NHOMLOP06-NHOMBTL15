import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

// Lấy nhân viên công ty theo id công ty
export function getemployee(id) {
  return axios.get(`congty/nhanviencongty/get_by_congty?id_congty=${id}`);
}

// Thêm nhân viên công ty theo id công ty
export function postemployee(data, clicked_congty) {
  clicked_congty.id = parseInt(clicked_congty.id);
  clicked_congty.dientichthue = parseInt(clicked_congty.dientichthue);
  console.log(data);
  return axios.post("/congty/nhanviencongty", {
    ma: data.ma,
    ten: data.ten,
    cccd: data.cccd,
    ngaysinh: data.ngaysinh,
    sdt: data.sdt,
    congTy: clicked_congty,
  });
}

// Sửa nhân viên công ty theo id công ty
export function putemployee(data, clicked_congty, id) {
  return axios.put(`/congty/nhanviencongty`, {
    id: id,
    ma: data.ma,
    ten: data.ten,
    cccd: data.cccd, //cho thêm vào so với cái cũ
    ngaysinh: data.ngaysinh,
    sdt: data.sdt,
    congTy: clicked_congty,
  });
}

// Xóa nhân viên công ty theo id công ty
export function deleteemployee(id) {
  return axios.delete(`/congty/nhanviencongty/${id}`);
}
