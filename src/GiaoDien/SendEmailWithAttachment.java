/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GiaoDien;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class SendEmailWithAttachment {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        final String user = "quysamset2@gmail.com"; // Thay bằng email của bạn
        final String password = "cwcbgrqbimkglhmy"; // Thay bằng mật khẩu ứng dụng

        String to = "doanngocduy62@gmail.com"; // Thay bằng email người nhận

        // Thiết lập thuộc tính cho email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // SSL
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

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
            String filename = "D:/chards.pdf"; // Đường dẫn đến file bạn muốn đính kèm
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("chards.pdf"); // Tên file đính kèm
            multipart.addBodyPart(messageBodyPart);

            // Thiết lập phần đính kèm vào message
            message.setContent(multipart);

            // Gửi email
            Transport.send(message);
            System.out.println("Email sent successfully with attachment.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}


