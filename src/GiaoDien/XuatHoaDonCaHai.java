/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GiaoDien;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import Entity.DonHangChiTiet.DonHangChiTietEntity;
import Entity.HoaDon.HoaDonEntity;
import Entity.HoaDon.XuatHoaDon;
import Repository.HoaDonrepository;
import Repository.XuatHoaDonRepository;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import KetNoiSQL.ketnoi;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.IBlockElement;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import java.io.File;
import java.io.IOException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFont;
import static com.itextpdf.kernel.pdf.PdfName.Border;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;
import java.io.File;
import java.io.IOException;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.apache.poi.ss.usermodel.Cell;
/**
 *
 * @author SingPC
 */
public class XuatHoaDonCaHai extends javax.swing.JFrame {
 private XuatHoaDonRepository hd = new XuatHoaDonRepository();
     private HoaDonrepository hdd = new HoaDonrepository();
    private DefaultTableModel md = new DefaultTableModel();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat formatterr = new DecimalFormat("###,###,###");
    /**
     * Creates new form XuatHoaDonCaHai
     */
    public XuatHoaDonCaHai() {
        initComponents();
        loadDuLieuu();
        hienThiDuLieuGioHangg();
       layDuLieuTuBang();
    }
     private void loadDuLieuu() {
    ArrayList<XuatHoaDon> hdList = hd.getAll();
    System.out.println("Số lượng hóa đơn: " + hdList.size());
    if (!hdList.isEmpty()) {
        System.out.println("Dữ liệu hóa đơn đầu tiên: " + hdList.get(0).toString());
        XuatHoaDon firstOrder = hdList.get(0);
        nhanvien.setText(firstOrder.getTenNhanVien());
        txtmahoadon.setText(firstOrder.getMaHoaDon());
        txthotenkhachhang.setText(firstOrder.getTenKhachHang());
//         tongtien.setText(formatterr.format(firstOrder.getThanhTien()));
        // giamgia.setText(formatterr.format(firstOrder.getGiaTri()));  
        thue.setText(formatterr.format(firstOrder.getThue())+"%");        
        txtsodienthoaikhachhang.setText(firstOrder.getSoDienThoai());
        txtid.setText(String.valueOf(firstOrder.getIdHoaDon()));     
        phuongthucthanhtoan.setText(firstOrder.getPhuongThuc());
        Date ngayLap = (Date) firstOrder.getNgayDat();
        ngaytao.setText(dateFormat.format(ngayLap));
        tienMat.setText(formatterr.format(firstOrder.getTienKhachDua())); 
        giamgia.setText(formatterr.format(firstOrder.getGiamGia()));
         chuyenkhoan.setText(formatterr.format(firstOrder.getChuyenKhoan()));
    } else {
        nhanvien.setText("NULL");
        System.out.println("Danh sách hóa đơn trống.");
    }
}
    private void hienThiDuLieuGioHangg() {
    try {
        // Kiểm tra nếu txtid rỗng
        if (txtid.getText().trim().isEmpty()) {
           
            return;
        }
        int id = Integer.parseInt(txtid.getText().trim());
        ArrayList<DonHangChiTietEntity> ls = hdd.layGioHangSanPham(id);
        if (ls.isEmpty()) {
        } else {
            hienThiDuLieuGioHang(ls); // Hiển thị danh sách vào bảng
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + ex.getMessage());
        ex.printStackTrace();
    }
}
        private void hienThiDuLieuGioHang(ArrayList<DonHangChiTietEntity>chitiet){
        md = (DefaultTableModel) tblxuathoadon.getModel();
        md.setRowCount(0);
        for (DonHangChiTietEntity donHangChiTietEntity : chitiet) {
            md.addRow(new Object[]{
       donHangChiTietEntity.getTenSanPham(),donHangChiTietEntity.getSoLuong(), donHangChiTietEntity.getGiaBan()

            });
        }
        
    }
private ArrayList<DonHangChiTietEntity> layDuLieuTuBang() {
    ArrayList<DonHangChiTietEntity> danhSachChiTiet = new ArrayList<>();
    DefaultTableModel model = (DefaultTableModel) tblxuathoadon.getModel();
    double tongTien = 0; // Biến lưu tổng tiền

    for (int i = 0; i < model.getRowCount(); i++) {
        DonHangChiTietEntity chiTiet = new DonHangChiTietEntity();

        // Lấy dữ liệu từ bảng
        chiTiet.setTenSanPham((String) model.getValueAt(i, 0));
        int soLuong = (int) model.getValueAt(i, 1); // Số lượng
        double donGia = (double) model.getValueAt(i, 2); // Đơn giá
        chiTiet.setSoLuong(soLuong);
        chiTiet.setGiaBan(donGia);

        // Tính tổng tiền cho sản phẩm và cộng dồn
        tongTien += soLuong * donGia;

        danhSachChiTiet.add(chiTiet);
    }
    tongtienn.setText(formatterr.format(tongTien));
    return danhSachChiTiet;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Email = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        giamgia = new javax.swing.JLabel();
        tongtienn = new javax.swing.JLabel();
        txthotenkhachhang = new javax.swing.JLabel();
        txtsodienthoaikhachhang = new javax.swing.JLabel();
        diachi = new javax.swing.JLabel();
        nhanvien = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ngaytao = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtmahoadon = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        thue = new javax.swing.JLabel();
        txtemail = new javax.swing.JLabel();
        XuatHoaDon = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtid = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        phuongthucthanhtoan = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblxuathoadon = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        chuyenkhoan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tienMat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("THÔNG TIN HÓA ĐƠN");
        Email.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Nhân viên");
        Email.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("THÔNG TIN KHÁCH HÀNG");
        Email.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 180, 170, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Địa Chỉ");
        Email.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Email");
        Email.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Họ và tên");
        Email.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 210, 60, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Số điện thoại");
        Email.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 252, 90, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("SẢN PHẨM");
        Email.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jLabel20.setText("Giảm Giá");
        Email.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, -1));

        jLabel21.setText("Tổng Tiền");
        Email.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        giamgia.setText(" ");
        Email.add(giamgia, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, -1, -1));

        tongtienn.setText(" ");
        Email.add(tongtienn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 530, -1, -1));

        txthotenkhachhang.setText(" ");
        Email.add(txthotenkhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 232, 142, -1));

        txtsodienthoaikhachhang.setText("   ");
        Email.add(txtsodienthoaikhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 272, 142, -1));

        diachi.setText("   Hậu Ái, Vân Canh, Hoài Đức, Hà Nội");
        Email.add(diachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 210, -1));

        nhanvien.setText(" ");
        Email.add(nhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 121, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ngày Tạo");
        Email.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 150, -1, -1));

        ngaytao.setText("   ");
        Email.add(ngaytao, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 150, 85, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Mã Hóa Đơn");
        Email.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 116, -1, -1));

        txtmahoadon.setText("   ");
        Email.add(txtmahoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 116, 58, -1));

        jLabel32.setText("Thuế");
        Email.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 35, -1));

        thue.setText(" ");
        Email.add(thue, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 630, -1, -1));

        txtemail.setText("doanngocduy62@gmail.com");
        Email.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        XuatHoaDon.setText("Xuất Hóa Đơn");
        XuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XuatHoaDonActionPerformed(evt);
            }
        });
        Email.add(XuatHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, -1, -1));

        jButton2.setText("Quay Lại");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Email.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 720, -1, -1));

        txtid.setText(" ");
        Email.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 116, -1, -1));

        jLabel13.setText("Phương Thức Thanh Toán");
        Email.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, -1, -1));

        phuongthucthanhtoan.setText(" ");
        Email.add(phuongthucthanhtoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 670, 100, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Brown Simple Aesthetic Bag Store Logo 2.png"))); // NOI18N
        Email.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 140));

        tblxuathoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng", "Giá Bán"
            }
        ));
        jScrollPane1.setViewportView(tblxuathoadon);

        Email.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 550, 130));

        jLabel10.setText("Chuyển Khoản");
        Email.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 80, -1));
        Email.add(chuyenkhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 560, 70, 20));

        jLabel11.setText("Tiền Mặt");
        Email.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 80, -1));
        Email.add(tienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, 70, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void XuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XuatHoaDonActionPerformed
//        String idmaDonHnag = txtid.getText();
//        String maDonHang = txtmahoadon.getText();
//        String trangThaiHoaDon = "Thanh Toán Thành Công";
//        String sql = """
//        UPDATE hoadon
//        SET trang_thai = ?
//        WHERE ma_hoa_don = ?
//        """;
//        String trangThaiDonHang = "Thanh Toán Thành Công";
//        String sqlDonHang = """
//        UPDATE donhang
//        SET trang_thai = ?
//        WHERE id_ma_don_hang = ?
//        """;
//        try {
//            Connection con = ketnoi.getConnection();
//            PreparedStatement psDonHang = con.prepareStatement(sqlDonHang);
//            psDonHang.setObject(1, trangThaiDonHang);
//            psDonHang.setObject(2, idmaDonHnag);
//            int checkDonHang = psDonHang.executeUpdate();
//            PreparedStatement psHoaDon = con.prepareStatement(sql);
//            psHoaDon.setObject(1, trangThaiHoaDon);
//            psHoaDon.setObject(2, maDonHang);
//            int checkHoaDon = psHoaDon.executeUpdate();
//            if (checkDonHang > 0 && checkHoaDon > 0) {
//
//            } else {
//                JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //        try {
            //    String filePath = "D:\\" + txtmahoadon.getText() + ".pdf";
            //    File file = new File(filePath);
            //
            //    if (file.exists()) {
                //        file.delete();
                //    }
            //
            //    PdfWriter writer = new PdfWriter(file);
            //    PdfDocument pdf = new PdfDocument(writer);
            //    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
            //    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
            //
            //    // Thêm ảnh tiêu đề
            //    ImageData data = ImageDataFactory.create("D:\\anh\\duyem.png");
            //    com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);
            //    image.setHeight(150f);
            //    image.setWidth(150f);
            //    document.add(image);
            //
            //    // Thông tin khách hàng
            //    float margin = 40f;
            //    float customerInfoX = margin;
            //    float invoiceInfoX = customerInfoX + 350f;
            //    float headerY = 650f;
            //    float detailsY = 600f;
            //
            //    Paragraph customerHeader = new Paragraph("THÔNG TIN KHÁCH HÀNG")
            //            .setFont(font)
            //            .setFontSize(16)
            //            .setBold();
            //    document.add(customerHeader.setFixedPosition(customerInfoX, headerY, 200f));
            //
            //    Paragraph customerDetails = new Paragraph("Họ và tên: " + txthotenkhachhang.getText() +
                //            "\nSố điện thoại: " + txtemail.getText() +
                //            "\nĐịa Chỉ Shop: " + diachi.getText() +
                //            "\nEmail: " + txtemail.getText())
            //            .setFont(font)
            //            .setFontSize(12);
            //    document.add(customerDetails.setFixedPosition(customerInfoX, detailsY - 100f, 200f));
            //
            //    Paragraph invoiceHeader = new Paragraph("THÔNG TIN HÓA ĐƠN")
            //            .setFont(font)
            //            .setFontSize(16)
            //            .setBold();
            //    document.add(invoiceHeader.setFixedPosition(invoiceInfoX, headerY, 200f));
            //
            //    Paragraph invoiceDetails = new Paragraph("Mã Hóa Đơn: " + txtmahoadon.getText() +
                //            "\nNgày Tạo: " + ngaytao.getText())
            //            .setFont(font)
            //            .setFontSize(12);
            //    document.add(invoiceDetails.setFixedPosition(invoiceInfoX, detailsY, 200f));
            //
            //    // Bảng sản phẩm
            //    float tableY = detailsY - 220f;
            //    Table table = new Table(4);
            //    table.setWidth(pdf.getDefaultPageSize().getWidth() * 0.9f);
            //
            //    table.addHeaderCell(new Paragraph("Tên Sản Phẩm").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //    table.addHeaderCell(new Paragraph("Số Lượng").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //    table.addHeaderCell(new Paragraph("Giá Bán").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //    table.addHeaderCell(new Paragraph("Thành Tiền").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //
            //    // Nhóm sản phẩm có cùng mã
            //    Map<String, DonHangChiTietEntity> groupedProducts = new HashMap<>();
            //    ArrayList<DonHangChiTietEntity> danhSachChiTiet = layDuLieuTuBang();
            //
            //    for (DonHangChiTietEntity chiTiet : danhSachChiTiet) {
                //        String maSanPham = chiTiet.getMaSanPham();
                //        if (groupedProducts.containsKey(maSanPham)) {
                    //            // Cộng dồn số lượng và tính lại thành tiền
                    //            DonHangChiTietEntity existingProduct = groupedProducts.get(maSanPham);
                    //            existingProduct.setSoLuong(existingProduct.getSoLuong() + chiTiet.getSoLuong());
                    //        } else {
                    //            groupedProducts.put(maSanPham, new DonHangChiTietEntity(
                        //                    chiTiet.getMaSanPham(),
                        //                    chiTiet.getTenSanPham(),
                        //                    chiTiet.getSoLuong(),
                        //                    chiTiet.getGiaBan()
                        //            ));
                //        }
            //    }
        //
        //    // Thêm dữ liệu vào bảng
        //    double totalAmount = 0;
        //    for (DonHangChiTietEntity chiTiet : groupedProducts.values()) {
            //        double thanhTien = chiTiet.getSoLuong() * chiTiet.getGiaBan();
            //        totalAmount += thanhTien;
            //
            //        table.addCell(new Paragraph(chiTiet.getTenSanPham()).setFont(font));
            //        table.addCell(new Paragraph(String.valueOf(chiTiet.getSoLuong())).setFont(font).setTextAlignment(TextAlignment.CENTER));
            //        table.addCell(new Paragraph(String.format("%,.0f", chiTiet.getGiaBan())).setFont(font).setTextAlignment(TextAlignment.CENTER));
            //        table.addCell(new Paragraph(String.format("%,.0f", thanhTien)).setFont(font).setTextAlignment(TextAlignment.CENTER));
            //    }
        //    document.add(table.setFixedPosition(margin, tableY, pdf.getDefaultPageSize().getWidth() - 2 * margin));
        //
        //    // Tính toán tổng tiền, thuế, giảm giá
        //    double tax = totalAmount * 0.1;
        //    double discountt = Double.parseDouble(giamgia.getText().trim().replace(",", ""));
        //    double giamGia = (discountt <= 100) ? (discountt / 100) * totalAmount : discountt;
        //    double totalAmountAfterTax = totalAmount + tax;
        //    double finalAmount = totalAmountAfterTax - giamGia;
        //
        //    double tienChuyenKhoan = Double.parseDouble(chuyenkhoan.getText().trim().replace(",", ""));
        //    double tienKhachDua = Double.parseDouble(tienkhachdua.getText().trim().replace(",", ""));
        //    double tienTraKhach = tienKhachDua - finalAmount;
        //
        //    // Thêm thông tin tổng tiền vào PDF
        //    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
        //    document.add(new Paragraph("TỔNG TIỀN: " + String.format("%,.0f", totalAmount) + " VND").setFont(font).setTextAlignment(TextAlignment.RIGHT));
        //    document.add(new Paragraph("Thuế (10%): " + String.format("%,.0f", tax) + " VND").setFont(font).setTextAlignment(TextAlignment.RIGHT));
        //    document.add(new Paragraph("Giảm Giá: " + String.format("%,.0f", giamGia) + " VND").setFont(font).setTextAlignment(TextAlignment.RIGHT));
        //    document.add(new Paragraph("TỔNG THANH TOÁN: " + String.format("%,.0f", finalAmount) + " VND").setFont(font).setBold().setTextAlignment(TextAlignment.RIGHT));
        //
        //    if (phuongthucthanhtoan.equals("Chuyển khoản")) {
            //        document.add(new Paragraph("TIỀN CHUYỂN KHOẢN: " + String.format("%,.0f", tienChuyenKhoan) + " VND").setFont(font).setTextAlignment(TextAlignment.RIGHT));
            //    } else {
            //        document.add(new Paragraph("TIỀN KHÁCH ĐƯA: " + String.format("%,.0f", tienKhachDua) + " VND").setFont(font).setTextAlignment(TextAlignment.RIGHT));
            //        document.add(new Paragraph("TIỀN TRẢ KHÁCH: " + String.format("%,.0f", tienTraKhach) + " VND").setFont(font).setBold().setTextAlignment(TextAlignment.RIGHT));
            //    }
        //
        //    document.close();
        //    dispose();
        //    TrangChu duyemday = new TrangChu();
        //    duyemday.setVisible(true);
        //} catch (NumberFormatException e) {
        //    System.out.println("Lỗi: Dữ liệu nhập không hợp lệ!");
        //    e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        try {
            String filePath = "D:\\" + txtmahoadon.getText() + ".pdf";
            File file = new File(filePath);

            if (file.exists()) {
                file.delete();
            }

            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
            PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);

            // Add image header
            ImageData data = ImageDataFactory.create("D:\\anh\\duyem.png");
            com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);
            image.setHeight(150f);
            image.setWidth(150f);
            document.add(image);

            // Customer and Invoice Information
            float margin = 40f;
            float customerInfoX = margin;
            float invoiceInfoX = customerInfoX + 350f;
            float headerY = 650f;
            float detailsY = 600f;

            Paragraph customerHeader = new Paragraph("THÔNG TIN KHÁCH HÀNG")
            .setFont(font)
            .setFontSize(16)
            .setBold();
            document.add(customerHeader.setFixedPosition(customerInfoX, headerY, 200f));

            Paragraph customerDetails = new Paragraph("Họ và tên: " + txthotenkhachhang.getText() +
                "\nSố điện thoại: " + txtemail.getText() +
                "\nĐịa Chỉ Shop: " + diachi.getText() +
                "\nEmail: " + txtemail.getText())
            .setFont(font)
            .setFontSize(12);
            document.add(customerDetails.setFixedPosition(customerInfoX, detailsY - 100f, 200f));

            Paragraph invoiceHeader = new Paragraph("THÔNG TIN HÓA ĐƠN")
            .setFont(font)
            .setFontSize(16)
            .setBold();
            document.add(invoiceHeader.setFixedPosition(invoiceInfoX, headerY, 200f));

            Paragraph invoiceDetails = new Paragraph("Mã Hóa Đơn: " + txtmahoadon.getText() +
                "\nNgày Tạo: " + ngaytao.getText())
            .setFont(font)
            .setFontSize(12);
            document.add(invoiceDetails.setFixedPosition(invoiceInfoX, detailsY, 200f));

            float tableY = detailsY - 220f;
            Table table = new Table(4);
            table.setWidth(pdf.getDefaultPageSize().getWidth() * 0.9f);

            table.addHeaderCell(new Paragraph("Tên Sản Phẩm").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Số Lượng").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Giá Bán").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Paragraph("Thành Tiền").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            Map<String, DonHangChiTietEntity> groupedProducts = new HashMap<>();
            // Lấy danh sách chi tiết đơn hàng từ bảng
            ArrayList<DonHangChiTietEntity> danhSachChiTiet = layDuLieuTuBang();

            // Tạo Map tạm để đếm số lần xuất hiện của mỗi tên sản phẩm
            Map<String, Integer> productNameCount = new HashMap<>();
            for (DonHangChiTietEntity chiTiet : danhSachChiTiet) {
                productNameCount.put(chiTiet.getTenSanPham(), productNameCount.getOrDefault(chiTiet.getTenSanPham(), 0) + 1);
            }

            // Lặp lại danh sách chi tiết và xử lý tất cả sản phẩm
            for (DonHangChiTietEntity chiTiet : danhSachChiTiet) {
                String tenSanPham = chiTiet.getTenSanPham();

                // Kiểm tra nếu tên sản phẩm có số lần xuất hiện lớn hơn 1, cộng dồn số lượng
                if (productNameCount.get(tenSanPham) > 1) {
                    DonHangChiTietEntity existingProduct = groupedProducts.get(tenSanPham);
                    if (existingProduct != null) {
                        // Nếu đã tồn tại, cộng dồn số lượng
                        int soLuongMoi = existingProduct.getSoLuong() + chiTiet.getSoLuong();
                        existingProduct.setSoLuong(soLuongMoi);
                    } else {
                        // Nếu chưa tồn tại, thêm vào Map
                        groupedProducts.put(tenSanPham, new DonHangChiTietEntity(
                            chiTiet.getMaSanPham(),
                            chiTiet.getTenSanPham(),
                            chiTiet.getSoLuong(),
                            chiTiet.getGiaBan()
                        ));
                    }
                } else {
                    // Nếu tên sản phẩm chỉ xuất hiện một lần, vẫn cần đưa vào groupedProducts
                    groupedProducts.put(tenSanPham, new DonHangChiTietEntity(
                        chiTiet.getMaSanPham(),
                        chiTiet.getTenSanPham(),
                        chiTiet.getSoLuong(),
                        chiTiet.getGiaBan()
                    ));
                }
            }
            double totalAmount = 0;
            for (DonHangChiTietEntity chiTiet : groupedProducts.values()) {
                double thanhTien = chiTiet.getSoLuong() * chiTiet.getGiaBan();
                totalAmount += thanhTien;
                table.addCell(new Paragraph(chiTiet.getTenSanPham()).setFont(font));
                table.addCell(new Paragraph(String.valueOf(chiTiet.getSoLuong()))
                    .setFont(font)
                    .setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%,.0f", chiTiet.getGiaBan()))
                    .setFont(font)
                    .setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%,.0f", thanhTien))
                    .setFont(font)
                    .setTextAlignment(TextAlignment.CENTER));
            }
            document.add(table.setFixedPosition(margin, tableY, pdf.getDefaultPageSize().getWidth() - 2 * margin));
            document.add(new Paragraph("\n").setMarginBottom(300f));

            double tax = totalAmount * 0.1;
            String discountText = giamgia.getText().trim().replace(",", ""); // Lấy chuỗi nhập liệu và loại bỏ khoảng trắng
            double discountt = Double.parseDouble(discountText);
            double totalAmountAfterTax = totalAmount + tax;
            double finalAmount = totalAmountAfterTax - discountt;
            double tienChuyenKhoan = Double.parseDouble(chuyenkhoan.getText().trim().replace(",", ""));
            double tienMat = Double.parseDouble(this.tienMat.getText().trim().replace(",", ""));
            System.out.println("Tổng tiền sau thuế: " + totalAmountAfterTax);
            System.out.println("Tổng tiền sau giảm giá: " + finalAmount);
            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
            document.add(new Paragraph("TỔNG TIỀN: " + String.format("%,.0f", totalAmount) + " VND")
                .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("Thuế (10%): " + String.format("%,.0f", tax) + " VND")
                .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("Giảm Giá : " + String.format("%,.0f", discountt) + " VND")
                .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("TỔNG TIỀN SAU THUẾ: " + String.format("%,.0f", totalAmountAfterTax) + " VND")
                .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("TỔNG THANH TOÁN: " + String.format("%,.0f", finalAmount) + " VND")
                .setFont(font).setFontSize(12).setBold().setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("TIỀN CHUYỂN KHOẢN: " + String.format("%,.0f",tienChuyenKhoan ) + " VND")
                .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("TIỀN MẶT: " + String.format("%,.0f",tienMat ) + " VND")
                .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));

            // Closing the document
            document.close();
            dispose();
            TrangChu duyemday = new TrangChu();
            duyemday.setVisible(true);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Dữ liệu nhập không hợp lệ!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //    }else{
        //           try {
            //    String filePath = "D:\\" + txtmahoadon.getText() + ".pdf";
            //    File file = new File(filePath);
            //
            //    if (file.exists()) {
                //        file.delete();
                //    }
            //
            //    PdfWriter writer = new PdfWriter(file);
            //    PdfDocument pdf = new PdfDocument(writer);
            //    com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
            //    PdfFont font = PdfFontFactory.createFont("D:\\spring_boot\\ARIALUNI.TTF", PdfEncodings.IDENTITY_H);
            //
            //    // Add image header
            //    ImageData data = ImageDataFactory.create("D:\\anh\\duyem.png");
            //    com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);
            //    image.setHeight(150f);
            //    image.setWidth(150f);
            //    document.add(image);
            //
            //    // Customer and Invoice Information
            //    float margin = 40f;
            //    float customerInfoX = margin;
            //    float invoiceInfoX = customerInfoX + 350f;
            //    float headerY = 650f;
            //    float detailsY = 600f;
            //
            //    Paragraph customerHeader = new Paragraph("THÔNG TIN KHÁCH HÀNG")
            //            .setFont(font)
            //            .setFontSize(16)
            //            .setBold();
            //    document.add(customerHeader.setFixedPosition(customerInfoX, headerY, 200f));
            //
            //    Paragraph customerDetails = new Paragraph("Họ và tên: " + txthotenkhachhang.getText() +
                //            "\nSố điện thoại: " + txtemail.getText() +
                //            "\nĐịa Chỉ Shop: " + diachi.getText() +
                //            "\nEmail: " + txtemail.getText())
            //            .setFont(font)
            //            .setFontSize(12);
            //    document.add(customerDetails.setFixedPosition(customerInfoX, detailsY - 100f, 200f));
            //
            //    Paragraph invoiceHeader = new Paragraph("THÔNG TIN HÓA ĐƠN")
            //            .setFont(font)
            //            .setFontSize(16)
            //            .setBold();
            //    document.add(invoiceHeader.setFixedPosition(invoiceInfoX, headerY, 200f));
            //
            //    Paragraph invoiceDetails = new Paragraph("Mã Hóa Đơn: " + txtmahoadon.getText() +
                //            "\nNgày Tạo: " + ngaytao.getText())
            //            .setFont(font)
            //            .setFontSize(12);
            //    document.add(invoiceDetails.setFixedPosition(invoiceInfoX, detailsY, 200f));
            //
            //    float tableY = detailsY - 220f;
            //    Table table = new Table(4);
            //    table.setWidth(pdf.getDefaultPageSize().getWidth() * 0.9f);
            //
            //    table.addHeaderCell(new Paragraph("Tên Sản Phẩm").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //    table.addHeaderCell(new Paragraph("Số Lượng").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //    table.addHeaderCell(new Paragraph("Giá Bán").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //    table.addHeaderCell(new Paragraph("Thành Tiền").setFont(font).setBold().setTextAlignment(TextAlignment.CENTER));
            //
            //    // Add table content
            //    double totalAmount = 0;
            //    ArrayList<DonHangChiTietEntity> danhSachChiTiet = layDuLieuTuBang();
            //    for (DonHangChiTietEntity chiTiet : danhSachChiTiet) {
                //        double thanhTien = chiTiet.getSoLuong() * chiTiet.getGiaBan();
                //        totalAmount += thanhTien;
                //        table.addCell(new Paragraph(chiTiet.getTenSanPham()).setFont(font));
                //        table.addCell(new Paragraph(String.valueOf(chiTiet.getSoLuong())).setFont(font).setTextAlignment(TextAlignment.CENTER));
                //        table.addCell(new Paragraph(String.format("%,.0f", chiTiet.getGiaBan())).setFont(font).setTextAlignment(TextAlignment.CENTER));
                //        table.addCell(new Paragraph(String.format("%,.0f", thanhTien)).setFont(font).setTextAlignment(TextAlignment.CENTER));
                //    }
            //    document.add(table.setFixedPosition(margin, tableY, pdf.getDefaultPageSize().getWidth() - 2 * margin));
            //document.add(new Paragraph("\n").setMarginBottom(300f));
            //
            //
            //    double tax = totalAmount * 0.1;
            //
            //    double totalAmountAfterTax = totalAmount + tax;
            //    if(totalAmount>=1000000){
                //        discount = totalAmount * 0.02;
                //    }
            //    double finalAmount = totalAmountAfterTax - discount;
            //    double tienChuyenKhoan = Double.parseDouble(chuyenkhoan.getText().trim().replace(",", ""));
            //    double tienKhachDua = Double.parseDouble(tienkhachdua.getText().trim().replace(",", ""));
            //    double tienTraKhach = tienKhachDua - finalAmount;
            //
            //    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------").setFont(font));
            //    document.add(new Paragraph("TỔNG TIỀN: " + String.format("%,.0f", totalAmount) + " VND")
                //            .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            //    document.add(new Paragraph("Thuế (10%): " + String.format("%,.0f", tax) + " VND")
                //            .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            //    document.add(new Paragraph("Giảm Giá %: " + String.format("%,.0f", discount) + " VND")
                //            .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            //    document.add(new Paragraph("TỔNG TIỀN SAU THUẾ: " + String.format("%,.0f", totalAmountAfterTax) + " VND")
                //            .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            //    document.add(new Paragraph("TỔNG THANH TOÁN: " + String.format("%,.0f", finalAmount) + " VND")
                //            .setFont(font).setFontSize(12).setBold().setTextAlignment(TextAlignment.RIGHT));
            //    if(phuongthucthanhtoan.equals("Chuyển khoản")){
                //          document.add(new Paragraph("TIỀN CHUYỂN KHOẢN: " + String.format("%,.0f",tienChuyenKhoan ) + " VND")
                    //            .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
                //    }else{
                //        document.add(new Paragraph("TIỀN KHÁCH ĐƯA: " + String.format("%,.0f", tienKhachDua) + " VND")
                    //            .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
                //    document.add(new Paragraph("TIỀN TRẢ KHÁCH: " + String.format("%,.0f", tienTraKhach) + " VND")
                    //            .setFont(font).setFontSize(12).setBold().setTextAlignment(TextAlignment.RIGHT));
                //    }
            //    // Closing the document
            //    document.close();
            //    dispose();
            //  TrangChu duyemday = new TrangChu();
            //  duyemday.setVisible(true);
            //} catch (NumberFormatException e) {
            //    System.out.println("Lỗi: Dữ liệu nhập không hợp lệ!");
            //    e.printStackTrace();
            //} catch (IOException e) {
            //    e.printStackTrace();
            //}
    }//GEN-LAST:event_XuatHoaDonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonCaHai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonCaHai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonCaHai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonCaHai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XuatHoaDonCaHai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Email;
    private javax.swing.JButton XuatHoaDon;
    private javax.swing.JLabel chuyenkhoan;
    private javax.swing.JLabel diachi;
    private javax.swing.JLabel giamgia;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ngaytao;
    private javax.swing.JLabel nhanvien;
    private javax.swing.JLabel phuongthucthanhtoan;
    private javax.swing.JTable tblxuathoadon;
    private javax.swing.JLabel thue;
    private javax.swing.JLabel tienMat;
    private javax.swing.JLabel tongtienn;
    private javax.swing.JLabel txtemail;
    private javax.swing.JLabel txthotenkhachhang;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtmahoadon;
    private javax.swing.JLabel txtsodienthoaikhachhang;
    // End of variables declaration//GEN-END:variables
}
