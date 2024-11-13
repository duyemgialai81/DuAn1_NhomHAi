/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author SingPC
 */
public class TaiKhoanEntity {
    private String maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;
    private int maRole;
    private int maNhanVien;
    private String tenNhanVien;

    public TaiKhoanEntity() {
    }

    public TaiKhoanEntity(String maTaiKhoan, String tenTaiKhoan, String matKhau, int maRole, int maNhanVien, String tenNhanVien) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.maRole = maRole;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaRole() {
        return maRole;
    }

    public void setMaRole(int maRole) {
        this.maRole = maRole;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

   

   
    
    
    
}
