package com.customer.biz.impl;

import com.customer.biz.CustomerService;
import com.myssm.CustomerDAO.CustomerDAOImpl;
import com.myssm.bean.Customer;

import java.util.Date;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDAOImpl customerDAO;
    @Override
    public List<Customer> getCustomerList(String keyword, Integer pageNum, Integer pageSize) {
        List<Customer> customerList = customerDAO.getCustomerList(keyword, pageNum, pageSize);
        return customerList;
    }

    @Override
    public void addCustomer(String name, String email, Date birth) {
        customerDAO.insertByPara(name, email, birth);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Customer customer = customerDAO.getCustomerById(id);
        return customer;
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerDAO.deleteById(id);
    }

    @Override
    public Integer getPageCount(String keyword, Integer pageSize) {
        Long count = customerDAO.getCount(keyword);
        int pageCount =(int) (count - 1 +pageSize) / pageSize;
        return pageCount;
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }
}
