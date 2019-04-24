package com.zhang.hadoop.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/11/12.
 */
public class JdbcTemplate {

    private String url;

    private String user;

    private String password;

    private Connection con;

    private String driver = "com.mysql.jdbc.Driver";

    public JdbcTemplate(String url, String user, String password) {
        this.url = "jdbc:mysql://" + url;
        this.user = user;
        this.password = password;
        try {
            Class.forName(driver);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> queryForList(String sql) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            //加载驱动程序
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
//                System.out.println("Succeeded connecting to the Database!");
            }
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                String[] name = new String[count];
                for (int i = 0; i < count; i++) {
                    name[i] = rsmd.getColumnName(i + 1);
                    map.put(name[i], rs.getObject(name[i]));
                }
                list.add(map);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(con!=null){
                    con.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }

    public int update(String sql) {
        int a = 0;
        try {
            con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();
            a = statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(con!=null)
                    con.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return a;
    }

//    private static final String DRIVER;
//    private static final String URL;
//    private static final String USER;
//    private static final String PASSWORD;
//
//    static {
//        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
//
//        DRIVER = bundle.getString("driver");
//        URL = bundle.getString("url");
//        USER = bundle.getString("user");
//        PASSWORD = bundle.getString("password");
//
//        /**
//         * 驱动注册
//         */
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    /**
//     * 获取 Connetion
//     *
//     * @return
//     * @throws SQLException
//     */
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    /**
//     * 释放资源
//     *
//     * @param conn
//     * @param st
//     * @param rs
//     */
//    public static void colseResource(Connection conn, Statement st, ResultSet rs) {
//        closeResultSet(rs);
//        closeStatement(st);
//        closeConnection(conn);
//    }
//
//    /**
//     * 释放连接 Connection
//     *
//     * @param conn
//     */
//    public static void closeConnection(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        //等待垃圾回收
//        conn = null;
//    }
//
//    /**
//     * 释放语句执行者 Statement
//     *
//     * @param st
//     */
//    public static void closeStatement(Statement st) {
//        if (st != null) {
//            try {
//                st.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        //等待垃圾回收
//        st = null;
//    }
//
//    /**
//     * 释放结果集 ResultSet
//     *
//     * @param rs
//     */
//    public static void closeResultSet(ResultSet rs) {
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        //等待垃圾回收
//        rs = null;
//    }

}
