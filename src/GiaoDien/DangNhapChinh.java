package GiaoDien;
import Repository.TaiKhoanRepository;
import javax.swing.JOptionPane;
public class DangNhapChinh extends javax.swing.JFrame {
private TaiKhoanRepository ls = new TaiKhoanRepository();
private boolean jcbShowPass;
    public DangNhapChinh() {
        initComponents();
    }
    private void DangNhap() {
    String taiKhoan = txttaikhoan.getText();
    String matKhau = txtmatkhau.getText();
    if (ls.dangNhap(taiKhoan, matKhau)) {
        String vaiTro = ls.layVaiTro(taiKhoan);
        LuuThongTinDangNhap.setTaiKhoan(taiKhoan);
        LuuThongTinDangNhap.setVaiTro(vaiTro);
         int idNhanVien = ls.layIDNhaanVien(taiKhoan); 
        LuuThongTinDangNhap.setInNhanVien(idNhanVien); 
        if (ls.kiemTraQuyen(vaiTro, "Quản trị viên")) {
            dispose();
            loandings dg = new loandings();
            dg.setVisible(true);
        } else {
            TrangChu dg = new TrangChu();
            dg.setVisible(true);
            dispose();
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "Tên tài khoản hoặc mật khẩu sai hoặc tài khoản đã hết hạn");
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txttaikhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        duyem = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtmatkhau = new javax.swing.JPasswordField();
        nen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đăng Nhập");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 90, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Rectangle 5 (2).png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 260, 40));
        getContentPane().add(txttaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 320, 40));

        jLabel3.setText("HOẶC");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Frame 1.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 140, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Frame 1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 160, 10));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bạn chưa có tài khoản _ Đăng ký.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 200, -1));

        duyem.setText("Hiển thị password");
        duyem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duyemActionPerformed(evt);
            }
        });
        getContentPane().add(duyem, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, -1, -1));

        jLabel9.setText("Quên mật khẩu ?");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, 90, -1));

        jLabel10.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel10.setText("ĐĂNG NHẬP");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 140, 60));

        jLabel11.setText("MẬT KHẨU");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, -1, -1));

        jLabel12.setText("TÀI KHOẢN");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, -1, -1));

        txtmatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmatkhauActionPerformed(evt);
            }
        });
        getContentPane().add(txtmatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 320, 40));

        nen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 1 (3).png"))); // NOI18N
        getContentPane().add(nen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void duyemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duyemActionPerformed
        // TODO add your handling code here:
        if (duyem.isSelected()) {
            txtmatkhau.setEchoChar((char) 0);
        } else {
            txtmatkhau.setEchoChar('*');
        }
    }//GEN-LAST:event_duyemActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        DangNhap();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtmatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmatkhauActionPerformed
        
    }//GEN-LAST:event_txtmatkhauActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        dispose();
        QuenMatKhau quenmatkhau = new QuenMatKhau();
        quenmatkhau.setVisible(true);
        
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(DangNhapChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox duyem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel nen;
    private javax.swing.JPasswordField txtmatkhau;
    private javax.swing.JTextField txttaikhoan;
    // End of variables declaration//GEN-END:variables
}
