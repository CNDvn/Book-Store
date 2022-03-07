/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.cartdetail;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CND
 */
public class CartDetailDTO implements Serializable {

    private int cartDetailId, cartId, amount, categoryId;
    private String bookId, title, image, description, author;
    private float price;
    private Date dateCreateBook;

    public CartDetailDTO(int cartDetailId, int cartId, int amount, int categoryId, String bookId, String title, String image, String description, String author, float price, Date dateCreateBook) {
        this.cartDetailId = cartDetailId;
        this.cartId = cartId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.bookId = bookId;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.price = price;
        this.dateCreateBook = dateCreateBook;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDateCreateBook() {
        return dateCreateBook;
    }

    public void setDateCreateBook(Date dateCreateBook) {
        this.dateCreateBook = dateCreateBook;
    }
    
    
}
