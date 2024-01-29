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
		int ow;
		int check;
		try {
			System.out.print("������ IP �ּ� �Է� : ");
			serverIP = sc.next();
			System.out.print("port �Է� : ");
			port = sc.nextInt();
			socket = new Socket(serverIP, port);
			if (socket != null) {
				System.out.println("����(" + serverIP + " : " + port + ")�� ���� ����!");
				// �Է¿� ��Ʈ��
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// ��¿� ��Ʈ��
				pw = new PrintWriter(socket.getOutputStream());
				
				while (true) {
					blackOrder();
					System.out.println("���� Ȯ�� ��");
					check = Integer.parseInt(br.readLine());
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
						pw.println(String.format("%d",check));
						pw.flush();
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
					System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				boolean wo = oc.insertWhite(Integer.parseInt(wx) - 1, Integer.parseInt(wy) - 1);
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
				String bx = br.readLine();	//�Է�1
				//pw.println(bX);	
				//pw.flush();
				System.out.println("�浹 �Է� ��...(y)");	
				String by = br.readLine();	//�Է�2
				//pw.println(bX);	
				//pw.flush();
				bX = Integer.parseInt(bx);
				bY = Integer.parseInt(by);
				if (Integer.parseInt(bx) < 0 || Integer.parseInt(bx) > 19 ||
						Integer.parseInt(by) < 0 || Integer.parseInt(by) > 19) {
					System.out.println("�浹 ���Է� ��...");
					continue;
				}
				boolean bo = oc.insertBlack(Integer.parseInt(bx) - 1, Integer.parseInt(by) - 1);
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