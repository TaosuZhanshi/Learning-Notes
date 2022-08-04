package com.myssm.CustomerDAO;

import com.myssm.bean.Customer;

import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public void insert(Customer cust) {
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        update(sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public void insertByPara(String name, String email, java.util.Date birth){
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        update(sql, name, email, birth);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from customers where id = ?";
        update(sql, id);
    }

    @Override
    public void update(Customer cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        Customer customer = getInstance(sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "select id, name, email, birth from customers";
        List<Customer> customer = getForList(sql);
        return customer;
    }

    @Override
    public List<Customer> getCustomerList(String keyword, int pageNumber, int pageSize) {
        String sql = "select id, name, email, birth from customers where name like ? limit ? , ?";
        List<Customer> customers = getForList(sql,"%" + keyword + "%", (pageNumber - 1) * pageSize, pageSize);
        return customers;
    }

    @Override
    public Long getCount(String keyword) {
        String sql = "select count(*) from customers where name like ?";
        return getValue(sql, "%" + keyword + "%");
    }

    @Override
    public Date getMaxBirth() {
        String sql = "select max(birth) from customers";
        return getValue(sql);
    }
}
