package com.example.qlvp.validation;
import com.example.qlvp.model.dto.*;
import com.example.qlvp.model.entity.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Component
public class Validation {

    private static boolean checkDate(final String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        boolean res = false;
        try {
            sdf.parse(date);
            sdf.setLenient(false);
            res = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    // kiểm tra giá trị nhập vào của nhân viên tòa nhà
    public List<String> getInputError(NhanVienToaNhaRequestDto requestDto) {
        List<String> list = new ArrayList<>();
        if (requestDto.getTen() == null) list.add("tên nhân viên bị trống");
        if (requestDto.getSdt() == null) list.add("số điện thoại bị trống");
        if (requestDto.getCccd() == null)  list.add("căn cước công dân bị trống");
        if (requestDto.getMa() == null)  list.add("mã nhân viên bị trống");
        if (requestDto.getNgaySinh().toString() == null)  list.add("ngày sinh bị trống");
        if (requestDto.getViTri() == null)  list.add("vị trí bị trống");
        return list;
    }
//// kiểm tra giá trị nhập vào của công ty
    public List<String> getInputError(CongTyRequestDto requestDto) {
        List<String> list = new ArrayList<>();
        if (requestDto.getSdt() == null) list.add("số điện thoại bị trống");
        if (requestDto.getMa() == null) list.add("mã công ty bị trống");
        if (requestDto.getDiaChiTrongToaNha() == null) list.add("địa chỉ trong nhà bị trống");
        if (requestDto.getLinhVucHoatDong() == null) list.add("lĩnh vực hoạt động bị trống");
        if (requestDto.getTen() == null) list.add("tên công ty bị trống");
        if (requestDto.getDienTichThue() <= 10) list.add("phải thuê trên 10m2");
        return list;
    }

    // kiểm tra giá trị nhập vào của nhân viên công ty
    public List<String> getInputError(NhanVienCongTyRequestDto requestDto) {
        List<String> list = new ArrayList<>();
        if (requestDto.getSdt() == null) list.add("số điện thoại bị trống");
        if (requestDto.getMa() == null) list.add("mã nhân viên bị trống");
        if (requestDto.getCccd() == null) list.add("căn cước công dân bị trống");
        if (requestDto.getNgaySinh().toString() == null) list.add("ngày sinh bị trống");
        else if (!checkDate(requestDto.getNgaySinh().toString()))
            list.add("ngày sinh nhập không hợp lệ");


        if (requestDto.getTen() == null) list.add("tên công ty bị trống");
        if (requestDto.getCongTy() == null) list.add("không có thông tin của công ty");
        return list;
    }

    // kiểm tra giá trị nhập vào của nhân viên _ dịch vụ
    public List<String> getInputError(NhanVien_DichVuRequestDto requestDto) {
        List<String> list = new ArrayList<>();
        if (requestDto.getMucLuong() <= 0) list.add("nhập lương phải >0");
        if (requestDto.getDichVu() == null) list.add("thông tin dịch vụ bị trống");
        if (requestDto.getNhanVienToaNha() == null) list.add("thông tin nhân viên bị trống");
        if (requestDto.getThangLam() < 0 || requestDto.getThangLam() > 12)
            list.add("nhập tháng từ tháng 1 đến 12");
        if (requestDto.getNamLam() < 0) list.add("Nhập năm làm phải là số dương");
        return list;
    }

    // kiểm tra giá trị nhập vào của dịch vụ
    public List<String> getInputError(DichVuRequestDto requestDto) {
        List<String> list = new ArrayList<>();
        if (requestDto.getLoaiDichVu() == null) list.add("loại dịch vụ bị trống");
        if (requestDto.getMa() == null) list.add("mã dịch vụ bị trống");
        if (requestDto.getMoTa() == null) list.add("mô tả bị trống");
        if (requestDto.getTen() == null) list.add("tên công ty bị trống");
        if (requestDto.getDonGia() < 0) list.add("Tiền dịch vụ phải là số dương");
        return list;
    }

    // kiểm tra giá trị nhập vào của dịch vụ_ công ty
    public List<String> getInputError(DichVu_CongTyRequestDto requestDto) {
        List<String> list = new ArrayList<>();
        if (requestDto.getDichVu() == null) list.add("thông tin dịch vụ bị trống");
        if (requestDto.getCongTy() == null) list.add("thông tin công ty bị trống");
        if (requestDto.getThangthue() < 0 || requestDto.getThangthue() > 12)
            list.add("nhập tháng từ tháng 1 đến 12");
        if (requestDto.getNamthue() < 0) list.add("Nhập năm làm phải là số dương");
        return list;
    }


}
