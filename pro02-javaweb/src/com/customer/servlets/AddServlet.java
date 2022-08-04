package com.customer.servlets;

import com.myssm.CustomerDAO.CustomerDAOImpl;
import com.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Date birth = null;
        try {
            request.setCharacterEncoding("utf-8");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String birthStr = request.getParameter("birth");
            birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
            dao.insertByPara(name, email, birth);
            response.sendRedirect("index");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
