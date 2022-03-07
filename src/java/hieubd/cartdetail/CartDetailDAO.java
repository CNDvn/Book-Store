/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.cartdetail;

import hieubd.book.BookDTO;
import hieubd.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author CND
 */
public class CartDetailDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeDB() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public void addBookToCart(int cartId, BookDTO bookObj, int amount) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "INSERT INTO CartDetail(cartId, bookId, amount, title, image, description, price, author, dateCreateBook, categoryId) VALUES(?,?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, cartId);
                stm.setString(2, bookObj.getBookId());
                stm.setInt(3, amount);
                stm.setString(4, bookObj.getTitle());
                stm.setString(5, bookObj.getImage());
                stm.setString(6, bookObj.getDescription());
                stm.setFloat(7, bookObj.getPrice());
                stm.setString(8, bookObj.getAuthor());
                stm.setDate(9, bookObj.getDate());
                stm.setInt(10, bookObj.getCategoryId());
                stm.executeUpdate();
            }
        } finally {
            closeDB();
        }
    }

    public List<CartDetailDTO> getListItemInCart(int cartId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT cartDetailId, cartId, bookId, amount, title, image, description, price, author, dateCreateBook, categoryId "
                        + " FROM CartDetail "
                        + " WHERE cartId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, cartId);
                rs = stm.executeQuery();
                List<CartDetailDTO> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new CartDetailDTO(rs.getInt("cartDetailId"),
                            rs.getInt("cartId"),
                            rs.getInt("amount"),
                            rs.getInt("categoryId"),
                            rs.getString("bookId"),
                            rs.getString("title"),
                            rs.getString("image"),
                            rs.getString("description"),
                            rs.getString("author"),
                            rs.getFloat("price"),
                            rs.getDate("dateCreateBook")));
                }
                return list;
            }
        } finally {
            closeDB();
        }
        return null;
    }
}
