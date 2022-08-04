package com.myssm.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/customer.do")
//@WebFilter("*.do")
public class Filter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("helloA2");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("helloB2");
    }

    @Override
    public void destroy() {

    }
}
