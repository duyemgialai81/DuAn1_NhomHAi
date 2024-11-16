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
    private String MaKH;
    private String TenKH;
    private String SDT;
    private String Email;
    private String DiaChi;
    public KhachHang() {
    }
    public KhachHang(String MaKH, String TenKH, String SDT, String Email, String DiaChi) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
    }

    public KhachHang(String TenKH, String SDT, String Email, String DiaChi) {
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
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
    
    public Object[] toDaTaRow(){
        return new Object[]{
            this.getMaKH(),
            this.getTenKH(),
            this.getSDT(),
            this.getEmail(),
            this.getDiaChi()
        };
    }
}
