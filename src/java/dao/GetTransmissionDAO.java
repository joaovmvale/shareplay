
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetTransmissionDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public GetTransmissionDAO(){
        con = new ConnectionDB();
    }

    public JSONArray getInformation(String transmissionID)
    {
        try
        {
            
            query = "SELECT * FROM live_podcasts WHERE id = ?";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, transmissionID);
            
            JSONArray jsonArray1 = convertToJSON(ps.executeQuery());
            
            query = "SELECT * FROM podcasts JOIN live_podcasts ON live_podcasts.id = ? AND podcast_id = podcasts.id";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, transmissionID);
            
            JSONArray jsonArray2 = convertToJSON(ps.executeQuery());
            
            JSONArray finalArray = new JSONArray();
            
            finalArray.putAll(jsonArray1);
            finalArray.putAll(jsonArray2);
            

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