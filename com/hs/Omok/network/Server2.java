package com.hs.Omok.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.hs.Omok.controller.OmokController;
import com.hs.Omok.model.vo.Omok;
import com.hs.Omok.model.vo.User;
import com.hs.Omok.service.Service;

public class Server2 {

	private OmokController oc = new OmokController();
	private int port = 3000;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private ServerSocket server = null;
	private Scanner sc = new Scanner(System.in);
	private int bX;
	private int bY;
	private int wX;
	private int wY;

	public void serverMain() {
		int bgu = 0;
		int wgu = 0;
		int ow1;
		int ow2;
		int check;
		int count = 0;
		
		try {
			System.out.print("등록을 위한 이름을 입력해주세요. : ");
			String blackUserName = sc.next();
			server = new ServerSocket(port);
			System.out.println("클라이언트 요청 대기중 ...");
			Socket socket = server.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
			
			System.out.println("상대방 정보 입력 대기중 ...");
			String whiteUserName = br.readLine();
			System.out.println(whiteUserName + "님 접속 완료.");
			
			while (true) {
				blackOrder(); // in, out 2개
				System.out.println("착수 확인 x : " + bX + " / y : " + bY);
				System.out.println("1. 착수 확인");
				System.out.println("2. 재입력");
				System.out.println("0. 기권");
				System.out.print("입력 : ");
				check = sc.nextInt();
				pw.println(String.format("%d",check));
				pw.flush();
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
					gameOver(Omok.BLACK, blackUserName, whiteUserName, count);
					return;
				} else if (ow1 == Omok.BLACK) {
					System.out.println("=================================백돌 승리=================================");
					gameOver(Omok.WHITE, whiteUserName, blackUserName, count);
					return;
				}
				oc.groundAllPrint();

				while (true) {
					whiteOrder();
					System.out.println("착수 확인 중");
					
					check = Integer.parseInt(br.readLine());
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
						gameOver(Omok.WHITE, whiteUserName, blackUserName, count);
						return;
					} else if (ow1 == Omok.WHITE) {
						System.out.println("=================================흑돌 승리=================================");
						gameOver(Omok.BLACK, blackUserName, whiteUserName, count);
						return;
					}
					break;
				}
				oc.groundAllPrint();
				count ++;
			}

		} catch (IOException e) {
//			e.printStackTrace();
		} finally {
			// 8) 통신 종료
			try {
				if (pw != null)
					pw.close();
				if (br != null)
					br.close();

				if (server != null)
					server.close(); // 서버 닫기(종료)
			} catch (IOException e) {
			}
		}
	}

	public void whiteOrder() {
		try {
			while (true) {
				System.out.println("백돌 입력 중...(x)");
				pw.println("백돌 가로값 입력 : ");
				pw.flush();
				wX = Integer.parseInt(br.readLine());
				System.out.println("백돌 입력 중...(y)");
				pw.println("백돌 세로값 입력 : ");
				pw.flush();

				wY = Integer.parseInt(br.readLine());
				if (wX < 0 || wX > 19 || wY < 0 || wY > 19) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				
				boolean wo = oc.insertWhite(wX - 1, wY - 1);
				if (wo == false) {
					System.out.println("백돌 재입력 중...");
					continue;
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blackOrder() {
		//try {
			while (true) {
				System.out.print("흑돌 가로값 입력 : ");
				String bx = sc.next();
				pw.println(bx); //출력 1
				pw.flush();
				bX = Integer.parseInt(bx);	
				System.out.print("흑돌 가로값 입력 : ");
				String by = sc.next();
				pw.println(by);	//출력 2
				pw.flush();
				bY = Integer.parseInt(by);
				boolean bo = oc.insertBlack(bX - 1, bY - 1);
				if (bX < 0 || bX > 19 || bY < 0 || bY > 19) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				if (bo == false) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				break;
			}
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}
	public void gameOver(int winner, String wName, String lName, int count) {
		User w = null;
		User l = null;
		if (winner == Omok.BLACK) {
			w = new User(wName, "흑돌", "승", count);
			l = new User(lName, "백돌", "패", count);
		} else {
			w = new User(wName, "백돌", "승", count);
			l = new User(lName, "흑돌", "패", count);
		}
		new Service().insertUser(w);
		new Service().insertUser(l);
	}

}
