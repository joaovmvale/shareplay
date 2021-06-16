<<<<<<< HEAD

package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.UserRegisterModel;
//import org.json.JSONArray;
//import org.json.JSONObject;

public class UserRegisterDAO {
    private ConnectionDB con;
    private String query;
=======
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserRegisterModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserRegisterDAO {
    private ConnectionDB con;
    private String queryInsert;
>>>>>>> login
    private PreparedStatement ps;

    public UserRegisterDAO(){
        con = new ConnectionDB();
    }

<<<<<<< HEAD
    public void registerUser(UserRegisterModel u)
    {
        try
        {
            query = "INSERT INTO users VALUES(null,?,?,?,?)";
            ps = con.getConnectionDB().prepareStatement(query);
=======
    public String registerUser(UserRegisterModel u)
    {
        try
        {
            queryInsert = "INSERT INTO users (id, name, email, password, date) SELECT null, ?, ?, ?, ? WHERE NOT EXISTS (SELECT email FROM users WHERE email = ?) LIMIT 1";
            ps = con.getConnectionDB().prepareStatement(queryInsert);
>>>>>>> login
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getDate());
<<<<<<< HEAD

            ps.executeUpdate();

        }
        catch(SQLException ex) {}

    }

//    public static JSONArray convertToJSON(ResultSet rs) throws SQLException {
//        JSONArray jsonArray = new JSONArray();
//
//        while(rs.next()){
//
//            int col = rs.getMetaData().getColumnCount();
//            JSONObject obj = new JSONObject();
//
//            for(int i = 0; i < col; i++){
//                obj.put(rs.getMetaData().getColumnLabel(i+1).toLowerCase(), rs.getObject(i+1));
//            }
//
//            jsonArray.put(obj);
//        }
//
//        return jsonArray;
//
//    }
=======
            ps.setString(5, u.getEmail());
            int response = ps.executeUpdate();
            
            return String.valueOf(response);

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
>>>>>>> login
}