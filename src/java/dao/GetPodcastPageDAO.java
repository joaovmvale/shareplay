
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetPodcastPageDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public GetPodcastPageDAO(){
        con = new ConnectionDB();
    }

    public JSONArray getInformation(String userID, String podcastID)
    {
        try
        {
            
            query = "SELECT * FROM podcasts WHERE id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, podcastID);
            
            JSONArray array1 = convertToJSON(ps.executeQuery());
            
            query = "SELECT * FROM recorded_podcasts WHERE podcast_id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, podcastID);
            
            JSONArray array2 =  convertToJSON(ps.executeQuery());
            
            query = "SELECT * FROM user_subscriptions WHERE user_id = ? AND podcast_id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, podcastID);
            
           JSONArray array3 =  convertToJSON(ps.executeQuery());
            
            query = "SELECT * FROM live_podcasts WHERE podcast_id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, podcastID);
            
            JSONArray array4 = convertToJSON(ps.executeQuery());
            
            
            JSONArray finalArray = new JSONArray(); 
            finalArray.putAll(array1);
            finalArray.put(array2);
            finalArray.put(array3);
            finalArray.putAll(array4);
       
            return finalArray;

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