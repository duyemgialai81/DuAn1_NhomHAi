/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author SingPC
 */
public class AddSanPhamEntity {
    private String maSanPham;
    private String tenSanPham;
    private float giaBan;
    private float giaNhap;
    private int soLuong;
    private String hinhAnh;
    private int  MaLoai;
    private int maKichCo;       
    private int maMauSac;       
    private int maThuongHieu;
    private int maChatLieu;      
    private int maXuatXu;

    public AddSanPhamEntity() {
    }

    public AddSanPhamEntity(String maSanPham, String tenSanPham, float giaBan, float giaNhap, int soLuong, String hinhAnh, int MaLoai, int maKichCo, int maMauSac, int maThuongHieu, int maChatLieu, int maXuatXu) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.MaLoai = MaLoai;
        this.maKichCo = maKichCo;
        this.maMauSac = maMauSac;
        this.maThuongHieu = maThuongHieu;
        this.maChatLieu = maChatLieu;
        this.maXuatXu = maXuatXu;
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

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    public int getMaKichCo() {
        return maKichCo;
    }

    public void setMaKichCo(int maKichCo) {
        this.maKichCo = maKichCo;
    }

    public int getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(int maMauSac) {
        this.maMauSac = maMauSac;
    }

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public int getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(int maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public int getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(int maXuatXu) {
        this.maXuatXu = maXuatXu;
    }
    
   
    
}
