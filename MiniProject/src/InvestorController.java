import java.util.ArrayList;

public class InvestorController {
	
	public void insert() {
		
	}

	public void insert(InfoDTO dto) {
		int cnt = new infoDAO().insert(dto);
		if (cnt > 0) {
			System.out.println("회원등록이 완료되었습니다.!");
		} else {
			System.out.println("회원등록에 실패했습니다. 다시 시도하세요!.");
		}
	}
	public void select(InfoDTO dto) {
	      String nick = null;
	      
	      ArrayList<InfoDTO> list = new infoDAO().select(dto);
	      System.out.println(list.get(0).getNick()+"님 환영합니다.");
	      
	   }
		
	}


