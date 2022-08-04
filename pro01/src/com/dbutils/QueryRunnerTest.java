package com.dbutils;

import com.bean.Customer;
import com.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryRunnerTest {

    /**
     * 测试插入
     */
    @Test
    public void testInsert(){
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "insert into customers(name, email, birth) values(?,?,?)";
            runner.update(connection, sql, "杨超越",  "chaoyueYang@163.com", "1995-07-17");
            System.out.println("添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    /**
     * 测试查询
     * BeanHander是ResultSetHandler接口的实现类，用于封装表中的一条记录
     */
    @Test
    public void testQuery1() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "select id, name, email, birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(connection, sql, handler, 2);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    /**
     * 测试查询
     * BeanListHander是ResultSetHandler接口的实现类，用于封装表中的多条记录
     */
    @Test
    public void testQuery2(){
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "select id, name, email, birth from customers where id < ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list = runner.query(connection, sql, handler, 7);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    /**
     * 测试查询
     * MapHander是ResultSetHandler接口的实现类，用于对应表中的所有记录
     */
    @Test
    public void testQuery3() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "select id, name, email, birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map customer = runner.query(connection, sql, handler, 2);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    /**
     * 测试查询
     * MapHander是ResultSetHandler接口的实现类，用于对应表中的所有记录
     */
    @Test
    public void testQuery4() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "select id, name, email, birth from customers where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> customer = runner.query(connection, sql, handler, 7);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    /**
     * 特殊类的查询
     */
    @Test
    public void testQuery5(){
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "select max(birth) from  customers";
            ScalarHandler handler = new ScalarHandler();
            Date maxBirth = (Date) runner.query(connection,sql, handler);
            System.out.println(maxBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testQuery6(){
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql = "select id, name, email, birth from  customers where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        Date birth = resultSet.getDate("birth");
                        return new Customer(id, name, email, birth);
                    }
                    return null;
                }
            };
            Customer customer = runner.query(connection, sql, handler, 2);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}
