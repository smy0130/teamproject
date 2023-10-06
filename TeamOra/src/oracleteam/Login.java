package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracleteam.OrderSelect;

public class Login {
   private static String DB_URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
   private static String USER = "teamora";
   private static String PASS = "1234";
   private static String USERLOGIN = "SELECT usernumber,userid,userpassword,username "
         + "FROM userinfo "
         + "WHERE usernumber is not null";
   private static String ADMINLOGIN = "SELECT adminnumber,adminid,adminpassword,adminname "
         + "FROM admininfo "
         + "WHERE adminnumber is not null";

   public static void main(String[] args) throws Exception {
	  Goods goods = new Goods();
      Rawmaterial rawmaterial = new Rawmaterial();
      Buygoods buygoods = new Buygoods();
      OrderSelect orderselect = new OrderSelect();
      Stockmaterial stockmaterial = new Stockmaterial();
      Salesinfo salesinfo = new Salesinfo();
      Scanner sc = new Scanner(System.in);
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         stmt = conn.createStatement();
         
         System.out.println("로그인 환경을 선택해주세요.");
         System.out.println("1. 사용자 로그인");
         System.out.println("2. 관리자 로그인");
         System.out.print("입력 >>> ");
         int loginNum = sc.nextInt();
         sc.nextLine();

         if (loginNum == 1) {
            String userId;
            System.out.println("사용자 로그인 화면입니다.");
            boolean loginSuccess = false;

            while (!loginSuccess) {
               System.out.println("유저ID를 입력 해주세요.");
               System.out.print("입력 >>> ");
               userId = sc.nextLine();

               String LOGINSQL = "SELECT usernumber, userid, userpassword, username "
                     + "FROM userinfo WHERE userid = '" + userId + "'";

               rs = stmt.executeQuery(LOGINSQL);

               while (rs.next()) {
                  String dbUserId = rs.getString("userid");
                  if (userId.equals(dbUserId)) {
                     System.out.println("비밀번호를 입력하세요.");
                     System.out.print("입력 >>> ");
                     String userPw = sc.nextLine();

                     if (userPw.equals(rs.getString("userpassword"))) {
                        System.out.println("로그인 성공!");
                        loginSuccess = true;
                        while (true) {
                        	System.out.println("\n======================");
                            System.out.println("1. 상품 조회");
                            System.out.println("2. 종료");
                            System.out.print("메뉴를 선택하세요: ");
                            int choice = sc.nextInt();

                            switch (choice) {
                                case 1:
                                    orderselect.orderSelect();
                                    break;
                                case 2:
                                    System.out.println("======프로그램을 종료합니다.=======");
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
                            }
                        }
                     } else {
                        System.out.println("로그인 실패(비밀번호 틀림)");
                        sc.nextLine();
                        continue;
                     }
                  } else {
                     System.out.println("로그인 실패(아이디 틀림)");  
                     sc.nextLine();
                  }
               }
            }
         } else if (loginNum == 2) {
            String adminId;
            System.out.println("\n관리자 로그인 화면입니다.");
            boolean loginSuccess = false;

            while (!loginSuccess) {
               System.out.println("ID를 입력 해주세요.");
               System.out.print("입력 >>> ");
               adminId = sc.nextLine();

               String ADMINLOGINSQL = "SELECT adminnumber, adminid, adminpassword, adminname "
                     + "FROM admininfo WHERE adminid = '" + adminId + "'";

               rs = stmt.executeQuery(ADMINLOGINSQL);

               while (rs.next()) {
                  String dbAdminId = rs.getString("adminid");
                  if (adminId.equals(dbAdminId)) {
                     System.out.println("비밀번호를 입력하세요.");
                     System.out.print("입력 >>> ");
                     String adminPw = sc.nextLine();

                     if (adminPw.equals(rs.getString("adminpassword"))) {
                        System.out.println("관리자 로그인 완료\n");
                        System.out.println("관리자님 환영합니다.");

                        while (true) {
                           System.out.println("=========메뉴========");
                           System.out.println("1. 상품정보");
                           System.out.println("2. 판매정보확인");
                           System.out.println("3. 재고관리");
                           System.out.println("4. 사용자 정보 확인");
                           System.out.println("5. 관리자 정보 확인");
                           System.out.print("입력 >>> ");
                           int adProduct = sc.nextInt();

                           if (adProduct == 1) {
                              goods.goods();
                           } else if (adProduct == 2) {
                              Salesinfo.showSalesInfo();
                           } else if (adProduct == 3) {
                        	   stockmaterial.Stockmaterial();
                           } else if (adProduct == 4) {
                              System.out.println();
                              queryAdminLogin(stmt, rs);
                              while(true) {
                                 System.out.println("1. 유저 number 변경");
                                 System.out.println("2. 삭제");
                                 System.out.println("3. 종료");
                                 System.out.print("입력 >>> ");
                                 sc.nextLine();
                                 int mNum = sc.nextInt();
                                 if (mNum == 1) {
                                    System.out.println("변경할 유저Number를 입력하세요.");
                                    System.out.print("입력 >>> ");
                                    sc.nextLine();
                                    int uNum = sc.nextInt();
                                    System.out.println("새로운 Number를 입력하세요.");
                                    System.out.print("입력 >>> ");
                                    sc.nextLine();
                                    int uNewNum = sc.nextInt();
                                    
                                    String updateUserInfo = "UPDATE userinfo "
                                          + "Set usernumber = '" + uNewNum + "' "
                                          + " WHERE usernumber = '" + uNum +"'";
                                    stmt.executeUpdate(updateUserInfo);
                                    System.out.println("수정 성공!\n");
                                    queryUserLogin(stmt, rs);
                                    break;
                                 } else if (mNum == 2) {
                                    System.out.println("삭제할 유저Number를 입력하세요.");
                                    System.out.print("입력 >>> ");
                                    int dNum = sc.nextInt();
                                    
                                    String deleteUserInfo = "DELETE FROM userinfo "
                                          + " WHERE usernumber = '" + dNum + "'";
                                    stmt.executeUpdate(deleteUserInfo);
                                    System.out.println("삭제 성공!\n");
                                    queryUserLogin(stmt, rs);
                                    break;
                                 }  else if (mNum == 3) { 
                                	 System.out.println("종료");
                                	 break;
                                 } else {
                                    System.out.println("다시 입력해 주세요.");
                                    continue;
                                 }
                              }
                              break;
                           } else if (adProduct == 5) {
                              System.out.println();
                              queryAdminLogin(stmt, rs);
                           } else {
                              System.out.println("다시 입력해주세요.");
                              continue;
                           }
                        }
                     }
                  }
               }
            }
         } else {
            System.out.println("번호를 잘못 입력하셨습니다.");
            System.out.println("종료됩니다.");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      System.out.println("\n===========시스템 종료===========");
      sc.close();
   }
   public static void queryUserLogin(Statement stmt, ResultSet rs) throws SQLException {
	   // 질의 수행
	   rs = stmt.executeQuery(USERLOGIN);
	   while (rs.next()) {
		   System.out.print("Num: " + rs.getInt("usernumber"));
		   System.out.print(", id: " + rs.getString("userid"));
		   System.out.print(", pw: " + rs.getString("userpassword"));
		   System.out.println(", name: " + rs.getString("username"));
		   System.out.println("=====================================");
	   }
	   rs.close();
   }
   public static void queryAdminLogin(Statement stmt, ResultSet rs) throws SQLException {
      // 질의 수행
      rs = stmt.executeQuery(ADMINLOGIN);
      while (rs.next()) {
         System.out.print("Num: " + rs.getInt("adminnumber"));
         System.out.print(", id: " + rs.getString("adminid"));
         System.out.print(", pw: " + rs.getString("adminpassword"));
         System.out.println(", name: " + rs.getString("adminname"));
         System.out.println("=====================================");
      }
      rs.close();
   }
}