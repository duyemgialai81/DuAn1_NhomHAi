/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.HoaDon.XuatHoaDon;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class XuatHoaDonRepository {
    public ArrayList<XuatHoaDon> getAll(){
        ArrayList<XuatHoaDon> ls = new ArrayList<>();
        String sql = """
             			SELECT TOP 1 
                                    hd.ma_hoa_don, 
                                    nv.ten_nhan_vien,
                                    kh.ten_khach_hang,
                                    dh.ngay_dat,
                                    nv.email,
                                    kh.so_dien_thoai,
                                    hd.tien_khach_dua,
                                    hd.tien_tra_khach,
                                    ctdh.tong_tien,
                                    hd.phuong_thuc,
                                    vc.gia_tri,
                                    ctdh.thue,
                                    sp.ten_san_pham,
                                    ctdh.so_luong,
                                    ctdh.gia_ban,
                                    kh.so_dien_thoai,
                                    dh.id_ma_don_hang, 
                                    hd.ma_hoa_don    
                                FROM 
                                    DonHang dh
                                JOIN HoaDon hd 
                                    ON hd.ma_don_hang = dh.id_ma_don_hang
                                JOIN NhanVien nv 
                                    ON dh.ma_nhan_vien = nv.id_ma_nhan_vien
                                JOIN KhachHang kh 
                                    ON dh.ma_khach_hang = kh.id_ma_khach_hang
                                JOIN ChiTietDonHang ctdh 
                                    ON ctdh.ma_don_hang = dh.id_ma_don_hang
                                LEFT JOIN Voucher vc 
                                    ON ctdh.ma_voucher = vc.id_voucher
                                LEFT JOIN SanPham sp 
                                    ON ctdh.ma_san_pham = sp.id_ma_san_pham
                                WHERE 
                                    hd.trang_thai = N'Thanh Toán Thành Công' 
                                    AND dh.trang_thai = N'Thanh Toán Thành Công'
                                ORDER BY dh.id_ma_don_hang DESC;
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              XuatHoaDon hd = new XuatHoaDon();
                            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
              hd.setTenNhanVien(rs.getString("ten_nhan_vien"));
              hd.setTenKhachHang(rs.getString("ten_khach_hang"));
              hd.setNgayDat(rs.getDate("ngay_dat"));
              hd.setEmail(rs.getString("email"));
              hd.setSoDienThoai(rs.getString("so_dien_thoai"));
              hd.setTienKhachDua(rs.getFloat("tien_khach_dua"));
              hd.setTienTraKhach(rs.getFloat("tien_tra_khacH"));
              hd.setPhuongThuc(rs.getString("phuong_thuc"));
              hd.setThanhTien(rs.getFloat("tong_tien"));
              hd.setGiaTri(rs.getFloat("gia_tri"));
              hd.setThue(rs.getFloat("thue"));
              hd.setTenSanPham(rs.getString("ten_san_pham"));
              hd.setSoLuong(rs.getInt("so_luong"));
              hd.setGiaBan(rs.getFloat("gia_ban"));
              hd.setSoDienThoai(rs.getString("so_dien_thoai"));
              hd.setIdHoaDon(rs.getInt("id_ma_don_hang"));
              ls.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
}
