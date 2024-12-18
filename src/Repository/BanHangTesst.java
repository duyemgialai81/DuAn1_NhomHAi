/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.SanPham.BanHangSanPham;
import Entity.HoaDon.HoaDonEntity;
import Entity.HoaDon.LayMaHoaDon;
import Entity.SanPham.SanPhamEntity;
import KetNoiSQL.ketnoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SingPC
 */
public class BanHangTesst {

    public ArrayList<SanPhamEntity> getSanPhamChhiTiet() {
        int SoLuongTon =0;
        ArrayList<SanPhamEntity> lsss = new ArrayList<>();
        String sql = """
           	select sp.id_ma_san_pham, sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, sp.gia_nhap, sp.so_luong_ton, sp.hinh_anh, lsp.ten_loai_san_pham, ms.mau_sac_san_pham,kc.kich_co,th.ten_thuong_hieu, cl.chat_lieu_san_pham, xx.quoc_gia, sp.trang_thai
                                from SanPham sp
                                join LoaiSanPham lsp on lsp.ten_loai_san_pham = sp.ma_loai_san_pham
                                join MauSac ms on ms.mau_sac_san_pham = sp.ma_mau_sac
                                join KichCo kc on kc.kich_co = sp.ma_kich_co
                                join ThuongHieu th on th.ten_thuong_hieu = sp.ma_thuong_hieu
                                join ChatLieu cl on cl.chat_lieu_san_pham = sp.ma_chat_lieu
                                join XuatXu xx on xx.quoc_gia = sp.ma_xuat_xu
                                where sp.so_luong_ton > 0
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
//           ps.setObject(1, SoLuongTon);
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
    public  ArrayList<HoaDonEntity> layThongTinKhachHanng(){
        ArrayList<HoaDonEntity> ls = new ArrayList<>();
        String sql ="""
                    """;
        return ls;
    }
    public ArrayList<HoaDonEntity> getAll() {
        ArrayList<HoaDonEntity> ls = new ArrayList<>();
        String sql = """
                    SELECT hd.id_ma_don_hang, hd.ma_don_hang, hd.ngay_dat, nv.ten_nhan_vien, kh.ten_khach_hang, kh.id_ma_khach_hang
                                                            FROM DonHang hd
                                                            JOIN NhanVien nv ON nv.id_ma_nhan_vien = hd.ma_nhan_vien
                                                            JOIN KhachHang kh ON kh.id_ma_khach_hang = hd.ma_khach_hang
                                                            WHERE hd.trang_thai = N'Đang chờ'
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonEntity dh = new HoaDonEntity();
                dh.setIdHoaDon(rs.getInt("id_ma_don_hang"));
                dh.setMaHoaDon(rs.getString("ma_don_hang"));
                dh.setNgayLap(rs.getDate("ngay_dat"));
                dh.setTenNhanVien(rs.getString("ten_nhan_vien"));
                dh.setTenKhachHang(rs.getString("ten_khach_hang"));
                dh.setIdMaKhachHang(rs.getInt("id_ma_khach_hang"));
                ls.add(dh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public ArrayList<HoaDonEntity> getAllLL() {
        ArrayList<HoaDonEntity> ls = new ArrayList<>();
        String sql = """
                    SELECT hd.id_ma_don_hang, hd.ma_don_hang, hd.ngay_dat, nv.ten_nhan_vien, kh.ten_khach_hang
                                        FROM DonHang hd
                                        JOIN NhanVien nv ON nv.id_ma_nhan_vien = hd.ma_nhan_vien
                                        JOIN KhachHang kh ON kh.id_ma_khach_hang = hd.ma_khach_hang
                                        WHERE hd.trang_thai = N''Đang chờ';
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonEntity dh = new HoaDonEntity();
                dh.setIdHoaDon(rs.getInt("id_ma_don_hang"));
                dh.setMaHoaDon(rs.getString("ma_don_hang"));
                dh.setNgayLap(rs.getDate("ngay_dat"));
                dh.setTenNhanVien(rs.getString("ten_nhan_vien"));
                dh.setTenKhachHang(rs.getString("ten_khach_hang"));
                ls.add(dh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public ArrayList<SanPhamEntity> timKiemVaLocSanPham(String trangThai, String tenSanPham) {
        ArrayList<SanPhamEntity> lsss = new ArrayList<>();
        String sql = """
        SELECT sp.id_ma_san_pham, sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, sp.gia_nhap, sp.so_luong_ton, sp.hinh_anh, 
               lsp.ten_loai_san_pham, ms.mau_sac_san_pham, kc.kich_co, th.ten_thuong_hieu, cl.chat_lieu_san_pham, 
               xx.quoc_gia, sp.trang_thai
          FROM SanPham sp
          JOIN LoaiSanPham lsp ON lsp.id_ma_loai = sp.ma_loai_san_pham
          JOIN MauSac ms ON ms.id_mau_sac = sp.ma_mau_sac
          JOIN KichCo kc ON kc.id_ma_kich_co = sp.ma_kich_co
          JOIN ThuongHieu th ON th.id_ma_thuong_hieu = sp.ma_thuong_hieu
          JOIN ChatLieu cl ON cl.id_chat_lieu = sp.ma_chat_lieu
          JOIN XuatXu xx ON xx.id_ma_xuat_xu = sp.ma_xuat_xu
         WHERE (sp.ten_san_pham LIKE ? OR ? = '')
           AND (sp.trang_thai LIKE ? OR ? = '')
    """;

        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            // Set the parameters in the correct order
            ps.setObject(1, "%" + tenSanPham + "%");  // First ? (for ten_san_pham LIKE)
            ps.setObject(2, tenSanPham);               // Second ? (for OR ? = '')
            ps.setObject(3, "%" + trangThai + "%");    // Third ? (for trang_thai LIKE)
            ps.setObject(4, trangThai);                // Fourth ? (for OR ? = '')

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

    public ArrayList<LayMaHoaDon> hoaDon() {
        ArrayList<LayMaHoaDon> ls = new ArrayList<>();
        String sql = """
                     select id_ma_hoa_don, ma_hoa_don
                    from HoaDon
                    where trang_thai =N'Đang Chờ Thanh Toán' or trang_thai =N'Đang Chờ Xác Nhận'
                     """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LayMaHoaDon dh = new LayMaHoaDon();
                dh.setIdMaHoaDon(rs.getInt("id_ma_hoa_don"));
                dh.setMaHoaDonnn(rs.getString("ma_hoa_don"));
                ls.add(dh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public ArrayList<SanPhamEntity> findSanPhamByMa(String maSanPhamm) {
        ArrayList<SanPhamEntity> ls = new ArrayList<>();
        String sql = "SELECT ma_san_pham,ten_san_pham,so_luong_ton,gia_ban  FROM SanPham WHERE ma_san_pham =?";
        try (Connection con = ketnoi.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setObject(1, maSanPhamm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SanPhamEntity sanPham = new SanPhamEntity();
                    sanPham.setMaSanPham(rs.getString("ma_san_pham"));
                    sanPham.setTenSanPham(rs.getString("ten_san_pham"));
                    sanPham.setSoLuong(rs.getInt("so_luong_ton"));
                    sanPham.setGiaBan(rs.getFloat("gia_ban"));
                    ls.add(sanPham);
                } else {
                    System.out.println("Không tìm thấy sản phẩm với mã: " + maSanPhamm);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ls; // Trả về null nếu không tìm thấy sản phẩm hoặc có lỗi
    }

    public ArrayList<SanPhamEntity> timkiemSanPhamm(String thuonghieu, String mauSac, String kichCo, String tenSanPham) {
        ArrayList<SanPhamEntity> ls = new ArrayList<>();
        String sql = """
        select sp.id_ma_san_pham ,sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, sp.gia_nhap, sp.so_luong_ton, sp.hinh_anh, 
               lsp.ten_loai_san_pham, ms.mau_sac_san_pham, kc.kich_co, th.ten_thuong_hieu, 
               cl.chat_lieu_san_pham, xx.quoc_gia, sp.trang_thai
        from SanPham sp
         join LoaiSanPham lsp on lsp.ten_loai_san_pham = sp.ma_loai_san_pham
                        join MauSac ms on ms.mau_sac_san_pham = sp.ma_mau_sac
                        join KichCo kc on kc.kich_co = sp.ma_kich_co
                        join ThuongHieu th on th.ten_thuong_hieu = sp.ma_thuong_hieu
                        join ChatLieu cl on cl.chat_lieu_san_pham = sp.ma_chat_lieu
                        join XuatXu xx on xx.quoc_gia = sp.ma_xuat_xu
         WHERE (sp.ten_san_pham LIKE ? OR ? = '') 
         AND (ms.mau_sac_san_pham LIKE ? OR ? = '') 
         AND (kc.kich_co LIKE ? OR ? = '')
         AND (th.ten_thuong_hieu LIKE ? OR ? = '') 
    """;
        try (Connection con = ketnoi.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, "%" + maSanPham + "%");  
//            ps.setString(2, maSanPham); 
            ps.setString(1, "%" + tenSanPham + "%");  
            ps.setString(2, tenSanPham);  
            ps.setString(3, "%" + mauSac + "%");  
            ps.setString(4, mauSac); 
            ps.setString(5, "%" + kichCo + "%");  
            ps.setString(6, kichCo);  
            ps.setString(7, "%" + thuonghieu + "%");  
            ps.setString(8, thuonghieu);
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
                ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }

}
