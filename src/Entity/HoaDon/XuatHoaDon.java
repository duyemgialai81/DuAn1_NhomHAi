/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.HoaDon;

import java.util.Date;

/**
 *
 * @author SingPC
 */
public class XuatHoaDon {

    private String tenNhanVien;
    private String tenKhachHang;
    private Date ngayDat;
    private String email;
    private String soDienThoai;
    private float tienKhachDua;
    private float tienTraKhach;
    private String phuongThuc;
    private float thanhTien;
    private float giaTri;
    private float thue;
    private String maHoaDon;
    private String tenSanPham;
    private int soLuong;
    private float giaBan;
    private int idHoaDon;

    public XuatHoaDon() {
    }

    public XuatHoaDon(String tenNhanVien, String tenKhachHang, Date ngayDat, String email, String soDienThoai, float tienKhachDua, float tienTraKhach, String phuongThuc, float thanhTien, float giaTri, float thue, String maHoaDon, String tenSanPham, int soLuong, float giaBan, int idHoaDon) {
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tienKhachDua = tienKhachDua;
        this.tienTraKhach = tienTraKhach;
        this.phuongThuc = phuongThuc;
        this.thanhTien = thanhTien;
        this.giaTri = giaTri;
        this.thue = thue;
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.idHoaDon = idHoaDon;
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

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public float getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(float tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public float getTienTraKhach() {
        return tienTraKhach;
    }

    public void setTienTraKhach(float tienTraKhach) {
        this.tienTraKhach = tienTraKhach;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(float giaTri) {
        this.giaTri = giaTri;
    }

    public float getThue() {
        return thue;
    }

    public void setThue(float thue) {
        this.thue = thue;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
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

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

}
