
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	InfoDTO dto = null;
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

	public void insert(InfoDTO dto) {

		try {
			getCon();
			String sql = "insert into Investor_info values(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			// ?
			// dto : title,name,price,bookNum
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNick());
			psmt.setInt(4, dto.getGold());

			cnt = psmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("회원등록이 완료되었습니다.!");
			} else {
				System.out.println("회원등록에 실패했습니다. 다시 시도하세요!.");
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류");
			e.printStackTrace();
		} finally {
			getClose();
		}
	}

	public int select(String id, String pw) {
		getCon();
		
		    try {
		       String sql = "select * from INVESTOR_INFO where id =? and pw = ?";
		       psmt = conn.prepareStatement(sql);
		       psmt.setString(1,id);
		       psmt.setString(2,pw);
		       cnt = psmt.executeUpdate();
		       rs = psmt.executeQuery();
		       
		       if(rs.next()) {
		    	   String db_nick = rs.getString(3);
		    	   System.out.println("\t\t"+db_nick + "님 환영합니다^^");
		       }else {
		    	   System.out.println("아이디 또는 비밀번호가 잘못 되었습니다. 다시 로그인 해주세요.");
		       }
		       
		    } catch (SQLException e) {
		       e.printStackTrace();
		    } finally {
		       getClose();
		    }
			return cnt;
		 }
	
	public ArrayList<InfoDTO> viewRank() {
		try {
			getCon();
			String sql ="select name ,gold from investor_info order by gold desc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString(1);
				int gold = rs.getInt(2);
				InfoDTO dto = new InfoDTO(name, gold);
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return list;
		
	}
	public int getGold(String userid) {
		getCon();
		int gold = 0;
		try {
			
			String sql = "select * from investor_info where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				gold = rs.getInt(4);
			}
			
//			System.out.println("잘 넘어오나? " + gold);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return gold;
	}
	public void UpdateGold(String userid, int getgold) {
		getCon();
		try {
			String sql = "update investor_info set gold = ? where id = ?";
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, getgold);
			psmt.setString(2, userid);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
	}

}
