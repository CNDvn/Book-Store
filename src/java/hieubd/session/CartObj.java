/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.session;

import hieubd.book.BookDAO;
import hieubd.book.BookDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author CND
 */
public class CartObj implements Serializable {

    private String userId;
    private String discountId;
    private Map<BookDTO, Integer> items;

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

    public Map<BookDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String itemId) throws NamingException, SQLException {
        if (items == null) {
            items = new HashMap<>();
        }
        Integer quantity = 1;
        for (BookDTO bookDTO : items.keySet()) {
            if (bookDTO.getBookId().equals(itemId)) {
                quantity = items.get(bookDTO) + 1;
                items.put(bookDTO, quantity);
                return;
            }
        }
        BookDTO bookObj = new BookDAO().getABook(itemId);
        items.put(bookObj, quantity);
    }

    public void removeItemFromCart(String itemId) {
        for (Map.Entry<BookDTO, Integer> item : items.entrySet()) {
            if (item.getKey().getBookId().equals(itemId)) {
                items.remove(item.getKey());
            }
        }
    }

    public void reduceQuantityItem(String bookId) {
        for (Map.Entry<BookDTO, Integer> item : items.entrySet()) {
            if (item.getKey().getBookId().equals(bookId)) {
                int quantity = item.getValue() - 1;
                if (quantity > 0) {
                    item.setValue(quantity);
                } else {
                    items.remove(item.getKey());
                }
            }
        }
    }

    public void increaseQuantityItem(String bookId) {
        for (Map.Entry<BookDTO, Integer> item : items.entrySet()) {
            if (item.getKey().getBookId().equals(bookId)) {
                item.setValue(item.getValue() + 1);
            }
        }
    }

}
