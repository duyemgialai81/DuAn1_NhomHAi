/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GiaoDien;
import KetNoiSQL.ketnoi;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author SingPC
 */
public class MatKhauMoi extends javax.swing.JFrame {
    public MatKhauMoi() {
        initComponents();
    }
    private boolean update(){
        int check =0;
        String sql ="""
                    update nhanVien
                    set mat_khau =?
                    where mat_khau = ?
                    """;
        try {
            Connection con = ketnoi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, txtmatkhaumoi.getText());
            ps.setObject(2, txtmatkhaucu.getText());
            check = ps.executeUpdate();
                   } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmatkhaucu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtnhaplaimatkhau = new javax.swing.JPasswordField();
        txtmatkhaumoi = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
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
        getContentPane().add(txtmatkhaucu, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 320, 40));

        jLabel3.setText("HOẶC");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Frame 1.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 140, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Frame 1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 160, 10));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bạn chưa có tài khoản _ Đăng ký.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 200, -1));

        jLabel10.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel10.setText("ĐỔI MẬT KHẨU");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 170, 60));

        jLabel11.setText("NHẬP LẠI MẬT KHẨU");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, -1, -1));

        jLabel12.setText("MẬT KHẨU CŨ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, -1));

        txtnhaplaimatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhaplaimatkhauActionPerformed(evt);
            }
        });
        getContentPane().add(txtnhaplaimatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 320, 40));

        txtmatkhaumoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmatkhaumoiActionPerformed(evt);
            }
        });
        getContentPane().add(txtmatkhaumoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 320, 40));

        jLabel13.setText("MẬT KHẨU MỚI");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        nen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 1 (3).png"))); // NOI18N
        getContentPane().add(nen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        if(txtmatkhaumoi.getText().equals(txtnhaplaimatkhau.getText())) {
    update();
    DangNhapChinh duyem = new DangNhapChinh();
    duyem.setVisible(true);
} else {
    JOptionPane.showMessageDialog(this, "Mật khẩu không khớp, vui lòng nhập lại!");
}

    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtnhaplaimatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhaplaimatkhauActionPerformed

    }//GEN-LAST:event_txtnhaplaimatkhauActionPerformed

    private void txtmatkhaumoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmatkhaumoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmatkhaumoiActionPerformed

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
            java.util.logging.Logger.getLogger(MatKhauMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatKhauMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatKhauMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatKhauMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatKhauMoi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel nen;
    private javax.swing.JTextField txtmatkhaucu;
    private javax.swing.JPasswordField txtmatkhaumoi;
    private javax.swing.JPasswordField txtnhaplaimatkhau;
    // End of variables declaration//GEN-END:variables
}
