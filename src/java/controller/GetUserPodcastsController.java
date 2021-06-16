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
        //String userID = (String) request.getSession().getAttribute("userID");
        JSONArray jsonArray = u.getPodcasts("1");
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
      
    }
}