package com.hs.omok.view;

import java.util.Scanner;

import com.hs.omok.controller.OmokController;
import com.hs.omok.model.vo.Omok;
import com.hs.omok.network.Client2;
import com.hs.omok.network.Server2;

public class OmokMenu {
	
	private OmokController oc = new OmokController();
	private Scanner sc = new Scanner(System.in);
	private Server2 server = new Server2();
	private Client2 client = new Client2();
	private int bX;
	private int bY;
	private int wX;
	private int wY;
	
	public void login() {
		
	}
	
	public void mainMenu() {
		while (true) {
			System.out.println("1. 혼자하기");
			System.out.println("2. 서버 생성");
			System.out.println("3. 클라이언트 접속");
			System.out.println("4. 직전 기보 확인");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 입력 : ");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				oc.groundClear();
				playMenu();
				break;
			case 2:
				oc.groundClear();
				server.serverMain();
				break;
			case 3:
				oc.groundClear();
				client.clientMain();
				break;
			case 4:
				oc.groundAllPrint();
				break;
			case 9:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("다시 입력해주세요.");
				break;
			}
		}
	}
	
	public void playMenu() {
		int bgu = 0;
		int wgu = 0;
		int ow1;
		int ow2;
		int check;
		while (true) {
			blackOrder();
			System.out.println("착수 확인 x : " + bX + " / y : " + bY);
			System.out.println("1. 착수 확인");
			System.out.println("2. 재입력");
			System.out.println("0. 기권");
			System.out.print("입력 : ");
			check = sc.nextInt();
			if (check == 1) {
				bgu = oc.giveUp(0);
			} else if (check == 2) {
				oc.insertSet(bX - 1, bY - 1);
				continue;
			} else if (check == 0) {
				bgu = oc.giveUp(Omok.BLACK);
			} else if (check < 0 || check > 2) {
				System.out.println("입력이 올바르지 않습니다. 다시 입력해주세요.");
				continue;
			}
			oc.samSam();
			ow1 = oc.omokWin(bgu);
			ow2 = oc.omokWin();
			if (ow2 == Omok.BLACK) {
				System.out.println("=================================흑돌 승리=================================");
				return;
			} else if (ow1 == Omok.BLACK) {
				System.out.println("=================================백돌 승리=================================");
				return;
			}
			oc.groundAllPrint();
			while (true) {
				whiteOrder();
				System.out.println("착수 확인 x : " + wX + " / y : " + wY);
				System.out.println("1. 착수 확인");
				System.out.println("2. 재입력");
				System.out.println("0. 기권");
				System.out.print("입력 : ");
				check = sc.nextInt();
				if (check == 1) {
					wgu = oc.giveUp(0);
				} else if (check == 2) {
					oc.insertSet(wX - 1, wY - 1);
					continue;
				} else if (check == 0) {
					wgu = oc.giveUp(Omok.WHITE);
				} else if (check < 0 || check > 2) {
					System.out.println("입력이 올바르지 않습니다. 다시 입력해주세요.");
					continue;
				}
				ow1 = oc.omokWin(wgu);
				ow2 = oc.omokWin();
				if (ow2 == Omok.WHITE) {
					System.out.println("=================================백돌 승리=================================");
					return;
				} else if (ow1 == Omok.WHITE) {
					System.out.println("=================================흑돌 승리=================================");
					return;
				}
				break;
			}
			oc.groundAllPrint();
		}
	}
	
	public void whiteOrder() {
		while (true) {
			System.out.print("백돌 가로값 입력 : ");
			wX = sc.nextInt();
			System.out.print("백돌 세로값 입력 : ");
			wY = sc.nextInt();
			
			if (wX < 0 || wX > 19 || wY < 0 || wY > 19) {
				System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			boolean wo = oc.insertWhite(wX - 1, wY - 1);
			if (wo == false) {
				System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			
			break;
		}
	}

	public void blackOrder() {
		while (true) {
			System.out.print("흑돌 가로값 입력 : ");
			bX = sc.nextInt();
			System.out.print("흑돌 세로값 입력 : ");
			bY = sc.nextInt();
			if (bX < 0 || bX > 19 || bY < 0 || bY > 19) {
				System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			boolean bo = oc.insertBlack(bX - 1, bY - 1);
			if (bo == false) {
				System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}
	}
	
	public void scorePrint() {
		
	}
	
	public void groundAllPrint() {
		
	}
			
}