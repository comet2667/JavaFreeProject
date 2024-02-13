package com.hs.omok.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.hs.omok.controller.OmokController;
import com.hs.omok.model.vo.Omok;

public class Client2 {
	
	private Scanner sc = new Scanner(System.in);
	private OmokController oc = new OmokController();
	private String serverIP;
	private int port;
	
	private Socket socket = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	
	private int bX;
	private int bY;
	private int wX;
	private int wY;
	
	public void clientMain() {
		int bgu = 0;
		int wgu = 0;
		int ow1;
		int ow2;
		int check;
		try {
			System.out.print("접속할 IP 주소 입력 : ");
			serverIP = sc.next();
			System.out.print("port 입력 : ");
			port = sc.nextInt();
			socket = new Socket(serverIP, port);
			if (socket != null) {
				System.out.println("서버(" + serverIP + " : " + port + ")로 연결 성공!");
				// 입력용 스트림
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 출력용 스트림
				pw = new PrintWriter(socket.getOutputStream());
				
				System.out.print("등록을 위한 이름을 입력해주세요. : ");
				String userName = sc.next();
				pw.println(userName);
				pw.flush();
				while (true) {
					blackOrder();
					System.out.println("착수 확인 중");
					check = Integer.parseInt(br.readLine());
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
						pw.println(String.format("%d",check));
						pw.flush();
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


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				pw.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void whiteOrder() {
		try {
			while (true) {
				System.out.print(br.readLine());
				String wx = sc.next();
				pw.println(wx);
				pw.flush();
				System.out.print(br.readLine());
				String wy = sc.next();
				pw.println(wy);
				pw.flush();
				wX = Integer.parseInt(wx);
				wY = Integer.parseInt(wy);
				if (Integer.parseInt(wx) < 0 || Integer.parseInt(wx) > 19 ||
						Integer.parseInt(wy) < 0 || Integer.parseInt(wy) > 19) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				boolean wo = oc.insertWhite(Integer.parseInt(wx) - 1, Integer.parseInt(wy) - 1);
				if (wo == false) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blackOrder() {
		try {
			while (true) {
				System.out.println("흑돌 입력 중...(x)");	
				String bx = br.readLine();	//입력1
				//pw.println(bX);	
				//pw.flush();
				System.out.println("흑돌 입력 중...(y)");	
				String by = br.readLine();	//입력2
				//pw.println(bX);	
				//pw.flush();
				bX = Integer.parseInt(bx);
				bY = Integer.parseInt(by);
				if (Integer.parseInt(bx) < 0 || Integer.parseInt(bx) > 19 ||
						Integer.parseInt(by) < 0 || Integer.parseInt(by) > 19) {
					System.out.println("흑돌 재입력 중...");
					continue;
				}
				boolean bo = oc.insertBlack(Integer.parseInt(bx) - 1, Integer.parseInt(by) - 1);
				if (bo == false) {
					System.out.println("흑돌 재입력 중...");
					continue;
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void scorePrint() {
		
	}
	
	public void groundAllPrint() {
		
	}

}