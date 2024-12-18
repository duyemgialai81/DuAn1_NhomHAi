/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.HoaDon.ChiTietHoaDonEntity;
import Entity.DonHang.DonHangEntity;
import Entity.DonHangChiTiet.DonHangChiTietEntity;
import Entity.HoaDon.HoaDonEntity;
import Entity.HoaDon.HoaDonXemDuLieu;
import Entity.HoaDon.InsertHoaDonEntity;
import Entity.NhanVien.NhanVienEntity;
import Entity.SanPham.SanPhamEntity;
import Entity.HoaDon.XemHoaDonTao;
import Entity.thongke.ThongKeEntity;
import GiaoDien.HoaDonChiTiet;
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
                    WHERE hd.trangThai = N'Đang Chờ Thanh Toán';
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
    public ArrayList<XemHoaDonTao> getAllGioHang(int maHoaDon){
        ArrayList<XemHoaDonTao> ls = new ArrayList<>();
        String sql ="""
                    select  sp.ma_san_pham, sp.ten_san_pham, cthd.so_luong,cthd.gia_ban
                    from ChiTietHoaDon cthd
                    join donhang hd on hd.id_ma_don_hang = cthd.ma_don_hang
                    join SanPham sp on sp.id_ma_san_pham = cthd.ma_san_pham
                    where hd.id_ma_don_hang = ?
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
        String sql = "INSERT INTO ChiTietHoaDon (ma_don_hang, ma_san_pham, so_luong, gia_ban) VALUES (?, ?, ?, ?)";
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
public ArrayList<HoaDonChiTiet> getAllChiTietHoaDon(){
     ArrayList<HoaDonChiTiet> ls = new ArrayList<>();
    String sql ="""
                select hd.ma_hoa_don, 
                                               sum(dhct.so_luong * dhct.gia_ban - COALESCE(voucher.gia_tri, 0) - dhct.thue) as tongTien,
                                               hd.tien_khach_dua - hd.tien_tra_khach as tien_can_thu, hd.tien_khach_dua,hd.tien_tra_khach,hd.phuong_thuc,dh.ngay_dat, dh.trang_thai
                                               from HoaDon hd
                                               left join DonHang dh on hd.ma_don_hang = dh.id_ma_don_hang
                                               join ChiTietDonHang dhct on dh.id_ma_don_hang = dhct.ma_don_hang
                                               left join event voucher on dhct.ma_voucher = voucher.id_voucher
                                               group by hd.ma_hoa_don, hd.tien_khach_dua, hd.tien_tra_khach,hd.phuong_thuc,dh.ngay_dat,dh.trang_thai
                """;
     try {
         Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
            HoaDonChiTiet hd = new HoaDonChiTiet();
            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hd.setTongTien(rs.getFloat("tongTien"));
            hd.setThanhToan(rs.getDouble("tien_can_thu"));
            hd.setTienKhachDua(rs.getDouble("tien_khach_dua"));
            hd.setTienTraKhach(rs.getDouble("tien_tra_khach"));
            hd.setPhuongThuc(rs.getString("phuong_thuc"));
            hd.setNgayDat(rs.getDate("ngay_dat"));
            hd.setTrangThai(rs.getString("trang_thai"));
             ls.add(hd);
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     return  ls;
 }
 public ArrayList<DonHangChiTietEntity> layGioHangSanPham(int idDonHang){
     ArrayList<DonHangChiTietEntity> ls= new ArrayList<>();
     String  sql = """
                      SELECT 
                   ctdh.ma_san_pham,
                            sp.ma_san_pham as maSanPham, 
                            sp.ten_san_pham, 
                            sp.gia_ban, 
                            SUM(ctdh.so_luong) AS so_luong
                        FROM 
                            chitietdonhang ctdh
                        JOIN 
                            SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
                        JOIN 
                            DonHang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
                        WHERE 
                            dh.id_ma_don_hang = ? 
                        GROUP BY 
                            sp.ma_san_pham, sp.ten_san_pham, sp.gia_ban,ctdh.ma_san_pham
                   """;
     try {
         Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setObject(1, idDonHang);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             DonHangChiTietEntity ct = new DonHangChiTietEntity();
             ct.setIdMaSanPham(rs.getInt("ma_san_pham"));
             ct.setMaSanPham(rs.getString("maSanPham"));
             ct.setTenSanPham(rs.getString("ten_san_pham"));
             ct.setGiaBan(rs.getFloat("gia_ban"));
             ct.setSoLuong(rs.getInt("so_luong"));
            ls.add(ct);
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     return ls;
     
 }
    public ArrayList<HoaDonChiTiet> timKiemChiTietHoaDon(Date ngayBatDau, Date ngayKetThuc, String maHoaDon, String trangThai, String phuongThuc) {
    ArrayList<HoaDonChiTiet> ls = new ArrayList<>();
    String sql = """
            SELECT hd.ma_hoa_don, 
                   SUM(dhct.so_luong * dhct.gia_ban - COALESCE(voucher.gia_tri, 0) - dhct.thue) AS tongTien,
                   hd.tien_khach_dua - hd.tien_tra_khach AS tien_can_thu, 
                   hd.tien_khach_dua, 
                   hd.tien_tra_khach, 
                   hd.phuong_thuc, 
                   dh.ngay_dat, 
                   dh.trang_thai
            FROM HoaDon hd
            LEFT JOIN DonHang dh ON hd.ma_don_hang = dh.id_ma_don_hang
            JOIN ChiTietDonHang dhct ON dh.id_ma_don_hang = dhct.ma_don_hang
            LEFT JOIN event voucher ON dhct.ma_voucher = voucher.id_voucher
            WHERE 
              (? IS NULL OR dh.ngay_dat BETWEEN ? AND ?)
              AND (? IS NULL OR dh.trang_thai = ?)
              AND (? IS NULL OR hd.phuong_thuc = ?)
            GROUP BY hd.ma_hoa_don, hd.tien_khach_dua, hd.tien_tra_khach, hd.phuong_thuc, dh.ngay_dat, dh.trang_thai
            """;
    try {
        Connection con = ketnoi.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        
        // Set ngày bắt đầu và kết thúc
        ps.setDate(1, ngayBatDau != null ? new java.sql.Date(ngayBatDau.getTime()) : null);
        ps.setDate(2, ngayBatDau != null ? new java.sql.Date(ngayBatDau.getTime()) : null);
        ps.setDate(3, ngayKetThuc != null ? new java.sql.Date(ngayKetThuc.getTime()) : null);
        
        // Set trạng thái và phương thức thanh toán
        ps.setString(4, trangThai != null && !trangThai.isEmpty() ? trangThai : null);
        ps.setString(5, trangThai != null && !trangThai.isEmpty() ? trangThai : null);
        ps.setString(6, phuongThuc != null && !phuongThuc.isEmpty() ? phuongThuc : null);
        ps.setString(7, phuongThuc != null && !phuongThuc.isEmpty() ? phuongThuc : null);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            HoaDonChiTiet hd = new HoaDonChiTiet();
            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hd.setTongTien(rs.getFloat("tongTien"));
            hd.setThanhToan(rs.getDouble("tien_can_thu"));
            hd.setTienKhachDua(rs.getDouble("tien_khach_dua"));
            hd.setTienTraKhach(rs.getDouble("tien_tra_khach"));
            hd.setPhuongThuc(rs.getString("phuong_thuc"));
            hd.setNgayDat(rs.getDate("ngay_dat"));
            hd.setTrangThai(rs.getString("trang_thai"));
            ls.add(hd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ls;
}
    
    
    public ArrayList<HoaDonChiTiet> timkiemSanPham(String TrangThai, String PhuongThuc, String maHoaDon, Date NgayBatDau, Date NgayKetThuc) {
    ArrayList<HoaDonChiTiet> ls = new ArrayList<>();
    String sql = """
       select hd.ma_hoa_don, 
               sum(dhct.so_luong * dhct.gia_ban - COALESCE(voucher.gia_tri, 0) - dhct.thue) as tongTien,
               hd.tien_khach_dua - hd.tien_tra_khach as tien_can_thu, hd.tien_khach_dua, hd.tien_tra_khach, hd.phuong_thuc, dh.ngay_dat, dh.trang_thai
       from HoaDon hd
       left join DonHang dh on hd.ma_don_hang = dh.id_ma_don_hang
       join ChiTietDonHang dhct on dh.id_ma_don_hang = dhct.ma_don_hang
       left join event voucher on dhct.ma_voucher = voucher.id_voucher
       WHERE (dh.trang_thai LIKE ? OR ? = '') 
       AND (hd.phuong_thuc LIKE ? OR ? = '')
       AND (hd.ma_hoa_don LIKE ? OR ? = '')
       AND (dh.ngay_dat BETWEEN ? and ?)
       group by hd.ma_hoa_don, hd.tien_khach_dua, hd.tien_tra_khach, hd.phuong_thuc, dh.ngay_dat, dh.trang_thai
    """;
    
    try (Connection con = ketnoi.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        
        // Cài đặt các tham số cho các điều kiện tìm kiếm
        ps.setString(1, "%" + TrangThai + "%"); 
        ps.setString(2, TrangThai);
        ps.setString(3, "%" + PhuongThuc + "%");  
        ps.setString(4, PhuongThuc);
        ps.setString(5, "%" + maHoaDon + "%");  
        ps.setString(6, maHoaDon);

        // Chuyển đổi java.util.Date sang java.sql.Date
        if (NgayBatDau != null) {
            ps.setDate(7, new java.sql.Date(NgayBatDau.getTime())); // Chuyển ngày bắt đầu
        } else {
            ps.setNull(7, java.sql.Types.DATE); // Nếu không có ngày bắt đầu, truyền null
        }
        
        if (NgayKetThuc != null) {
            ps.setDate(8, new java.sql.Date(NgayKetThuc.getTime())); // Chuyển ngày kết thúc
        } else {
            ps.setNull(8, java.sql.Types.DATE); // Nếu không có ngày kết thúc, truyền null
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            HoaDonChiTiet hdd = new HoaDonChiTiet();
            hdd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hdd.setTongTien(rs.getFloat("tongTien"));
            hdd.setThanhToan(rs.getDouble("tien_can_thu"));
            hdd.setTienKhachDua(rs.getDouble("tien_khach_dua"));
            hdd.setTienTraKhach(rs.getDouble("tien_tra_khach"));
            hdd.setPhuongThuc(rs.getString("phuong_thuc"));
            hdd.setNgayDat(rs.getDate("ngay_dat"));
            hdd.setTrangThai(rs.getString("trang_thai"));
            ls.add(hdd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ls;
}


}
