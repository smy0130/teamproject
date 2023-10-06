package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Salesinfo {
    private static String QUERY1 = "SELECT goodscode, orderqty, salesdate FROM salesinfo";

    public static void showSalesInfo() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	// 데이터베이스 연결
            conn = DB.getConnection();
            stmt = conn.createStatement();

            // 질의 수행
            rs = stmt.executeQuery(QUERY1);
            System.out.println("상품 목록:");
            while (rs.next()) {
                System.out.print("상품코드: " + rs.getInt("goodscode") + "");
                System.out.print(" 주문수량: " + rs.getString("orderqty") + "");
                System.out.println(" 주문날짜: " + rs.getString("salesdate") + "");
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