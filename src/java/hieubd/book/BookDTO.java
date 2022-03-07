/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.book;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CND
 */
public class BookDTO implements Serializable {

    private int quantity, statusId, categoryId;
    private String bookId, title, image, description, author;
    private float price;
    private Date date;

    public BookDTO(String bookId, int quantity, int statusId, int categoryId, String title, String image, String description, String author, float price, Date date) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.statusId = statusId;
        this.categoryId = categoryId;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.price = price;
        this.date = date;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
