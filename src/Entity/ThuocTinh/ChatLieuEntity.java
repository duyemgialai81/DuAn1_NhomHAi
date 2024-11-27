/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity.ThuocTinh;

/**
 *
 * @author SingPC
 */
public class ChatLieuEntity {
    private int idChatLieu;
    private String maChatLieu;
    private String chatLieu;
    private String moTa;

    public ChatLieuEntity() {
    }

    public ChatLieuEntity(int idChatLieu, String maChatLieu, String chatLieu, String moTa) {
        this.idChatLieu = idChatLieu;
        this.maChatLieu = maChatLieu;
        this.chatLieu = chatLieu;
        this.moTa = moTa;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    

    
    
}
