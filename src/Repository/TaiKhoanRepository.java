/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.NhanVienEntity;
import Entity.TaiKhoanEntity;
import GiaoDien.LuuThongTinDangNhap;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class TaiKhoanRepository {
//    public boolean dangNhap(String tenDangNhap, String matKhau){
//        String sql = """
//                     select * 
//                     from TaiKhoan join Role on TaiKhoan.maRole = Role.maRole
//                     where tenTaiKhoan =? and matKhau =?
//                     """;
//        try {
//            Connection con = ketnoi.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setObject(1, tenDangNhap);
//            ps.setObject(2, matKhau);
//           ResultSet rs = ps.executeQuery();
//           while(rs.next()){
//               
//           }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//      return false;
//    }
    // Mã đăng nhập với vai trò là chuỗi (nvarchar hoặc varchar)
//public boolean dangNhap(String tenDangNhap, String matKhau) {
//    // Chắc chắn vai trò ở đây là một chuỗi chứ không phải số
//    String sql = "SELECT t.*, r.tenRole FROM TaiKhoan t " +
//             "JOIN Role r ON t.maRole = r.idMaRole " +
//             "WHERE t.tenTaiKhoan = ? AND t.matKhau = ?";
//
//    try (Connection conn = ketnoi.getConnection();
//         PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//        stmt.setString(1, tenDangNhap);
//        stmt.setString(2, matKhau);
//        ResultSet rs = stmt.executeQuery();
//
//        if (rs.next()) {
//            String vaiTro = rs.getString("maRole"); // Đảm bảo vai trò ở đây là chuỗi
//            System.out.println("Đăng nhập thành công! Vai trò: " + vaiTro);
//            return true;
//        } else {
//            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
//            return false;
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        return false;
//    }
//}
//public boolean kiemTraQuyen( String quyenCanKiemTra) {
//        // Lấy tên quyền từ bảng Role
//        String sql = "SELECT tenRole FROM Role WHERE idMaRole = ?";
//        try (Connection conn = ketnoi.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, quyenCanKiemTra);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                String tenRole = rs.getString("tenRole");
//                return tenRole.equalsIgnoreCase(quyenCanKiemTra);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false; // Không tìm thấy quyền
//    }
public boolean dangNhap(String tenDangNhap, String matKhau) {
        String sql = """
                     select ten_role
                     from Role
                     join NhanVien on NhanVien.id_ma_nhan_vien = Role.id_ma_role
                     where nhanvien.ten_tai_khoan = ? and nhanvien.mat_khau = ? 
                     """;

        try (Connection conn = ketnoi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String vaiTro = rs.getString("ten_role"); // Lấy vai trò
                System.out.println("Đăng nhập thành công! Vai trò: " + vaiTro);
                return true;
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
public String layTenNhanVien(String taiKhoan) {
    String maNhanVien = null;
    String sql = "SELECT ten_nhan_vien FROM NhanVien WHERE ten_tai_khoan = ?";
    try (Connection con = ketnoi.getConnection()){
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, taiKhoan);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            maNhanVien = rs.getString("ten_nhan_vien");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return maNhanVien;
}
public int layIDNhaanVien(String taiKhoan) {
    int idNhanVien = -1;
    String sql = "select id_ma_nhan_vien from NhanVien where ten_tai_khoan = ?";
    try (Connection con = ketnoi.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, taiKhoan);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idNhanVien = rs.getInt("id_ma_nhan_vien");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return idNhanVien;
}


//public ArrayList<NhanVienEntity> LaytenNhanVien(String taiKhoan) {
//    ArrayList<NhanVienEntity> ls = new ArrayList<>();
//    String sql = "SELECT ten_nhan_vien FROM NhanVien WHERE taiKhoan = ?";
//    try (Connection con = ketnoi.getConnection()){
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setString(1, taiKhoan);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//          NhanVienEntity sp = new NhanVienEntity();
//          sp.setTenNhanVien(rs.getString("ten_nhan_vien"));
//          ls.add(sp);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return ls;
//}


    // Phương thức lấy vai trò
    public String layVaiTro(String tenTaiKhoan) {
        String sql = """
                     select ten_role
                     from Role
                     join NhanVien on NhanVien.id_ma_nhan_vien = Role.id_ma_role
                     where nhanvien.ten_tai_khoan = ?
                     """;
        String tenRole = null;

        try (Connection conn = ketnoi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenTaiKhoan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tenRole = rs.getString("ten_role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenRole;
    }

    // Phương thức kiểm tra quyền
    public boolean kiemTraQuyen(String tenRole, String quyenCanKiemTra) {
        return tenRole != null && tenRole.equalsIgnoreCase(quyenCanKiemTra);
    }

}
