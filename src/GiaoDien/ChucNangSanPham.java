/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.SanPham.AddSanPhamEntity;
import Entity.khac.RoleEntity;
import Entity.SanPham.SanPhamEntity;
import Entity.SanPham.ThongTinSanPham;
import Entity.ThuocTinh.ChatLieuEntity;
import Entity.ThuocTinh.KichCoEnTity;
import Entity.ThuocTinh.MauSacEntity;
import Entity.ThuocTinh.ThuongHieuEntity;
import Entity.ThuocTinh.XuatXuEntity;
import Entity.ThuocTinh.loaiSanPhamEntity;
import KetNoiSQL.ketnoi;
import Repository.SanPhamRepository;
import Repository.TaiKhoanRepository;
import Repository.ThuocTinhRepositoty;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author SingPC
 */
public class ChucNangSanPham extends javax.swing.JPanel {

    private SanPhamRepository ls = new SanPhamRepository();
    private DefaultTableModel md = new DefaultTableModel();
    private TaiKhoanRepository duyem = new TaiKhoanRepository();
private String hiddenPath;
private ThuocTinhRepositoty thuocTinh = new ThuocTinhRepositoty();
    /**
     * Creates new form ChucNangSanPham
     */
    public ChucNangSanPham() {
        initComponents();

        hienThiThongTinSanPham(ls.getAll());
        hienThiDuLieu(ls.getSanPhamChhiTiet());
//        thuocTinhLoaiSanPham();
        thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }
    private void hienThiThuocTinhKichCo(ArrayList<KichCoEnTity>kichCo){
        md = (DefaultTableModel)thuocTinnhKichCo.getModel();
        md.setRowCount(0);
        for (KichCoEnTity kichCoEnTity : kichCo) {
            md.addRow(new Object[]{
                kichCoEnTity.getIdKichCo(), kichCoEnTity.getMaKichCo(), kichCoEnTity.getKichCo(), kichCoEnTity.getMoTa()
            });
        }
    }
    private MauSacEntity getFatMauSac(){
        MauSacEntity ms= new MauSacEntity();
        ms.setIdMauSac(Integer.parseInt(idMaMauSacSanPham.getText()));
        ms.setMauSac(tenMauSacSanPham.getText());
        ms.setMaMauSac(maMauSacSanPham.getText());
        ms.setMoTa(moTaMauSacSanPham.getText());
        return ms;
    }
    
    private void hienThiThuocTinhSanPham(ArrayList<loaiSanPhamEntity> loaiSanPham){
        md = (DefaultTableModel) thuocTinhLoaiSanPham.getModel();
        md.setRowCount(0);
        for (loaiSanPhamEntity sanPhamEntity : loaiSanPham) {
            md.addRow(new Object[]{
              sanPhamEntity.getIdLoaiSanPham(), sanPhamEntity.getMaLoaiSanPham(),  sanPhamEntity.getTenLoaiSanPham(), sanPhamEntity.getMota()
            });
        }
    }
    private void hienThiThuocTinhChatLieu(ArrayList<ChatLieuEntity> chatLieu){
        md = (DefaultTableModel)thuocTinhChatLieu.getModel();
        md.setRowCount(0);
        for (ChatLieuEntity chatLieuEntity : chatLieu) {
            md.addRow(new Object[]{
                chatLieuEntity.getIdChatLieu(), chatLieuEntity.getMaChatLieu(), chatLieuEntity.getChatLieu(), chatLieuEntity.getMoTa()
            });
        }
    }
    private void HienThiDuLieuThuongHieu(ArrayList<ThuongHieuEntity>thuongHieu){
        md = (DefaultTableModel)thuocTinhThuongHieu.getModel();
        md.setRowCount(0);
        for (ThuongHieuEntity thuongHieuEntity : thuongHieu) {
            md.addRow(new Object[]{
                thuongHieuEntity.getIdThuongHieu(), thuongHieuEntity.getMaThuogHieu(), thuongHieuEntity.getTenThuongHieu(), thuongHieuEntity.getMoTa()
            });
        }
    }
    private void hienThiThuocTinhXuatXu(ArrayList<XuatXuEntity> xuatXu){
        md = (DefaultTableModel) thuocTinhXuatXu.getModel();
        md.setRowCount(0);
        for (XuatXuEntity xuatXuEntity : xuatXu) {
            md.addRow(new Object[]{
                xuatXuEntity.getIdMaXuatXu(), xuatXuEntity.getMaXuatXu(), xuatXuEntity.getQuocGia(), xuatXuEntity.getMota()
            });
        }
    }
    private void hienThiDuLieuMauSac (ArrayList<MauSacEntity> mauSac){
        md = (DefaultTableModel) thuocTinhMauSacSanPham.getModel();
        md.setRowCount(0);
        for (MauSacEntity mauSacEntity : mauSac) {
            md.addRow(new Object[]{
                mauSacEntity.getIdMauSac(), mauSacEntity.getMaMauSac(), mauSacEntity.getMauSac(), mauSacEntity.getMoTa()
            });
        }
    }
    
    private void hienThiDuLieuLenTextFileThuocTinhSanPhamLoaiSanPham(int index){
        loaiSanPhamEntity sp = thuocTinh.loaiSanPhaam().get(index);
        tenLoaiSanPham.setText(sp.getTenLoaiSanPham());
        moTaSanPham.setText(sp.getMota());
        idLoaiSanPham.setText(String.valueOf(sp.getIdLoaiSanPham()));
        maLoaiSanPham.setText(sp.getMaLoaiSanPham());
    }
    private loaiSanPhamEntity getLoaiSanPham(){
        loaiSanPhamEntity sp = new loaiSanPhamEntity();
        sp.setTenLoaiSanPham(tenLoaiSanPham.getText());
        sp.setMota(moTaSanPham.getText());
        return sp;
    }
private void thuocTinhLoaiSanPhamm(){
        ArrayList<loaiSanPhamEntity> ls = new ArrayList<>();
        String sql = """
                     select * from LoaiSanPham
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
             txt_addDanhMuc.removeAllItems();
            while (rs.next()) {
                loaiSanPhamEntity sp = new loaiSanPhamEntity();
                sp.setTenLoaiSanPham(rs.getString("ten_loai_san_pham"));
                sp.setMota(rs.getString("mo_ta"));
                ls.add(sp);
                  String tenLoai = rs.getString("ten_loai_san_pham");
            txt_addDanhMuc.addItem(tenLoai);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("hiện thị dữ liệu thất bại");
        }
}
private void thuocTinhKichCo(){
        ArrayList<KichCoEnTity> ls = new ArrayList<>();
        String sql = """
                    select kich_co, mo_ta
                     from kichCo
                    
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            txt_addSize.removeAllItems();;
            while (rs.next()) {
                KichCoEnTity kc = new KichCoEnTity();
                kc.setKichCo(rs.getString("kich_co"));
                kc.setMoTa(rs.getString("mo_ta"));
                ls.add(kc);
                String kichCo= rs.getString("kich_co");
                txt_addSize.addItem(kichCo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
private void thuocTinhChatLieu(){
    ArrayList<ChatLieuEntity> ls = new ArrayList<>();
        String sql = """
                     select chat_lieu_san_pham, mo_ta
                     from chatLieu
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            txt_addChatLieu.removeAllItems();
            while(rs.next()){
                ChatLieuEntity cl = new ChatLieuEntity();
                cl.setChatLieu(rs.getString("chat_lieu_san_pham"));
                cl.setMoTa(rs.getString("mo_ta"));
                ls.add(cl);
                String chtaLieu = rs.getString("chat_lieu_san_pham");
                txt_addChatLieu.addItem(chtaLieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
private void thuocTinhThuongHieu(){
    ArrayList<ThuongHieuEntity> ls = new ArrayList<>();
        String sql = """
                     select ten_thuong_hieu, mo_ta
                     from thuongHieu
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            txt_addThuongHieu.removeAllItems();
            while(rs.next()){
                ThuongHieuEntity cl = new ThuongHieuEntity();
                cl.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
                cl.setMoTa(rs.getString("mo_ta"));
                ls.add(cl);
                String thuongHieu = rs.getString("ten_thuong_hieu");
                txt_addThuongHieu.addItem(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
private void thuocTinhXuatXu(){
    ArrayList<XuatXuEntity> ls = new ArrayList<>();
        String sql ="""
                    select quoc_gia, mo_ta
                    from xuatxu
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            txt_addXuatXu.removeAllItems();
            while(rs.next()){
                XuatXuEntity xx = new XuatXuEntity();
                xx.setQuocGia(rs.getString("quoc_gia"));
                xx.setMota(rs.getString("mo_ta"));
                ls.add(xx);
                String quocGia = rs.getString("quoc_gia");
                txt_addXuatXu.addItem(quocGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
private void thuocTinhMauSac(){
     ArrayList<MauSacEntity> ls = new ArrayList<>();
        String sql = """
                     select mau_sac_san_pham, mo_ta
                     from mauSac 
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            txt_addMau.removeAllItems();
            while (rs.next()) {
                MauSacEntity ms = new MauSacEntity();
                ms.setMauSac(rs.getString("mau_sac_san_pham"));
                ms.setMoTa(rs.getString("mo_ta"));
                ls.add(ms);
                String mauSac = rs.getString("mau_sac_san_pham");
                txt_addMau.addItem(mauSac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    private void hienThiThongTinSanPham(ArrayList<ThongTinSanPham> lsss) {
        md = (DefaultTableModel) tbl_thongTinSP.getModel();
        md.setRowCount(0);
        for (ThongTinSanPham lss : lsss) {
            md.addRow(new Object[]{
                lss.getMaSanPham(), lss.getTenSanPham(), lss.getGiaBan(), lss.getGiaNhap(), lss.getSoLuong(), lss.getHinhAnh(), lss.getTrangThai()
            });
        }
    }

    private void hienThiDuLieu(ArrayList<SanPhamEntity> lss) {
        md = (DefaultTableModel) tbl_sanphamchitiet.getModel();
        md.setRowCount(0);
        for (SanPhamEntity l : lss) {
            md.addRow(new Object[]{
              l.getIdSanPham(),  l.getMaSanPham(), l.getTenSanPham(), l.getSoLuong(), l.getGiaBan(),l.getGiaNhap(), l.getHinhAnh(), l.getKichCo(), l.getMauSac(), l.getTenThuongHieu(), l.getChatLieu(), l.getQuocGia(), l.getTenMaLoai(), l.getTrangThai()
            });
        }
    }

    private void hienThiDuLieuThongTinSanPhamLenTextFile(int index) {
        ThongTinSanPham sp = ls.getAll().get(index);

        txt_ttspMaSanPham.setText(sp.getMaSanPham());
        txt_ttspTenSanPham.setText(sp.getTenSanPham());
        txt_ttspGiaban.setText(String.valueOf(sp.getGiaBan()));
        txt_ttspSoLuongTon.setText(String.valueOf(sp.getSoLuong()));
        txt_ttspTrangThai.setSelectedItem((String) sp.getTrangThai());
        String hinhAnhPath = sp.getHinhAnh();
        if (hinhAnhPath != null && !hinhAnhPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(hinhAnhPath);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(txthinhanh.getWidth(), txthinhanh.getHeight(), Image.SCALE_SMOOTH);
            txthinhanh.setIcon(new ImageIcon(scaledImg));
        } else {
            txthinhanh.setIcon(null);
        }
    }

    private AddSanPhamEntity getFomat() {
        String maSanPham = txt_addMaSanPham.getText().trim();
        String tenSanPham = txt_addTenSanPham.getText().trim();
        float giaBan = Float.parseFloat(txt_addGiaBan.getText().trim());
        float giaNhap = Float.parseFloat(txt_addGiaNhap.getText().trim());
        int soLuong = Integer.parseInt(txt_addSoLuong.getText().trim());
        int maLoai = getMaFromTen(txt_addDanhMuc.getSelectedItem().toString());
        int Mau = getMaFromTenchatlieu(txt_addChatLieu.getSelectedItem().toString());
        int kichCo = getMaFromTenKichCo(txt_addSize.getSelectedItem().toString());
        int XuatXu = getMaFrommauSac(txt_addMau.getSelectedItem().toString());
        int ChatLieu = getMaFromThuongHieu(txt_addThuongHieu.getSelectedItem().toString());
        int ThuongHieu = getMaFromXuatXu(txt_addXuatXu.getSelectedItem().toString());
        String hinhAnh = hiddenPath;
        String qrCodePath = "D:\\anh\\" + maSanPham + "_qr.png";
        return new AddSanPhamEntity(maSanPham, tenSanPham, giaBan, giaNhap, soLuong, hinhAnh, maLoai, kichCo, Mau, ThuongHieu, ChatLieu, XuatXu);
    }

    private void xoaDuLienTrenO() {
        txt_addMaSanPham.setText("");
        txt_addTenSanPham.setText("");
        txt_addDanhMuc.setSelectedItem("    ");
        txt_addGiaBan.setText("");
        txt_addSoLuong.setText("");
        txt_addHinhanh.setText("");
        txt_addSize.setSelectedItem("    ");
        txt_addChatLieu.setSelectedItem("    ");
        txt_addXuatXu.setSelectedItem("    ");
        txt_addMau.setSelectedItem("    ");
        txt_addThuongHieu.setSelectedItem("     ");
        txt_addHinhanh.setIcon(null);
    }

    private void add() {
        ls.addSanPhammm(getFomat());
        hienThiDuLieu(ls.getSanPhamChhiTiet());
    }
//private String createQRCode(SanPhamEntity sc) {
//    try {
//        // Ghép thông tin sản phẩm thành một chuỗi
//        String productInfo = "Ma San Pham: " + sc.getMaSanPham() + "\n"
//                + "Ten San Pham: " + sc.getTenSanPham() + "\n"
//                + "Gia Ban: " + sc.getGiaBan() + "\n"
//                + "So Luong: " + sc.getSoLuong();
//        String filePath = "D:\\anh\\" + sc.getMaSanPham() + "duyem.png";
//        String encodedInfo = new String(productInfo.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(encodedInfo, BarcodeFormat.QR_CODE, 200, 200);
//        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
//        return filePath; 
//    } catch (WriterException | IOException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi tạo mã QR: " + e.getMessage());
//        return null;
//    }
//}


    private void hienThiDuLieuLenText(int index) {
        SanPhamEntity sc = ls.getSanPhamChhiTiet().get(index);
        txt_addMaSanPham.setText(sc.getMaSanPham());
        txt_addTenSanPham.setText(sc.getTenSanPham());
        txt_addGiaBan.setText(String.valueOf(sc.getGiaBan()));
        txt_addSoLuong.setText(String.valueOf(sc.getSoLuong()));
        txt_addDanhMuc.setSelectedItem(sc.getTenMaLoai());
        txt_addChatLieu.setSelectedItem(sc.getChatLieu());
        txt_addSize.setSelectedItem(sc.getKichCo());
        txt_addMau.setSelectedItem(sc.getMauSac());
        txt_addThuongHieu.setSelectedItem(sc.getTenThuongHieu());
        txt_addXuatXu.setSelectedItem(sc.getQuocGia());
        String hinhAnhPath = sc.getHinhAnh();
        if (hinhAnhPath != null && !hinhAnhPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(hinhAnhPath);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(txt_addHinhanh.getWidth(), txt_addHinhanh.getHeight(), Image.SCALE_SMOOTH);
            txt_addHinhanh.setIcon(new ImageIcon(scaledImg));
        } else {
            txt_addHinhanh.setIcon(null);
        }
 
    }

    private int getMaFromTen(String ten) {
        switch (ten) {
            case "Áo":
                return 1;
            case "Quần":
                return 2;
            
            default:
                throw new IllegalArgumentException("Tên không hợp lệ: " + ten);
        }
    }

    private int getMaFromTenchatlieu(String ten) {
        switch (ten) {
            case "Cotton":
                return 1;
            case "Polyester":
                return 2;
            case "Lụa":
                return 3;
            case "Jean":
                return 4;
            default:
                throw new IllegalArgumentException("Tên không hợp lệ: " + ten);
        }
    }

    private int getMaFromTenKichCo(String ten) {
        switch (ten) {
            case "S":
                return 1;
            case "M":
                return 2;
            case "L":
                return 3;
            case "XL":
                return 4;
           
            default:
                throw new IllegalArgumentException("Tên không hợp lệ: " + ten);
        }
    }

    private int getMaFrommauSac(String ten) {
        switch (ten) {
            case "Đỏ":
                return 1;
            case "Xanh":
                return 2;
            case "Vàng":
                return 3;
            case "Đen":
                return 4;
            default:
                throw new IllegalArgumentException("Tên không hợp lệ: " + ten);
        }
    }

    private int getMaFromThuongHieu(String ten) {
        switch (ten) {
            case "Nike":
                return 1;
            case "Adidas":
                return 2;
            case "Puma":
                return 3;
            case "Zara":
                return 4;
            
            default:
                throw new IllegalArgumentException("Tên không hợp lệ: " + ten);
        }
    }

    private int getMaFromXuatXu(String ten) {
        switch (ten) {
            case "Trung Quốc":
                return 1;
            case "Việt Nam":
                return 2;
            case "Mỹ":
                return 3;
            case "Hàn Quốc":
                return 4;
            default:
                throw new IllegalArgumentException("Tên không hợp lệ: " + ten);
        }
    }
private void addHinhAnh() {
    JFileChooser fileChooser = new JFileChooser("D:\\asm1webbbb\\img");
    fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));
    
    int userSelection = fileChooser.showOpenDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToOpen = fileChooser.getSelectedFile();
        hiddenPath = fileToOpen.getAbsolutePath(); // Lưu đường dẫn đầy đủ mà không hiển thị

        // Kiểm tra xem file có tồn tại không
        if (!fileToOpen.exists()) {
            System.out.println("File không tồn tại!");
            return;
        }

        try {
            // Tạo một ImageIcon từ đường dẫn hình ảnh
            ImageIcon icon = new ImageIcon(hiddenPath);
            Image img = icon.getImage();
            
            // Lấy kích thước của JLabel để thay đổi kích thước hình ảnh cho phù hợp
            int labelWidth = txt_addHinhanh.getWidth();
            int labelHeight = txt_addHinhanh.getHeight();
            double aspectRatio = (double) img.getWidth(null) / (double) img.getHeight(null);

            // Điều chỉnh kích thước hình ảnh sao cho không bị biến dạng
            if (labelWidth / (double) labelHeight > aspectRatio) {
                labelWidth = (int) (labelHeight * aspectRatio);
            } else {
                labelHeight = (int) (labelWidth / aspectRatio);
            }

            // Thay đổi kích thước hình ảnh và hiển thị trong JLabel
            Image newImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
            txt_addHinhanh.setIcon(new ImageIcon(newImg)); // Đặt hình ảnh vào JLabel
            txt_addHinhanh.setHorizontalAlignment(SwingConstants.CENTER);
            txt_addHinhanh.setVerticalAlignment(SwingConstants.CENTER);

            // Cập nhật lại giao diện
            txt_addHinhanh.revalidate();
            txt_addHinhanh.repaint();
        } catch (Exception e) {
            System.err.println("Lỗi khi xử lý hình ảnh:");
            e.printStackTrace();
        }
    } else {
        System.out.println("Không chọn file.");
    }
}


//private void addHinhAnh() {
//    JFileChooser fileChooser = new JFileChooser("D:\\asm1webbbb\\img");
//    fileChooser.setDialogTitle("Chọn hình ảnh");
//    fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));
//    
//    int userSelection = fileChooser.showOpenDialog(this);
//    if (userSelection == JFileChooser.APPROVE_OPTION) {
//        File fileToOpen = fileChooser.getSelectedFile();
//        hiddenPath = fileToOpen.getAbsolutePath(); // Lưu đường dẫn đầy đủ
//        String fileName = fileToOpen.getName();    // Lấy tên file (không bao gồm đường dẫn)
//        
//        txt_addHinhanh.setText(fileName);          // Hiển thị tên file trong JTextField
//        
//        try {
//            ImageIcon icon = new ImageIcon(hiddenPath);
//            Image img = icon.getImage();
//            int labelWidth = txt_addHinhanh.getWidth();
//            int labelHeight = txt_addHinhanh.getHeight();
//            double aspectRatio = (double) img.getWidth(null) / (double) img.getHeight(null);
//
//            if (labelWidth / (double) labelHeight > aspectRatio) {
//                labelWidth = (int) (labelHeight * aspectRatio);
//            } else {
//                labelHeight = (int) (labelWidth / aspectRatio);
//            }
//
//            Image newImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
//            txt_addHinhanh.setIcon(new ImageIcon(newImg)); // Đặt hình ảnh vào JLabel
//            txt_addHinhanh.setHorizontalAlignment(SwingConstants.CENTER);
//            txt_addHinhanh.setVerticalAlignment(SwingConstants.CENTER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    } else {
//        System.out.println("Không chọn file.");
//    }
//}
//    private void addHinhAnh() {
//        JFileChooser fileChooser = new JFileChooser("D:\\asm1webbbb\\img");
//         fileChooser.setDialogTitle("Chọn hình ảnh");
////        fileChooser.setDialogTitle("D:\\asm1webbbb\\img");
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));
//        int userSelection = fileChooser.showOpenDialog(this);
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToOpen = fileChooser.getSelectedFile();
//            String hinhAnhPath = fileToOpen.getAbsolutePath();
//            txt_addHinhanh.setText(hinhAnhPath);
//            try {
//                ImageIcon icon = new ImageIcon(hinhAnhPath);
//                Image img = icon.getImage();
//                int labelWidth = txt_addHinhanh.getWidth();
//                int labelHeight = txt_addHinhanh.getHeight();
//                double aspectRatio = (double) img.getWidth(null) / (double) img.getHeight(null);
//
//                if (labelWidth / (double) labelHeight > aspectRatio) {
//                    labelWidth = (int) (labelHeight * aspectRatio);
//                } else {
//                    labelHeight = (int) (labelWidth / aspectRatio);
//                }
//                Image newImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
//                txt_addHinhanh.setIcon(new ImageIcon(newImg)); // Đặt hình ảnh vào JLabel
//                txt_addHinhanh.setHorizontalAlignment(SwingConstants.CENTER);
//                txt_addHinhanh.setVerticalAlignment(SwingConstants.CENTER);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Không chọn file.");
//        }
//    }




    private void update() {
        AddSanPhamEntity Capnhap = getFomat();
        if (Capnhap == null || Capnhap.getMaSanPham() == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ hoặc mã sản phẩm bị thiếu.");
            return;
        }
        boolean KetQua = ls.update(Capnhap.getMaSanPham(), Capnhap);
        if (KetQua) {
            JOptionPane.showMessageDialog(this, "Cập Nhập Thành Công");
            hienThiDuLieu(ls.getSanPhamChhiTiet());
        } else {
            JOptionPane.showMessageDialog(this, "Cập Nhập Thất Bại");
        }
    }

    private void delete() {
        String ma = txt_addMaSanPham.getText();
        boolean KetQua = ls.delete(ma);
        if (KetQua) {
            JOptionPane.showMessageDialog(this, "Xóa Thành Công");
            hienThiDuLieu(ls.getSanPhamChhiTiet());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Thất bại");
        }
    }

    private void timKiem() {
    String thuonghieu = (String)cbo_LocThuongHieuduyem.getSelectedItem();
    String xuatXu = (String) cbo_LocXuatXu2.getSelectedItem();
    String trangThai = (String) timkiemtrangthia.getSelectedItem();
    String tenSanPham = txt_TimKiemSP.getText();
    ArrayList<SanPhamEntity> ketQua = ls.timkiemSanPham(thuonghieu, xuatXu, trangThai, tenSanPham);
        hienThiDuLieu(ketQua);
}
private void timkiemThongTinSanPham() {
        // Chuyển đổi giá từ input
        float giaMax = Float.parseFloat(txtGiamax.getText());
        float giaMin = Float.parseFloat(txtGiaMin.getText());
        String trangThai = (String)cbo_LocTrangThai.getSelectedItem();
        String tenSanPham = txt_TimKiemThongTinSanPham.getText();
        ArrayList<ThongTinSanPham> KetQua = ls.timkiemThongtinSanPham(giaMin, giaMax, trangThai, tenSanPham);
        hienThiThongTinSanPham(KetQua);
   
}
//private void thuocTinhLoaiSanPham(){
//        ArrayList<loaiSanPhamEntity> ls = new ArrayList<>();
//        String sql = """
//                     select * from LoaiSanPham
//                     """;
//        try {
//            Connection con = ketnoi.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//             txtLoaiSanPham.removeAllItems();
//            while (rs.next()) {
//                loaiSanPhamEntity sp = new loaiSanPhamEntity();
//                sp.setTenLoaiSanPham(rs.getString("ten_loai_san_pham"));
//                sp.setMota(rs.getString("mo_ta"));
//                ls.add(sp);
//                  String tenLoai = rs.getString("ten_loai_san_pham");
//            txtLoaiSanPham.addItem(tenLoai);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("hiện thị dữ liệu thất bại");
//        }
//    
//
//}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_SanPham = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_ttspTenSanPham = new javax.swing.JTextField();
        txt_ttspTrangThai = new javax.swing.JComboBox<>();
        txt_ttspSoLuongTon = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_ttspGiaban = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ttspMaSanPham = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        txthinhanh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtGiamax = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGiaMin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbo_LocTrangThai = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_TimKiemThongTinSanPham = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongTinSP = new javax.swing.JTable();
        jPanel_SanPhamChiTiet = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_addSoLuong = new javax.swing.JTextField();
        txt_addGiaBan = new javax.swing.JTextField();
        txt_addTenSanPham = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        txt_addHinhanh = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Sua1 = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_addXuatXu = new javax.swing.JComboBox<>();
        txt_addChatLieu = new javax.swing.JComboBox<>();
        txt_addSize = new javax.swing.JComboBox<>();
        txt_addMau = new javax.swing.JComboBox<>();
        txt_addThuongHieu = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txt_addDanhMuc = new javax.swing.JComboBox<>();
        txt_addMaSanPham = new javax.swing.JLabel();
        txt_addGiaNhap = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btn_XuatFile = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cbo_LocThuongHieuduyem = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txt_TimKiemSP = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cbo_LocXuatXu2 = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        timkiemtrangthia = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanphamchitiet = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tenLoaiSanPham = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        thuocTinhLoaiSanPham = new javax.swing.JTable();
        moTaSanPham = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        idLoaiSanPham = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        maLoaiSanPham = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        moTaMauSacSanPham = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        tenMauSacSanPham = new javax.swing.JTextField();
        idMaMauSacSanPham = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        thuocTinhMauSacSanPham = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        maMauSacSanPham = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        moTaSanPham1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        tenLoaiSanPham1 = new javax.swing.JTextField();
        idXuatXu = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        thuocTinhXuatXu = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        maLoaiSanPham1 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        moTakichCo = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        tenLoaiSanPham2 = new javax.swing.JTextField();
        idMaKichCo = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        thuocTinnhKichCo = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        maKichCow = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        moTaChatLieu = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        tenLoaiSanPham3 = new javax.swing.JTextField();
        idChatLieu = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        thuocTinhChatLieu = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        maChatLieu = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        moTaThuongHieu = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        tenLoaiSanPham4 = new javax.swing.JTextField();
        idThuongHieu = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        thuocTinhThuongHieu = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        maThuongHieu = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_SanPham.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_SanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm:");

        txt_ttspTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                              ", "Còn hàng", "Hết hàng" }));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Số Lượng:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Giá Bán:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("TrangThai:");

        txt_ttspMaSanPham.setText("jLabel5");

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txthinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txthinhanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel26)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ttspGiaban, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(txt_ttspSoLuongTon)
                    .addComponent(txt_ttspMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ttspTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ttspTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_ttspMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txt_ttspGiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_ttspTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ttspSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel4)
                    .addComponent(txt_ttspTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel_SanPham.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtGiamax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamaxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Giá Bán Max:");

        txtGiaMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaMinActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Giá Bán Min:");

        cbo_LocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "              ", "Còn hàng", "Hết hàng" }));
        cbo_LocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LocTrangThaiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Trang Thái:");

        txt_TimKiemThongTinSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemThongTinSanPhamActionPerformed(evt);
            }
        });
        txt_TimKiemThongTinSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TimKiemThongTinSanPhamKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tìm Kiếm Sản Phẩm:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txtGiamax, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(cbo_LocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(txt_TimKiemThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(184, 184, 184)
                .addComponent(jLabel7)
                .addGap(183, 183, 183)
                .addComponent(jLabel8)
                .addGap(182, 182, 182)
                .addComponent(jLabel9)
                .addGap(105, 105, 105))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiamax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_LocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiemThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel_SanPham.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1060, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_thongTinSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Giá Nhập", "Số Lượng Tồn", "Hình Ảnh", "Trạng  Thái"
            }
        ));
        tbl_thongTinSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_thongTinSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_thongTinSP);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
        );

        jPanel_SanPham.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 1066, 230));

        jTabbedPane1.addTab("Sản Phẩm", jPanel_SanPham);

        jPanel_SanPhamChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_SanPhamChiTiet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Danh Muc:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tên Sản Phẩm:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Giá Bán:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Số Lượng:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Mã Sản Phẩm:");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_addHinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_addHinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        btn_Them.setBackground(new java.awt.Color(255, 204, 0));
        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(255, 204, 0));
        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Sua1.setBackground(new java.awt.Color(255, 204, 0));
        btn_Sua1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Sua1.setText("Xóa");
        btn_Sua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua1ActionPerformed(evt);
            }
        });

        btn_LamMoi.setBackground(new java.awt.Color(255, 204, 0));
        btn_LamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Sua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Them)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btn_Sua)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua1)
                .addGap(18, 18, 18)
                .addComponent(btn_LamMoi)
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Chất Liệu:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Size:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Màu :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Thương Hiệu:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gallery.png"))); // NOI18N
        jButton1.setText("Thêm Hình");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_addXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "             ", "Trung Quốc", "Việt Nam", "Mỹ", "Hàn Quốc" }));

        txt_addChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                ", "Cotton", "Polyester", "Lụa", "Jean" }));

        txt_addSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "           ", "S", "M", "L", "XL" }));

        txt_addMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "             ", "Đỏ", "Xanh", "Vàng", "Đen" }));

        txt_addThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "             ", "Nike", "Adidas", "Puma", "Zara" }));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Xuất Xứa");

        txt_addDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "             ", "Áo", "Quần" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Giá Nhập");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr.png"))); // NOI18N
        jButton3.setText("Tạo QR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_XuatFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_XuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel-file.png"))); // NOI18N
        btn_XuatFile.setText("Xuất Flie");
        btn_XuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel21)
                .addGap(34, 34, 34)
                .addComponent(txt_addMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btn_XuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel19)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_addGiaNhap)
                    .addComponent(txt_addGiaBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_addTenSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_addSoLuong)
                    .addComponent(txt_addDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel17)
                    .addComponent(jLabel12)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_addThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_addMau, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_addSize, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_addChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_addXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txt_addDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txt_addTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_addSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txt_addGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txt_addGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txt_addChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_addSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_addMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_addThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_addXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_addMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_XuatFile)
                            .addComponent(jButton1)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel_SanPhamChiTiet.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 6, 1100, 310));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Thương Hiệu");

        cbo_LocThuongHieuduyem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "            ", "Adidas", "Puma", "Nike", "Reebok", "Under Armour" }));
        cbo_LocThuongHieuduyem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LocThuongHieuduyemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel30))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbo_LocThuongHieuduyem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_LocThuongHieuduyem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Tìm Kiếm Sản Phẩm:");

        txt_TimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TimKiemSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(txt_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("Xuất Xứ");

        cbo_LocXuatXu2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "               ", "Việt Nam", "Trung Quốc", "Hàn Quốc", "Nhật Bản", "Mỹ" }));
        cbo_LocXuatXu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LocXuatXu2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel32))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(cbo_LocXuatXu2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_LocXuatXu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        timkiemtrangthia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "           ", "Còn hàng", "Hết hàng" }));
        timkiemtrangthia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timkiemtrangthiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(timkiemtrangthia, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(timkiemtrangthia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel_SanPhamChiTiet.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 1070, 110));

        tbl_sanphamchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Tồn", "Giá Bán", "Hình Ảnh", "Kích Cỡ", "Màu Sắc", "Tên Thương Hiệu", "Chất Liệu", "Quốc Gia", "Danh Muc", "Trạng Thái", "Giá Nhập", "Title 14"
            }
        ));
        tbl_sanphamchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanphamchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanphamchitiet);

        jPanel_SanPhamChiTiet.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 1060, 120));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/filter.png"))); // NOI18N
        jLabel3.setText("LỌC");
        jPanel_SanPhamChiTiet.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 70, 30));

        jTabbedPane1.addTab("Sản Phẩm Chi Tiết", jPanel_SanPhamChiTiet);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        thuocTinhLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Loại Sản Phẩm", "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinhLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinhLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(thuocTinhLoaiSanPham);

        jLabel5.setText("Tên Loại:");

        jLabel13.setText("Mô Tả:");

        jButton2.setText("Thêm ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Sửa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Làm Mới");

        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel14.setText("ID Mã Loại Sản Phẩm:");

        jLabel22.setText("Mã Loại San Phẩm:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(idLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(maLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(tenLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(moTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(24, 24, 24)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel22)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tenLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14)
                                    .addComponent(idLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(maLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))))
        );

        jTabbedPane2.addTab("Tên Loại Sản Phẩm", jPanel5);

        jLabel24.setText("Màu Sắc:");

        jLabel27.setText("Mô Tả:");

        jButton7.setText("Thêm ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Sửa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Làm Mới");

        jButton10.setText("Xóa");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel28.setText("ID Mã Màu Sắc Sản Phẩm");

        thuocTinhMauSacSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Loại Sản Phẩm", "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinhMauSacSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinhMauSacSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(thuocTinhMauSacSanPham);

        jLabel29.setText("Mã Màu Sắc Sản Phẩm:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(550, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(maMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(256, 256, 256))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(483, 483, 483))))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jButton7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton8)
                            .addGap(24, 24, 24)
                            .addComponent(jButton10)
                            .addGap(18, 18, 18)
                            .addComponent(jButton9))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(idMaMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(240, 240, 240)
                                        .addComponent(jLabel29)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(tenMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(moTaMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(568, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jLabel29)
                            .addGap(28, 28, 28)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7)
                                .addComponent(jButton8)
                                .addComponent(jButton9)
                                .addComponent(jButton10)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moTaMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tenMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel28)
                                .addComponent(idMaMauSacSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        jTabbedPane2.addTab("Màu Sắc Sản Phẩm", jPanel7);

        jLabel33.setText("Tên Xuất Xứ:");

        jLabel34.setText("Mô Tả:");

        jButton11.setText("Thêm ");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Sửa");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Làm Mới");

        jButton14.setText("Xóa");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel35.setText("Id Xuất Xứa");

        thuocTinhXuatXu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Loại Sản Phẩm", "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinhXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinhXuatXuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(thuocTinhXuatXu);

        jLabel36.setText("Mã Xuất Xứ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(idXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addComponent(maLoaiSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jButton11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton12)
                            .addGap(24, 24, 24)
                            .addComponent(jButton14)
                            .addGap(18, 18, 18)
                            .addComponent(jButton13))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tenLoaiSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moTaSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maLoaiSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel36)))
                .addContainerGap(565, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton11)
                                .addComponent(jButton12)
                                .addComponent(jButton13)
                                .addComponent(jButton14)))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moTaSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tenLoaiSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)
                                .addComponent(jLabel34))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel35)))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        jTabbedPane2.addTab("Xuất Xứ Sản Phẩm", jPanel17);

        jLabel37.setText("Kích Cỡ");

        jLabel38.setText("Mô Tả:");

        jButton15.setText("Thêm ");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Sửa");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Làm Mới");

        jButton18.setText("Xóa");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel39.setText("ID Mã Kích Cỡ:");

        thuocTinnhKichCo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Loại Sản Phẩm", "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinnhKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinnhKichCoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(thuocTinnhKichCo);

        jLabel40.setText("Mã Kích Cỡ");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(idMaKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(827, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jButton15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton16)
                            .addGap(24, 24, 24)
                            .addComponent(jButton18)
                            .addGap(18, 18, 18)
                            .addComponent(jButton17)
                            .addContainerGap(574, Short.MAX_VALUE))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel39)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                            .addGap(389, 389, 389)
                                            .addComponent(jLabel40)
                                            .addGap(18, 18, 18)
                                            .addComponent(maKichCow, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                            .addComponent(tenLoaiSanPham2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(moTakichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(idMaKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(571, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jLabel40)
                            .addGap(28, 28, 28)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton15)
                                .addComponent(jButton16)
                                .addComponent(jButton17)
                                .addComponent(jButton18)))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moTakichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tenLoaiSanPham2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37)
                                .addComponent(jLabel38))
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel39))
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(maKichCow, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        jTabbedPane2.addTab("Kích Cỡ Sản Phẩm", jPanel19);

        jLabel41.setText("Chất Liệu:");

        jLabel42.setText("Mô Tả:");

        jButton19.setText("Thêm ");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Sửa");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Làm Mới");

        jButton22.setText("Xóa");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel43.setText("ID Chất Liệu");

        thuocTinhChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Loại Sản Phẩm", "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinhChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinhChatLieuMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(thuocTinhChatLieu);

        jLabel44.setText("Mã Chất Liệu");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(idChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jButton19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton20)
                            .addGap(24, 24, 24)
                            .addComponent(jButton22)
                            .addGap(18, 18, 18)
                            .addComponent(jButton21))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addComponent(tenLoaiSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(moTaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(idChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(572, Short.MAX_VALUE))
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton19)
                                .addComponent(jButton20)
                                .addComponent(jButton21)
                                .addComponent(jButton22)))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moTaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tenLoaiSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel41)
                                .addComponent(jLabel42))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel43)))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        jTabbedPane2.addTab("Chất Liệu Sản Phẩm", jPanel20);

        jLabel45.setText("Thương Hiệu:");

        jLabel46.setText("Mô Tả:");

        jButton23.setText("Thêm ");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText("Sửa");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setText("Làm Mới");

        jButton26.setText("Xóa");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel47.setText("ID Thương Hiệu");

        thuocTinhThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Loại Sản Phẩm", "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinhThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinhThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(thuocTinhThuongHieu);

        jLabel48.setText("Mã Thương Hiệu");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(545, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(298, 298, 298))
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(idThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(tenLoaiSanPham4, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moTaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jButton23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton24)
                            .addGap(24, 24, 24)
                            .addComponent(jButton26)
                            .addGap(18, 18, 18)
                            .addComponent(jButton25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48)
                    .addComponent(maThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(569, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton23)
                                .addComponent(jButton24)
                                .addComponent(jButton25)
                                .addComponent(jButton26)))
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moTaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tenLoaiSanPham4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel45)
                                .addComponent(jLabel46))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel47)
                                .addComponent(idThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        jTabbedPane2.addTab("Thương Hiệu Sản Phẩm", jPanel21);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thộc Tính San Phẩm", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_thongTinSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thongTinSPMouseClicked
        // TODO add your handling code here:
        int index = tbl_thongTinSP.getSelectedRow();
        hienThiDuLieuThongTinSanPhamLenTextFile(index);
    }//GEN-LAST:event_tbl_thongTinSPMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here: // Lấy vai trò người dùng
    
        
        
         String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
        String vaiTro = LuuThongTinDangNhap.getVaiTro();
        if ("Quản trị viên".equals(vaiTro)) {
             String tenSanPham = txt_addTenSanPham.getText().trim();
    if (tenSanPham.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
        return;
    }
    String sql = "SELECT COUNT(*) AS count FROM SanPham WHERE ten_san_pham = ?";
    try (Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, tenSanPham);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt("count") > 0) {
            // Nếu sản phẩm đã tồn tại
            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
        } else {
            ls.addSanPhammm(getFomat());
                   hienThiDuLieu(ls.getSanPhamChhiTiet());
           
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra tên sản phẩm");
    }
        } else {
            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
        }
        
        


    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        xoaDuLienTrenO();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void tbl_sanphamchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanphamchitietMouseClicked
        // TODO add your handling code here:
        int index = tbl_sanphamchitiet.getSelectedRow();
        hienThiDuLieuLenText(index);
    }//GEN-LAST:event_tbl_sanphamchitietMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addHinhAnh();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_XuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatFileActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("D:\\anh");
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Sản Phẩm");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Tên Sản Phẩm");
                headerRow.createCell(1).setCellValue("Giá Bán");
                headerRow.createCell(2).setCellValue("Số Lượng Tồn");
                headerRow.createCell(3).setCellValue("Hình Ảnh");
                headerRow.createCell(4).setCellValue("Tên Loại");
                headerRow.createCell(5).setCellValue("kích Cỡ sản Phẩm");
                headerRow.createCell(6).setCellValue("Màu Sắc Sản Phẩm");
                headerRow.createCell(7).setCellValue("Tên Thương Hiệu");
                headerRow.createCell(8).setCellValue("Chất Liệu");
                headerRow.createCell(9).setCellValue("Xuất Xứ");
                headerRow.createCell(10).setCellValue("Mã Sản Phẩm");
                for (int i = 0; i < md.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < md.getColumnCount(); j++) {
                        Object cellValue = md.getValueAt(i, j);
                        row.createCell(j).setCellValue(cellValue != null ? cellValue.toString() : "");
                    }
                }
                try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
                    workbook.write(outputStream);
                    JOptionPane.showMessageDialog(this, "Xuất Excel thành công!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_btn_XuatFileActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
        String vaiTro = LuuThongTinDangNhap.getVaiTro();
        if ("Quản trị viên".equals(vaiTro)) {
            update();
        } else {
            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
        }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_Sua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua1ActionPerformed
        // TODO add your handling code here:
        String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
        String vaiTro = LuuThongTinDangNhap.getVaiTro();
        if ("Quản trị viên".equals(vaiTro)) {
            delete();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
        }
    }//GEN-LAST:event_btn_Sua1ActionPerformed

    private void txt_TimKiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemSPKeyReleased
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_txt_TimKiemSPKeyReleased

    private void cbo_LocThuongHieuduyemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocThuongHieuduyemActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_cbo_LocThuongHieuduyemActionPerformed
//private String createQRCode() {
//    try {
//        AddSanPhamEntity maSanPham = getFomat(); // Fetch mã sản phẩm
//        String filePath = "D:\\anh\\" + maSanPham + ".png";
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(maSanPham.getMaSanPham(), BarcodeFormat.QR_CODE, 200, 200);
//        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
//        return filePath;
//    } catch (WriterException | IOException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi tạo mã QR: " + e.getMessage());
//        return null;
//    }
//}
private String createQRCode(SanPhamEntity sanPham) {
    try {
        String maSanPham = sanPham.getMaSanPham(); // Lấy mã sản phẩm
        String filePath = "D:\\anh\\" + maSanPham + ".png";
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(maSanPham, BarcodeFormat.QR_CODE, 200, 200);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
        
        return filePath;
    } catch (WriterException | IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi tạo mã QR: " + e.getMessage());
        return null;
    }
}

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     int selectedIndex = tbl_sanphamchitiet.getSelectedRow();
    if (selectedIndex != -1) {
        SanPhamEntity sanPham = ls.getSanPhamChhiTiet().get(selectedIndex);
        String qrCodePath = createQRCode(sanPham);
        if (qrCodePath != null) {
            JOptionPane.showMessageDialog(null, "QR Code đã được tạo tại: " + qrCodePath);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tạo mã QR. Vui lòng thử lại.");
        }
    } else {
        // Thông báo nếu không có sản phẩm nào được chọn
        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để tạo mã QR.");
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbo_LocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocTrangThaiActionPerformed
        // TODO add your handling code here:
        timkiemThongTinSanPham();
    }//GEN-LAST:event_cbo_LocTrangThaiActionPerformed

    private void txt_TimKiemThongTinSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemThongTinSanPhamKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_TimKiemThongTinSanPhamKeyReleased

    private void cbo_LocXuatXu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocXuatXu2ActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_cbo_LocXuatXu2ActionPerformed

    private void timkiemtrangthiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timkiemtrangthiaActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_timkiemtrangthiaActionPerformed

    private void txt_TimKiemThongTinSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemThongTinSanPhamActionPerformed
        // TODO add your handling code here:
        timkiemThongTinSanPham();
    }//GEN-LAST:event_txt_TimKiemThongTinSanPhamActionPerformed

    private void txtGiaMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaMinActionPerformed
        // TODO add your handling code here:
        timkiemThongTinSanPham();
    }//GEN-LAST:event_txtGiaMinActionPerformed

    private void txtGiamaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamaxActionPerformed
        // TODO add your handling code here:
        timkiemThongTinSanPham();
    }//GEN-LAST:event_txtGiamaxActionPerformed

    private void thuocTinhLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        int index = thuocTinhLoaiSanPham.getSelectedRow();
        hienThiDuLieuLenTextFileThuocTinhSanPhamLoaiSanPham(index);
    }//GEN-LAST:event_thuocTinhLoaiSanPhamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        thuocTinh.addLoaiSanPham(getLoaiSanPham());
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int idLoaiSanPhammm = Integer.parseInt(idLoaiSanPham.getText());
        boolean ketQua = thuocTinh.deleteLoaiSanPham(idLoaiSanPhammm);
        if(ketQua){
            hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        }else{
            JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        loaiSanPhamEntity sp = getLoaiSanPham();
       
        boolean ketQua = thuocTinh.updateLoaiSanPham(sp,Integer.parseInt(idLoaiSanPham.getText()));
        if(ketQua){
            hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        }else{
            JOptionPane.showMessageDialog(btn_LamMoi, "Update sản phẩm thất bại");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void thuocTinhMauSacSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhMauSacSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_thuocTinhMauSacSanPhamMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void thuocTinhXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhXuatXuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_thuocTinhXuatXuMouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void thuocTinnhKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinnhKichCoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_thuocTinnhKichCoMouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void thuocTinhChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhChatLieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_thuocTinhChatLieuMouseClicked

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void thuocTinhThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhThuongHieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_thuocTinhThuongHieuMouseClicked
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Sua1;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_XuatFile;
    private javax.swing.JComboBox<String> cbo_LocThuongHieuduyem;
    private javax.swing.JComboBox<String> cbo_LocTrangThai;
    private javax.swing.JComboBox<String> cbo_LocXuatXu2;
    private javax.swing.JLabel idChatLieu;
    private javax.swing.JLabel idLoaiSanPham;
    private javax.swing.JLabel idMaKichCo;
    private javax.swing.JLabel idMaMauSacSanPham;
    private javax.swing.JLabel idThuongHieu;
    private javax.swing.JLabel idXuatXu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_SanPham;
    private javax.swing.JPanel jPanel_SanPhamChiTiet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel maChatLieu;
    private javax.swing.JLabel maKichCow;
    private javax.swing.JLabel maLoaiSanPham;
    private javax.swing.JLabel maLoaiSanPham1;
    private javax.swing.JLabel maMauSacSanPham;
    private javax.swing.JLabel maThuongHieu;
    private javax.swing.JTextField moTaChatLieu;
    private javax.swing.JTextField moTaMauSacSanPham;
    private javax.swing.JTextField moTaSanPham;
    private javax.swing.JTextField moTaSanPham1;
    private javax.swing.JTextField moTaThuongHieu;
    private javax.swing.JTextField moTakichCo;
    private javax.swing.JTable tbl_sanphamchitiet;
    private javax.swing.JTable tbl_thongTinSP;
    private javax.swing.JTextField tenLoaiSanPham;
    private javax.swing.JTextField tenLoaiSanPham1;
    private javax.swing.JTextField tenLoaiSanPham2;
    private javax.swing.JTextField tenLoaiSanPham3;
    private javax.swing.JTextField tenLoaiSanPham4;
    private javax.swing.JTextField tenMauSacSanPham;
    private javax.swing.JTable thuocTinhChatLieu;
    private javax.swing.JTable thuocTinhLoaiSanPham;
    private javax.swing.JTable thuocTinhMauSacSanPham;
    private javax.swing.JTable thuocTinhThuongHieu;
    private javax.swing.JTable thuocTinhXuatXu;
    private javax.swing.JTable thuocTinnhKichCo;
    private javax.swing.JComboBox<String> timkiemtrangthia;
    private javax.swing.JTextField txtGiaMin;
    private javax.swing.JTextField txtGiamax;
    private javax.swing.JTextField txt_TimKiemSP;
    private javax.swing.JTextField txt_TimKiemThongTinSanPham;
    private javax.swing.JComboBox<String> txt_addChatLieu;
    private javax.swing.JComboBox<String> txt_addDanhMuc;
    private javax.swing.JTextField txt_addGiaBan;
    private javax.swing.JTextField txt_addGiaNhap;
    private javax.swing.JLabel txt_addHinhanh;
    private javax.swing.JLabel txt_addMaSanPham;
    private javax.swing.JComboBox<String> txt_addMau;
    private javax.swing.JComboBox<String> txt_addSize;
    private javax.swing.JTextField txt_addSoLuong;
    private javax.swing.JTextField txt_addTenSanPham;
    private javax.swing.JComboBox<String> txt_addThuongHieu;
    private javax.swing.JComboBox<String> txt_addXuatXu;
    private javax.swing.JTextField txt_ttspGiaban;
    private javax.swing.JLabel txt_ttspMaSanPham;
    private javax.swing.JTextField txt_ttspSoLuongTon;
    private javax.swing.JTextField txt_ttspTenSanPham;
    private javax.swing.JComboBox<String> txt_ttspTrangThai;
    private javax.swing.JLabel txthinhanh;
    // End of variables declaration//GEN-END:variables
}
