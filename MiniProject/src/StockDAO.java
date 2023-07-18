
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	ArrayList<StockDTO> dto = new ArrayList<StockDTO>();
	String data = "";

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

	public String select(StockDTO dto) {
		getCon();
		try {
			String sql = "select * from Stock where id = ?";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colum = rsmd.getColumnCount();
			if(rs.next()) {
				for(int i = 2; i <=colum; i++) {
					System.out.println(rsmd.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
