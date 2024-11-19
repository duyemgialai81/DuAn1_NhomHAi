/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

/**
 *
 * @author SingPC
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class CreatePDFWithImage {
 public static void main(String[] args) {
    String imagePath = "D:\\anh\\SP00036.png";
    String outputPath = "D:\\output_with_imageee.pdf";
    try (PDDocument document = new PDDocument()) {
        PDPage page = new PDPage();
        document.addPage(page);
        File file = new File(imagePath);
        if (!file.exists()) {
            throw new IOException("Tệp hình ảnh không tồn tại: " + imagePath);
        }
        PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        float imageWidth = 100; 
        float imageHeight = 100;
        float imageX = (pageWidth - imageWidth) / 2; 
        float imageY = (pageHeight - imageHeight) / 2;
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.drawImage(pdImage, imageX, imageY, imageWidth, imageHeight);
        }
        document.save(outputPath);
        System.out.println("PDF created successfully with centered image!");
} catch (IOException e) {
        e.printStackTrace();
    }
}
 

}




