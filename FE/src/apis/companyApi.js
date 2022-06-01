import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

// Lấy danh sách các công ty
export function getcompany() {
  return axios.get("/congty");
}

// Thêm công ty
export function postcompany(data) {
  data.dientichthue = parseInt(data.dientichthue);
  console.log(data);
  return axios.post("/congty", {
    ma: data.ma,
    ten: data.ten,
    linhvuchoatdong: data.linhvuchoatdong,
    diachitrongtoanha: data.diachitrongtoanha,
    sdt: data.sdt,
    dientichthue: data.dientichthue,
  });
}

// Sửa công ty theo id công ty
export function putcompany(data, id) {
  return axios.put("/congty", {
    id: id,
    ma: data.ma,
    ten: data.ten,
    linhvuchoatdong: data.linhvuchoatdong,
    diachitrongtoanha: data.diachitrongtoanha,
    sdt: data.sdt,
    dientichthue: data.dientichthue,
  });
}

// Xóa công ty theo công ty id
export function deletecompany(id) {
  return axios.delete(`/congty/${id}`);
}


