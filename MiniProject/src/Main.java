import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		InfoDTO infodto = null;
		InfoDAO infodao = new InfoDAO();
		StockDTO stockdto = null;
		StockDAO stockdao = new StockDAO();
		UserStockDTO userstockdto = null;
		UserStockDAO userstockdao = new UserStockDAO();
		ArrayList<StockDTO> list = new ArrayList<StockDTO>();
		String userid = null;
		String stockname = null;

		while (true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹 [4]종료 >> ");
			int menu1 = sc.nextInt();
			// 회원가입 - 메인
			if (menu1 == 1) {
				System.out.print("아이디를 입력해주세요 : ");
				String id = sc.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				String pw = sc.next();
				System.out.print("닉네임을 입력해주세요 : ");
				String nick = sc.next();
				infodto = new InfoDTO(id, pw, nick, 100000);
				infodao.insert(infodto);

			} else if (menu1 == 2) {
				// 로그인 - 메인
				System.out.print("아이디를 입력해주세요 : ");
				String id = sc.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				String pw = sc.next();
				String nick = null;
				// id, pw 받아와서 일치하면 nick 출력
				infodto = new InfoDTO(id, pw, null);
				infodao.select(infodto);
				// 로그인 성공실패 코드작성

				while (true) {
					userid = id;
					System.out.print("[1]주식거래 [2]미니게임 [3]종료 >> ");
					int menu2 = sc.nextInt();
					if (menu2 == 1) {
						// 주식거래
						while (true) {
							String input_stockname = null;
							// 내 자산현황 출력
							userstockdao.select(userid);
							// 주식 리스트 출력
							System.out.printf("%-10s\t%7s\t%7s\t%7s%n%n", "주식이름", "이전가격", "현재가격", "변동가격");
							list = stockdao.select();
//							stockdto = new StockDTO(list);
//							stockdao.update(stockdto);
							// 메뉴 출력
							System.out.printf("%n[1]판매 [2]구매 [3]뒤로가기 [4]종료 >> ");
							int menu3 = sc.nextInt();
							if (menu3 == 1) {
								System.out.print("판매할 주식 이름을 입력하세요 >> ");
								input_stockname = sc.next();
								stockname = input_stockname;
								stockdao.select(stockname);
								// 주식리스트에서 있는지 검증
								if (input_stockname.equals(stockname)) {
									// 보유주식리스트에서 있는지 검증
									if (input_stockname.equals(0)) {
										stockdao.select(stockname);
										// UserStockDAO호출해서 거래하기

									} else {
										System.out.println("보유한 주식이 아닙니다.");
										break;
									}
								} else {
									// 입력오류 - 주식판매
									System.out.println("다시 입력해주세요.");
								}
							} else if (menu3 == 2) {
								System.out.print("구매할 주식 이름을 입력하세요 >> ");
								input_stockname = sc.next();
								stockname = input_stockname;
								// 보유주식 리스트 , 주식리스트에서 있는지 이중검증
								if (input_stockname.equals(stockname)) {
									stockdao.select(stockname);
									System.out.print("구매하실 수량을 입력해주세요 >> ");
									int stocknum = sc.nextInt();
									// 소지금이 충분한지 확인
//									if(gold >= nowprice*stocknum) {
//										System.out.println("구매에 성공하였습니다.");
//									}else {
//										System.out.println("보유금액이 부족합니다.");
//									}
								} else {
									// 입력오류 - 주식판매
									System.out.println("다시 입력해주세요.");
								}
							} else if (menu3 == 3) {
								break;
							} else if (menu3 == 4) {
								// 종료 - 주식거래
								System.out.println("프로그램을 종료합니다.");
								System.exit(0);
							} else {
								// 입력오류 - 주식거래
								System.out.println("다시 입력해주세요.");
							}

						}

					} else if (menu2 == 2) {
						// Mini Game //
						while (true) {
							System.out.print("[1]퀴즈 [2]룰렛 [3]홀짝 [4]종료 >> ");
							int menu3 = sc.nextInt();
							if (menu3 == 1) {
								// 퀴즈게임 실행 - 미니게임
							} else if (menu3 == 2) {
								// 룰렛 - 미니게임
							} else if (menu3 == 3) {
								// 홀짝 - 미니게임
							} else if (menu3 == 4) {
								// 종료 - 미니게임
								System.out.println("프로그램을 종료합니다.");
								System.exit(0);
							} else {
								// 입력오류 - 미니게임
								System.out.println("다시 입력해주세요.");
							}
						}
					} else if (menu2 == 3) {
						// 종료 - 메인메뉴
						System.out.println("프로그램을 종료합니다.");
						System.exit(0);
					} else {
						// 입력오류 - 메인메뉴
						System.out.println("다시 입력해주세요.");
					}

				}
			} else if (menu1 == 3) {
				// 랭킹 페이지 출력 - 메인
			} else if (menu1 == 4) {
				// 프로그램 종료 - 메인
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				// 입력오류 - 메인
				System.out.println("다시 입력해주세요.");
			}

		}

	}

}
