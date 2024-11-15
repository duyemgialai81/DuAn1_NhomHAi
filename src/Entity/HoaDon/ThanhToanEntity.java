/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.HoaDon;

import Entity.HoaDon.XemHoaDonTao;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author SingPC
 */
public class ThanhToanEntity {
 private int maHoaDon;
 private String tenSanPham;
 private String tenKhachHang;
 private String tenNhanVien;
 private int soLuongTon;
 private  float tongTien;
 private Date ngayLap;

    public ThanhToanEntity() {
    }

    public ThanhToanEntity(int maHoaDon, String tenSanPham, String tenKhachHang, String tenNhanVien, int soLuongTon, float tongTien, Date ngayLap) {
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.tenKhachHang = tenKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.soLuongTon = soLuongTon;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float tinhTongTien(ArrayList<XemHoaDonTao> hoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
     

   

    
}
