/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import hieubd.utils.DBHelper;
import hieubd.utils.MyContanst;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CND
 */
public class BookDAO  implements Serializable{

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

    public List<BookDTO> searchBook(Integer min, Integer max, Integer categoryId, String name) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String addSql = "";
                if (min != null && categoryId != null) {
                    addSql = " AND price BETWEEN ? AND ? AND categoryId = ? ";
                } else if (categoryId != null) {
                    addSql = " AND categoryId = ? ";
                } else if (min != null) {
                    addSql = " AND price BETWEEN ? AND ? ";
                }

                String sql = "SELECT bookId, quantity, statusId, categoryId, title, image, description, author, price, date "
                        + " FROM Book "
                        + " WHERE title like ? AND statusId = ? AND quantity > 0 " + addSql;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                stm.setInt(2, MyContanst.STATUS_ACTIVE);
                if (min != null && categoryId != null) {
                    stm.setInt(3, min);
                    stm.setInt(4, max);
                    stm.setInt(5, categoryId);
                } else if (categoryId != null) {
                    stm.setInt(3, categoryId);
                } else if (min != null) {
                    stm.setInt(3, min);
                    stm.setInt(4, max);
                }

                rs = stm.executeQuery();
                List<BookDTO> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new BookDTO(
                            rs.getString("bookId"),
                            rs.getInt("quantity"),
                            rs.getInt("statusId"),
                            rs.getInt("categoryId"),
                            rs.getString("title"),
                            rs.getString("image"),
                            rs.getString("description"),
                            rs.getString("author"),
                            rs.getFloat("price"),
                            rs.getDate("date")));
                }
                return list;
            }
        } finally {
            closeDB();
        }
        return null;
    }

    public int addABook(BookDTO bookObj) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "INSERT INTO Book(bookId, title, image, description, price, author, quantity, date, statusId, categoryId) "
                        + " VALUES(?,?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, bookObj.getBookId());
                stm.setString(2, bookObj.getTitle());
                stm.setString(3, bookObj.getImage());
                stm.setString(4, bookObj.getDescription());
                stm.setFloat(5, bookObj.getPrice());
                stm.setString(6, bookObj.getAuthor());
                stm.setInt(7, bookObj.getQuantity());
                stm.setDate(8, bookObj.getDate());
                stm.setInt(9, bookObj.getStatusId());
                stm.setInt(10, bookObj.getCategoryId());
                return stm.executeUpdate();
            }
        } finally {
            closeDB();
        }
        return 0;
    }

    public int UpdateBook(BookDTO bookObj) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "UPDATE Book "
                        + " SET title=?, image=?, description=?, price =?, author=?, quantity=?, date=?, statusId=?, categoryId=? "
                        + " WHERE bookId = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, bookObj.getTitle());
                stm.setString(2, bookObj.getImage());
                stm.setString(3, bookObj.getDescription());
                stm.setFloat(4, bookObj.getPrice());
                stm.setString(5, bookObj.getAuthor());
                stm.setInt(6, bookObj.getQuantity());
                stm.setDate(7, bookObj.getDate());
                stm.setInt(8, bookObj.getStatusId());
                stm.setInt(9, bookObj.getCategoryId());
                stm.setString(10, bookObj.getBookId());
                return stm.executeUpdate();
            }
        } finally {
            closeDB();
        }
        return 0;
    }

    public boolean isExistBook(String bookId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT title "
                        + " FROM Book "
                        + " WHERE bookId = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, bookId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            closeDB();
        }
        return false;
    }

    public BookDTO getABook(String bookId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT bookId, title, image, description, price, author, quantity, date, statusId, categoryId "
                        + " FROM Book "
                        + " WHERE bookId = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, bookId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return new BookDTO(bookId,
                            rs.getInt("quantity"),
                            rs.getInt("statusId"),
                            rs.getInt("categoryId"),
                            rs.getString("title"),
                            rs.getString("image"),
                            rs.getString("description"),
                            rs.getString("author"),
                            rs.getFloat("price"),
                            rs.getDate("date"));
                }
            }
        } finally {
            closeDB();
        }
        return null;
    }

    public int updateBookStatus(String bookId, int status) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "UPDATE Book "
                        + " SET statusId = ? "
                        + " WHERE bookId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, bookId);
                return stm.executeUpdate();
            }
        } finally {
            closeDB();
        }
        return 0;
    }

    public int getQuantityABookInStore(String bookId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT quantity "
                        + " FROM Book "
                        + " WHERE bookId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }
        } finally {
            closeDB();
        }
        return -1;
    }

    public Integer addCart(String userId, String discountId, Date date) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "INSERT INTO Cart(userId,discountId,createDate) VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setString(2, discountId);
                stm.setDate(3, date);
                int result = stm.executeUpdate();
                if (result > 0) {
                    return getLastCartId();
                }
            }
        } finally {
            closeDB();
        }
        return null;
    }

    public Integer getLastCartId() throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT MAX(cartId) as count FROM Cart";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } finally {
            closeDB();
        }
        return null;
    }
}
