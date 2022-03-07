/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discount_account;

import java.io.Serializable;

/**
 *
 * @author CND
 */
public class Discount_AccountDTO implements Serializable {

    private String discountId, userId;
    private int statusId;

    public Discount_AccountDTO(String discountId, String userId, int statusId) {
        this.discountId = discountId;
        this.userId = userId;
        this.statusId = statusId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    
}
