import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserStockDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	PreparedStatement psmt2 = null;
	ResultSet rs2 = null;
	int cnt = 0;
	UserStockDTO dto = null;
	ArrayList<UserStockDTO> list = new ArrayList<UserStockDTO>();
	ArrayList<UserStockDTO> Searchlist = new ArrayList<UserStockDTO>();

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

//	public void select(String id) {
//		getCon();
//		try {
//			String sql = "select * from UserStock where id = ?";
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			
//			while(rs.next()) {
//				String stockname = rs.getString("stockname");
//				String sql2 = "select nowprice from Stock where stockname = ?";
//				psmt2 = conn.prepareStatement(sql);
//				rs2 = psmt2.executeQuery();
//				System.out.printf("%-10s\t%-3d원\t%-3d원\t%-3d주\t%-3d원%n",rs.getString("stockname"),rs.getInt("buyprice"),rs2.getInt("nowprice"),rs.getInt("stocknum"),rs.getInt("buyprice") - rs2.getInt("nowprice"));
//				}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			getClose();
//		}
//	}

	// 내 자산현황 출력
	public ArrayList<UserStockDTO> select(String id) {
		getCon();
		try {
			list.removeAll(list);
			String sql = "select * from UserStock where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String nickName = rs.getString(2);
				String stockName = rs.getString(3);
				int buyPrice = rs.getInt(4);
				int stockNum = rs.getInt(5);
				dto = new UserStockDTO(nickName, stockName, buyPrice, stockNum);

				list.add(dto);
			}
			System.out.println("====보유하고 계신 주식 목록====");
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String _i = list.get(i).getId();
					String stockName = list.get(i).getStockName();
					int buyPrice = list.get(i).getBuyPrice();
					int stockNum = list.get(i).getStockNum();
					System.out.printf("%d.\t%s\t%s\t%d\t%d%n", i + 1, _i, stockName, buyPrice, stockNum);
				}
			} else {
				System.out.println("보유하고 있는 주식이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return list;
	}

	public ArrayList<UserStockDTO> selectName(String stockname, String userid) {
		getCon();
		try {
			String sql = "select * from UserStock where stockname = ? and id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, stockname);
			psmt.setString(2, userid);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String stname = rs.getString(3);
				int buyprice = rs.getInt(4);
				int stocknum = rs.getInt(5);
				dto = new UserStockDTO(id, name, stname, buyprice, stocknum);
				Searchlist.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return Searchlist;
	}

	public void updatestock(String userid, int stocknum, int price) {
		getCon();
		try {
			String sql = "update userstock set stocknum = ? where id = ? and buyprice = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, stocknum);
			psmt.setString(2, userid);
			psmt.setInt(3, price);
			psmt.executeUpdate();

			if (stocknum == 0) {
				String sql2 = "DELETE FROM USERSTOCK WHERE STOCKNUM = 0 AND BUYPRICE = ?";

				psmt = conn.prepareStatement(sql2);
				psmt.setInt(1, price);
				psmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}

}
