package com.customer.servlets;

import com.myssm.CustomerDAO.CustomerDAOImpl;
import com.myssm.bean.Customer;
import com.myssm.myspringmvc.ViewBaseServlet;
import com.myssm.utils.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cidStr = req.getParameter("cid");
        if (StringUtil.isNotEmpty(cidStr)) {
            int cid = Integer.parseInt(cidStr);
            Customer customer = dao.getCustomerById(cid);
            req.setAttribute("customer", customer);
            super.processTemplate("edit", req, resp);
        }
    }
}
