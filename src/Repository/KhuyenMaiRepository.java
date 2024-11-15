/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Entity.KhuyenMai.KhuyenMaiEntity;
import Entity.SanPham.SanPhamEntity;
import KetNoiSQL.ketnoi;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author SingPC
 */
public class KhuyenMaiRepository {
public boolean ThemVoucherVaCapNhatSanPham( ) {
    KhuyenMaiEntity km = new KhuyenMaiEntity();
    int checkVoucher = 0;
    int idVoucher = 0; // Biến lưu id_voucher vừa chèn vào
    String insertVoucherSQL = """
                 INSERT INTO voucher (ten_voucher, chuong_trinh_khuyen_mai, gia_tri, trang_thai, ngay_bat_dau, ngay_ket_thuc, mo_ta, loai_gia_tri, ma_san_pham)
                 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                 """;
    String updateSanPhamSQL = """
                 UPDATE sanPham
                 SET ma_voucher = ?
                 WHERE ma_san_pham= ?
                 """;
    try (Connection con = ketnoi.getConnection()) {
        con.setAutoCommit(false);
        try (PreparedStatement ps = con.prepareStatement(insertVoucherSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, km.getTenVouCher());
            ps.setString(2, km.getTruongTrinhKhuyenMai());
            ps.setFloat(3, km.getGiaTri());
            ps.setString(4, km.getTrangThai());
            ps.setObject(5, km.getNgayBatDau());
            ps.setObject(6, km.getNgayKetThuc());
            ps.setString(7, km.getMoTa());
            ps.setString(8, km.getLoaiTriGia());
            ps.setInt(9, km.getMaSanPham());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idVoucher = rs.getInt(1);
                    }
                }
            }
        }
        if (idVoucher > 0) {
            try (PreparedStatement psUpdate = con.prepareStatement(updateSanPhamSQL)) {
                psUpdate.setInt(1, idVoucher);
                psUpdate.setInt(2, km.getMaSanPham());
                psUpdate.executeUpdate();
            }
        }
        con.commit();
        checkVoucher = 1; 
    } catch (Exception e) {
        try (Connection con = ketnoi.getConnection()) {
            con.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    }

    return checkVoucher > 0;
}
public ArrayList<SanPhamEntity>layDanhSachSanPham(){
    ArrayList<SanPhamEntity> ls = new ArrayList<>();
    String sql = """
                 select ma_san_pham, ten_san_pham
                 from sanpham 
                 """;
    try {
        Connection con = ketnoi.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs  = ps.executeQuery();
        while(rs.next()){
            SanPhamEntity sp = new SanPhamEntity();
            sp.setMaSanPham(rs.getString("ma_san_pham"));
            sp.setTenSanPham(rs.getString("ten_san_pham"));
            ls.add(sp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ls;
}


}
