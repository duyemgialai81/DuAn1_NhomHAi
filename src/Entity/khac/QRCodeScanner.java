/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.khac;

/**
 *
 * @author SingPC
 */
import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import java.awt.image.BufferedImage;

public class QRCodeScanner {
    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault(); // Lấy webcam mặc định
        if (webcam == null) {
            System.out.println("Không tìm thấy webcam!");
            return;
        }

        webcam.open(); // Mở webcam

        try {
            do {
                BufferedImage image = null;
                if (webcam.isOpen()) { // Kiểm tra nếu webcam đang mở
                    image = webcam.getImage(); // Lấy hình ảnh từ webcam
                }

                if (image == null) {
                    continue;
                }

                try {
                    // Đọc và xử lý mã QR từ hình ảnh
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Result result = new MultiFormatReader().decode(bitmap);

                    if (result != null) {
                        System.out.println("Đã quét mã QR: " + result.getText());
                        // handleQRCodeScan(result.getText()); // Gọi hàm xử lý mã QR
                    }
                } catch (NotFoundException e) {
                    // Không tìm thấy mã QR, bỏ qua
                }

                Thread.sleep(100); // Giảm tải CPU
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            webcam.close(); // Đóng webcam khi thoát chương trình
        }
    }
}

