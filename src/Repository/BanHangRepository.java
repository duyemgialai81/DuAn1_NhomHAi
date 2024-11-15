/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.SanPham.BanHangSanPham;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class BanHangRepository {
    public ArrayList<BanHangSanPham> getAll(){
        ArrayList<BanHangSanPham> ls = new ArrayList<>();
        String sql = """
                     select sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban, vc.gia_tri , ms.mau_sac_san_pham, cl.chat_lieu_san_pham, kc.kich_co, sp.so_luong_ton 
                     from SanPham sp
                     join Voucher vc on sp.ma_voucher = vc.id_voucher
                     join MauSac ms on ms.id_mau_sac = sp.ma_mau_sac
                     join ChatLieu cl on cl.id_chat_lieu = sp.ma_chat_lieu
                     join KichCo kc on kc.id_ma_kich_co = sp.ma_kich_co
                     """;
        try (Connection con = ketnoi.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               BanHangSanPham sp = new BanHangSanPham();
               sp.setMaSanPham(rs.getString("ma_san_pham"));
               sp.setTenSanPham(rs.getString("ten_san_pham"));
               sp.setGiaBan(rs.getFloat("gia_ban"));
               sp.setVoCher(rs.getFloat("gia_tri"));
               sp.setMauSac(rs.getString("mau_sac_san_pham"));
               sp.setChatLieu(rs.getString("chat_lieu_san_pham"));
               sp.setChatLieu(rs.getString("chat_lieu_san_pham"));
               sp.setKichThuoc(rs.getString("kich_co"));
               sp.setSoLuong(rs.getInt("so_luong_ton"));
               ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
}
