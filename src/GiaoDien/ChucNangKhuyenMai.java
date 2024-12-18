/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.KhuyenMai.KhuyenMaiEntity;
import Entity.SanPham.SanPhamEntity;
import KetNoiSQL.ketnoi;
import Repository.KhuyenMaiRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SingPC
 */
public class ChucNangKhuyenMai extends javax.swing.JPanel {
private DefaultTableModel md = new DefaultTableModel();
private KhuyenMaiRepository km = new KhuyenMaiRepository();
    /**
     * Creates new form ChucNangKhuyenMai
     */
    public ChucNangKhuyenMai() {
        initComponents();
        hienThiDuLieu(km.hienThiDuLieuKhuyenMai());
       
    }
private KhuyenMaiEntity getFomat() {
    KhuyenMaiEntity km = new KhuyenMaiEntity();
    km.setTenVouCher(txttenkhuyenmai.getText());
    km.setNgayBatDau(txtngaybatdau.getDate());
    km.setNgayKetThuc(txtngayketthu.getDate());
    km.setMoTa(txt_moTa.getText());
    km.setLoaiTriGia((String) cboloaigiatri.getSelectedItem());
    km.setTruongTrinhKhuyenMai(txttruongtrinhkhuyenmai.getText()); // Tên chương trình
    String loaiKhuyenMai = (String) cboloaigiatri.getSelectedItem();
    float giaTri = Float.parseFloat(txt_giaTri.getText());
    if (loaiKhuyenMai.equalsIgnoreCase("Giảm Theo %")) {
        if (giaTri > 0 && giaTri <= 10) {
            km.setGiaTri(giaTri);
        } else {
           JOptionPane.showMessageDialog(jPanel_Nen, "ivent chỉ giảm tới 10% ");
        }
    } else if (loaiKhuyenMai.equalsIgnoreCase("Giảm Tiền Trực Tiếp")) {
        if (giaTri > 0) {
            km.setGiaTri(giaTri);
        } else {
            throw new IllegalArgumentException("Giá trị tiền phải lớn hơn 0.");
        }
    } else {
        km.setGiaTri(0);
    }

    return km;
}
private void update(){
    KhuyenMaiEntity kmm = getFomat();
    kmm.setIdVoucher(Integer.parseInt(txtid.getText()));
    boolean ketQua = km.updateKhuyenMai(kmm, kmm.getIdVoucher());
    if(ketQua){
        hienThiDuLieu(km.hienThiDuLieuKhuyenMai());
    }else{
        JOptionPane.showMessageDialog(jPanel_Nen, "Update thất bại");
    }
    
}
private void add(){
    km.themKhuyenMai(getFomat());
}
public void hienThiDuLieu(ArrayList<KhuyenMaiEntity> ls){
    md= (DefaultTableModel) tblkhuyenmai.getModel();
    md.setRowCount(0);
    for (KhuyenMaiEntity l : ls) {
        md.addRow(new Object[]{
          l.getGiaTri(),l.getNgayBatDau(),l.getNgayKetThuc(), l.getMoTa(), l.getLoaiTriGia(),l.getTruongTrinhKhuyenMai(),l.getTrangThai(),l.getTenVouCher(),l.getIdVoucher() 
        });
    }
}
public void hienThiDuLieuLenTextFile(int index){
    KhuyenMaiEntity kmm = km.hienThiDuLieuKhuyenMai().get(index);
   txtid.setText(String.valueOf(kmm.getIdVoucher()));
    txttenkhuyenmai.setText(kmm.getTenVouCher());
    txt_giaTri.setText(String.valueOf(kmm.getGiaTri()));
    txtngaybatdau.setDate(kmm.getNgayBatDau());
     txtngayketthu.setDate(kmm.getNgayKetThuc());
     txt_moTa.setText(kmm.getMoTa());
    cboloaigiatri.setSelectedItem(kmm.getLoaiTriGia());
   txttruongtrinhkhuyenmai.setText(kmm.getTruongTrinhKhuyenMai());
}

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Nen = new javax.swing.JPanel();
        jPanel_ChuongTrinhKhuyenMai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttenkhuyenmai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txttruongtrinhkhuyenmai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboloaigiatri = new javax.swing.JComboBox<>();
        txt_giaTri = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();
        jPanel_ThoiGianSuDung = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_moTa = new javax.swing.JTextArea();
        txtngaybatdau = new com.toedter.calendar.JDateChooser();
        txtngayketthu = new com.toedter.calendar.JDateChooser();
        jPanel_DanhSachKhuyenMai = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblkhuyenmai = new javax.swing.JTable();
        btn_lamMoi = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();

        jPanel_Nen.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_ChuongTrinhKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ChuongTrinhKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chương Trình Khuyến Mại", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tạo Tên  khuyến mại:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tạo chương trình khuyến mại:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Hình thức giảm giá:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mức giảm giá:");

        cboloaigiatri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm Theo %", "Giảm Tiền Trực Tiếp" }));

        jLabel8.setText("ID mã khuyến mại");

        txtid.setText(" ");

        javax.swing.GroupLayout jPanel_ChuongTrinhKhuyenMaiLayout = new javax.swing.GroupLayout(jPanel_ChuongTrinhKhuyenMai);
        jPanel_ChuongTrinhKhuyenMai.setLayout(jPanel_ChuongTrinhKhuyenMaiLayout);
        jPanel_ChuongTrinhKhuyenMaiLayout.setHorizontalGroup(
            jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                        .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_giaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(46, 46, 46))))
            .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttruongtrinhkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttenkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboloaigiatri, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel_ChuongTrinhKhuyenMaiLayout.setVerticalGroup(
            jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttenkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttruongtrinhkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboloaigiatri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_giaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel_ChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtid))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jPanel_ThoiGianSuDung.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ThoiGianSuDung.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời Gian Sử Dụng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Thời Gian Bắt Đầu Giảm Giá:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Thời Gian Kết Thúc Giảm Giá:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mô Tả:");

        txt_moTa.setColumns(20);
        txt_moTa.setRows(5);
        jScrollPane2.setViewportView(txt_moTa);

        javax.swing.GroupLayout jPanel_ThoiGianSuDungLayout = new javax.swing.GroupLayout(jPanel_ThoiGianSuDung);
        jPanel_ThoiGianSuDung.setLayout(jPanel_ThoiGianSuDungLayout);
        jPanel_ThoiGianSuDungLayout.setHorizontalGroup(
            jPanel_ThoiGianSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThoiGianSuDungLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel_ThoiGianSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_ThoiGianSuDungLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_ThoiGianSuDungLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtngayketthu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7))
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ThoiGianSuDungLayout.createSequentialGroup()
                .addGap(0, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel_ThoiGianSuDungLayout.setVerticalGroup(
            jPanel_ThoiGianSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThoiGianSuDungLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_ThoiGianSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel_ThoiGianSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtngayketthu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel_DanhSachKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_DanhSachKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khuyến Mại", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblkhuyenmai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Gia Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Mô Tả", "Loại Giá Trị", "Chương Trình Khuyến Mại", "Trạng Thái", "Tên  khuyến Mãi"
            }
        ));
        tblkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhuyenmaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblkhuyenmai);

        javax.swing.GroupLayout jPanel_DanhSachKhuyenMaiLayout = new javax.swing.GroupLayout(jPanel_DanhSachKhuyenMai);
        jPanel_DanhSachKhuyenMai.setLayout(jPanel_DanhSachKhuyenMaiLayout);
        jPanel_DanhSachKhuyenMaiLayout.setHorizontalGroup(
            jPanel_DanhSachKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel_DanhSachKhuyenMaiLayout.setVerticalGroup(
            jPanel_DanhSachKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        btn_lamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_lamMoi.setText("Làm Mới");

        btn_Luu.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_NenLayout = new javax.swing.GroupLayout(jPanel_Nen);
        jPanel_Nen.setLayout(jPanel_NenLayout);
        jPanel_NenLayout.setHorizontalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_NenLayout.createSequentialGroup()
                        .addComponent(jPanel_DanhSachKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel_NenLayout.createSequentialGroup()
                        .addComponent(jPanel_ChuongTrinhKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_NenLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_ThoiGianSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel_NenLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_NenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_lamMoi)
                                .addGap(224, 224, 224))))))
        );
        jPanel_NenLayout.setVerticalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_ChuongTrinhKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_NenLayout.createSequentialGroup()
                        .addComponent(jPanel_ThoiGianSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_lamMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(jPanel_DanhSachKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel_Nen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Nen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        // TODO add your handling code here:
   add();
        hienThiDuLieu(km.hienThiDuLieuKhuyenMai());

    }//GEN-LAST:event_btn_LuuActionPerformed

    private void tblkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhuyenmaiMouseClicked
        // TODO add your handling code here:
        int index = tblkhuyenmai.getSelectedRow();
        hienThiDuLieuLenTextFile(index);
    }//GEN-LAST:event_tblkhuyenmaiMouseClicked

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btn_SuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JComboBox<String> cboloaigiatri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel_ChuongTrinhKhuyenMai;
    private javax.swing.JPanel jPanel_DanhSachKhuyenMai;
    private javax.swing.JPanel jPanel_Nen;
    private javax.swing.JPanel jPanel_ThoiGianSuDung;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblkhuyenmai;
    private javax.swing.JTextField txt_giaTri;
    private javax.swing.JTextArea txt_moTa;
    private javax.swing.JLabel txtid;
    private com.toedter.calendar.JDateChooser txtngaybatdau;
    private com.toedter.calendar.JDateChooser txtngayketthu;
    private javax.swing.JTextField txttenkhuyenmai;
    private javax.swing.JTextField txttruongtrinhkhuyenmai;
    // End of variables declaration//GEN-END:variables
}
