package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userID = (String) request.getSession().getAttribute("user-id");
        String status;
        if(userID == null)
            status = "false";
        else
            status = "true";
     
        response.setContentType("application/json");
        response.getWriter().write(status);
        
    }
}