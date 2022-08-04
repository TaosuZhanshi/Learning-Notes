package com.blob;

import com.bean.Customer;
import com.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

public class BlobTest {

    @Test
    public void testInsert(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into customers (name, email, birth, photo) values (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, "成龙");
            ps.setObject(2, "jackyChan@gmail.com");
            ps.setObject(3, "1970-03-4");
            FileInputStream is = new FileInputStream(new File("chenglong.jpg"));
            ps.setBlob(4, is);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, ps);
        }
    }

    @Test
    public void testQuery(){
        Connection connection = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth, photo from customers where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, 5);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("123.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection,ps);
        }

    }
}
