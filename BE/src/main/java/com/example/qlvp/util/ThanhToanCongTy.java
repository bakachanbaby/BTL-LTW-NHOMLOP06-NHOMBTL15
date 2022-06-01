package com.example.qlvp.util;

import com.example.qlvp.model.entity.DichVu;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class ThanhToanCongTy {

    private static final Logger logger = LoggerFactory.getLogger(ThanhToanCongTy.class);
    //tính tiền thuê dựa theo mặt bằng
    public double tienThueMatBang(int dienTichThue) {
        return dienTichThue *1000000;

    }

    // tính tổng tiền thuê (của tất cả dịch vụ)
    public double tongPhiDichVu(Long soLuongNhanVien, List<DichVu> dichVu) {
        logger.info("tính tổng phí dịch vụ: {}");
        double tongPhiDichVu = 0;
        for (DichVu dichvu : dichVu) {
            tongPhiDichVu += dichvu.getDonGia() ;
        }
        logger.info("tổng phí dịch vụ là: {}", tongPhiDichVu);
        return  tongPhiDichVu;
    }

    // tính tổng tiền thuê (của tất cả dịch vụ)
    public double tongTienThue(double tienThueMatBang, double tongPhiDichVu ) {
        logger.info("tiền thuê tổng: {}");
        return tienThueMatBang + tongPhiDichVu;
    }


}
