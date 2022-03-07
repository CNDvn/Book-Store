/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bieubd.account;

import java.io.Serializable;

/**
 *
 * @author CND
 */
public class AccountDTO implements Serializable {

    private String userId, password, name;
    private int roleId;

    public AccountDTO(String userId, String password, String name, int roleId) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    
}
