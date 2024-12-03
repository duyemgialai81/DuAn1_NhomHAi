/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

/**
 *
 * @author SingPC
 */
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class SendEmailWithAttachment {
    public static void main(String[] args) {
        // Tạo giao diện để chọn file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file để gửi");

        // Hiển thị hộp thoại chọn file
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn file người dùng đã chọn
            File selectedFile = fileChooser.getSelectedFile();
            String filename = selectedFile.getAbsolutePath(); // Đường dẫn tuyệt đối của file đã chọn

            // Gửi email với file đính kèm
            sendEmailWithAttachment(filename);
        } else {
            JOptionPane.showMessageDialog(null, "Không có file nào được chọn!");
        }
    }

    public static void sendEmailWithAttachment(String filename) {
        String host = "smtp.gmail.com";
        final String user = "quysamset2@gmail.com"; // Thay bằng email của bạn
        final String password = "cwcbgrqbimkglhmy"; // Thay bằng mật khẩu ứng dụng

        String to = "doanngocduy62@gmail.com"; // Thay bằng email người nhận

        // Thiết lập thuộc tính cho email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Dùng port 587 cho STARTTLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Sử dụng STARTTLS

        // Xác thực và gửi email
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);  // Sử dụng mật khẩu ứng dụng
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Email with Attachment");

            // Tạo phần thân email
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Here is the email with attachment.");

            // Tạo phần đính kèm
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Đính kèm file
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(new File(filename).getName()); // Lấy tên file từ đường dẫn
            multipart.addBodyPart(messageBodyPart);

            // Thiết lập phần đính kèm vào message
            message.setContent(multipart);

            // Gửi email
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Email sent successfully with attachment.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi gửi email!");
        }
    }
}



