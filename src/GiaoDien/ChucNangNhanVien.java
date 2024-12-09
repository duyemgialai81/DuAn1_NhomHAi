/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.NhanVien.NhanVienEntity;
import KetNoiSQL.ketnoi;
import Repository.NhanVienRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SingPC
 */
public class ChucNangNhanVien extends javax.swing.JPanel {

    private DefaultTableModel mol = new DefaultTableModel();
    private NhanVienRepository rp = new NhanVienRepository();
    private int i = -1;

    /**
     * Creates new form ChucNangNhanVien
     */
    public ChucNangNhanVien() {
        initComponents();
        fillTable(rp.getAll());
        fillTable2(rp.getAll2());

        txt_TimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearch();
            }
//
////            private void cbo_LocGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {
////                String gioiTinh = cbo_LocGioiTinh.getSelectedItem().toString();
////                if (gioiTinh.equals("Tất cả")) { // Giả sử "Tất cả" là giá trị thứ ba
////                    loadAllData(); // Phương thức để tải toàn bộ dữ liệu ban đầu
////                } else {
////                    performSearch();
////                }
////            }
//
////            private void loadAllData() {
////                ArrayList<NhanVienEntity> allData = rp.getAll(); // Giả sử bạn có phương thức getAllData() để lấy tất cả dữ liệu
////                fillTable(allData);
////            }
////
////            private void loadAllData2() {
////                ArrayList<NhanVienEntity> allData = rp.getAll2(); // Giả sử bạn có phương thức getAllData() để lấy tất cả dữ liệu
////                fillTable2(allData);
////            }
//

            private void performSearch() {
                String maNV = txt_TimKiem.getText();
                String gioiTinh = cbo_LocGioiTinh.getSelectedItem().toString();
                String vaiTo = cbo_LocVaiTro.getSelectedItem().toString();

                if (gioiTinh.equalsIgnoreCase("Tất cả") && vaiTo.equalsIgnoreCase("Tất cả")) {
                    // Tìm kiếm chỉ dựa trên mã nhân viên và vai trò
                    ArrayList<NhanVienEntity> result = rp.timNV(maNV, null, null);
                    fillTable(result);

                    ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, null, null);
                    fillTable2(result2);
                } else if (gioiTinh.equalsIgnoreCase("Tất cả")) {
                    ArrayList<NhanVienEntity> result = rp.timNV(maNV, null, vaiTo);
                    fillTable(result);

                    ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, null, vaiTo);
                    fillTable2(result2);
                } else if (vaiTo.equalsIgnoreCase("Tất cả")) {
                    ArrayList<NhanVienEntity> result = rp.timNV(maNV, gioiTinh, null);
                    fillTable(result);

                    ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, gioiTinh, null);
                    fillTable2(result2);
                } else {
                    // Tìm kiếm dựa trên mã nhân viên, giới tính và vai trò
                    ArrayList<NhanVienEntity> result = rp.timNV(maNV, gioiTinh, vaiTo);
                    fillTable(result);

                    ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, gioiTinh, vaiTo);
                    fillTable2(result2);
                }
            }

        });
    }

    void fillTable(ArrayList<NhanVienEntity> list) {
        mol = (DefaultTableModel) tbl_DangLamViec.getModel();
        mol.setRowCount(0);
        for (NhanVienEntity x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    void fillTable2(ArrayList<NhanVienEntity> list) {
        mol = (DefaultTableModel) tbl_NghiViec.getModel();
        mol.setRowCount(0);
        for (NhanVienEntity x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    public void showData(int i) {
        NhanVienEntity nv = rp.getAll().get(i);
        txt_MaNV.setText(tbl_DangLamViec.getValueAt(i, 0).toString());
        txt_TenNV.setText(tbl_DangLamViec.getValueAt(i, 1).toString());
        QuanLy.setSelectedItem(tbl_DangLamViec.getValueAt(i, 2).toString());
        txt_DiaChi.setText(tbl_DangLamViec.getValueAt(i, 3).toString());
        txt_SDT.setText(tbl_DangLamViec.getValueAt(i, 4).toString());
        txt_Date.setText(tbl_DangLamViec.getValueAt(i, 5).toString());
        txt_Email.setText(tbl_DangLamViec.getValueAt(i, 7).toString());
        String tt = nv.getTrangThai();
        boolean gt = nv.isGioiTinh();
        if (gt == false) {
            rdo_Nu.setSelected(true);
        } else {
            rdo_Nam.setSelected(true);
        }
        if (tt.equalsIgnoreCase("Hoạt động")) {
            rdo_DangLamViec.setSelected(true);
        } else {
            rdo_NghiViec.setSelected(true);
        }
    }

    public void showData2(int i) {
        NhanVienEntity nv = rp.getAll2().get(i);
        txt_MaNV.setText(tbl_NghiViec.getValueAt(i, 0).toString());
        txt_TenNV.setText(tbl_NghiViec.getValueAt(i, 1).toString());
        QuanLy.setSelectedItem(tbl_NghiViec.getValueAt(i, 2).toString());
        txt_DiaChi.setText(tbl_NghiViec.getValueAt(i, 3).toString());
        txt_SDT.setText(tbl_NghiViec.getValueAt(i, 4).toString());
        txt_Date.setText(tbl_NghiViec.getValueAt(i, 5).toString());
        txt_Email.setText(tbl_NghiViec.getValueAt(i, 7).toString());
        String tt = nv.getTrangThai();
        boolean gt = nv.isGioiTinh();
        if (gt == false) {
            rdo_Nu.setSelected(true);
        } else {
            rdo_Nam.setSelected(true);
        }
        if (tt.equalsIgnoreCase("Nghỉ làm")) {
            rdo_NghiViec.setSelected(true);
        } else {
            rdo_DangLamViec.setSelected(true);
        }
    }

    public NhanVienEntity readForm() {
        String tenNhanVien, vaiTro, diaChi, soDienThoai, ngaySinh, email, matKhau, trangThai;
        boolean gioiTinh;

        tenNhanVien = txt_TenNV.getText().trim();
        if (tenNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên đang trống!");
            txt_TenNV.requestFocus();
            return null;
        }

        soDienThoai = txt_SDT.getText().trim();
        if (soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đang trống!");
            txt_SDT.requestFocus();
            return null;
        }

        ngaySinh = txt_Date.getText().trim();
        if (ngaySinh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh đang trống!");
            txt_Date.requestFocus();
            return null;
        }

        email = txt_Email.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email đang trống!");
            txt_Email.requestFocus();
            return null;
        }

        diaChi = txt_DiaChi.getText().trim();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ đang trống!");
            txt_DiaChi.requestFocus();
            return null;
        }

        matKhau = txt_MatKhau.getText().trim();
        if (matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu đang trống!");
            txt_MatKhau.requestFocus();
            return null;
        }

        vaiTro = (String) QuanLy.getSelectedItem();
        if (vaiTro == null || vaiTro.isEmpty()) {
            QuanLy.requestFocus();
            return null;
        }

        if (rdo_DangLamViec.isSelected()) {
            trangThai = "Hoạt động";
        } else {
            trangThai = "Nghỉ làm";
        }

        if (rdo_Nam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }

        // Đảm bảo thứ tự các tham số khớp với thứ tự trong phương thức khởi tạo NhanVienEntity
        return new NhanVienEntity(tenNhanVien, vaiTro, diaChi, soDienThoai, ngaySinh, gioiTinh, email, matKhau, trangThai);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel_Nen = new javax.swing.JPanel();
        cbo_VaiTro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_MatKhau = new javax.swing.JTextField();
        txt_DiaChi = new javax.swing.JTextField();
        txt_TenNV = new javax.swing.JTextField();
        txt_MaNV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        QuanLy = new javax.swing.JComboBox<>();
        txt_Email = new javax.swing.JTextField();
        txt_Date = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        rdo_Nam = new javax.swing.JRadioButton();
        rdo_Nu = new javax.swing.JRadioButton();
        rdo_DangLamViec = new javax.swing.JRadioButton();
        rdo_NghiViec = new javax.swing.JRadioButton();
        btn_Them = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbo_LocGioiTinh = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbo_LocVaiTro = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btn_Tim = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DangLamViec = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_NghiViec = new javax.swing.JTable();

        jPanel_Nen.setBackground(new java.awt.Color(255, 255, 255));

        cbo_VaiTro.setBackground(new java.awt.Color(255, 255, 255));
        cbo_VaiTro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết Lập Thông Tin Nhân Viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã NV:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên NV:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mật Khẩu:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Vai Trò:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Địa Chỉ:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Điện Thoại:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ngày Sinh:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giới Tính:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Trạng Thái:");

        QuanLy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản trị viên", "Quản lý", "Nhân viên bán hàng", "Nhân viên kho", "Kế toán" }));

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");

        buttonGroup2.add(rdo_DangLamViec);
        rdo_DangLamViec.setText("Đang Làm Việc");

        buttonGroup2.add(rdo_NghiViec);
        rdo_NghiViec.setText("Nghỉ Việc");

        btn_Them.setBackground(new java.awt.Color(255, 204, 0));
        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
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

        btn_Sua.setBackground(new java.awt.Color(255, 204, 0));
        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cbo_VaiTroLayout = new javax.swing.GroupLayout(cbo_VaiTro);
        cbo_VaiTro.setLayout(cbo_VaiTroLayout);
        cbo_VaiTroLayout.setHorizontalGroup(
            cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cbo_VaiTroLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(txt_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cbo_VaiTroLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cbo_VaiTroLayout.createSequentialGroup()
                        .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cbo_VaiTroLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(33, 33, 33)))
                        .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_MatKhau)
                            .addComponent(QuanLy, 0, 200, Short.MAX_VALUE))))
                .addGap(177, 177, 177)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_LamMoi))
                    .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                        .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdo_DangLamViec)
                                    .addComponent(rdo_Nam))
                                .addGap(18, 18, 18)
                                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdo_Nu)
                                    .addComponent(rdo_NghiViec))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cbo_VaiTroLayout.setVerticalGroup(
            cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cbo_VaiTroLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_Nam)
                    .addComponent(rdo_Nu))
                .addGap(18, 18, 18)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(rdo_DangLamViec)
                    .addComponent(rdo_NghiViec))
                .addGap(18, 18, 18)
                .addGroup(cbo_VaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_LamMoi)
                    .addComponent(btn_Sua))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Lọc Theo Giới Tính:");

        cbo_LocGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ" }));
        cbo_LocGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LocGioiTinhActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Lọc Theo Vai Trò:");

        cbo_LocVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Quản trị viên", "Kế toán", "Quản lý", "Nhân viên kho", "Nhân viên bán hàng" }));
        cbo_LocVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_LocVaiTroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cbo_LocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cbo_LocVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbo_LocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cbo_LocVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btn_Tim.setBackground(new java.awt.Color(255, 204, 0));
        btn_Tim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Tim.setText("Tìm");

        txt_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Tim)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Tim)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

        tbl_DangLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên Nhân Viên", "Vai Trò", "Địa Chỉ", "Điện Thoại", "Ngày Sinh", "Giới Tính", "Email", "Trạng Thái"
            }
        ));
        tbl_DangLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DangLamViecMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DangLamViec);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Đang Làm Việc", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tbl_NghiViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên Nhân Viên", "Vai Trò", "Địa Chỉ", "Điện Thoại", "Ngày Sinh", "Giới Tính", "Email", "Trạng Thái"
            }
        ));
        tbl_NghiViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NghiViecMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_NghiViec);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Nghỉ Việc", jPanel5);

        javax.swing.GroupLayout jPanel_NenLayout = new javax.swing.GroupLayout(jPanel_Nen);
        jPanel_Nen.setLayout(jPanel_NenLayout);
        jPanel_NenLayout.setHorizontalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_VaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_NenLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel_NenLayout.setVerticalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbo_VaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Nen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Nen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        String soDienThoi = txt_SDT.getText().trim();
    String sql = "SELECT COUNT(*) AS count FROM nhanVien WHERE so_dien_thoai = ? ";
    try (Connection con = ketnoi.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, soDienThoi);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt("count") > 0) {
            // Nếu sản phẩm đã tồn tại
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Đã Tồn Tại");
        } else {
            if (readForm() != null) {
            rp.them(readForm());
            fillTable(rp.getAll());
        }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra tên sản phẩm");
    }

        
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        int selectedRowDangLamViec = tbl_DangLamViec.getSelectedRow();
        int selectedRowNghiViec = tbl_NghiViec.getSelectedRow();

        if (selectedRowDangLamViec != -1 || selectedRowNghiViec != -1) {
            String manv;
            if (selectedRowDangLamViec != -1) {
                manv = tbl_DangLamViec.getValueAt(selectedRowDangLamViec, 0).toString(); // Giả sử mã nhân viên ở cột đầu tiên
            } else {
                manv = tbl_NghiViec.getValueAt(selectedRowNghiViec, 0).toString(); // Giả sử mã nhân viên ở cột đầu tiên
            }

            NhanVienEntity nvE = readForm(); // Lấy thông tin từ form
            if (nvE != null) {
                int result = rp.update(manv, nvE);
                if (result == 1) {
//                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    this.fillTable(rp.getAll());
                    this.fillTable2(rp.getAll2());// Làm mới bảng
                } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần cập nhật!");
        }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        txt_MaNV.setText("");
        txt_TenNV.setText("");
        txt_MatKhau.setText("");
        txt_SDT.setText("");
        txt_Date.setText("");
        txt_Email.setText("");
        txt_DiaChi.setText("");
        QuanLy.setSelectedItem("Quản trị viên");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void cbo_LocVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocVaiTroActionPerformed
        // TODO add your handling code here:
        String maNV = txt_TimKiem.getText();
        String gioiTinh = cbo_LocGioiTinh.getSelectedItem().toString();
        String vaiTo = cbo_LocVaiTro.getSelectedItem().toString();

        if (gioiTinh.equalsIgnoreCase("Tất cả") && vaiTo.equalsIgnoreCase("Tất cả")) {
            // Tìm kiếm chỉ dựa trên mã nhân viên và vai trò
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, null, null);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, null, null);
            fillTable2(result2);
        } else if (gioiTinh.equalsIgnoreCase("Tất cả")) {
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, null, vaiTo);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, null, vaiTo);
            fillTable2(result2);
        } else if (vaiTo.equalsIgnoreCase("Tất cả")) {
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, gioiTinh, null);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, gioiTinh, null);
            fillTable2(result2);
        } else {
            // Tìm kiếm dựa trên mã nhân viên, giới tính và vai trò
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, gioiTinh, vaiTo);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, gioiTinh, vaiTo);
            fillTable2(result2);
        }
    }//GEN-LAST:event_cbo_LocVaiTroActionPerformed

    private void cbo_LocGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_LocGioiTinhActionPerformed
        // TODO add your handling code here:
        String maNV = txt_TimKiem.getText();
        String gioiTinh = cbo_LocGioiTinh.getSelectedItem().toString();
        String vaiTo = cbo_LocVaiTro.getSelectedItem().toString();

        if (gioiTinh.equalsIgnoreCase("Tất cả") && vaiTo.equalsIgnoreCase("Tất cả")) {
            // Tìm kiếm chỉ dựa trên mã nhân viên và vai trò
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, null, null);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, null, null);
            fillTable2(result2);
        } else if (gioiTinh.equalsIgnoreCase("Tất cả")) {
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, null, vaiTo);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, null, vaiTo);
            fillTable2(result2);
        } else if (vaiTo.equalsIgnoreCase("Tất cả")) {
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, gioiTinh, null);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, gioiTinh, null);
            fillTable2(result2);
        } else {
            // Tìm kiếm dựa trên mã nhân viên, giới tính và vai trò
            ArrayList<NhanVienEntity> result = rp.timNV(maNV, gioiTinh, vaiTo);
            fillTable(result);

            ArrayList<NhanVienEntity> result2 = rp.timNV2(maNV, gioiTinh, vaiTo);
            fillTable2(result2);
        }
    }//GEN-LAST:event_cbo_LocGioiTinhActionPerformed

    private void txt_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemActionPerformed
        // TODO add your handling code here:
        String maNV = txt_TimKiem.getText();
        String gioiTinh = cbo_LocGioiTinh.getSelectedItem().toString();
        String vaiTo = cbo_LocVaiTro.getSelectedItem().toString();
        ArrayList<NhanVienEntity> c = rp.timNV(maNV, gioiTinh, vaiTo);
        fillTable(c);
        ArrayList<NhanVienEntity> d = rp.timNV2(maNV, gioiTinh, vaiTo);
        fillTable2(d);
    }//GEN-LAST:event_txt_TimKiemActionPerformed

    private void tbl_DangLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DangLamViecMouseClicked
        // TODO add your handling code here:
        int a = tbl_DangLamViec.getSelectedRow();
        this.showData(a);
    }//GEN-LAST:event_tbl_DangLamViecMouseClicked

    private void tbl_NghiViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NghiViecMouseClicked
        // TODO add your handling code here:
        int b = tbl_NghiViec.getSelectedRow();
        this.showData2(b);
    }//GEN-LAST:event_tbl_NghiViecMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> QuanLy;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Tim;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_LocGioiTinh;
    private javax.swing.JComboBox<String> cbo_LocVaiTro;
    private javax.swing.JPanel cbo_VaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_Nen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo_DangLamViec;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_NghiViec;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JTable tbl_DangLamViec;
    private javax.swing.JTable tbl_NghiViec;
    private javax.swing.JTextField txt_Date;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_TenNV;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
