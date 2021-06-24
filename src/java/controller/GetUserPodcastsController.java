package controller;

import dao.GetUserPodcastsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class GetUserPodcastsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GetUserPodcastsDAO u = new GetUserPodcastsDAO();
        String userID = (String) request.getSession().getAttribute("user-id");
        JSONArray jsonArray = u.getPodcasts(userID);
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
      
    }
}