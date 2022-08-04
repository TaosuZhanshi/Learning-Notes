package com.preparedstatement.crud;

import com.bean.Customer;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

public class CustomerForQuery {

    @Test
    public void testQueryForCustomers(){
        String sql = "select id, name, birth, email from customers where id = ?";
        Customer customer = queryForCustomers(sql, 2);
        System.out.println(customer);
    }

    public Customer queryForCustomers(String sql, Object ...args){
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
                Customer cust = new Customer();
                for(int i = 0; i < columnCount; ++i){
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列名,这里更推荐使用列的别名来以针对类的字段和列名不一
                    //String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，幅值为columValue：通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust, columnValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }

    @Test
    public void testQuery1(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth from customers where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, 1);

            //执行，并返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            if(resultSet.next()){
                //获取各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                //将数据封装为一个对象
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
    }
}
