package dao;

import com.util.JDBCUtils;

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
     * @param connection
     * @param sql
     * @param args
     * @return
     */
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

    /**
     * 返回单个对象的查询
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public T getInstance(Connection connection, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
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
        }finally {
            JDBCUtils.closeResource(null, ps, resultSet);
        }
        return null;
    }

    /**
     * 返回多个对象的查询
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public List<T> getForList(Connection connection, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
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
        }finally {
            JDBCUtils.closeResource(null, ps, resultSet);
        }
        return null;
    }

    public <E> E getValue(Connection connection, String sql, Object ...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i){
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return (E) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }
}
