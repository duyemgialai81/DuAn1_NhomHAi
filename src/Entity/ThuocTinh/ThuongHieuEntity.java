/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.ThuocTinh;

/**
 *
 * @author SingPC
 */
public class ThuongHieuEntity {
    private int idThuongHieu;
    private String maThuogHieu;
    private String tenThuongHieu;
    private String moTa;

    public ThuongHieuEntity() {
    }

    public ThuongHieuEntity(int idThuongHieu, String maThuogHieu, String tenThuongHieu, String moTa) {
        this.idThuongHieu = idThuongHieu;
        this.maThuogHieu = maThuogHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.moTa = moTa;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getMaThuogHieu() {
        return maThuogHieu;
    }

    public void setMaThuogHieu(String maThuogHieu) {
        this.maThuogHieu = maThuogHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    

    
    
}
