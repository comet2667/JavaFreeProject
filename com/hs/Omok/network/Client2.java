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
	private String serverIP = "192.168.10.24";
	private int port = 3000;
	
	private Socket socket = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	
	public void clientMain() {
		
		try {
			socket = new Socket(serverIP, port);
			if (socket != null) {
				System.out.println("����(" + serverIP + " : " + port + ")�� ���� ����!");
				// �Է¿� ��Ʈ��
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// ��¿� ��Ʈ��
				pw = new PrintWriter(socket.getOutputStream());
				
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
				String wX = sc.next();
				pw.println(wX);
				pw.flush();
				System.out.print(br.readLine());
				String wY = sc.next();
				pw.println(wY);
				pw.flush();
				if (Integer.parseInt(wX) < 0 || Integer.parseInt(wX) > 19 ||
						Integer.parseInt(wY) < 0 || Integer.parseInt(wY) > 19) {
					System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				boolean wo = oc.insertWhite(Integer.parseInt(wX) - 1, Integer.parseInt(wY) - 1);
				if (wo == false) {
					System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
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
		try {
			while (true) {
				System.out.println("�浹 �Է� ��...(x)");	
				String bX = br.readLine();	//�Է�1
				//pw.println(bX);	
				//pw.flush();
				System.out.println("�浹 �Է� ��...(y)");	
				String bY = br.readLine();	//�Է�2
				//pw.println(bX);	
				//pw.flush();
				if (Integer.parseInt(bX) < 0 || Integer.parseInt(bX) > 19 ||
						Integer.parseInt(bY) < 0 || Integer.parseInt(bY) > 19) {
					System.out.println("�浹 ���Է� ��...");
					continue;
				}
				boolean bo = oc.insertBlack(Integer.parseInt(bX) - 1, Integer.parseInt(bY) - 1);
				if (bo == false) {
					System.out.println("�浹 ���Է� ��...");
					continue;
				}
				oc.groundAllPrint();
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