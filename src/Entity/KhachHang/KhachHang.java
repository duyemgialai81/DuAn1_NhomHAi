/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.KhachHang;

/**
 *
 * @author SingPC
 */
public class KhachHang {
    private int idKhachHang;
    private String MaKH;
    private String TenKH;
    private String SDT;
    private String Email;
    private String DiaChi;
    private boolean gioiTinh;
     private boolean trangThai;

    public KhachHang() {
    }

    public KhachHang(int idKhachHang, String MaKH, String TenKH, String SDT, String Email, String DiaChi, boolean gioiTinh, boolean trangThai) {
        this.idKhachHang = idKhachHang;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    
    
    public Object[] toDaTaRow(){
        return new Object[]{
            this.getIdKhachHang(),
            this.getMaKH(),
            this.getTenKH(),
            this.getSDT(),
            this.getEmail(),
            this.getDiaChi(),
                this.isGioiTinh(),
                this.isTrangThai()
        };
    }
}
