package controller;

import dao.GetSubscriptionsDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class GetSubscriptionsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //String userID = (String) request.getSession().getAttribute("userID");

        GetSubscriptionsDAO dao = new GetSubscriptionsDAO();
        JSONArray jsonArray = dao.getInformation("1");
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());

    }
}