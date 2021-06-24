package controller;

import dao.CreatePodcastDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PodcastModel;

public class CreatePodcastController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PodcastModel mod = new PodcastModel();
        String userID = (String) request.getSession().getAttribute("user-id");
        mod.setUser_id(userID);
        mod.setName(request.getParameter("name"));
        mod.setAbout(request.getParameter("about"));
        mod.setCategory(request.getParameter("category"));

        CreatePodcastDAO dao = new CreatePodcastDAO();
        dao.createPodcast(mod);
        
    }
}