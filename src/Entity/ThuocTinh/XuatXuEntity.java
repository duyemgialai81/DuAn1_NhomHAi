/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.ThuocTinh;

/**
 *
 * @author SingPC
 */
public class XuatXuEntity {
    private int idMaXuatXu;
    private String maXuatXu;
    private String quocGia;

    private String mota;

    public XuatXuEntity() {
    }

    public XuatXuEntity(int idMaXuatXu, String maXuatXu, String quocGia, String mota) {
        this.idMaXuatXu = idMaXuatXu;
        this.maXuatXu = maXuatXu;
        this.quocGia = quocGia;
        this.mota = mota;
    }

    public int getIdMaXuatXu() {
        return idMaXuatXu;
    }

    public void setIdMaXuatXu(int idMaXuatXu) {
        this.idMaXuatXu = idMaXuatXu;
    }

    public String getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(String maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    
    
    
}
