/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.KhachHang.KhachHang;
import Repository.ChonKhachHangRepository;
import Repository.KhachHangRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SingPC
 */
public class ChucNangKhachHang extends javax.swing.JPanel {
    private KhachHangRepository repo = new KhachHangRepository();
    private DefaultTableModel mol = new DefaultTableModel();
    private ChonKhachHangRepository kh = new ChonKhachHangRepository();
private DefaultTableModel md = new DefaultTableModel();
    private int i = -1;
    /**
     * Creates new form ChucNangKhachHang
     */
    public ChucNangKhachHang() {
        initComponents();
//           this.fillTable(repo.getAll());
//        txt_MaKH.setEditable(false);
 hienThiLuaChoKhachHangg(kh.layThongTinKhachHang());
    }
 void fillTable(ArrayList<KhachHang> list){
        mol = (DefaultTableModel) tbl_thongTinKH.getModel();
        mol.setRowCount(0);
        for(KhachHang x : list){
            mol.addRow(x.toDaTaRow());
        }
    }
    public  void showData(int index){
        String ma, ten, sdt,email,dc;
        boolean gioiTinh, trangThai;
        ma =tbl_thongTinKH.getValueAt(index , 0).toString();
        ten =tbl_thongTinKH.getValueAt(index , 1).toString();
        sdt =tbl_thongTinKH.getValueAt(index , 2).toString();
        email =tbl_thongTinKH.getValueAt(index , 3).toString();
        dc =tbl_thongTinKH.getValueAt(index, 4).toString();
        gioiTinh = Boolean.parseBoolean( tbl_thongTinKH.getValueAt(index, 5).toString());
        trangThai = Boolean.parseBoolean(tbl_thongTinKH.getValueAt(index, 6).toString());
        txt_MaKH.setText(ma);
        txt_TenKH.setText(ten);
        txt_SDT.setText(sdt);
        txt_Email.setText(email);
        txt_DiaChi.setText(dc);
        if(gioiTinh){
            rdo_Nam.setSelected(true);
        }else{
            rdo_Nu.setSelected(true);
        }
        if(trangThai){
            rdo_ConHD.setSelected(true);
        }else{
            rdo_NgungHD.setSelected(true);
        }
        }
        KhachHang readForm() {
        String ten, sdt, email, dc;
        boolean gioiTinh, trangThai;
        int idMaKhachHang;
        ten = txt_TenKH.getText();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống.");
            txt_TenKH.requestFocus();
            return null;
        }

        sdt = txt_SDT.getText();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.");
            txt_SDT.requestFocus();
            return null;
        }

        email = txt_Email.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống.");
            txt_Email.requestFocus();
            return null;
        }

        dc = txt_DiaChi.getText();
        if (dc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
            txt_DiaChi.requestFocus();
            return null;
        }
           idMaKhachHang = Integer.parseInt(txt_DiaChi.getText());
        if (dc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
            txt_DiaChi.requestFocus();
            return null;
        }
        if (rdo_Nam.isSelected()) {
       gioiTinh = true;  // Giới tính Nam
    } else if (rdo_Nu.isSelected()) {
        gioiTinh = false;  // Giới tính Nữ
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính.");
        return null;
    }
    if (rdo_ConHD.isSelected()) {
       trangThai = true;  // Giới tính Nam
    } else if (rdo_NgungHD.isSelected()) {
        trangThai = false;  // Giới tính Nữ
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính.");
        return null;
    }
        return new KhachHang(idMaKhachHang, email, ten, sdt, email, dc, gioiTinh, trangThai);
    }
    private void timKiemTuDong() {
    String soDienThoai = txt_TimKiem.getText().trim();

    if (soDienThoai.isEmpty()) {
        fillTable(repo.getAll());
        return;
    }
    ArrayList<KhachHang> ketQua = repo.timkiem(soDienThoai);
    if (ketQua != null && !ketQua.isEmpty()) {
        fillTable(ketQua);
    } else {
        DefaultTableModel model = (DefaultTableModel) tbl_thongTinKH.getModel();
        model.setRowCount(0);
    }
}
      private void hienThiLuaChoKhachHangg(ArrayList<KhachHang> khachHang){
        md = (DefaultTableModel) tbl_thongTinKH.getModel();
        md.setRowCount(0);
        for (KhachHang khachHang1 : khachHang) {
            md.addRow(new Object[]{
                khachHang1.getMaKH(), khachHang1.getTenKH(), khachHang1.getEmail(),khachHang1.getSDT(),khachHang1.getDiaChi(), khachHang1.isGioiTinh()?"Nam":"Nữ", khachHang1.isTrangThai()?"Đang Hoạt Động":"Ngừng Hoạt Đông"
            });
        }
    }
    private void add(){
        kh.addThongTinKhachHang(getFomat());
        hienThiLuaChoKhachHangg(kh.layThongTinKhachHang());
    }
    private KhachHang getFomat(){
        KhachHang kh = new KhachHang();
        kh.setMaKH(txt_MaKH.getText());
        kh.setTenKH(txt_TenKH.getText());
        kh.setEmail(txt_Email.getText());
        kh.setSDT(txt_SDT.getText());
        kh.setDiaChi(txt_DiaChi.getText());
        boolean gioiTinh = rdo_Nam.isSelected();
        kh.setGioiTinh(gioiTinh);
        boolean trangThai = rdo_ConHD.isSelected();
        kh.setTrangThai(trangThai);
        return kh;
    }
    private void hienThiDuLieuLenTextFile(int index){
        KhachHang sc = kh.layThongTinKhachHang().get(index);
        txt_MaKH.setText(sc.getMaKH());
        txt_TenKH.setText(sc.getTenKH());
        txt_Email.setText(sc.getEmail());
        txt_SDT.setText(sc.getSDT());
        txt_DiaChi.setText(sc.getDiaChi());
        if(sc.isGioiTinh()){
            rdo_Nam.setSelected(true);
        }else{
            rdo_Nu.setSelected(true);
        }
        if(sc.isTrangThai()){
            rdo_ConHD.setSelected(true);
        }else{
            rdo_NgungHD.setSelected(true);
        }
    }
    private void update(){
        KhachHang sc = getFomat();
        boolean ketQua = kh.updateThongTinKhachHang(sc, sc.getMaKH());
        if(ketQua){
            hienThiLuaChoKhachHangg(kh.layThongTinKhachHang());
        }else{
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel_Nen = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_TenKH = new javax.swing.JTextField();
        txt_MaKH = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        rdo_Nam = new javax.swing.JRadioButton();
        rdo_Nu = new javax.swing.JRadioButton();
        rdo_ConHD = new javax.swing.JRadioButton();
        rdo_NgungHD = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DiaChi = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_thongTinKH = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbo_GioiTinh = new javax.swing.JComboBox<>();
        cbo_TrangThai = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();

        jPanel_Nen.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết Lập Thông Tin Khách Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Khách Hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên Khách Hàng:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Số Điện Thoại:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Giới Tính:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Trạng Thái:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");

        buttonGroup2.add(rdo_ConHD);
        rdo_ConHD.setText("Còn Hoạt Động");

        buttonGroup2.add(rdo_NgungHD);
        rdo_NgungHD.setText("Ngừng Hoạt Động");

        txt_DiaChi.setColumns(20);
        txt_DiaChi.setRows(5);
        jScrollPane1.setViewportView(txt_DiaChi);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_Them)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua)
                .addGap(18, 18, 18)
                .addComponent(btn_LamMoi)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdo_Nam)
                        .addGap(54, 54, 54)
                        .addComponent(rdo_Nu))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_TenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addComponent(txt_MaKH)
                        .addComponent(txt_SDT)))
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(50, 50, 50)
                        .addComponent(txt_Email))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_ConHD)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_NgungHD))
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(txt_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(rdo_ConHD)
                            .addComponent(rdo_NgungHD))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(rdo_Nam)
                                    .addComponent(rdo_Nu)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm Kiếm:");

        txt_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemActionPerformed(evt);
            }
        });

        tbl_thongTinKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Email", "Số Điện Thoại", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tbl_thongTinKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_thongTinKHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_thongTinKH);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giới Tính:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Trạng Thái:");

        cbo_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Nam", "Nữ" }));

        cbo_TrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Còn Hoạt Động", "Ngừng Hoạt Động" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbo_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(cbo_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbo_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbo_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(34, 34, 34)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Cá Nhân", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1055, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lịch Sử Giao Dịch", jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jPanel_NenLayout = new javax.swing.GroupLayout(jPanel_Nen);
        jPanel_Nen.setLayout(jPanel_NenLayout);
        jPanel_NenLayout.setHorizontalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_NenLayout.setVerticalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txt_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemActionPerformed
        // TODO add your handling code here:
         txt_TimKiem.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                timKiemTuDong();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                timKiemTuDong();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                timKiemTuDong();
            }
        });
    }//GEN-LAST:event_txt_TimKiemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
//        int i = tbl_thongTinKH.getSelectedRow();
//        if (i < 0) {
//            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần sửa.");
//            return;
//        }
//        String ma = tbl_thongTinKH.getValueAt(i, 0).toString();
//
//        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không?");
//        if (chon == 0) {
//            KhachHang kh = this.readForm();
//            if (kh != null) {
//                KhachHangRepository repo = new KhachHangRepository();
//                int ketQua = repo.sua(ma, kh);
//                if (ketQua > 0) {
//                    JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
//                    this.fillTable(repo.getAll());
//                } else {
//                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại.");
//                }
//            }
//            }
update();
        
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void tbl_thongTinKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thongTinKHMouseClicked
        // TODO add your handling code here:
//          i = tbl_thongTinKH.getSelectedRow();
//        showData(i);
int index = tbl_thongTinKH.getSelectedRow();
        hienThiDuLieuLenTextFile(index);
    }//GEN-LAST:event_tbl_thongTinKHMouseClicked

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
            txt_MaKH.setText("");
        txt_TenKH.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
        txt_DiaChi.setText("");
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
//         if (this.readForm() != null) {
//            int chon = JOptionPane.showConfirmDialog(this, "ban co muon nhap them khong ?");
//            if (chon == 0) {
//                repo.them(this.readForm());
//                JOptionPane.showMessageDialog(this, "them thanh cong");
//                this.fillTable(repo.getAll());
//            } else {
//                JOptionPane.showMessageDialog(this, "chon khong them");
//            }
//        }
add();
    }//GEN-LAST:event_btn_ThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_GioiTinh;
    private javax.swing.JComboBox<String> cbo_TrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel_Nen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo_ConHD;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_NgungHD;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JTable tbl_thongTinKH;
    private javax.swing.JTextArea txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_MaKH;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
