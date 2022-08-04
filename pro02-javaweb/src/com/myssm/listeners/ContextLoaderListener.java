package com.myssm.listeners;

import com.myssm.ioc.BeanFactory;
import com.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

//监视上下文启动，在上下文启动时创建IOC容器

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
