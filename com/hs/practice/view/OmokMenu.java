package com.hs.practice.view;

import java.util.Scanner;

import com.hs.practice.controller.OmokController;
import com.hs.practice.model.vo.Omok;

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
		System.out.print("백돌 가로값 입력 : ");
		int wX = sc.nextInt();
		System.out.print("백돌 세로값 입력 : ");
		int wY = sc.nextInt();
		oc.insertWhite(wX -1, wY -1);
		oc.groundAllPrint();
		
	}
	
	public void blackOrder() {
		System.out.print("흑돌 가로값 입력 : ");
		int bX = sc.nextInt();
		System.out.print("흑돌 세로값 입력 : ");
		int bY = sc.nextInt();
		oc.insertBlack(bX -1, bY -1);
		oc.groundAllPrint();
	}
	
	public void scorePrint() {
		
	}
	
	public void groundAllPrint() {
		
	}
			
}
