package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rawmaterial {
    private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
    private static String USER = "teamora";
    private static String PASS = "1234";
    private static String QUERY2 = "SELECT RAWMATERIALCODE, RAWMATERIALNAME, RAWMATSTOCK_QUANTITY FROM RAWMATERIAL WHERE RAWMATERIALNAME IS NOT NULL";

    public void Rawmaterial() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 연결 설정
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 구문객체 생성
            stmt = conn.createStatement();

            // 질의 수행
            rs = stmt.executeQuery(QUERY2);
            System.out.println("원자재 목록:");
            while (rs.next()) {
                System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
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