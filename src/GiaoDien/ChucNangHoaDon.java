/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GiaoDien;

import Entity.DonHangChiTiet.DonHangChiTietEntity;
import Entity.HoaDon.HoaDonXemDuLieu;
import GiaoDien.HoaDonChiTiet;
import Repository.DonHangChiTietRepository;
import Repository.HoaDonrepository;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SingPC
 */
public class ChucNangHoaDon extends javax.swing.JPanel {
private HoaDonrepository hd = new HoaDonrepository();
private DefaultTableModel md = new DefaultTableModel();
private DonHangChiTietRepository ct = new DonHangChiTietRepository();

    /**
     * Creates new form ChucNangHoaDon
     */
    public ChucNangHoaDon() {
        initComponents();
        
        hienThiDuLieu(hd.getAllChiTietHoaDon());

    }
    public void hienThiDuLieu(ArrayList<HoaDonChiTiet> hoaDon){
        md = (DefaultTableModel) tbl_hoaDon.getModel();
        md.setRowCount(0);
        for (HoaDonChiTiet hoaDonXemDuLieu : hoaDon) {
            md.addRow(new Object[]{
                hoaDonXemDuLieu.getMaHoaDon(), hoaDonXemDuLieu.getTongTien(),hoaDonXemDuLieu.getThanhToan(),hoaDonXemDuLieu.getTienKhachDua(), hoaDonXemDuLieu.getTienTraKhach(), hoaDonXemDuLieu.getPhuongThuc(),hoaDonXemDuLieu.getNgayDat()
            });
        }
    }
    public void hienThiDonHangChiTiet(ArrayList<DonHangChiTietEntity> chitiet){
        md = (DefaultTableModel)tbl_hoaDonChiTiet.getModel();
        md.setRowCount(0);
        for (DonHangChiTietEntity donHangChiTietEntity : chitiet) {
            md.addRow(new Object[]{
                donHangChiTietEntity.getMaSanPham(), donHangChiTietEntity.getTenSanPham(), donHangChiTietEntity.getSoLuong(), donHangChiTietEntity.getGiaBan(),donHangChiTietEntity.getTongTien(), donHangChiTietEntity.getTrangThai()
            });
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_HoaDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_timKiemHoaDon = new javax.swing.JTextField();
        jPanel_HinhThucGiaoHang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbo_hinhThucGiaoHang = new javax.swing.JComboBox<>();
        btn_timKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hoaDon = new javax.swing.JTable();
        jPanel_TrangThaiThanhToan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbo_trangThaiThanhToan = new javax.swing.JComboBox<>();
        jPanel_HinhThucThanhToan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbo_hinhThucThanhToan = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_hoaDonChiTiet = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel_HoaDon.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_HoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel_HoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm Kiếm Hoá Đơn:");
        jPanel_HoaDon.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 49, -1, -1));

        txt_timKiemHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_timKiemHoaDonKeyTyped(evt);
            }
        });
        jPanel_HoaDon.add(txt_timKiemHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 46, 420, -1));

        jPanel_HinhThucGiaoHang.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_HinhThucGiaoHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Hình Thức Giao Hàng:");

        cbo_hinhThucGiaoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Bán Trực Tiếp", "Khác" }));

        javax.swing.GroupLayout jPanel_HinhThucGiaoHangLayout = new javax.swing.GroupLayout(jPanel_HinhThucGiaoHang);
        jPanel_HinhThucGiaoHang.setLayout(jPanel_HinhThucGiaoHangLayout);
        jPanel_HinhThucGiaoHangLayout.setHorizontalGroup(
            jPanel_HinhThucGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HinhThucGiaoHangLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel_HinhThucGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbo_hinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel_HinhThucGiaoHangLayout.setVerticalGroup(
            jPanel_HinhThucGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HinhThucGiaoHangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_hinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel_HoaDon.add(jPanel_HinhThucGiaoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 97, -1, -1));

        btn_timKiem.setBackground(new java.awt.Color(255, 204, 0));
        btn_timKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_timKiem.setText("Tìm Kiếm");
        btn_timKiem.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                btn_timKiemComponentRemoved(evt);
            }
        });
        jPanel_HoaDon.add(btn_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        tbl_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hoá Đơn", "Tổng Tiền", "Thanh Toán", "Tiền Khách Đưa", "Tiền Trả Khách", "Hình Thức Thanh Toán", "Ngày Lập Hoá Đơn"
            }
        ));
        tbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoaDon);

        jPanel_HoaDon.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1090, 148));

        jPanel_TrangThaiThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_TrangThaiThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Trạng Thái Thanh Toán:");

        cbo_trangThaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Chờ Thanh Toán", "Đã Thanh Toán", "Đã Huỷ" }));

        javax.swing.GroupLayout jPanel_TrangThaiThanhToanLayout = new javax.swing.GroupLayout(jPanel_TrangThaiThanhToan);
        jPanel_TrangThaiThanhToan.setLayout(jPanel_TrangThaiThanhToanLayout);
        jPanel_TrangThaiThanhToanLayout.setHorizontalGroup(
            jPanel_TrangThaiThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TrangThaiThanhToanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_TrangThaiThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbo_trangThaiThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel_TrangThaiThanhToanLayout.setVerticalGroup(
            jPanel_TrangThaiThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TrangThaiThanhToanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_trangThaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel_HoaDon.add(jPanel_TrangThaiThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 97, 170, -1));

        jPanel_HinhThucThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_HinhThucThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Hình Thức Thanh Toán:");

        cbo_hinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Tiền Mặt", "Chuyển Khoản", "Quẹt Thẻ" }));

        javax.swing.GroupLayout jPanel_HinhThucThanhToanLayout = new javax.swing.GroupLayout(jPanel_HinhThucThanhToan);
        jPanel_HinhThucThanhToan.setLayout(jPanel_HinhThucThanhToanLayout);
        jPanel_HinhThucThanhToanLayout.setHorizontalGroup(
            jPanel_HinhThucThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HinhThucThanhToanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_HinhThucThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbo_hinhThucThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel_HinhThucThanhToanLayout.setVerticalGroup(
            jPanel_HinhThucThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HinhThucThanhToanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_HoaDon.add(jPanel_HinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, 110));

        tbl_hoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Bán", "Tổng Tiền", "Trạng Thái"
            }
        ));
        jScrollPane3.setViewportView(tbl_hoaDonChiTiet);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 1114, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timKiemComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_btn_timKiemComponentRemoved
        // TODO add your handling code here:
   
    }//GEN-LAST:event_btn_timKiemComponentRemoved

    private void txt_timKiemHoaDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemHoaDonKeyTyped
        // TODO add your handling code here:
 
    }//GEN-LAST:event_txt_timKiemHoaDonKeyTyped

    private void tbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoaDonMouseClicked
        // TODO add your handling code here:
        int selectedRowHoaDon = tbl_hoaDon.getSelectedRow();
        if (selectedRowHoaDon != -1) {
            String maGioHangHienTai = tbl_hoaDon.getValueAt(selectedRowHoaDon, 0).toString();
            hienThiDonHangChiTiet(ct.getAll(maGioHangHienTai));
        }

    }//GEN-LAST:event_tbl_hoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_timKiem;
    private javax.swing.JComboBox<String> cbo_hinhThucGiaoHang;
    private javax.swing.JComboBox<String> cbo_hinhThucThanhToan;
    private javax.swing.JComboBox<String> cbo_trangThaiThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel_HinhThucGiaoHang;
    private javax.swing.JPanel jPanel_HinhThucThanhToan;
    private javax.swing.JPanel jPanel_HoaDon;
    private javax.swing.JPanel jPanel_TrangThaiThanhToan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_hoaDon;
    private javax.swing.JTable tbl_hoaDonChiTiet;
    private javax.swing.JTextField txt_timKiemHoaDon;
    // End of variables declaration//GEN-END:variables
}
