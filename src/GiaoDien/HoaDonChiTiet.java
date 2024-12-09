/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;

import java.util.Date;

/**
 *
 * @author SingPC
 */
public class HoaDonChiTiet {
    private String maHoaDon;
    private double tongTien;
    private double thanhToan;
    private double tienKhachDua;
    private double tienTraKhach;
    private String phuongThuc;
    private Date ngayDat;
    private String trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHoaDon, double tongTien, double thanhToan, double tienKhachDua, double tienTraKhach, String phuongThuc, Date ngayDat, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.thanhToan = thanhToan;
        this.tienKhachDua = tienKhachDua;
        this.tienTraKhach = tienTraKhach;
        this.phuongThuc = phuongThuc;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(double thanhToan) {
        this.thanhToan = thanhToan;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public double getTienTraKhach() {
        return tienTraKhach;
    }

    public void setTienTraKhach(double tienTraKhach) {
        this.tienTraKhach = tienTraKhach;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
}
