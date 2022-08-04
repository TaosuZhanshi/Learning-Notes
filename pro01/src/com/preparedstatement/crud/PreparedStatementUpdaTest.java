package com.preparedstatement.crud;

import com.util.JDBCUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PreparedStatementUpdaTest {

    @Test
    public void testCommonUpdate(){
        String sql = "delete from customers where id = ?";
        upadate(sql, 3);

    }

    //通用的增删改查操作
    public void upadate(String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5.关闭资源
        JDBCUtils.closeResource(connection, ps);
    }

    //修改customers表的一条记录
    @Test
    public void testUpdate(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();

            //2.预编译sql语句
            String sql = "update customers set name = ? where id = ?";
            ps = connection.prepareStatement(sql);

            //3.填充占位符
            ps.setObject(1, "莫扎特");
            ps.setObject(2, 1);

            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5.资源的关闭
            JDBCUtils.closeResource(connection, ps);
        }
    }

    @Test
    public void testInsert(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
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
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connection = " + connection);

            //预编译sql语句，返回PreparedStatement实例
            String sql = "insert into customers(name, email, birth) value (?, ?, ?)";
            ps = connection.prepareStatement(sql);

            //填充占位符
            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@gmail.com");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse("1000-01-01");
            ps.setDate(3, new java.sql.Date(date.getTime()));

            //执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //资源管理
            try {
                if(ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
