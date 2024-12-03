/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GiaoDien;
import Entity.SanPham.SanPhamEntity;
import java.awt.Color;
import java.awt.Toolkit; 
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class TrangChu extends javax.swing.JFrame {
    public TrangChu() {
        initComponents();
        setTitle("Phầm Mềm Quản Lý Và Bán Hàng");
        setIconImage();
        ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
        ccn.setView(txtbanhang1, txtbanhang);
        ArrayList<ChucNang> ls = new ArrayList<>();
        ls.add(new ChucNang("ChucNangBanHang", txtbanhang1, txtbanhang));
//        ls.add(new ChucNang("ChucNangSanPham", txtsanpham1, txtsanpham));
        ls.add(new ChucNang("ChucNangHoaDon", txthoadon1, txthoadon));
        ls.add(new ChucNang("FomtestBanHnag", txtbanhang1, txtbanhang));
        ls.add(new ChucNang("ChucNangThongKe", txtthongke1, txtthongke));
//                ls.add(new ChucNang("ChucNangSanPham", txtsanpham1, txtsanpham));
          ls.add(new ChucNang("ChucNangSanPham", txtsanpham1, txtsanpham));
////        ls.add(new ChucNang("ChucNangVouvher", txtvoucher1, txtvoucher));
//        ls.add(new ChucNang("ChucNangKhachHang", txtkhachhang1, txtkhachhang));
//        ls.add(new ChucNang("ChucNangNhanVien", txtnhanvien1, txtnhanvien));
//        ls.add(new ChucNang("ChucNangDoiHang", txtdoihang1, txtdoihang));
        ccn.DuyEm(ls);
        hirnThiDuLieu();
    }
    public void hirnThiDuLieu(){
//        txtTenNhanvien.setText(luuThongTinDangNhap.getTenNhanVien());
//        txtidnhanvien.setText(String.valueOf(LuuThongTinDangNhap.getInNhanVien()));
    }
    /**
         * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        manhinh = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        banhang = new javax.swing.JLabel();
        txtbanhang = new javax.swing.JLabel();
        sanpham = new javax.swing.JLabel();
        txtsanpham = new javax.swing.JLabel();
        hoadon = new javax.swing.JLabel();
        txthoadon = new javax.swing.JLabel();
        txtdoihang = new javax.swing.JLabel();
        doihang = new javax.swing.JLabel();
        txtkhachhang = new javax.swing.JLabel();
        khachhang = new javax.swing.JLabel();
        txtthongke = new javax.swing.JLabel();
        thongke = new javax.swing.JLabel();
        txtdangxuat = new javax.swing.JLabel();
        dangxuat = new javax.swing.JLabel();
        txtnhanvien = new javax.swing.JLabel();
        nhanvien = new javax.swing.JLabel();
        txtidnhanvien = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtbanhang1 = new javax.swing.JPanel();
        txtsanpham1 = new javax.swing.JPanel();
        txthoadon1 = new javax.swing.JPanel();
        txtdoihang1 = new javax.swing.JPanel();
        txtkhachhang1 = new javax.swing.JPanel();
        txtthongke1 = new javax.swing.JPanel();
        txtdangxuat1 = new javax.swing.JPanel();
        txtnhanvien1 = new javax.swing.JPanel();
        txtbanhang27 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        manhinh.setBackground(new java.awt.Color(244, 239, 245));
        manhinh.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout manhinhLayout = new javax.swing.GroupLayout(manhinh);
        manhinh.setLayout(manhinhLayout);
        manhinhLayout.setHorizontalGroup(
            manhinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1109, Short.MAX_VALUE)
        );
        manhinhLayout.setVerticalGroup(
            manhinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 759, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        banhang.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        banhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/seller.png"))); // NOI18N
        banhang.setText("BÁN HÀNG");
        jPanel3.add(banhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, 30));

        txtbanhang.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtbanhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 190, 30));

        sanpham.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        sanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/SanPham.png"))); // NOI18N
        sanpham.setText("SẢN PHẨM");
        jPanel3.add(sanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, 30));

        txtsanpham.setForeground(new java.awt.Color(255, 255, 255));
        txtsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsanphamMouseClicked(evt);
            }
        });
        jPanel3.add(txtsanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, 30));

        hoadon.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        hoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/HoaDon.png"))); // NOI18N
        hoadon.setText("HÓA ĐƠN");
        jPanel3.add(hoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, 30));

        txthoadon.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(txthoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 190, 30));

        txtdoihang.setForeground(new java.awt.Color(255, 255, 255));
        txtdoihang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdoihangMouseClicked(evt);
            }
        });
        jPanel3.add(txtdoihang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 190, 30));

        doihang.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        doihang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/DoiHang.png"))); // NOI18N
        doihang.setText("Event");
        jPanel3.add(doihang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 120, 30));

        txtkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkhachhangMouseClicked(evt);
            }
        });
        jPanel3.add(txtkhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 190, 30));

        khachhang.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        khachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/KhachHang.png"))); // NOI18N
        khachhang.setText("khách hàng");
        khachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khachhangMouseClicked(evt);
            }
        });
        jPanel3.add(khachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, -1, 30));

        txtthongke.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtthongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 190, 30));

        thongke.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        thongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ThongKe.png"))); // NOI18N
        thongke.setText("Thống kê");
        jPanel3.add(thongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, -1, 30));

        txtdangxuat.setForeground(new java.awt.Color(255, 255, 255));
        txtdangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdangxuatMouseClicked(evt);
            }
        });
        jPanel3.add(txtdangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 190, 30));

        dangxuat.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Thoat.png"))); // NOI18N
        dangxuat.setText("Đăng xuất");
        jPanel3.add(dangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, -1, 30));

        txtnhanvien.setForeground(new java.awt.Color(255, 255, 255));
        txtnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnhanvienMouseClicked(evt);
            }
        });
        jPanel3.add(txtnhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 190, 30));

        nhanvien.setFont(new java.awt.Font("Barlow Condensed", 1, 24)); // NOI18N
        nhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/NhanVien.png"))); // NOI18N
        nhanvien.setText("nhân viên");
        jPanel3.add(nhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, 30));

        txtidnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New Project.png"))); // NOI18N
        txtidnhanvien.setText("  ");
        jPanel3.add(txtidnhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 120));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 760));

        txtbanhang1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtbanhang1Layout = new javax.swing.GroupLayout(txtbanhang1);
        txtbanhang1.setLayout(txtbanhang1Layout);
        txtbanhang1Layout.setHorizontalGroup(
            txtbanhang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtbanhang1Layout.setVerticalGroup(
            txtbanhang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtbanhang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        txtsanpham1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtsanpham1Layout = new javax.swing.GroupLayout(txtsanpham1);
        txtsanpham1.setLayout(txtsanpham1Layout);
        txtsanpham1Layout.setHorizontalGroup(
            txtsanpham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtsanpham1Layout.setVerticalGroup(
            txtsanpham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtsanpham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        txthoadon1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txthoadon1Layout = new javax.swing.GroupLayout(txthoadon1);
        txthoadon1.setLayout(txthoadon1Layout);
        txthoadon1Layout.setHorizontalGroup(
            txthoadon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txthoadon1Layout.setVerticalGroup(
            txthoadon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txthoadon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        txtdoihang1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtdoihang1Layout = new javax.swing.GroupLayout(txtdoihang1);
        txtdoihang1.setLayout(txtdoihang1Layout);
        txtdoihang1Layout.setHorizontalGroup(
            txtdoihang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtdoihang1Layout.setVerticalGroup(
            txtdoihang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtdoihang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        txtkhachhang1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtkhachhang1Layout = new javax.swing.GroupLayout(txtkhachhang1);
        txtkhachhang1.setLayout(txtkhachhang1Layout);
        txtkhachhang1Layout.setHorizontalGroup(
            txtkhachhang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtkhachhang1Layout.setVerticalGroup(
            txtkhachhang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtkhachhang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        txtthongke1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtthongke1Layout = new javax.swing.GroupLayout(txtthongke1);
        txtthongke1.setLayout(txtthongke1Layout);
        txtthongke1Layout.setHorizontalGroup(
            txtthongke1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtthongke1Layout.setVerticalGroup(
            txtthongke1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtthongke1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        txtdangxuat1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtdangxuat1Layout = new javax.swing.GroupLayout(txtdangxuat1);
        txtdangxuat1.setLayout(txtdangxuat1Layout);
        txtdangxuat1Layout.setHorizontalGroup(
            txtdangxuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtdangxuat1Layout.setVerticalGroup(
            txtdangxuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtdangxuat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, -1, -1));

        txtnhanvien1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtnhanvien1Layout = new javax.swing.GroupLayout(txtnhanvien1);
        txtnhanvien1.setLayout(txtnhanvien1Layout);
        txtnhanvien1Layout.setHorizontalGroup(
            txtnhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtnhanvien1Layout.setVerticalGroup(
            txtnhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtnhanvien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        txtbanhang27.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout txtbanhang27Layout = new javax.swing.GroupLayout(txtbanhang27);
        txtbanhang27.setLayout(txtbanhang27Layout);
        txtbanhang27Layout.setHorizontalGroup(
            txtbanhang27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );
        txtbanhang27Layout.setVerticalGroup(
            txtbanhang27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel3.add(txtbanhang27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manhinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manhinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtdangxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdangxuatMouseClicked
        // TODO add your handling code here:
        dispose();
        DangNhapChinh duyem = new DangNhapChinh();
        duyem.setVisible(true);
    }//GEN-LAST:event_txtdangxuatMouseClicked

    private void txtsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsanphamMouseClicked
        // TODO add your handling code here:
//             String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
//        String vaiTro = LuuThongTinDangNhap.getVaiTro();
//        ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
//         ArrayList<ChucNang> ls = new ArrayList<>();
//         if ("Quản trị viên".equals(vaiTro)) {
//           
//             ccn.DuyEm(ls);
//        } else {
//            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
//        
//        }
    }//GEN-LAST:event_txtsanphamMouseClicked

    private void txtnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnhanvienMouseClicked
        // TODO add your handling code here:
             String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
              ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
        String vaiTro = LuuThongTinDangNhap.getVaiTro();
         ArrayList<ChucNang> ls = new ArrayList<>();
         if ("Quản trị viên".equals(vaiTro)) {
            ls.add(new ChucNang("ChucNangNhanVien", txtnhanvien1, txtnhanvien));
           ccn.DuyEm(ls);
        } else {
            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
        
        }
    }//GEN-LAST:event_txtnhanvienMouseClicked

    private void khachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khachhangMouseClicked
        // TODO add your handling code here:
//              String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
//              ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
//        String vaiTro = LuuThongTinDangNhap.getVaiTro();
//         ArrayList<ChucNang> ls = new ArrayList<>();
//         if ("Quản trị viên".equals(vaiTro)) {
//            ls.add(new ChucNang("ChucNangNhanVien", txtkhachhang1, txtkhachhang));
//           ccn.DuyEm(ls);
//        } else {
//            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
//        }
    }//GEN-LAST:event_khachhangMouseClicked

    private void txtkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkhachhangMouseClicked
        // TODO add your handling code here:
             String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
              ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
        String vaiTro = LuuThongTinDangNhap.getVaiTro();
         ArrayList<ChucNang> ls = new ArrayList<>();
         if ("Quản trị viên".equals(vaiTro)) {
            ls.add(new ChucNang("ChucNangKhachHang", txtkhachhang1, txtkhachhang));
           ccn.DuyEm(ls);
        } else {
            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
        }
    }//GEN-LAST:event_txtkhachhangMouseClicked

    private void txtdoihangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdoihangMouseClicked
              String taiKhoan = LuuThongTinDangNhap.getTaiKhoan();
              ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
        String vaiTro = LuuThongTinDangNhap.getVaiTro();
         ArrayList<ChucNang> ls = new ArrayList<>();
         if ("Quản trị viên".equals(vaiTro)) {
            ls.add(new ChucNang("ChucNangDoiHang", txtdoihang1, txtdoihang));
           ccn.DuyEm(ls);
        } else {
            JOptionPane.showMessageDialog(this, "bạn Là nhân viên bạn không có quyền này");
        }
    }//GEN-LAST:event_txtdoihangMouseClicked

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
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }   
        });
    }
 private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/Brown Simple Aesthetic Bag Store Logo 2.png")));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banhang;
    private javax.swing.JLabel dangxuat;
    private javax.swing.JLabel doihang;
    private javax.swing.JLabel hoadon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel khachhang;
    private javax.swing.JPanel manhinh;
    private javax.swing.JLabel nhanvien;
    private javax.swing.JLabel sanpham;
    private javax.swing.JLabel thongke;
    private javax.swing.JLabel txtbanhang;
    private javax.swing.JPanel txtbanhang1;
    private javax.swing.JPanel txtbanhang27;
    private javax.swing.JLabel txtdangxuat;
    private javax.swing.JPanel txtdangxuat1;
    private javax.swing.JLabel txtdoihang;
    private javax.swing.JPanel txtdoihang1;
    private javax.swing.JLabel txthoadon;
    private javax.swing.JPanel txthoadon1;
    private javax.swing.JLabel txtidnhanvien;
    private javax.swing.JLabel txtkhachhang;
    private javax.swing.JPanel txtkhachhang1;
    private javax.swing.JLabel txtnhanvien;
    private javax.swing.JPanel txtnhanvien1;
    private javax.swing.JLabel txtsanpham;
    private javax.swing.JPanel txtsanpham1;
    private javax.swing.JLabel txtthongke;
    private javax.swing.JPanel txtthongke1;
    // End of variables declaration//GEN-END:variables

}
