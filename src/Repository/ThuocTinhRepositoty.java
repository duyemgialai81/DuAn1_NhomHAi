package Repository;

import Entity.ThuocTinh.ChatLieuEntity;
import Entity.ThuocTinh.KichCoEnTity;
import Entity.ThuocTinh.MauSacEntity;
import Entity.ThuocTinh.ThuongHieuEntity;
import Entity.ThuocTinh.XuatXuEntity;
import Entity.ThuocTinh.loaiSanPhamEntity;
import java.util.ArrayList;
import java.sql.*;
import KetNoiSQL.ketnoi;


public class ThuocTinhRepositoty {
    public ArrayList<loaiSanPhamEntity> loaiSanPhaam() {
        ArrayList<loaiSanPhamEntity> ls = new ArrayList<>();
        String sql = """
                     select ten_loai_san_pham, mo_ta
                     from loaiSanPham
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                loaiSanPhamEntity sp = new loaiSanPhamEntity();
                sp.setTenLoaiSanPham(rs.getString("ten_loai_san_pham"));
                sp.setMota(rs.getString("mo_ta"));
                ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("hiện thị dữ liệu thất bại");
        }
        return ls;
    }

    public ArrayList<MauSacEntity> mauSac() {
        ArrayList<MauSacEntity> ls = new ArrayList<>();
        String sql = """
                     select mau_sac_san_pham, mo_ta
                     from mauSac 
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSacEntity ms = new MauSacEntity();
                ms.setMauSac(rs.getString("mau_sac_san_pham"));
                ms.setMoTa(rs.getString("mo_ta"));
                ls.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public ArrayList<KichCoEnTity> kichCo() {
        ArrayList<KichCoEnTity> ls = new ArrayList<>();
        String sql = """
                    select  kich_co, mo_ta
                     from kichCo
                    
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCoEnTity kc = new KichCoEnTity();
                kc.setKichCo(rs.getString("kich_co"));
                kc.setMoTa(rs.getString("mo_ta"));
                ls.add(kc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public ArrayList<ChatLieuEntity> chatLieu(){
        ArrayList<ChatLieuEntity> ls = new ArrayList<>();
        String sql = """
                     select chat_lieu_san_pham, mo_ta
                     from chatLieu
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ChatLieuEntity cl = new ChatLieuEntity();
                cl.setChatLieu(rs.getString("chat_lieu_san_pham"));
                cl.setMoTa(rs.getString("mo_ta"));
                ls.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public ArrayList<ThuongHieuEntity> thuogHieu(){
        ArrayList<ThuongHieuEntity> ls = new ArrayList<>();
        String sql = """
                     select ten_thuong_hieu, mo_ta
                     from thuongHieu
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ThuongHieuEntity cl = new ThuongHieuEntity();
                cl.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
                cl.setMoTa(rs.getString("mo_ta"));
                ls.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public ArrayList<XuatXuEntity> xuatXu(){
        ArrayList<XuatXuEntity> ls = new ArrayList<>();
        String sql ="""
                    select quoc_gia, mo_ta
                    from xuatxu
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                XuatXuEntity xx = new XuatXuEntity();
                xx.setQuocGia(rs.getString("quoc_gia"));
                xx.setMota(rs.getString("mo_ta"));
                ls.add(xx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public boolean  addLoaiSanPham(loaiSanPhamEntity sp ){
        int check =0;
        String sql ="""
                    insert into loaiSanPham (ten_loai_san_pham, mo_ta)
                    values (?,?)
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenLoaiSanPham());
            ps.setObject(2, sp.getMota());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean addThuognHieu(ThuongHieuEntity th){
        int check = 0;
        String sql ="""
                    insert into thuonghieu(ten_thuong_hieu, mo_ta)
                    values(?,?)
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, th.getTenThuongHieu());
            ps.setObject(2, th.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    public boolean addMauSac(MauSacEntity ms ){
        int check = 0;
        String sql ="""
                    insert into mausac(mau_sac_san_pham, mo_ta)
                    values(?,?)
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setObject(1, ms.getMauSac());
            ps.setObject(2, ms.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean addKichCo(KichCoEnTity kc ){
        int check =0;
        String sql ="""
                    insert into kichco (kich_co, mo_ta)
                    values (?,?)
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, kc.getKichCo());
            ps.setObject(2, kc.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    public boolean addChatLieu(ChatLieuEntity cl){
        int check = 0;
        String sql ="""
                    insert into chatLieu(chat_lieu_san_pham, mo_ta)
                    values(?,?)
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, cl.getChatLieu());
            ps.setObject(2, cl.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean addXuatXu(XuatXuEntity xx ){
        int check =0;
        String sql ="""
                    insert into xuatxu(quoc_gia, mo_ta)
                    values(?,?)
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setObject(1, xx.getQuocGia());
            ps.setObject(2, xx.getMota());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean deleteLoaiSanPham(int idMaLoai){
        int check = 0;
        String sql = """
                     delete LoaiSanPham
                     where id_ma_loai = ?
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, idMaLoai);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean deleteKichCo(String moTa){
        int check = 0;
        String sql = """
                     delete KichCo
                     where mo_ta =?
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, moTa);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean updateLoaiSanPham(loaiSanPhamEntity sp, String tenLoaiSanPham){
        int check =0;
        String sql = """
                     update LoaiSanPham
                     set ten_loai_san_pham = ?, mo_ta = ?
                     where ten_loai_san_pham = ?
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenLoaiSanPham());
            ps.setObject(2, sp.getMota());
            ps.setObject(3, tenLoaiSanPham);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean updateMauSac( MauSacEntity ms, String mauSach){
        int check = 0;
        String sql ="""
                    update mauSac 
                    set ma_mau_sac_san_pham = ?, mo_ta =?
                    where ma_mau_sac_san_pham =?
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
          ps.setObject(1, ms.getMauSac());
          ps.setObject(2, ms.getMoTa());
          ps.setObject(3, mauSach);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    public boolean updateChatLieuSanPham ( ChatLieuEntity cl, int maChatLieu){
        int check =0;
        String sql = """
                     update chatlieu
                     set chat_lieu_san_pham =?
                     where ma_chat_lieu_san_pham =?
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,cl.getChatLieu() );
            ps.setObject(2, cl.getMoTa());
            ps.setObject(3 ,maChatLieu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public boolean  updateThuongHieu(ThuongHieuEntity th, int maThuongHieu){
        int check =0;
        String sql = """
                     update thuongHieu
                     set ten_thuong_hieu_san_pham
                     where ma_thuong_hieu
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setObject(1, th.getTenThuongHieu());
            ps.setObject(2, th.getMoTa());
            ps.setObject(3, maThuongHieu);
        } catch (Exception e) {
        }
        
        return check >0;
    }
    public boolean updateXuatXu(XuatXuEntity xx, String maXuatXu){
       int check =0;
       String sql = """
                    update xuatxu
                    set quoc_gia =?, mo_ta =?
                    where quoc_gia = ?
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, xx.getQuocGia());
            ps.setObject(2, xx.getMota());
            ps.setObject(3, maXuatXu);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return check >0;
    }
    
}
