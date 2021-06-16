
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class PodcastsLoadingDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public PodcastsLoadingDAO(){
        con = new ConnectionDB();
    }

    public JSONArray pullPodcasts(String search)
    {
        try
        {
            
            query = "SELECT live_podcasts.*, podcasts.name, podcasts.id FROM live_podcasts"
                    + " JOIN podcasts ON podcasts.id = live_podcasts.podcast_id WHERE"
                    + " live_podcasts.title LIKE ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, "%" + search + "%");

            
            JSONArray jsonArray1 = convertToJSON(ps.executeQuery());
            
            query = "SELECT * FROM podcasts WHERE name LIKE ? OR about LIKE ? OR category LIKE ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            
            JSONArray jsonArray2 = convertToJSON(ps.executeQuery());
            
            JSONArray finalArray = new JSONArray();
            finalArray.put(jsonArray1);
            finalArray.put(jsonArray2);

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