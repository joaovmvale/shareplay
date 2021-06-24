package controller;

import dao.GetPodcastPageDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class GetPodcastPageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userID = (String) request.getSession().getAttribute("user-id");
        
        String podcastID = request.getParameter("podcastID");
        GetPodcastPageDAO dao = new GetPodcastPageDAO();
        JSONArray jsonArray = dao.getInformation(userID, podcastID);
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());

    }
}