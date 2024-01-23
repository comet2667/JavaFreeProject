package com.hs.omok.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		// 1) 요청할 서버의 IP주소와 포트번호 확인
		String serverIP = "192.168.10.24";
		int port = 3000;
		
		//사용되는 통로(소켓, 스트림) 초기화
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
//		
		// 2) 서버에 연결 요청을 보내기 (요청하고자 하는 IP주소와 포트번호를 사용하여 소켓 객체 생성)
		try {

			socket = new Socket(serverIP, port); // 서버로 연결 요청

			if (socket != null) { // 서버와의 연결 성공했을 경우
				System.out.println("서버(" + serverIP + " : " + port + ")로 연결 성공!");


				// 입력용 스트림
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// 출력용 스트림
				pw = new PrintWriter(socket.getOutputStream());

				System.out.print(br.readLine()); // #1
				String sendMessageX = sc.next();
				pw.println(sendMessageX); // @1
				pw.flush();

				// System.out.print("입력 1 : ");
				System.out.print(br.readLine()); // #2
				String sendMessageY = sc.next();
				pw.println(sendMessageY); // @2
				pw.flush();		
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

}