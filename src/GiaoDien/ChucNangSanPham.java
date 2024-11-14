/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.AddSanPhamEntity;
import Entity.RoleEntity;
import Entity.SanPhamEntity;
import Entity.ThongTinSanPham;
import Repository.SanPhamRepository;
import Repository.TaiKhoanRepository;
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

    /**
     * Creates new form ChucNangSanPham
     */
    public ChucNangSanPham() {
        initComponents();

        hienThiThongTinSanPham(ls.getAll());
        hienThiDuLieu(ls.getSanPhamChhiTiet());
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
              l.getIdSanPham(),  l.getMaSanPham(), l.getTenSanPham(), l.getSoLuong(), l.getGiaBan(), l.getHinhAnh(), l.getKichCo(), l.getMauSac(), l.getTenThuongHieu(), l.getChatLieu(), l.getQuocGia(), l.getTenMaLoai(), l.getTrangThai()
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
        String hinhAnh = txt_addHinhanh.getText().trim();
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
        JFileChooser fileChooser = new JFileChooser("D:\\anh");
        fileChooser.setDialogTitle("D:\\anh");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            String hinhAnhPath = fileToOpen.getAbsolutePath();
            txt_addHinhanh.setText(hinhAnhPath);
            try {
                ImageIcon icon = new ImageIcon(hinhAnhPath);
                Image img = icon.getImage();
                int labelWidth = txt_addHinhanh.getWidth();
                int labelHeight = txt_addHinhanh.getHeight();
                double aspectRatio = (double) img.getWidth(null) / (double) img.getHeight(null);

                if (labelWidth / (double) labelHeight > aspectRatio) {
                    labelWidth = (int) (labelHeight * aspectRatio);
                } else {
                    labelHeight = (int) (labelWidth / aspectRatio);
                }
                Image newImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                txt_addHinhanh.setIcon(new ImageIcon(newImg)); // Đặt hình ảnh vào JLabel
                txt_addHinhanh.setHorizontalAlignment(SwingConstants.CENTER);
                txt_addHinhanh.setVerticalAlignment(SwingConstants.CENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không chọn file.");
        }
    }

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
    String danhMuc = (String)cbo_LocThuongHieuduyem.getSelectedItem();
    String xuatXu = (String) cbo_LocXuatXu2.getSelectedItem();
    String trangThai = (String) timkiemtrangthia.getSelectedItem();
    String tenSanPham = txt_TimKiemSP.getText();
 
    ArrayList<SanPhamEntity> ketQua = ls.timkiemSanPham(danhMuc, xuatXu, trangThai, tenSanPham);
        hienThiDuLieu(ketQua);
}
//private void timkiemThongTinSanPham() {
//        // Chuyển đổi giá từ input
//        float giaMax = Float.parseFloat(txtGiamax.getText());
//        float giaMin = Float.parseFloat(txtGiaMin.getText());
//        String trangThai = (String)cbo_LocTrangThai.getSelectedItem();
//        String tenSanPham = txt_TimKiemThongTinSanPham.getText();
//        ArrayList<ThongTinSanPham> KetQua = ls.timkiemThongtinSanPham(giaMin, giaMax, trangThai, tenSanPham);
//        hienThiThongTinSanPham(KetQua);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtGiamax = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtGiaMin = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbo_LocTrangThai = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_TimKiemThongTinSanPham = new javax.swing.JTextField();
        btn_tim = new javax.swing.JButton();
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
        btn_XuatFile = new javax.swing.JButton();
        btn_Sua1 = new javax.swing.JButton();
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
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cbo_LocThuongHieuduyem = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txt_TimKiemSP = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cbo_LocXuatXu2 = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        timkiemtrangthia = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanphamchitiet = new javax.swing.JTable();

        jPanel_SanPham.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txthinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(txthinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ttspTrangThai, 0, 239, Short.MAX_VALUE)
                    .addComponent(txt_ttspTenSanPham))
                .addGap(65, 65, 65)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
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
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Giá Bán Max:");

        txtGiamax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtGiamax, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiamax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Giá Bán Min:");

        txtGiaMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaMinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Trang Thái:");

        cbo_LocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "              ", "Còn hàng", "Hết hàng" }));
        cbo_LocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LocTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cbo_LocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(83, 83, 83))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(15, 15, 15)
                .addComponent(cbo_LocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tìm Kiếm Sản Phẩm:");

        txt_TimKiemThongTinSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemThongTinSanPhamActionPerformed(evt);
            }
        });

        btn_tim.setBackground(new java.awt.Color(255, 204, 0));
        btn_tim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_tim.setText("Tìm");
        btn_tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_timMouseClicked(evt);
            }
        });
        btn_tim.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                btn_timComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txt_TimKiemThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btn_tim)
                .addGap(77, 77, 77))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_TimKiemThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btn_tim)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addComponent(jScrollPane1)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel_SanPhamLayout = new javax.swing.GroupLayout(jPanel_SanPham);
        jPanel_SanPham.setLayout(jPanel_SanPhamLayout);
        jPanel_SanPhamLayout.setHorizontalGroup(
            jPanel_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_SanPhamLayout.setVerticalGroup(
            jPanel_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel_SanPham);

        jPanel_SanPhamChiTiet.setBackground(new java.awt.Color(255, 255, 255));

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
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel10.add(txt_addHinhanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 130, 110));

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

        btn_LamMoi.setBackground(new java.awt.Color(255, 204, 0));
        btn_LamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_XuatFile.setBackground(new java.awt.Color(255, 204, 0));
        btn_XuatFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_XuatFile.setText("Xuất Flie");
        btn_XuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatFileActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Sua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btn_Them)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Sua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Sua1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_LamMoi)
                .addGap(18, 18, 18)
                .addComponent(btn_XuatFile)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Chất Liệu:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Size:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Màu :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Thương Hiệu:");

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

        jButton3.setText("Tạo Mã QR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(34, 34, 34)
                                .addComponent(txt_addMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel19))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_addTenSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_addDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, 0, 208, Short.MAX_VALUE)
                                            .addComponent(txt_addSoLuong)))
                                    .addComponent(txt_addGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                        .addComponent(txt_addGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel15)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_addThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_addMau, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_addSize, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_addChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_addXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(22, 22, 22)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(244, 244, 244)))
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                    .addComponent(txt_addXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(22, 22, 22)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_addMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3))
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

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

        btn_Tim.setBackground(new java.awt.Color(255, 204, 0));
        btn_Tim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
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
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txt_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_Tim)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Tim))
                .addContainerGap(15, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(91, 91, 91))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        tbl_sanphamchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Tồn", "Giá Bán", "Hình Ảnh", "Kích Cỡ", "Màu Sắc", "Tên Thương Hiệu", "Chất Liệu", "Quốc Gia", "Danh Muc", "Trạng Thái"
            }
        ));
        tbl_sanphamchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanphamchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanphamchitiet);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_SanPhamChiTietLayout = new javax.swing.GroupLayout(jPanel_SanPhamChiTiet);
        jPanel_SanPhamChiTiet.setLayout(jPanel_SanPhamChiTietLayout);
        jPanel_SanPhamChiTietLayout.setHorizontalGroup(
            jPanel_SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_SanPhamChiTietLayout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_SanPhamChiTietLayout.createSequentialGroup()
                        .addGap(0, 89, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel_SanPhamChiTietLayout.setVerticalGroup(
            jPanel_SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm Chi Tiết", jPanel_SanPhamChiTiet);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
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
            add();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
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

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_btn_TimActionPerformed

    private void txt_TimKiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemSPKeyReleased
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_txt_TimKiemSPKeyReleased

    private void cbo_LocXuatXu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocXuatXu2ActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_cbo_LocXuatXu2ActionPerformed

    private void cbo_LocThuongHieuduyemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocThuongHieuduyemActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_cbo_LocThuongHieuduyemActionPerformed

    private void timkiemtrangthiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timkiemtrangthiaActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_timkiemtrangthiaActionPerformed

    private void txtGiamaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamaxActionPerformed
        // TODO add your handling code here:
//        timkiemThongTinSanPham();   
    }//GEN-LAST:event_txtGiamaxActionPerformed

    private void txtGiaMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaMinActionPerformed
//        // TODO add your handling code here:
//        timkiemThongTinSanPham();
    }//GEN-LAST:event_txtGiaMinActionPerformed

    private void btn_timMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_timMouseClicked
        // TODO add your handling code here:
//        timkiemThongTinSanPham();
    }//GEN-LAST:event_btn_timMouseClicked

    private void cbo_LocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocTrangThaiActionPerformed
        // TODO add your handling code here:
//        timkiemThongTinSanPham();
    }//GEN-LAST:event_cbo_LocTrangThaiActionPerformed

    private void txt_TimKiemThongTinSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemThongTinSanPhamActionPerformed
        // TODO add your handling code here:
//        timkiemThongTinSanPham();
    }//GEN-LAST:event_txt_TimKiemThongTinSanPhamActionPerformed

    private void btn_timComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_btn_timComponentResized
        // TODO add your handling code here:
//        timkiemThongTinSanPham();
    }//GEN-LAST:event_btn_timComponentResized
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
     int selectedIndex = tbl_sanphamchitiet.getSelectedRow(); // Bảng chứa danh sách sản phẩm
    
    if (selectedIndex != -1) {
        // Lấy chi tiết sản phẩm từ dòng đã chọn
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
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Sua1;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_XuatFile;
    private javax.swing.JButton btn_tim;
    private javax.swing.JComboBox<String> cbo_LocThuongHieuduyem;
    private javax.swing.JComboBox<String> cbo_LocTrangThai;
    private javax.swing.JComboBox<String> cbo_LocXuatXu2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_sanphamchitiet;
    private javax.swing.JTable tbl_thongTinSP;
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
