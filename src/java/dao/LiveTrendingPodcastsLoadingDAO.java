
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class LiveTrendingPodcastsLoadingDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public LiveTrendingPodcastsLoadingDAO(){
        con = new ConnectionDB();
    }

    public JSONArray pullPodcasts()
    {
        try
        {
            query = "SELECT live_podcasts.*, podcasts.name FROM live_podcasts, podcasts"
                    + " WHERE live_podcasts.podcast_id = podcasts.id GROUP BY viewers LIMIT 15";
            ps = con.getConnectionDB().prepareStatement(query);

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