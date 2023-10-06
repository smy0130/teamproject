package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Stockmaterial {
	private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
    private static String USER = "teamora";
    private static String PASS = "1234";
    private static String QUERY6 = "SELECT RAWMATERIALCODE, RAWMATERIALNAME, RAWMATSTOCK_QUANTITY FROM RAWMATERIAL WHERE RAWMATERIALNAME IS NOT NULL";

    public void Stockmaterial(){
    	Scanner sc = new Scanner(System.in);
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 연결 설정
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 구문객체 생성
            stmt = conn.createStatement();

            // 질의 수행
            rs = stmt.executeQuery(QUERY6);
            System.out.println("원자재 목록:");
            while (rs.next()) {
                System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
            }
            System.out.print("재고를 바꿀 재고코드를 입력하세요 : ");
            int quantityCode = sc.nextInt();
            System.out.print("재고 수량을 입력하세요 : ");
            int rawNewQuantity = sc.nextInt();
            
            String QUERY7 = "UPDATE rawmaterial "
            		+ "SET rawmatstock_quantity = '" + rawNewQuantity + "'"
            		+ "WHERE rawmaterialcode = '" + quantityCode + "'";
            stmt.executeUpdate(QUERY7);
            
            String QUERY5 = "SELECT rawmaterialcode,rawmaterialname,rawmatstock_quantity "
            		+ "FROM rawmaterial WHERE rawmaterialcode is not null";
            
            rs = stmt.executeQuery(QUERY5);
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
