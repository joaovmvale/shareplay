package controller;

import dao.SubscribeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SubscribeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userID = (String) request.getSession().getAttribute("user-id");
        String podcastID = request.getParameter("podcastID");
        
        SubscribeDAO dao = new SubscribeDAO();
        try {
            if(!dao.getStatus(userID, podcastID).next())
                dao.subscribe(userID, podcastID);
            else
                dao.unsubscribe(userID, podcastID);
        } catch (SQLException ex) {}
       

    }
}