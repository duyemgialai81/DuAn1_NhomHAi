/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.HoaDon.XuatHoaDon;
import Entity.thongke.ThongKeEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.chart.renderer.category.BarRenderer;
import java.sql.*;
import KetNoiSQL.ketnoi;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartUtilities;

public class ChucNangThongKe extends javax.swing.JPanel {

    public ChucNangThongKe() {
        initComponents();
        hienThiBieuDo();
        hienThiDuLieu();

    }

 public void hienThiBieuDo() {
        // Xóa nội dung cũ
        panelBarChartt.removeAll();

        // Lấy giá trị được chọn
        String selectedOption = (String) cboChonBieuDo.getSelectedItem();

        if ("Sản Phẩm Bán Chạy".equals(selectedOption)) {
            showBarChartBanChay();
            showPieChartbanChay();
        } else if ("Số Lượng Đã Bán".equals(selectedOption)) {
            showBarChart();
            showPieChart();
        }
        panelBarChartt.revalidate();
        panelBarChartt.repaint();
    }
public  ChartPanel showPieChart() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    int year = 2024;
    String query = """
          SELECT sp.ten_san_pham,
                                   SUM(cthd.so_luong) AS soLuongDaBan,
                                   sp.so_luong_ton AS soLuongConLai,
                                   ((sp.gia_ban - sp.gia_nhap) * SUM(cthd.so_luong)) AS loiNhuan
                            FROM ChiTietDonHang cthd
                            JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham
                            JOIN DonHang hd ON cthd.ma_don_hang = hd.id_ma_don_hang
                            WHERE YEAR(hd.ngay_dat) = ?
                            GROUP BY sp.ten_san_pham, sp.so_luong_ton, sp.gia_ban, sp.gia_nhap
                            ORDER BY sp.ten_san_pham ASC;
        """;
    try (Connection conn = ketnoi.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, year);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tenSanPham = rs.getString("ten_san_pham");
                int soLuongDaBan = rs.getInt("soLuongDaBan");
                int soLuongConLai = rs.getInt("soLuongConLai");
                dataset.setValue(tenSanPham, soLuongDaBan);
                dataset.setValue(tenSanPham, soLuongConLai);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JFreeChart pieChart = ChartFactory.createPieChart(
        "Tỷ lệ sản phẩm bán ra năm " + year,  
        dataset,                            
        true,                              
        true,                               
        false                              
    );
    PiePlot plot = (PiePlot) pieChart.getPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setOutlineVisible(false);
    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
    plot.setSectionPaint("Đã Bán", Color.BLUE);
    plot.setSectionPaint("Còn Lại", Color.RED);
    ChartPanel pieChartPanel = new ChartPanel(pieChart);
    panelLineChart.setLayout(new BorderLayout());
    panelLineChart.removeAll();
    panelLineChart.add(pieChartPanel, BorderLayout.CENTER);
    panelLineChart.validate();
    panelLineChart.repaint();
        return pieChartPanel;
}
public ChartPanel showPieChartbanChay() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    int year = 2024;
    String query = """
       SELECT TOP 5 
                                            sp.ten_san_pham AS TenSanPham,
                                            SUM(ctdh.so_luong) AS TongSoLuongBanRa
                                        FROM 
                                            ChiTietDonHang ctdh
                                        INNER JOIN 
                                            SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
                                        INNER JOIN 
                                            DonHang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
                                        WHERE 
                                            YEAR(dh.ngay_dat) = ? 
                                        GROUP BY 
                                            sp.ten_san_pham
                                        ORDER BY 
                                            TongSoLuongBanRa DESC
        """;
    try (Connection conn = ketnoi.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, year);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongDaBan = rs.getInt("TongSoLuongBanRa");
                dataset.setValue(tenSanPham, soLuongDaBan);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JFreeChart pieChart = ChartFactory.createPieChart(
        "SẢN PHẨM BÁN CHẠY NHẤT NĂM" + year,  
        dataset,                            
        true,                              
        true,                               
        false                              
    );
    PiePlot plot = (PiePlot) pieChart.getPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setOutlineVisible(false);
    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
    plot.setSectionPaint("Đã bán", Color.BLUE);
    ChartPanel pieChartPanel = new ChartPanel(pieChart);
    panelLineChart.setLayout(new BorderLayout());
    panelLineChart.removeAll();
    panelLineChart.add(pieChartPanel, BorderLayout.CENTER);
    panelLineChart.validate();
    panelLineChart.repaint();
    return pieChartPanel;
}
public ChartPanel showBarChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    int year = 2024;
    String query = """
        SELECT sp.ten_san_pham,
                           SUM(cthd.so_luong) AS soLuongDaBan,
                           sp.so_luong_ton AS soLuongConLai,
                           ((sp.gia_ban - sp.gia_nhap) * SUM(cthd.so_luong)) AS loiNhuan
                    FROM ChiTietDonHang cthd
                    JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham
                    JOIN DonHang hd ON cthd.ma_don_hang = hd.id_ma_don_hang
                    WHERE YEAR(hd.ngay_dat) = ?
                    GROUP BY sp.ten_san_pham, sp.so_luong_ton, sp.gia_ban, sp.gia_nhap
                    ORDER BY sp.ten_san_pham ASC;
        """;
    try (Connection conn = ketnoi.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, year);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tenSanPham = rs.getString("ten_san_pham");
                int soLuongDaBan = rs.getInt("soLuongDaBan");    
                int soLuongConLai = rs.getInt("soLuongConLai");  
                dataset.setValue(soLuongDaBan, "Đã bán", tenSanPham);
                dataset.setValue(soLuongConLai, "Còn lại", tenSanPham);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JFreeChart barChart = ChartFactory.createBarChart(
        "Số Lượng Sản Phẩm Năm " + year,
        "Sản Phẩm",                      
        "Số Lượng",                       
        dataset,                               
        PlotOrientation.VERTICAL,          
        true,                              
        true,                                
        false                              
    );
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setRangeGridlinePaint(Color.GRAY);
    ChartPanel barChartPanel = new ChartPanel(barChart);
    panelBarChartt.setLayout(new BorderLayout());
    panelBarChartt.removeAll();
    panelBarChartt.add(barChartPanel, BorderLayout.CENTER);
    panelBarChartt.validate();
    panelBarChartt.repaint();
        return barChartPanel;
}
public ChartPanel showBarChartBanChay() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    int year = 2024;
    String query = """
        SELECT TOP 5 
                        sp.ten_san_pham AS TenSanPham,
                        SUM(ctdh.so_luong) AS TongSoLuongBanRa
                    FROM 
                        ChiTietDonHang ctdh
                    INNER JOIN 
                        SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
                    INNER JOIN 
                        DonHang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
                    WHERE 
                        YEAR(dh.ngay_dat) = ? 
                    GROUP BY 
                        sp.ten_san_pham
                    ORDER BY 
                        TongSoLuongBanRa DESC
        """;
    try (Connection conn = ketnoi.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, year);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongBanRa = rs.getInt("TongSoLuongBanRa");    
                dataset.setValue(soLuongBanRa, "Số lượng đã bán", tenSanPham);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JFreeChart barChart = ChartFactory.createBarChart(
        "SẢN PHẨM BÁN CHẠY NHẤT NĂM " + year,
        "Sản Phẩm",                      
        "Số Lượng",                       
        dataset,                               
        PlotOrientation.VERTICAL,          
        true,                              
        true,                                
        false                              
    );
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setRangeGridlinePaint(Color.GRAY);
    ChartPanel barChartPanel = new ChartPanel(barChart);
    panelBarChartt.setLayout(new BorderLayout());
    panelBarChartt.removeAll();
    panelBarChartt.add(barChartPanel, BorderLayout.CENTER);
    panelBarChartt.validate();
    panelBarChartt.repaint();
    return barChartPanel;
}
public void searchByDateRange() {
    java.util.Date startDate = ngaybatdau.getDate();
    java.util.Date endDate = ngayketthuc.getDate();
    if (startDate == null || endDate == null) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc!");
        return;
    }
    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String query = """
        SELECT 
            sp.ten_san_pham AS tenSanPham,
            ctdh.so_luong AS soLuongDaBan,
            (sp.so_luong_ton) AS soLuongConLai
        FROM ChiTietDonHang ctdh
        JOIN SanPham sp ON ctdh.ma_san_pham = sp.id_ma_san_pham
        JOIN DonHang dh ON ctdh.ma_don_hang = dh.id_ma_don_hang
        WHERE dh.ngay_dat BETWEEN ? AND ?
        ORDER BY dh.ngay_dat ASC, sp.ten_san_pham ASC;
        """;
    try (Connection conn = ketnoi.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setDate(1, sqlStartDate);
        stmt.setDate(2, sqlEndDate);
        try (ResultSet rs = stmt.executeQuery()) {
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu trong khoảng thời gian này.");
                showEmptyChart();
                return;
            }
            while (rs.next()) {
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongDaBan = rs.getInt("soLuongDaBan");
                int soLuongConLai = rs.getInt("soLuongConLai");
                dataset.setValue(soLuongDaBan, "Đã bán", tenSanPham);
                dataset.setValue(soLuongConLai, "Còn lại", tenSanPham);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        e.printStackTrace();
        return;
    }
    JFreeChart barChart = ChartFactory.createBarChart(
        "Số Lượng Áo Bán Trong Khoảng Ngày",
        "Sản Phẩm",                        
        "Số Lượng",                          
        dataset,                             
        PlotOrientation.VERTICAL,      
        true,                            
        true,                            
        false                            
    );
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setRangeGridlinePaint(Color.GRAY);
    ChartPanel barChartPanel = new ChartPanel(barChart);
    panelBarChartt.setLayout(new BorderLayout());
    panelBarChartt.removeAll(); 
    panelBarChartt.add(barChartPanel, BorderLayout.CENTER);
    panelBarChartt.validate(); 
    panelBarChartt.repaint();  
}
private void showEmptyChart() {
    DefaultCategoryDataset emptyDataset = new DefaultCategoryDataset();
    JFreeChart emptyChart = ChartFactory.createBarChart(
        "Không có dữ liệu",
        "Sản Phẩm",        
        "Số Lượng",       
        emptyDataset,      
        PlotOrientation.VERTICAL,
        false,         
        false,            
        false             
    );
    ChartPanel emptyChartPanel = new ChartPanel(emptyChart);
    panelBarChartt.setLayout(new BorderLayout());
    panelBarChartt.removeAll();
    panelBarChartt.add(emptyChartPanel, BorderLayout.CENTER);
    panelBarChartt.validate();
    panelBarChartt.repaint();
}
public ArrayList<ThongKeEntity>getAlll(){
    ArrayList<ThongKeEntity> ls = new ArrayList<>();
    String sql = """
                 			SELECT 
                                                    SUM((sp.gia_ban - sp.gia_nhap) * cthd.so_luong) AS tongLoiNhuan
                                                FROM ChiTietDonHang cthd
                                                JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham
                                                JOIN DonHang hd ON cthd.ma_don_hang = hd.id_ma_don_hang
                                                WHERE YEAR(hd.ngay_dat) = 2024
                 """;
    try {
        Connection con = ketnoi.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    ThongKeEntity tk = new ThongKeEntity();
                    tk.setDoanhThu(rs.getFloat("tongLoiNhuan"));
                    ls.add(tk);
                }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ls;
             
}
public void hienThiDuLieu(){
      DecimalFormat formatterr = new DecimalFormat("###,###,###");
    ArrayList<ThongKeEntity> hdList = getAlll();
    System.out.println("Số lượng hóa đơn: " + hdList.size());
    if (!hdList.isEmpty()) {
        System.out.println("Dữ liệu hóa đơn đầu tiên: " + hdList.get(0).toString());
         ThongKeEntity firstOrder = hdList.get(0);
       txtloinhuan.setText(formatterr.format((firstOrder.getDoanhThu())));
}
}
 public void exportChartsToPDF(ChartPanel pieChartPanel, ChartPanel barChartPanel, String pdfFilePath) {
        Document document = new Document();
        try {
            // Tạo PdfWriter để xuất ra file PDF
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            // Xuất biểu đồ tròn (Pie Chart) ra PDF
            JFreeChart pieChart = pieChartPanel.getChart();
            BufferedImage pieImage = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = pieImage.createGraphics();
            pieChart.draw(g2d, new Rectangle(0, 0, 500, 400));  // Vẽ biểu đồ vào BufferedImage

            // Chuyển đổi BufferedImage thành byte array
            ByteArrayOutputStream pieOut = new ByteArrayOutputStream();
            ChartUtilities.writeBufferedImageAsPNG(pieOut, pieImage);
            Image piePdfImage = Image.getInstance(pieOut.toByteArray());
            piePdfImage.scaleToFit(500, 400);
            document.add(piePdfImage);  // Thêm hình ảnh vào tài liệu PDF

            // Thêm một khoảng trống giữa các biểu đồ
            document.add(new com.itextpdf.text.Paragraph(" "));

            // Xuất biểu đồ cột (Bar Chart) ra PDF
            JFreeChart barChart = barChartPanel.getChart();
            BufferedImage barImage = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2dBar = barImage.createGraphics();
            barChart.draw(g2dBar, new Rectangle(0, 0, 500, 400));  // Vẽ biểu đồ vào BufferedImage

            // Chuyển đổi BufferedImage thành byte array
            ByteArrayOutputStream barOut = new ByteArrayOutputStream();
            ChartUtilities.writeBufferedImageAsPNG(barOut, barImage);
            Image barPdfImage = Image.getInstance(barOut.toByteArray());
            barPdfImage.scaleToFit(500, 400);
            document.add(barPdfImage);  // Thêm hình ảnh vào tài liệu PDF

            // Đóng tài liệu PDF
            document.close();
            System.out.println("Biểu đồ đã được xuất ra PDF thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public void hienThiBieuDooo() {
      panelBarChartt.removeAll();
      String selectedOption = (String) cboChonBieuDo.getSelectedItem();
      if ("Sản Phẩm Bán Chạy".equals(selectedOption)) {
          ChartPanel pieChartPanel = showPieChartbanChay();
          ChartPanel barChartPanel = showBarChartBanChay();
          if (pieChartPanel != null && barChartPanel != null) {
              String pdfFilePath = "D:/duyem.pdf";
              exportChartsToPDF(pieChartPanel, barChartPanel, pdfFilePath);
          } else {
              System.out.println("Biểu đồ không được tạo đúng cách.");
          }
      } else if ("Số Lượng Đã Bán".equals(selectedOption)) {
          ChartPanel pieChartPanel = showPieChart();
          ChartPanel barChartPanel = showBarChart();
          if (pieChartPanel != null && barChartPanel != null) {
              String pdfFilePath = "D:/duyem.pdf";
              exportChartsToPDF(pieChartPanel, barChartPanel, pdfFilePath);
          } else {
              System.out.println("Biểu đồ không được tạo đúng cách.");
          }
      }
      panelBarChartt.revalidate();
      panelBarChartt.repaint();
    }
public void generateAndExportCharts() {
    
   
    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();
        panelBarChartt = new javax.swing.JPanel();
        ngaybatdau = new com.toedter.calendar.JDateChooser();
        ngayketthuc = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtloinhuan = new javax.swing.JLabel();
        cboChonBieuDo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        ngaybatdau.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ngaybatdauAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ngaybatdau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ngaybatdauMouseClicked(evt);
            }
        });

        ngayketthuc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ngayketthucAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ngayketthuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ngayketthucMouseClicked(evt);
            }
        });

        jButton1.setText("tìm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtloinhuan.setFont(new java.awt.Font("Segoe UI", 1, 38)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        cboChonBieuDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số Lượng Đã Bán", "Sản Phẩm Bán Chạy" }));
        cboChonBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChonBieuDoActionPerformed(evt);
            }
        });

        jButton2.setText("PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(panelBarChartt, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboChonBieuDo, 0, 321, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ngaybatdau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayketthuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboChonBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(panelBarChartt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
      
        searchByDateRange();
    }//GEN-LAST:event_jButton1MouseClicked

    private void ngayketthucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ngayketthucMouseClicked
       
    }//GEN-LAST:event_ngayketthucMouseClicked

    private void ngayketthucAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ngayketthucAncestorAdded
    
    }//GEN-LAST:event_ngayketthucAncestorAdded

    private void ngaybatdauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ngaybatdauMouseClicked

    }//GEN-LAST:event_ngaybatdauMouseClicked

    private void ngaybatdauAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ngaybatdauAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ngaybatdauAncestorAdded

    private void cboChonBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChonBieuDoActionPerformed
        // TODO add your handling code here:
        hienThiBieuDo();
    }//GEN-LAST:event_cboChonBieuDoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        hienThiBieuDooo();
    }//GEN-LAST:event_jButton2ActionPerformed


//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChucNangThongKe().setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboChonBieuDo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private com.toedter.calendar.JDateChooser ngaybatdau;
    private com.toedter.calendar.JDateChooser ngayketthuc;
    private javax.swing.JPanel panelBarChartt;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JLabel txtloinhuan;
    // End of variables declaration//GEN-END:variables
}
