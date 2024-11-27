/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author SingPC
 */
class ProductInfo {
    String maSanPham;  // Mã sản phẩm
    String tenSanPham; // Tên sản phẩm
    int soLuong;       // Số lượng sản phẩm
    float giaBan;      // Giá bán của sản phẩm
    float tongTien;    // Tổng tiền của sản phẩm (soLuong * giaBan)

    // Constructor (hàm khởi tạo) để gán giá trị cho các thuộc tính
    public ProductInfo(String maSanPham, String tenSanPham, int soLuong, float giaBan, float tongTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.tongTien = tongTien;
    }
}