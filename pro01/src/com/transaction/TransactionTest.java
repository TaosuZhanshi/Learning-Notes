package com.transaction;

import com.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    public void testUpdateWithTransaction(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(connection, sql1, "AA");
            System.out.println(10/0);
            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(connection, sql2, "BB");
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection, null);
        }
    }

    public int update(Connection connection, String sql, Object ...args){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }
}
