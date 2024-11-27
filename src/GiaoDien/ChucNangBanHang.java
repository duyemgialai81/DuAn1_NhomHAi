/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.SanPham.BanHangSanPham;
import Entity.HoaDon.ChiTietHoaDonEntity;
import Entity.DonHang.DonHangEntity;
import Entity.HoaDon.HoaDonEntity;
import Entity.SanPham.SanPhamEntity;
import Entity.HoaDon.ThanhToanEntity;
import Entity.HoaDon.XemHoaDonTao;
import GiaoDien.LuuThongTinDangNhap;
import KetNoiSQL.ketnoi;
import Repository.BanHangRepository;
import Repository.ChiTietHoaDonRepository;
import Repository.HoaDonrepository;
import Repository.SanPhamRepository;
import Repository.ThanhToanRepository;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.itextpdf.io.font.PdfEncodings;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.Cell;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author SingPC
 */
public class ChucNangBanHang extends javax.swing.JPanel {

    private BanHangRepository bh = new BanHangRepository();
    private HoaDonrepository hd = new HoaDonrepository();
    private DefaultTableModel md = new DefaultTableModel();
    private SanPhamRepository sp = new SanPhamRepository();
    private ChiTietHoaDonRepository ct = new ChiTietHoaDonRepository();
    private ThanhToanRepository tt = new ThanhToanRepository();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private double tongTien = 0;

    /**
     * Creates new form ChucNangBanHang
     */
    public ChucNangBanHang() {
        initComponents();
//        hienThiDuLieu(sp.getSanPhamChhiTiet());
//        HienThiDuLiuHoaDon(hd.getAll());
////        int maGioHangHienTai;

//        HienThiDuLieuGioHang(hd.getAllGioHang());
    }

    private void hienThiDuLieu(ArrayList<SanPhamEntity> lss) {
        md = (DefaultTableModel) tbl_sanPham.getModel();
        md.setRowCount(0);
        for (SanPhamEntity l : lss) {
            md.addRow(new Object[]{
                l.getIdSanPham(), l.getMaSanPham(), l.getTenSanPham(), l.getSoLuong(), l.getGiaBan(), l.getHinhAnh(), l.getKichCo(), l.getMauSac(), l.getTenThuongHieu(), l.getChatLieu(), l.getQuocGia(), l.getTenMaLoai(), l.getTrangThai()
            });
        }
    }

    private void HienThiDuLiuHoaDon(ArrayList<HoaDonEntity> lss) {
        md = (DefaultTableModel) tblhoadon1.getModel();
        md.setRowCount(0);
        for (HoaDonEntity ls : lss) {
            md.addRow(new Object[]{
                ls.getIdHoaDon(), ls.getMaHoaDon(), ls.getNgayLap(), ls.getTenNhanVien(), ls.getTenKhachHang()
            });
        }
    }

    private void HienThiDuLieuGioHang(ArrayList<XemHoaDonTao> hoaDon) {
        md = (DefaultTableModel) tbl_duyem.getModel();
        md.setRowCount(0);
        for (XemHoaDonTao sc : hoaDon) {
            md.addRow(new Object[]{
                sc.getMaSanPham(), sc.getTenSanPham(), sc.getSoLuong(), sc.getGiaBan()
            });
        }
    }

    public void thanhToan() {

    }
//public void addProductToInvoice(int invoiceId, int productId, int quantity, double price) {
//        String sql = "INSERT INTO ChiTietHoaDon (ma_hoa_don, ma_san_pham, so_luong, gia_ban) VALUES (?, ?, ?, ?)";
//        
//        try (Connection conn = ketnoi.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, invoiceId);
//            ps.setInt(2, productId);
//            ps.setInt(3, quantity);
//            ps.setDouble(4, price);
//
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Sản phẩm đã được thêm vào chi tiết hóa đơn.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    // Assuming you have a method to populate tbl_phieu

 private void loadCartData(int idMaHoaDon) throws FileNotFoundException, IOException {
    ArrayList<XemHoaDonTao> chiTietList = hd.getAllGioHang(idMaHoaDon);
    DefaultTableModel model = (DefaultTableModel) tbl_duyem.getModel();
    model.setRowCount(0);
    
    // Sử dụng Map để lưu trữ sản phẩm và cộng dồn số lượng nếu trùng tên sản phẩm
    Map<String, XemHoaDonTao> productMap = new HashMap<>();

    for (XemHoaDonTao chiTiet : chiTietList) {
        String tenSanPham = chiTiet.getTenSanPham();
        
        if (productMap.containsKey(tenSanPham)) {
            // Nếu sản phẩm đã có, cộng dồn số lượng và tính lại giá
            XemHoaDonTao existingItem = productMap.get(tenSanPham);
            existingItem.setSoLuong(existingItem.getSoLuong() + chiTiet.getSoLuong());
            existingItem.setGiaBan(existingItem.getGiaBan());  // Giữ giá bán hiện tại
        } else {
            // Nếu sản phẩm chưa có trong map, thêm vào
            productMap.put(tenSanPham, chiTiet);
        }
    }
    
    // Hiển thị sản phẩm lên bảng
    for (XemHoaDonTao chiTiet : productMap.values()) {
        Object[] rowData = {
            chiTiet.getMaSanPham(),
            chiTiet.getTenSanPham(),
            chiTiet.getSoLuong(),
            chiTiet.getGiaBan(),
            chiTiet.getSoLuong() * chiTiet.getGiaBan()  // Tổng giá
        };
        model.addRow(rowData);
    }
}


//private void hienThiDuLieuGioHang(int maHoaDon) {
//    DefaultTableModel model = (DefaultTableModel) tbl_duyem.getModel();  // Giả sử bạn có một JTable hiển thị giỏ hàng
//
//    // Lấy dữ liệu giỏ hàng từ cơ sở dữ liệu
//    ArrayList<XemHoaDonTao> gioHang = hd.getAllGioHang(maHoaDon);
//    
//    model.setRowCount(0);  // Xóa hết dữ liệu cũ trong bảng
//
//    double tongTien = 0;  // Khởi tạo biến tính tổng tiền giỏ hàng
//double thanhTien = 0; // Khởi tạo biến tính thành tiền của từng sản phẩm
//
//// Duyệt qua danh sách giỏ hàng và thêm từng sản phẩm vào bảng
//for (XemHoaDonTao item : gioHang) {
//    thanhTien = item.getGiaBan() * item.getSoLuong();  // Tính thành tiền cho từng sản phẩm
//    model.addRow(new Object[] {
//        item.getMaSanPham(),
//        item.getTenSanPham(),
//        item.getSoLuong(),
//        item.getGiaBan(),
//        thanhTien
//    });
//    tongTien += thanhTien;  // Cộng dồn thành tiền vào tổng tiền
//}
//
//txtthanhtien.setText(String.format("%.2f", tongTien)); // Hiển thị tổng tiền vào JTextField
//txttongSoTien.setText(String.format("%.2f", tongTien)); 
//String voucherCode = txtvoucher.getText();
//double discountValue = 0; 
//String sql = """
//             SELECT gia_tri FROM Voucher WHERE ma_voucher = ?
//             """;
//try {
//    Connection con = ketnoi.getConnection();
//        PreparedStatement stmt = con.prepareStatement(sql) ;
//    stmt.setString(1, voucherCode);
//    ResultSet rs = stmt.executeQuery();
//
//    if (rs.next()) {
//        discountValue = rs.getDouble("gia_tri");  // Lấy giá trị giảm giá
//    } else {
//        JOptionPane.showMessageDialog(null, "Voucher không hợp lệ");
//    }
//} catch (SQLException e) {
//    e.printStackTrace();
//}
//if (discountValue > 0) {
//    if (discountValue <= 1) {
//        // Nếu giảm giá là phần trăm (ví dụ 0.10 cho 10%)
//        tongTien -= tongTien * discountValue;  // Áp dụng giảm giá phần trăm
//    } else {
//        // Nếu giảm giá là giá trị cố định
//        tongTien -= discountValue;  // Áp dụng giảm giá cố định
//    }
//}
//txtthanhtien.setText(String.format("%.2f", tongTien));
//
//}

    private void hienThiDuLieuGioHang(int maHoaDon) {
         ArrayList<XemHoaDonTao> chiTietList = hd.getAllGioHang(maHoaDon);
    DefaultTableModel model = (DefaultTableModel) tbl_duyem.getModel();
    model.setRowCount(0);

    // Sử dụng Map để nhóm các sản phẩm theo tên và cộng dồn số lượng
    Map<String, XemHoaDonTao> productMap = new HashMap<>();
    
    for (XemHoaDonTao chiTiet : chiTietList) {
        String tenSanPham = chiTiet.getTenSanPham();
        
        if (productMap.containsKey(tenSanPham)) {
            // Nếu sản phẩm đã có trong map, cộng dồn số lượng
            XemHoaDonTao existingItem = productMap.get(tenSanPham);
            existingItem.setSoLuong(existingItem.getSoLuong() + chiTiet.getSoLuong());
        } else {
            // Nếu sản phẩm chưa có, thêm vào map
            productMap.put(tenSanPham, chiTiet);
        }
    }
    
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    for (XemHoaDonTao chiTiet : productMap.values()) {
        double thanhTien = chiTiet.getGiaBan() * chiTiet.getSoLuong();
        tongTien += thanhTien;

        Object[] rowData = {
            chiTiet.getMaSanPham(),
            chiTiet.getTenSanPham(),
            chiTiet.getSoLuong(),
            chiTiet.getGiaBan(),
            thanhTien
        };
        model.addRow(rowData);
    }

    // Hiển thị tổng tiền
    txttongSoTien.setText(formatter.format(tongTien));

        // Lấy voucher theo id_ma_hoa_don
        double discountValue = 0;
        String sql = """
                 select Voucher.gia_tri
                 from Voucher 
                 join HoaDon  on .HoaDon.ma_voucher = Voucher.id_voucher
                 where HoaDon.id_ma_hoa_don = ?
                 """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHoaDon);  // Sử dụng mã hóa đơn để tìm voucher
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                discountValue = rs.getDouble("gia_tri");  // Lấy giá trị giảm giá từ voucher
            } else {
                JOptionPane.showMessageDialog(null, "Voucher không hợp lệ cho hóa đơn này");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (discountValue > 0) {
            if (discountValue <= 1) {
                tongTien -= tongTien * discountValue;
            } else {
                tongTien -= discountValue;
            }
        }
        txtGiamGia.setText(formatter.format(discountValue));
        txtthanhtien.setText(formatter.format(tongTien));
        txt_tienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                capNhatTienThua();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                capNhatTienThua();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                capNhatTienThua();
            }

            public void capNhatTienThua() {
                String inputAmountStr = txt_tienKhachDua.getText();  // Lấy số tiền từ ô nhập tiền
                if (!inputAmountStr.isEmpty()) {
                    try {
                        double inputAmount = Double.parseDouble(inputAmountStr);  // Chuyển đổi số tiền nhập vào

                        // Tính số tiền thừa
                        double moneyLeft = inputAmount - tongTien;

                        // Đảm bảo số tiền thừa không âm
                        if (moneyLeft < 0) {
                            txtTienTraKhac.setText("Số tiền bạn nhập không đủ để thanh toán!");
                        } else {
                            // Hiển thị số tiền thừa
                            String formattedMoneyLeft = formatter.format(moneyLeft);
                            txtTienTraKhac.setText(formattedMoneyLeft);  // Hiển thị số tiền thừa vào JTextField
                        }
                    } catch (NumberFormatException e) {
                        txtTienTraKhac.setText("Vui lòng nhập số tiền hợp lệ.");
                    }
                } else {
                    txtTienTraKhac.setText("");  // Nếu trường nhập tiền trống, làm sạch kết quả
                }
            }
        });
    }
    private ArrayList<XemHoaDonTao> layChiTietHoaDon(int maHoaDon) {
        ArrayList<XemHoaDonTao> chiTietHoaDon = new ArrayList<>();
        String query = "SELECT SanPham.id_ma_san_pham, SanPham.ten_san_pham, ChiTietHoaDon.so_luong, SanPham.gia_ban "
                + "FROM ChiTietHoaDon "
                + "JOIN SanPham ON ChiTietHoaDon.ma_san_pham = SanPham.id_ma_san_pham "
                + "WHERE ChiTietHoaDon.ma_hoa_don = ?";

        try (Connection con = ketnoi.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, maHoaDon);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                XemHoaDonTao chiTiet = new XemHoaDonTao();
                chiTiet.setIdSanPham(rs.getInt("id_ma_san_pham"));
                chiTiet.setTenSanPham(rs.getString("ten_san_pham"));
                chiTiet.setSoLuong(rs.getInt("so_luong"));
                chiTiet.setGiaBan(rs.getFloat("gia_ban"));
                chiTietHoaDon.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu giỏ hàng.");
        }
        return chiTietHoaDon;
    }

    private void taoHoaDonPDF() {
int selectedRow = tblhoadon1.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để cập nhật tổng tiền.");
    return;
}

int maHoaDon = Integer.parseInt(tblhoadon1.getValueAt(selectedRow, 0).toString());
String tenNhanVien = tblhoadon1.getValueAt(selectedRow, 3).toString();
String tenKhachHang = tblhoadon1.getValueAt(selectedRow, 4).toString();
Date ngayLap = (Date) tblhoadon1.getValueAt(selectedRow, 2);

// Lấy chi tiết hóa đơn ban đầu
ArrayList<XemHoaDonTao> chiTietHoaDon = layChiTietHoaDon(maHoaDon);

// Sử dụng Map để cộng dồn số lượng sản phẩm giống nhau
Map<String, XemHoaDonTao> productMap = new HashMap<>();
for (XemHoaDonTao chiTiet : chiTietHoaDon) {
    String tenSanPham = chiTiet.getTenSanPham();

    if (productMap.containsKey(tenSanPham)) {
        // Cộng dồn số lượng cho sản phẩm đã tồn tại
        XemHoaDonTao existingItem = productMap.get(tenSanPham);
        existingItem.setSoLuong(existingItem.getSoLuong() + chiTiet.getSoLuong());
    } else {
        // Thêm sản phẩm mới vào Map
        productMap.put(tenSanPham, chiTiet);
    }
}
double tongTien = 0.0;
for (XemHoaDonTao ct : productMap.values()) {
    tongTien += ct.getGiaBan() * ct.getSoLuong();
}
txttongSoTien.setText(formatter.format(tongTien));
DefaultTableModel model = (DefaultTableModel) tbl_duyem.getModel();
model.setRowCount(0); // Xóa các dòng cũ
for (XemHoaDonTao ct : productMap.values()) {
    Object[] rowData = {
        ct.getMaSanPham(),
        ct.getTenSanPham(),
        ct.getSoLuong(),
        formatter.format(ct.getGiaBan()),
        formatter.format(ct.getSoLuong() * ct.getGiaBan()) // Tính tổng cho mỗi sản phẩm
    };
    model.addRow(rowData); // Thêm dữ liệu đã cộng dồn vào bảng
}
        try {
    File file = new File("D:\\HoaDon_" + maHoaDon + ".pdf");
    if (file.exists()) {
        file.delete();
    }
    PdfWriter writer = new PdfWriter(file);
    PdfDocument pdf = new PdfDocument(writer);
    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
    
    // Thêm tiêu đề và thông tin hóa đơn
    document.add(new Paragraph("HÓA ĐƠN THANH TOÁN")
            .setFont(font)
            .setFontSize(20)
            .setTextAlignment(TextAlignment.CENTER)
            .setBold());
    document.add(new Paragraph("Mã Hóa Đơn: " + maHoaDon).setFont(font).setFontSize(12));
    document.add(new Paragraph("Nhân Viên: " + tenNhanVien).setFont(font).setFontSize(12));
    document.add(new Paragraph("Khách Hàng: " + tenKhachHang).setFont(font).setFontSize(12));
    document.add(new Paragraph("Ngày Lập: " + ngayLap).setFont(font).setFontSize(12));
    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
    
    // Tạo bảng
    float pageWidth = pdf.getDefaultPageSize().getWidth(); // Lấy chiều rộng của trang
    float tableWidth = pageWidth * 0.9f; // 80% chiều rộng màn hình
    Table table = new Table(3);
    table.setWidth(tableWidth);
    
    // Thêm tiêu đề cho các cột
    table.addHeaderCell(new Paragraph("Tên Sản Phẩm").setFont(font).setBold()
            .setBackgroundColor(ColorConstants.GRAY)
            .setFontColor(ColorConstants.WHITE)
            .setTextAlignment(TextAlignment.CENTER));
    table.addHeaderCell(new Paragraph("Số Lượng").setFont(font).setBold()
            .setBackgroundColor(ColorConstants.GRAY)
            .setFontColor(ColorConstants.WHITE)
            .setTextAlignment(TextAlignment.CENTER));
    table.addHeaderCell(new Paragraph("Giá").setFont(font).setBold()
            .setBackgroundColor(ColorConstants.GRAY)
            .setFontColor(ColorConstants.WHITE)
            .setTextAlignment(TextAlignment.CENTER));
    
    // In danh sách sản phẩm đã cộng dồn từ productMap
    for (XemHoaDonTao ct : productMap.values()) {
        table.addCell(new Paragraph(ct.getTenSanPham()).setFont(font));
        table.addCell(new Paragraph(String.valueOf(ct.getSoLuong())).setFont(font).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Paragraph(String.valueOf(ct.getGiaBan())).setFont(font).setTextAlignment(TextAlignment.CENTER));
    }

    document.add(table);
    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
    
    // Thêm tổng tiền vào cuối
    document.add(new Paragraph("TÔNG TIỀN: " + formatter.format(tongTien))
            .setFont(font)
            .setFontSize(12)
            .setBold()
            .setTextAlignment(TextAlignment.RIGHT));
    
    // Thêm thông tin ngân hàng và liên hệ
    document.add(new Paragraph("GỬI THANH TOÁN ĐẾN")
            .setFont(font)
            .setFontSize(10)
            .setMarginTop(20));
    document.add(new Paragraph("Số ngân hàng: 0367548754 \n Tên ngân hàng: Đoàn Ngọc Duy")
            .setFont(font)
            .setFontSize(10));
    document.add(new Paragraph("LIÊN HỆ")
            .setFont(font)
            .setFontSize(10)
            .setMarginTop(-40)
            .setTextAlignment(TextAlignment.RIGHT));
    document.add(new Paragraph("doanngocduy62@gmail.com\nSĐT:0382424762")
            .setFont(font)
            .setFontSize(10)
            .setTextAlignment(TextAlignment.RIGHT));
    
    document.close();
    JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
} catch (IOException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn PDF!");
}

    }
    private void timKKiem(){
        String tenSanPham = txt_timSanPham.getText();
        String trangThai = (String)txt_TimDanhMuc.getSelectedItem();
        ArrayList<SanPhamEntity> KetQua = hd.timkiemSanPhamm(trangThai, tenSanPham);
        hienThiDuLieu(KetQua);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jpanel_Nen = new javax.swing.JPanel();
        jPanel_DonHang = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_chon = new javax.swing.JButton();
        btn_thaydoi = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbo_hinhThucThanhToan = new javax.swing.JComboBox<>();
        cbo_hinhThucMuaHang = new javax.swing.JComboBox<>();
        txt_tienKhachDua = new javax.swing.JTextField();
        txtTienTraKhac = new javax.swing.JLabel();
        txtthanhtien = new javax.swing.JLabel();
        txttongSoTien = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_taoma = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JLabel();
        jPanel_HoaDonCHo = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblhoadon1 = new javax.swing.JTable();
        jPanel_QuetMa = new javax.swing.JPanel();
        QR = new javax.swing.JLabel();
        qr_img = new javax.swing.JLabel();
        jPanel_Phieu = new javax.swing.JPanel();
        btn_xoasp = new javax.swing.JButton();
        btn_xoaAll = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_duyem = new javax.swing.JTable();
        btn_themsp = new javax.swing.JButton();
        jPanel_DanhSachSP = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txt_timSanPham = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_TimDanhMuc = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        Jpanel_Nen.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_DonHang.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_DonHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_DonHang.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Khách Hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên Khách Hàng:");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("KH00");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Khách Mua Tại Quầy");

        btn_chon.setBackground(new java.awt.Color(204, 204, 204));
        btn_chon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_chon.setForeground(new java.awt.Color(51, 51, 51));
        btn_chon.setText("Chọn");
        btn_chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonActionPerformed(evt);
            }
        });

        btn_thaydoi.setBackground(new java.awt.Color(204, 204, 204));
        btn_thaydoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_thaydoi.setForeground(new java.awt.Color(51, 51, 51));
        btn_thaydoi.setText("Thay Đổi");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_chon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_thaydoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(btn_chon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(btn_thaydoi))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã Hoá Đơn:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tổng Tiền:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Giảm Giá:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Thanh Toán:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tiền Khách Đưa:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tiền Thừa Trả Khách:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Hình Thức Thanh Toán:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Hình Thức Mua Hàng:");

        cbo_hinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản", "Quẹt Thẻ", " " }));

        cbo_hinhThucMuaHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bán Trực Tiếp", "Bán Online", " " }));

        txt_tienKhachDua.setText("0");

        txtTienTraKhac.setText("0");

        txtthanhtien.setText("0");

        txttongSoTien.setText("0");
        txttongSoTien.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txttongSoTienAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel17.setText("Vui lòng tạo!");

        btn_taoma.setBackground(new java.awt.Color(255, 204, 0));
        btn_taoma.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_taoma.setText("Tạo");
        btn_taoma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_taomaMouseClicked(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 204, 0));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton12.setText("Huỷ Hoá Đơn");

        jButton13.setBackground(new java.awt.Color(255, 204, 0));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setText("Làm Mới");

        jButton14.setBackground(new java.awt.Color(255, 204, 0));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton14.setText("Thanh Toán");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel18.setText("VNĐ");

        jLabel19.setText("VNĐ");

        jLabel20.setText("VNĐ");

        jLabel21.setText("VNĐ");

        jLabel22.setText("VNĐ");

        txtGiamGia.setText("0");

        javax.swing.GroupLayout jPanel_DonHangLayout = new javax.swing.GroupLayout(jPanel_DonHang);
        jPanel_DonHang.setLayout(jPanel_DonHangLayout);
        jPanel_DonHangLayout.setHorizontalGroup(
            jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                        .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton12)
                                .addComponent(jLabel12)))
                        .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_DonHangLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_taoma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                                        .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTienTraKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addComponent(jLabel20))
                                            .addComponent(cbo_hinhThucMuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                                            .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txttongSoTien, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))))))))
                    .addComponent(jLabel6)
                    .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_DonHangLayout.setVerticalGroup(
            jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel17)
                    .addComponent(btn_taoma))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel18))
                    .addComponent(txttongSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel19))
                    .addComponent(txtGiamGia))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtthanhtien)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTienTraKhac)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbo_hinhThucMuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(18, 18, 18)
                .addComponent(jButton14)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel_HoaDonCHo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_HoaDonCHo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá Đơn Chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_HoaDonCHo.setForeground(new java.awt.Color(255, 255, 255));

        tblhoadon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Hóa Đơn", "Mã Hóa Đơn", "Ngày Tạo", "Nhân Viên Tạo", "Mã Khách Hàng"
            }
        ));
        tblhoadon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadon1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblhoadon1);

        javax.swing.GroupLayout jPanel_HoaDonCHoLayout = new javax.swing.GroupLayout(jPanel_HoaDonCHo);
        jPanel_HoaDonCHo.setLayout(jPanel_HoaDonCHoLayout);
        jPanel_HoaDonCHoLayout.setHorizontalGroup(
            jPanel_HoaDonCHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDonCHoLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_HoaDonCHoLayout.setVerticalGroup(
            jPanel_HoaDonCHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDonCHoLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel_QuetMa.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_QuetMa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét Mã San Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_QuetMa.setForeground(new java.awt.Color(255, 255, 255));

        QR.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                QRAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel_QuetMaLayout = new javax.swing.GroupLayout(jPanel_QuetMa);
        jPanel_QuetMa.setLayout(jPanel_QuetMaLayout);
        jPanel_QuetMaLayout.setHorizontalGroup(
            jPanel_QuetMaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QuetMaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qr_img, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_QuetMaLayout.setVerticalGroup(
            jPanel_QuetMaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QuetMaLayout.createSequentialGroup()
                .addGroup(jPanel_QuetMaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QR, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addGroup(jPanel_QuetMaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(qr_img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel_Phieu.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Phieu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_Phieu.setForeground(new java.awt.Color(255, 255, 255));

        btn_xoasp.setBackground(new java.awt.Color(255, 204, 0));
        btn_xoasp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoasp.setText("Xoá Sản Phẩm");

        btn_xoaAll.setBackground(new java.awt.Color(255, 204, 0));
        btn_xoaAll.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoaAll.setText("Xoá Tất Cả");

        tbl_duyem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Tiền"
            }
        ));
        tbl_duyem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_duyemMouseClicked(evt);
            }
        });
        tbl_duyem.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                tbl_duyemComponentMoved(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_duyem);

        btn_themsp.setBackground(new java.awt.Color(255, 204, 0));
        btn_themsp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_themsp.setText("Thêm Sản Phẩm");
        btn_themsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themspActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_PhieuLayout = new javax.swing.GroupLayout(jPanel_Phieu);
        jPanel_Phieu.setLayout(jPanel_PhieuLayout);
        jPanel_PhieuLayout.setHorizontalGroup(
            jPanel_PhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PhieuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addGroup(jPanel_PhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_themsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_xoasp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel_PhieuLayout.setVerticalGroup(
            jPanel_PhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PhieuLayout.createSequentialGroup()
                .addGroup(jPanel_PhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PhieuLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btn_xoasp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_themsp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_PhieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel_DanhSachSP.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_DanhSachSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_DanhSachSP.setForeground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Tìm Kiếm Sản Phẩm:");

        txt_timSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timSanPhamActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Danh Mục:");

        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Sản Phẩm", "Mã Sản Phẩm", "Tến Sản Phẩm", "Số Lượng", "Giá Sản Phẩm", "Hình Ảnh", "Kích Thước", "Máu Sắc", "Thương Hiệu", "Chất Liệu", "Xuất Xứ", "Loại Sản Phẩm", "Trạng Thái"
            }
        ));
        jScrollPane4.setViewportView(tbl_sanPham);

        jButton1.setText("TimKiem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_DanhSachSPLayout = new javax.swing.GroupLayout(jPanel_DanhSachSP);
        jPanel_DanhSachSP.setLayout(jPanel_DanhSachSPLayout);
        jPanel_DanhSachSPLayout.setHorizontalGroup(
            jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DanhSachSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_DanhSachSPLayout.createSequentialGroup()
                        .addGroup(jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_DanhSachSPLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_timSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jPanel_DanhSachSPLayout.createSequentialGroup()
                                .addComponent(txt_TimDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_DanhSachSPLayout.setVerticalGroup(
            jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DanhSachSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_DanhSachSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Jpanel_NenLayout = new javax.swing.GroupLayout(Jpanel_Nen);
        Jpanel_Nen.setLayout(Jpanel_NenLayout);
        Jpanel_NenLayout.setHorizontalGroup(
            Jpanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_NenLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Jpanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel_NenLayout.createSequentialGroup()
                        .addComponent(jPanel_HoaDonCHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_QuetMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel_Phieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_DanhSachSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addComponent(jPanel_DonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Jpanel_NenLayout.setVerticalGroup(
            Jpanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_NenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel_NenLayout.createSequentialGroup()
                        .addGroup(Jpanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel_HoaDonCHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_QuetMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_Phieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_DanhSachSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_DonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jpanel_Nen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jpanel_Nen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_chonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_chonActionPerformed

    private void btn_taomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_taomaMouseClicked
        // TODO add your handling code here:
        if (tblhoadon1.getSelectedRow() > 10) {
            JOptionPane.showMessageDialog(Jpanel_Nen, "Hóa Đơn Không Tạo Lớn Hơn 10");
        } else {
            int idNhanVien = LuuThongTinDangNhap.getInNhanVien();
            int maKhachHang = 3;
            String trangThai = "đang chờ thanh toán";
            String hinhThuc = "Đang Đợi";
            String hinhThucBanHang = "Hình Thức Bán Hàng";
            String sql = """
                 INSERT INTO HoaDon (ngay_lap, ma_nhan_vien, ma_khach_hang, tong_tien,trangThai,hinh_thuc_chuyen_tien,hinh_thuc_ban_hang)
                 VALUES (GETDATE(), ?, ?,?,?,?,?)
                 """;

            try (Connection con = ketnoi.getConnection()) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, idNhanVien);  // ID nhân viên tạo hóa đơn
                ps.setInt(2, maKhachHang);  // Mã khách hàng (bán tại quầy)
                ps.setObject(3, 0);
                ps.setObject(4, trangThai);
                ps.setObject(5, hinhThuc);
                ps.setObject(6, hinhThucBanHang);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Hóa đơn được tạo thành công!");
                    HienThiDuLiuHoaDon(hd.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi khi tạo hóa đơn.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu.");
            }
        }


    }//GEN-LAST:event_btn_taomaMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedTongTien = formatter.format(tongTien);
        txtthanhtien.setText(formattedTongTien);
        int selectedRow = tblhoadon1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để cập nhật tổng tiền.");
            return;
        }
        int maHoaDon = Integer.parseInt(tblhoadon1.getValueAt(selectedRow, 0).toString());  // Cột 0 chứa maHoaDon
        String trangThai ="Đã Thanh Toán";
        String sql = """
             UPDATE HoaDon SET tong_tien = ?, trangThai = ?, hinh_thuc_chuyen_tien = ?, hinh_thuc_ban_hang = ?   WHERE id_ma_hoa_don = ?
             """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, tongTien);
            ps.setObject(2, trangThai);
            ps.setObject(3,cbo_hinhThucThanhToan.getSelectedItem());
            ps.setObject(4, cbo_hinhThucMuaHang.getSelectedItem());
            ps.setInt(5, maHoaDon);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công");
            taoHoaDonPDF();
            int selectedRoww = tblhoadon1.getSelectedRow();
            int duyem = tbl_duyem.getSelectedRow();
            if (selectedRoww != -1) {
                DefaultTableModel model = (DefaultTableModel) tblhoadon1.getModel();
                model.removeRow(selectedRoww);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để xóa.");
            }
            DefaultTableModel modelDuyem = (DefaultTableModel) tbl_duyem.getModel();
            modelDuyem.setRowCount(0);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi khi cập nhật tổng tiền!");
        }
        txtGiamGia.setText("");
        txtTienTraKhac.setText("");
        txtthanhtien.setText("");
        txttongSoTien.setText("");
        txt_tienKhachDua.setText("");

//try {
//    File file = new File("D:\\HoaDon_" + maHoaDon + ".pdf"); 
//
//    // Kiểm tra nếu file đã tồn tại, nếu có, xóa đi trước khi tạo lại
//    if (file.exists()) {
//        file.delete();
//    }
//
//    // Tạo PdfWriter và PdfDocument
//    PdfWriter writer = new PdfWriter(file);
//    PdfDocument pdf = new PdfDocument(writer);
//
//    // Sử dụng font chuẩn từ file TTF
//    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
//
//    // Tạo các phần tử của PDF như tiêu đề, thông tin hóa đơn
//    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
//
//    document.add(new Paragraph("HÓA ĐƠN THANH TOÁN")
//            .setFont(font).setFontSize(20));
//    document.add(new Paragraph("Mã Hóa Đơn: " + maHoaDon).setFont(font));
//    document.add(new Paragraph("Ngày: " + java.time.LocalDate.now()).setFont(font));
//    document.add(new Paragraph("-------------------------------------------------").setFont(font));
//
//    // Thêm thông tin tổng tiền
//    document.add(new Paragraph("Tổng Tiền: " + tongTien).setFont(font));
//    document.add(new Paragraph("-------------------------------------------------").setFont(font));
//
//    // Thêm thông báo
//    document.add(new Paragraph("Cảm ơn quý khách đã mua hàng!").setFont(font));
//
//    // Đóng tài liệu PDF
//    document.close();
//
//    // Hiển thị thông báo khi file được tạo thành công
//    System.out.println("Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
//} catch (IOException e) {
//    e.printStackTrace();
//}
// Kiểm tra người dùng đã chọn một dòng trong bảng giỏ hàng hay chưa
//int maHoaDon = 1;
//ThanhToanEntity tt = new ThanhToanEntity();
////tt.setMaHoaDon(maHoaDon);
//String query = "SELECT SanPham.ten_san_pham, SanPham.so_luong_ton, NhanVien.ten_nhan_vien, "
//             + "KhachHang.ten_khach_hang, HoaDon.ngay_lap, HoaDon.tong_tien "
//             + "FROM ChiTietHoaDon "
//             + "JOIN HoaDon ON ChiTietHoaDon.ma_hoa_don = HoaDon.id_ma_hoa_don "
//             + "JOIN KhachHang ON HoaDon.ma_khach_hang = KhachHang.id_ma_khach_hang "
//             + "JOIN NhanVien ON NhanVien.id_ma_nhan_vien = HoaDon.ma_nhan_vien "
//             + "JOIN SanPham ON SanPham.id_ma_san_pham = ChiTietHoaDon.ma_san_pham "
//             + "WHERE HoaDon.id_ma_hoa_don = ? ";
//try {
//    Connection con = ketnoi.getConnection();
//    PreparedStatement stmt = con.prepareStatement(query);
//    stmt.setInt(1, tt.getMaHoaDon()); // Truyền mã hóa đơn cần tìm
//    
//    // Kiểm tra trước khi thực thi truy vấn
//    System.out.println("Đang thực thi truy vấn với Mã Hóa Đơn: " + tt.getMaHoaDon());
//    
//    ResultSet rs = stmt.executeQuery();
//
//    if (rs.next()) {
//        // Gán giá trị vào đối tượng ThanhToanEntity
//        tt.setTenSanPham(rs.getString("ten_san_pham"));
//        tt.setSoLuongTon(rs.getInt("so_luong_ton"));
//        tt.setTenNhanVien(rs.getString("ten_nhan_vien"));
//        tt.setTenKhachHang(rs.getString("ten_khach_hang"));
//        tt.setNgayLap(rs.getDate("ngay_lap"));
//        tongTien = rs.getDouble("tong_tien");
//    } else {
//        // Trường hợp không tìm thấy dữ liệu
//        System.out.println("Không tìm thấy hóa đơn với mã: " + tt.getMaHoaDon());
//    }
//    
//    // Kiểm tra các giá trị đã được gán
//    System.out.println("Tên Sản Phẩm: " + tt.getTenSanPham());
//    System.out.println("Số Lượng Tồn: " + tt.getSoLuongTon());
//    System.out.println("Tên Nhân Viên: " + tt.getTenNhanVien());
//    System.out.println("Tên Khách Hàng: " + tt.getTenKhachHang());
//    System.out.println("Ngày Lập: " + tt.getNgayLap());
//    System.out.println("Tổng Tiền: " + tongTien);
//    
//    // Tạo file PDF
//    File file = new File("D:\\HoaDon_" + tt.getMaHoaDon() + ".pdf");
//
//    if (file.exists()) {
//        file.delete();
//    }
//
//    // Tạo PdfWriter và PdfDocument
//    PdfWriter writer = new PdfWriter(file);
//    com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
//    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
//    // Thêm tiêu đề và thông tin vào PDF
//    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
//    document.add(new Paragraph("HÓA ĐƠN THANH TOÁN").setFontSize(20));
//    document.add(new Paragraph("Mã Hóa Đơn: " + tt.getMaHoaDon()));
//    document.add(new Paragraph("Mã Nhân Viên: " + tt.getTenNhanVien()));
//    document.add(new Paragraph("Mã Khách Hàng: " + tt.getTenKhachHang()));
//    document.add(new Paragraph("Ngày: " + tt.getNgayLap()));
//    document.add(new Paragraph("-------------------------------------------------"));
//
//    // Thêm thông tin sản phẩm và số lượng
//    document.add(new Paragraph("Sản Phẩm: " + tt.getTenSanPham()));
//    document.add(new Paragraph("Số Lượng Tồn: " + tt.getSoLuongTon()));
//
//    // Thêm thông tin tổng tiền
//    document.add(new Paragraph("Tổng Tiền: " + tongTien));
//    document.add(new Paragraph("-------------------------------------------------"));
//
//    // Thêm thông báo
//    document.add(new Paragraph("Cảm ơn quý khách đã mua hàng!"));
//
//    // Đóng tài liệu PDF
//    document.close();
//
//    // Thông báo tạo hóa đơn thành công
//    System.out.println("Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
//
//} catch (SQLException | IOException e) {
//    e.printStackTrace();
//    System.out.println("Lỗi khi tạo hóa đơn PDF!");
//}

    }//GEN-LAST:event_jButton14ActionPerformed

    private void txttongSoTienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txttongSoTienAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongSoTienAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        timKKiem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_timSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timSanPhamActionPerformed
        // TODO add your handling code here:
        timKKiem();
    }//GEN-LAST:event_txt_timSanPhamActionPerformed

    private void btn_themspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themspActionPerformed
        //         int selectedRow = tbl_sanPham.getSelectedRow();
        //                    if (selectedRow != -1) {
            //                        // Lấy dữ liệu từ dòng được chọn
            //                        String maSanPham = tbl_sanPham.getValueAt(selectedRow, 0).toString();
            //                        String tenSanPham = tbl_sanPham.getValueAt(selectedRow, 1).toString();
            //                        String giaBan = tbl_sanPham.getValueAt(selectedRow, 2).toString();
            //                        String soLuong = tbl_sanPham.getValueAt(selectedRow, 3).toString();
            //
            //                        // Hiển thị thông tin sản phẩm được chọn
            //                        JOptionPane.showMessageDialog(null,
                //                                "Mã sản phẩm: " + maSanPham + "\n" +
                //                                "Tên sản phẩm: " + tenSanPham + "\n" +
                //                                "Giá bán: " + giaBan + "\n" +
                //                                "Số lượng: " + soLuong,
                //                                "Thông tin sản phẩm",
                //                                JOptionPane.INFORMATION_MESSAGE);
            //                    }
        //// Giả sử bạn có bảng tbl_hoaDon và tbl_sanPham
        //
        //// Lấy idMaHoaDon từ bảng tbl_hoaDon khi người dùng chọn hóa đơn
        ////int selectedRowHoaDon = tblhoadon.getSelectedRow();
        ////if (selectedRowHoaDon != -1) {
            ////    int idMaHoaDon = Integer.parseInt(tblhoadon.getValueAt(selectedRowHoaDon, 0).toString());
            ////    int selectedRowSanPham = tbl_sanPham.getSelectedRow();
            ////    if (selectedRowSanPham != -1) {
                ////        int idMaSanPham = Integer.parseInt(tbl_sanPham.getValueAt(selectedRowSanPham, 0).toString());
                ////        int soLuong = Integer.parseInt(tbl_sanPham.getValueAt(selectedRowSanPham, 3).toString());
                ////        float giaBan = Float.parseFloat(tbl_sanPham.getValueAt(selectedRowSanPham, 4).toString());
                ////        boolean success = hd.themChiTietHoaDon(idMaHoaDon, idMaSanPham, soLuong, giaBan);
                ////
                ////        if (success) {
                    ////            JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào hóa đơn.");
                    ////        } else {
                    ////            JOptionPane.showMessageDialog(null, "Thêm sản phẩm vào hóa đơn thất bại.");
                    ////        }
                ////    } else {
                ////        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để thêm vào hóa đơn.");
                ////    }
            ////} else {
            ////    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để thêm sản phẩm.");
            ////}
        //// Lấy thông tin từ bảng tbl_sanPham khi người dùng chọn sản phẩm

        // Lấy thông tin từ bảng tbl_sanPham khi người dùng chọn sản phẩm
        int selectedRowSanPham = tbl_sanPham.getSelectedRow();
        if (selectedRowSanPham != -1) {
            int idMaSanPham = Integer.parseInt(tbl_sanPham.getValueAt(selectedRowSanPham, 0).toString());
            String tenSanPham = tbl_sanPham.getValueAt(selectedRowSanPham, 1).toString();
            int soLuongTon = Integer.parseInt(tbl_sanPham.getValueAt(selectedRowSanPham, 3).toString());
            double giaBan = Double.parseDouble(tbl_sanPham.getValueAt(selectedRowSanPham, 4).toString());
            String input = JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm để thêm vào hóa đơn:", "Số lượng sản phẩm", JOptionPane.QUESTION_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                int soLuong = Integer.parseInt(input.trim());
                if (soLuong <= soLuongTon) {
                    int selectedRowHoaDon = tblhoadon1.getSelectedRow();
                    if (selectedRowHoaDon != -1) {
                        int idMaHoaDon = Integer.parseInt(tblhoadon1.getValueAt(selectedRowHoaDon, 0).toString());
                        boolean success = hd.themChiTietHoaDon(idMaHoaDon, idMaSanPham, soLuong, giaBan);
                        if (success) {
                            boolean updateSuccess = hd.capNhatSoLuongSanPham(idMaSanPham, soLuongTon - soLuong);
                            if (updateSuccess) {
                                tbl_sanPham.setValueAt(soLuongTon - soLuong, selectedRowSanPham, 3);
                                JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào hóa đơn và cập nhật số lượng.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm vào hóa đơn thất bại.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn trước.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Số lượng không đủ trong kho!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm.");
        }

    }//GEN-LAST:event_btn_themspActionPerformed

    private void tbl_duyemComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbl_duyemComponentMoved
        // TODO add your handling code here:
        // Load cart data for this invoice
    }//GEN-LAST:event_tbl_duyemComponentMoved

    private void tbl_duyemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_duyemMouseClicked
        // TODO add your handling code here:
        int selectedRowHoaDon = tblhoadon1.getSelectedRow();
        if (selectedRowHoaDon != -1) {
            int maGioHangHienTai = Integer.parseInt(tblhoadon1.getValueAt(selectedRowHoaDon, 0).toString());
            hienThiDuLieuGioHang(maGioHangHienTai); // Hiển thị dữ liệu giỏ hàng
        }
    }//GEN-LAST:event_tbl_duyemMouseClicked

    private void QRAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_QRAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_QRAncestorAdded

    private void tblhoadon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadon1MouseClicked
        // TODO add your handling code here
        int selectedRowHoaDon = tblhoadon1.getSelectedRow(); // Kiểm tra xem có hàng nào được chọn không
        if (selectedRowHoaDon != -1) {  // Nếu có hàng được chọn
            // Lấy mã giỏ hàng từ cột đầu tiên (cột 0) của hàng được chọn trong bảng hóa đơn
            int maGioHangHienTai = Integer.parseInt(tblhoadon1.getValueAt(selectedRowHoaDon, 0).toString());
            try {
                loadCartData(maGioHangHienTai);
            } catch (IOException ex) {
                Logger.getLogger(ChucNangBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblhoadon1MouseClicked
private static String scanQRCode(BufferedImage bufferedImage) throws FormatException, ChecksumException {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new QRCodeReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null; // Không tìm thấy mã QR trong khung hình
        }
    }
   private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel_Nen;
    private javax.swing.JLabel QR;
    private javax.swing.JButton btn_chon;
    private javax.swing.JButton btn_taoma;
    private javax.swing.JButton btn_thaydoi;
    private javax.swing.JButton btn_themsp;
    private javax.swing.JButton btn_xoaAll;
    private javax.swing.JButton btn_xoasp;
    private javax.swing.JComboBox<String> cbo_hinhThucMuaHang;
    private javax.swing.JComboBox<String> cbo_hinhThucThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel_DanhSachSP;
    private javax.swing.JPanel jPanel_DonHang;
    private javax.swing.JPanel jPanel_HoaDonCHo;
    private javax.swing.JPanel jPanel_Phieu;
    private javax.swing.JPanel jPanel_QuetMa;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel qr_img;
    private javax.swing.JTable tbl_duyem;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTable tblhoadon1;
    private javax.swing.JLabel txtGiamGia;
    private javax.swing.JLabel txtTienTraKhac;
    private javax.swing.JComboBox<String> txt_TimDanhMuc;
    private javax.swing.JTextField txt_tienKhachDua;
    private javax.swing.JTextField txt_timSanPham;
    private javax.swing.JLabel txtthanhtien;
    private javax.swing.JLabel txttongSoTien;
    // End of variables declaration//GEN-END:variables
}
