/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GiaoDien;


/**
 *
 * @author SingPC
 */
public class loandings extends javax.swing.JFrame implements Runnable{
    public loandings() {
        initComponents();
         Thread t = new Thread(this);
        t.start();
    
    }
//        loatDaTa();
    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            try {
                Thread.sleep(30);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
      
            duyok.setText(i+ "%");
            
           loding.setValue(i);
        }
        TrangChu lc1 = new TrangChu();
                lc1.setVisible(true);
                dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loding = new javax.swing.JProgressBar();
        duyok = new javax.swing.JLabel();
        duyem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loding.setForeground(new java.awt.Color(204, 0, 0));
        getContentPane().add(loding, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 340, 10));

        duyok.setFont(new java.awt.Font("Wide Latin", 2, 24)); // NOI18N
        duyok.setForeground(new java.awt.Color(255, 255, 255));
        duyok.setText("0%");
        getContentPane().add(duyok, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        duyem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2.gif"))); // NOI18N
        getContentPane().add(duyem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 280));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(loandings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loandings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loandings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loandings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loandings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel duyem;
    public static javax.swing.JLabel duyok;
    public static javax.swing.JProgressBar loding;
    // End of variables declaration//GEN-END:variables
}
