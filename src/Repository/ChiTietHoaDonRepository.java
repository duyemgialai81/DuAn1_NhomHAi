/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Entity.HoaDon.ChiTietHoaDonEntity;
import java.sql.*;
import KetNoiSQL.ketnoi;
/**
 *
 * @author SingPC
 */
public class ChiTietHoaDonRepository {
//    public boolean addVoGioHang(){
//        int check =0;
//       String sql ="""
//                   insert into ChiTietHoaDon (ma_hoa_don,ma_san_pham,so_luong,gia_ban)
//                   values (?,?,?,?)
//                   """;
//        try (Connection con =  ketnoi.getConnection()){
//            PreparedStatement ps = con.prepareStatement(sql);
//            ChiTietHoaDonEntity hd = new ChiTietHoaDonEntity();
//            ps.setObject(1, hd.getMaDonHang());
//            ps.setObject(2, hd.getMaSanPham());
//            ps.setObject(3, hd.getSoLuong());
//            ps.setObject(4, hd.getGiaBan());
//            check = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return check > 0;
//    }
}
