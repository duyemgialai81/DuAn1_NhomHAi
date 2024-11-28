/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.DonHangChiTiet.DonHangChiTietEntity;
import Entity.HoaDon.HoaDonXemDuLieu;
import GiaoDien.LuuThongTinDangNhap;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class DonHangChiTietRepository {
    public ArrayList<DonHangChiTietEntity> getAll(){
        ArrayList<DonHangChiTietEntity> ls = new ArrayList<>();
        String sql = """
                      
                                                                 	SELECT 
                                                                                                                             SanPham.ma_san_pham, 
                                                                                                                             SanPham.ten_san_pham, 
                                                                                                                             ChiTietDonHang.so_luong AS tong_so_luong,
                                                                                                                         	ChiTietDonHang.gia_ban,
                                                                                                                         	ChiTietDonHang.tong_tien,
                                                                                                                         	DonHang.trang_thai,
                                                                                                        					DonHang.ngay_dat, 
                                                                                                        					DonHang.ma_don_hang,
                                                                                                        		HoaDon.ma_hoa_don
                                                                                                                         FROM ChiTietDonHang
                                                                                                                             JOIN SanPham ON SanPham.id_ma_san_pham = ChiTietDonHang.ma_san_pham
                                                                                                                         	join DonHang on ChiTietDonHang.ma_don_hang = DonHang.id_ma_don_hang
                                                                                                        				left	join HoaDon on DonHang.id_ma_don_hang = HoaDon.ma_don_hang
                                                                                                        					order by DonHang.ma_don_hang asc
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet sc = ps.executeQuery();
            while(sc.next()){
                DonHangChiTietEntity ct = new DonHangChiTietEntity();
                ct.setMaSanPham(sc.getString("ma_san_pham"));
                ct.setTenSanPham(sc.getString("ten_san_pham"));
                ct.setSoLuong(sc.getInt("tong_so_luong"));
                ct.setGiaBan(sc.getDouble("gia_ban"));
                ct.setTongTien(sc.getDouble("tong_tien"));
                ct.setTrangThai(sc.getString("trang_thai"));
                ls.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
  public ArrayList<DonHangChiTietEntity> LocvaTiemKiemHoaDon(String tenSanPham, String maSanPham, String trangThai) {
    ArrayList<DonHangChiTietEntity> ls = new ArrayList<>();
    String sql = """
        SELECT 
            SanPham.ma_san_pham, 
            SanPham.ten_san_pham, 
            SUM(ChiTietDonHang.so_luong) AS tong_so_luong,
            ChiTietDonHang.gia_ban,
            ChiTietDonHang.tong_tien,
            DonHang.trang_thai
        FROM 
            ChiTietDonHang
        JOIN 
            SanPham ON SanPham.id_ma_san_pham = ChiTietDonHang.ma_san_pham
        JOIN 
            DonHang ON ChiTietDonHang.ma_don_hang = DonHang.id_ma_don_hang
        WHERE 
            (SanPham.ten_san_pham LIKE ? OR ? = '')
            AND (SanPham.ma_san_pham LIKE ? OR ? = '')
            AND (DonHang.trang_thai LIKE ? OR ? = '')
        GROUP BY 
            SanPham.ma_san_pham, 
            SanPham.ten_san_pham,
            ChiTietDonHang.gia_ban,
            ChiTietDonHang.tong_tien,
            DonHang.trang_thai
    """;
    try {
        Connection con = ketnoi.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,  tenSanPham );
        ps.setString(2, "%" + tenSanPham + "%"); 
        ps.setString(3,  maSanPham ); 
        ps.setString(4,"%" + maSanPham + "%"); 
        ps.setString(5,  trangThai);  
        ps.setString(6, "%" + trangThai + "%"); 

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DonHangChiTietEntity ct = new DonHangChiTietEntity();
            ct.setMaSanPham(rs.getString("ma_san_pham"));
            ct.setTenSanPham(rs.getString("ten_san_pham"));
            ct.setSoLuong(rs.getInt("tong_so_luong"));
            ct.setGiaBan(rs.getDouble("gia_ban"));
            ct.setTongTien(rs.getDouble("tong_tien"));
            ct.setTrangThai(rs.getString("trang_thai"));
            ls.add(ct);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ls;
}
  public ArrayList<HoaDonXemDuLieu> layHhoaDon(){
      ArrayList<HoaDonXemDuLieu> ls = new ArrayList<>();
      String tenNhaVien = LuuThongTinDangNhap.getTenNhanVien();
      String sql ="""
                  select dh.id_ma_don_hang, dh.ma_don_hang,kh.ten_khach_hang,nv.ten_nhan_vien, dh.ngay_dat, dh.trang_thai
                  from DonHang dh
                  join khachhang kh on dh.ma_khach_hang = kh.id_ma_khach_hang
                  join nhanvien nv on dh.ma_nhan_vien = nv.id_ma_nhan_vien
                  where dh.trang_thai = N'đang chờ thanh toán'
                  """;
      try {
          Connection con = ketnoi.getConnection();
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet sc = ps.executeQuery();
          while(sc.next()){
              HoaDonXemDuLieu dl = new HoaDonXemDuLieu();
              dl.setIdDonHang(sc.getInt("id_ma_don_hang"));
              dl.setMaHoaDon(sc.getString("ma_don_hang"));
              dl.setTenKhachHang(sc.getString("ten_khach_hang"));
              dl.setTenNhanVien(sc.getString("ten_nhan_vien"));
              dl.setNgayLap(sc.getDate("ngay_dat"));
              dl.setTrangThai(sc.getString("trang_thai"));
             ls.add(dl);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return ls;
  }
}
