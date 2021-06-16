
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetUserPodcastsDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public GetUserPodcastsDAO(){
        con = new ConnectionDB();
    }

    public JSONArray getPodcasts(String userID)
    {
        try
        {
            
            query = "SELECT * FROM podcasts WHERE user_id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, userID);
            
       
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