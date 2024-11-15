/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.khac;

/**
 *
 * @author SingPC
 */
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.charset.StandardCharsets;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.qrcode.QRCodeReader;

public class QRScannerForm extends JFrame {
    private JLabel imageLabel;
    private VideoCapture capture;

    public QRScannerForm() {
        setTitle("Quét mã QR từ Webcam");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        imageLabel = new JLabel();
        imageLabel.setSize(640, 480);
        add(imageLabel, BorderLayout.CENTER);
        JButton btnScan = new JButton("Bắt đầu quét mã QR");
        btnScan.addActionListener(e -> startQRScanning());
        add(btnScan, BorderLayout.SOUTH);
    }

    private void startQRScanning() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            JOptionPane.showMessageDialog(this, "Không thể mở webcam.");
            return;
        }
        new Thread(() -> {
            Mat frame = new Mat();
            while (capture.read(frame)) {
                BufferedImage image = matToBufferedImage(frame);
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(image)));

                String qrCodeText = scanQRCode(image);
                if (qrCodeText != null) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Nội dung mã QR: " + qrCodeText));
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            capture.release();
        }).start();
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }

    private static String scanQRCode(BufferedImage bufferedImage) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new QRCodeReader().decode(bitmap);
            return new String(result.getText().getBytes(), StandardCharsets.UTF_8);
        } catch (NotFoundException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QRScannerForm scannerForm = new QRScannerForm();
            scannerForm.setVisible(true);
        });
    }
}
