package com.customer.servlets;

import com.myssm.CustomerDAO.CustomerDAOImpl;
import com.myssm.bean.Customer;
import com.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String birthStr = req.getParameter("birth");
            Date birth = null;
            birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            Customer customer = new Customer(id, name, email, birth);
            dao.update(customer);
            resp.sendRedirect("index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
