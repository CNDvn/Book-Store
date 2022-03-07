/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import hieubd.utils.DBHelper;

/**
 *
 * @author CND
 */
public class CategoryDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    
    public void closeDB()throws SQLException{
        if(rs != null) rs.close();
        if(stm != null) stm.close();
        if(con != null) con.close();
    }
    
    public List<CategoryDTO> getAll()throws NamingException, SQLException{
        con = DBHelper.getConnection();
        try{
            if(con != null){
                String sql = "SELECT categoryId, name "
                        + " FROM Category ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                List<CategoryDTO> list = new ArrayList<>();
                while(rs.next()){
                    list.add(new CategoryDTO(rs.getInt("categoryId"), rs.getString("name")));
                }
                return list;
            }
        }finally{
            closeDB();
        }
        return null;
    }
}
