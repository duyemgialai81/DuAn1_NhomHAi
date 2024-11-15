/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.SanPham;

/**
 *
 * @author SingPC
 */
public class SanPhamEntity {
    private int idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private float giaBan;
    private float giaNhap;
    private int soLuong;
    private String hinhAnh;
    private String  tenMaLoai;
    private String kichCo;       
    private String mauSac;       
    private String tenThuongHieu; 
    private String chatLieu;      
    private String quocGia; 
    private String trangThai;

    public SanPhamEntity() {
    }

    public SanPhamEntity(int idSanPham, String maSanPham, String tenSanPham, float giaBan, float giaNhap, int soLuong, String hinhAnh, String tenMaLoai, String kichCo, String mauSac, String tenThuongHieu, String chatLieu, String quocGia, String trangThai) {
        this.idSanPham = idSanPham;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.tenMaLoai = tenMaLoai;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.tenThuongHieu = tenThuongHieu;
        this.chatLieu = chatLieu;
        this.quocGia = quocGia;
        this.trangThai = trangThai;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
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

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenMaLoai() {
        return tenMaLoai;
    }

    public void setTenMaLoai(String tenMaLoai) {
        this.tenMaLoai = tenMaLoai;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

  

    
    
}
