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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
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
                kichCoEnTity.getKichCo(), kichCoEnTity.getMoTa()
            });
        }
    }
    private MauSacEntity getFatMauSac(){
        MauSacEntity ms= new MauSacEntity();
        ms.setMauSac(tenMauSacSanPham.getText());
        ms.setMoTa(moTaMauSacSanPham.getText());
        return ms;
    }
    
    private void hienThiThuocTinhSanPham(ArrayList<loaiSanPhamEntity> loaiSanPham){
        md = (DefaultTableModel) thuocTinhLoaiSanPham.getModel();
        md.setRowCount(0);
        for (loaiSanPhamEntity sanPhamEntity : loaiSanPham) {
            md.addRow(new Object[]{
               sanPhamEntity.getTenLoaiSanPham(), sanPhamEntity.getMota()
            });
        }
    }
    private void hienThiThuocTinhChatLieu(ArrayList<ChatLieuEntity> chatLieu){
        md = (DefaultTableModel)thuocTinhChatLieu.getModel();
        md.setRowCount(0);
        for (ChatLieuEntity chatLieuEntity : chatLieu) {
            md.addRow(new Object[]{
                chatLieuEntity.getChatLieu(), chatLieuEntity.getMoTa()
            });
        }
    }
    private void HienThiDuLieuThuongHieu(ArrayList<ThuongHieuEntity>thuongHieu){
        md = (DefaultTableModel)thuocTinhThuongHieu.getModel();
        md.setRowCount(0);
        for (ThuongHieuEntity thuongHieuEntity : thuongHieu) {
            md.addRow(new Object[]{
                thuongHieuEntity.getTenThuongHieu(), thuongHieuEntity.getMoTa()
            });
        }
    }
    private void hienThiThuocTinhXuatXu(ArrayList<XuatXuEntity> xuatXu){
        md = (DefaultTableModel) thuocTinhXuatXu.getModel();
        md.setRowCount(0);
        for (XuatXuEntity xuatXuEntity : xuatXu) {
            md.addRow(new Object[]{
                xuatXuEntity.getQuocGia(), xuatXuEntity.getMota()
            });
        }
    }
    private void hienThiDuLieuMauSac (ArrayList<MauSacEntity> mauSac){
        md = (DefaultTableModel) thuocTinhMauSacSanPham.getModel();
        md.setRowCount(0);
        for (MauSacEntity mauSacEntity : mauSac) {
            md.addRow(new Object[]{
              mauSacEntity.getMauSac(), mauSacEntity.getMoTa()
            });
        }
    }
    
    private void hienThiDuLieuLenTextFileThuocTinhSanPhamLoaiSanPham(int index){
        loaiSanPhamEntity sp = thuocTinh.loaiSanPhaam().get(index);
        tenLoaiSanPham.setText(sp.getTenLoaiSanPham());
        moTaSanPham.setText(sp.getMota());
     
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
private void thuocTinhThuongHieu1(){
    ArrayList<ThuongHieuEntity> ls = new ArrayList<>();
        String sql = """
                     select ten_thuong_hieu, mo_ta
                     from thuongHieu
                     """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while(rs.next()){
                ThuongHieuEntity cl = new ThuongHieuEntity();
                cl.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
                cl.setMoTa(rs.getString("mo_ta"));
                ls.add(cl);
                String thuongHieu = rs.getString("ten_thuong_hieu");
                cbo_LocThuongHieuduyem.addItem(thuongHieu);
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
private void thuocTinhXuatXu1(){
    ArrayList<XuatXuEntity> ls = new ArrayList<>();
        String sql ="""
                    select quoc_gia, mo_ta
                    from xuatxu
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
          
            while(rs.next()){
                XuatXuEntity xx = new XuatXuEntity();
                xx.setQuocGia(rs.getString("quoc_gia"));
                xx.setMota(rs.getString("mo_ta"));
                ls.add(xx);
                String quocGia = rs.getString("quoc_gia");
                cbo_LocXuatXu2.addItem(quocGia);
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
        txt_addGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
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

    // Lấy giá trị từ các JComboBox
    String maLoai = txt_addDanhMuc.getSelectedItem().toString();
    String chatLieu = txt_addChatLieu.getSelectedItem().toString();
    String kichCo = txt_addSize.getSelectedItem().toString();
    String mauSac = txt_addMau.getSelectedItem().toString(); 
    String thuongHieu = txt_addThuongHieu.getSelectedItem().toString();
    String xuatXu = txt_addXuatXu.getSelectedItem().toString();
    String hinhAnh = hiddenPath; 
    String qrCodePath = "D:\\anh\\" + maSanPham + "_qr.png"; 
    return new AddSanPhamEntity(maSanPham, tenSanPham, giaBan, giaNhap, soLuong, hinhAnh, maLoai, kichCo, mauSac, thuongHieu, chatLieu, xuatXu);
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
        txt_addGiaNhap.setText(String.valueOf(sc.getGiaNhap()));
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
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        moTaMauSacSanPham = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        tenMauSacSanPham = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        thuocTinhMauSacSanPham = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        moTaXuatXu = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        tenXuatXU = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        thuocTinhXuatXu = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        moTaKichCo = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        tenKichCo = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        thuocTinnhKichCo = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        moTaChatLieu = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        tenChatLieu = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        thuocTinhChatLieu = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        moTaThuongHieu = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        tenThuongHieu = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        thuocTinhThuongHieu = new javax.swing.JTable();

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_ttspMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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

        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

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
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btn_Them)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btn_Sua)
                .addGap(26, 26, 26)
                .addComponent(btn_LamMoi)
                .addGap(26, 26, 26))
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

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Xuất Xứ:");

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

        cbo_LocThuongHieuduyem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "          " }));
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

        cbo_LocXuatXu2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "         " }));
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
                "ID Sản Phẩm", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Bán", "Giá Nhập", "Hình ảnh", "Kích Thước", "Màu Sắc", "Thương Hiệu", "Chất Liệu", "Quốc Gia", "Loại Sản Phẩm", "Trang Thái"
            }
        ));
        tbl_sanphamchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanphamchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanphamchitiet);

        jPanel_SanPhamChiTiet.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 1060, 190));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/filter.png"))); // NOI18N
        jLabel3.setText("LỌC");
        jPanel_SanPhamChiTiet.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 70, 30));

        jTabbedPane1.addTab("Sản Phẩm Chi Tiết", jPanel_SanPhamChiTiet);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
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

        jButton5.setText("Làm Mới");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tenLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton5)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tenLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13))))
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

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setText("Màu Sắc:");

        jLabel27.setText("Mô Tả:");

        jButton7.setText("Thêm ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("Làm Mới");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        thuocTinhMauSacSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên Loại Sản Phẩm", "Mô tả loại sản phẩm"
            }
        ));
        thuocTinhMauSacSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thuocTinhMauSacSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(thuocTinhMauSacSanPham);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(483, 483, 483))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(158, 158, 158)
                            .addComponent(jButton7)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(161, 597, Short.MAX_VALUE)
                            .addComponent(moTaMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(39, 39, 39)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel24)
                    .addComponent(tenMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jButton9)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addComponent(jButton7))
                        .addComponent(moTaMauSacSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(515, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Màu Sắc Sản Phẩm", jPanel7);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setText("Tên Xuất Xứ:");

        jLabel34.setText("Mô Tả:");

        jButton11.setText("Thêm ");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setText("Làm Mới");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton11)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tenXuatXU, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(moTaXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton11)
                            .addComponent(jButton13)))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moTaXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tenXuatXU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)
                        .addComponent(jLabel34)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        jTabbedPane2.addTab("Xuất Xứ Sản Phẩm", jPanel17);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setText("Kích Cỡ");

        jLabel38.setText("Mô Tả:");

        jButton15.setText("Thêm ");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton17.setText("Làm Mới");

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

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton15)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tenKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(moTaKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15)
                            .addComponent(jButton17)))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moTaKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tenKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37)
                        .addComponent(jLabel38)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        jTabbedPane2.addTab("Kích Cỡ Sản Phẩm", jPanel19);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setText("Chất Liệu:");

        jLabel42.setText("Mô Tả:");

        jButton19.setText("Thêm ");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton21.setText("Làm Mới");

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

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton19)
                        .addGap(30, 30, 30)
                        .addComponent(jButton21))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21)
                            .addComponent(tenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(moTaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19)
                            .addComponent(jButton21)))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moTaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41)
                        .addComponent(jLabel42)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        jTabbedPane2.addTab("Chất Liệu Sản Phẩm", jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setText("Thương Hiệu:");

        jLabel46.setText("Mô Tả:");

        jButton23.setText("Thêm ");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton25.setText("Làm Mới");

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

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton23)
                        .addGap(29, 29, 29)
                        .addComponent(jButton25))
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tenThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(moTaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton23)
                            .addComponent(jButton25)))
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moTaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tenThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45)
                        .addComponent(jLabel46)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(8, 8, 8))
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
//         String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
//        String vaiTro = LuuThongTinDangNhap.getVaiTro();
//        if(txt_addTenSanPham.equals("")||txt_addGiaBan.equals("")||txt_addGiaNhap.equals("")||txt_addDanhMuc.equals("")||txt_addMau.equals("")||txt_addSize.equals("")||txt_addXuatXu.equals("")||txt_addThuongHieu.equals("")||txt_addChatLieu.equals("")){
//             JOptionPane.showMessageDialog(btn_LamMoi, "Nhập Đầy Đủ Thông Tin");
//                    
//                }else{
//                  float giaBan = Float.parseFloat(txt_addGiaBan.getText());
//             float giaNhap = Float.parseFloat(txt_addGiaNhap.getText());
//            try {
//                if(giaBan >=0||giaNhap >=0 ){
//                              if ("Quản trị viên".equals(vaiTro)) {
//             String tenSanPham = txt_addTenSanPham.getText().trim();
//    if (tenSanPham.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
//        return;
//    }
//    String sql = "SELECT COUNT(*) AS count FROM SanPham WHERE ten_san_pham = ?";
//    try (Connection con = ketnoi.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//
//        ps.setString(1, tenSanPham);
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next() && rs.getInt("count") > 0) {
//            // Nếu sản phẩm đã tồn tại
//            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
//        } else {
//            ls.addSanPhammm(getFomat());
//                   hienThiDuLieu(ls.getSanPhamChhiTiet());
//           
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra tên sản phẩm");
//    }
//        } else {
//            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
//        }
//                }else{
//                              JOptionPane.showMessageDialog(btn_LamMoi, "Giá Bán Lớn Hơn 0 AND Giá Nhập Lớn hơn 0");
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Sai Gì Đó HiHI");
//                
//            }
//        }
ls.addSanPhammm(getFomat());
                   hienThiDuLieu(ls.getSanPhamChhiTiet());
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
                headerRow.createCell(5).setCellValue("Kích Cỡ Sản Phẩm");
                headerRow.createCell(6).setCellValue("Màu Sắc Sản Phẩm");
                headerRow.createCell(7).setCellValue("Tên Thương Hiệu");
                headerRow.createCell(8).setCellValue("Chất Liệu");
                headerRow.createCell(9).setCellValue("Xuất Xứ");
                headerRow.createCell(10).setCellValue("Mã Sản Phẩm");
                TableModel model = tbl_sanphamchitiet.getModel();
                System.out.println("Số dòng: " + model.getRowCount());
                System.out.println("Số cột: " + model.getColumnCount());
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object cellValue = model.getValueAt(i, j);
                        System.out.println("Dòng " + (i + 1) + ", Cột " + (j + 1) + ": " + cellValue);
                        row.createCell(j).setCellValue(cellValue != null ? cellValue.toString() : "");
                    }
                }
                try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
                    workbook.write(outputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }
        }


    }//GEN-LAST:event_btn_XuatFileActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
//        String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
//        String vaiTro = LuuThongTinDangNhap.getVaiTro();
//        if(txt_addTenSanPham.equals("")||txt_addGiaBan.equals("")||txt_addGiaNhap.equals("")||txt_addDanhMuc.equals("")||txt_addMau.equals("")||txt_addSize.equals("")||txt_addXuatXu.equals("")||txt_addThuongHieu.equals("")||txt_addChatLieu.equals("")){
//             JOptionPane.showMessageDialog(btn_LamMoi, "Nhập Đầy Đủ Thông Tin");
//                }else{
//                  float giaBan = Float.parseFloat(txt_addGiaBan.getText());
//             float giaNhap = Float.parseFloat(txt_addGiaNhap.getText());
//            try {
//                if(giaBan >=0||giaNhap >=0 ){
//                    if ("Quản trị viên".equals(vaiTro)) {
//            update();
//        } else {
//            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
//        }
//                    
//                }else{
//                    JOptionPane.showMessageDialog(btn_LamMoi, "Giá Bán Lớn Hơn 0 AND Giá Nhập Lớn hơn 0");
//                }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        }
//        
 update();
        
    }//GEN-LAST:event_btn_SuaActionPerformed

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
        String maSanPham = String.valueOf(sanPham.getIdSanPham()); // Lấy mã sản phẩm
        String filePath = "D:\\" + maSanPham + ".png";
        
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
           thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }//GEN-LAST:event_jButton2ActionPerformed
private XuatXuEntity getFomatXuatXu(){
    XuatXuEntity xx = new XuatXuEntity();
    xx.setQuocGia(tenXuatXU.getText());
    xx.setMota(moTaXuatXu.getText());
    return xx;
}
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        thuocTinh.addXuatXu(getFomatXuatXu());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
           thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void thuocTinhXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhXuatXuMouseClicked
        // TODO add your handling code here:
        int index = thuocTinhXuatXu.getSelectedRow();
        HienThiDuLieuLentxtThuongHieu(index);
    }//GEN-LAST:event_thuocTinhXuatXuMouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        thuocTinh.addXuatXu(getFomatXuatXu());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
           thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }//GEN-LAST:event_jButton15ActionPerformed

    private void thuocTinnhKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinnhKichCoMouseClicked
        // TODO add your handling code here:
        int index = thuocTinnhKichCo.getSelectedRow();
        KichCoEnTity kc = thuocTinh.kichCo().get(index);
        tenKichCo.setText(kc.getKichCo());
        moTaKichCo.setText(kc.getMoTa());
    }//GEN-LAST:event_thuocTinnhKichCoMouseClicked
private ChatLieuEntity getFomatChatLieu(){
    ChatLieuEntity cl = new ChatLieuEntity();
    cl.setChatLieu(tenChatLieu.getText());
    cl.setMoTa(moTaChatLieu.getText());
    return cl;
}
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        thuocTinh.addChatLieu(getFomatChatLieu());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
           thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }//GEN-LAST:event_jButton19ActionPerformed

    private void thuocTinhChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhChatLieuMouseClicked
        // TODO add your handling code here:
        int index = thuocTinhChatLieu.getSelectedRow();
        ChatLieuEntity cl = thuocTinh.chatLieu().get(index);
        tenChatLieu.setText(cl.getChatLieu());
        moTaChatLieu.setText(cl.getMoTa());
    }//GEN-LAST:event_thuocTinhChatLieuMouseClicked
private ThuongHieuEntity getFoamTThuongHieu(){
    ThuongHieuEntity th = new ThuongHieuEntity();
    th.setTenThuongHieu(tenThuongHieu.getText());
    th.setMoTa(moTaThuongHieu.getText());
    return th;
}
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        thuocTinh.addThuognHieu(getFoamTThuongHieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
           thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }//GEN-LAST:event_jButton23ActionPerformed

    private void thuocTinhThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhThuongHieuMouseClicked
        // TODO add your handling code here:
        int index = thuocTinhThuongHieu.getSelectedRow();
       ThuongHieuEntity th = thuocTinh.thuogHieu().get(index);
       tenThuongHieu.setText(th.getTenThuongHieu());
       moTaThuongHieu.setText(th.getMoTa());
    }//GEN-LAST:event_thuocTinhThuongHieuMouseClicked

    private void thuocTinhMauSacSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thuocTinhMauSacSanPhamMouseClicked
        // TODO add your handling code here:
        int index = thuocTinhMauSacSanPham.getSelectedRow();
        HienThiDuLieuLentxtMauSac(index);
    }//GEN-LAST:event_thuocTinhMauSacSanPhamMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        thuocTinh.addMauSac(getFatMauSac());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
           thuocTinhLoaiSanPhamm();
        thuocTinhKichCo();
        thuocTinhChatLieu();
        thuocTinhXuatXu();
        thuocTinhXuatXu1();
        thuocTinhMauSac();
        thuocTinhThuongHieu();
        thuocTinhThuongHieu1();
        hienThiThuocTinhSanPham(thuocTinh.loaiSanPhaam());
        hienThiThuocTinhXuatXu(thuocTinh.xuatXu());
        hienThiDuLieuMauSac(thuocTinh.mauSac());
        hienThiThuocTinhKichCo(thuocTinh.kichCo());
        hienThiThuocTinhChatLieu(thuocTinh.chatLieu());
        HienThiDuLieuThuongHieu(thuocTinh.thuogHieu());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed
  private void HienThiDuLieuLentxtMauSac(int index){
      MauSacEntity ms = thuocTinh.mauSac().get(index);
      tenMauSacSanPham.setText(ms.getMauSac());
      moTaMauSacSanPham.setText(ms.getMoTa());
  }
 private void HienThiDuLieuLentxtThuongHieu(int index){
      XuatXuEntity ms = thuocTinh.xuatXu().get(index);
      tenXuatXU.setText(ms.getQuocGia());
      moTaXuatXu.setText(ms.getMota());
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_XuatFile;
    private javax.swing.JComboBox<String> cbo_LocThuongHieuduyem;
    private javax.swing.JComboBox<String> cbo_LocTrangThai;
    private javax.swing.JComboBox<String> cbo_LocXuatXu2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
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
    private javax.swing.JTextField moTaChatLieu;
    private javax.swing.JTextField moTaKichCo;
    private javax.swing.JTextField moTaMauSacSanPham;
    private javax.swing.JTextField moTaSanPham;
    private javax.swing.JTextField moTaThuongHieu;
    private javax.swing.JTextField moTaXuatXu;
    private javax.swing.JTable tbl_sanphamchitiet;
    private javax.swing.JTable tbl_thongTinSP;
    private javax.swing.JTextField tenChatLieu;
    private javax.swing.JTextField tenKichCo;
    private javax.swing.JTextField tenLoaiSanPham;
    private javax.swing.JTextField tenMauSacSanPham;
    private javax.swing.JTextField tenThuongHieu;
    private javax.swing.JTextField tenXuatXU;
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
