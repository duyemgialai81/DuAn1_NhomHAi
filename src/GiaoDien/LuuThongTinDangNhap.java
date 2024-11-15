/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;

import Entity.NhanVien.NhanVienEntity;
import KetNoiSQL.ketnoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SingPC
 */
public class LuuThongTinDangNhap {
    private static String taiKhoan;
    private static String vaiTro;
    private static String maNhanVien;
    private static String tenNhanVien;
    private static int inNhanVien;

    // Getter và Setter cho inNhanVien
    public static int getInNhanVien() {
        return inNhanVien;
    }

    public static void setInNhanVien(int inNhanVien) {
        LuuThongTinDangNhap.inNhanVien = inNhanVien;
    }

    // Getter và Setter cho tenNhanVien
    public static String getTenNhanVien() {
        return tenNhanVien;
    }

    public static void setTenNhanVien(String tenNhanVien) {
        LuuThongTinDangNhap.tenNhanVien = tenNhanVien;
    }

    // Getter và Setter cho maNhanVien
    public static String getMaNhanVien() {
        return maNhanVien;
    }

    public static void setMaNhanVien(String maNhanVien) {
        LuuThongTinDangNhap.maNhanVien = maNhanVien;
    }

    // Getter và Setter cho taiKhoan
    public static String getTaiKhoan() {
        return taiKhoan;
    }

    public static void setTaiKhoan(String taiKhoan) {
        LuuThongTinDangNhap.taiKhoan = taiKhoan;
    }

    // Getter và Setter cho vaiTro
    public static String getVaiTro() {
        return vaiTro;
    }

    public static void setVaiTro(String vaiTro) {
        LuuThongTinDangNhap.vaiTro = vaiTro;
    }
}
