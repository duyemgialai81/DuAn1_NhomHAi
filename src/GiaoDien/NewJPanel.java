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
                ctdh.id_ma_chi_tiet_don_hang,
                sp.ma_san_pham, 
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
             
            // Lấy dữ liệu sản phẩm
            psChiTiet.setInt(1, maGioHang);
            ResultSet rsChiTiet = psChiTiet.executeQuery();
            Map<String, Object[]> productMap = new HashMap<>();
            while (rsChiTiet.next()) {
                int idMaDonHang = rsChiTiet.getInt("id_ma_chi_tiet_don_hang");
                String maSanPham = rsChiTiet.getString("ma_san_pham");
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
            
            // Hiển thị dữ liệu vào bảng
            DefaultTableModel model = (DefaultTableModel) hienthigiohang.getModel();
            model.setRowCount(0);
            for (Object[] product : productMap.values()) {
                model.addRow(product);
            }
            
            // Tính tổng tiền và thuế
            psTongTienThue.setInt(1, maGioHang);
            ResultSet rsTongTienThue = psTongTienThue.executeQuery();
            if (rsTongTienThue.next()) {
                float tongTien = rsTongTienThue.getFloat("tongTien");
                float tongThue = rsTongTienThue.getFloat("tongThue");
                // Tính tổng thành tiền
                float giamGia = 0;
                float thanhTien = tongTien + tongThue; 
//                if (tongTien > 1000000) {
//                    giamGia = thanhTien * 0.02f;
//                }
                DecimalFormat formatter = new DecimalFormat("#,###.##");
                txttongSoTien.setText(formatter.format(tongTien));
                txtthue.setText(formatter.format(tongThue));
                txtthanhtien.setText(formatter.format(thanhTien));
                txtGiamGia.setText(formatter.format(giamGia));
                apDungKhuyenMai();
                // Cập nhật tiền thừa khi khách nhập tiền
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
        md.setRowCount(0);
        for (SanPhamEntity l : lss) {
            md.addRow(new Object[]{
                l.getIdSanPham(), l.getTenSanPham(), l.getSoLuong(), l.getGiaBan(),l.getKichCo(), l.getMauSac(), l.getTenThuongHieu()
            });
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
                donHangChiTietEntity.getIdDonHangChiTiet(), donHangChiTietEntity.getMaSanPham(), donHangChiTietEntity.getTenSanPham(), donHangChiTietEntity.getGiaBan(), donHangChiTietEntity.getSoLuong()

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

// Lấy mã sản phẩm từ QR code và hiển thị sản phẩm
  private void handleQRCodeScan(String maSanPhamHoaDon) {
    ArrayList<SanPhamEntity> productList = new ArrayList<>();
    String sql = "SELECT ma_san_pham, ten_san_pham, so_luong_ton, gia_ban FROM SanPham WHERE ma_san_pham = ?";

    try (Connection con = ketnoi.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setObject(1, maSanPhamHoaDon);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                SanPhamEntity product = new SanPhamEntity();
                product.setMaSanPham(rs.getString("ma_san_pham"));
                product.setTenSanPham(rs.getString("ten_san_pham"));
                product.setSoLuong(rs.getInt("so_luong_ton"));
                product.setGiaBan(rs.getFloat("gia_ban"));
                productList.add(product);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã: " + maSanPhamHoaDon);
                return;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage());
        e.printStackTrace();
    }

    if (!productList.isEmpty()) {
        SanPhamEntity product = productList.get(0); // Chỉ lấy sản phẩm đầu tiên
        int quantity = promptQuantity(product);
        if (quantity > 0) {
            addProductToOrder(product, quantity);
        }
    }
}

private int promptQuantity(SanPhamEntity product) {
    while (true) {
        String input = JOptionPane.showInputDialog(null, 
                "Nhập số lượng cho sản phẩm " + product.getTenSanPham() + " (Tồn kho: " + product.getSoLuong() + "):");
        if (input == null || input.isEmpty()) {
            return -1; // Hủy bỏ
        }

        try {
            int quantity = Integer.parseInt(input);
            if (quantity > 0 && quantity <= product.getSoLuong()) {
                return quantity;
            } else {
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ, vui lòng nhập lại.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ.");
        }
    }
}

private void addProductToOrder(SanPhamEntity product, int quantity) {
    int maSanPham = Integer.parseInt(product.getMaSanPham().replace("SP", ""));
    float giaBan = product.getGiaBan();

    if (quantity > 0 && quantity <= product.getSoLuong()) {
        addProductToOrderDetailAndUpdatePayment(maSanPham, product.getTenSanPham(), giaBan, quantity);
        updateProductQuantity(maSanPham, quantity);
        hienThiDuLieuGioHangg();
    } else {
        JOptionPane.showMessageDialog(null, "Không đủ số lượng trong kho. Tồn kho hiện tại: " + product.getSoLuong());
    }
}

private void updateProductQuantity(int maSanPham, int quantity) {
    String sql = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? WHERE id_ma_san_pham = ?";
    try (Connection con = ketnoi.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, quantity);
        stmt.setInt(2, maSanPham);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cập nhật số lượng tồn kho thành công.");
        } else {
            System.out.println("Không thể cập nhật số lượng tồn kho.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng tồn kho: " + e.getMessage());
        e.printStackTrace();
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

                // Thêm item vào combobox
                txtlockichco1.addItem(kichCo);
            }

            // Cập nhật lại giao diện
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

                // Thêm item vào combobox
                txtLocMauSac.addItem(mauSac);
            }

            // Cập nhật lại giao diện
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
                "ID Sản Phẩm", "Tến Sản Phẩm", "Số Lượng", "Giá Sản Phẩm", "Kích Thước", "Máu Sắc", "Thương Hiệu"
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
        jPanel_DonHang1.add(chuyenkhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 620, 150, 20));

        jPanel2.add(jPanel_DonHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 380, 730));

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("Sản Phẩm");
    }// </editor-fold>//GEN-END:initComponents

    private void txttongSoTienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txttongSoTienAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongSoTienAncestorAdded

    private void btn_taomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_taomaMouseClicked
        int idNhanVien = LuuThongTinDangNhap.getInNhanVien();
        int maKhachHangg = 10;
       String tenKhachHang = "NULL";
        if(txtTenKhachHang.getText().equals(tenKhachHang)||txtTenKhachHang.getText().equals("Khách Bán Lẻ")){
            String trangThaiDonHnag = "đang chờ thanh toán";
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
//            JOptionPane.showMessageDialog(hienthigiohang, "hhgffduhb");
        }else{
             int maKhachHang = Integer.parseInt(txtidkhachhang.getText());
        String trangThaiDonHnag = "đang chờ thanh toán";
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
//String idmaDonHnag = txtid.getText();
//String maDonHang = txtmahoadon.getText();
//String trangThaiHoaDon = "Đã thanh toán";
//String sql = """
//    UPDATE hoadon
//    SET ngay_lap = GETDATE(), tien_khach_dua = ?, tien_tra_khach = ?, phuong_thuc = ?, trang_thai = ?
//    WHERE ma_hoa_don = ?
//    """;
//
//String trangThaiDonHang = "Đã Thanh Toán";
//String DonHang = txtdonhang.getText();
//String sqlDonHang = """
//    UPDATE donhang
//    SET trang_thai = ?
//    WHERE ma_don_hang = ?
//    """;
//
//try {
//    Connection con = ketnoi.getConnection();
//    PreparedStatement psDonHang = con.prepareStatement(sqlDonHang);
//    psDonHang.setObject(1, trangThaiDonHang);
//    psDonHang.setObject(2, DonHang);
//    int checkDonHang = psDonHang.executeUpdate();
//    double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText().trim().replace(",", ""));
//    double tienTraKhach = Double.parseDouble(txtTienTraKhac.getText().trim().replace(",", ""));
//    String phuongThuc = cbo_hinhThucThanhToan.getSelectedItem().toString();
//    PreparedStatement psHoaDon = con.prepareStatement(sql);
//    psHoaDon.setObject(1, tienKhachDua);
//    psHoaDon.setObject(2, tienTraKhach);
//    psHoaDon.setObject(3, phuongThuc);
//    psHoaDon.setObject(4, trangThaiHoaDon);
//    psHoaDon.setObject(5, maDonHang);
//    int checkHoaDon = psHoaDon.executeUpdate();
//    if (checkDonHang > 0 && checkHoaDon > 0) {
//        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
//  try {
//    File file = new File("D:\\HoaDon_" + maDonHang + ".pdf");
//    if (file.exists()) {
//        file.delete();
//    }
//    PdfWriter writer = new PdfWriter(file);
//    PdfDocument pdf = new PdfDocument(writer);
//    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
//    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
//
//    Connection conn = ketnoi.getConnection();
//
//    String sqlHoaDon = """
//        SELECT nv.ten_nhan_vien, kh.ten_khach_hang, hd.ngay_lap, count(ctdh.tong_tien) as tongTien
//        FROM DonHang dh 
//        JOIN HoaDon hd ON hd.ma_don_hang = dh.id_ma_don_hang
//        JOIN NhanVien nv ON dh.ma_nhan_vien = nv.id_ma_nhan_vien
//        JOIN KhachHang kh ON dh.ma_khach_hang = kh.id_ma_khach_hang
//        JOIN ChiTietDonHang ctdh ON dh.id_ma_don_hang = ctdh.ma_don_hang
//        WHERE hd.ma_hoa_don = ?
//        group by nv.ten_nhan_vien, kh.ten_khach_hang, hd.ngay_lap
//    """;
//    PreparedStatement psHoaDonnn = conn.prepareStatement(sqlHoaDon);
//    psHoaDonnn.setString(1, maDonHang);
//    ResultSet rsHoaDon = psHoaDonnn.executeQuery();
//
//    String tenNhanVien = "", tenKhachHang = "";
//    String ngayLap = "";
//    float tongTien = 0;
//    if (rsHoaDon.next()) {
//        tenNhanVien = rsHoaDon.getString("ten_nhan_vien");
//        tenKhachHang = rsHoaDon.getString("ten_khach_hang");
//        ngayLap = rsHoaDon.getString("ngay_lap");
//        tongTien = rsHoaDon.getFloat("tongTien");
//    }
//
//    // Tiêu đề và thông tin hóa đơn
//    document.add(new Paragraph("HÓA ĐƠN THANH TOÁN")
//            .setFont(font)
//            .setFontSize(20)
//            .setTextAlignment(TextAlignment.CENTER)
//            .setBold());
//    document.add(new Paragraph("Mã Hóa Đơn: " + maDonHang).setFont(font).setFontSize(12));
//    document.add(new Paragraph("Nhân Viên: " + tenNhanVien).setFont(font).setFontSize(12));
//    document.add(new Paragraph("Khách Hàng: " + tenKhachHang).setFont(font).setFontSize(12));
//    document.add(new Paragraph("Ngày Lập: " + ngayLap).setFont(font).setFontSize(12));
//    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
//
//    // Lấy thông tin chi tiết đơn hàng
// String sqlChiTietDonHang = """
//    SELECT sp.ten_san_pham, SUM(ctdh.so_luong) as tong_so_luong, SUM(ctdh.so_luong * ctdh.gia_ban) as tong_gia
//    FROM ChiTietDonHang ctdh
//    JOIN SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
//    WHERE ctdh.ma_don_hang = ?
//    GROUP BY sp.ten_san_pham
//""";
//PreparedStatement psChiTietDonHang = conn.prepareStatement(sqlChiTietDonHang);
//psChiTietDonHang.setString(1, idmaDonHnag);
//ResultSet rsChiTietDonHang = psChiTietDonHang.executeQuery();
//
//// Tạo bảng chi tiết sản phẩm
//Table table = new Table(3);
//table.setWidth(pdf.getDefaultPageSize().getWidth() * 0.9f);
//
//// Thêm tiêu đề cột
//table.addHeaderCell(new Paragraph("Tên Sản Phẩm").setFont(font).setBold()
//        .setBackgroundColor(ColorConstants.GRAY)
//        .setFontColor(ColorConstants.WHITE)
//        .setTextAlignment(TextAlignment.CENTER));
//table.addHeaderCell(new Paragraph("Số Lượng").setFont(font).setBold()
//        .setBackgroundColor(ColorConstants.GRAY)
//        .setFontColor(ColorConstants.WHITE)
//        .setTextAlignment(TextAlignment.CENTER));
//table.addHeaderCell(new Paragraph("Tổng Giá").setFont(font).setBold()
//        .setBackgroundColor(ColorConstants.GRAY)
//        .setFontColor(ColorConstants.WHITE)
//        .setTextAlignment(TextAlignment.CENTER));
//
//// Thêm dữ liệu sản phẩm vào bảng
//while (rsChiTietDonHang.next()) {
//    String tenSanPham = rsChiTietDonHang.getString("ten_san_pham");
//    int tongSoLuong = rsChiTietDonHang.getInt("tong_so_luong");
//    float tongGia = rsChiTietDonHang.getFloat("tong_gia");
//
//    table.addCell(new Paragraph(tenSanPham).setFont(font));
//    table.addCell(new Paragraph(String.valueOf(tongSoLuong)).setFont(font).setTextAlignment(TextAlignment.CENTER));
//    table.addCell(new Paragraph(String.format("", tongGia)).setFont(font).setTextAlignment(TextAlignment.CENTER));
//}
//
//    document.add(table);
//    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
//
//    // Tổng tiền
//    document.add(new Paragraph("TỔNG TIỀN: " + String.format("%,.0f", tongTien) + " VND")
//            .setFont(font)
//            .setFontSize(12)
//            .setBold()
//            .setTextAlignment(TextAlignment.RIGHT));
//
//    // Thông tin ngân hàng và liên hệ
//    document.add(new Paragraph("GỬI THANH TOÁN ĐẾN")
//            .setFont(font)
//            .setFontSize(10)
//            .setMarginTop(20));
//    document.add(new Paragraph("Số ngân hàng: 0367548754 \nTên ngân hàng: Đoàn Ngọc Duy")
//            .setFont(font)
//            .setFontSize(10));
//    document.add(new Paragraph("LIÊN HỆ")
//            .setFont(font)
//            .setFontSize(10)
//            .setMarginTop(-40)
//            .setTextAlignment(TextAlignment.RIGHT));
//    document.add(new Paragraph("doanngocduy62@gmail.com\nSĐT: 0382424762")
//            .setFont(font)
//            .setFontSize(10)
//            .setTextAlignment(TextAlignment.RIGHT));
//
//    document.close();
//    JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
//} catch (IOException e) {
//    e.printStackTrace();
//    JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn PDF!");
//} catch (SQLException e) {
//    e.printStackTrace();
//    JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu!");
//}
//
//
//    } else {
//        JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//    }
//
//} catch (Exception e) {
//    e.printStackTrace();
//}
//String idmaDonHnag = txtid.getText();
//String maDonHang = txtmahoadon.getText();
//String trangThaiHoaDon = "Đã thanh toán";
//String sql = """
//    UPDATE hoadon
//    SET ngay_lap = GETDATE(), tien_khach_dua = ?, tien_tra_khach = ?, phuong_thuc = ?, trang_thai = ?
//    WHERE ma_hoa_don = ?
//    """;
//
//String trangThaiDonHang = "Đã Thanh Toán";
//String DonHang = txtdonhang.getText();
//String sqlDonHang = """
//    UPDATE donhang
//    SET trang_thai = ?
//    WHERE ma_don_hang = ?
//    """;
//
//try {
//    // Kết nối cơ sở dữ liệu
//    Connection con = ketnoi.getConnection();
//
//    // Cập nhật bảng `donhang`
//    PreparedStatement psDonHang = con.prepareStatement(sqlDonHang);
//    psDonHang.setObject(1, trangThaiDonHang);
//    psDonHang.setObject(2, DonHang);
//    int checkDonHang = psDonHang.executeUpdate();
//
//    // Cập nhật bảng `hoadon`
//    double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText().trim().replace(",", ""));
//    double tienTraKhach = Double.parseDouble(txtTienTraKhac.getText().trim().replace(",", ""));
//    String phuongThuc = cbo_hinhThucThanhToan.getSelectedItem().toString();
//
//    PreparedStatement psHoaDon = con.prepareStatement(sql);
//    psHoaDon.setObject(1, tienKhachDua);
//    psHoaDon.setObject(2, tienTraKhach);
//    psHoaDon.setObject(3, phuongThuc);
//    psHoaDon.setObject(4, trangThaiHoaDon);
//    psHoaDon.setObject(5, maDonHang);
//    int checkHoaDon = psHoaDon.executeUpdate();
//
//    // Thông báo kết quả
//    if (checkDonHang > 0 && checkHoaDon > 0) {
//        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
//  try {
//    // Đường dẫn file PDF
//    File file = new File("D:\\HoaDon_" + maDonHang + ".pdf");
//    if (file.exists()) {
//        file.delete();
//    }
//
//    // Khởi tạo đối tượng PDF
//    PdfWriter writer = new PdfWriter(file);
//    PdfDocument pdf = new PdfDocument(writer);
//    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
//    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
//
//    Connection conn = ketnoi.getConnection();
//
//    String sqlHoaDon = """
//        SELECT nv.ten_nhan_vien, kh.ten_khach_hang, hd.ngay_lap, ctdh.tong_tien
//        FROM DonHang dh 
//        JOIN HoaDon hd ON hd.ma_don_hang = dh.id_ma_don_hang
//        JOIN NhanVien nv ON dh.ma_nhan_vien = nv.id_ma_nhan_vien
//        JOIN KhachHang kh ON dh.ma_khach_hang = kh.id_ma_khach_hang
//        JOIN ChiTietDonHang ctdh ON dh.id_ma_don_hang = ctdh.ma_don_hang
//        WHERE hd.ma_hoa_don = ?
//    """;
//    PreparedStatement psHoaDonnn = conn.prepareStatement(sqlHoaDon);
//    psHoaDonnn.setString(1, maDonHang);
//    ResultSet rsHoaDon = psHoaDonnn.executeQuery();
//
//    String tenNhanVien = "", tenKhachHang = "";
//    String ngayLap = "";
//    double tongTien = 0;
//    if (rsHoaDon.next()) {
//        tenNhanVien = rsHoaDon.getString("ten_nhan_vien");
//        tenKhachHang = rsHoaDon.getString("ten_khach_hang");
//        ngayLap = rsHoaDon.getString("ngay_lap");
//        tongTien = rsHoaDon.getDouble("tong_tien");
//    }
//
//    // Tiêu đề và thông tin hóa đơn
//    document.add(new Paragraph("HÓA ĐƠN THANH TOÁN")
//            .setFont(font)
//            .setFontSize(20)
//            .setTextAlignment(TextAlignment.CENTER)
//            .setBold());
//    document.add(new Paragraph("Mã Hóa Đơn: " + maDonHang).setFont(font).setFontSize(12));
//    document.add(new Paragraph("Nhân Viên: " + tenNhanVien).setFont(font).setFontSize(12));
//    document.add(new Paragraph("Khách Hàng: " + tenKhachHang).setFont(font).setFontSize(12));
//    document.add(new Paragraph("Ngày Lập: " + ngayLap).setFont(font).setFontSize(12));
//    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
//
//    // Lấy thông tin chi tiết đơn hàng
//    String sqlChiTietDonHang = """
//    SELECT sp.ten_san_pham, SUM(ctdh.so_luong) AS tong_so_luong, ctdh.gia_ban
//    FROM ChiTietDonHang ctdh
//    JOIN SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
//    WHERE ctdh.ma_don_hang = ?
//    GROUP BY sp.ten_san_pham, ctdh.gia_ban
//""";
//
//PreparedStatement psChiTietDonHang = conn.prepareStatement(sqlChiTietDonHang);
//psChiTietDonHang.setString(1, idmaDonHnag);
//ResultSet rsChiTietDonHang = psChiTietDonHang.executeQuery();
//
//// Tạo bảng chi tiết sản phẩm
//Table table = new Table(3);
//table.setWidth(pdf.getDefaultPageSize().getWidth() * 0.9f);
//
//// Thêm tiêu đề cột
//table.addHeaderCell(new Paragraph("Tên Sản Phẩm").setFont(font).setBold()
//        .setBackgroundColor(ColorConstants.GRAY)
//        .setFontColor(ColorConstants.WHITE)
//        .setTextAlignment(TextAlignment.CENTER));
//table.addHeaderCell(new Paragraph("Số Lượng").setFont(font).setBold()
//        .setBackgroundColor(ColorConstants.GRAY)
//        .setFontColor(ColorConstants.WHITE)
//        .setTextAlignment(TextAlignment.CENTER));
//table.addHeaderCell(new Paragraph("Giá").setFont(font).setBold()
//        .setBackgroundColor(ColorConstants.GRAY)
//        .setFontColor(ColorConstants.WHITE)
//        .setTextAlignment(TextAlignment.CENTER));
//
//// Thêm dữ liệu sản phẩm vào bảng
//while (rsChiTietDonHang.next()) {
//    String tenSanPham = rsChiTietDonHang.getString("ten_san_pham");
//    int tongSoLuong = rsChiTietDonHang.getInt("tong_so_luong");
//    double giaBan = rsChiTietDonHang.getDouble("gia_ban");
//
//    table.addCell(new Paragraph(tenSanPham).setFont(font));
//    table.addCell(new Paragraph(String.valueOf(tongSoLuong)).setFont(font).setTextAlignment(TextAlignment.CENTER));
//    table.addCell(new Paragraph(String.format("%,.0f", giaBan)).setFont(font).setTextAlignment(TextAlignment.CENTER));
//}
//
//document.add(table);
//    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
//
//    // Tổng tiền
//    document.add(new Paragraph("TỔNG TIỀN: " + String.format("%,.0f", tongTien) + " VND")
//            .setFont(font)
//            .setFontSize(12)
//            .setBold()
//            .setTextAlignment(TextAlignment.RIGHT));
//
//    // Thông tin ngân hàng và liên hệ
//    document.add(new Paragraph("GỬI THANH TOÁN ĐẾN")
//            .setFont(font)
//            .setFontSize(10)
//            .setMarginTop(20));
//    document.add(new Paragraph("Số ngân hàng: 0367548754 \nTên ngân hàng: Đoàn Ngọc Duy")
//            .setFont(font)
//            .setFontSize(10));
//    document.add(new Paragraph("LIÊN HỆ")
//            .setFont(font)
//            .setFontSize(10)
//            .setMarginTop(-40)
//            .setTextAlignment(TextAlignment.RIGHT));
//    document.add(new Paragraph("doanngocduy62@gmail.com\nSĐT: 0382424762")
//            .setFont(font)
//            .setFontSize(10)
//            .setTextAlignment(TextAlignment.RIGHT));
//
//    document.close();
//    JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
//} catch (IOException e) {
//    e.printStackTrace();
//    JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn PDF!");
//} catch (SQLException e) {
//    e.printStackTrace();
//    JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu!");
//}
//
//
//    } else {
//        JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//    }
//
//} catch (Exception e) {
//    e.printStackTrace();
//}
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
        double chuyenKhoan = Double.parseDouble(numericValuee);
        String sql = """
    insert into hoadon (ngay_lap, tien_khach_dua , tien_tra_khach , phuong_thuc , trang_thai, thanh_tien, ma_don_hang, tien_chuyen_khoan)
    values(getdate(),?,?,?,?,?,?,?)
    """;

        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement psHoaDon = con.prepareStatement(sql);
            psHoaDon.setObject(1, tienKhachDua);
            psHoaDon.setObject(2, tienTraKhach);
            psHoaDon.setObject(3, phuongThuc);
            psHoaDon.setObject(4, trangThaiHoaDon);
            psHoaDon.setFloat(5, thanhTien);
            psHoaDon.setObject(6, idmaDonHnag);
            psHoaDon.setObject(7, chuyenKhoan);
            int checkHoaDon = psHoaDon.executeUpdate();
            if (checkHoaDon > 0) {
             XuatHoaDonnn duyem = new XuatHoaDonnn();
              duyem.setVisible(true);
              
                hienThiDuLieu(dh.layHhoaDon());
            } else {
                JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                            FROM Voucher
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
                    tongTien -= giamGia;
                        if (tongTien < 0) {
                            tongTien = 0;
                        }
                        double thue = tongTien * 0.10;
                        tongTien += thue;
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
        FROM Voucher
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
    int maVoucher = 17;  // Assuming a static voucher for now
    float tongTien = giaBan * soLuong;
    
    String insertSql = """
        INSERT INTO ChiTietDonHang (ma_don_hang, ma_san_pham, so_luong, gia_ban, tong_tien, thue, ma_voucher)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;
    String updateSql = """
        UPDATE SanPham
        SET so_luong_ton = so_luong_ton - ?
        WHERE id_ma_san_pham = ?
    """;
    String paymentInfoSql = """
        SELECT 
            SUM(tong_tien) AS tong_tien, 
            SUM(tong_tien * thue / 100) AS tong_thue
        FROM ChiTietDonHang
        WHERE ma_don_hang = ?
    """;
    String voucherSql = """
        SELECT TOP 1 id_voucher, gia_tri 
        FROM Voucher
        WHERE trang_thai = N'Chưa bắt đầu'
          AND ngay_bat_dau <= GETDATE()
          AND ngay_ket_thuc >= GETDATE()
        ORDER BY gia_tri DESC
    """;

    try (Connection con = ketnoi.getConnection()) {
        con.setAutoCommit(false);

        try (PreparedStatement psInsert = con.prepareStatement(insertSql);
             PreparedStatement psUpdate = con.prepareStatement(updateSql);
             PreparedStatement psPaymentInfo = con.prepareStatement(paymentInfoSql);
             PreparedStatement psVoucher = con.prepareStatement(voucherSql)) {

            // Inserting the product into the order details
            psInsert.setInt(1, maDonHang);
            psInsert.setInt(2, maSanPham);
            psInsert.setInt(3, soLuong);
            psInsert.setFloat(4, giaBan);
            psInsert.setFloat(5, tongTien);
            psInsert.setDouble(6, 10.00);  // Assuming tax is 10%
            psInsert.setInt(7, maVoucher);
            int rowsAffected = psInsert.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sản phẩm đã được thêm vào đơn hàng chi tiết.");
                psUpdate.setInt(1, soLuong);
                psUpdate.setInt(2, maSanPham);
                int updateRows = psUpdate.executeUpdate();
                if (updateRows > 0) {
                    System.out.println("Số lượng sản phẩm đã được cập nhật trong bảng SanPham.");
                    psPaymentInfo.setInt(1, maDonHang);
                    ResultSet rs = psPaymentInfo.executeQuery();
                    if (rs.next()) {
                        float totalAmount = rs.getFloat("tong_tien");
                        float totalTax = rs.getFloat("tong_thue");
                      
                        float finalAmount = totalAmount + totalTax;

                        DecimalFormat formatter = new DecimalFormat("#,###.##");
                        txttongSoTien.setText("Tổng Tiền: " + formatter.format(totalAmount));
                        txtthue.setText("Thuế: " + formatter.format(totalTax));
                        txtthanhtien.setText("Thành Tiền: " + formatter.format(finalAmount));
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
                                double moneyLeft = inputAmount - finalAmount;
                                if (moneyLeft < 0) {
                                    txtTienTraKhac.setText("Số tiền bạn nhập không đủ để thanh toán!");
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
                    con.commit(); // Commit the transaction
                } else {
                    System.out.println("Lỗi khi cập nhật số lượng sản phẩm.");
                    con.rollback(); // Rollback if update fails
                }
            } else {
                System.out.println("Lỗi khi thêm sản phẩm vào đơn hàng chi tiết.");
                con.rollback(); // Rollback if insert fails
            }
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback(); // Rollback in case of error during the transaction
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}







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
//        int selectedRow = tbl_sanPham1.getSelectedRow();
//        if (selectedRow != -1) {
//            int maSanPham = (int) tbl_sanPham1.getValueAt(selectedRow, 0);
//            String tenSanPham = (String) tbl_sanPham1.getValueAt(selectedRow, 2);
//            float giaBan = (float) tbl_sanPham1.getValueAt(selectedRow, 4);
//            int soLuongTon = (int) tbl_sanPham1.getValueAt(selectedRow, 3);
//            String input = javax.swing.JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm để thêm vào hóa đơn:");
//
//            if (input != null) {
//                input = input.trim(); // Loại bỏ khoảng trắng thừa ở đầu và cuối chuỗi
//
//                if (input.isEmpty()) {
//                    // Nếu người dùng không nhập gì
//                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
//                } else {
//                    try {
//                        int soLuong = Integer.parseInt(input); // Chuyển chuỗi thành số nguyên
//                        // Xử lý logic thêm sản phẩm vào hóa đơn ở đây
//                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm đã được thêm: " + soLuong);
//                    } catch (NumberFormatException e) {
//                        // Nếu người dùng nhập không phải là số hợp lệ
//                        JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ.");
//                    }
//                }
//            } else {
//                // Người dùng nhấn Cancel hoặc đóng cửa sổ
//                System.out.println("Người dùng đã nhấn Cancel hoặc đóng cửa sổ.");
//            }
//
//            try {
//                int soLuong = Integer.parseInt(input);
//                if (soLuong > 0) {
//                    if (soLuongTon < soLuong) {
//                        JOptionPane.showMessageDialog(panel, "Số Lượng Tồn Nhỏ Hơn ");
//                    } else {
//                        addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuong);
//                        hienThiDuLieuuu(ls.getSanPhamChhiTiet());
//                        hienThiDuLieuGioHangg();
//                        int selectedRowHoaDon = tbldanhsachdonhagcho.getSelectedRow(); // Kiểm tra xem có hàng nào được chọn không
//                        if (selectedRowHoaDon != -1) {
//                            int maGioHangHienTai = Integer.parseInt(tbldanhsachdonhagcho.getValueAt(selectedRowHoaDon, 0).toString());
//                            hienThiDuLieuGioHang(maGioHangHienTai);
//                        }
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
//            }
//        }
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
                            hienThiDuLieuGioHangg();
                            int selectedRowHoaDon = tbldanhsachdonhagcho.getSelectedRow();
                            if (selectedRowHoaDon != -1) {
                                int maGioHangHienTai = Integer.parseInt(tbldanhsachdonhagcho.getValueAt(selectedRowHoaDon, 0).toString());
                                hienThiDuLieuGioHangg(maGioHangHienTai);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        // Nếu người dùng nhập không phải là số hợp lệ
//                        JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ.");
                    }
                }
            } else {
                // Người dùng nhấn Cancel hoặc đóng cửa sổ
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
//    int selectedRowHoaDon = tbldanhsachdonhagcho.getSelectedRow();
//    if (selectedRowHoaDon != -1) {
//        int maGioHangHienTai = Integer.parseInt(tbldanhsachdonhagcho.getValueAt(selectedRowHoaDon, 0).toString());
//        hienThiDuLieuGioHang(maGioHangHienTai);
//    }            

    }//GEN-LAST:event_hienthigiohangMouseClicked
    private void xoaSanPhamKhoiGioHang(int soLuongTrongGioHang) {
        Connection conn = null;
        PreparedStatement psDelete = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psGetQuantity = null;
        int duyem = Integer.parseInt(txtid.getText());
        try {
            conn = ketnoi.getConnection();
            String sqlGetQuantity = "SELECT so_luong FROM chitietdonhang WHERE ma_don_hang = ?";
            psGetQuantity = conn.prepareStatement(sqlGetQuantity);
            psGetQuantity.setInt(1, duyem);
            ResultSet rs = psGetQuantity.executeQuery();
            if (rs.next()) {
                int soLuongCanXoa = rs.getInt("so_luong");
                String sqlUpdate = "UPDATE SanPham SET so_luong_ton = so_luong_ton + ? "
                        + "WHERE id_ma_san_pham IN (SELECT ma_san_pham FROM chitietdonhang WHERE ma_don_hang = ?)";
                psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setInt(1, soLuongCanXoa);
                psUpdate.setInt(2, duyem);
                int rowsUpdated = psUpdate.executeUpdate();
                if (rowsUpdated > 0) {
                    String sqlDelete = "DELETE FROM chitietdonhang WHERE ma_don_hang = ?";
                    psDelete = conn.prepareStatement(sqlDelete);
                    psDelete.setInt(1, duyem);
                    int rowsDeleted = psDelete.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công và cập nhật tồn kho!");
                        hienThiDuLieuGioHangg();
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa sản phẩm khỏi giỏ hàng!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật số lượng tồn kho!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm trong giỏ hàng!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (psDelete != null) {
                    psDelete.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (psGetQuantity != null) {
                    psGetQuantity.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void xoaSanPhamKhoiGioHangChiTiet(int idChiTietDonHang) {
        Connection conn = null;
        PreparedStatement psDelete = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psGetQuantity = null;

        // Lấy dòng được chọn trong bảng
        int selectedRow = hienthigiohang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa!");
            return;
        }

        // Lấy id_ma_chi_tiet_don_hang từ cột đầu tiên (cột 0)
        int duyem = Integer.parseInt(hienthigiohang.getValueAt(selectedRow, 0).toString());

        try {
            conn = ketnoi.getConnection();

            // Lấy số lượng sản phẩm từ bảng chitietdonhang
            String sqlGetQuantity = "SELECT so_luong FROM chitietdonhang WHERE id_ma_chi_tiet_don_hang = ?";
            psGetQuantity = conn.prepareStatement(sqlGetQuantity);
            psGetQuantity.setInt(1, duyem);
            ResultSet rs = psGetQuantity.executeQuery();

            if (rs.next()) {
                int soLuongCanXoa = rs.getInt("so_luong");

                // Cập nhật số lượng tồn kho trong bảng SanPham
                String sqlUpdate = "UPDATE SanPham SET so_luong_ton = so_luong_ton + ? "
                        + "WHERE id_ma_san_pham IN (SELECT ma_san_pham FROM chitietdonhang WHERE id_ma_chi_tiet_don_hang = ?)";
                psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setInt(1, soLuongCanXoa);
                psUpdate.setInt(2, duyem);

                int rowsUpdated = psUpdate.executeUpdate();
                if (rowsUpdated > 0) {
                    // Xóa sản phẩm khỏi bảng chitietdonhang
                    String sqlDelete = "DELETE FROM chitietdonhang WHERE id_ma_chi_tiet_don_hang = ?";
                    psDelete = conn.prepareStatement(sqlDelete);
                    psDelete.setInt(1, duyem);

                    int rowsDeleted = psDelete.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công và cập nhật tồn kho!");
                        hienThiDuLieuGioHangg(); // Cập nhật lại giao diện
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa sản phẩm khỏi giỏ hàng!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật số lượng tồn kho!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm trong giỏ hàng!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (psDelete != null) {
                    psDelete.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (psGetQuantity != null) {
                    psGetQuantity.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int id = Integer.parseInt(txtid.getText().trim());

        // Kiểm tra trạng thái checkbox
        if (xoaTatCa.isSelected()) {
            // Nếu checkbox được chọn, xóa tất cả sản phẩm
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                xoaSanPhamKhoiGioHang(id);
                hienThiDuLieuGioHang(hd.layGioHangSanPham(id));
                hienThiDuLieuuu(ls.getSanPhamChhiTiet());
            }
        } else {
            // Nếu checkbox không được chọn, yêu cầu chọn sản phẩm cụ thể
            int selectedRow = hienthigiohang.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }

            try {
                int soLuongTrongGioHang = Integer.parseInt(hienthigiohang.getValueAt(selectedRow, 0).toString());
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    xoaSanPhamKhoiGioHangChiTiet(soLuongTrongGioHang);
                    hienThiDuLieuGioHang(hd.layGioHangSanPham(id));
                    hienThiDuLieuuu(ls.getSanPhamChhiTiet());
                    updateCartAndRecalculateVoucher();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm: " + ex.getMessage());
                ex.printStackTrace();
            }
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
    private void capNhapSoLuongTru(){
        int selectRow = hienthigiohang.getSelectedRow();
        if(selectRow == -1){
            JOptionPane.showMessageDialog(hienthigiohang, "Vui lòng chọn sản phẩm trong giỏ hàng muốn cập nhập");
            return;
        }
        int idChiTietDonHang = Integer.parseInt(hienthigiohang.getValueAt(selectRow,0).toString());
        int soLuongHienTai = Integer.parseInt(hienthigiohang.getValueAt(selectRow, 4).toString());
        String loLuongMoi = JOptionPane.showInputDialog(this, "Mời Nhập Số Lượng");
        if(loLuongMoi == null || loLuongMoi.trim().isEmpty()){
            JOptionPane.showMessageDialog(hienthigiohang, "Số Lượng Không Hợp Lệ");
            return;
        }
        try {
//            int soLuongMoi = Integer.parseInt()
            Connection con = ketnoi.getConnection();
        } catch (Exception e) {
        }
    }
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
                        + "(SELECT ma_san_pham FROM chitietdonhang WHERE id_ma_chi_tiet_don_hang = ?)";
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
                            + "WHERE id_ma_san_pham IN (SELECT ma_san_pham FROM chitietdonhang WHERE id_ma_chi_tiet_don_hang = ?)";
                    psUpdateSanPham = conn.prepareStatement(sqlUpdateSanPham);
                    psUpdateSanPham.setInt(1, Math.abs(thayDoiSoLuong)); // Cộng số lượng bị giảm vào tồn kho
                    psUpdateSanPham.setInt(2, idChiTietDonHang);
                    int rowsUpdatedSanPham = psUpdateSanPham.executeUpdate();
                    if (rowsUpdatedSanPham > 0) {
                        
                    } else {
                        System.out.println("Không cập nhật được bảng Sản Phẩm!");
                    }
                }
                else if (thayDoiSoLuong > 0) {
                    String sqlUpdateSanPham = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? "
                            + "WHERE id_ma_san_pham IN (SELECT ma_san_pham FROM chitietdonhang WHERE id_ma_chi_tiet_don_hang = ?)";
                    psUpdateSanPham = conn.prepareStatement(sqlUpdateSanPham);
                    psUpdateSanPham.setInt(1, Math.abs(thayDoiSoLuong)); // Trừ số lượng vào tồn kho
                    psUpdateSanPham.setInt(2, idChiTietDonHang);
                    int rowsUpdatedSanPham = psUpdateSanPham.executeUpdate();
                    if (rowsUpdatedSanPham > 0) {
               
                    } else {
                        System.out.println("Không cập nhật được bảng SanPham!");
                    }
                }
                String sqlUpdateChiTiet = "UPDATE chitietdonhang SET so_luong = ? WHERE id_ma_chi_tiet_don_hang = ?";
                psUpdateChiTietDonHang = conn.prepareStatement(sqlUpdateChiTiet);
                psUpdateChiTietDonHang.setInt(1, soLuongMoi);
                psUpdateChiTietDonHang.setInt(2, idChiTietDonHang);
                int rowsUpdatedChiTiet = psUpdateChiTietDonHang.executeUpdate();

                if (rowsUpdatedChiTiet > 0) {
                    hienThiDuLieuGioHangg(); // Refresh lại dữ liệu giỏ hàng
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
        chuyenkhoan.setText("");
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
                                // Làm sạch đầu vào, loại bỏ dấu phẩy và khoảng trắng
                                String cleanedInput = inputAmountStr.replaceAll("[,\\s]", "");
                                double inputAmount = Double.parseDouble(cleanedInput);

                                // Tính số tiền còn thiếu sau khi thanh toán tiền mặt
                                double moneyLeft = thanhTien - inputAmount;

                                // Nếu số tiền khách đã thanh toán đủ (hoặc vượt), hiển thị 0
                                if (moneyLeft <= 0) {
                                chuyenkhoan.setText(formatter.format(Math.abs(moneyLeft)) + " VND"); // Lấy giá trị tuyệt đối để không có dấu "-"
                            } else {
                                // Nếu số tiền còn thiếu, hiển thị số tiền cần chuyển khoản
                                chuyenkhoan.setText(formatter.format(moneyLeft) + " VND");
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
            // Handle the case when the cleaned input still cannot be parsed
            System.out.println("Invalid input format for thanhTien.");
        }

        }
    }//GEN-LAST:event_cbo_hinhThucThanhToanActionPerformed


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
