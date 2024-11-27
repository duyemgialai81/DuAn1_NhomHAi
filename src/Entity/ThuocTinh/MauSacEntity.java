/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.ThuocTinh;

/**
 *
 * @author SingPC
 */
public class MauSacEntity {
    private int IdMauSac;
    private String maMauSac;
    
    private String mauSac;
    private String moTa;

    public MauSacEntity() {
    }

    public MauSacEntity(int IdMauSac, String maMauSac, String mauSac, String moTa) {
        this.IdMauSac = IdMauSac;
        this.maMauSac = maMauSac;
        this.mauSac = mauSac;
        this.moTa = moTa;
    }

    public int getIdMauSac() {
        return IdMauSac;
    }

    public void setIdMauSac(int IdMauSac) {
        this.IdMauSac = IdMauSac;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
}
