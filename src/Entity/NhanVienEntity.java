/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author SingPC
 */
public class NhanVienEntity {
    private String maNhanVien;
    private String tenNhanVien;
    private String vaiTro;
    private String diaChi;
    private String soDienThoai;
    private String ngaySinh;
    private boolean gioiTinh;
    private String email;
    private String matKhau;
    private String trangThai;
    private int idRole;

    public NhanVienEntity() {
    }

    public NhanVienEntity(String maNhanVien, String tenNhanVien, String soDienThoai, String ngaySinh, boolean gioiTinh, String email, String diaChi, String trangThai, String vaiTro, String matKhau, int idRole) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.vaiTro = vaiTro;
        this.matKhau = matKhau;
        this.idRole = idRole;
    }

    

    public NhanVienEntity(String maNhanVien, String tenNhanVien, String soDienThoai, String ngaySinh, boolean gioiTinh, String email, String diaChi, String trangThai, String vaiTro) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.vaiTro = vaiTro;
    }

    public NhanVienEntity(String tenNhanVien, String vaiTro, String diaChi, String soDienThoai, String ngaySinh, boolean gioiTinh, String email, String matKhau, String trangThai) {
        this.tenNhanVien = tenNhanVien;
        this.vaiTro = vaiTro;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
    }

    

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    
    public Object[] toDataRow(){
        return new Object[]{this.getMaNhanVien(),this.getTenNhanVien(),this.getVaiTro(),this.getDiaChi(),this.getSoDienThoai(),this.getNgaySinh(),this.isGioiTinh()?"Nam":"Nữ",this.getEmail(),this.getTrangThai()};
    }
}

