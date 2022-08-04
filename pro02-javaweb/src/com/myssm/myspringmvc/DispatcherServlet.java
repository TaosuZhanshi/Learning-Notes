package com.myssm.myspringmvc;

import com.myssm.ioc.BeanFactory;
import com.myssm.ioc.ClassPathXmlApplicationContext;
import com.myssm.utils.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    //private Map<String, Object> beanMap = new HashMap<>();
    private BeanFactory beanFactory;

    public DispatcherServlet() {
    }

    @Override
    public void init() {
        super.init();
        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj =  application.getAttribute("beanFactory");
        if(beanFactoryObj != null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else{
            throw new RuntimeException("IOC容器获取失败");
        }
//        try {
//            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
//            //创建document对象
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse(inputStream);
//
//            NodeList beanNodeList = document.getElementsByTagName("bean");
//            for (int i = 0; i < beanNodeList.getLength(); i++) {
//                Node beanNode = beanNodeList.item(i);
//                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element beanElement = (Element) beanNode;
//
//                    //得到具体的controller实例，并与其名字一起放入beanMap中
//                    //例如key = (String) customer, value = (Object) new CustomerController()
//                    String beanId = beanElement.getAttribute("id");
//                    String className = beanElement.getAttribute("class");
//                    Class controllerBeanClass = Class.forName(className);
//                    Object beanObj = controllerBeanClass.newInstance();
//                    beanMap.put(beanId, beanObj);
//
//                    //获取此Servlet的ServletContext容器，并将其传给beanObj也就是所得的实例中(通过设置的set方法)，再将其传入init方法中
////                    Method beanSetServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
////                    beanSetServletContextMethod.invoke(beanObj, this.getServletContext());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码， 已挪用到过滤器
        //req.setCharacterEncoding("UTF-8");

        //如果url是 :http://localhost:8080/pro02/hello.do
        //则获取的是 /hello.do
        String servletPath = req.getServletPath();
        //截掉后面的.do以及前面的/
        int dotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1, dotIndex);

        //获取具体的controller实例，为后续调用其方法做准备
        Object controllerBeanObj = beanFactory.getBean(servletPath);
        String operateStr = req.getParameter("operate");
        if (StringUtil.isEmpty(operateStr)) {
            operateStr = "index";
        }


        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
//            Method method = controllerBeanObj.getClass().getDeclaredMethod(operateStr, HttpServletRequest.class);
            for (Method method : methods) {
                if (operateStr.equals(method.getName())) {
                    //统一获取请求参数
                    Parameter[] parameters = method.getParameters();
                    //parameterValues用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; ++i) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = resp;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            String parameterValue = req.getParameter(parameter.getName());
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;
                            if (parameterValue != null) {
                                switch (typeName) {
                                    case "java.lang.Integer":
                                        parameterObj = Integer.parseInt(parameterValue);
                                        break;
                                    case "java.util.Date":
                                        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(parameterValue);
                                        parameterObj = birth;
                                        break;
                                }
                            }else if(parameterValue == ""){
                                parameterObj = null;
                            }
                            parameterValues[i] = parameterObj;

                        }
                    }

                    //controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = null;
                    returnObj = method.invoke(controllerBeanObj, parameterValues);
                    String methodReturnStr = (String) returnObj;

                    //视图处理
                    if (methodReturnStr.startsWith("redirect:")) {
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    } else if("error".equals(methodReturnStr)){
                        resp.sendRedirect("index");
                    }else{
                        super.processTemplate(methodReturnStr, req, resp);
                    }
                }
            }
//            }else{
//                throw new RuntimeException("operate值非法！");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherException("DispatcherServlet出错");
        }
    }
}
