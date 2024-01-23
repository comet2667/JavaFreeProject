package com.hs.omok.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.hs.omok.model.vo.Omok;
import com.hs.omok.controller.OmokController;

public class OmokController {
	
	private Omok om = new Omok();
	private int[] score = new int[2];
	private String empty = "\t·";
	private String white = "\t○";
	private String black = "\t●";
	
	public OmokController() {
		
	}
	
	public boolean insertBlack(int x, int y) {	//흑돌 착수, 중복값 있을 경우 false 반환
		if (om.groundPrint()[x][y] != 0 || x < 0 || x > 19 || y < 0 || y > 19) {
			return false;
		} else {
			om.insert(x, y, om.BLACK);
			return true;
		}
	}

	public boolean insertWhite(int x, int y) {	//백돌 착수, 중복값 있을 경우 false 반환
		if (om.groundPrint()[x][y] != 0 || x < 0 || x > 19 || y < 0 || y > 19) {
			return false;
		} else {
			om.insert(x, y, om.WHITE);
			return true;
		}
	}
	
	public int omokWin() {	//Omok 2차원 배열을 반복문으로 확인 후 승자 반환
		int bxCount = 0;
		int byCount = 0;
		int bxyCount = 0;
		int byxCount = 0;
		int wxCount = 0;
		int wyCount = 0;
		int wxyCount = 0;
		int wyxCount = 0;
		
		int cntB = 0;
		int cntW = 0;
		
		for(int i = 0 ; i < 19 ; i++) {
			
			for(int j = 0 ; j < 19 ; j++) {
				if (om.groundPrint()[i][j] == om.BLACK) {
					bxCount += 1;
				}else {
					bxCount = 0;
				}
				if (om.groundPrint()[j][i] == om.BLACK) {
					byCount += 1;
				}else {
					byCount = 0;
				}
				if ((j + cntB) < 19) {

					if (om.groundPrint()[j][j + cntB] == om.BLACK) {
						bxyCount += 1;
					} else {
						bxyCount = 0;
					}
					if (om.groundPrint()[j + cntB][18 - j] == om.BLACK) {
						byxCount += 1;
					} else {
						byxCount = 0;
					}
				}
				if (bxCount == 5 || byCount == 5 || bxyCount == 5 || byxCount == 5) {
					return om.BLACK;
				}
				
			}
			cntB += 1;
		}
		
		for(int i = 0; i < 19 ; i++) {
			for(int j = 0; j < 19 ; j++) {
				if (om.groundPrint()[i][j] == om.WHITE) {
					wxCount += 1;
				}else {
					wxCount = 0;
				}
				if (om.groundPrint()[j][i] == om.WHITE) {
					wyCount += 1;
				}else {
					wyCount = 0;
				}
				if ((j + cntW) < 19) {

					if (om.groundPrint()[j][j + cntW] == om.WHITE) {
						wxyCount += 1;
					} else {
						wxyCount = 0;
					}
					if (om.groundPrint()[j + cntW][18 - j] == om.WHITE) {
						wyxCount += 1;
					} else {
						wyxCount = 0;
					}
				}
				if (wxCount == 5 || wyCount == 5 || wxyCount == 5 || wyxCount == 5) {
					return om.WHITE;
				}
			}
			cntW += 1;

		}
		return 0;
	}
	
	public void samSam() {	//	삼삼 감지
		
	}
	
	public void giveUp() {	//	기권 처리(프론트 구현 시 사용예정 메소드)
		
	}
	
	public void groundAllPrint() {	// Omok 클래스의 2차원 배열을 String 타입으로 출력
		for(int i = 1; i < 20 ; i++) {
			System.out.print("\t"+i);
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		int num = 0;
		for(int i = 0; i < 19 ; i++) {
			System.out.print(i+1 + " ");
			for(int j = 0 ; j < 19 ; j++) {
				num = om.groundPrint()[i][j];
				switch(num) {
				case 0 :
					System.out.print(this.empty);
					break;
				case 1 :
					System.out.print(this.black);
					break;
				case 2 :
					System.out.print(this.white);
					break;
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
	
	public void omokServer() {	// 서버 보류
		Scanner sc = new Scanner(System.in);
		OmokController oc = new OmokController();
		int port = 3000;
		BufferedReader br = null;
		PrintWriter pw = null;
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("클라이언트 요청 대기중 ...");
			Socket socket = server.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());

			pw.println("백돌 가로값 입력 : ");	// #1
			pw.flush();
			
			int wX = Integer.parseInt(br.readLine());	// @1
			System.out.println("백돌 입력 값 : " + wX);

			
			pw.println("백돌 세로값 입력 : ");	// #2
			pw.flush();
			int wY = Integer.parseInt(br.readLine());	// @2
			
			System.out.println("백돌 입력 값 : " + wY);
			
			boolean wo = oc.insertWhite(wX - 1, wY - 1);
			if (wo == false) {
				pw.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				pw.flush();

			}

			oc.omokWin();
			if (oc.omokWin() == Omok.WHITE) {
				System.out.println("=================================백돌 승리=================================");
				return;
			}

			for (int i = 1; i < 20; i++) {
				pw.println("\t" + i + "\n");
				pw.flush();
			}
			pw.println("\n\n\n");
			pw.flush();
			int num = 0;
			for (int i = 0; i < 19; i++) {
				pw.println(i + 1 + " ");	//줄바꿈 없음
				pw.flush();
				for (int j = 0; j < 19; j++) {
					num = om.groundPrint()[i][j];
					switch (num) {
					case 0:
						pw.println(this.empty); // 줄바꿈 없음
						pw.flush();
						break;
					case 1:
						pw.println(this.black);
						pw.flush();
						break;
					case 2:
						pw.println(this.white);
						pw.flush();
						break;
					}
				}
				pw.println("\n\n\n");
				pw.flush();
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int[] scoreSet(int black, int white) {
		return score;
	}
}