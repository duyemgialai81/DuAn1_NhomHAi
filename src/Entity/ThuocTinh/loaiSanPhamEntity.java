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
    private String tenLoaiSanPham;
    private String mota;

    public loaiSanPhamEntity() {
    }

    public loaiSanPhamEntity(String tenLoaiSanPham, String mota) {
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.mota = mota;
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
