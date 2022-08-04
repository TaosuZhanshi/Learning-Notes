package com.customer.servlets;

import com.myssm.CustomerDAO.CustomerDAOImpl;
import com.myssm.myspringmvc.ViewBaseServlet;
import com.myssm.utils.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DeleteServlet extends ViewBaseServlet {
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        if (StringUtil.isNotEmpty(idStr)) ;
        {
            int id = Integer.parseInt(idStr);
            dao.deleteById(id);
            resp.sendRedirect("index");
        }

    }
}
