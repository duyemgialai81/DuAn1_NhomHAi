/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author SingPC
 */
public class RoleEntity {
    private String maRole;
    private String tenRole;

    public RoleEntity() {
    }

    public RoleEntity(String maRole, String tenRole) {
        this.maRole = maRole;
        this.tenRole = tenRole;
    }

    public String getMaRole() {
        return maRole;
    }

    public void setMaRole(String maRole) {
        this.maRole = maRole;
    }

    public String getTenRole() {
        return tenRole;
    }

    public void setTenRole(String tenRole) {
        this.tenRole = tenRole;
    }
    
    
}
