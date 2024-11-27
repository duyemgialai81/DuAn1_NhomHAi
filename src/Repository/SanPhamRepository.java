/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.SanPham.AddSanPhamEntity;
import Entity.SanPham.SanPhamEntity;
import Entity.SanPham.ThongTinSanPham;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import KetNoiSQL.ketnoi;
import java.sql.SQLException;

/**
 *
 * @author SingPC
 */
public class SanPhamRepository {

    public ArrayList<ThongTinSanPham> getAll() {
        ArrayList<ThongTinSanPham> ls = new ArrayList<>();
        String sql = """
                   select id_ma_san_pham, ma_san_pham, ten_san_pham, gia_ban, gia_nhap, so_luong_ton, hinh_anh,trang_thai 
                   from SanPham
                    """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongTinSanPham sp = new ThongTinSanPham();
                sp.setMaSanPham(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setGiaBan(rs.getFloat("gia_ban"));
                sp.setGiaNhap(rs.getFloat("gia_nhap"));
                sp.setSoLuong(rs.getInt("so_luong_ton"));
                sp.setHinhAnh(rs.getString("hinh_anh"));
                sp.setTrangThai(rs.getString("trang_thai"));

                ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public ArrayList<SanPhamEntity> getSanPhamChhiTiet() {
        ArrayList<SanPhamEntity> lsss = new ArrayList<>();
        String sql = """
           	select sp.id_ma_san_pham, sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, sp.gia_nhap, sp.so_luong_ton, sp.hinh_anh, lsp.ten_loai_san_pham, ms.mau_sac_san_pham,kc.kich_co,th.ten_thuong_hieu, cl.chat_lieu_san_pham, xx.quoc_gia, sp.trang_thai
                from SanPham sp
                join LoaiSanPham lsp on lsp.id_ma_loai = sp.ma_loai_san_pham
                join MauSac ms on ms.id_mau_sac = sp.ma_mau_sac
                join KichCo kc on kc.id_ma_kich_co = sp.ma_kich_co
                join ThuongHieu th on th.id_ma_thuong_hieu = sp.ma_thuong_hieu
                join ChatLieu cl on cl.id_chat_lieu = sp.ma_chat_lieu
                join XuatXu xx on xx.id_ma_xuat_xu = sp.ma_xuat_xu
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamEntity sp = new SanPhamEntity();
                sp.setIdSanPham(rs.getInt("id_ma_san_pham"));
                sp.setMaSanPham(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setGiaBan(rs.getFloat("gia_ban"));
                sp.setGiaNhap(rs.getFloat("gia_nhap"));
                sp.setSoLuong(rs.getInt("so_luong_ton"));
                sp.setHinhAnh(rs.getString("hinh_anh"));
                sp.setTenMaLoai(rs.getString("ten_loai_san_pham"));
                sp.setKichCo(rs.getString("kich_co"));
                sp.setMauSac(rs.getString("mau_sac_san_pham"));
                sp.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
                sp.setChatLieu(rs.getString("chat_lieu_san_pham"));
                sp.setQuocGia(rs.getString("quoc_gia"));
                sp.setTrangThai(rs.getString("trang_thai"));
                lsss.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsss;
    }

    public boolean addSanPhammm(AddSanPhamEntity sp) {
        int check = 0;
        String sql = """
                   insert into SanPham (ten_san_pham,gia_ban,gia_nhap, so_luong_ton, hinh_anh, ma_loai_san_pham, ma_kich_co, ma_chat_lieu, ma_thuong_hieu,ma_mau_sac, ma_xuat_xu)
                  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                   """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement pstmtSanPham = con.prepareStatement(sql);
            pstmtSanPham.setObject(1, sp.getTenSanPham());
            pstmtSanPham.setObject(2, sp.getGiaBan());
            pstmtSanPham.setObject(3, sp.getGiaNhap());
            pstmtSanPham.setObject(4, sp.getSoLuong());
            pstmtSanPham.setObject(5, sp.getHinhAnh());
            pstmtSanPham.setObject(6, sp.getMaLoai());
            pstmtSanPham.setObject(7, sp.getMaKichCo());
            pstmtSanPham.setObject(8, sp.getMaChatLieu());
            pstmtSanPham.setObject(9, sp.getMaThuongHieu());
            pstmtSanPham.setObject(10, sp.getMaMauSac());
            pstmtSanPham.setObject(11, sp.getMaXuatXu());
            check = pstmtSanPham.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean update(String ma, AddSanPhamEntity sp) {
        int check = 0;
        String sql = """
            UPDATE SanPham
                 set ten_san_pham =?, gia_ban =?,gia_nhap =?, so_luong_ton =?, hinh_anh =?, ma_loai_san_pham =?, ma_kich_co =?, ma_mau_sac =?, ma_thuong_hieu =?, ma_chat_lieu =?, ma_xuat_xu =?
                 WHERE ma_san_pham = ?
            """;

        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement pstmtSanPham = con.prepareStatement(sql);
            pstmtSanPham.setString(1, sp.getTenSanPham());
            pstmtSanPham.setFloat(2, sp.getGiaBan());
            pstmtSanPham.setFloat(3, sp.getGiaNhap());
            pstmtSanPham.setInt(4, sp.getSoLuong());
            pstmtSanPham.setString(5, sp.getHinhAnh());
            pstmtSanPham.setInt(6, sp.getMaLoai());
            pstmtSanPham.setInt(7, sp.getMaKichCo());
            pstmtSanPham.setInt(8, sp.getMaChatLieu());
            pstmtSanPham.setInt(9, sp.getMaThuongHieu());
            pstmtSanPham.setInt(10, sp.getMaMauSac());
            pstmtSanPham.setInt(11, sp.getMaXuatXu());
            pstmtSanPham.setObject(12, ma);
            check = pstmtSanPham.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean delete(String maSanPham) {
        int check = 0;
        int check1 =0;
        String sql1 ="""
                    delete chitietdonhang
                    where ma_san_pham = ?
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql1);
            ps.setObject(1, maSanPham);
            check1 = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = """
                     delete SanPham
                     where ma_san_pham = ?
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, maSanPham);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean dangNhap(String tenDangNhap, String matKhau) {
        String sql = "SELECT t.*, r.tenRole FROM TaiKhoan t "
                + "JOIN Role r ON t.maRole = r.idMaRole "
                + "WHERE t.tenTaiKhoan = ? AND t.matKhau = ?";

        try (Connection conn = ketnoi.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String vaiTro = rs.getString("tenRole"); // Lấy vai trò
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

    // Phương thức lấy vai trò
    public String layTenRoleTuDatabase(int userId) {
        String tenRole = null;
        String query = "SELECT r.tenRole FROM Users u JOIN Roles r ON u.roleId = r.id WHERE u.id = ?";

        // Kết nối tới cơ sở dữ liệu
        try (Connection conn = ketnoi.getConnection(); // Giả sử bạn có lớp DatabaseConnection để lấy kết nối
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId); 
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tenRole = rs.getString("tenRole"); // Lấy tên vai trò
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenRole;
    }
   public ArrayList<SanPhamEntity> timkiemSanPham(String thuonghieu, String xuatXu, String trangThai, String tenSanPham) {
    ArrayList<SanPhamEntity> ls = new ArrayList<>();
    String sql = """
        select sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, sp.gia_nhap, sp.so_luong_ton, sp.hinh_anh, 
               lsp.ten_loai_san_pham, ms.mau_sac_san_pham, kc.kich_co, th.ten_thuong_hieu, 
               cl.chat_lieu_san_pham, xx.quoc_gia, sp.trang_thai
        from SanPham sp
        join LoaiSanPham lsp on lsp.id_ma_loai = sp.ma_loai_san_pham
        join MauSac ms on ms.id_mau_sac = sp.ma_mau_sac
        join KichCo kc on kc.id_ma_kich_co = sp.ma_kich_co
        join ThuongHieu th on th.id_ma_thuong_hieu = sp.ma_thuong_hieu
        join ChatLieu cl on cl.id_chat_lieu = sp.ma_chat_lieu
        join XuatXu xx on xx.id_ma_xuat_xu = sp.ma_xuat_xu
         WHERE (sp.ten_san_pham LIKE ? OR ? = '') 
         AND (xx.quoc_gia LIKE ? OR ? = '') 
         AND (sp.trang_thai LIKE ? OR ? = '') 
         AND (th.ten_thuong_hieu LIKE ? OR ? = '')
    """;
    try (Connection con = ketnoi.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        
        // Cài đặt các tham số cho các điều kiện tìm kiếm
        ps.setString(1, "%" + tenSanPham + "%");  // Tìm theo tên sản phẩm
        ps.setString(2, tenSanPham);  // Khi tên sản phẩm rỗng, sẽ bỏ qua tìm kiếm
        ps.setString(3, "%" + xuatXu + "%");  // Tìm theo xuất xứ
        ps.setString(4, xuatXu);  // Khi xuất xứ rỗng, sẽ bỏ qua tìm kiếm
        ps.setString(5, "%" + trangThai + "%");  // Tìm theo trạng thái
        ps.setString(6, trangThai);  // Khi trạng thái rỗng, sẽ bỏ qua tìm kiếm
        ps.setString(7, "%" + thuonghieu + "%");  // Tìm theo thương hiệu
        ps.setString(8, thuonghieu);  // Khi thương hiệu rỗng, sẽ bỏ qua tìm kiếm

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPhamEntity sp = new SanPhamEntity();
            sp.setMaSanPham(rs.getString("ma_san_pham"));
            sp.setTenSanPham(rs.getString("ten_san_pham"));
            sp.setGiaBan(rs.getFloat("gia_ban"));
            sp.setGiaNhap(rs.getFloat("gia_nhap"));
            sp.setSoLuong(rs.getInt("so_luong_ton"));
            sp.setHinhAnh(rs.getString("hinh_anh"));
            sp.setTenMaLoai(rs.getString("ten_loai_san_pham"));
            sp.setKichCo(rs.getString("kich_co"));
            sp.setMauSac(rs.getString("mau_sac_san_pham"));
            sp.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
            sp.setChatLieu(rs.getString("chat_lieu_san_pham"));
            sp.setQuocGia(rs.getString("quoc_gia"));
            sp.setTrangThai(rs.getString("trang_thai"));
            ls.add(sp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ls;
}



//    public ArrayList<ThongTinSanPham> timkiemThongtinSanPham(Float giaMin, Float giaMax, String trangThai, String tenSanPham) {
//        ArrayList<ThongTinSanPham> ls = new ArrayList<>();
//        String sql = """
//                 SELECT * FROM sanPham
//                 WHERE (ten_san_pham LIKE ? )
//                 AND (trang_Thai LIKE ? )
//                 AND (gia_ban BETWEEN ? AND ?)
//                 """;
//        try {
//            Connection con = ketnoi.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//
//            // Thiết lập giá trị cho các tham số
//            ps.setString(1, "%" + tenSanPham + "%");
//            ps.setString(2, tenSanPham.isEmpty() ? null : tenSanPham); // kiểm tra nếu tên sản phẩm rỗng
//            ps.setString(3, "%" + trangThai + "%");
//            ps.setString(4, trangThai.isEmpty() ? null : trangThai); // kiểm tra nếu trạng thái rỗng
//            ps.setFloat(5, giaMin);
//            ps.setFloat(6, giaMax);
//            ps.setFloat(7, giaMin == null ? 0 : giaMin); // kiểm tra nếu giá tối thiểu không được chỉ định
//            ps.setFloat(8, giaMax == null ? Float.MAX_VALUE : giaMax); // kiểm tra nếu giá tối đa không được chỉ định
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ThongTinSanPham sc = new ThongTinSanPham();
//                sc.setMaSanPham(rs.getString("ma_san_pham"));
//                sc.setTenSanPham(rs.getString("ten_san_pham"));
//                sc.setGiaBan(rs.getFloat("gia_ban"));
//                sc.setSoLuong(rs.getInt("so_luong_ton"));
//                sc.setHinhAnh(rs.getString("hinh_anh"));
//                sc.setTrangThai(rs.getString("trang_thai"));
//                ls.add(sc);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ls;
//    }
 public ArrayList<ThongTinSanPham> timkiemThongtinSanPham(Float giaMin, Float giaMax, String trangThai, String tenSanPham) {
    ArrayList<ThongTinSanPham> ls = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM sanPham WHERE 1=1");

    // Thêm điều kiện cho các tham số nếu có
    if (tenSanPham != null && !tenSanPham.isEmpty()) {
        sql.append(" AND ten_san_pham LIKE ?");
    }
    if (trangThai != null && !trangThai.isEmpty()) {
        sql.append(" AND trang_thai LIKE ?");
    }

    // Điều kiện giá bán
    if (giaMin != null && giaMax != null) {
        sql.append(" AND gia_ban BETWEEN ? AND ?");
    } else if (giaMin != null) {
        sql.append(" AND gia_ban >= ?");
    } else if (giaMax != null) {
        sql.append(" AND gia_ban <= ?");
    }
    try (Connection con = ketnoi.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql.toString());
        int index = 1;
        if (tenSanPham != null && !tenSanPham.isEmpty()) {
            ps.setString(index++, "%" + tenSanPham + "%");
        }
        if (trangThai != null && !trangThai.isEmpty()) {
            ps.setString(index++, "%" + trangThai + "%");
        }
        if (giaMin != null || giaMax != null) {
            ps.setFloat(index++, giaMin);
            ps.setFloat(index++, giaMax);
        } else if (giaMin != null) {
            ps.setFloat(index++, giaMin);
        } else if (giaMax != null) {
            ps.setFloat(index++, giaMax);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongTinSanPham sp = new ThongTinSanPham();
            sp.setMaSanPham(rs.getString("ma_san_pham"));
            sp.setTenSanPham(rs.getString("ten_san_pham"));
            sp.setGiaBan(rs.getFloat("gia_ban"));
            sp.setSoLuong(rs.getInt("so_luong_ton"));
            sp.setHinhAnh(rs.getString("hinh_anh"));
            sp.setTrangThai(rs.getString("trang_thai"));
            ls.add(sp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ls;
}



}
