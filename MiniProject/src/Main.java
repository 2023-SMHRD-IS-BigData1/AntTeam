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
		ArrayList<UserStockDTO> Searchlist = new ArrayList<UserStockDTO>();
		int stockindex = 0;
		int stocknum = 0;
		int stocksell = 0;
		int gold = 0;
		String id = null;
		String pw = null;
		String userid = null;
		String stockname = null;
		MP3Player bgm = new MP3Player();
		Random rn = new Random();
		GameController gcon = new GameController();
		Game_QuizDTO gamedto = null;
		ArrayList<MusicVO> music = new BackGround().Sound();

//		PrintMain.print1();
//			bgm.play(music.get(0).getPath());
//		PrintMain.sleep(1000);
//		PrintMain.print2();
//			bgm.play(music.get(0).getPath());
		if(bgm.isPlaying()) {
			bgm.stop();
		}
		bgm.play(music.get(0).getPath());
		while (true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹 [4]종료 >> ");
			int menu1 = sc.nextInt();
			// 회원가입 - 메인

			if (menu1 == 1) {
				System.out.print("아이디를 입력해주세요 : ");
				id = sc.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				pw = sc.next();
				System.out.print("닉네임을 입력해주세요 : ");
				String nick = sc.next();
				infodto = new InfoDTO(id, pw, nick, 100000);
				infodao.insert(infodto);

			} else if (menu1 == 2) {
				// 로그인 - 메인
				int result = 0;
				while (result == 0) {
					System.out.print("아이디를 입력해주세요 : ");
					id = sc.next();
					System.out.print("비밀번호를 입력해주세요 : ");
					pw = sc.next();
					infodto = new InfoDTO(id, pw);
					result = infodao.select(id, pw);
				}
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
							stockdao.update(list);
							list = stockdao.select();
							// 메뉴 출력
							System.out.printf("%n[1]판매 [2]구매 [3]뒤로가기 [4]종료 >> ");
							int menu3 = sc.nextInt();
							if (menu3 == 1) {
								System.out.print("판매할 주식 이름을 입력하세요 >> ");
								input_stockname = sc.next();
								Searchlist = userstockdao.selectName(input_stockname, userid);
								
								//판매주식 입력시 리스트 사이즈만큼 출력은 되지만 구매가격및 보유수량이 출력XX
								for (int i = 0; i < Searchlist.size(); i++) {
									System.out.printf("%d. %s\t 구매가격 : %d\t %d주%n", i + 1,
											Searchlist.get(i).getStockName(), Searchlist.get(i).getBuyPrice(), Searchlist.get(i).getStockNum());
								}
								System.out.print("판매할 주식 번호를 입력해주세요 >> ");
								stockindex = sc.nextInt() - 1;
								System.out.print("판매할 주식 수량을 입력해주세요 >> ");
								stocksell = sc.nextInt();
								while (true) {
									if (stockindex <= Searchlist.size()) {
										if (stocksell <= Searchlist.get(stockindex).getStockNum()) {
											getGold += list.get(stockindex).getNowPrice() * stocksell;
											infodao.UpdateGold(userid, getGold);
											stocknum = Searchlist.get(stockindex).getStockNum();
											stocknum -= stocksell;
											userstockdao.updatestock(userid, stocknum, Searchlist.get(stockindex).getBuyPrice());
											System.out.printf("%n주식판매 완료.%n");
											break;
										} else {
											System.out.println("수량을 다시 입력해주세요.");
										}
									} else {
										System.out.println("번호를 다시 입력해주세요.");
									}
								}
							} else if (menu3 == 2) {
								System.out.print("구매할 주식 이름을 입력하세요 >> ");
								input_stockname = sc.next();
								Searchlist = userstockdao.selectName(input_stockname, userid);
									stockdao.select(stockname);
									System.out.print("구매하실 수량을 입력해주세요 >> ");
									stocknum = sc.nextInt();
									// 입력받은 문자열의 index값 가져오기
//									stockindex = list.indexOf(input_stockname);
									// 소지금이 충분한지 확인
									if(getGold >= list.get(stockindex).getNowPrice() *stocknum) {
										System.out.println("구매에 성공하였습니다.");
									}else {
										System.out.println("보유금액이 부족합니다.");
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
						if(bgm.isPlaying()) {
							bgm.stop();
						}
//						bgm.play(music.get(4).getPath());
						while (true) {
							System.out.print("[1]퀴즈 [2]룰렛 [3]뒤로가기 [4]종료 >> ");
							int menu3 = sc.nextInt();
							if (menu3 == 1) {
								// 퀴즈게임 실행 - 미니게임
								while (true) {
									int cnt = 0;
									System.out
											.println("====퀴즈게임에 참가하셨습니다==== \n" + "gold가 없는 분들은 퀴즈를 풀어 gold를 벌어보세요! \n "
													+ "한 문제 맞출 시 10000골드를 얻게됩니다. \n" + "참가비 없이 자유롭게 참가해보세요.^^");
									System.out.print("[1] 도전하기 [2] 게임 나가기 >>");
									int menu = sc.nextInt();
									if (menu == 1) {

										// 게임시작
										System.out.println("====빈칸에 들어갈 단어를 맞춰보세요!====");

										getGold+=gcon.Quiz(gold);
										System.out.println("보유골드 : " + getGold);
									} else if (menu == 2) {
										// 게임 종료
										System.out.println("게임을 종료합니다.");
										break;
									} else {
										System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
									}
								}
							} else if (menu3 == 2) {
								// 룰렛 - 미니게임
								while (true) {
									System.out.println(
											"====카지노 잭팟에 오신걸 환영합니다==== \n " + "잭팟을 터트리시면 베팅한 금액의 5배를 돌려드립니다^^ \n "
													+ "반대로 잭팟을 터트리지 못하면 베팅한 금액을 모두 잃게됩니다.ㅠㅠ \n " + "도전하시겠습니까?");
									System.out.print("[1] 도전하기 [2] 게임 나가기 >>");
									int menu = sc.nextInt();

									if (menu == 1) {
										// 도전하기
										System.out.println("보유 gold : " + getGold);
										System.out.print("베팅하실 금액을 입력해주세요 >>");
										int bettingGold = sc.nextInt();
										if (bettingGold <= getGold) {
											getGold = getGold - bettingGold;
										} else {
											System.out.println("베팅에 사용할 gold가 보유하고 계신 gold보다 많습니다. 다시 입력해 주세요.");
										}
										if (1 == gcon.jackpot()) {
											getGold += bettingGold + bettingGold*5;
											System.out.println("축하합니다! +" + (bettingGold*5)+"gold를 얻었습니다!");
											System.out.println("총 보유 gold : " + getGold);
										}
									} else if (menu == 2) {
										// 게임종료
										System.out.println("게임을 종료합니다.");
										break;
									} else {
										System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
									}
								}

							} else if (menu3 == 3) {
								break;
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
				System.out.println("         주식왕 : " + rank.get(0).getNick());
				for (int i = 1; i < 10; i++) {
					System.out.printf("%d등   \t%s \t보유금액 : \t%d%n", i + 1, rank.get(i).getNick(),
							rank.get(i).getGold());
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
