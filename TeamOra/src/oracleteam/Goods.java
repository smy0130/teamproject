package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Goods {
    private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
    private static String USER = "teamora";
    private static String PASS = "1234";
    private static String QUERY1 = "SELECT goodscode, goodsname, goodsprice FROM goods WHERE goodsname IS NOT NULL";

    public void goods() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 연결 설정
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 구문객체 생성
            stmt = conn.createStatement();

            // 질의 수행
            rs = stmt.executeQuery(QUERY1);
            System.out.println("상품 목록:");
            while (rs.next()) {
                System.out.print("상품코드: " + rs.getInt("goodscode") + "");
                System.out.print(" 상품이름: " + rs.getString("goodsname") + "");
                System.out.println(" 상품가격: " + rs.getString("goodsprice") + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}