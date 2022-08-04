package com.customer.biz;

import com.myssm.bean.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {
    /**
     * 获取客户列表
     * @param keyword
     * @param pageNum
     * @return
     */
    List<Customer> getCustomerList(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加客户
     * @param name
     * @param email
     * @param birth
     */
    void addCustomer(String name, String email, Date birth);

    /**
     * 通过客户的id获取其信息
     * @param id
     * @return
     */
    Customer getCustomerById(Integer id);

    /**
     * 通过id删除某一客户的信息
     * @param id
     */
    void deleteCustomer(Integer id);

    /**
     * 获取需要显示的总的客户页数
     * @param keyword
     * @param pageSize
     * @return
     */
    Integer getPageCount(String keyword, Integer pageSize);

    /**
     * 更新客户信息
     * @param customer
     */
    void update(Customer customer);
}
