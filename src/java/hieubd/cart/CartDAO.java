/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.cart;

import hieubd.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author CND
 */
public class CartDAO implements Serializable {

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

    public List<CartDTO> getListCart(String userId, String bookTitle, Date date) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String addSql = "";
                if (date != null) {
                    addSql = " AND createDate = ? ";
                }

                String sql = "SELECT DISTINCT c.cartId, userId, discountId, createDate "
                        + " FROM Cart c, CartDetail cd"
                        + " WHERE c.cartId = cd.cartId AND c.userId = ? AND cd.title like ? " + addSql 
                        + " ORDER BY createDate DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setString(2, "%"+bookTitle+"%");
                if (date != null) {
                    stm.setDate(3, date);
                }
                rs = stm.executeQuery();
                List<CartDTO> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new CartDTO(rs.getInt("cartId"), rs.getString("userId"), rs.getString("discountId"), rs.getDate("createDate")));
                }
                return list;
            }
        } finally {
            closeDB();
        }
        return null;
    }

}
