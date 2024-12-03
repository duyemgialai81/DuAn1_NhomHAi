/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.SanPham;

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
    private String  MaLoai;
    private String maKichCo;       
    private String maMauSac;       
    private String maThuongHieu;
    private String maChatLieu;      
    private String maXuatXu;

    public AddSanPhamEntity() {
    }

    public AddSanPhamEntity(String maSanPham, String tenSanPham, float giaBan, float giaNhap, int soLuong, String hinhAnh, String MaLoai, String maKichCo, String maMauSac, String maThuongHieu, String maChatLieu, String maXuatXu) {
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

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getMaKichCo() {
        return maKichCo;
    }

    public void setMaKichCo(String maKichCo) {
        this.maKichCo = maKichCo;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public String getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(String maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

   
   
    
}
