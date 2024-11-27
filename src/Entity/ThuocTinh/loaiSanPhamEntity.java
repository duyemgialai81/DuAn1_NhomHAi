/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.ThuocTinh;

/**
 *
 * @author SingPC
 */
public class loaiSanPhamEntity {
    private int idLoaiSanPham;
    private String maLoaiSanPham;
    private String tenLoaiSanPham;
    private String mota;

    public loaiSanPhamEntity() {
    }

    public loaiSanPhamEntity(int idLoaiSanPham, String maLoaiSanPham, String tenLoaiSanPham, String mota) {
        this.idLoaiSanPham = idLoaiSanPham;
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.mota = mota;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    
    
    
    
}
