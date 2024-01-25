package com.hs.omok.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.hs.omok.controller.OmokController;
import com.hs.omok.model.vo.Omok;
import com.hs.omok.view.OmokMenu;

public class Server2 {

	private OmokController oc = new OmokController();
	private int port = 3000;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private ServerSocket server = null;
	private Scanner sc = new Scanner(System.in);

	public void serverMain() {

		try {
			server = new ServerSocket(port);
			System.out.println("클라이언트 요청 대기중 ...");
			Socket socket = server.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());

			while (true) {
				blackOrder(); // in, out 2개
				oc.samSam();
				oc.omokWin();
				if (oc.omokWin() == Omok.BLACK) {
					System.out.println("=================================흑돌 승리=================================");
					return;
				}
				whiteOrder(); // in, out 2개
				oc.omokWin();
				if (oc.omokWin() == Omok.WHITE) {
					System.out.println("=================================백돌 승리=================================");
					return;
				}

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
				pw.println("백돌 가로값 입력 : ");
				pw.flush();
				int wX = Integer.parseInt(br.readLine());
				pw.println("백돌 세로값 입력 : ");
				pw.flush();

				int wY = Integer.parseInt(br.readLine());
				if (wX < 0 || wX > 19 || wY < 0 || wY > 19) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				
				boolean wo = oc.insertWhite(wX - 1, wY - 1);
				if (wo == false) {
					System.out.println("백돌 재입력 중...");
					continue;
				}
				oc.groundAllPrint();
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
				int bX = Integer.parseInt(bx);	
				System.out.print("흑돌 가로값 입력 : ");
				String by = sc.next();
				pw.println(by);	//출력 2
				pw.flush();
				int bY = Integer.parseInt(by);
				boolean bo = oc.insertBlack(bX - 1, bY - 1);
				if (bX < 0 || bX > 19 || bY < 0 || bY > 19) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				if (bo == false) {
					System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				oc.groundAllPrint();
				break;
			}
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}

}
