package dao;

import com.bean.Customer;
import com.util.JDBCUtils;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

class CustomerDAOImplTest {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @org.junit.jupiter.api.Test
    void insert() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = new Customer(1, "小飞", "xiaofw@163.com", new Date(12434341));
            dao.insert(connection, customer);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @org.junit.jupiter.api.Test
    void deleteById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection, 1);

            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @org.junit.jupiter.api.Test
    void update() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer cust = new Customer(2, "莫扎特", "mozhate@163.com", new java.sql.Date(123435345));
            dao.update(connection, cust);

            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @org.junit.jupiter.api.Test
    void getCustomerById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection3();
            Customer customer = dao.getCustomerById(connection, 2);

            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<Customer> all = dao.getAll(connection);
            all.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @org.junit.jupiter.api.Test
    void getCount() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Long count = dao.getCount(connection);


            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @org.junit.jupiter.api.Test
    void getMaxBirth() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            java.sql.Date maxBirth = dao.getMaxBirth(connection);


            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}