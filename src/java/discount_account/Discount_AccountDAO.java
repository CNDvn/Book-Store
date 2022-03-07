/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discount_account;

import hieubd.utils.DBHelper;
import hieubd.utils.MyContanst;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author CND
 */
public class Discount_AccountDAO implements Serializable {

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

    public boolean isDiscountInAccount(String discountId, String userId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            String sql = "SELECT discountId "
                    + " FROM Discount_Account "
                    + " WHERE discountId = ? AND userId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, discountId);
            stm.setString(2, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            closeDB();
        }
        return false;
    }

    public void addDiscountToAccount(String discountId, String userId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            String sql = "INSERT INTO Discount_Account(discountId, userId, statusId) VALUES(?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, discountId);
            stm.setString(2, userId);
            stm.setInt(3, MyContanst.STATUS_ACTIVE);
            stm.executeUpdate();
        } finally {
            closeDB();
        }
    }
}
