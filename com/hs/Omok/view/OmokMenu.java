package com.hs.omok.view;

import java.util.Scanner;

import com.hs.omok.controller.OmokController;
import com.hs.omok.model.vo.Omok;

public class OmokMenu {
	
	private OmokController oc = new OmokController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
	}
	
	public void playMenu() {
		while(true) {
			blackOrder();
			oc.samSam();
			oc.omokWin();
			if(oc.omokWin() == Omok.BLACK) {
				System.out.println("=================================흑돌 승리=================================");
				return;
			}
			whiteOrder();
			oc.omokWin();
			if(oc.omokWin() == Omok.WHITE) {
				System.out.println("=================================백돌 승리=================================");
				return;
			}
		}
	}
	
	public void whiteOrder() {
		while (true) {
			System.out.print("백돌 가로값 입력 : ");
			int wX = sc.nextInt();
			System.out.print("백돌 세로값 입력 : ");
			int wY = sc.nextInt();
			boolean wo = oc.insertWhite(wX - 1, wY - 1);
			if (wo == false) {
				System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			oc.groundAllPrint();
			break;
		}
	}

	public void blackOrder() {
		while (true) {
			System.out.print("흑돌 가로값 입력 : ");
			int bX = sc.nextInt();
			System.out.print("흑돌 세로값 입력 : ");
			int bY = sc.nextInt();
			boolean bo = oc.insertBlack(bX - 1, bY - 1);
			if (bo == false) {
				System.out.println("해당 위치에 착수할 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			oc.groundAllPrint();
			break;
		}
	}
	
	public void scorePrint() {
		
	}
	
	public void groundAllPrint() {
		
	}
			
}