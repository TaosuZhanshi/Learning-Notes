package com.myssm.CustomerDAO;


import com.myssm.utils.ConnUtil;
import com.myssm.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础的DAO
 */
public abstract class BaseDAO<T> {
    private Class<T> clazz = null;
    //此子类获取父类（也就是BaseDAO）的泛型并赋值给clazz
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) typeArguments[0];
    }
    /**
     * 增删改
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO update出错");
        }finally {
            //JDBCUtils.closeResource(connection, ps);
        }
    }

    /**
     * 返回单个查询结果
     * @param sql
     * @param args
     * @return
     */
    public T getInstance(String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = ConnUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过结果集的元数据来获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            if(resultSet.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < columnCount; ++i){
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列名,这里更推荐使用列的别名来以针对类的字段和列名不一
                    //String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，幅值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getInstance出错");
        }finally {
            //JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }

    /**
     * 返回多个对象查询结果
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = ConnUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length; ++i){
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过结果集的元数据来获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            List<T> list = new ArrayList<>();
            while (resultSet.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < columnCount; ++i){
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列名,这里更推荐使用列的别名来以针对类的字段和列名不一
                    //String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，幅值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getForList出错");
        }finally {
            //JDBCUtils.closeResource(connection, ps, resultSet);
        }
    }

    public <E> E getValue(String sql, Object ...args){
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = ConnUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i){
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return (E) resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getValue出错");
        }finally {
            //JDBCUtils.closeResource(connection, preparedStatement,resultSet);
        }
        return null;
    }
}
