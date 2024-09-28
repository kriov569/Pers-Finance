package com.example;

import com.example.model.Expense;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SummaryServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        var context = config.getServletContext();
        context.log("[SummaryServlet] init");

        var salary = Integer.parseInt(context.getInitParameter("salary"));
        var rent = Integer.parseInt(config.getInitParameter("rent"));

        context.setAttribute("freeMoney", salary - rent);
        List<Expense> expenses = new ArrayList();
        expenses.add(new Expense("rent", rent));
        context.setAttribute("expenses", expenses);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();
        context.log("[SummaryServlet] doGet");

        var session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not authorized");
            return;
        }

        req.getRequestDispatcher("/details").include(req, resp);
        resp.getWriter().println("Free money " + context.getAttribute("freeMoney"));
    }
}