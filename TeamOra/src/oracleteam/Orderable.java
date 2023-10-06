package oracleteam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Orderable{
	//주문가능상품보기
	Connection con;

	ArrayList<Integer> goodsCodeArr = new ArrayList<Integer>();
	ArrayList<String> goodsNameArr = new ArrayList<String>();
	ArrayList<Integer> goodsPriceArr = new ArrayList<Integer>();
	ArrayList<Integer> rawmatArr = new ArrayList<Integer>();

	public Orderable() {
		String DB_URL 
		= "jdbc:oracle:thin:@192.168.0.126:1521:xe";
		String USER = "teamora";
		String PASS = "1234";

		//Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			//			queryGoods(stmt, rs);
		} catch (SQLException e) {

		}
	}

	public void getgoods() throws Exception {
		String query = "select goodsCode, goodsname, goodsprice\r\n"
				+ "from goods\r\n"
				+ "where goodsname is not null";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		String query2 = "select rawmatstock_quantity\r\n"
				+ "from rawmaterial\r\n"
				+ "where rawmatstock_quantity is not null";
		Statement stmt2 = con.createStatement();
		ResultSet rs2 = stmt2.executeQuery(query2);

		int index = 0;
		while(rs.next() && rs2.next()) {

			goodsCodeArr.add(index, rs.getInt(1));
			goodsNameArr.add(index, rs.getString(2));
			goodsPriceArr.add(index, rs.getInt(3));
			rawmatArr.add(index, rs2.getInt(1));
			index++;
		}
	}

	public void OrderableGoods() throws Exception { 
		//주문가능상품목록

		for(int i = 0; i < rawmatArr.size(); i++) {
			if(rawmatArr.get(i) > 0) 
			{ //for문으로 배열 사이즈만큼 반복시키고 if문으로 수량이 0개 초과인 항목만 출력
				System.out.print(goodsCodeArr.get(i) + ". ");
				System.out.print("메뉴: " + goodsNameArr.get(i));
				System.out.print(", 가격: " + goodsPriceArr.get(i));
				System.out.println(", 재고수량: " + rawmatArr.get(i));
			}
		}

	}

}
	