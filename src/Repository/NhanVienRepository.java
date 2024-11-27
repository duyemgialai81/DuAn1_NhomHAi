/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.NhanVien.NhanVienEntity;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class NhanVienRepository {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
public ArrayList<NhanVienEntity> getAll() {
        ArrayList<NhanVienEntity> ls = new ArrayList<>();
        String sql = """
                 select ma_nhan_vien, ten_nhan_vien, so_dien_thoai, email, dia_chi , id_role, trang_thai, ngay_sinh, gioi_tinh, ten_role
                                  from NhanVien nv
                                  join Role r on nv.id_role = r.id_ma_role where trang_thai = N'Hoạt động'
                 """;
        try {
            con = ketnoi.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maNhanVien, tenNhanVien, soDienThoai, ngaySinh, email, diaChi, trangThai, vaiTro;
                boolean gioiTinh;
                int idRole;
                maNhanVien = rs.getString("ma_nhan_vien");
                tenNhanVien = rs.getString("ten_nhan_vien");
                soDienThoai = rs.getString("so_dien_thoai");
                ngaySinh = rs.getString("ngay_sinh");
                email = rs.getString("email");
                diaChi = rs.getString("dia_chi");
                trangThai = rs.getString("trang_thai");
                vaiTro = rs.getString("ten_role");
                gioiTinh = rs.getBoolean("gioi_tinh");
//                nv.setMaNhanVien(rs.getString("ma_nhan_vien"));
//                nv.setTenNhanVien(rs.getString("ten_nhan_vien"));
//                nv.setSoDienThoai(rs.getString("so_dien_thoai"));
//                nv.setEmail(rs.getString("email"));
//                nv.setDiaChi(rs.getString("dia_chi"));
//                nv.setTrangThai(rs.getString("trang_thai"));
//                nv.setNgaySinh(rs.getString("ngay_sinh"));
//                nv.setGioiTinh(rs.getBoolean("gioi_tinh"));
//                nv.setVaiTro(rs.getString("ten_role"));
//                nv.setIdRole(rs.getInt("id_role"));
                // Nếu bạn có thuộc tính idRole trong NhanVienEntity
                NhanVienEntity nv = new NhanVienEntity(maNhanVien, tenNhanVien, soDienThoai, ngaySinh, gioiTinh, email, diaChi, trangThai, vaiTro);
                ls.add(nv);
            }
            return ls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVienEntity> getAll2() {
        ArrayList<NhanVienEntity> ls = new ArrayList<>();
        String sql = """
                 select ma_nhan_vien, ten_nhan_vien, so_dien_thoai, email, dia_chi , id_role, trang_thai, ngay_sinh, gioi_tinh, ten_role
                                  from NhanVien nv
                                  join Role r on nv.id_role = r.id_ma_role where trang_thai = N'Nghỉ làm'
                 """;
        try (Connection con = ketnoi.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
// Đặt giá trị cho tham số id_role

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity();
                nv.setMaNhanVien(rs.getString("ma_nhan_vien"));
                nv.setTenNhanVien(rs.getString("ten_nhan_vien"));
                nv.setSoDienThoai(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setDiaChi(rs.getString("dia_chi"));
                nv.setTrangThai(rs.getString("trang_thai"));
                nv.setNgaySinh(rs.getString("ngay_sinh"));
                nv.setGioiTinh(rs.getBoolean("gioi_tinh"));
                nv.setVaiTro(rs.getString("ten_role"));
//                nv.setIdRole(rs.getInt("id_role"));  // Nếu bạn có thuộc tính idRole trong NhanVienEntity
                ls.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public ArrayList<NhanVienEntity> timNV(String maNV, String gioiTinh, String vt) {
        ArrayList<NhanVienEntity> ls = new ArrayList<>();
        String sql = """
                 SELECT ma_nhan_vien, ten_nhan_vien, so_dien_thoai, email, dia_chi, id_role, trang_thai, ngay_sinh, gioi_tinh, ten_role
                 FROM NhanVien nv
                 JOIN Role r ON nv.id_role = r.id_ma_role
                 WHERE trang_thai = N'Hoạt động' AND ma_nhan_vien LIKE ?
                 """;
        if (gioiTinh != null && !gioiTinh.equalsIgnoreCase("Tất cả")) {
            sql += " AND gioi_tinh = ?";
        }
        if (vt != null && !vt.equalsIgnoreCase("Tất cả")) {
            sql += " AND ten_role = ?";
        }

        try (Connection con = ketnoi.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + maNV + "%");
            int paramIndex = 2;
            if (gioiTinh != null && !gioiTinh.equalsIgnoreCase("Tất cả")) {
                boolean gtBit = "Nam".equals(gioiTinh);
                ps.setBoolean(paramIndex++, gtBit);
            }
            if (vt != null && !vt.equalsIgnoreCase("Tất cả")) {
                ps.setString(paramIndex++, vt);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity();
                nv.setMaNhanVien(rs.getString("ma_nhan_vien"));
                nv.setTenNhanVien(rs.getString("ten_nhan_vien"));
                nv.setSoDienThoai(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setDiaChi(rs.getString("dia_chi"));
                nv.setTrangThai(rs.getString("trang_thai"));
                nv.setNgaySinh(rs.getString("ngay_sinh"));
                nv.setGioiTinh(rs.getBoolean("gioi_tinh"));
                nv.setVaiTro(rs.getString("ten_role"));
//                nv.setIdRole(rs.getInt("id_role"));
                ls.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
        return ls;
    }

    public ArrayList<NhanVienEntity> timNV2(String maNV, String gioiTinh, String vt) {
        ArrayList<NhanVienEntity> ls = new ArrayList<>();
        String sql = """
                 SELECT ma_nhan_vien, ten_nhan_vien, so_dien_thoai, email, dia_chi, id_role, trang_thai, ngay_sinh, gioi_tinh, ten_role
                 FROM NhanVien nv
                 JOIN Role r ON nv.id_role = r.id_ma_role
                 WHERE trang_thai = N'Nghỉ làm' AND ma_nhan_vien LIKE ?
                 """;
        if (gioiTinh != null && !gioiTinh.equalsIgnoreCase("Tất cả")) {
            sql += " AND gioi_tinh = ?";
        }
        if (vt != null && !vt.equalsIgnoreCase("Tất cả")) {
            sql += " AND ten_role = ?";
        }

        try (Connection con = ketnoi.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + maNV + "%");
            int paramIndex = 2;
            if (gioiTinh != null && !gioiTinh.equalsIgnoreCase("Tất cả")) {
                boolean gtBit = "Nam".equals(gioiTinh);
                ps.setBoolean(paramIndex++, gtBit);
            }
            if (vt != null && !vt.equalsIgnoreCase("Tất cả")) {
                ps.setString(paramIndex++, vt);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity();
                nv.setMaNhanVien(rs.getString("ma_nhan_vien"));
                nv.setTenNhanVien(rs.getString("ten_nhan_vien"));
                nv.setSoDienThoai(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setDiaChi(rs.getString("dia_chi"));
                nv.setTrangThai(rs.getString("trang_thai"));
                nv.setNgaySinh(rs.getString("ngay_sinh"));
                nv.setGioiTinh(rs.getBoolean("gioi_tinh"));
                nv.setVaiTro(rs.getString("ten_role"));
//                nv.setIdRole(rs.getInt("id_role"));
                ls.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
        return ls;
    }

   public int update(String manv, NhanVienEntity nvE) {
        String sql = """
                    UPDATE NhanVien SET 
                        ten_nhan_vien = ?,
                        so_dien_thoai = ?,
                        email = ?, 
                        dia_chi = ?,
                        trang_thai = ?,
                        mat_khau = ?,
                        ngay_sinh = ?,
                        gioi_tinh = ?
                    WHERE ma_nhan_vien = ?;
                """;

        String updateRoleSQL = """
                        UPDATE Role 
                        SET ten_role = ? 
                        WHERE id_ma_role = (
                            SELECT id_role 
                            FROM NhanVien 
                            WHERE ma_nhan_vien = ?
                        );
                """;

        try (Connection con = ketnoi.getConnection()) {
            // Cập nhật bảng NhanVien
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nvE.getTenNhanVien());
                ps.setString(2, nvE.getSoDienThoai());
                ps.setString(3, nvE.getEmail());
                ps.setString(4, nvE.getDiaChi());
                ps.setString(5, nvE.getTrangThai());
                ps.setString(6, nvE.getMatKhau());
                ps.setString(7, nvE.getNgaySinh());
                ps.setBoolean(8, nvE.isGioiTinh());
                ps.setString(9, manv);
                ps.executeUpdate();
            }

            // Cập nhật bảng Role
            try (PreparedStatement ps2 = con.prepareStatement(updateRoleSQL)) {
                ps2.setString(1, nvE.getVaiTro());
                ps2.setString(2, manv);
                ps2.executeUpdate();
            }

            return 1; // Trả về giá trị thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Trả về giá trị thất bại
        }
    }

    public int them(NhanVienEntity nv) {
        String sqlNhanVien = """
                    INSERT INTO NhanVien(ten_nhan_vien, so_dien_thoai, email, dia_chi, mat_khau, trang_thai, ngay_sinh, gioi_tinh, id_role, ten_tai_khoan) 
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;

        String sqlRole = """
                    MERGE INTO Role AS target
                    USING (SELECT ? AS ten_role) AS source (ten_role)
                    ON (target.ten_role = source.ten_role)
                    WHEN MATCHED THEN 
                        UPDATE SET ten_role = source.ten_role
                    WHEN NOT MATCHED THEN 
                        INSERT (ten_role) VALUES (source.ten_role)
                    OUTPUT inserted.id_ma_role;
                """;

        int result = 0;
        try (Connection con = ketnoi.getConnection()) {
            con.setAutoCommit(false); // Bắt đầu giao dịch

            int roleId = 0;
            // Thêm hoặc cập nhật vai trò trong bảng Role
            try (PreparedStatement psRole = con.prepareStatement(sqlRole)) {
                psRole.setString(1, nv.getVaiTro());
                try (ResultSet generatedKeys = psRole.executeQuery()) {
                    if (generatedKeys.next()) {
                        roleId = generatedKeys.getInt(1);
                    }
                }

                // Giả sử mã nhân viên tự tăng được lấy sau khi thêm
                // Lấy mã nhân viên đã tự động tăng (nếu cần)
                int maNhanVienMoi = 0;
                String sqlGetMaNV = "SELECT IDENT_CURRENT('NhanVien') AS ma_nhan_vien";
                try (PreparedStatement psGetMaNV = con.prepareStatement(sqlGetMaNV); ResultSet rsMaNV = psGetMaNV.executeQuery()) {
                    if (rsMaNV.next()) {
                        maNhanVienMoi = rsMaNV.getInt("ma_nhan_vien");
                    }
                }

                // Tạo tên tài khoản tự động: chữ cái đầu tiên của tên chính + mã nhân viên
                String[] tenParts = nv.getTenNhanVien().split("\\s+");
                String tenChinh = tenParts[tenParts.length - 1].toLowerCase() + maNhanVienMoi;

                // Thêm dữ liệu vào bảng NhanVien
                try (PreparedStatement psNhanVien = con.prepareStatement(sqlNhanVien)) {
                    psNhanVien.setString(1, nv.getTenNhanVien());
                    psNhanVien.setString(2, nv.getSoDienThoai());
                    psNhanVien.setString(3, nv.getEmail());
                    psNhanVien.setString(4, nv.getDiaChi());
                    psNhanVien.setString(5, nv.getMatKhau());
                    psNhanVien.setString(6, nv.getTrangThai());
                    psNhanVien.setObject(7, nv.getNgaySinh());
                    psNhanVien.setBoolean(8, nv.isGioiTinh());
                    psNhanVien.setInt(9, roleId); // Thêm id_role vào bảng NhanVien
                    psNhanVien.setString(10, tenChinh); // Thêm tên tài khoản
                    result = psNhanVien.executeUpdate();
                }

                con.commit(); // Xác nhận giao dịch
            } catch (SQLException e) {
                con.rollback(); // Rollback nếu có lỗi xảy ra
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

