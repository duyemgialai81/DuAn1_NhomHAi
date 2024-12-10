/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;
import Entity.DonHangChiTiet.DonHangChiTietEntity;
import Entity.HoaDon.HoaDonEntity;
import Entity.HoaDon.HoaDonXemDuLieu;
import Entity.HoaDon.LayMaHoaDon;
import Entity.HoaDon.XemHoaDonTao;
import Entity.HoaDon.XuatHoaDon;
import Entity.KhachHang.KhachHang;
import Entity.SanPham.SanPhamEntity;
import Entity.ThuocTinh.ChatLieuEntity;
import Entity.ThuocTinh.KichCoEnTity;
import Entity.ThuocTinh.MauSacEntity;
import Entity.ThuocTinh.ThuongHieuEntity;
import Repository.BanHangTesst;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import KetNoiSQL.ketnoi;
import Repository.DonHangChiTietRepository;
import Repository.HoaDonrepository;
import Repository.KhachHangRepository;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.property.HorizontalAlignment;
import java.awt.Image;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.lang.System.Logger.Level;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SingPC
 */
public class NewJPanel extends javax.swing.JPanel implements Runnable, ThreadFactory {

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private double tongTien = 0;
    private DefaultTableModel md = new DefaultTableModel();
    private BanHangTesst ls = new BanHangTesst();
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int pageSize = 16; // Số sản phẩm mỗi trang
    private int currentPage = 1;
    private boolean isduyem = false;
    private DonHangChiTietRepository dh = new DonHangChiTietRepository();
    private HoaDonrepository hd = new HoaDonrepository();
    private KhachHangRepository khachHang = new KhachHangRepository();

    /**
     * Creates new form NewJPanel
     */
    
    public NewJPanel() {
        initComponents();
        hienThiDuLieuuu(ls.getSanPhamChhiTiet());
        initWebcam();
        hienThiDuLieu(dh.layHhoaDon());
        hienThiDuLieuGioHangg();
        thuocTinhThuongHieu();
        thuocTinhMauSac();
        thuocTinhKichCo();
    }

    public void hienThiDuLieuGioHangg() {
        try {
            if (txtid.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm trong trường dữ liệu!");
                return;
            }
            int id = Integer.parseInt(txtid.getText().trim());
            ArrayList<DonHangChiTietEntity> ls = hd.layGioHangSanPham(id);
            if (ls.isEmpty()) {
            } else {
                hienThiDuLieuGioHang(ls);
            }
        } catch (NumberFormatException e) {
            hienThiDuLieu(dh.layHhoaDon());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void timKimMaKhachHang() {
        String soDienThaoi = txtsodienthoai.getText();
        ArrayList<KhachHang> kh = khachHang.timKiemKhachHang(soDienThaoi);
        if (!kh.isEmpty()) {
            KhachHang khtimkiem = kh.get(0);

            txtidkhachhang.setText(String.valueOf(khtimkiem.getIdKhachHang()));
            txtTenKhachHang.setText(khtimkiem.getTenKH());
        } else {
            ChonKhachHangBanHang duyemdayne = new ChonKhachHangBanHang();
            duyemdayne.setVisible(true);
        }

    }

    private void hienThiDuLieuGioHangg(int maGioHang) {
    try (Connection con = ketnoi.getConnection()) {
        String sqlChiTiet = """
               SELECT	
                ctdh.ma_san_pham,
                sp.ma_san_pham as maSanPham, 
                sp.ten_san_pham, 
                sp.gia_ban, 
                ctdh.so_luong
               FROM chitietdonhang ctdh
               JOIN SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
               JOIN DonHang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
               WHERE dh.id_ma_don_hang = ?
        """;
        String sqlTongTienThue = """
            SELECT 
            SUM(tong_tien) AS tongTien,
            SUM(tong_tien * thue / 100) AS tongThue
            FROM ChiTietDonHang ctdh
            JOIN donhang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
            WHERE ctdh.ma_don_hang = ?
        """;
        try (PreparedStatement psChiTiet = con.prepareStatement(sqlChiTiet); 
             PreparedStatement psTongTienThue = con.prepareStatement(sqlTongTienThue)) {
            psChiTiet.setInt(1, maGioHang);
            ResultSet rsChiTiet = psChiTiet.executeQuery();
            Map<String, Object[]> productMap = new HashMap<>();
            while (rsChiTiet.next()) {
                int idMaDonHang = rsChiTiet.getInt("ma_san_pham");
                String maSanPham = rsChiTiet.getString("maSanPham");
                String tenSanPham = rsChiTiet.getString("ten_san_pham");
                float giaBan = rsChiTiet.getFloat("gia_ban");
                int soLuong = rsChiTiet.getInt("so_luong");
                if (productMap.containsKey(maSanPham)) {
                    Object[] productData = productMap.get(maSanPham);
                    productData[4] = (int) productData[4] + soLuong;
                } else {
                    productMap.put(maSanPham, new Object[]{
                        idMaDonHang,
                        maSanPham,
                        tenSanPham,
                        giaBan,
                        soLuong
                    });
                }
            }
            DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
            model.setRowCount(0);
            for (Object[] product : productMap.values()) {
                model.addRow(product);
            }
            psTongTienThue.setInt(1, maGioHang);
            ResultSet rsTongTienThue = psTongTienThue.executeQuery();
            if (rsTongTienThue.next()) {
                float tongTien = rsTongTienThue.getFloat("tongTien");
                float tongThue = rsTongTienThue.getFloat("tongThue");
                float giamGia = 0;
                float thanhTien = tongTien + tongThue; 
                DecimalFormat formatter = new DecimalFormat("#,###.##");
                txttongSoTien.setText(formatter.format(tongTien));
                txtthue.setText(formatter.format(tongThue));
                txtthanhtien.setText(formatter.format(thanhTien));
                txtGiamGia.setText(formatter.format(giamGia));
                apDungKhuyenMai();
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

                    private void capNhatTienThua() {
                        String inputAmountStr = txt_tienKhachDua.getText();
                        if (!inputAmountStr.isEmpty()) {
                            try {
                                String cleanedInput = inputAmountStr.replaceAll("[,\\s]", "");
                                double inputAmount = Double.parseDouble(cleanedInput);
                                double moneyLeft = inputAmount - thanhTien;
                                if (moneyLeft <0) {
                                    txtTienTraKhac.setText(formatter.format(moneyLeft));
                                } else {
                                    txtTienTraKhac.setText(formatter.format(moneyLeft));
                                }
                            } catch (NumberFormatException e) {
                                txtTienTraKhac.setText("Vui lòng nhập số tiền hợp lệ.");
                            }
                        } else {
                            txtTienTraKhac.setText("");
                        }
                    }
                });
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu giỏ hàng: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
//     private void hienThiDuLieuGioHangg(int maGioHang) {
//    try (Connection con = ketnoi.getConnection()) {
//        String sqlChiTiet = """
//               SELECT	
//                ctdh.id_ma_chi_tiet_don_hang,
//                sp.ma_san_pham, 
//                sp.ten_san_pham, 
//                sp.gia_ban, 
//                ctdh.so_luong,
//                ctdh.ma_voucher
//               FROM chitietdonhang ctdh
//               JOIN SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
//               JOIN DonHang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
//               WHERE dh.id_ma_don_hang = ?
//        """;
//        String sqlVoucher = """
//            SELECT 
//            gia_tri, 
//            ngay_bat_dau, 
//            ngay_ket_thuc, 
//            trang_thai, 
//            loai_gia_tri
//            FROM Voucher
//            WHERE id_voucher = ?
//        """;
//        String sqlTongTienThue = """
//            SELECT 
//            SUM(tong_tien) AS tongTien,
//            SUM(tong_tien * thue / 100) AS tongThue
//            FROM ChiTietDonHang ctdh
//            JOIN donhang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
//            WHERE ctdh.ma_don_hang = ?
//        """;
//
//        try (PreparedStatement psChiTiet = con.prepareStatement(sqlChiTiet); 
//             PreparedStatement psVoucher = con.prepareStatement(sqlVoucher);
//             PreparedStatement psTongTienThue = con.prepareStatement(sqlTongTienThue)) {
//             
//            // Lấy dữ liệu sản phẩm
//            psChiTiet.setInt(1, maGioHang);
//            ResultSet rsChiTiet = psChiTiet.executeQuery();
//            Map<String, Object[]> productMap = new HashMap<>();
//            while (rsChiTiet.next()) {
//                int idMaDonHang = rsChiTiet.getInt("id_ma_chi_tiet_don_hang");
//                String maSanPham = rsChiTiet.getString("ma_san_pham");
//                String tenSanPham = rsChiTiet.getString("ten_san_pham");
//                float giaBan = rsChiTiet.getFloat("gia_ban");
//                int soLuong = rsChiTiet.getInt("so_luong");
//                int maVoucher = rsChiTiet.getInt("ma_voucher");
//                if (maVoucher > 0) {
//                    psVoucher.setInt(1, maVoucher);
//                    ResultSet rsVoucher = psVoucher.executeQuery();
//                    if (rsVoucher.next()) {
//                        Date ngayBatDau = rsVoucher.getDate("ngay_bat_dau");
//                        Date ngayKetThuc = rsVoucher.getDate("ngay_ket_thuc");
//                        String trangThai = rsVoucher.getString("trang_thai");
//                        float giaTri = rsVoucher.getFloat("gia_tri");
//                        String loaiGiaTri = rsVoucher.getString("loai_gia_tri");
//                        LocalDate currentDate = LocalDate.now();
//                        if (trangThai.equals("active") && !currentDate.isBefore(ngayBatDau.toLocalDate()) && !currentDate.isAfter(ngayKetThuc.toLocalDate())) {
//                            // Voucher hợp lệ
//                            float discount = 0;
//                            if (loaiGiaTri.equals("Giảm Theo %")) {
//                                discount = giaBan * giaTri / 100;
//                            } else if (loaiGiaTri.equals("Giảm Tiền Trực Tiếp")) {
//                                discount = giaTri;
//                            }
//                            giaBan -= discount;
//                        }
//                    }
//                }
//                if (productMap.containsKey(maSanPham)) {
//                    Object[] productData = productMap.get(maSanPham);
//                    productData[4] = (int) productData[4] + soLuong;
//                } else {
//                    productMap.put(maSanPham, new Object[]{
//                        idMaDonHang,
//                        maSanPham,
//                        tenSanPham,
//                        giaBan,
//                        soLuong
//                    });
//                }
//            }
//            DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
//            model.setRowCount(0);
//            for (Object[] product : productMap.values()) {
//                model.addRow(product);
//            }
//            psTongTienThue.setInt(1, maGioHang);
//            ResultSet rsTongTienThue = psTongTienThue.executeQuery();
//            if (rsTongTienThue.next()) {
//                float tongTien = rsTongTienThue.getFloat("tongTien");
//                float tongThue = rsTongTienThue.getFloat("tongThue");
//                float thanhTien = tongTien + tongThue; 
//              
//                DecimalFormat formatter = new DecimalFormat("#,###.##");
//                txttongSoTien.setText(formatter.format(tongTien));
//                txtthue.setText(formatter.format(tongThue));
//                txtthanhtien.setText(formatter.format(thanhTien));
//                  apDungKhuyenMai();
//                  hienThiDuLieuGioHangg();
////                  hienThiDuLieuGioHangg();
////                txt_tienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
////                    @Override
////                    public void insertUpdate(DocumentEvent e) {
////                        capNhatTienThua();
////                    }
////
////                    @Override
////                    public void removeUpdate(DocumentEvent e) {
////                        capNhatTienThua();
////                    }
////
////                    @Override
////                    public void changedUpdate(DocumentEvent e) {
////                        capNhatTienThua();
////                    }
////
////                    private void capNhatTienThua() {
////                        String inputAmountStr = txt_tienKhachDua.getText();
////                        if (!inputAmountStr.isEmpty()) {
////                            try {
////                                String cleanedInput = inputAmountStr.replaceAll("[,\\s]", "");
////                                double inputAmount = Double.parseDouble(cleanedInput);
////                                double moneyLeft = inputAmount - thanhTien;
////                                if (moneyLeft < 0) {
////                                    txtTienTraKhac.setText("Số tiền bạn nhập không đủ để thanh toán!");
////                                } else {
////                                    txtTienTraKhac.setText(formatter.format(moneyLeft));
////                                }
////                            } catch (NumberFormatException e) {
////                                txtTienTraKhac.setText("Vui lòng nhập số tiền hợp lệ.");
////                            }
////                        } else {
////                            txtTienTraKhac.setText("");
////                        }
////                    }
////                });
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu giỏ hàng: " + e.getMessage(),
//                "Lỗi", JOptionPane.ERROR_MESSAGE);
//    }
//}


   private void hienThiDuLieuuu(ArrayList<SanPhamEntity> lss) {
    md = (DefaultTableModel) tbl_sanPham1.getModel();
    md.setRowCount(0); // Xóa dữ liệu cũ trong bảng
    
    for (SanPhamEntity l : lss) {
        if (l.getSoLuong() > 0) { // Kiểm tra số lượng lớn hơn 0
            md.addRow(new Object[]{
                l.getIdSanPham(), 
                l.getTenSanPham(), 
                l.getSoLuong(), 
                l.getGiaBan(),
                l.getKichCo(), 
                l.getMauSac(), 
                l.getTenThuongHieu()
            });
        }
    }
}


//    private void nextPage(ArrayList<SanPhamEntity> lss) {
//        if ((currentPage * pageSize) < lss.size()) { // Kiểm tra có còn trang tiếp theo không
//            currentPage++;
//            hienThiTrang(lss);
//        }
//    }
//
//    private void previousPage(ArrayList<SanPhamEntity> lss) {
//        if (currentPage > 1) { // Kiểm tra có còn trang trước đó không
//            currentPage--;
//            hienThiTrang(lss);
//        }
//    }
    private void hienThiDuLieuGioHang(ArrayList<DonHangChiTietEntity> chitiet) {
        md = (DefaultTableModel) hienthigiohang.getModel();
        md.setRowCount(0);
        for (DonHangChiTietEntity donHangChiTietEntity : chitiet) {
            md.addRow(new Object[]{
                donHangChiTietEntity.getIdMaSanPham(), donHangChiTietEntity.getMaSanPham(), donHangChiTietEntity.getTenSanPham(), donHangChiTietEntity.getGiaBan(), donHangChiTietEntity.getSoLuong()

            });
        }
    }
    private void hienThiDuLieu(ArrayList<HoaDonXemDuLieu> lss) {
        md = (DefaultTableModel) tbldanhsachdonhagcho.getModel();
        md.setRowCount(0);
        for (HoaDonXemDuLieu ls1 : lss) {
            md.addRow(new Object[]{
                ls1.getIdDonHang(), ls1.getMaHoaDon(), ls1.getTenKhachHang(), ls1.getTenNhanVien(), ls1.getNgayLap(), ls1.getTrangThai()
            });
        }
    }

    private void locSanPham() {
        // Lấy giá trị từ các trường lọc
        String thuongHieu = (String) thuonghieu.getSelectedItem();
        String mauSac = (String) txtLocMauSac.getSelectedItem();
        String kichCo = (String) txtlockichco1.getSelectedItem();
        String tenSanPham = txtLocTenSanPham1.getText().trim();
        ArrayList<SanPhamEntity> sanPham = ls.timkiemSanPhamm(thuongHieu, mauSac, kichCo, tenSanPham);
        hienThiDuLieuuu(sanPham);
    }

//private void initWebcam() {
//    Dimension size = WebcamResolution.QVGA.getSize();
//    webcam = Webcam.getWebcams().get(0);
//    if (webcam.isOpen()) {
//        webcam.close();
//    }
//    webcam.setViewSize(size);
//    panel = new WebcamPanel(webcam);
//    panel.setPreferredSize(size);
//    panel.setFPSDisplayed(true);
//    duyemWebcam.setLayout(new BorderLayout());
//    duyemWebcam.add(panel, 0);
//    executor.execute( this);
//}
//@Override
//public void run() {
//    do {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Result result = null;
//        BufferedImage image = null;
//
//        if (webcam.isOpen()) {
//            image = webcam.getImage();
//            if (image == null) {
//                continue;
//            }
//        }
//        try {
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            result = new MultiFormatReader().decode(bitmap);
//        } catch (Exception e) {
//        }
//
//        if (result != null) {
//            handleQRCodeScan(result.getText());
//        }
//    } while (true);
//}
//private void handleQRCodeScan(String maSanPhamHoaDon) {
//    ArrayList<SanPhamEntity> ls = new ArrayList<>();
//    String sql = "SELECT ma_san_pham, ten_san_pham, so_luong_ton, gia_ban FROM SanPham WHERE ma_san_pham = ?";
//    
//    try (Connection con = ketnoi.getConnection(); 
//         PreparedStatement stmt = con.prepareStatement(sql)) {
//        stmt.setObject(1, maSanPhamHoaDon);
//        
//        try (ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                SanPhamEntity sanPham = new SanPhamEntity();
//                sanPham.setMaSanPham(rs.getString("ma_san_pham"));
//                sanPham.setTenSanPham(rs.getString("ten_san_pham"));
//                sanPham.setSoLuong(rs.getInt("so_luong_ton"));
//                sanPham.setGiaBan(rs.getFloat("gia_ban"));
//                ls.add(sanPham);
//            } else {
//                System.out.println("Không tìm thấy sản phẩm với mã: " + maSanPhamHoaDon);
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    String input = javax.swing.JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm để thêm vào hóa đơn:", "Số lượng sản phẩm", javax.swing.JOptionPane.QUESTION_MESSAGE);
//    try {
//        int soLuongg = Integer.parseInt(input);
//        if (soLuongg <= 0) {
//            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
//            return;
//        }
//        addAllProductsToOrder(ls, soLuongg);
//    } catch (NumberFormatException ex) {
//        JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
//    }
//}
//private void addAllProductsToOrder(ArrayList<SanPhamEntity> ls, int soLuongg) {
//    for (SanPhamEntity sanPham : ls) {
//        int maSanPham = Integer.parseInt(sanPham.getMaSanPham().replace("SP", ""));
//        String tenSanPham = sanPham.getTenSanPham();
//        float giaBan = sanPham.getGiaBan();
//        if (soLuongg > 0) {
//            addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuongg);
//            updateProductQuantity(maSanPham, soLuongg);
//        } else {
//            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0.");
//        }
//    }
//}
//private void updateProductQuantity(int maSanPham, int soLuong) {
//    String sql = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? WHERE ma_san_pham = ?";
//    try (Connection con = ketnoi.getConnection(); 
//         PreparedStatement stmt = con.prepareStatement(sql)) {
//        stmt.setInt(1, soLuong);
//        stmt.setInt(2, maSanPham);
//        int rowsAffected = stmt.executeUpdate();
//        if (rowsAffected > 0) {
//            System.out.println("Cập nhật số lượng tồn kho thành công.");
//        } else {
//            System.out.println("Không thể cập nhật số lượng tồn kho.");
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
//
//
//private int nhapSoLuong(SanPhamEntity sanPham) {
//    while (true) {
//        String input = JOptionPane.showInputDialog(this, "Nhập số lượng cho sản phẩm " + sanPham.getTenSanPham() + ":");
//        if (input == null || input.isEmpty()) {
//            return -1;  // Người dùng hủy hoặc không nhập gì
//        }
//
//        try {
//            int soLuong = Integer.parseInt(input);
//            if (soLuong <= 0) {
//                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng nhập lại.");
//            }  else {
//                return soLuong;
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ.");
//        }
//    }
//}
//
//
//@Override
//public Thread newThread(Runnable r) {
//    Thread t = new Thread(r, "WebcamThread");
//    t.setDaemon(true);
//    return t;
//}
//private void batCam() {
//    System.out.println("Bắt đầu bật camera...");
//    if (webcam == null || !webcam.isOpen()) {
//        webcam = Webcam.getDefault();  // Khởi tạo lại camera mặc định nếu cần
//        webcam.setViewSize(WebcamResolution.QVGA.getSize());
//        
//        // Thiết lập WebcamPanel với FPS cao hơn
//        panel = new WebcamPanel(webcam);
//        panel.setPreferredSize(WebcamResolution.QVGA.getSize());
//        panel.setFPSLimited(true);      // Hạn chế FPS theo mức thiết lập          // Thiết lập FPS lên 120
//        panel.setFPSDisplayed(true);    // Hiển thị FPS để kiểm tra
//
//        duyemWebcam.setLayout(new BorderLayout());
//        duyemWebcam.add(panel, BorderLayout.CENTER);
//        duyemWebcam.revalidate();
//        duyemWebcam.repaint();
//
//        webcam.open();
//        System.out.println("Webcam đã bật với FPS 120.");
//    } else {
//        System.out.println("Webcam đã bật sẵn.");
//    }
//}
//
//
// private void tatCam() {
//    System.out.println("Đang tắt camera...");
//    if (webcam != null && webcam.isOpen()) {
//        webcam.close();
//        webcam = null;  // Đảm bảo rằng webcam sẽ được khởi tạo lại khi bật
//        duyemWebcam.remove(panel);
//        duyemWebcam.revalidate();
//        duyemWebcam.repaint();
//        System.out.println("Camera đã tắt.");
//    }
//}
//     private void loadCartData(int idMaHoaDon) throws FileNotFoundException, IOException {
//    ArrayList<XemHoaDonTao> chiTietList = hd.getAllGioHang(idMaHoaDon);
//    DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
//    model.setRowCount(0);
//    
//    // Sử dụng Map để lưu trữ sản phẩm và cộng dồn số lượng nếu trùng tên sản phẩm
//    Map<String, XemHoaDonTao> productMap = new HashMap<>();
//
//    for (XemHoaDonTao chiTiet : chiTietList) {
//        String tenSanPham = chiTiet.getTenSanPham();
//        
//        if (productMap.containsKey(tenSanPham)) {
//            XemHoaDonTao existingItem = productMap.get(tenSanPham);
//            existingItem.setSoLuong(existingItem.getSoLuong() + chiTiet.getSoLuong());
//            existingItem.setGiaBan(existingItem.getGiaBan()); 
//        } else {
//            productMap.put(tenSanPham, chiTiet);
//        }
//    }
//    for (XemHoaDonTao chiTiet : productMap.values()) {
//        Object[] rowData = {
//            chiTiet.getMaSanPham(),
//            chiTiet.getTenSanPham(),
//            chiTiet.getSoLuong(),
//            chiTiet.getGiaBan(),
//            chiTiet.getSoLuong() * chiTiet.getGiaBan()
//        };
//        model.addRow(rowData);
//    }
//}
 
private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        if (webcam.isOpen()) {
            webcam.close();
        }
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        duyemWebcam.setLayout(new BorderLayout());
        duyemWebcam.add(panel, 0);
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                image = webcam.getImage();
                if (image == null) {
                    continue;
                }
            }
            try {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
                // Ignoring decoding errors
            }

            if (result != null) {
                handleQRCodeScan(result.getText());
            }
        } while (true);
    }
  private void handleQRCodeScan(String maSanPhamHoaDon) {
    String sql = "SELECT id_ma_san_pham, ten_san_pham, so_luong_ton, gia_ban FROM SanPham WHERE id_ma_san_pham = ?";
    try (Connection con = ketnoi.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, maSanPhamHoaDon);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int maSanPham = rs.getInt("id_ma_san_pham");
                String tenSanPham = rs.getString("ten_san_pham");
                int soLuongTon = rs.getInt("so_luong_ton");
                float giaBan = rs.getFloat("gia_ban");

                System.out.println("Sản phẩm: " + tenSanPham + ", Tồn kho: " + soLuongTon + ", Giá bán: " + giaBan);

                if (soLuongTon > 0) {
                    String input = JOptionPane.showInputDialog(
                            null,
                            "Nhập số lượng muốn thêm vào giỏ hàng (Tối đa " + soLuongTon + " sản phẩm):",
                            "Nhập số lượng",
                            JOptionPane.QUESTION_MESSAGE);
                    
                    if (input != null && !input.isEmpty()) {
                        System.out.println("Số lượng nhập vào: " + input); // Debugging value
                        try {
                            // Kiểm tra xem input có phải là số nguyên dương hay không
                            if (!input.matches("\\d+")) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ.");
                                return;
                            }

                            int soLuong = Integer.parseInt(input);
                            System.out.println("Số lượng: " + soLuong); // Debugging value

                            if (soLuong > 0 && soLuong <= soLuongTon) {
                                int maDonHang = Integer.parseInt(txtid.getText());
                                addProductToOrderDetailAndUpdatePaymentDuyem(maSanPham, soLuong, giaBan,maDonHang);
                               
                            } else {
                                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ! Tối đa: " + soLuongTon);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Lỗi khi nhập số lượng.");
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sản phẩm hiện không còn tồn kho!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã: " + maSanPhamHoaDon);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage());
        e.printStackTrace();
    }
}
private void addProductToOrderDetailAndUpdatePaymentDuyem(int maSanPham, int soLuong, float giaBan, int maDonHang) {
    int idDonHang = Integer.parseInt(txtid.getText());
    int maVoucher = -1; // Khởi tạo giá trị mặc định là không có voucher
    float tongTien = soLuong * giaBan; 

    // Câu lệnh để kiểm tra voucher hợp lệ
    String voucherSql = """
        SELECT TOP 1 id_voucher, gia_tri
        FROM event
        WHERE trang_thai = N'Đang hoạt động'
          AND ngay_bat_dau <= GETDATE()
          AND ngay_ket_thuc >= GETDATE()
        ORDER BY gia_tri DESC
    """;

    // Câu lệnh kiểm tra sản phẩm đã tồn tại trong ChiTietDonHang
    String checkExistSQL = "SELECT so_luong FROM ChiTietDonHang WHERE ma_san_pham = ? AND ma_don_hang = ?";
    
    // Câu lệnh thêm mới sản phẩm nếu chưa có, hoặc cập nhật nếu đã tồn tại
    String insertSQL = "INSERT INTO ChiTietDonHang (ma_san_pham, so_luong, gia_ban, tong_tien, ma_don_hang, ma_voucher) VALUES (?, ?, ?, ?, ?, ?)";
    String updateSQL = "UPDATE ChiTietDonHang SET so_luong = so_luong + ?, tong_tien = tong_tien + ? WHERE ma_san_pham = ? AND ma_don_hang = ?";
    
    // Cập nhật số lượng tồn kho trong bảng SanPham
    String updateStockSQL = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? WHERE id_ma_san_pham = ?";

    try (Connection con = ketnoi.getConnection()) {
        con.setAutoCommit(false); // Bắt đầu giao dịch

        // Kiểm tra voucher hợp lệ
        try (PreparedStatement psVoucher = con.prepareStatement(voucherSql)) {
            try (ResultSet rsVoucher = psVoucher.executeQuery()) {
                if (rsVoucher.next()) {
                    maVoucher = rsVoucher.getInt("id_voucher"); // Lấy id voucher
                    tongTien -= (tongTien * rsVoucher.getFloat("gia_tri") / 100); // Áp dụng giá trị voucher vào tổng tiền
                }
            }
        }
        try (PreparedStatement checkStmt = con.prepareStatement(checkExistSQL)) {
            checkStmt.setInt(1, maSanPham);
            checkStmt.setInt(2, idDonHang);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    try (PreparedStatement updateStmt2 = con.prepareStatement(updateSQL)) {
                        updateStmt2.setInt(1, soLuong); // Cộng thêm số lượng
                        updateStmt2.setFloat(2, tongTien); // Cộng thêm tổng tiền
                        updateStmt2.setInt(3, maSanPham);
                        updateStmt2.setInt(4, idDonHang);
                        updateStmt2.executeUpdate();
                    }
                } else {
                    try (PreparedStatement insertStmt = con.prepareStatement(insertSQL)) {
                        insertStmt.setInt(1, maSanPham);
                        insertStmt.setInt(2, soLuong);
                        insertStmt.setFloat(3, giaBan);
                        insertStmt.setFloat(4, tongTien);
                        insertStmt.setInt(5, idDonHang);
                        insertStmt.setObject(6, maVoucher == -1 ? null : maVoucher); // Thêm voucher nếu có
                        insertStmt.executeUpdate();
                    }
                }
            }
        }
        try (PreparedStatement updateStockStmt = con.prepareStatement(updateStockSQL)) {
            updateStockStmt.setInt(1, soLuong);
            updateStockStmt.setInt(2, maSanPham);
            updateStockStmt.executeUpdate();
        }
        con.commit();
        hienThiDuLieuGioHangg(idDonHang);
        hienThiDuLieuuu(ls.getSanPhamChhiTiet());
        apDungKhuyenMai();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm hoặc cập nhật tồn kho: " + e.getMessage());
    }
}


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "WebcamThread");
        t.setDaemon(true);
        return t;
    }

//    @Override
//    public void run() {
//        while (webcam != null && webcam.isOpen()) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            BufferedImage image = null;
//            if (webcam.isOpen()) {
//                image = webcam.getImage();
//                if (image == null) {
//                    continue;
//                }
//                try {
//                    LuminanceSource source = new BufferedImageLuminanceSource(image);
//                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                    Result result = new MultiFormatReader().decode(bitmap);
//
//                    if (result != null) {
//                        handleQRCodeScan(result.getText());
//                    }
//                } catch (Exception e) {
//                    // Xử lý ngoại lệ khi không quét được mã
//                }
//            }
//        }
//    }
//
//    private void handleQRCodeScan(String maSanPhamHoaDon) {
//        ArrayList<SanPhamEntity> ls = new ArrayList<>();
//        String sql = "SELECT ma_san_pham, ten_san_pham, so_luong_ton, gia_ban FROM SanPham WHERE ma_san_pham = ?";
//
//        try (Connection con = ketnoi.getConnection(); 
//             PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setObject(1, maSanPhamHoaDon);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    SanPhamEntity sanPham = new SanPhamEntity();
//                    sanPham.setMaSanPham(rs.getString("ma_san_pham"));
//                    sanPham.setTenSanPham(rs.getString("ten_san_pham"));
//                    sanPham.setSoLuong(rs.getInt("so_luong_ton"));
//                    sanPham.setGiaBan(rs.getFloat("gia_ban"));
//                    ls.add(sanPham);
//                } else {
//                    System.out.println("Không tìm thấy sản phẩm với mã: " + maSanPhamHoaDon);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        String input = JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm để thêm vào hóa đơn:", "Số lượng sản phẩm", JOptionPane.QUESTION_MESSAGE);
//        try {
//            int soLuongg = Integer.parseInt(input);
//            if (soLuongg <= 0) {
//                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
//                return;
//            }
//            addAllProductsToOrder(ls, soLuongg);
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
//        }
//    }
//
//    private void addAllProductsToOrder(ArrayList<SanPhamEntity> ls, int soLuongg) {
//        for (SanPhamEntity sanPham : ls) {
//            int maSanPham = Integer.parseInt(sanPham.getMaSanPham().replace("SP", ""));
//            String tenSanPham = sanPham.getTenSanPham();
//            float giaBan = sanPham.getGiaBan();
//            if (soLuongg > 0) {
//                addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuongg);
//                updateProductQuantity(maSanPham, soLuongg);
//            } else {
//                JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0.");
//            }
//        }
//    }
//
//    private void updateProductQuantity(int maSanPham, int soLuong) {
//        String sql = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? WHERE ma_san_pham = ?";
//        try (Connection con = ketnoi.getConnection(); 
//             PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setInt(1, soLuong);
//            stmt.setInt(2, maSanPham);
//            int rowsAffected = stmt.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Cập nhật số lượng tồn kho thành công.");
//            } else {
//                System.out.println("Không thể cập nhật số lượng tồn kho.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Thread newThread(Runnable r) {
//        Thread t = new Thread(r, "WebcamThread");
//        t.setDaemon(true);
//        return t;
//    }
//    private void timkiem() {
//        String tenSanPham = locSanPham.getText();
//        String trangThai = (String) txtLocMauSac.getSelectedItem();
//        ArrayList<SanPhamEntity> sc = ls.timKiemVaLocSanPham(trangThai, tenSanPham);
//        hienThiTrang(sc);
//    }
    private void thuocTinhKichCo() {
        ArrayList<KichCoEnTity> ls = new ArrayList<>();
        String sql = """
                 select kich_co, mo_ta
                 from kichCo
                 """;
        try {
            // Làm trống combobox trước khi thêm dữ liệu mới
            txtlockichco1.removeAllItems();
            txtlockichco1.addItem("");
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCoEnTity kc = new KichCoEnTity();
                kc.setKichCo(rs.getString("kich_co"));
                kc.setMoTa(rs.getString("mo_ta"));
                ls.add(kc);
                String kichCo = rs.getString("kich_co");
                txtlockichco1.addItem(kichCo);
            }
            txtlockichco1.revalidate();
            txtlockichco1.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void thuocTinhMauSac() {
        ArrayList<MauSacEntity> ls = new ArrayList<>();
        String sql = """
                 select mau_sac_san_pham, mo_ta
                 from mauSac 
                 """;
        try {
            // Làm trống combobox trước khi thêm dữ liệu mới
            txtLocMauSac.removeAllItems();
            txtLocMauSac.addItem("");
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSacEntity ms = new MauSacEntity();
                ms.setMauSac(rs.getString("mau_sac_san_pham"));
                ms.setMoTa(rs.getString("mo_ta"));
                ls.add(ms);
                String mauSac = rs.getString("mau_sac_san_pham");
                txtLocMauSac.addItem(mauSac);
            }
            txtLocMauSac.revalidate();
            txtLocMauSac.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void thuocTinhThuongHieu() {
        ArrayList<ThuongHieuEntity> ls = new ArrayList<>();
        String sql = """
                 select ten_thuong_hieu, mo_ta
                 from thuongHieu
                 """;
        try {
            thuonghieu.removeAllItems();
            thuonghieu.addItem("");
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieuEntity cl = new ThuongHieuEntity();
                cl.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
                cl.setMoTa(rs.getString("mo_ta"));
                ls.add(cl);
                String thuongHieuu = rs.getString("ten_thuong_hieu");
                thuonghieu.addItem(thuongHieuu);
            }
            thuonghieu.revalidate();
            thuonghieu.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txttranng = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_sanPham1 = new javax.swing.JTable();
        duyemWebcam = new javax.swing.JPanel();
        dd = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbldanhsachdonhagcho = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel_DonHang1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbo_hinhThucThanhToan = new javax.swing.JComboBox<>();
        txt_tienKhachDua = new javax.swing.JTextField();
        txtTienTraKhac = new javax.swing.JLabel();
        txtthanhtien = new javax.swing.JLabel();
        txttongSoTien = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JLabel();
        txtthue = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JLabel();
        txtsodienthoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_taoma = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txtidkhachhang = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtdonhang = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JLabel();
        txtngaytao = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        chuyenkhoan = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        thuonghieu = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtLocMauSac = new javax.swing.JComboBox<>();
        txtLocTenSanPham1 = new javax.swing.JTextField();
        txtlockichco1 = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        hienthigiohang = new javax.swing.JTable();
        xoaTatCa = new javax.swing.JCheckBox();

        tbl_sanPham.setBackground(new java.awt.Color(242, 242, 242));
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
        tbl_sanPham.getAccessibleContext().setAccessibleName("Danh Sách Sản Phẩm");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrows.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrows (1).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txttranng.setFont(new java.awt.Font("UTM Seagull", 1, 18)); // NOI18N

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_sanPham1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_sanPham1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Sản Phẩm", "Kích Thước", "Máu Sắc", "Thương Hiệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPham1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_sanPham1);

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 710, 200));

        duyemWebcam.setBackground(new java.awt.Color(255, 255, 255));
        duyemWebcam.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.add(duyemWebcam, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 210, 100));

        dd.setBackground(new java.awt.Color(255, 255, 255));
        dd.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ĐƠN HÀNG CHỜ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tbldanhsachdonhagcho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã Đơn Hàng", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập", "Trạng Thái"
            }
        ));
        tbldanhsachdonhagcho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldanhsachdonhagchoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbldanhsachdonhagcho);

        javax.swing.GroupLayout ddLayout = new javax.swing.GroupLayout(dd);
        dd.setLayout(ddLayout);
        ddLayout.setHorizontalGroup(
            ddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
        );
        ddLayout.setVerticalGroup(
            ddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ddLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 710, 210));

        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 80, -1));

        jButton5.setText("Cập Nhập ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 100, -1));

        jPanel_DonHang1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_DonHang1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_DonHang1.setForeground(new java.awt.Color(242, 242, 242));
        jPanel_DonHang1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tổng Tiền:");
        jPanel_DonHang1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Giảm Giá:");
        jPanel_DonHang1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Thanh Toán:");
        jPanel_DonHang1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tiền Khách Đưa:");
        jPanel_DonHang1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tiền Thừa Trả Khách:");
        jPanel_DonHang1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Hình Thức Thanh Toán:");
        jPanel_DonHang1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Thuế");
        jPanel_DonHang1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 37, -1));

        cbo_hinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản", "Cả Hai" }));
        cbo_hinhThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_hinhThucThanhToanActionPerformed(evt);
            }
        });
        jPanel_DonHang1.add(cbo_hinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 190, -1));

        txt_tienKhachDua.setBackground(new java.awt.Color(242, 242, 242));
        txt_tienKhachDua.setText(" 0");
        txt_tienKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tienKhachDuaMouseClicked(evt);
            }
        });
        txt_tienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienKhachDuaActionPerformed(evt);
            }
        });
        jPanel_DonHang1.add(txt_tienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 160, -1));

        txtTienTraKhac.setText("0");
        jPanel_DonHang1.add(txtTienTraKhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 149, -1));

        txtthanhtien.setText("0");
        jPanel_DonHang1.add(txtthanhtien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 153, -1));

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
        jPanel_DonHang1.add(txttongSoTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 153, -1));

        jLabel19.setText("VNĐ");
        jPanel_DonHang1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, -1, -1));

        jLabel20.setText("VNĐ");
        jPanel_DonHang1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, -1));

        jLabel21.setText("VNĐ");
        jPanel_DonHang1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, -1, -1));

        jLabel22.setText("VNĐ");
        jPanel_DonHang1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, -1, 40));

        jLabel23.setText("VNĐ");
        jPanel_DonHang1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, -1, -1));

        txtGiamGia.setText("0");
        jPanel_DonHang1.add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 153, -1));
        jPanel_DonHang1.add(txtthue, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 590, 150, 20));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Nhập Số Điện Thoại:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên Khách Hàng:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, -1));

        txtTenKhachHang.setForeground(new java.awt.Color(255, 0, 0));
        txtTenKhachHang.setText("Khách Bán Lẻ");
        txtTenKhachHang.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtTenKhachHangAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel5.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        txtsodienthoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsodienthoaiActionPerformed(evt);
            }
        });
        jPanel5.add(txtsodienthoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 210, -1));

        jLabel7.setText("ID Khách Hàng:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        btn_taoma.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_taoma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        btn_taoma.setText("Tạo");
        btn_taoma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_taomaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_taomaMouseEntered(evt);
            }
        });
        jPanel5.add(btn_taoma, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 90, 30));

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton12.setText("Huỷ Đơn Hàng");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, 30));

        txtidkhachhang.setForeground(new java.awt.Color(255, 0, 0));
        txtidkhachhang.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtidkhachhangAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel5.add(txtidkhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 100, 20));

        jPanel_DonHang1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 32, 350, 170));

        jButton14.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/payment.png"))); // NOI18N
        jButton14.setText("THANH TOÁN");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel_DonHang1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, 250, 60));

        jLabel3.setText("Tên nhân viên:");
        jPanel_DonHang1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, -1));

        jLabel8.setText("Ngày Tạo");
        jPanel_DonHang1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 90, 40));

        jLabel2.setText("ID");
        jPanel_DonHang1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, -1));

        txtid.setForeground(new java.awt.Color(255, 0, 102));
        txtid.setText("0");
        txtid.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtidAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel_DonHang1.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 133, -1));

        jLabel1.setText("Đơn Hàng");
        jPanel_DonHang1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 100, -1));

        txtdonhang.setBackground(new java.awt.Color(255, 0, 0));
        txtdonhang.setForeground(new java.awt.Color(255, 0, 0));
        txtdonhang.setText("0");
        txtdonhang.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtdonhangAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel_DonHang1.add(txtdonhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 133, -1));

        txtTenNhanVien.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtTenNhanVienAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel_DonHang1.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 150, 19));

        txtngaytao.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtngaytaoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel_DonHang1.add(txtngaytao, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 140, 22));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Chuyển Khoản");
        jPanel_DonHang1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 100, 30));

        chuyenkhoan.setText("0");
        jPanel_DonHang1.add(chuyenkhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 620, 150, 20));

        jPanel2.add(jPanel_DonHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 390, 730));

        jLabel26.setFont(new java.awt.Font("Source Sans Pro Black", 1, 14)); // NOI18N
        jLabel26.setText("Tên Sản Phẩm");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 30));

        jLabel27.setFont(new java.awt.Font("Source Sans Pro Black", 1, 14)); // NOI18N
        jLabel27.setText("Lọc Thương Hiệu");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, 20));

        thuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "      ", "Adidas", "Puma", "Nike", "Reebok", "Under Armour" }));
        thuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thuonghieuActionPerformed(evt);
            }
        });
        jPanel2.add(thuonghieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 170, 31));

        jLabel18.setFont(new java.awt.Font("Source Sans Pro Black", 1, 14)); // NOI18N
        jLabel18.setText("Lọc Kích Cỡ");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, 20));

        jLabel4.setFont(new java.awt.Font("Source Sans Pro Black", 1, 14)); // NOI18N
        jLabel4.setText("Lọc Màu Sắc");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 20));

        txtLocMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "         " }));
        txtLocMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocMauSacActionPerformed(evt);
            }
        });
        jPanel2.add(txtLocMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 142, 31));

        txtLocTenSanPham1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocTenSanPham1KeyReleased(evt);
            }
        });
        jPanel2.add(txtLocTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 140, 30));

        txtlockichco1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "         " }));
        txtlockichco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlockichco1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtlockichco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 170, 30));

        hienthigiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID ", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Số Lượng"
            }
        ));
        hienthigiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hienthigiohangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(hienthigiohang);
        if (hienthigiohang.getColumnModel().getColumnCount() > 0) {
            hienthigiohang.getColumnModel().getColumn(0).setPreferredWidth(10);
            hienthigiohang.getColumnModel().getColumn(1).setPreferredWidth(10);
            hienthigiohang.getColumnModel().getColumn(2).setPreferredWidth(10);
            hienthigiohang.getColumnModel().getColumn(3).setPreferredWidth(10);
            hienthigiohang.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 710, 150));

        xoaTatCa.setText("All");
        jPanel2.add(xoaTatCa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, 50, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Sản Phẩm");
    }// </editor-fold>//GEN-END:initComponents

    private void txttongSoTienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txttongSoTienAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongSoTienAncestorAdded

    private void btn_taomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_taomaMouseClicked
        int idNhanVien = LuuThongTinDangNhap.getInNhanVien();
        int maKhachHangg = 6;
       String tenKhachHang = "Bán Tại Quầy";
        if(txtTenKhachHang.getText().equals(tenKhachHang)||txtTenKhachHang.getText().equals("Khách Bán Lẻ")){
            String trangThaiDonHnag = "Đang chờ";
        String sqlDonHang = """
        INSERT INTO DonHang (ngay_dat, ma_nhan_vien, ma_khach_hang,trang_thai)
        VALUES (GETDATE(), ?, ?,?)
        """;
        try (Connection con = ketnoi.getConnection()) {

            try (PreparedStatement psDonHang = con.prepareStatement(sqlDonHang)) {
                psDonHang.setInt(1, idNhanVien);
                psDonHang.setInt(2, maKhachHangg);
                psDonHang.setString(3, trangThaiDonHnag);
                int rowsAffectedDonHang = psDonHang.executeUpdate();
                con.commit();
                loadDuLieu();
                hienThiDuLieu(dh.layHhoaDon());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(panel, "Đăng Nhập Để Tạo Hóa Đơn");
            e.printStackTrace();
        }
        }else{
             int maKhachHang = Integer.parseInt(txtidkhachhang.getText());
        String trangThaiDonHnag = "Đang chờ";
        String sqlDonHang = """
        INSERT INTO DonHang (ngay_dat, ma_nhan_vien, ma_khach_hang,trang_thai)
        VALUES (GETDATE(), ?, ?,?)
        """;
        try (Connection con = ketnoi.getConnection()) {
            try (PreparedStatement psDonHang = con.prepareStatement(sqlDonHang)) {
                psDonHang.setInt(1, idNhanVien);
                psDonHang.setInt(2, maKhachHang);
                psDonHang.setString(3, trangThaiDonHnag);
                int rowsAffectedDonHang = psDonHang.executeUpdate();
                con.commit();
                loadDuLieu();
                hienThiDuLieu(dh.layHhoaDon());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(panel, "Đăng Nhập Để Tạo Hóa Đơn");
            e.printStackTrace();
        }
        }
    }//GEN-LAST:event_btn_taomaMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

       int idmaDonHnag = Integer.parseInt(txtid.getText());
String trangThaiHoaDon = "Đang Chờ Thanh Toán";
String rawValue = txtthanhtien.getText().trim();
String numericValue = rawValue.replaceAll("[^\\d.]", "");
float thanhTien = Float.parseFloat(numericValue);
double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText().trim().replace(",", ""));
String rawValuee = txtTienTraKhac.getText().trim();
String numericValuee = rawValuee.replaceAll("[^\\d.]", "");
double tienTraKhach = Double.parseDouble(numericValuee);
String phuongThuc = cbo_hinhThucThanhToan.getSelectedItem().toString();
String rawValueee = chuyenkhoan.getText().trim();
String numericValueee = rawValueee.replaceAll("[^\\d.]", "");
double chuyenKhoan = Double.parseDouble(numericValueee);
String giamGia = txtGiamGia.getText().trim();
String chuyenGiamGia = giamGia.replaceAll("[^\\d.]", "");
double giamGiatxt = Double.parseDouble(chuyenGiamGia);
       String sqlInsert = """
    INSERT INTO hoadon (ngay_lap, tien_khach_dua, tien_tra_khach, phuong_thuc, trang_thai, thanh_tien, ma_don_hang, tien_chuyen_khoan, giam_gia)
    VALUES (GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?)
""";
String sqlUpdate = """
    UPDATE hoadon
    SET tien_khach_dua = ?, tien_tra_khach = ?, phuong_thuc = ?, thanh_tien = ?, tien_chuyen_khoan = ?, giam_gia = ?
    WHERE ma_don_hang = ?
""";
try (Connection con = ketnoi.getConnection()) {
    // Insert hóa đơn
    try (PreparedStatement psHoaDon = con.prepareStatement(sqlInsert)) {
        psHoaDon.setObject(1, tienKhachDua);
        psHoaDon.setObject(2, tienTraKhach);
        psHoaDon.setObject(3, phuongThuc);
        psHoaDon.setObject(4, trangThaiHoaDon);
        psHoaDon.setObject(5, thanhTien);
        psHoaDon.setObject(6, idmaDonHnag);
        psHoaDon.setObject(7, chuyenKhoan);
        psHoaDon.setObject(8, giamGiatxt);
        int checkHoaDon = psHoaDon.executeUpdate();
        if (checkHoaDon > 0) {
            try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                psUpdate.setObject(1, tienKhachDua);
                psUpdate.setObject(2, tienTraKhach);
                psUpdate.setObject(3, phuongThuc);
                psUpdate.setObject(4, thanhTien);
                psUpdate.setObject(5, chuyenKhoan);
                psUpdate.setObject(6, giamGiatxt);
                psUpdate.setObject(7, idmaDonHnag);

                int updatedRows = psUpdate.executeUpdate();
                if (updatedRows > 0) {
                    if (phuongThuc.equals("Tiền Mặt")) {
                        XuatHoaDonnn xuatHoaDon = new XuatHoaDonnn();
                        xuatHoaDon.setVisible(true);
                    } else if (phuongThuc.equals("Chuyển Khoản")) {
                        XuatHoaDonTChuyenKhoan xuatHoaDon = new XuatHoaDonTChuyenKhoan();
                        xuatHoaDon.setVisible(true);
                    }else{
                        XuatHoaDonCaHai caHai = new XuatHoaDonCaHai();
                        caHai.setVisible(true);
                    }
                    hienThiDuLieu(dh.layHhoaDon());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
        }
    }
    
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xử lý hóa đơn!");
}
    }//GEN-LAST:event_jButton14ActionPerformed

//private void addProductAndUpdatePayment(int maSanPham, String tenSanPham, double giaBan, int soLuong) {
//    double tongTienSanPham = giaBan * soLuong; // Tính tổng tiền của sản phẩm mới
//    float thue = 0;
//    // SQL để thêm sản phẩm vào ChiTietDonHang
//    String sqlInsert = """
//        INSERT INTO ChiTietDonHang (ma_san_pham, so_luong, gia_ban, tong_tien, ma_voucher, trang_thai)
//        VALUES (?, ?, ?, ?, ?, ?)
//    """;
//
//    // SQL để tính tổng số lượng và tổng tiền các sản phẩm "Đang chờ"
//    String sqlSum = "SELECT SUM(so_luong), SUM(tong_tien), thue FROM ChiTietDonHang WHERE trang_thai = N'Đang chờ' group by thue";
//
//    try (Connection con = ketnoi.getConnection()) {
//        // Thêm sản phẩm vào bảng ChiTietDonHang
//        try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
//            psInsert.setInt(1, maSanPham);      // Mã sản phẩm
//            psInsert.setInt(2, soLuong);        // Số lượng
//            psInsert.setDouble(3, giaBan);      // Giá bán
//            psInsert.setDouble(4, tongTienSanPham); // Tổng tiền của sản phẩm
//            psInsert.setObject(5, null, Types.INTEGER); // Mã voucher (null nếu không có)
//            psInsert.setString(6, "Đang chờ");  // Trạng thái ban đầu
//
//            int rowsAffected = psInsert.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Sản phẩm đã được thêm vào đơn hàng chi tiết.");
//
//                // Sau khi thêm sản phẩm, tính tổng số lượng và tổng tiền để cập nhật giao diện
//                try (PreparedStatement psSum = con.prepareStatement(sqlSum)) {
//                    ResultSet rs = psSum.executeQuery();
//                    if (rs.next()) {
//                        int totalQuantity = rs.getInt(1);       // Tổng số lượng
//                        double totalAmount = rs.getDouble(2);   // Tổng tiền chưa giảm giá và thuế
//       double voucherDiscountPercent=0;
//         double taxPercent=0;
//                        // Tính giảm giá từ voucher
//                        double voucherDiscount = totalAmount * (voucherDiscountPercent / 100);
//                        txtGiamGia.setText("Giảm Giá: " + voucherDiscount + " VNĐ");
//                        double taxAmount = totalAmount * (taxPercent / 100);
//                        txtthue.setText("Thuế: " + taxAmount + " VNĐ");
//
//                        // Tính thành tiền sau khi trừ voucher và cộng thuế
//                        double finalAmount = totalAmount - voucherDiscount + taxAmount;
//                        txttongSoTien.setText("Tổng Tiền: " + totalAmount + " VNĐ");
//                        txtthanhtien.setText("Thành Tiền: " + finalAmount + " VNĐ");
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//                System.out.println("Lỗi khi thêm sản phẩm vào đơn hàng chi tiết.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
//     private void kiemTraTongTien() {
//        try {
//            // Lấy giá trị từ txtTongTien
//            String tongTienText = txttongSoTien.getText();
//            if (tongTienText.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Vui lòng nhập tổng tiền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            // Chuyển đổi sang kiểu số
//            double tongTien = Double.parseDouble(tongTienText);
//
//            // Kiểm tra tổng tiền
//            if (tongTien > 1000000) {
//                JOptionPane.showMessageDialog(this, "Áp dụng khuyến mãi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                // Thực hiện logic áp dụng khuyến mãi
//                apDungKhuyenMai();
//            } else {
//                JOptionPane.showMessageDialog(this, "Tổng tiền chưa đủ để áp dụng khuyến mãi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
private void apDungKhuyenMai() {
    try {
        double tongTien = Double.parseDouble(txttongSoTien.getText().trim().replace(",", "")); // Loại bỏ dấu phẩy
        if (tongTien > 1000000) {
            String sqlVoucher = """
               SELECT TOP 1 id_voucher, gia_tri, loai_gia_tri
                            FROM event
                            WHERE trang_thai = N'Đang hoạt động'
                              AND ngay_bat_dau <= GETDATE()
                              AND ngay_ket_thuc >= GETDATE()
                            ORDER BY gia_tri DESC
            """;
            try (Connection con = ketnoi.getConnection(); PreparedStatement psVoucher = con.prepareStatement(sqlVoucher)) {
                ResultSet rs = psVoucher.executeQuery();
                if (rs.next()) {
                    double giaTriVoucher = rs.getDouble("gia_tri");  
                    String loaiGiaTri = rs.getString("loai_gia_tri"); 
                    double giamGia = 0;
                    if ("Giảm Theo %".equals(loaiGiaTri)) {
                        giamGia = tongTien * (giaTriVoucher / 100.0);
                    } else if ("Giảm Tiền Trực Tiếp".equals(loaiGiaTri)) {
                        giamGia = giaTriVoucher;
                    }
                    double thue =tongTien * 0.1;
                        tongTien += thue;
                    tongTien -= giamGia;
                        if (tongTien < 0) {
                            tongTien = 0;
                        }
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        txtthanhtien.setText("Thành Tiền: " + formatter.format(tongTien) + " VND");
                        txtGiamGia.setText("Giảm Giá: " + formatter.format(giamGia) + " VND");
                        txtthue.setText("Thuế: " + formatter.format(thue) + " VND");
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

                            private void capNhatTienThua() {
                                String tienKhachDuaStr = txt_tienKhachDua.getText().trim().replace(",", "");
                                try {
                                    double tienKhachDua = 0;
                                    if (!tienKhachDuaStr.isEmpty()) {
                                        tienKhachDua = Double.parseDouble(tienKhachDuaStr);
                                    }
                                    double tongTien = Double.parseDouble(txtthanhtien.getText().replace("Thành Tiền: ", "").replace(" VND", "").replace(",", "").trim());
                                    double tienThua = tienKhachDua - tongTien;
                                    DecimalFormat formatter = new DecimalFormat("#,###,###");

                                    if (tienThua < 0) {
                                        txtTienTraKhac.setText("Tiền trả lại: " + formatter.format(tienThua) + " VND");
                                    } else {
                                        txtTienTraKhac.setText("Tiền trả lại: " + formatter.format(tienThua) + " VND");
                                    }
                                } catch (NumberFormatException e) {
                                }
                            }
                        });

                    } else {
//                    JOptionPane.showMessageDialog(this, "Không có voucher hợp lệ để áp dụng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Lỗi khi áp dụng voucher: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
            }
        } catch (NumberFormatException ex) {
        }
    }

    private void updateCartAndRecalculateVoucher() {
        int maDonHang = Integer.parseInt(txtid.getText());
        String paymentInfoSql = """
        SELECT 
            SUM(tong_tien) AS tong_tien, 
            SUM(tong_tien * thue / 100) AS tong_thue
        FROM ChiTietDonHang
        WHERE ma_don_hang = ?
    """;
        String voucherSql = """
        SELECT TOP 1 id_voucher, gia_tri, loai_gia_tri
        FROM event
        WHERE trang_thai = N'Chưa bắt đầu'
          AND GETDATE() BETWEEN ngay_bat_dau AND ngay_ket_thuc
    """;
    try (Connection con = ketnoi.getConnection()) {
        try (PreparedStatement psPaymentInfo = con.prepareStatement(paymentInfoSql); 
             PreparedStatement psVoucher = con.prepareStatement(voucherSql)) {
            psPaymentInfo.setInt(1, maDonHang);
            ResultSet rs = psPaymentInfo.executeQuery();
                if (rs.next()) {
                float totalAmount = rs.getFloat("tong_tien");
                float totalTax = rs.getFloat("tong_thue");
                float giamGia = 0;
                ResultSet rsVoucher = psVoucher.executeQuery();
                if (rsVoucher.next()) {
                    String loaiGiaTri = rsVoucher.getString("loai_gia_tri");
                    float giaTriVoucher = rsVoucher.getFloat("gia_tri");
                    if ("Giảm Theo %".equals(loaiGiaTri)) {
                        giamGia += totalAmount * (giaTriVoucher / 100);
                    } else if ("Giảm Tiền Trực Tiếp".equals(loaiGiaTri)) {
                        giamGia += giaTriVoucher;
                    }
                }
                float finalAmount = totalAmount - giamGia + totalTax;
                DecimalFormat formatter = new DecimalFormat("#,###.##");
                txttongSoTien.setText("Tổng Tiền: " + formatter.format(totalAmount));
                txtGiamGia.setText("Giảm Giá: " + formatter.format(giamGia) + " VND");
                txtthue.setText("Thuế: " + formatter.format(totalTax));
                txtthanhtien.setText("Thành Tiền: " + formatter.format(finalAmount));
            }

        } catch (SQLException e) {
            // Xử lý lỗi nếu có khi thực thi câu truy vấn
            e.printStackTrace();
        }
    } catch (SQLException e) {
        // Xử lý lỗi kết nối cơ sở dữ liệu
        e.printStackTrace();
    }
}



    private void removeProductFromCart(int maSanPham, int soLuong) {
        int maDonHang = Integer.parseInt(txtid.getText());
        String deleteSql = """
    DELETE FROM ChiTietDonHang
    WHERE ma_don_hang = ? AND ma_san_pham = ?
    """;

        try (Connection con = ketnoi.getConnection()) {
            try (PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                // Xóa sản phẩm khỏi giỏ hàng
                psDelete.setInt(1, maDonHang);
                psDelete.setInt(2, maSanPham);
                int rowsAffected = psDelete.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
                    updateCartAndRecalculateVoucher(); // Tự động tính lại voucher khi xóa sản phẩm
                } else {
                    System.out.println("Lỗi khi xóa sản phẩm khỏi giỏ hàng.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void addProductToOrderDetailAndUpdatePayment(int maSanPham, String tenSanPham, float giaBan, int soLuong) {
    int maDonHang = Integer.parseInt(txtid.getText());
    float tongTien = giaBan * soLuong;
    int maVoucher = -1; // Khởi tạo giá trị mặc định nếu không có voucher

    // SQL statements
    String checkExistSql = """
        SELECT so_luong FROM ChiTietDonHang 
        WHERE ma_don_hang = ? AND ma_san_pham = ?
    """;
    String insertSql = """
        INSERT INTO ChiTietDonHang (ma_don_hang, ma_san_pham, so_luong, gia_ban, tong_tien, thue, ma_voucher)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;
    String updateSql = """
        UPDATE SanPham
        SET so_luong_ton = so_luong_ton - ?
        WHERE id_ma_san_pham = ?
    """;
    String updateDetailSql = """
        UPDATE ChiTietDonHang
        SET so_luong = ?, tong_tien = ?
        WHERE ma_don_hang = ? AND ma_san_pham = ?
    """;
    String paymentInfoSql = """
        SELECT 
            SUM(tong_tien) AS tong_tien, 
            SUM(tong_tien * thue / 100) AS tong_thue
        FROM ChiTietDonHang
        WHERE ma_don_hang = ?
    """;
    String voucherSql = """
        SELECT TOP 1 id_voucher 
        FROM event
        WHERE trang_thai = N'Đang hoạt động'
          AND ngay_bat_dau <= GETDATE()
          AND ngay_ket_thuc >= GETDATE()
        ORDER BY gia_tri DESC
    """;

    try (Connection con = ketnoi.getConnection()) {
        con.setAutoCommit(false);

        try (PreparedStatement psVoucher = con.prepareStatement(voucherSql);
             PreparedStatement psInsert = con.prepareStatement(insertSql);
             PreparedStatement psUpdate = con.prepareStatement(updateSql);
             PreparedStatement psCheckExist = con.prepareStatement(checkExistSql);
             PreparedStatement psUpdateDetail = con.prepareStatement(updateDetailSql);
             PreparedStatement psPaymentInfo = con.prepareStatement(paymentInfoSql)) {

            // Kiểm tra nếu sản phẩm đã có trong chi tiết đơn hàng
            psCheckExist.setInt(1, maDonHang);
            psCheckExist.setInt(2, maSanPham);
            ResultSet rsExist = psCheckExist.executeQuery();
            boolean isProductExist = rsExist.next();
            int existingQuantity = isProductExist ? rsExist.getInt("so_luong") : 0;

            // Lấy voucher nếu có
            try (ResultSet rsVoucher = psVoucher.executeQuery()) {
                if (rsVoucher.next()) {
                    maVoucher = rsVoucher.getInt("id_voucher");
                }
            }

            if (isProductExist) {
                // Cập nhật số lượng và tổng tiền nếu sản phẩm đã có trong chi tiết đơn hàng
                int newQuantity = existingQuantity + soLuong;
                float newTongTien = giaBan * newQuantity;
                psUpdateDetail.setInt(1, newQuantity);
                psUpdateDetail.setFloat(2, newTongTien);
                psUpdateDetail.setInt(3, maDonHang);
                psUpdateDetail.setInt(4, maSanPham);

                // Nếu cập nhật chi tiết thành công, thực hiện tiếp các bước sau
                if (psUpdateDetail.executeUpdate() > 0) {
                    // Cập nhật số lượng tồn kho
                    psUpdate.setInt(1, soLuong);
                    psUpdate.setInt(2, maSanPham);
                    if (psUpdate.executeUpdate() > 0) {
                        // Lấy thông tin thanh toán
                        psPaymentInfo.setInt(1, maDonHang);
                        try (ResultSet rsPayment = psPaymentInfo.executeQuery()) {
                            if (rsPayment.next()) {
                                float totalAmount = rsPayment.getFloat("tong_tien");
                                float totalTax = rsPayment.getFloat("tong_thue");
                                float finalAmount = totalAmount + totalTax;
                                DecimalFormat formatter = new DecimalFormat("#,###.##");
                                txttongSoTien.setText("Tổng Tiền: " + formatter.format(totalAmount));
                                txtthue.setText("Thuế: " + formatter.format(totalTax));
                                txtthanhtien.setText("Thành Tiền: " + formatter.format(finalAmount));
                            }
                        }
                        con.commit();
                    } else {
                        con.rollback();
                        System.out.println("Lỗi khi cập nhật số lượng sản phẩm.");
                    }
                } else {
                    con.rollback();
                    System.out.println("Lỗi khi cập nhật chi tiết đơn hàng.");
                }
            } else {
                // Nếu sản phẩm chưa có trong chi tiết đơn hàng, thêm mới
                psInsert.setInt(1, maDonHang);
                psInsert.setInt(2, maSanPham);
                psInsert.setInt(3, soLuong);
                psInsert.setFloat(4, giaBan);
                psInsert.setFloat(5, tongTien);
                psInsert.setDouble(6, 10.0); // Thuế giả định là 10%
                psInsert.setObject(7, maVoucher == -1 ? null : maVoucher);

                // Nếu thêm thành công, cập nhật số lượng tồn kho và thông tin thanh toán
                if (psInsert.executeUpdate() > 0) {
                    psUpdate.setInt(1, soLuong);
                    psUpdate.setInt(2, maSanPham);
                    if (psUpdate.executeUpdate() > 0) {
                        psPaymentInfo.setInt(1, maDonHang);
                        try (ResultSet rsPayment = psPaymentInfo.executeQuery()) {
                            if (rsPayment.next()) {
                                float totalAmount = rsPayment.getFloat("tong_tien");
                                float totalTax = rsPayment.getFloat("tong_thue");
                                float finalAmount = totalAmount + totalTax;
                                DecimalFormat formatter = new DecimalFormat("#,###.##");
                                txttongSoTien.setText("Tổng Tiền: " + formatter.format(totalAmount));
                                txtthue.setText("Thuế: " + formatter.format(totalTax));
                                txtthanhtien.setText("Thành Tiền: " + formatter.format(finalAmount));
                                apDungKhuyenMai();
                            }
                        }
                        con.commit();
                    } else {
                        con.rollback();
                        System.out.println("Lỗi khi cập nhật số lượng sản phẩm.");
                    }
                } else {
                    con.rollback();
                    System.out.println("Lỗi khi thêm sản phẩm vào đơn hàng chi tiết.");
                }
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

//private void addProductToOrderDetailAndUpdatePayment(int maSanPham, String tenSanPham, float giaBan, int soLuong) {
//int maDonHang = Integer.parseInt(txtid.getText());
//float tongTien = giaBan * soLuong;
//int maVoucher = -1; // Khởi tạo giá trị mặc định nếu không có voucher
//
//// SQL statements
//String insertSql = """
//    INSERT INTO ChiTietDonHang (ma_don_hang, ma_san_pham, so_luong, gia_ban, tong_tien, thue, ma_voucher)
//    VALUES (?, ?, ?, ?, ?, ?, ?)
//""";
//String updateSql = """
//    UPDATE SanPham
//    SET so_luong_ton = so_luong_ton - ?
//    WHERE id_ma_san_pham = ?
//""";
//String paymentInfoSql = """
//    SELECT 
//        SUM(tong_tien) AS tong_tien, 
//        SUM(tong_tien * thue / 100) AS tong_thue
//    FROM ChiTietDonHang
//    WHERE ma_don_hang = ?
//""";
//String voucherSql = """
//    SELECT TOP 1 id_voucher 
//    FROM event
//    WHERE trang_thai = N'Đang hoạt động'
//      AND ngay_bat_dau <= GETDATE()
//      AND ngay_ket_thuc >= GETDATE()
//    ORDER BY gia_tri DESC
//""";
//
//try (Connection con = ketnoi.getConnection()) {
//    con.setAutoCommit(false);
//
//    try (PreparedStatement psVoucher = con.prepareStatement(voucherSql);
//         PreparedStatement psInsert = con.prepareStatement(insertSql);
//         PreparedStatement psUpdate = con.prepareStatement(updateSql);
//         PreparedStatement psPaymentInfo = con.prepareStatement(paymentInfoSql)) {
//        try (ResultSet rsVoucher = psVoucher.executeQuery()) {
//            if (rsVoucher.next()) {
//                maVoucher = rsVoucher.getInt("id_voucher");
//            }
//        }
//
//        psInsert.setInt(1, maDonHang);
//        psInsert.setInt(2, maSanPham); // maSanPham được truyền từ bên ngoài
//        psInsert.setInt(3, soLuong);
//        psInsert.setFloat(4, giaBan);
//        psInsert.setFloat(5, tongTien);
//        psInsert.setDouble(6, 10.0); // Thuế giả định là 10%
//        psInsert.setObject(7, maVoucher == -1 ? null : maVoucher); // Nếu không có voucher, dùng null
//
//        if (psInsert.executeUpdate() > 0) {
//            // Cập nhật số lượng tồn kho
//            psUpdate.setInt(1, soLuong);
//            psUpdate.setInt(2, maSanPham);
//
//            if (psUpdate.executeUpdate() > 0) {
//                // Lấy thông tin thanh toán
//                psPaymentInfo.setInt(1, maDonHang);
//                try (ResultSet rsPayment = psPaymentInfo.executeQuery()) {
//                    if (rsPayment.next()) {
//                        float totalAmount = rsPayment.getFloat("tong_tien");
//                        float totalTax = rsPayment.getFloat("tong_thue");
//                        float finalAmount = totalAmount + totalTax;
//                        DecimalFormat formatter = new DecimalFormat("#,###.##");
//                        txttongSoTien.setText("Tổng Tiền: " + formatter.format(totalAmount));
//                        txtthue.setText("Thuế: " + formatter.format(totalTax));
//                        txtthanhtien.setText("Thành Tiền: " + formatter.format(finalAmount));
////                        apDungKhuyenMai();
////                          apDungKhuyenMai();
////               int idTXT = Integer.parseInt(txtid.getText());
////                        hienThiDuLieuGioHangg(idTXT);
//                    }
//                }
//                con.commit();
//            } else {
//                con.rollback();
//                System.out.println("Lỗi khi cập nhật số lượng sản phẩm.");
//            }
//        } else {
//            con.rollback();
//            System.out.println("Lỗi khi thêm sản phẩm vào đơn hàng chi tiết.");
//        }
//    } catch (SQLException e) {
//        con.rollback();
//        e.printStackTrace();
//    }
//} catch (SQLException e) {
//    e.printStackTrace();
//}
//
//}

    private void txtdonhangAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtdonhangAncestorAdded
        // TODO add your handling code here:

//        ArrayList<HoaDonEntity> hdList = ls.getAll();
//        if (!hdList.isEmpty()) {
//            HoaDonEntity firstOrder = hdList.get(0);
//            txtdonhang.setText(firstOrder.getMaHoaDon());
//            // Hiển thị mã hóa đơn đầu tiên lên JTextField
//        } else {
//            txtdonhang.setText("NULL");
//        }
    }//GEN-LAST:event_txtdonhangAncestorAdded

    private void txtidAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtidAncestorAdded
        // TODO add your handling code here:
//        ArrayList<HoaDonEntity> hdList = ls.getAll();
//        if (!hdList.isEmpty()) {
//            HoaDonEntity firstOrder = hdList.get(0);
//            txtid.setText(String.valueOf(firstOrder.getIdHoaDon()));
//            // Hiển thị mã hóa đơn đầu tiên lên JTextField
//        } else {
//            txtid.setText("NULL");
//        }
    }//GEN-LAST:event_txtidAncestorAdded

    private void txtTenKhachHangAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtTenKhachHangAncestorAdded
        // TODO add your handling code here:
//        ArrayList<HoaDonEntity> hdList = ls.getAll();
//        if (!hdList.isEmpty()) {
//            HoaDonEntity firstOrder = hdList.get(0);
//            txtTenKhachHang.setText(firstOrder.getTenKhachHang());
//        } else {
//            txtTenKhachHang.setText("Khách Bán Lẻ");
//        }
    }//GEN-LAST:event_txtTenKhachHangAncestorAdded

    private void txtTenNhanVienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtTenNhanVienAncestorAdded
        // TODO add your handling code here:
//        ArrayList<HoaDonEntity> hdList = ls.getAll();
//        if (!hdList.isEmpty()) {
//            HoaDonEntity firstOrder = hdList.get(0);
//            // Hiển thị mã hóa đơn đầu tiên lên JTextField
//            txtTenNhanVien.setText(firstOrder.getTenNhanVien());
//        } else {
//            txtTenNhanVien.setText("NULL");
//        }
    }//GEN-LAST:event_txtTenNhanVienAncestorAdded
    private void loadDuLieu() {
        ArrayList<HoaDonEntity> hdList = ls.getAll();
        if (!hdList.isEmpty()) {
            HoaDonEntity firstOrder = hdList.get(0);
            txtTenNhanVien.setText(firstOrder.getTenNhanVien());
            txtid.setText(String.valueOf(firstOrder.getIdHoaDon()));
            txtTenKhachHang.setText(firstOrder.getTenKhachHang());
            txtdonhang.setText(firstOrder.getMaHoaDon());
            Date ngayLap = (Date) firstOrder.getNgayLap();
            txtngaytao.setText(dateFormat.format(ngayLap));
        } else {
//            txtTenNhanVien.setText("NULL");
txtTenKhachHang.setText("Khách Bán Lẻ");
        }
    }

    private void loadDuLieuHuyDonHang() {

        txtTenNhanVien.setText("NULL");
        txtid.setText("NULL");
        txtTenKhachHang.setText("NULL");
        txtdonhang.setText("NULL");
        txtngaytao.setText("MULL");

    }
    private void txtngaytaoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtngaytaoAncestorAdded
        // TODO add your handling code here:
//        ArrayList<HoaDonEntity> hdList = ls.getAll();
//        if (!hdList.isEmpty()) {
//            HoaDonEntity firstOrder = hdList.get(0);
//            Date ngayLap = (Date) firstOrder.getNgayLap();
//            txtngaytao.setText(dateFormat.format(ngayLap)); // Định dạng và hiển thị ngày lập
//        } else {
//            txtngaytao.setText("NULL"); // Giá trị mặc định khi danh sách trống
//        }
    }//GEN-LAST:event_txtngaytaoAncestorAdded

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int idNhanVien = LuuThongTinDangNhap.getInNhanVien();
int maKhachHang = 3;
String trangThaiDonHang = "Hủy Đơn Hàng";
String sqlDonHang = """
    UPDATE DonHang
    SET ngay_dat = GETDATE(), ma_nhan_vien = ?, ma_khach_hang = ?, trang_thai = ?
    WHERE id_ma_don_hang = ?
""";

String sqlLayChiTietDonHang = """
    SELECT ma_san_pham, so_luong
    FROM ChiTietDonHang
    WHERE ma_don_hang = ?
""";

String sqlCapNhatSanPham = """
    UPDATE SanPham
    SET so_luong_ton = so_luong_ton + ?
    WHERE id_ma_san_pham = ?
""";

try (Connection con = ketnoi.getConnection()) {
    con.setAutoCommit(false);

    try (PreparedStatement psDonHang = con.prepareStatement(sqlDonHang);
         PreparedStatement psChiTiet = con.prepareStatement(sqlLayChiTietDonHang);
         PreparedStatement psCapNhatSanPham = con.prepareStatement(sqlCapNhatSanPham)) {

        // 1. Cập nhật trạng thái đơn hàng
        psDonHang.setInt(1, idNhanVien);
        psDonHang.setInt(2, maKhachHang);
        psDonHang.setString(3, trangThaiDonHang);
        psDonHang.setObject(4, txtid.getText());
        int rowsAffectedDonHang = psDonHang.executeUpdate();

        if (rowsAffectedDonHang > 0) {
            // 2. Lấy chi tiết sản phẩm trong đơn hàng
            psChiTiet.setObject(1, txtid.getText());
            try (ResultSet rs = psChiTiet.executeQuery()) {
                while (rs.next()) {
                    int maSanPham = rs.getInt("ma_san_pham");
                    int soLuong = rs.getInt("so_luong");
                    psCapNhatSanPham.setInt(1, soLuong);
                    psCapNhatSanPham.setInt(2, maSanPham);
                    psCapNhatSanPham.executeUpdate();
                }
            }
            con.commit();
            System.out.println("Đơn hàng đã được hủy và số lượng sản phẩm đã được cập nhật.");
            hienThiDuLieu(dh.layHhoaDon());
            loadDuLieuHuyDonHang();
            DefaultTableModel md = (DefaultTableModel) hienthigiohang.getModel();
            txtthue.setText("");
            txttongSoTien.setText("0");
            txtthanhtien.setText("0");
            txtGiamGia.setText("0");
            md.setRowCount(0);
            hienThiDuLieuuu(ls.getSanPhamChhiTiet());
        } else {
            con.rollback();
            System.out.println("Không thể hủy đơn hàng.");
        }
    } catch (SQLException e) {
        con.rollback();
        System.out.println("Lỗi khi thực thi SQL: " + e.getMessage());
    }
} catch (SQLException e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        previousPage(ls.getSanPhamChhiTiet());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        nextPage(ls.getSanPhamChhiTiet());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_sanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPham1MouseClicked
    int selectedRow = tbl_sanPham1.getSelectedRow();
        if (selectedRow != -1) {
            int maSanPham = (int) tbl_sanPham1.getValueAt(selectedRow, 0);
            String tenSanPham = (String) tbl_sanPham1.getValueAt(selectedRow, 1);
            float giaBan = (float) tbl_sanPham1.getValueAt(selectedRow, 3);
            int soLuongTon = (int) tbl_sanPham1.getValueAt(selectedRow, 2);

            String input = javax.swing.JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm để thêm vào hóa đơn:");

            if (input != null) {
                input = input.trim();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
                } else {
                    try {
                        int soLuong = Integer.parseInt(input);

                        if (soLuong <= 0) {
                            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                        } else if (soLuong > soLuongTon) {
                            JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ.");
                        } else {
                            addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuong);
                            hienThiDuLieuuu(ls.getSanPhamChhiTiet());
                            apDungKhuyenMai();
//                            hienThiDuLieuGioHangg();
                            int selectedRowHoaDon = tbldanhsachdonhagcho.getSelectedRow();
                            if (selectedRowHoaDon != -1) {
                                int maGioHangHienTai = Integer.parseInt(tbldanhsachdonhagcho.getValueAt(selectedRowHoaDon, 0).toString());
                                int idHoaDon = Integer.parseInt(txtid.getText());
                                hienThiDuLieuGioHangg(idHoaDon);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        
                    }
                }
            } else {
                System.out.println("Người dùng đã nhấn Cancel hoặc đóng cửa sổ.");
            }
        }

    }//GEN-LAST:event_tbl_sanPham1MouseClicked

    private void btn_taomaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_taomaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_taomaMouseEntered

    private void txt_tienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachDuaActionPerformed

    private void thuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thuonghieuActionPerformed
        // TODO add your handling code here:
        locSanPham();
    }//GEN-LAST:event_thuonghieuActionPerformed
//    private void hienThiDuLieuGioHang(int maGioHang) {
//        try {
//            ArrayList<DonHangChiTietEntity> ls = hd.layGioHangSanPham(maGioHang);
//            if (ls.isEmpty()) {
//                DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
//                model.setRowCount(0);
//            } else {
//                hienThiDuLieuGioHang(ls);
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
    private void tbldanhsachdonhagchoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldanhsachdonhagchoMouseClicked
        // TODO add your handling code here:
        int selectedRowHoaDon = tbldanhsachdonhagcho.getSelectedRow();
        if (selectedRowHoaDon != -1) {
            int maGioHangHienTai = Integer.parseInt(tbldanhsachdonhagcho.getValueAt(selectedRowHoaDon, 0).toString());
            hienThiDuLieuGioHangg(maGioHangHienTai);
            int index = tbldanhsachdonhagcho.getSelectedRow();
            HienThiDuLieuDonHangCho(index);
        }
    }//GEN-LAST:event_tbldanhsachdonhagchoMouseClicked
    private void HienThiDuLieuDonHangCho(int index) {
        
        
        HoaDonEntity sc = ls.getAll().get(index);
        txtTenNhanVien.setText(sc.getTenNhanVien());
        txtid.setText(String.valueOf(sc.getIdHoaDon()));
        txtdonhang.setText(sc.getMaHoaDon());
        Date ngayLap = (Date) sc.getNgayLap();
        txtngaytao.setText(dateFormat.format(ngayLap));
        txtTenKhachHang.setText(sc.getTenKhachHang());
       if(txtTenKhachHang.getText().equals("Khách Bán Lẻ")){
           txtidkhachhang.setText("");
       }else{
            txtidkhachhang.setText(String.valueOf(sc.getIdMaKhachHang()));
       }
        
    }
    private void hienthigiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hienthigiohangMouseClicked
        // TODO add your handling code here
    int selectedRowHoaDon = tbldanhsachdonhagcho.getSelectedRow();
    if (selectedRowHoaDon != -1) {
        int maGioHangHienTai = Integer.parseInt(tbldanhsachdonhagcho.getValueAt(selectedRowHoaDon, 0).toString());
    }            
    }//GEN-LAST:event_hienthigiohangMouseClicked
private void xoa1SanPham(){
    int chonDong = hienthigiohang.getSelectedRow();
if (chonDong != -1) {
    try {
        int idMaChiTietDonHang = Integer.parseInt(hienthigiohang.getValueAt(chonDong, 0).toString());
        String maSanPham = hienthigiohang.getValueAt(chonDong, 1).toString();
        int soLuongXoa = Integer.parseInt(hienthigiohang.getValueAt(chonDong, 4).toString());
        String sqlXoa = """
                    DELETE FROM chitietdonhang
                    WHERE ma_san_pham = ?
                    """;
        String sqlCapNhat = """
                    UPDATE sanpham
                    SET so_luong_ton = so_luong_ton + ?
                    WHERE ma_san_pham = ?
                    """;
        Connection con = ketnoi.getConnection();
        con.setAutoCommit(false); // Bắt đầu transaction

        // Thực hiện xóa
        PreparedStatement psXoa = con.prepareStatement(sqlXoa);
        psXoa.setInt(1, idMaChiTietDonHang);
        int rowsDeleted = psXoa.executeUpdate();

        if (rowsDeleted > 0) {
            PreparedStatement psCapNhat = con.prepareStatement(sqlCapNhat);
            psCapNhat.setInt(1, soLuongXoa);
            psCapNhat.setString(2, maSanPham);
            int rowsUpdated = psCapNhat.executeUpdate();

            if (rowsUpdated > 0) {
                con.commit();
                DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
                model.removeRow(chonDong);
                hienThiDuLieuuu(ls.getSanPhamChhiTiet());
                int id = Integer.parseInt(txtid.getText());
                hienThiDuLieuGioHangg(id);
            } else {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại!");
            }
            psCapNhat.close();
        } else {
            con.rollback();
            JOptionPane.showMessageDialog(null, "Xóa dòng thất bại!");
        }
        psXoa.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage());
    }
} else {
    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
}
}
private void xoaHetSanPham() {
    int idDonHang = Integer.parseInt(txtid.getText());
    try {
        String sqlXoa = """
                DELETE FROM chitietdonhang
                WHERE ma_don_hang = ?
                """;
        String sqlCapNhat = """
                UPDATE sanpham
                SET so_luong_ton = so_luong_ton + ?
                WHERE ma_san_pham = ?
                """;
        Connection con = ketnoi.getConnection();
        con.setAutoCommit(false);
        DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int idMaChiTietDonHang = Integer.parseInt(model.getValueAt(i, 0).toString());
            String maSanPham = model.getValueAt(i, 1).toString();
            int soLuongXoa = Integer.parseInt(model.getValueAt(i, 4).toString());
            PreparedStatement psXoa = con.prepareStatement(sqlXoa);
            psXoa.setInt(1, idDonHang);
            psXoa.executeUpdate();
            psXoa.close();
            PreparedStatement psCapNhat = con.prepareStatement(sqlCapNhat);
            psCapNhat.setInt(1, soLuongXoa);
            psCapNhat.setString(2, maSanPham);
            psCapNhat.executeUpdate();
            psCapNhat.close();
        }
        con.commit();
        model.setRowCount(0);
        hienThiDuLieuuu(ls.getSanPhamChhiTiet());
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage());
        try {
            Connection con = ketnoi.getConnection();
            con.rollback();
            con.close();
        } catch (Exception rollbackEx) {
            rollbackEx.printStackTrace();
        }
    }
}

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(xoaTatCa.isSelected()){
            xoaHetSanPham();
            txttongSoTien.setText("0");
            txtthanhtien.setText("0");
            txtGiamGia.setText("0");
            txtthue.setText("");
        }else{
            xoa1SanPham();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtLocMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocMauSacActionPerformed
        // TODO add your handling code here:
        locSanPham();
    }//GEN-LAST:event_txtLocMauSacActionPerformed

    private void txtLocTenSanPham1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocTenSanPham1KeyReleased
        // TODO add your handling code here:
        locSanPham();
    }//GEN-LAST:event_txtLocTenSanPham1KeyReleased

    private void txtlockichco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlockichco1ActionPerformed
        // TODO add your handling code here:
        locSanPham();
    }//GEN-LAST:event_txtlockichco1ActionPerformed

    private void txtsodienthoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsodienthoaiActionPerformed
        timKimMaKhachHang();
    }//GEN-LAST:event_txtsodienthoaiActionPerformed
private void capNhatSoLuongGioHang() {
    int selectedRow = hienthigiohang.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng để cập nhật!");
        return;
    }
    int idChiTietDonHang = Integer.parseInt(hienthigiohang.getValueAt(selectedRow, 0).toString());
    int soLuongHienTai = Integer.parseInt(hienthigiohang.getValueAt(selectedRow, 4).toString());
    String soLuongMoiStr = JOptionPane.showInputDialog(this, "Nhập số lượng mới:");
    if (soLuongMoiStr == null || soLuongMoiStr.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
        return;
    }
    try {
        int soLuongMoi = Integer.parseInt(soLuongMoiStr);
        int thayDoiSoLuong = soLuongMoi - soLuongHienTai;
        Connection conn = ketnoi.getConnection();
        PreparedStatement psCheckStock = null;
        ResultSet rsStock = null;
        try {
            String sqlCheckStock = "SELECT so_luong_ton FROM SanPham WHERE id_ma_san_pham IN "
                    + "(SELECT ma_san_pham FROM chitietdonhang WHERE ma_san_pham = ?)";
            psCheckStock = conn.prepareStatement(sqlCheckStock);
            psCheckStock.setInt(1, idChiTietDonHang);
            rsStock = psCheckStock.executeQuery();

            if (rsStock.next()) {
                int soLuongTon = rsStock.getInt("so_luong_ton");
                if (thayDoiSoLuong > 0 && soLuongMoi > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ trong kho!");
                    return;
                }
            }
            PreparedStatement psUpdateSanPham = null;
            PreparedStatement psUpdateChiTietDonHang = null;
            if (thayDoiSoLuong < 0) {
                String sqlUpdateSanPham = "UPDATE SanPham SET so_luong_ton = so_luong_ton + ? "
                        + "WHERE id_ma_san_pham IN (SELECT ma_san_pham FROM chitietdonhang WHERE ma_san_pham = ?)";
                psUpdateSanPham = conn.prepareStatement(sqlUpdateSanPham);
                psUpdateSanPham.setInt(1, Math.abs(thayDoiSoLuong)); // Cộng số lượng bị giảm vào tồn kho
                psUpdateSanPham.setInt(2, idChiTietDonHang);
                int rowsUpdatedSanPham = psUpdateSanPham.executeUpdate();
                if (rowsUpdatedSanPham <= 0) {
                    System.out.println("Không cập nhật được bảng Sản Phẩm!");
                }
            }
            else if (thayDoiSoLuong > 0) {
                String sqlUpdateSanPham = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? "
                        + "WHERE id_ma_san_pham IN (SELECT ma_san_pham FROM chitietdonhang WHERE ma_san_pham = ?)";
                psUpdateSanPham = conn.prepareStatement(sqlUpdateSanPham);
                psUpdateSanPham.setInt(1, Math.abs(thayDoiSoLuong)); // Trừ số lượng vào tồn kho
                psUpdateSanPham.setInt(2, idChiTietDonHang);
                int rowsUpdatedSanPham = psUpdateSanPham.executeUpdate();
                if (rowsUpdatedSanPham <= 0) {
                    System.out.println("Không cập nhật được bảng SanPham!");
                }
            }

            // Cập nhật số lượng trong giỏ hàng
            String sqlUpdateChiTiet = "UPDATE chitietdonhang SET so_luong = ? WHERE ma_san_pham = ?";
            psUpdateChiTietDonHang = conn.prepareStatement(sqlUpdateChiTiet);
            psUpdateChiTietDonHang.setInt(1, soLuongMoi);
            psUpdateChiTietDonHang.setInt(2, idChiTietDonHang);
            int rowsUpdatedChiTiet = psUpdateChiTietDonHang.executeUpdate();

            if (rowsUpdatedChiTiet > 0) {
                hienThiDuLieuGioHangg(); // Refresh lại dữ liệu giỏ hàng

                // Cập nhật lại tổng tiền cho chi tiết đơn hàng
                double giaBan = Double.parseDouble(hienthigiohang.getValueAt(selectedRow, 3).toString());
                double tongTienChiTiet = giaBan * soLuongMoi;
                
                // Cập nhật tổng tiền vào chi tiết đơn hàng
                String sqlUpdateTongTien = "UPDATE chitietdonhang SET tong_tien = ? WHERE ma_san_pham = ?";
                PreparedStatement psUpdateTongTien = conn.prepareStatement(sqlUpdateTongTien);
                psUpdateTongTien.setDouble(1, tongTienChiTiet);
                psUpdateTongTien.setInt(2, idChiTietDonHang);
                psUpdateTongTien.executeUpdate();
                
                // Cập nhật lại tổng tiền và thuế cho giỏ hàng
                double tongTien = 0.0;
                double thue = 0.1; // Thuế 10%

                // Duyệt qua tất cả các sản phẩm trong giỏ hàng để tính tổng tiền
                for (int i = 0; i < hienthigiohang.getRowCount(); i++) {
                    int soLuong = Integer.parseInt(hienthigiohang.getValueAt(i, 4).toString());
                    double giaBanSP = Double.parseDouble(hienthigiohang.getValueAt(i, 3).toString());
                    tongTien += soLuong * giaBanSP;
                }

                // Cộng thuế vào tổng tiền
                double tongTienVoiThue = tongTien * (1 + thue);
                txttongSoTien.setText("" + String.format("%.2f", tongTienVoiThue));
                apDungKhuyenMai();
                int idDonHang = Integer.parseInt(txtid.getText());
                hienThiDuLieuGioHangg(idDonHang);
                hienThiDuLieuuu(ls.getSanPhamChhiTiet());
            } else {
                JOptionPane.showMessageDialog(this, "Không thể cập nhật số lượng!");
            }
        } finally {
            if (psCheckStock != null) {
                psCheckStock.close();
            }
            if (rsStock != null) {
                rsStock.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật số lượng: " + ex.getMessage());
        ex.printStackTrace();
    }
}

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
      capNhatSoLuongGioHang();
     
    }//GEN-LAST:event_jButton5ActionPerformed
public void resetFields() {
        txttongSoTien.setText(""); // Reset ô txtid
        txtthanhtien.setText(""); // Reset ô txtthanhtien
    }
    private void txtidkhachhangAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtidkhachhangAncestorAdded
        // TODO add your handling code here:
//          ArrayList<HoaDonEntity> hdList = ls.getAll();
//        if (!hdList.isEmpty()) {
//            if(txtTenKhachHang.getText().equals("Khách Bán Lẻ")){
//                txtidkhachhang.setText("");
//            }else{
//                HoaDonEntity firstOrder = hdList.get(0);
//            txtidkhachhang.setText(String.valueOf(firstOrder.getIdMaKhachHang()));
//            }
//        } else {
//            txtidkhachhang.setText("NULL");
//        }
    }//GEN-LAST:event_txtidkhachhangAncestorAdded

    private void cbo_hinhThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_hinhThucThanhToanActionPerformed
        // TODO add your handling code here:
   String hinhThucThanhToan = (String) cbo_hinhThucThanhToan.getSelectedItem();
    if (hinhThucThanhToan.equals("Tiền Mặt")) {
        txt_tienKhachDua.setEnabled(true);
        txtTienTraKhac.setEnabled(false);
        chuyenkhoan.setEnabled(false);
        chuyenkhoan.setText("0");
    } else if (hinhThucThanhToan.equals("Chuyển Khoản")) {
        txt_tienKhachDua.setEnabled(false);
        txtTienTraKhac.setEnabled(false);
        chuyenkhoan.setEnabled(true);
            chuyenkhoan.setText(txtthanhtien.getText());
        } else if (hinhThucThanhToan.equals("Cả Hai")) {
            txt_tienKhachDua.setEnabled(true);
            chuyenkhoan.setEnabled(true);
            txtTienTraKhac.setEnabled(false);
            
            String thanhTienText = txtthanhtien.getText();
            try {
                String cleanedInput = thanhTienText.replaceAll("[^\\d,]", "");
                cleanedInput = cleanedInput.replaceAll(",", "");
                float thanhTien = Float.parseFloat(cleanedInput);
                DecimalFormat formatter = new DecimalFormat("#,###.##");
                apDungKhuyenMai();
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

                    private void capNhatTienThua() {
                        String inputAmountStr = txt_tienKhachDua.getText();
                        if (!inputAmountStr.isEmpty()) {
                            try {   
                                String cleanedInput = inputAmountStr.replaceAll("[,\\s]", "");
                                double inputAmount = Double.parseDouble(cleanedInput);
                                double moneyLeft = thanhTien - inputAmount;
                                if (moneyLeft <= 0) {
                                chuyenkhoan.setText(formatter.format(Math.abs(moneyLeft)) + " VND");
                                txtTienTraKhac.setText("");// Lấy giá trị tuyệt đối để không có dấu "-"
                            } else {
                                    chuyenkhoan.setText(formatter.format(moneyLeft) + " VND");
                                    txtTienTraKhac.setText("");
                            }
                        } catch (NumberFormatException e) {
                            chuyenkhoan.setText("Vui lòng nhập số tiền hợp lệ.");
                        }
                    } else {
                        chuyenkhoan.setText("");
                    }
                }

            });
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format for thanhTien.");
        }
        }
    }//GEN-LAST:event_cbo_hinhThucThanhToanActionPerformed

    private void txt_tienKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaMouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_txt_tienKhachDuaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_taoma;
    private javax.swing.JComboBox<String> cbo_hinhThucThanhToan;
    private javax.swing.JLabel chuyenkhoan;
    private javax.swing.JPanel dd;
    private javax.swing.JPanel duyemWebcam;
    private javax.swing.JTable hienthigiohang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_DonHang1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTable tbl_sanPham1;
    private javax.swing.JTable tbldanhsachdonhagcho;
    private javax.swing.JComboBox<String> thuonghieu;
    private javax.swing.JLabel txtGiamGia;
    private javax.swing.JComboBox<String> txtLocMauSac;
    private javax.swing.JTextField txtLocTenSanPham1;
    private javax.swing.JLabel txtTenKhachHang;
    private javax.swing.JLabel txtTenNhanVien;
    private javax.swing.JLabel txtTienTraKhac;
    private javax.swing.JTextField txt_tienKhachDua;
    private javax.swing.JLabel txtdonhang;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtidkhachhang;
    private javax.swing.JComboBox<String> txtlockichco1;
    private javax.swing.JLabel txtngaytao;
    private javax.swing.JTextField txtsodienthoai;
    private javax.swing.JLabel txtthanhtien;
    private javax.swing.JLabel txtthue;
    private javax.swing.JLabel txttongSoTien;
    private javax.swing.JLabel txttranng;
    private javax.swing.JCheckBox xoaTatCa;
    // End of variables declaration//GEN-END:variables

    void resetFieldsInNewJPanel() {
        hienThiDuLieuGioHangg();
        hienThiDuLieu(dh.layHhoaDon());// Đặt lại giá trị cho ô txtthanhtien
    }
}
