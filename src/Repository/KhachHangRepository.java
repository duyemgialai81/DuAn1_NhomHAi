/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Entity.KhachHang.KhachHang;
import KetNoiSQL.ketnoi;
import java.sql.*;
import java.util.ArrayList;
public class KhachHangRepository {
    private Connection con= null;
    private PreparedStatement ps= null;
    private ResultSet rs= null;
    private String sql=null;
    
    public  ArrayList<KhachHang> getAll(){
        sql="select id_ma_khach_hang, ma_khach_hang,ten_khach_hang,so_dien_thoai,email,dia_chi, gioi_tinh, trang_thai from KhachHang";
        ArrayList<KhachHang> list = new ArrayList();
        try{
            con=ketnoi.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                int idMaKhachHang = rs.getInt(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String email = rs.getString(5);
                String diachi = rs.getString(6);
                boolean gioiTinh = rs.getBoolean(7);
                boolean trangThai = rs.getBoolean(8);
                KhachHang kh = new KhachHang(idMaKhachHang, maKH, tenKH, sdt, email, diachi, gioiTinh, trangThai);
                list.add(kh);
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public int them(KhachHang kh) {
        sql = "insert into KhachHang(ten_khach_hang,so_dien_thoai,email,dia_chi) values (?,?,?,?)";
        try {
            con = ketnoi.getConnection();
            ps = con.prepareStatement(sql);
//            ps.setString(1, kh.getMaKH());
            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getSDT());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDiaChi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int sua(String ma, KhachHang kh) {
    String sql = "UPDATE KhachHang SET ten_khach_hang = ?, so_dien_thoai = ?, email = ?, dia_chi = ? WHERE ma_khach_hang = ?";
    try (Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, kh.getTenKH());
        ps.setString(2, kh.getSDT());
        ps.setString(3, kh.getEmail());
        ps.setString(4, kh.getDiaChi());
        ps.setString(5, ma);
        return ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
    
    public ArrayList<KhachHang> timkiem(String Sdtcantim) {
    sql = "SELECT ma_khach_hang, ten_khach_hang, so_dien_thoai, email, dia_chi " +
          "FROM KhachHang " +
          "WHERE so_dien_thoai LIKE ?";
    
    ArrayList<KhachHang> list = new ArrayList<>();
    
    try {
        con = ketnoi.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + Sdtcantim + "%");
        rs = ps.executeQuery();
        
        while (rs.next()) {
           int idMaKhachHang = rs.getInt(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String email = rs.getString(5);
                String diachi = rs.getString(6);
                boolean gioiTinh = rs.getBoolean(7);
                boolean trangThai = rs.getBoolean(8);
                KhachHang kh = new KhachHang(idMaKhachHang, maKH, tenKH, sdt, email, diachi, gioiTinh, trangThai);
                list.add(kh);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Đóng tài nguyên
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return list;
}

}