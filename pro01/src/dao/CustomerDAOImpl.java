package dao;

import com.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO{
    @Override
    public void insert(Connection connection, Customer cust) {
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        update(connection, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customers where id = ?";
        update(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Customer cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(connection, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        Customer customer = getInstance(connection, sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection connection) {
        String sql = "select id, name, email, birth from customers";
        List<Customer> customer = getForList(connection, sql);
        return customer;
    }

    @Override
    public Long getCount(Connection connection) {
        String sql = "select count(*) from customers";
        return getValue(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(birth) from customers";
        return getValue(connection, sql);
    }
}
