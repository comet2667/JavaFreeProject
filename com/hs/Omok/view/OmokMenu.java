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
				System.out.println("=================================�浹 �¸�=================================");
				return;
			}
			whiteOrder();
			oc.omokWin();
			if(oc.omokWin() == Omok.WHITE) {
				System.out.println("=================================�鵹 �¸�=================================");
				return;
			}
		}
	}
	
	public void whiteOrder() {
		while (true) {
			System.out.print("�鵹 ���ΰ� �Է� : ");
			int wX = sc.nextInt();
			System.out.print("�鵹 ���ΰ� �Է� : ");
			int wY = sc.nextInt();
			boolean wo = oc.insertWhite(wX - 1, wY - 1);
			if (wo == false) {
				System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
			oc.groundAllPrint();
			break;
		}
	}

	public void blackOrder() {
		while (true) {
			System.out.print("�浹 ���ΰ� �Է� : ");
			int bX = sc.nextInt();
			System.out.print("�浹 ���ΰ� �Է� : ");
			int bY = sc.nextInt();
			boolean bo = oc.insertBlack(bX - 1, bY - 1);
			if (bo == false) {
				System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
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