/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.book;

import java.io.Serializable;

/**
 *
 * @author CND
 */
public class BookErr implements Serializable{
    String bookIdErr, titleErr, imageErr, authorErr;

    public BookErr() {
    }

    public String getBookIdErr() {
        return bookIdErr;
    }

    public void setBookIdErr(String bookIdErr) {
        this.bookIdErr = bookIdErr;
    }

    public String getTitleErr() {
        return titleErr;
    }

    public void setTitleErr(String titleErr) {
        this.titleErr = titleErr;
    }

    public String getImageErr() {
        return imageErr;
    }

    public void setImageErr(String imageErr) {
        this.imageErr = imageErr;
    }

    public String getAuthorErr() {
        return authorErr;
    }

    public void setAuthorErr(String authorErr) {
        this.authorErr = authorErr;
    }
    
}
