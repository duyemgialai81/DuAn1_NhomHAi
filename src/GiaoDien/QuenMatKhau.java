/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GiaoDien;
import KetNoiSQL.ketnoi;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;
/**
 *
 * @author SingPC
 */
public class QuenMatKhau extends javax.swing.JFrame {

    /**
     * Creates new form QuenMatKhau
     */
    public QuenMatKhau() {
        initComponents();
    }
     public boolean isEmailExist() throws SQLException {
    String sql = "SELECT email, ten_tai_khoan FROM NhanVien WHERE email = ? and ten_tai_khoan =?";
    try (Connection con = ketnoi.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, txtemail.getText());
        ps.setString(2, txttaikhoan.getText());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String emailFromDb = rs.getString("email"); 
            String taiKhaon = rs.getString("ten_tai_khoan");
            return emailFromDb.equalsIgnoreCase(txtemail.getText())||taiKhaon.equalsIgnoreCase(txttaikhoan.getText());
        }
    }
    return false;
    
}
 public void sendVerificationCode(JTextField txtemail, String verificationCode) {
    String recipientEmail = txtemail.getText().trim(); // Lấy email từ JTextField

    // Kiểm tra email không được để trống
    if (recipientEmail.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập email người nhận!");
        return;
    }
    String senderEmail = "quysamset2@gmail.com";
    String senderPassword = "cwcbgrqbimkglhmy";
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Mã Xác Nhận");
        message.setText("Mã xác nhận của bạn là: " + verificationCode);
        Transport.send(message);
    } catch (MessagingException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi gửi email!");
    }
}



// Hàm tạo mã xác nhận
private String generateVerificationCode() {
    int randomCode = (int) (Math.random() * 900000) + 100000; // Tạo mã 6 chữ số ngẫu nhiên
    return String.valueOf(randomCode);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txttaikhoan = new javax.swing.JTextField();
        nen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NEXT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 40, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Rectangle 5 (2).png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 260, 40));
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 320, 40));

        jLabel3.setText("HOẶC");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Frame 1.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 140, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Frame 1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 160, 10));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bạn chưa có tài khoản _ Đăng ký.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 200, -1));

        jLabel10.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel10.setText("Xác Nhận Tài Khoản");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 230, 60));

        jLabel11.setText("EMAIL");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, -1, -1));

        jLabel12.setText("TÊN TÀI KHOẢN");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, -1, -1));
        getContentPane().add(txttaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 320, 40));

        nen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 1 (3).png"))); // NOI18N
        getContentPane().add(nen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
     try {
    if (isEmailExist()) {
        String verificationCode = generateVerificationCode(); // Tạo mã xác nhận
sendVerificationCode(txtemail, verificationCode); 
        int attempts = 0;
        boolean isCodeCorrect = false;
        while (attempts < 3) {
            String inputCode = JOptionPane.showInputDialog(this, "Nhập mã xác nhận đã gửi qua email:");
            if (inputCode == null || inputCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã xác nhận không được để trống!");
                continue; 
            }
            if (inputCode.equals(verificationCode)) {
                isCodeCorrect = true;
                break; 
            } else {
                attempts++;
                if (attempts < 3) {
                    JOptionPane.showMessageDialog(this, "Mã xác nhận không đúng! Bạn còn " + (3 - attempts) + " lần thử.");
                }
            }
        }
        if (isCodeCorrect) {
            dispose();
            MatKhauMoi mk = new MatKhauMoi();
            mk.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã nhập sai mã xác nhận 3 lần. Vui lòng thử lại sau.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Email không tồn tại!");
    }
} catch (SQLException ex) {
    Logger.getLogger(QuenMatKhau.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi kiểm tra email.");
}

    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel nen;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txttaikhoan;
    // End of variables declaration//GEN-END:variables
}
