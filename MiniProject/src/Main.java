import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import game.GameController;
import game.Game_QuizDTO;
import javazoom.jl.player.MP3Player;
import music.BackGround;
import music.MusicVO;

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
		MP3Player bgm = new MP3Player();
		Random rn = new Random();
		GameController gcon = new GameController();
		Game_QuizDTO gamedto = null;
		ArrayList<MusicVO> music =  new BackGround().Sound();

		while (true) {
			PrintMain.print1();
			bgm.play(music.get(0).getPath());
			PrintMain.sleep(2000);
			PrintMain.print2();
			bgm.play(music.get(0).getPath());
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
				// id, pw 받아와서 일치하면 nick 출력
				infodto = new InfoDTO(id, pw);
				infodao.select(id, pw);
				// 로그인 성공실패 코드작성

				while (true) {
					userid = id;
					int getGold = infodao.getGold(userid);
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
								while(true) {
									int cnt = 0;
									System.out.println("====퀴즈게임에 참가하셨습니다==== \n"
											+ "gold가 없는 분들은 퀴즈를 풀어 gold를 벌어보세요! \n "
											+ "한 문제 맞출 시 10000골드를 얻게됩니다. \n"
											+ "참가비 없이 자유롭게 참가해보세요.^^");
									System.out.print("[1] 도전하기 [2] 게임 나가기 >>");
									int menu = sc.nextInt();
									if(menu==1) {
										
										//게임시작
										System.out.println("====빈칸에 들어갈 단어를 맞춰보세요!====");
										
										getGold+=gcon.Quiz();
										System.out.println("보유골드 : " + getGold);
									}else if(menu==2){
										//게임 종료
										System.out.println("게임을 종료합니다.");
										break;
									}else {
										System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
									}
								}
							} else if (menu3 == 2) {
								// 룰렛 - 미니게임
								while(true){
									System.out.println("====카지노 잭팟에 오신걸 환영합니다==== \n "
											+ "잭팟을 터트리시면 베팅한 금액의 3배를 돌려드립니다^^ \n "
											+ "반대로 잭팟을 터트리지 못하면 베팅한 금액을 모두 잃게됩니다.ㅠㅠ \n "
											+ "도전하시겠습니까?");
									System.out.print("[1] 도전하기 [2] 게임 나가기 >>");
									int menu = sc.nextInt();
									
									if(menu==1) {
										// bgm
												
										//도전하기
										System.out.println("보유 gold : "+getGold);
//										System.out.print(     보유 gold 입력       );
										System.out.print("베팅하실 금액을 입력해주세요 >>");
										int bettingGold = sc.nextInt();
										if(bettingGold<=getGold){
											getGold = getGold-bettingGold;
										}else {
											System.out.println("베팅에 사용할 gold가 보유하고 계신 gold보다 많습니다. 다시 입력해 주세요.");
										}
										if(1==gcon.jackpot()) {
											getGold = getGold-bettingGold;
											getGold = getGold+bettingGold*4;
										}
									}else if(menu==2) {
										//게임종료
										System.out.println("게임을 종료합니다.");
										break;
									}else {
										System.out.println("잘 못 입력하셨습니다. 다시 입력해 주세요.");
									}
								}
								
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
				ArrayList<InfoDTO> rank = new InfoDAO().viewRank();
				System.out.println("             RANKING          ");
				System.out.println("         주식왕 : "+rank.get(0).getNick());
				for(int i = 1; i <10 ;i++) {
					System.out.printf("%d등   \t%s \t보유금액 : \t%d%n",i+1,
							rank.get(i).getNick(),rank.get(i).getGold());
				}
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
