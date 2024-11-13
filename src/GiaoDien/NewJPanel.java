/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.HoaDonEntity;
import Entity.LayMaHoaDon;
import Entity.SanPhamEntity;
import Repository.BanHangTesst;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import KetNoiSQL.ketnoi;
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
/**
 *
 * @author SingPC
 */
public class NewJPanel extends javax.swing.JPanel implements Runnable, ThreadFactory {
  DecimalFormat formatter = new DecimalFormat("###,###,###");
    private double tongTien = 0;
      private DefaultTableModel md = new DefaultTableModel();
      private BanHangTesst ls = new BanHangTesst();
      private Webcam webcam = null ;
private WebcamPanel panel = null;
private Executor executor = Executors.newSingleThreadExecutor(this);
    /**
     * Creates new form NewJPanel
     */
    public NewJPanel() {
        initComponents();
          hienThiDuLieu(ls.getSanPhamChhiTiet());
          initWebcam();
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
        md.setRowCount(0);
        for (HoaDonEntity ls : lss) {
            md.addRow(new Object[]{
                ls.getIdHoaDon(), ls.getMaHoaDon(), ls.getNgayLap(), ls.getTenNhanVien(), ls.getTenKhachHang()
            });
        }
    }
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
    executor.execute( this);
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
    ArrayList<SanPhamEntity> ls = new ArrayList<>();
    String sql = "SELECT ma_san_pham, ten_san_pham, so_luong_ton, gia_ban FROM SanPham WHERE ma_san_pham = ?";
    
    try (Connection con = ketnoi.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setObject(1, maSanPhamHoaDon);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                SanPhamEntity sanPham = new SanPhamEntity();
                sanPham.setMaSanPham(rs.getString("ma_san_pham"));
                sanPham.setTenSanPham(rs.getString("ten_san_pham"));
                sanPham.setSoLuong(rs.getInt("so_luong_ton"));
                sanPham.setGiaBan(rs.getFloat("gia_ban"));
                ls.add(sanPham);
            } else {
                System.out.println("Không tìm thấy sản phẩm với mã: " + maSanPhamHoaDon);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Nhập số lượng sản phẩm từ người dùng
    String input = javax.swing.JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm để thêm vào hóa đơn:", "Số lượng sản phẩm", javax.swing.JOptionPane.QUESTION_MESSAGE);
    try {
        int soLuongg = Integer.parseInt(input);
        if (soLuongg <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
            return;
        }

        // Gọi phương thức để thêm sản phẩm vào hóa đơn
        addAllProductsToOrder(ls, soLuongg);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
    }
}

// Thêm tất cả sản phẩm vào chi tiết hóa đơn
private void addAllProductsToOrder(ArrayList<SanPhamEntity> ls, int soLuongg) {
    // Lấy sản phẩm từ danh sách đã tìm
    for (SanPhamEntity sanPham : ls) {
        int maSanPham = Integer.parseInt(sanPham.getMaSanPham().replace("SP", ""));  // Nếu maSanPham là chuỗi như "SP0001", chuyển thành int
        String tenSanPham = sanPham.getTenSanPham();
        float giaBan = sanPham.getGiaBan();

        if (soLuongg > 0) {
            // Thêm sản phẩm vào chi tiết hóa đơn và cập nhật thanh toán
            addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuongg);

            // Cập nhật số lượng sản phẩm trong kho
            updateProductQuantity(maSanPham, soLuongg);
        } else {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0.");
        }
    }
}

// Cập nhật số lượng tồn kho trong cơ sở dữ liệu
private void updateProductQuantity(int maSanPham, int soLuong) {
    String sql = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? WHERE ma_san_pham = ?";
    try (Connection con = ketnoi.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, soLuong);
        stmt.setInt(2, maSanPham);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cập nhật số lượng tồn kho thành công.");
        } else {
            System.out.println("Không thể cập nhật số lượng tồn kho.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


private int nhapSoLuong(SanPhamEntity sanPham) {
    while (true) {
        String input = JOptionPane.showInputDialog(this, "Nhập số lượng cho sản phẩm " + sanPham.getTenSanPham() + ":");
        if (input == null || input.isEmpty()) {
            return -1;  // Người dùng hủy hoặc không nhập gì
        }

        try {
            int soLuong = Integer.parseInt(input);
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng nhập lại.");
            }  else {
                return soLuong;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ.");
        }
    }
}


@Override
public Thread newThread(Runnable r) {
    Thread t = new Thread(r, "WebcamThread");
    t.setDaemon(true);
    return t;
}
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        txthoadon = new javax.swing.JLabel();
        jPanel_DonHang1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_chon1 = new javax.swing.JButton();
        btn_thaydoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
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
        txtmahoadon = new javax.swing.JLabel();
        btn_taoma = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JLabel();
        txtthue = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        txtid = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        duyemWebcam = new javax.swing.JPanel();

        jLabel13.setText("Bảng Sản Phẩm");

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

        txthoadon.setBackground(new java.awt.Color(255, 102, 102));
        txthoadon.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txthoadonAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel_DonHang1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_DonHang1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_DonHang1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã Khách Hàng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên Khách Hàng:");

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("KH00");

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Khách Mua Tại Quầy");

        btn_chon1.setBackground(new java.awt.Color(204, 204, 204));
        btn_chon1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_chon1.setForeground(new java.awt.Color(51, 51, 51));
        btn_chon1.setText("Chọn");
        btn_chon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chon1ActionPerformed(evt);
            }
        });

        btn_thaydoi.setBackground(new java.awt.Color(204, 204, 204));
        btn_thaydoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_thaydoi.setForeground(new java.awt.Color(51, 51, 51));
        btn_thaydoi.setText("Thay Đổi");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_chon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_thaydoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(btn_chon1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(btn_thaydoi))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mã Hoá Đơn:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tổng Tiền:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Giảm Giá:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Thanh Toán:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tiền Khách Đưa:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tiền Thừa Trả Khách:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Hình Thức Thanh Toán:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Thuế");

        cbo_hinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản", "Quẹt Thẻ", " " }));

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

        txtmahoadon.setText("Vui lòng tạo!");
        txtmahoadon.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtmahoadonAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

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

        jLabel19.setText("VNĐ");

        jLabel20.setText("VNĐ");

        jLabel21.setText("VNĐ");

        jLabel22.setText("VNĐ");

        jLabel23.setText("VNĐ");

        txtGiamGia.setText("0");

        javax.swing.GroupLayout jPanel_DonHang1Layout = new javax.swing.GroupLayout(jPanel_DonHang1);
        jPanel_DonHang1.setLayout(jPanel_DonHang1Layout);
        jPanel_DonHang1Layout.setHorizontalGroup(
            jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                        .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel16))
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel14))
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jButton12))
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(jLabel17))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_DonHang1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel9)
                                        .addGap(65, 65, 65)))
                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_DonHang1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txtmahoadon)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_taoma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTienTraKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)))
                                            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                                                    .addGap(124, 124, 124)
                                                                    .addComponent(jLabel21))
                                                                .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(txttongSoTien, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel19)
                                                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                                                        .addComponent(txtthue, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)))
                                                .addGap(0, 0, Short.MAX_VALUE)))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_DonHang1Layout.setVerticalGroup(
            jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DonHang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_taoma)
                    .addComponent(txtmahoadon)
                    .addComponent(jLabel9))
                .addGap(14, 14, 14)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel19))
                    .addComponent(txttongSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel20))
                    .addComponent(txtGiamGia))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtthanhtien)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTienTraKhac)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17)
                    .addComponent(txtthue, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_DonHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(18, 18, 18)
                .addComponent(jButton14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Thêm Sản Phẩm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField1.setText("jTextField1");

        txtid.setForeground(new java.awt.Color(255, 0, 102));
        txtid.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtidAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jButton2.setText("Tìm Sản Phẩm Bằng QR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(427, 427, 427)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(duyemWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(326, 326, 326))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txthoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_DonHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txthoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(duyemWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(5, 5, 5)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(jPanel_DonHang1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_chon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chon1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_chon1ActionPerformed

    private void txttongSoTienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txttongSoTienAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongSoTienAncestorAdded

    private void btn_taomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_taomaMouseClicked
        // TODO add your handling code here:
int idNhanVien = LuuThongTinDangNhap.getInNhanVien(); // Lấy ID nhân viên
int maKhachHang = 3; // Giả sử mã khách hàng được gán cố định
String trangThai = "đang chờ thanh toán"; 
String trangThaiDonHnag ="đang chờ thanh toán";// Trạng thái hóa đơn
String hinhThuc = "Đang Đợi"; // Hình thức chuyển tiền
String hinhThucBanHang = "Hình Thức Bán Hàng"; // Hình thức bán hàng

String sqlDonHang = """
        INSERT INTO DonHang (ngay_dat, ma_nhan_vien, ma_khach_hang,trang_thai)
        VALUES (GETDATE(), ?, ?,?)
        """;

String sqlHoaDon = """
        INSERT INTO HoaDon (ngay_lap, tien_khach_dua, tien_tra_khach, phuong_thuc, trang_thai, ma_don_hang)
        VALUES (GETDATE(), ?, ?, ?, ?, ?)
        """;

try (Connection con = ketnoi.getConnection()) {
    // Tắt tự động commit để bắt đầu giao dịch
    con.setAutoCommit(false);

    // Tạo PreparedStatement để thực thi câu lệnh SQL cho đơn hàng
    try (PreparedStatement psDonHang = con.prepareStatement(sqlDonHang, Statement.RETURN_GENERATED_KEYS)) {
        psDonHang.setInt(1, idNhanVien);  // ID nhân viên tạo hóa đơn
        psDonHang.setInt(2, maKhachHang);
        psDonHang.setString(3, trangThaiDonHnag);// Mã khách hàng
        int rowsAffectedDonHang = psDonHang.executeUpdate();

        if (rowsAffectedDonHang > 0) {
            // Lấy mã đơn hàng vừa được thêm vào (auto-incremented ID)
            try (ResultSet rs = psDonHang.getGeneratedKeys()) {
                if (rs.next()) {
                    int maDonHang = rs.getInt(1);  // Mã đơn hàng mới tạo
                    float tienKhacDua =0;
                    float tienTraKhach =0;
                    String trangTThai ="Đang Chờ Thanh Toán";
                    // Tạo PreparedStatement để thêm hóa đơn
                    try (PreparedStatement psHoaDon = con.prepareStatement(sqlHoaDon)) {
                        // Cập nhật thông tin vào bảng HoaDon
                        psHoaDon.setDouble(1, tienKhacDua); // Giả sử tiền khách đưa (tạm)
                        psHoaDon.setDouble(2, tienTraKhach); // Tiền trả khách (tạm)
                        psHoaDon.setObject(3, cbo_hinhThucThanhToan.getSelectedItem()); // Phương thức thanh toán
                        psHoaDon.setString(4, trangTThai); // Trạng thái hóa đơn
                        psHoaDon.setInt(5, maDonHang); // Mã đơn hàng

                        int rowsAffectedHoaDon = psHoaDon.executeUpdate(); // Thực thi câu lệnh

                        if (rowsAffectedHoaDon > 0) {
                            // Commit giao dịch nếu tất cả đều thành công
                            con.commit();
                            System.out.println("Đơn hàng và hóa đơn đã được tạo thành công.");
                        } else {
                            // Rollback nếu không thêm được hóa đơn
                            con.rollback();
                            System.out.println("Không thể thêm hóa đơn vào cơ sở dữ liệu.");
                        }
                    } catch (SQLException e) {
                        // Rollback nếu có lỗi khi thêm hóa đơn
                        con.rollback();
                        System.out.println("Lỗi khi thực thi câu lệnh SQL cho hóa đơn: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                // Rollback nếu không lấy được mã đơn hàng
                con.rollback();
                System.out.println("Lỗi khi lấy mã đơn hàng mới: " + e.getMessage());
            }
        } else {
            // Rollback nếu không thể thêm đơn hàng
            con.rollback();
            System.out.println("Không thể thêm đơn hàng vào cơ sở dữ liệu.");
        }
    } catch (SQLException e) {
        // Rollback nếu có lỗi khi thêm đơn hàng
        con.rollback();
        System.out.println("Lỗi khi thực thi câu lệnh SQL cho đơn hàng: " + e.getMessage());
    }
} catch (SQLException e) {
    e.printStackTrace(); // In ra lỗi nếu có vấn đề với kết nối
}




    }//GEN-LAST:event_btn_taomaMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
String idmaDonHnag = txtid.getText();
     String maDonHang = txtmahoadon.getText();
String trangThaiHoaDon = "Đã thanh toán";
String sql = """
    UPDATE hoadon
    SET ngay_lap = GETDATE(), tien_khach_dua = ?, tien_tra_khach = ?, phuong_thuc = ?, trang_thai = ?
    WHERE ma_hoa_don = ?
    """;

String trangThaiDonHang = "Đã Thanh Toán";
String DonHang = txthoadon.getText();
String sqlDonHang = """
    UPDATE donhang
    SET trang_thai = ?
    WHERE ma_don_hang = ?
    """;

try {
    // Kết nối cơ sở dữ liệu
    Connection con = ketnoi.getConnection();

    // Cập nhật bảng `donhang`
    PreparedStatement psDonHang = con.prepareStatement(sqlDonHang);
    psDonHang.setObject(1, trangThaiDonHang);
    psDonHang.setObject(2, DonHang);
    int checkDonHang = psDonHang.executeUpdate();

    // Cập nhật bảng `hoadon`
    double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText().trim().replace(",", ""));
    double tienTraKhach = Double.parseDouble(txtTienTraKhac.getText().trim().replace(",", ""));
    String phuongThuc = cbo_hinhThucThanhToan.getSelectedItem().toString();

    PreparedStatement psHoaDon = con.prepareStatement(sql);
    psHoaDon.setObject(1, tienKhachDua);
    psHoaDon.setObject(2, tienTraKhach);
    psHoaDon.setObject(3, phuongThuc);
    psHoaDon.setObject(4, trangThaiHoaDon);
    psHoaDon.setObject(5, maDonHang);
    int checkHoaDon = psHoaDon.executeUpdate();

    // Thông báo kết quả
    if (checkDonHang > 0 && checkHoaDon > 0) {
        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
  try {
    // Đường dẫn file PDF
    File file = new File("D:\\HoaDon_" + maDonHang + ".pdf");
    if (file.exists()) {
        file.delete();
    }

    // Khởi tạo đối tượng PDF
    PdfWriter writer = new PdfWriter(file);
    PdfDocument pdf = new PdfDocument(writer);
    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);

    Connection conn = ketnoi.getConnection();

    String sqlHoaDon = """
        SELECT nv.ten_nhan_vien, kh.ten_khach_hang, hd.ngay_lap, ctdh.tong_tien
        FROM DonHang dh 
        JOIN HoaDon hd ON hd.ma_don_hang = dh.id_ma_don_hang
        JOIN NhanVien nv ON dh.ma_nhan_vien = nv.id_ma_nhan_vien
        JOIN KhachHang kh ON dh.ma_khach_hang = kh.id_ma_khach_hang
        JOIN ChiTietDonHang ctdh ON dh.id_ma_don_hang = ctdh.ma_don_hang
        WHERE hd.ma_hoa_don = ?
    """;
    PreparedStatement psHoaDonnn = conn.prepareStatement(sqlHoaDon);
    psHoaDonnn.setString(1, maDonHang);
    ResultSet rsHoaDon = psHoaDonnn.executeQuery();

    String tenNhanVien = "", tenKhachHang = "";
    String ngayLap = "";
    double tongTien = 0;
    if (rsHoaDon.next()) {
        tenNhanVien = rsHoaDon.getString("ten_nhan_vien");
        tenKhachHang = rsHoaDon.getString("ten_khach_hang");
        ngayLap = rsHoaDon.getString("ngay_lap");
        tongTien = rsHoaDon.getDouble("tong_tien");
    }

    // Tiêu đề và thông tin hóa đơn
    document.add(new Paragraph("HÓA ĐƠN THANH TOÁN")
            .setFont(font)
            .setFontSize(20)
            .setTextAlignment(TextAlignment.CENTER)
            .setBold());
    document.add(new Paragraph("Mã Hóa Đơn: " + maDonHang).setFont(font).setFontSize(12));
    document.add(new Paragraph("Nhân Viên: " + tenNhanVien).setFont(font).setFontSize(12));
    document.add(new Paragraph("Khách Hàng: " + tenKhachHang).setFont(font).setFontSize(12));
    document.add(new Paragraph("Ngày Lập: " + ngayLap).setFont(font).setFontSize(12));
    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));

    // Lấy thông tin chi tiết đơn hàng
    String sqlChiTietDonHang = """
        SELECT sp.ten_san_pham, ctdh.so_luong, ctdh.gia_ban
        FROM ChiTietDonHang ctdh
        JOIN SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
        WHERE ctdh.ma_don_hang = ?
    """;
    PreparedStatement psChiTietDonHang = conn.prepareStatement(sqlChiTietDonHang);
    psChiTietDonHang.setString(1, idmaDonHnag);
    ResultSet rsChiTietDonHang = psChiTietDonHang.executeQuery();

    // Tạo bảng chi tiết sản phẩm
    Table table = new Table(3);
    table.setWidth(pdf.getDefaultPageSize().getWidth() * 0.9f);

    // Thêm tiêu đề cột
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

    // Thêm dữ liệu sản phẩm vào bảng
    while (rsChiTietDonHang.next()) {
        String tenSanPham = rsChiTietDonHang.getString("ten_san_pham");
        int soLuong = rsChiTietDonHang.getInt("so_luong");
        double giaBan = rsChiTietDonHang.getDouble("gia_ban");

        table.addCell(new Paragraph(tenSanPham).setFont(font));
        table.addCell(new Paragraph(String.valueOf(soLuong)).setFont(font).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Paragraph(String.format("%,.0f", giaBan)).setFont(font).setTextAlignment(TextAlignment.CENTER));
    }

    document.add(table);
    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));

    // Tổng tiền
    document.add(new Paragraph("TỔNG TIỀN: " + String.format("%,.0f", tongTien) + " VND")
            .setFont(font)
            .setFontSize(12)
            .setBold()
            .setTextAlignment(TextAlignment.RIGHT));

    // Thông tin ngân hàng và liên hệ
    document.add(new Paragraph("GỬI THANH TOÁN ĐẾN")
            .setFont(font)
            .setFontSize(10)
            .setMarginTop(20));
    document.add(new Paragraph("Số ngân hàng: 0367548754 \nTên ngân hàng: Đoàn Ngọc Duy")
            .setFont(font)
            .setFontSize(10));
    document.add(new Paragraph("LIÊN HỆ")
            .setFont(font)
            .setFontSize(10)
            .setMarginTop(-40)
            .setTextAlignment(TextAlignment.RIGHT));
    document.add(new Paragraph("doanngocduy62@gmail.com\nSĐT: 0382424762")
            .setFont(font)
            .setFontSize(10)
            .setTextAlignment(TextAlignment.RIGHT));

    document.close();
    JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
} catch (IOException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn PDF!");
} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu!");
}


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
private void addProductToOrderDetailAndUpdatePayment(int maSanPham, String tenSanPham, float giaBan, int soLuong) {
    int maDonHang = Integer.parseInt(txtid.getText()); // Lấy mã đơn hàng từ txthoadon
    float tongTien = giaBan * soLuong; // Tính tổng tiền cho sản phẩm
    String insertSql = """
        INSERT INTO ChiTietDonHang (ma_don_hang, ma_san_pham, so_luong, gia_ban, tong_tien, ma_voucher, trang_thai, thue)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """; 
    String updateSql = """
        UPDATE SanPham
        SET so_luong_ton = so_luong_ton - ?
        WHERE id_ma_san_pham = ?
    """;

    // SQL để tính tổng số lượng, tổng tiền, thuế và voucher trong ChiTietDonHang cho đơn hàng cụ thể
    String paymentInfoSql = """
        SELECT SUM(so_luong), SUM(tong_tien), SUM(tong_tien * (thue / 100)), COALESCE(SUM(gia_tri), 0)
        FROM ChiTietDonHang ctdh
        LEFT JOIN Voucher v ON ctdh.ma_voucher = v.id_voucher
        WHERE ma_don_hang = ?
    """; 
String trangThaiii = "Đang xử lý";
    try (Connection con = ketnoi.getConnection()) {
        // Bắt đầu một giao dịch (transaction) để đảm bảo tính nhất quán
        con.setAutoCommit(false); 

        try (PreparedStatement psInsert = con.prepareStatement(insertSql);
             PreparedStatement psUpdate = con.prepareStatement(updateSql);
             PreparedStatement psPaymentInfo = con.prepareStatement(paymentInfoSql)) {
            // Thêm sản phẩm vào ChiTietDonHang
            psInsert.setInt(1, maDonHang);   // Mã đơn hàng
            psInsert.setInt(2, maSanPham);   // Mã sản phẩm
            psInsert.setInt(3, soLuong);     // Số lượng
            psInsert.setFloat(4, giaBan);   // Giá bán
            psInsert.setFloat(5, tongTien); // Tổng tiền
            psInsert.setObject(6, null, Types.INTEGER); // Mã voucher (null nếu không có)
            psInsert.setString(7, trangThaiii); // Trạng thái ban đầu
            psInsert.setDouble(8, 10.00); // Thuế mặc định

            int rowsAffected = psInsert.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sản phẩm đã được thêm vào đơn hàng chi tiết.");

                // Cập nhật số lượng tồn trong bảng SanPham
                psUpdate.setInt(1, soLuong);    // Số lượng đã bán
                psUpdate.setInt(2, maSanPham); // Mã sản phẩm
                int updateRows = psUpdate.executeUpdate();
                if (updateRows > 0) {
                    System.out.println("Số lượng sản phẩm đã được cập nhật trong bảng SanPham.");
                } else {
                    System.out.println("Lỗi khi cập nhật số lượng sản phẩm.");
                }

                // Cập nhật thông tin thanh toán cho đơn hàng cụ thể
                psPaymentInfo.setInt(1, maDonHang);
                con.commit();
                ResultSet rs = psPaymentInfo.executeQuery();

                if (rs.next()) {
                    int totalQuantity = rs.getInt(1);      // Lấy tổng số lượng
                    float totalAmount = rs.getFloat(2);     // Lấy tổng tiền
                    float totalTax = rs.getFloat(3);        // Lấy tổng thuế
                    float discount = rs.getFloat(4);        // Lấy tổng giá trị voucher

                    // Tính tổng thanh toán
                    double finalAmount = totalAmount - discount + totalTax;

                    txttongSoTien.setText("Tổng Tiền: " + totalAmount + " VNĐ");
                    txtthue.setText("Thuế: " + totalTax + " VNĐ");
                    txtGiamGia.setText("Giảm Giá: " + discount + " VNĐ");
                    txtthanhtien.setText("Thành Tiền: " + finalAmount + " VNĐ");
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

                // Commit giao dịch
                con.commit();
            } else {
                System.out.println("Lỗi khi thêm sản phẩm vào đơn hàng chi tiết.");
                // Nếu có lỗi thì rollback giao dịch
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Nếu có lỗi thì rollback giao dịch
            con.rollback();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbl_sanPham.getSelectedRow();
    if (selectedRow != -1) {  // Kiểm tra nếu có dòng nào được chọn
        // Lấy thông tin sản phẩm từ dòng đã chọn
        int maSanPham = (int) tbl_sanPham.getValueAt(selectedRow, 0); // ID sản phẩm
        String tenSanPham = (String) tbl_sanPham.getValueAt(selectedRow, 2); // Tên sản phẩm
        float giaBan = (float) tbl_sanPham.getValueAt(selectedRow, 4); // Giá bán
            String input = javax.swing.JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm để thêm vào hóa đơn:", "Số lượng sản phẩm", javax.swing.JOptionPane.QUESTION_MESSAGE);
        try {
            int soLuong = Integer.parseInt(input);
            if (soLuong > 0) {
                addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuong); 
                hienThiDuLieu(ls.getSanPhamChhiTiet());// Sử dụng số lượng nhập từ người dùng
            } else {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txthoadonAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txthoadonAncestorAdded
        // TODO add your handling code here:
       ArrayList<HoaDonEntity> hdList = ls.getAll();
if (!hdList.isEmpty()) {
    HoaDonEntity firstOrder = hdList.get(0); // Lấy hóa đơn đầu tiên từ danh sách
    txthoadon.setText(firstOrder.getMaHoaDon());
     txtid.setText(String.valueOf(firstOrder.getIdHoaDon())); // Hiển thị mã hóa đơn đầu tiên lên JTextField
} else {
    txthoadon.setText("Không có đơn hàng nào đang xử lý.");
}
    }//GEN-LAST:event_txthoadonAncestorAdded

    private void txtidAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtidAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidAncestorAdded

    private void txtmahoadonAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtmahoadonAncestorAdded
        // TODO add your handling code here:
             ArrayList<LayMaHoaDon> hdList = ls.hoaDon();
if (!hdList.isEmpty()) {
    LayMaHoaDon firstOrder = hdList.get(0); // Lấy hóa đơn đầu tiên từ danh sách
    txtmahoadon.setText(firstOrder.getMaHoaDonnn());
    // Hiển thị mã hóa đơn đầu tiên lên JTextField
} else {
    txthoadon.setText("Không có đơn hàng nào đang xử lý.");
}
    }//GEN-LAST:event_txtmahoadonAncestorAdded

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2ActionPerformed
//  public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewJPanel().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_chon1;
    private javax.swing.JButton btn_taoma;
    private javax.swing.JButton btn_thaydoi;
    private javax.swing.JComboBox<String> cbo_hinhThucThanhToan;
    private javax.swing.JPanel duyemWebcam;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_DonHang1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JLabel txtGiamGia;
    private javax.swing.JLabel txtTienTraKhac;
    private javax.swing.JTextField txt_tienKhachDua;
    private javax.swing.JLabel txthoadon;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtmahoadon;
    private javax.swing.JLabel txtthanhtien;
    private javax.swing.JLabel txtthue;
    private javax.swing.JLabel txttongSoTien;
    // End of variables declaration//GEN-END:variables
}