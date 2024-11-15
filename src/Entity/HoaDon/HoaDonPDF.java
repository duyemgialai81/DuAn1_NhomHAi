/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.HoaDon;

/**
 *
 * @author SingPC
 */
import KetNoiSQL.ketnoi;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileOutputStream;

public class HoaDonPDF {
    public static void main(String[] args) {
        double tongTien = 100000;  // Giả sử giá trị của tổng tiền là 100000 (thay thế bằng giá trị thực tế)
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedTongTien = formatter.format(tongTien);
        JTextField txtthanhtien = new JTextField();  // JTextField để hiển thị tổng tiền
        txtthanhtien.setText(formattedTongTien);  // Hiển thị tổng tiền lên JTextField

        // Lấy `maHoaDon` từ hàng được chọn trong bảng hóa đơn
        JTable tblhoadon1 = new JTable();  // Giả sử đây là bảng hiển thị danh sách hóa đơn
        int selectedRow = tblhoadon1.getSelectedRow();  // `tblHoaDon` là bảng hiển thị danh sách hóa đơn
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để cập nhật tổng tiền.");
            return;
        }
        int maHoaDon = Integer.parseInt(tblhoadon1.getValueAt(selectedRow, 0).toString());  // Cột 0 chứa `maHoaDon`

        // Câu lệnh SQL để cập nhật `tongTien` vào bảng `HoaDon`
        String sql = """
                     UPDATE HoaDon SET tong_tien = ?  WHERE id_ma_hoa_don = ?
                     """;
        
        try {
            Connection con = ketnoi.getConnection();  // Giả sử ketnoi là lớp kết nối cơ sở dữ liệu
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, tongTien);  // Truyền giá trị `tongTien` vào câu truy vấn
            ps.setInt(2, maHoaDon);  // Truyền `maHoaDon` để xác định hóa đơn cần cập nhật
            ps.executeUpdate();  // Thực hiện cập nhật
            JOptionPane.showMessageDialog(null, "Cập nhật tổng tiền thành công!");

            // Xóa dòng đã chọn trong bảng hóa đơn
            DefaultTableModel model = (DefaultTableModel) tblhoadon1.getModel();
            model.removeRow(selectedRow);

            // Xuất hóa đơn ra PDF
            createInvoicePDF(maHoaDon, formattedTongTien);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi khi cập nhật tổng tiền!");
        }
    }

    private static void createInvoicePDF(int maHoaDon, String tongTien) {
        try {
            // Tạo file PDF
            File file = new File("D:\\"+"HoaDon_" + maHoaDon + ".pdf");
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Tiêu đề Hóa Đơn
            document.add(new Paragraph("HÓA ĐƠN THANH TOÁN").setFont(StandardFonts.HELVETICA_BOLD).setFontSize(20));
            document.add(new Paragraph("Mã Hóa Đơn: " + maHoaDon));
            document.add(new Paragraph("Ngày: " + java.time.LocalDate.now()));
            document.add(new Paragraph("-------------------------------------------------"));

            // Thêm thông tin tổng tiền
            document.add(new Paragraph("Tổng Tiền: " + tongTien));
            document.add(new Paragraph("-------------------------------------------------"));

            // Thêm thông báo
            document.add(new Paragraph("Cảm ơn quý khách đã mua hàng!"));
            document.close();

            JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công tại: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi khi tạo hóa đơn PDF!");
        }
    }
}

