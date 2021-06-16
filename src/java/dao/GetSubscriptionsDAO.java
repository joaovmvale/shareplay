package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetSubscriptionsDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public GetSubscriptionsDAO(){
        con = new ConnectionDB();
    }

    public JSONArray getInformation(String userID)
    {
        try
        {
            
            query = "SELECT podcasts.* FROM podcasts JOIN user_subscriptions"
                    + " ON user_subscriptions.user_id = ? AND podcasts.id = user_subscriptions.podcast_id";
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