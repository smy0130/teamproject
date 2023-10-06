 package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Buygoods {
	private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
	private static String USER = "teamora";
	private static String PASS = "1234";
	private static String QUERY3 = "SELECT goodscode "
			+ "FROM goods WHERE goodscode = ?";
	private static String QUERY4 = "UPDATE rawmaterial "
			+ "SET rawmatstock_quantity = ? WHERE rawmaterialcode = ?"; //바꿀재고, 코드값
	public void buygoods() {
		Scanner scanner = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 연결 설정
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 구문객체 생성
			stmt = conn.createStatement();

			// 질의 수행
			//rs = stmt.executeQuery(QUERY3); //상품코드 검색
			System.out.println("살 상품의 코드를 입력하세요.(원자제 개수 줄어듬)");
            System.out.print("입력 >>> ");
            int goodsNum = scanner.nextInt(); //살 상품코드
            scanner.nextLine();
            QUERY3 = "SELECT goodscode,goodsname,goodsprice FROM goods WHERE goodscode = '" + goodsNum + "'"; //입력한 상품검색
            rs = stmt.executeQuery(QUERY3); //상품코드 검색
            while (rs.next()) { //해당 상품만 출력
            	System.out.println("선택한 상품: " + goodsNum);
            	System.out.print("상품코드: " + rs.getInt("goodscode") + "");
                System.out.print(" 상품이름: " + rs.getString("goodsname") + "");
                System.out.println(" 상품가격: " + rs.getString("goodsprice") + "");
			}
            
            String QUERY5 = "SELECT rawmaterialcode,rawmaterialname,rawmatstock_quantity "
            		+ "FROM rawmaterial WHERE rawmaterialcode is not null";
            
            if (goodsNum == 1) { //옛날통닭
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4); //재고감소
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } if (goodsNum == 2) { //후라이드치킨
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 102";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 3) { //양념치킨
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 102";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 103";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 4) { //마늘치킨
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 102";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 104";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 5) { //뿌릴클치킨
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 102";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 104";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 6) { //또또닭강정
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 102";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 106";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 7) { //로제찜닭
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 107";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 8) { //마라찜닭
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 113";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 9) { //간장찜닭
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 108";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 10) { //한방능이백숙
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 109";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 111";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 11) { //백숙
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 101";
                stmt.executeUpdate(QUERY4);
                QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
                		+ "WHERE rawmaterialcode = 109";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 12) { //콜라
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 112";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 13) { //사이다
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 117";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 14) { //소주
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 110";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 15) { //맥주
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 115";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else if (goodsNum == 16) { //치즈볼
            	QUERY4 = "UPDATE rawmaterial "
            			+ "SET rawmatstock_quantity = rawmatstock_quantity - 1"
            			+ "WHERE rawmaterialcode = 116";
                stmt.executeUpdate(QUERY4);
                System.out.println("\n=======상품 재고 감소=======");
                rs = stmt.executeQuery(QUERY5);
                while (rs.next()) {
                	System.out.print("상품코드: " + rs.getInt("RAWMATERIALCODE") + "");
                    System.out.print(" 상품이름: " + rs.getString("RAWMATERIALNAME") + "");
                    System.out.println(" 재고수량: " + rs.getString("RAWMATSTOCK_QUANTITY") + "");
                }
            } else {
            	System.out.println("잘못선택하셨습니다.\n");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
