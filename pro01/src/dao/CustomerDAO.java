package dao;

import com.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomerDAO {
    /**
     * 插入一条Customer数据
     * @param connection
     * @param cust
     */
    void insert(Connection connection, Customer cust);

    /**
     * 依据id删除指定数据
     * @param connection
     * @param id
     */
    void deleteById(Connection connection, int id);

    /**
     * 依据cust对象更新指定数据
     * @param connection
     * @param cust
     */
    void update(Connection connection, Customer cust);

    /**
     * 依据id查询指定的Customer对象
     * @param connection
     * @param id
     */
    Customer getCustomerById(Connection connection, int id);

    /**
     * 查询表中所有记录构成的集合
     * @param connection
     * @return
     */
    List<Customer> getAll(Connection connection);

    /**
     * 返回数据表中的数据条数
     * @param connection
     * @return
     */
    Long getCount(Connection connection);

    /**
     * 返回数据中的数据的条目数
     * @param connection
     * @return
     */
    Date getMaxBirth(Connection connection);

}
