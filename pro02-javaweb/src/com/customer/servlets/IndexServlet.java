package com.customer.servlets;

import com.myssm.CustomerDAO.CustomerDAO;
import com.myssm.CustomerDAO.CustomerDAOImpl;
import com.myssm.bean.Customer;
import com.myssm.myspringmvc.ViewBaseServlet;
import com.myssm.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    private CustomerDAO dao = new CustomerDAOImpl();
    boolean flag = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNum = 1, pageSize = 5;
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        String operateStr = request.getParameter("operate");
        String keyword = null;
        if (StringUtil.isNotEmpty(operateStr) && "search".equals(operateStr)) {
            //说明是通过查询过来的请求
            pageNum = 1;
            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            //说明不是通过查询过来的请求
            String pageNumberStr = request.getParameter("pageNum");
            if (StringUtil.isNotEmpty(pageNumberStr)) {
                pageNum = Integer.parseInt(pageNumberStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        session.setAttribute("pageNum", pageNum);

        request.setCharacterEncoding("utf-8");

        List<Customer> customerList = dao.getCustomerList(keyword, pageNum, pageSize);
        Long count = dao.getCount(keyword);
        int pageCount = (int) (count + pageSize - 1) / pageSize;
        session.setAttribute("pageCount", pageCount);
        session.setAttribute("customerList", customerList);
        super.processTemplate("index", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
