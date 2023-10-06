package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
    private static String USER = "teamora";
    private static String PASS = "1234";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }
}
