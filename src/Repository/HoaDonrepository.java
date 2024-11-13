/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.ChiTietHoaDonEntity;
import Entity.DonHangEntity;
import Entity.HoaDonEntity;
import Entity.HoaDonXemDuLieu;
import Entity.InsertHoaDonEntity;
import Entity.NhanVienEntity;
import Entity.SanPhamEntity;
import Entity.XemHoaDonTao;
import GiaoDien.LuuThongTinDangNhap;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class HoaDonrepository {
    public ArrayList<HoaDonEntity> getAll(){
        ArrayList<HoaDonEntity> ls = new ArrayList<>();
        String sql = """
                  SELECT hd.id_ma_hoa_don, hd.ma_hoa_don, hd.ngay_lap, nv.ten_nhan_vien, kh.ten_khach_hang
                    FROM HoaDon hd
                    JOIN NhanVien nv ON nv.id_ma_nhan_vien = hd.ma_nhan_vien
                    JOIN KhachHang kh ON kh.id_ma_khach_hang = hd.ma_khach_hang
                    WHERE hd.trangThai = N'đang chờ thanh toán';
                     """;
        try (Connection con = ketnoi.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDonEntity dh = new HoaDonEntity();
                dh.setIdHoaDon(rs.getInt("id_ma_hoa_don"));
                dh.setMaHoaDon(rs.getString("ma_hoa_don"));
                dh.setNgayLap(rs.getDate("ngay_lap"));
                dh.setTenNhanVien(rs.getString("ten_nhan_vien"));
                dh.setTenKhachHang(rs.getString("ten_khach_hang"));
                ls.add(dh);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
//    public boolean TaoHoaDon(){
//        int check =0;
//        String  sql = """
//                   insert into HoaDon (ngay_lap,ma_nhan_vien,ma_khach_hang)
//                   values(getDate(),?,?)
//                   """;
//        try {
//            Connection con= ketnoi.getConnection();
//            InsertHoaDonEntity hd = new InsertHoaDonEntity();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setObject(1,luuThongTinDangNhap.getInNhanVien());
//            ps.setObject(2, 1);
//            check = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return check >0;
//    }
    public ArrayList<XemHoaDonTao> getAllGioHang(int maHoaDon){
        ArrayList<XemHoaDonTao> ls = new ArrayList<>();
        String sql ="""
                    select  sp.ma_san_pham, sp.ten_san_pham, cthd.so_luong,cthd.gia_ban
                    from ChiTietHoaDon cthd
                    join HoaDon hd on hd.id_ma_hoa_don = cthd.ma_hoa_don
                    join SanPham sp on sp.id_ma_san_pham = cthd.ma_san_pham
                    where hd.id_ma_hoa_don = ?
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                XemHoaDonTao ct = new XemHoaDonTao();
                ct.setMaSanPham(rs.getString("ma_san_pham"));
                ct.setTenSanPham(rs.getString("ten_san_pham"));
                ct.setSoLuong(rs.getInt("so_luong"));
                ct.setGiaBan(rs.getFloat("gia_ban"));
                ls.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean themChiTietHoaDon(int idMaHoaDon, int idMaSanPham, int soLuong, double giaBan) {
        String sql = "INSERT INTO ChiTietHoaDon (ma_hoa_don, ma_san_pham, so_luong, gia_ban) VALUES (?, ?, ?, ?)";
        try (Connection con = ketnoi.getConnection()){ 
        PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idMaHoaDon);
            preparedStatement.setInt(2, idMaSanPham);
            preparedStatement.setInt(3, soLuong);
            preparedStatement.setDouble(4, giaBan);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
public boolean capNhatSoLuongSanPham(int idMaSanPham, int soLuongMoi) {
    String sql = "UPDATE SanPham SET so_luong_ton = ? WHERE id_ma_san_pham = ?";  
    try (Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, soLuongMoi);
        ps.setInt(2, idMaSanPham);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

 public ArrayList<SanPhamEntity> timkiemSanPhamm( String trangThai, String tenSanPham) {
    ArrayList<SanPhamEntity> ls = new ArrayList<>();
    String sql = """
        select sp.id_ma_san_pham, sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, sp.gia_nhap, sp.so_luong_ton, sp.hinh_anh, 
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
         AND (sp.trang_thai LIKE ? OR ? = '')
    """;
    try (Connection con = ketnoi.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, "%" + tenSanPham + "%");
            ps.setString(2, tenSanPham);
            ps.setString(3, "%" + trangThai + "%");
            ps.setString(4, trangThai);
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
 public ArrayList<HoaDonXemDuLieu> getAllChiTietHoaDon(){
     ArrayList<HoaDonXemDuLieu> ls = new ArrayList<>();
    String sql ="""
                select hd.ma_hoa_don, hd.tong_tien, hd.hinh_thuc_chuyen_tien, hd.ngay_lap, hd.trangThai, nv.ma_nhan_vien, nv.ten_nhan_vien, kh.ma_khach_hang, kh.ten_khach_hang
                from HoaDon hd 
                join NhanVien nv on hd.ma_nhan_vien = nv.id_ma_nhan_vien
                join KhachHang kh on kh.id_ma_khach_hang = nv.id_ma_nhan_vien
                """;
     try {
         Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             HoaDonXemDuLieu hd = new HoaDonXemDuLieu();
             hd.setMaHoaDon(rs.getString("ma_hoa_don"));
             hd.setTongTien(rs.getFloat("tong_tien"));
             hd.setHinhThuc(rs.getString("hinh_thuc_chuyen_tien"));
             hd.setNgayLap(rs.getDate("ngay_lap"));
             hd.setTrangThai(rs.getString("trangThai"));
             hd.setMaNhanVien(rs.getString("ma_nhan_vien"));
             hd.setTenNhanVien(rs.getString("ten_nhan_vien"));
             hd.setMaKhachHang(rs.getString("ma_khach_hang"));
             hd.setTenKhachHang(rs.getString("ten_khach_hang"));
             ls.add(hd);
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     return  ls;
 }
}
