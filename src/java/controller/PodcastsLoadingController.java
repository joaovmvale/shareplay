package controller;

import dao.PodcastsLoadingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class PodcastsLoadingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String search = request.getParameter("search");

        PodcastsLoadingDAO dao = new PodcastsLoadingDAO();
        JSONArray jsonArray = dao.pullPodcasts(search);
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());

    }
}