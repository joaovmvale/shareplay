
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.PodcastModel;

public class CreatePodcastDAO {
    private ConnectionDB con;
    private String query;
    private PreparedStatement ps;

    public CreatePodcastDAO(){
        con = new ConnectionDB();
    }

    public void createPodcast(PodcastModel mod)
    {
        try
        {
            
            query = "INSERT INTO podcasts VALUES ('0', ?, ?, ?, ?, ?)";
            ps = con.getConnectionDB().prepareStatement(query);
            ps.setString(1, mod.getUser_id());
            ps.setString(2, mod.getName());
            ps.setString(3, mod.getAbout());
            ps.setString(4, "0");
            ps.setString(5, mod.getCategory());
            ps.executeUpdate();

        }
        catch(SQLException ex) {}

    }

}