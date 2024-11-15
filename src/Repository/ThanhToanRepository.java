/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.HoaDon.ThanhToanEntity;
import Entity.HoaDon.XemHoaDonTao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class ThanhToanRepository {
     ThanhToanEntity tt = new ThanhToanEntity();
    public void taoThanhToan(int maNhanVien, ArrayList<XemHoaDonTao> hoaDon, String phuongThuc, String trangThai) {
    float tongTien = tt.tinhTongTien(hoaDon);  
    LocalDate ngayThanhToan = LocalDate.now(); 

    String sql = """
                 INSERT INTO ThanhToan (ma_nhan_vien, ngay_thanh_toan, so_tien, phuong_thuc, trang_thai)
                 VALUES (?, GETDATE(), ?, ?, ?)
                 """;
    try (Connection con = ketnoi.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, maNhanVien); 
        ps.setDate(2, java.sql.Date.valueOf(ngayThanhToan)); 
        ps.setFloat(3, tongTien); 
        ps.setString(4, phuongThuc);  
        ps.setString(5, trangThai); 

        ps.executeUpdate(); 
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
