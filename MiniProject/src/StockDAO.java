
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class StockDAO {

	
	Random rd = new Random();
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	int min = 0;
	int max = 0;

	// getCon : DB연결 권한 확인 메소드
	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String db_id = "campus_e_0718_1";
			String db_pw = "smhrd1";

			conn = DriverManager.getConnection(url, db_id, db_pw);
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 찾기 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}

	// getClose : DB자원 반납하는 메소드
	public void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQL 오류");
			e.printStackTrace();
		}
	}
	public void update() {
		getCon();
		
		try {
			// while 문 안에서 배열 수만큼 반복하여 현재 가격을 기준으로
			// 현재가격을 이전가격에 담아주고 현재가격에 변동값을 준다
			// 변동가격은 지금가격에서 변동수치를 랜덤으로 잡아준다( 변동범위 설정 생각하기)
			// 들어간 변동값을 moveprice에 담아주고 변동한 가격을 현재가격에 다시 담아준다
			
			String sql = "select nowprice from stock";
			psmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
	}
	
	
	public void select() {
		getCon();
		try {
			String sql = "select * from Stock";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				System.out.printf("%-10s\t%7d원\t%7d원\t%+7d원%n",rs.getString("stockname"), rs.getInt("beforeprice"), rs.getInt("nowprice"), rs.getInt("moveprice"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}
	
	public void select(String stockname) {
		getCon();
		try {
			String sql = "select * from Stock where stockname = ?";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				if(stockname.equals(rs.getString("stockname"))) {
//					System.out.print("%-10s\t");
				}else {
					break;
				}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}

}
