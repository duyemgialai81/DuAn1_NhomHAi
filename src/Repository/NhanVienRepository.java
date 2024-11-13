/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.NhanVienEntity;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class NhanVienRepository {
   public ArrayList<NhanVienEntity> getAll() {
    ArrayList<NhanVienEntity> ls = new ArrayList<>();
    String sql = """
                 select ma_nhan_vien, ten_nhan_vien, so_dien_thoai, email, dia_chi , id_role
                 from NhanVien nv
                 join Role r on nv.id_role = r.id_ma_role
                 """;
    try (Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
// Đặt giá trị cho tham số id_role
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NhanVienEntity nv = new NhanVienEntity();
            nv.setMaNhanVien(rs.getString("ma_nhan_vien"));
            nv.setTenNhanVien(rs.getString("ten_nhan_vien"));
            nv.setSoDienThoai(rs.getString("so_dien_thoai"));
            nv.setEmail(rs.getString("email"));
            nv.setDiaChi(rs.getString("dia_chi"));
            nv.setIdRole(rs.getInt("id_role"));  // Nếu bạn có thuộc tính idRole trong NhanVienEntity
            ls.add(nv);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ls;
}

}
