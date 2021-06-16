package controller;

import dao.LivePodcastsLoadingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class LivePodcastsLoadingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LivePodcastsLoadingDAO dao = new LivePodcastsLoadingDAO();
        JSONArray jsonArray = dao.pullPodcasts();
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());

    }
}