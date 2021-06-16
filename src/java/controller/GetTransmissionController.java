package controller;

import dao.GetTransmissionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class GetTransmissionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String transmissionID = request.getParameter("transmissionID");

        GetTransmissionDAO dao = new GetTransmissionDAO();
        JSONArray jsonArray = dao.getInformation(transmissionID);
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());

    }
}