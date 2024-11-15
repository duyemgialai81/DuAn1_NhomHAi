/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.KhuyenMai;

import java.util.Date;

/**
 *
 * @author SingPC
 */
public class KhuyenMaiEntity {
    private int idVoucher;
    private float giaTri;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String trangThai;
    private String tenVouCher;
    private String moTa;
    private int maSanPham;
    private String loaiTriGia;
    private String truongTrinhKhuyenMai;

    public KhuyenMaiEntity() {
    }

    public KhuyenMaiEntity(int idVoucher, float giaTri, Date ngayBatDau, Date ngayKetThuc, String trangThai, String tenVouCher, String moTa, int maSanPham, String loaiTriGia, String truongTrinhKhuyenMai) {
        this.idVoucher = idVoucher;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.tenVouCher = tenVouCher;
        this.moTa = moTa;
        this.maSanPham = maSanPham;
        this.loaiTriGia = loaiTriGia;
        this.truongTrinhKhuyenMai = truongTrinhKhuyenMai;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(float giaTri) {
        this.giaTri = giaTri;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenVouCher() {
        return tenVouCher;
    }

    public void setTenVouCher(String tenVouCher) {
        this.tenVouCher = tenVouCher;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getLoaiTriGia() {
        return loaiTriGia;
    }

    public void setLoaiTriGia(String loaiTriGia) {
        this.loaiTriGia = loaiTriGia;
    }

    public String getTruongTrinhKhuyenMai() {
        return truongTrinhKhuyenMai;
    }

    public void setTruongTrinhKhuyenMai(String truongTrinhKhuyenMai) {
        this.truongTrinhKhuyenMai = truongTrinhKhuyenMai;
    }

    
    
}
