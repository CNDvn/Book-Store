/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bieubd.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import hieubd.utils.DBHelper;
import hieubd.utils.MyContanst;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CND
 */
public class AccountDAO {

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

    public AccountDTO checkLogin(String userId, String password) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT userId, password, name, roleId "
                        + " FROM Account "
                        + " WHERE userId = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return new AccountDTO(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getInt("roleId"));
                }
            }
        } finally {
            closeDB();
        }
        return null;
    }

    public List<AccountDTO> getAllCustomer() throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String sql = "SELECT userId, name, roleId "
                        + " FROM Account "
                        + " WHERE roleId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, MyContanst.ROLE_CUSTOMER);
                rs = stm.executeQuery();
                List<AccountDTO> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new AccountDTO(rs.getString("userId"), null, rs.getString("name"), rs.getInt("roleId")));
                }
                return list;
            }
        } finally {
            closeDB();
        }
        return null;
    }
}
