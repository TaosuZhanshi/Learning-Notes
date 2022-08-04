package com.myssm.CustomerDAO;


import com.myssm.bean.Customer;

import java.sql.Date;
import java.util.List;

public interface CustomerDAO {
    /**
     * 插入一条Customer数据
     * @param cust
     */
    void insert(Customer cust);

    /**
     * 插入一条Customer数据，使用具体的数据
     * @param name
     * @param email
     * @param birth
     */
    void insertByPara(String name, String email, java.util.Date birth);

    /**
     * 依据id删除指定数据
     * @param id
     */
    void deleteById(int id);

    /**
     * 依据cust对象更新指定数据
     * @param cust
     */
    void update(Customer cust);

    /**
     * 依据id查询指定的Customer对象
     * @param id
     */
    Customer getCustomerById(int id);

    /**
     * 查询表中所有记录构成的集合
     * @return
     */
    List<Customer> getAll();

    /**
     * 查询特定页面的客户信息
     * @param keyword
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Customer> getCustomerList(String keyword, int pageNumber, int pageSize);

    /**
     * 查询客户的总量
     * @param key
     * @return
     */
    Long getCount(String key);

    /**
     * 返回数据中的数据的条目数
     * @return
     */
    Date getMaxBirth();

}
