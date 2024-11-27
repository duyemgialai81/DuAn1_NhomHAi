/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author SingPC
 */
public class ChucNang {
    private String kind;
    private JPanel jpn;
    private JLabel lpb;

    public ChucNang() {
    }

    public ChucNang(String kind, JPanel jpn, JLabel lpb) {
        this.kind = kind;
        this.jpn = jpn;
        this.lpb = lpb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getLpb() {
        return lpb;
    }

    public void setLpb(JLabel lpb) {
        this.lpb = lpb;
    }
    
}
