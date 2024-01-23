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
		
//		// 1) ��û�� ������ IP�ּҿ� ��Ʈ��ȣ Ȯ��
		String serverIP = "192.168.10.24";
		int port = 3000;
		
		//���Ǵ� ���(����, ��Ʈ��) �ʱ�ȭ
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
//		
		// 2) ������ ���� ��û�� ������ (��û�ϰ��� �ϴ� IP�ּҿ� ��Ʈ��ȣ�� ����Ͽ� ���� ��ü ����)
		try {

			socket = new Socket(serverIP, port); // ������ ���� ��û

			if (socket != null) { // �������� ���� �������� ���
				System.out.println("����(" + serverIP + " : " + port + ")�� ���� ����!");


				// �Է¿� ��Ʈ��
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// ��¿� ��Ʈ��
				pw = new PrintWriter(socket.getOutputStream());

				System.out.print(br.readLine()); // #1
				String sendMessageX = sc.next();
				pw.println(sendMessageX); // @1
				pw.flush();

				// System.out.print("�Է� 1 : ");
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