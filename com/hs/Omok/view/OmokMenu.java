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
	
	public void mainMenu() {
		while (true) {
			System.out.println("1. ȥ���ϱ�");
			System.out.println("2. ���� ����");
			System.out.println("3. Ŭ���̾�Ʈ ����");
			System.out.println("4. ���� �⺸ Ȯ��");
			System.out.println("9. ���α׷� ����");
			System.out.print("�޴� ��ȣ �Է� : ");
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
				System.out.println("���α׷� ����");
				return;
			default:
				System.out.println("�ٽ� �Է����ּ���.");
				break;
			}
		}
	}
	
	public void playMenu() {
		int bgu = 0;
		int wgu = 0;
		int ow;
		int check;
		while (true) {
			blackOrder();
			System.out.println("���� Ȯ�� x : " + bX + " / y : " + bY);
			System.out.println("1. ���� Ȯ��");
			System.out.println("2. ���Է�");
			System.out.println("0. ���");
			System.out.print("�Է� : ");
			check = sc.nextInt();
			if (check == 1) {
				bgu = oc.giveUp(0);
			} else if (check == 2) {
				oc.insertSet(bX - 1, bY - 1);
				continue;
			} else if (check == 0) {
				bgu = oc.giveUp(Omok.BLACK);
			}
			oc.samSam();
			ow = oc.omokWin(bgu);
			if (ow == Omok.BLACK) {
				System.out.println("=================================�浹 �¸�=================================");
				return;
			}
			while (true) {
				whiteOrder();
				System.out.println("���� Ȯ�� x : " + wX + " / y : " + wY);
				System.out.println("1. ���� Ȯ��");
				System.out.println("2. ���Է�");
				System.out.println("0. ���");
				System.out.print("�Է� : ");
				check = sc.nextInt();
				if (check == 1) {
					wgu = oc.giveUp(0);
				} else if (check == 2) {
					oc.insertSet(wX - 1, wY - 1);
					continue;
				} else if (check == 0) {
					wgu = oc.giveUp(Omok.WHITE);
				}
				ow = oc.omokWin(wgu);
				if (ow == Omok.WHITE) {
					System.out.println("=================================�鵹 �¸�=================================");
					return;
				}
				break;
			}
		}
	}
	
	public void whiteOrder() {
		while (true) {
			System.out.print("�鵹 ���ΰ� �Է� : ");
			wX = sc.nextInt();
			System.out.print("�鵹 ���ΰ� �Է� : ");
			wY = sc.nextInt();
			
			if (wX < 0 || wX > 19 || wY < 0 || wY > 19) {
				System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
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
			bX = sc.nextInt();
			System.out.print("�浹 ���ΰ� �Է� : ");
			bY = sc.nextInt();
			if (bX < 0 || bX > 19 || bY < 0 || bY > 19) {
				System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
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