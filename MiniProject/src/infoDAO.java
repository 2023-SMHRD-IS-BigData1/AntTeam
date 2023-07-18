
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class infoDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	String data = "";
	ArrayList<InfoDTO> list = new ArrayList<InfoDTO>();
	
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

	public int insert(InfoDTO dto) {

		try {
			getCon();
			String sql = "insert into Investor_info values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			// ?
			// dto : title,name,price,bookNum
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNick());

			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류");
			e.printStackTrace();
		} finally {
			getClose();
		}
		return cnt;
	}

	public ArrayList<InfoDTO> select(InfoDTO dto) {

		getCon();
		    try {
		       String sql = "select * from Investor_INFO where id =? and pw = ?";
		       psmt = conn.prepareStatement(sql);
		       psmt.setString(1,dto.getId());
		       psmt.setString(2,dto.getPw());
		       rs = psmt.executeQuery();
		       while(rs.next()) {
		          String id = rs.getString(1);
		          String pw = rs.getString(2);
		          String nick = rs.getString(3);
		          InfoDTO result = new InfoDTO(id, pw , nick);
		          list.add(result);
		       }
		    } catch (SQLException e) {
		       e.printStackTrace();
		    } finally {
		       getClose();
		    }
		    return list;
		 }

}
