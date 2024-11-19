/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

/**
 *
 * @author SingPC
 */

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class ItextImageExample {
    public static void main(String[] args) {

  try {
      PdfDocument pdfDoc = new PdfDocument(new PdfWriter("image.pdf"));
      Document document = new Document(pdfDoc);

      ImageData data = ImageDataFactory.create("logo.png");
      Image image = new Image(data);
      document.add(image);

      data = ImageDataFactory.create("create-pdf-with-itext-java.jpg");
      image = new Image(data);
      // set Absolute Position
      image.setFixedPosition(220f, 550f);
      // set Scaling
      image.setAutoScaleHeight(false);
      image.setAutoScaleWidth(false);
      // set Rotation
      image.setRotationAngle(45f);
      document.add(image);

      document.close();
  } catch (Exception e) {
      e.printStackTrace();
  }
    }
}
