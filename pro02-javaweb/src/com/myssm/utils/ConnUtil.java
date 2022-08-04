package com.myssm.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.javafx.fxml.expression.Expression;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 使用Druid数据库连接池
     */
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ConnUtil.class.getResourceAsStream("druid.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection() throws SQLException {
        Connection connection = source.getConnection();
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = createConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null){
            return;
        }
        if(!connection.isClosed()){
            connection.close();
            threadLocal.set(null);
        }
        DbUtils.closeQuietly(connection);
    }
}
