/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

/**
 *
 * @author SingPC
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;

public class ChartExample {

    private JPanel jPanel1;  // Panel cho biểu đồ tròn
    private JPanel jPanel2;  // Panel cho biểu đồ cột
    private JPanel jPanel3;  // Panel cho biểu đồ đường

    public ChartExample() {
        JFrame frame = new JFrame("Biểu đồ");
        frame.setLayout(new GridLayout(1, 3));

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        frame.add(jPanel1);
        frame.add(jPanel2);
        frame.add(jPanel3);

        showPieChart();  // Hiển thị biểu đồ tròn mặc định
        showBarChart();  // Hiển thị biểu đồ cột
        showLineChart(); // Hiển thị biểu đồ đường

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Hiển thị biểu đồ tròn
    public void showPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("A", 45);
        dataset.setValue("B", 25);
        dataset.setValue("C", 30);

        JFreeChart chart = ChartFactory.createPieChart(
                "Biểu đồ tròn",  // Tên biểu đồ
                dataset,         // Dữ liệu
                true,            // Có hiển thị chú thích
                true,            // Có hiển thị thông tin
                false            // Không sử dụng tooltips
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel1.removeAll();
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    // Hiển thị biểu đồ cột
    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Series1", "A");
        dataset.addValue(4.0, "Series1", "B");
        dataset.addValue(3.0, "Series1", "C");

        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ cột",         // Tên biểu đồ
                "Thể loại",            // Tên trục X
                "Giá trị",             // Tên trục Y
                dataset,               // Dữ liệu
                org.jfree.chart.plot.PlotOrientation.VERTICAL, // Định hướng
                true,                  // Hiển thị chú thích
                true,                  // Hiển thị thông tin
                false                  // Không dùng tooltips
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel2.removeAll();
        jPanel2.add(chartPanel, BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    // Hiển thị biểu đồ đường
    public void showLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Series1", "A");
        dataset.addValue(4.0, "Series1", "B");
        dataset.addValue(3.0, "Series1", "C");

        JFreeChart chart = ChartFactory.createLineChart(
                "Biểu đồ đường",      // Tên biểu đồ
                "Thể loại",           // Tên trục X
                "Giá trị",            // Tên trục Y
                dataset,              // Dữ liệu
                org.jfree.chart.plot.PlotOrientation.VERTICAL, // Định hướng
                true,                 // Hiển thị chú thích
                true,                 // Hiển thị thông tin
                false                 // Không dùng tooltips
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel3.removeAll();
        jPanel3.add(chartPanel, BorderLayout.CENTER);
        jPanel3.revalidate();
        jPanel3.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChartExample());
    }
}

