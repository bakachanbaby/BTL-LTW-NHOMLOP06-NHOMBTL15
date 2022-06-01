import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

// Lấy danh sách nhân viên dịch vụ
export function getstaffservice(id) {
  return axios.get(`/nhanvien_dichvu/${id}`);
}

// Thêm nhân viên dịch vụ
export function poststaffservice(data, nhanvientoanha, dichvu) {
  return axios.post("/nhanvien_dichvu", {
    mucluong: data.mucluong,
    thanglam: data.thanglam,
    namlam: data.namlam,
    dichvu: dichvu,
    nhanvientoanha: nhanvientoanha,
  });
}

// Sửa nhân viên dịch vụ
export function putstaffservice(data, nhanvientoanha, dichvu, id) {
  return axios.put(`/nhanvien_dichvu`, {
    id: id,
    mucluong: data.mucluong,
    thanglam: data.thanglam,
    namlam: data.namlam,
    dichvu: dichvu,
    nhanvientoanha: nhanvientoanha,
  });
}

export function deletestaffservice(id) {
  return axios.delete(`/nhanvien_dichvu/${id}`);
}
