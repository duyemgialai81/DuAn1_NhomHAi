/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

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
/**
 *
 * @author SingPC
 */
public class ChucNangThongKe extends javax.swing.JPanel {

    /**
     * Creates new form ChucNangThongKe
     */
    public ChucNangThongKe() {
        initComponents();
showPieChart();
showLineChart();
showBarChart();
    }
    public void showPieChart() {
    DefaultPieDataset pieDataset = new DefaultPieDataset();
    String query = """
                   SELECT sp.ten_san_pham, 
                   SUM((sp.gia_ban - sp.gia_nhap) * cthd.so_luong) AS doanhThu
                   FROM chitietdonhang cthd
                   JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham
                   GROUP BY sp.ten_san_pham
                   ORDER BY doanhThu DESC;
                   """;
    
    try (Connection conn = ketnoi.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            String tenSanPham = rs.getString("ten_san_pham");
            double doanhThu = rs.getDouble("doanhThu");
            pieDataset.setValue(tenSanPham, doanhThu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JFreeChart pieChart = ChartFactory.createPieChart(
        "Doanh Thu Sản Phẩm",   
        pieDataset,              
        true,                   
        true,                   
        false                    
    );
    PiePlot piePlot = (PiePlot) pieChart.getPlot();
    piePlot.setSectionPaint("IPhone 5s", new Color(255, 255, 102));
    piePlot.setSectionPaint("SamSung Grand", new Color(102, 255, 102));
    piePlot.setSectionPaint("MotoG", new Color(255, 102, 153));
    piePlot.setSectionPaint("Nokia Lumia", new Color(0, 204, 204));
    piePlot.setBackgroundPaint(Color.white);
    ChartPanel chartPanel = new ChartPanel(pieChart);
    panelBarChart.removeAll();
    panelBarChart.add(chartPanel, BorderLayout.CENTER);
    panelBarChart.validate();
}
    public void showLineChart() {
    // Tạo dataset cho biểu đồ
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Truy vấn SQL để lấy dữ liệu doanh thu theo tháng
    String query = """
               SELECT sp.ten_san_pham,
                      SUM(cthd.so_luong) AS soLuongDaBan,
                      (sp.so_luong_ton - SUM(cthd.so_luong)) AS soLuongConLai,
                      FORMAT(hd.ngay_dat, 'yyyy-MM') AS thang,
                      SUM((sp.gia_ban - sp.gia_nhap) * cthd.so_luong) AS doanhThu
               FROM ChiTietDonHang cthd
               JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham
               JOIN DonHang hd ON cthd.ma_don_hang = hd.id_ma_don_hang
               GROUP BY sp.ten_san_pham, sp.so_luong_ton, FORMAT(hd.ngay_dat, 'yyyy-MM')
               ORDER BY thang ASC;
               """;

    try (Connection conn = ketnoi.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        
        System.out.println("Đang kết nối và truy vấn dữ liệu...");
        
        // Kiểm tra nếu không có dữ liệu
        if (!rs.isBeforeFirst()) {
            System.out.println("Truy vấn không trả về kết quả.");
            return;
        }
        
        // Duyệt qua kết quả và thêm vào dataset
        while (rs.next()) {
            String thang = rs.getString("thang"); // Tháng (yyyy-MM)
            double doanhThu = rs.getDouble("doanhThu"); // Doanh thu
            System.out.println("Tháng: " + thang + ", Doanh thu: " + doanhThu);

            // Thêm dữ liệu vào dataset
            dataset.setValue(doanhThu, "Doanh Thu", thang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return;
    }

    // Tạo biểu đồ đường
    JFreeChart lineChart = ChartFactory.createLineChart(
        "Doanh Thu Hàng Tháng",     // Tiêu đề
        "Tháng",                   // Trục x
        "Doanh Thu (Triệu)",       // Trục y
        dataset,                   // Dữ liệu
        PlotOrientation.VERTICAL,  // Kiểu biểu đồ
        false,                     // Không hiển thị chú thích
        true,                      // Hiển thị tooltip
        false                      // Không sử dụng URL
    );

    // Tùy chỉnh biểu đồ
    CategoryPlot plot = lineChart.getCategoryPlot();
    plot.setBackgroundPaint(Color.RED); // Màu nền
    plot.setRangeGridlinePaint(Color.BLACK); // Màu đường lưới ngang

    // Tùy chỉnh đường trong biểu đồ
    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, new Color(204, 0, 51)); // Màu đường biểu đồ

    // Hiển thị biểu đồ trên giao diện
    ChartPanel lineChartPanel = new ChartPanel(lineChart);
    panelLineChart.setLayout(new BorderLayout());
    panelLineChart.removeAll();
    panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
    panelLineChart.revalidate();
    panelLineChart.repaint();
}
public void showBarChart() {
    // Tạo dataset cho biểu đồ
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Truy vấn SQL để lấy dữ liệu số lượng sản phẩm đã bán và còn lại
    String query = """
               SELECT sp.ten_san_pham,
                      SUM(cthd.so_luong) AS soLuongDaBan,
                      (sp.so_luong_ton - SUM(cthd.so_luong)) AS soLuongConLai
               FROM ChiTietDonHang cthd
               JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham
               GROUP BY sp.ten_san_pham, sp.so_luong_ton
               ORDER BY sp.ten_san_pham ASC;
               """;

    try (Connection conn = ketnoi.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        
        // Duyệt qua kết quả và thêm vào dataset
        while (rs.next()) {
            String tenSanPham = rs.getString("ten_san_pham"); // Tên sản phẩm
            int soLuongDaBan = rs.getInt("soLuongDaBan"); // Số lượng đã bán
            int soLuongConLai = rs.getInt("soLuongConLai"); // Số lượng còn lại

            // Thêm dữ liệu vào dataset
            dataset.setValue(soLuongDaBan, "Đã bán", tenSanPham);
            dataset.setValue(soLuongConLai, "Còn lại", tenSanPham);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Tạo biểu đồ hình cột
    JFreeChart barChart = ChartFactory.createBarChart(
        "Số Lượng Sản Phẩm",       // Tiêu đề
        "Sản Phẩm",               // Nhãn trục X
        "Số Lượng",               // Nhãn trục Y
        dataset,                  // Dữ liệu
        PlotOrientation.VERTICAL, // Hướng biểu đồ
        true,                     // Hiển thị chú thích
        true,                     // Hiển thị tooltips
        false                     // Không sử dụng URL
    );

    // Tùy chỉnh biểu đồ
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(Color.white); // Màu nền
    plot.setRangeGridlinePaint(Color.GRAY); // Màu đường lưới ngang

    // Hiển thị biểu đồ trên giao diện
    ChartPanel barChartPanel = new ChartPanel(barChart);
    panelBarChartt.removeAll();
    panelBarChartt.add(barChartPanel, BorderLayout.CENTER);
    panelBarChartt.validate();
}

//public void showPieChart(){
//        
//        //create dataset
//      DefaultPieDataset barDataset = new DefaultPieDataset( );
//      barDataset.setValue( "IPhone 5s" , new Double( 20 ) );  
//      barDataset.setValue( "SamSung Grand" , new Double( 20 ) );   
//      barDataset.setValue( "MotoG" , new Double( 40 ) );    
//      barDataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
//      
//      //create chart
//       JFreeChart piechart = ChartFactory.createPieChart("mobile sales",barDataset, false,true,false);//explain
//      
//        PiePlot piePlot =(PiePlot) piechart.getPlot();
//      
//       //changing pie chart blocks colors
//       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
//        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
//        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
//        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
//      
//       
//        piePlot.setBackgroundPaint(Color.white);
//        
//        //create chartPanel to display chart(graph)
//        ChartPanel barChartPanel = new ChartPanel(piechart);
//        panelBarChart.removeAll();
//        panelBarChart.add(barChartPanel, BorderLayout.CENTER);
//        panelBarChart.validate();
//    }
//
//    /*=============================================================================*/
//    
//
//    /*========================================================================================*/
//    
//    public void showBarChart(){
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.setValue(200, "Amount", "january");
//        dataset.setValue(150, "Amount", "february");
//        dataset.setValue(18, "Amount", "march");
//        dataset.setValue(100, "Amount", "april");
//        dataset.setValue(80, "Amount", "may");
//        dataset.setValue(250, "Amount", "june");
//        
//        JFreeChart chart = ChartFactory.createBarChart("contribution","monthly","amount", 
//                dataset, PlotOrientation.VERTICAL, false,true,false);
//        
//        CategoryPlot categoryPlot = chart.getCategoryPlot();
//        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
//        categoryPlot.setBackgroundPaint(Color.WHITE);
//        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
//        Color clr3 = new Color(204,0,51);
//        renderer.setSeriesPaint(0, clr3);
//        
//        ChartPanel barpChartPanel = new ChartPanel(chart);
//        jPanel1.removeAll();
//        jPanel1.add(barpChartPanel, BorderLayout.CENTER);
//        jPanel1.validate();
//        
//        
//    }
//    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        panelBarChartt = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelBarChart = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(panelBarChartt, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(panelBarChartt, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );

        jTabbedPane1.addTab("THỐNG KÊ DOANH THU", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 555, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 113, Short.MAX_VALUE)
                .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("THỐNG KÊ SẢN PHẨM BÁN CHẠY NHẤT", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("THỐNG KẾ SỐ LƯỢNG SẢN PHẨM ĐÃ BÁN VÀ CHƯA BÁN", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelBarChart;
    private javax.swing.JPanel panelBarChartt;
    private javax.swing.JPanel panelLineChart;
    // End of variables declaration//GEN-END:variables
}
