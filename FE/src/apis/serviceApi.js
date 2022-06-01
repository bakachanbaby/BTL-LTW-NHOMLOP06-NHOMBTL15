import axios from "axios";
 
axios.defaults.baseURL = "http://localhost:8080/api/v1";
 
// Lấy danh sách dịch vụ 
export function getservice() {
  return axios.get("/dichvu");
}
// Lấy dịch vụ theo id dịch vụ
export function getservicebyid(id) {
  return axios.get(`/dichvu/${id}`);
}
 
// Thêm dịch vụ
export function postservice(data) {
  data.dongia=parseInt(data.dongia)
  console.log(data);
  return axios.post("/dichvu", {
    ma: data.ma,
    ten: data.ten,
    loaidichvu: data.loaidichvu,
    dongia: data.dongia,
    mota: data.mota,
  });
}

// Sửa dịch vụ
export function putservice(data, id) {
  data.dongia=parseInt(data.dongia);
  console.log(data);
  return axios.put(`/dichvu`, {
    id:id,
    ma: data.ma,
    ten: data.ten,
    loaidichvu: data.loaidichvu,
    dongia: data.dongia, 
    mota: data.mota, 
  });
}

// Xóa dịch vụ theo id dịch vụ
export function deleteservice(id) {
  return axios.delete(`/dichvu/${id}`);
}
