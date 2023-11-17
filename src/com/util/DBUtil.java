package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {  //DataBase
/**
 * JDBC driver name and database URL
 * static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
 * static final String DB_URL = "jdbc:mysql://localhost/";
 * //  Database credentials
 * static final String USER = "root";
 * static final String PASS = "000000";
 */
static String JDBC_DRIVER;
    static String DB_URL;
    static String USER;
    static String PASS;
    static {
        //获得Properties对象
        Properties properties = new Properties();
        //解析jdbc.properties属性文件
        InputStream inputStream = DBUtil.class.getResourceAsStream("/jdbc.properties");

        try {
            properties.load(inputStream);
            JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
            DB_URL = properties.getProperty("DB_URL");
            USER = properties.getProperty("USER");
            PASS = properties.getProperty("PASS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获得connection对象
    public static Connection getConn(){
        Connection conn = null;
//        Class.forName("JDBC_DRIVER");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭操作封装
    public static void closeAll(Connection conn, PreparedStatement pstmt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
