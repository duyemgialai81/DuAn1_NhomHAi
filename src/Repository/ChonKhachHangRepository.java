/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.KhachHang.KhachHang;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class ChonKhachHangRepository {
    public ArrayList<KhachHang> layThongTinKhachHang(){
        ArrayList<KhachHang> ls = new ArrayList<>();
        String sql = """
                     select * from khachHang
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setIdKhachHang(rs.getInt("id_ma_khach_hang"));
                kh.setMaKH(rs.getString("ma_khach_hang"));
                kh.setTenKH(rs.getString("ten_khach_hang"));
                kh.setEmail(rs.getString("email"));
                kh.setSDT(rs.getString("so_dien_thoai"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setTrangThai(rs.getBoolean("trang_thai"));
                ls.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean  addThongTinKhachHang(KhachHang kh){
        int check =0;
        String sql = """
                     insert into Khachhang (ten_khach_hang,email,so_dien_thoai,dia_chi)
                     values(?,?,?,?)
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTenKH());
            ps.setObject(2, kh.getEmail());
            ps.setObject(3, kh.getSDT());
            ps.setObject(4, kh.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    public boolean updateThongTinKhachHang(KhachHang kh, String maKH){
        int check =0;
        String sql ="""
                    update khachhang
                    set ten_khach_hang =?, email =?,so_dien_thoai =?, dia_chi=?, gioi_tinh =? ,trang_thai = ?
                    where ma_khach_hang = ?
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTenKH());
            ps.setObject(2, kh.getEmail());
            ps.setObject(3, kh.getSDT());
            ps.setObject(4, kh.getDiaChi());
            ps.setObject(5, kh.isGioiTinh());
            ps.setObject(6, kh.isTrangThai());
            ps.setObject(7, maKH);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    public boolean deleteKhachHanng(String maKhachHang){
        int check = 0;
        String sql ="""
                    delete khachHang
                    where ma_khach_hang =?
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, maKhachHang);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
}
