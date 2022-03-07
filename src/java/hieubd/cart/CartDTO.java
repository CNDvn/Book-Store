/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.cart;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CND
 */
public class CartDTO implements Serializable {

    private int cartId;
    private String userId, discountId;
    private Date createDate;

    public CartDTO(int cartId, String userId, String discountId, Date createDate) {
        this.cartId = cartId;
        this.userId = userId;
        this.discountId = discountId;
        this.createDate = createDate;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
