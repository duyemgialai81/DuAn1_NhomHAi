/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;
import org.bridj.cpp.com.OLEAutomationLibrary;

/**
 *
 * @author SingPC
 */
public class HoaDonEntity {
    private int idHoaDon;
    private String maHoaDon;
    private Date ngayLap;
    private float tongTien;
    private String tenNhanVien;
    private String tenKhachHang;

    public HoaDonEntity() {
    }

    public HoaDonEntity(int idHoaDon, String maHoaDon, Date ngayLap, float tongTien, String tenNhanVien, String tenKhachHang) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

   
    
}
