/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.BanHangSanPham;
import Entity.HoaDonEntity;
import Entity.LayMaHoaDon;
import Entity.SanPhamEntity;
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
      public ArrayList<HoaDonEntity> getAll(){
        ArrayList<HoaDonEntity> ls = new ArrayList<>();
        String sql = """
                    SELECT hd.ma_don_hang , hd.id_ma_don_hang
                                      FROM donHang hd
                                    where hd.trang_thai = N'Đang Chờ Thanh Toán'
                     """;
        try (Connection con = ketnoi.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDonEntity dh = new HoaDonEntity();
                dh.setMaHoaDon(rs.getString("ma_don_hang"));
                dh.setIdHoaDon(rs.getInt("id_ma_don_hang"));
                ls.add(dh);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
      public ArrayList<LayMaHoaDon> hoaDon(){
        ArrayList<LayMaHoaDon> ls = new ArrayList<>();
        String sql = """
                     select ma_hoa_don
                    from HoaDon
                    where trang_thai =N'Đang Chờ Thanh Toán'
                     """;
        try (Connection con = ketnoi.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LayMaHoaDon dh = new LayMaHoaDon();
                dh.setMaHoaDonnnn(rs.getString("ma_hoa_don"));
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
    try (Connection con = ketnoi.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(sql)) {
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

}
