package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Orderinfo {
    private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
    private static String USER = "teamora";
    private static String PASS = "1234";

    public void orderinfo(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {          
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            
            Scanner scanner = new Scanner(System.in);
            
            while(true) {
                System.out.println("원하는 주문 정보를 선택하세요 (y 또는 n):");
                String selection = scanner.nextLine();
            
                String selectQuery;
                if (selection.equalsIgnoreCase("y")) {
                    selectQuery = "SELECT orderid, goodscode, orderconfirmed, orderdate FROM orderinfo WHERE orderconfirmed = 'y'";
                } else if (selection.equalsIgnoreCase("n")) {
                    selectQuery = "SELECT orderid, goodscode, orderconfirmed, orderdate FROM orderinfo WHERE orderconfirmed = 'n'";
                } else {
                    System.out.println("다시 선택해주세요");
                    selection = scanner.nextLine();
                    continue;
                }

                rs = stmt.executeQuery(selectQuery);

                while (rs.next()) {
                    int orderId = rs.getInt("orderid");
                    String goodsCode = rs.getString("goodscode");
                    String orderConfirmed = rs.getString("orderconfirmed");
                    java.sql.Date orderDate = rs.getDate("orderdate");

                    System.out.println("주문자ID: " + orderId);
                    System.out.println("주문코드: " + goodsCode);
                    System.out.println("주문확인: " + orderConfirmed);
                    System.out.println("주문날짜: " + orderDate);
                    System.out.println("------------------------------");
                }
                
                System.out.println("다시 선택하시겠습니까? (y 또는 n):");
                selection = scanner.nextLine();
                
                if (!selection.equals("y")) {
                    break; // "y" 이외의 입력이 들어오면 루프를 종료합니다.
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
