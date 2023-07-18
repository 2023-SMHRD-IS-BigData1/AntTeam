

public class InvestorController {
	
	public void insert() {
		
	}

	public void insert(InfoDTO dto) {
		
		int cnt = new DAO().insert(dto);
		if (cnt > 0) {
			System.out.println("회원등록이 완료되었습니다.!");
		} else {
			System.out.println("회원등록에 실패했습니다. 다시 시도하세요!.");
		}
		
		
		
	}

}
