package controller;

import dao.LiveTrendingPodcastsLoadingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class LiveTrendingPodcastsLoadingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LiveTrendingPodcastsLoadingDAO dao = new LiveTrendingPodcastsLoadingDAO();
        JSONArray jsonArray = dao.pullPodcasts();
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());

    }
}