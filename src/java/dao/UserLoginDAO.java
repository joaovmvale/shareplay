
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserLoginModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserLoginDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public UserLoginDAO(){
        con = new ConnectionDB();
    }

    public JSONArray loginUser(UserLoginModel u)
    {
        try
        {
            query = "SELECT id FROM users WHERE (name = ? or email = ?) and password = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, u.getUser());
            ps.setString(2, u.getUser());
            ps.setString(3, u.getPassword());

            return convertToJSON(ps.executeQuery());

        }
        catch(SQLException ex) {}
        
        return null;

    }

    public static JSONArray convertToJSON(ResultSet rs) throws SQLException {
        JSONArray jsonArray = new JSONArray();

        while(rs.next()){

            int col = rs.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();

            for(int i = 0; i < col; i++){
                obj.put(rs.getMetaData().getColumnLabel(i+1).toLowerCase(), rs.getObject(i+1));
            }

            jsonArray.put(obj);
        }

        return jsonArray;

    }
}