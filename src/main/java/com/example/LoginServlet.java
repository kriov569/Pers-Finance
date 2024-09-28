package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final String passwd = "123456";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");

        if (passwd.equals(password)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(10);
            resp.sendRedirect("/summary");
        } else {
            resp.getWriter().println("Wrong password");
        }
    }
}
