/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

/**
 *
 * @author SingPC
 */
import java.text.DecimalFormat;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.sql.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

public class ProductSalesChart extends ApplicationFrame {
    public ProductSalesChart(String title) {
        super(title);
        
        // Dữ liệu biểu đồ
        DefaultCategoryDataset dataset = createDataset();
        
        // Tạo biểu đồ cột
        JFreeChart chart = ChartFactory.createBarChart(
                "Sản phẩm bán chạy",   // Tiêu đề biểu đồ
                "Sản phẩm",            // Trục x
                "Doanh thu",           // Trục y
                dataset                // Dữ liệu
        );
        
        // Chỉnh sửa biểu đồ để đảo ngược trục Y
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeGridlinesVisible(true); // Hiện lưới cho trục Y
        plot.setRangeGridlinePaint(Color.BLACK); // Màu đường lưới
        plot.getRangeAxis().setInverted(true);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = """
                       SELECT TOP 10 sp.ten_san_pham, 
                                     SUM(cthd.so_luong) AS soLuongBan, 
                                     SUM((sp.gia_ban - sp.gia_nhap) * cthd.so_luong) AS doanhThu 
                       FROM chitietdonhang cthd 
                       JOIN SanPham sp ON cthd.ma_san_pham = sp.id_ma_san_pham 
                       GROUP BY sp.ten_san_pham 
                       ORDER BY doanhThu DESC;
                       """;
        
        DecimalFormat formatter = new DecimalFormat("###,###,###");  // Định dạng tiền tệ (chứa dấu phẩy)

        try (Connection conn = ketnoi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                String tenSanPham = rs.getString("ten_san_pham");
                double doanhThu = rs.getDouble("doanhThu");
                
                // Định dạng doanh thu thành tiền tệ
                String formattedDoanhThu = formatter.format(doanhThu);
                
                // Thêm dữ liệu vào dataset
                dataset.addValue(doanhThu, "Doanh thu", tenSanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dataset;
    }

    // Chạy ứng dụng với biểu đồ
    public static void main(String[] args) {
        ProductSalesChart chart = new ProductSalesChart("Thống kê sản phẩm bán chạy");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
