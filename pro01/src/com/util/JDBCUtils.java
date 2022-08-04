package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {


    /**
     * 使用c3p0获取连接
     * @return
     * @throws SQLException
     */
    //数据连接池只需提供一个
    static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection2() throws SQLException {
        Connection connection = cpds.getConnection();
        return connection;
    }


    /**
     * 使用Druid数据库连接池
     */
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection3() throws SQLException {
        Connection connection = source.getConnection();
        return connection;
    }


    /**
     * 连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        FileInputStream fis = new FileInputStream("D:\\JavaProject\\learningnotes\\src\\jdbc.properties");
        BufferedInputStream stream = new BufferedInputStream(fis);

        Properties properties = new Properties();
        properties.load(stream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }


    /**
     * 关闭资源
     * @param connection
     * @param ps
     */
    public static void closeResource(Connection connection, Statement ps){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(ps);
    }

    /**
     *关闭资源，增加了ResultSet的关闭，适用于查询mysql后的关闭
     * @param connection
     * @param ps
     * @param resultSet
     */
    public static void closeResource(Connection connection, Statement ps, ResultSet resultSet){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(ps);
    }

}
