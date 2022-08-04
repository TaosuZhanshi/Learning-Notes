package com.preparedstatement.crud;

import com.bean.Customer;
import com.bean.User;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreparedStatementQueryTest {

    @Test
    public void testLogin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入用户名:");
        String user = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        String sql = "SELECT user, password FROM user_table WHERE user = ? AND password = ?";
        User user1 = getInstance(User.class, sql, user, password);
        if(user1 != null) {
            System.out.println("登录成功");
        }else {
            System.out.println("用户名不存在或者密码错误");
        }
    }

    @Test
    public void testGetForList(){
        String sql = "select id,name, email,birth from customers where id <= ?";
        List<Customer> customers = getForList(Customer.class, sql, 3);
        customers.forEach(System.out::println);
    }

    /**
     * 获取多个对象
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过结果集的元数据来获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            List<T> list = new ArrayList<>();
            while (resultSet.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < columnCount; ++i){
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列名,这里更推荐使用列的别名来以针对类的字段和列名不一
                    //String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，幅值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }

    @Test
    public void testGetInstance(){
        String sql = "select id,name, email,birth from customers where id = ?";
        Customer customer = getInstance(Customer.class, sql, 1);
        System.out.println(customer);
    }

    /**
     * 获取一个对象
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T>T getInstance(Class<T> clazz, String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过结果集的元数据来获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            if(resultSet.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < columnCount; ++i){
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列名,这里更推荐使用列的别名来以针对类的字段和列名不一
                    //String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，幅值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }
}
