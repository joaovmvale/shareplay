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

        //String userID = (String) request.getSession().getAttribute("userID");
        String podcastID = request.getParameter("podcastID");
        
        SubscribeDAO dao = new SubscribeDAO();
        try {
            if(!dao.getStatus("1", podcastID).next())
                dao.subscribe("1", podcastID);
            else
                dao.unsubscribe("1", podcastID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        

    }
}