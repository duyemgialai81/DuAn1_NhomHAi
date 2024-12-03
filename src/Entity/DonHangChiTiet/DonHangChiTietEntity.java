/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.DonHangChiTiet;

/**
 *
 * @author SingPC
 */
public class DonHangChiTietEntity {
    private int idDonHangChiTiet;
    private int idMaSanPham;
    private String maDonHangChiTiet;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double giaBan;
    private double tongTien;
    private String trangThai;

    public DonHangChiTietEntity() {
    }

    public DonHangChiTietEntity(int idDonHangChiTiet, int idMaSanPham, String maDonHangChiTiet, String maSanPham, String tenSanPham, int soLuong, double giaBan, double tongTien, String trangThai) {
        this.idDonHangChiTiet = idDonHangChiTiet;
        this.idMaSanPham = idMaSanPham;
        this.maDonHangChiTiet = maDonHangChiTiet;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public DonHangChiTietEntity(String maSanPham, String tenSanPham, int soLuong, double giaBan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public int getIdDonHangChiTiet() {
        return idDonHangChiTiet;
    }

    public void setIdDonHangChiTiet(int idDonHangChiTiet) {
        this.idDonHangChiTiet = idDonHangChiTiet;
    }

    public int getIdMaSanPham() {
        return idMaSanPham;
    }

    public void setIdMaSanPham(int idMaSanPham) {
        this.idMaSanPham = idMaSanPham;
    }

    public String getMaDonHangChiTiet() {
        return maDonHangChiTiet;
    }

    public void setMaDonHangChiTiet(String maDonHangChiTiet) {
        this.maDonHangChiTiet = maDonHangChiTiet;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
   
 
    
}
