import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[1]회원가입 [2]로그인 [3]랭킹 [4]종료 >> ");
		int menu1 = sc.nextInt();
		
		while(true) {
			//회원가입 - 메인
			if(menu1 == 1) {
				System.out.print("아이디를 입력해주세요 : ");
				String id = sc.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				String pw = sc.next();
				System.out.print("닉네임을 입력해주세요 : ");
				String nick = sc.next();
				
			}
			
			
			
		}
		
	}

}
