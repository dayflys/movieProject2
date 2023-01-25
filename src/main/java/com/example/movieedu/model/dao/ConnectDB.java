package com.example.movieedu.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@192.168.45.172:1521:XE";
            String user = "mpro1";
            String passwd = "movie";
            conn = DriverManager.getConnection(jdbcUrl, user, passwd);
        } catch (Exception e) {
            System.out.println("DB접속오류 발생!!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}