package com.myssm.trans;

import com.connection.ConnectionTest;
import com.myssm.utils.ConnUtil;
import com.myssm.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    //开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConnection().setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        Connection connection = ConnUtil.getConnection();
        connection.commit();
        ConnUtil.closeConnection();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        ConnUtil.getConnection().rollback();
    }
}
