/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GiaoDien;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author SingPC
 */
public class TrangChu extends javax.swing.JFrame {
    public TrangChu() {
        initComponents();
        
             setTitle("Phầm Mềm Quản Lý Và Bán Hàng");
         ChuyenChucNang ccn = new ChuyenChucNang(manhinh);
         ccn.setView(text2, txt1);
         ArrayList<ChucNang> ls  = new ArrayList<>();
//         ls.add(new ChucNang("ChucNangBanHang", tblbanhang1,tblbanhang ));
         ls.add(new ChucNang("ChucNangSanPham", tblsanpham, tblsanpham1));
         ls.add(new ChucNang("ChucNangHoaDon", tblhoadon, tbhhoadon1));
         ls.add(new ChucNang("FomtestBanHnag", text2, txt1));
         ls.add(new ChucNang("ChucNangThongKe", THONGKE, NUTTHONGKE));
         ls.add(new ChucNang("ChucNangVouvher", txtvouche, txtvoucher));
         ls.add(new ChucNang("ChucNangKhachHang", khachhang, khachhang1));
          ls.add(new ChucNang("ChucNangNhanVien", nhanvien, nhanvien1));
         ccn.DuyEm(ls);
         hirnThiDuLieu();
    }
  public void hirnThiDuLieu(){
//        txtTenNhanvien.setText(luuThongTinDangNhap.getTenNhanVien());
        txtTenNhanvien.setText(String.valueOf(LuuThongTinDangNhap.getInNhanVien()));
    }
    
    /**
         * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        hinhMenu = new javax.swing.JPanel();
        txtTenNhanvien = new javax.swing.JLabel();
        chucnangmenu = new javax.swing.JPanel();
        tblsanpham = new javax.swing.JPanel();
        tblsanpham1 = new javax.swing.JButton();
        tblhoadon = new javax.swing.JPanel();
        tbhhoadon1 = new javax.swing.JButton();
        text2 = new javax.swing.JPanel();
        txt1 = new javax.swing.JButton();
        THONGKE = new javax.swing.JPanel();
        NUTTHONGKE = new javax.swing.JButton();
        txtvouche = new javax.swing.JPanel();
        txtvoucher = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        nhanvien = new javax.swing.JPanel();
        nhanvien1 = new javax.swing.JButton();
        khachhang = new javax.swing.JPanel();
        khachhang1 = new javax.swing.JButton();
        txtdnagxuat1 = new javax.swing.JPanel();
        txtdanngxuat = new javax.swing.JLabel();
        manhinh = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        hinhMenu.setBackground(new java.awt.Color(255, 255, 255));
        hinhMenu.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtTenNhanvien.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtTenNhanvienAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout hinhMenuLayout = new javax.swing.GroupLayout(hinhMenu);
        hinhMenu.setLayout(hinhMenuLayout);
        hinhMenuLayout.setHorizontalGroup(
            hinhMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hinhMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTenNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        hinhMenuLayout.setVerticalGroup(
            hinhMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hinhMenuLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(txtTenNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        chucnangmenu.setBackground(new java.awt.Color(255, 255, 255));
        chucnangmenu.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblsanpham.setBackground(new java.awt.Color(255, 255, 255));

        tblsanpham1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        tblsanpham1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/SanPham.png"))); // NOI18N
        tblsanpham1.setText("SẢN PHẨM");
        tblsanpham1.setPreferredSize(new java.awt.Dimension(90, 24));
        //jButton1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tblsanpham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanpham1MouseClicked(evt);
            }
        });
        tblsanpham1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblsanpham1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tblsanphamLayout = new javax.swing.GroupLayout(tblsanpham);
        tblsanpham.setLayout(tblsanphamLayout);
        tblsanphamLayout.setHorizontalGroup(
            tblsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblsanphamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tblsanpham1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tblsanphamLayout.setVerticalGroup(
            tblsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblsanphamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tblsanpham1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tblhoadon.setBackground(new java.awt.Color(255, 255, 255));

        tbhhoadon1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        tbhhoadon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/HoaDon.png"))); // NOI18N
        tbhhoadon1.setText("HÓA ĐƠN");

        javax.swing.GroupLayout tblhoadonLayout = new javax.swing.GroupLayout(tblhoadon);
        tblhoadon.setLayout(tblhoadonLayout);
        tblhoadonLayout.setHorizontalGroup(
            tblhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblhoadonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbhhoadon1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tblhoadonLayout.setVerticalGroup(
            tblhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tblhoadonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tbhhoadon1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        text2.setBackground(new java.awt.Color(255, 255, 255));

        txt1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        txt1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/seller.png"))); // NOI18N
        txt1.setText("BÁN HÀNG");

        javax.swing.GroupLayout text2Layout = new javax.swing.GroupLayout(text2);
        text2.setLayout(text2Layout);
        text2Layout.setHorizontalGroup(
            text2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, text2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        text2Layout.setVerticalGroup(
            text2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(text2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
        );

        THONGKE.setBackground(new java.awt.Color(255, 255, 255));

        NUTTHONGKE.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        NUTTHONGKE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ThongKe.png"))); // NOI18N
        NUTTHONGKE.setText("THỐNG KÊ");

        javax.swing.GroupLayout THONGKELayout = new javax.swing.GroupLayout(THONGKE);
        THONGKE.setLayout(THONGKELayout);
        THONGKELayout.setHorizontalGroup(
            THONGKELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, THONGKELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NUTTHONGKE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        THONGKELayout.setVerticalGroup(
            THONGKELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, THONGKELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NUTTHONGKE, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtvouche.setBackground(new java.awt.Color(255, 255, 255));

        txtvoucher.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        txtvoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/GiamGia.png"))); // NOI18N
        txtvoucher.setText("VOUCHER");
        txtvoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtvoucheLayout = new javax.swing.GroupLayout(txtvouche);
        txtvouche.setLayout(txtvoucheLayout);
        txtvoucheLayout.setHorizontalGroup(
            txtvoucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtvoucheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtvoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        txtvoucheLayout.setVerticalGroup(
            txtvoucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtvoucheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtvoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/DoiHang.png"))); // NOI18N
        jButton1.setText("ĐỔI HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        nhanvien.setBackground(new java.awt.Color(255, 255, 255));

        nhanvien1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        nhanvien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/NhanVien.png"))); // NOI18N
        nhanvien1.setText("NHÂN VIÊN");

        javax.swing.GroupLayout nhanvienLayout = new javax.swing.GroupLayout(nhanvien);
        nhanvien.setLayout(nhanvienLayout);
        nhanvienLayout.setHorizontalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nhanvien1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        nhanvienLayout.setVerticalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nhanvien1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        khachhang.setBackground(new java.awt.Color(255, 255, 255));

        khachhang1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        khachhang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/KhachHang.png"))); // NOI18N
        khachhang1.setText("KHÁC HÀNG");

        javax.swing.GroupLayout khachhangLayout = new javax.swing.GroupLayout(khachhang);
        khachhang.setLayout(khachhangLayout);
        khachhangLayout.setHorizontalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(khachhang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        khachhangLayout.setVerticalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(khachhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtdanngxuat.setText("Đăng Xuất");
        txtdanngxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdanngxuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout txtdnagxuat1Layout = new javax.swing.GroupLayout(txtdnagxuat1);
        txtdnagxuat1.setLayout(txtdnagxuat1Layout);
        txtdnagxuat1Layout.setHorizontalGroup(
            txtdnagxuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtdnagxuat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtdanngxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );
        txtdnagxuat1Layout.setVerticalGroup(
            txtdnagxuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtdnagxuat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtdanngxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout chucnangmenuLayout = new javax.swing.GroupLayout(chucnangmenu);
        chucnangmenu.setLayout(chucnangmenuLayout);
        chucnangmenuLayout.setHorizontalGroup(
            chucnangmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chucnangmenuLayout.createSequentialGroup()
                .addGroup(chucnangmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(chucnangmenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(chucnangmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtvouche, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tblsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tblhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(THONGKE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(khachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(chucnangmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtdnagxuat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chucnangmenuLayout.setVerticalGroup(
            chucnangmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chucnangmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtvouche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(THONGKE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdnagxuat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chucnangmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hinhMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chucnangmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        manhinh.setBackground(new java.awt.Color(255, 255, 255));
        manhinh.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout manhinhLayout = new javax.swing.GroupLayout(manhinh);
        manhinh.setLayout(manhinhLayout);
        manhinhLayout.setHorizontalGroup(
            manhinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1112, Short.MAX_VALUE)
        );
        manhinhLayout.setVerticalGroup(
            manhinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 721, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manhinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(manhinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenNhanvienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtTenNhanvienAncestorAdded
        // TODO add your handling code here:
        //hienThiDuLieuLen(0);

    }//GEN-LAST:event_txtTenNhanvienAncestorAdded

    private void tblsanpham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanpham1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsanpham1MouseClicked

    private void tblsanpham1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblsanpham1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsanpham1ActionPerformed

    private void txtvoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvoucherActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtvoucherActionPerformed

    private void txtdanngxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdanngxuatMouseClicked
        // TODO add your handling code here:
        DangNhapChinh duy = new DangNhapChinh();
        duy.setVisible(true);
//        JOptionPane.showConfirmDialog( null,  YES_NO_OPTION);
        dispose();
      
    }//GEN-LAST:event_txtdanngxuatMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }   
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NUTTHONGKE;
    private javax.swing.JPanel THONGKE;
    private javax.swing.JPanel chucnangmenu;
    private javax.swing.JPanel hinhMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel khachhang;
    private javax.swing.JButton khachhang1;
    private javax.swing.JPanel manhinh;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel nhanvien;
    private javax.swing.JButton nhanvien1;
    private javax.swing.JButton tbhhoadon1;
    private javax.swing.JPanel tblhoadon;
    private javax.swing.JPanel tblsanpham;
    private javax.swing.JButton tblsanpham1;
    private javax.swing.JPanel text2;
    private javax.swing.JButton txt1;
    private javax.swing.JLabel txtTenNhanvien;
    private javax.swing.JLabel txtdanngxuat;
    private javax.swing.JPanel txtdnagxuat1;
    private javax.swing.JPanel txtvouche;
    private javax.swing.JButton txtvoucher;
    // End of variables declaration//GEN-END:variables
}
