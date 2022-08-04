package com.connection;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    @Test
    public void testConnection1() throws SQLException{
        Driver driver = new com.mysql.cj.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/test01_library?useUnicode=true&characterEncoding=utf-8";
        //将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "liruifeng110");
        Connection conn = driver.connect(url, info);
        System.out.println("conn = " + conn);
    }

    @Test
    public void testConnection2() throws Exception{
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        String url = "jdbc:mysql://localhost:3306/test01_library?useUnicode=true&characterEncoding=utf-8";
        //将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "liruifeng110");

        Connection conn = driver.connect(url, info);
        System.out.println("conn = " + conn);
    }

    @Test
    public void testConnection3() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        String url = "jdbc:mysql://localhost:3306/test01_library?useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "liruifeng110";

        //注册驱动
        //DriverManager.registerDriver(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("conn = " + conn);
    }

    //方式5
    @Test
    public void getConnection5() throws Exception{

        FileInputStream fis = new FileInputStream("D:\\JavaProject\\learningnotes\\src\\jdbc.properties");
        BufferedInputStream stream = new BufferedInputStream(fis);

        Properties properties = new Properties();
        properties.load(stream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("connection = " + connection);


    }

}
