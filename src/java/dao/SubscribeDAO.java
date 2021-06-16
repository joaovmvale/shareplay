
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class SubscribeDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public SubscribeDAO(){
        con = new ConnectionDB();
    }
    
    public ResultSet getStatus(String userID, String podcastID){
        
        try
        {
           
            query = "SELECT * from user_subscriptions WHERE user_id = ? AND podcast_id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, podcastID);
            
            return ps.executeQuery();

        }
        catch(SQLException ex) {}
        
        return null;
        
    }

    public void subscribe(String userID, String podcastID)
    {
        try
        {
            
            query = "INSERT INTO user_subscriptions VALUES ('0', ?, ?)";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, podcastID);
            
            ps.executeUpdate();

        }
        catch(SQLException ex) {}
        

    }
    
    public void unsubscribe(String userID, String podcastID)
    {
        try
        {
            
            query = "DELETE FROM user_subscriptions WHERE user_id = ? AND podcast_id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, podcastID);
            
            ps.executeUpdate();

        }
        catch(SQLException ex) {}
        

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