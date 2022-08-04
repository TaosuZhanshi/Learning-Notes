package com.customer.controllers;

import com.customer.biz.impl.CustomerServiceImpl;
import com.myssm.bean.Customer;
import com.myssm.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


public class CustomerController {

    private CustomerServiceImpl customerService;

    //当CustomerController不再为Servlet组件时，他不再调用ViewBaseServlet中的init()方法
    //private CustomerDAO dao = new CustomerDAOImpl();

//    private ServletContext servletContext;
//
//    public void setServletContext(ServletContext servletContext) throws ServletException {
//        this.servletContext = servletContext;
//        super.init();
//    }
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//
////        switch (operateStr) {
////            case "index":
////                index(req, resp);
////                break;
////            case "add":
////                add(req, resp);
////                break;
////            case "delete":
////                delete(req, resp);
////                break;
////            case "edit":
////                edit(req, resp);
////                break;
////            case "update":
////                update(req, re sp);
////                break;
////            default:
////                throw new RuntimeException("operate值非法!");
////        }
//    }

    private String index(String operate1, String keyword, Integer pageNum, HttpServletRequest request) {
//        int pageNum = 1;
        if(pageNum == null){
            pageNum = 1;
        }
        int pageSize = 5;
        HttpSession session = request.getSession();
//        String operateStr = request.getParameter("operate1");
        if (StringUtil.isNotEmpty(operate1) && "search".equals(operate1)) {
            //说明是通过查询过来的请求
//            pageNum = 1;
//            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            //说明不是通过查询过来的请求
//            String pageNumberStr = request.getParameter("pageNum");
//            if (StringUtil.isNotEmpty(pageNumberStr)) {
//                pageNum = Integer.parseInt(pageNumberStr);
//            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }
        session.setAttribute("pageNum", pageNum);
        List<Customer> customerList = customerService.getCustomerList(keyword, pageNum, pageSize);
        int pageCount = customerService.getPageCount(keyword, pageSize);
        session.setAttribute("pageCount", pageCount);
        session.setAttribute("customerList", customerList);
        //super.processTemplate("index", request, response);
        return "index";
    }

    private String add(String name, String email, Date birth){
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String birthStr = request.getParameter("birth");
//        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
        customerService.addCustomer(name, email, birth);
        //response.sendRedirect("customer.do");
        return "redirect:customer.do";
    }

    private String delete(Integer id) {
//        String idStr = req.getParameter("id");
//        if (StringUtil.isNotEmpty(idStr)) ;
//        {
//            int id = Integer.parseInt(idStr);
//            dao.deleteById(id);
//            //resp.sendRedirect("customer.do");
//            return "redirect:customer.do";
//        }
        if(id != null){
            customerService.deleteCustomer(id);
            return "redirect:customer.do";
        }
        return "error";
    }

    private String edit(Integer id, HttpServletRequest request) {
//        String cidStr = req.getParameter("cid");
//        if (StringUtil.isNotEmpty(cidStr)) {
//            int cid = Integer.parseInt(cidStr);
//            Customer customer = dao.getCustomerById(cid);
//            req.setAttribute("customer", customer);
//            //super.processTemplate("edit", req, resp);
//            return "edit";
//        }
//        return null;
        if(id != null){
            Customer customer = customerService.getCustomerById(id);
            request.setAttribute("customer", customer);
            return "edit";
        }
        return "error";
    }

    private String update(Integer id, String name, String email, Date birth){
//        String name = req.getParameter("name");
//        String email = req.getParameter("email");
//        String birthStr = req.getParameter("birth");
//        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
//        String idStr = req.getParameter("id");
//        int id = Integer.parseInt(idStr);
        Customer customer = new Customer(id, name, email, birth);
        customerService.update(customer);

        //资源跳转
        //resp.sendRedirect("customer.do");
        return "redirect:customer.do";
    }
}
