package controller;

import dao.UserRegisterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserRegisterModel;

public class UserRegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email-register");
<<<<<<< HEAD
        String password = request.getParameter("password-register");
=======
        String password = request.getParameter("password-hash");
>>>>>>> login
        String name = request.getParameter("name-register");
        String date = request.getParameter("date-register");

        UserRegisterModel u = new UserRegisterModel(name, email, password, date);

<<<<<<< HEAD
        UserRegisterDAO dao = new UserRegisterDAO();
        dao.registerUser(u);

=======
        UserRegisterDAO udao = new UserRegisterDAO();
        
        response.setContentType("application/json");
        response.getWriter().write(udao.registerUser(u));
>>>>>>> login
    }
}