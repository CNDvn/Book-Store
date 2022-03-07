/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.discount;

import java.io.Serializable;

/**
 *
 * @author CND
 */
public class DiscountDTO  implements Serializable{

    private int value;
    private String discountId, description;

    public DiscountDTO(String discountId, int value, String description) {
        this.discountId = discountId;
        this.value = value;
        this.description = description;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
