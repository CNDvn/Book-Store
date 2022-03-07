/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.discount;

import hieubd.utils.DBHelper;
import hieubd.utils.MyContanst;
import java.io.Serializable;
import java.sql.Connection;
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
public class DiscountDAO implements Serializable {

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

    public int getValueDiscount(String userId, String discountId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT d.value "
                        + " FROM Discount_Account a, Discount d "
                        + " WHERE a.discountId = ? AND a.userId = ? AND a.discountId = d.discountId AND statusId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountId);
                stm.setString(2, userId);
                stm.setInt(3, MyContanst.STATUS_ACTIVE);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("value");
                }
            }
        } finally {
            closeDB();
        }
        return 0;
    }

    public List<DiscountDTO> getAllDiscount() throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT discountId, value, description "
                        + " FROM Discount ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<DiscountDTO> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new DiscountDTO(rs.getString("discountId"), rs.getInt("value"), rs.getString("description")));
                }
                return list;
            }
        } finally {
            closeDB();
        }
        return null;
    }

    public int addNewDiscount(DiscountDTO discountObj) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "INSERT INTO Discount(discountId, value, description) VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountObj.getDiscountId());
                stm.setInt(2, discountObj.getValue());
                stm.setString(3, discountObj.getDescription());
                return stm.executeUpdate();
            }
        } finally {
            closeDB();
        }
        return 0;
    }
    
    public boolean isExistedDiscount(String discountId) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT discountId FROM Discount WHERE discountId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountId);
                rs = stm.executeQuery();
                if(rs.next())
                    return true;
            }
        } finally {
            closeDB();
        }
        return false;
    }
}
