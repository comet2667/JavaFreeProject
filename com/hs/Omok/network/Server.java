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

public class Server {

	private OmokController oc = new OmokController();
	private int port = 3000;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private ServerSocket server = null;

	public void serverMain() {

		try {
			server = new ServerSocket(port);
			System.out.println("Ŭ���̾�Ʈ ��û ����� ...");
			Socket socket = server.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());

			while (true) {
				blackOrder(); // in, out 2��
				oc.samSam();
				oc.omokWin();
				if (oc.omokWin() == Omok.BLACK) {
					System.out.println("=================================�浹 �¸�=================================");
					return;
				}
				whiteOrder(); // in, out 2��
				oc.omokWin();
				if (oc.omokWin() == Omok.WHITE) {
					System.out.println("=================================�鵹 �¸�=================================");
					return;
				}

			}

		} catch (IOException e) {
//			e.printStackTrace();
		} finally {
			// 8) ��� ����
			try {
				if (pw != null)
					pw.close();
				if (br != null)
					br.close();

				if (server != null)
					server.close(); // ���� �ݱ�(����)
			} catch (IOException e) {
			}
		}
	}

	public void whiteOrder() {
		try {
			while (true) {
				pw.println("�鵹 ���ΰ� �Է� : ");
				pw.flush();
				int wX = Integer.parseInt(br.readLine());
				pw.println("�鵹 ���ΰ� �Է� : ");
				pw.flush();

				int wY = Integer.parseInt(br.readLine());
				if (wX < 0 || wX > 19 || wY < 0 || wY > 19) {
					System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				boolean wo = oc.insertWhite(wX - 1, wY - 1);
				if (wo == false) {
					// pw.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				System.out.println("�鵹 �Է� �Ϸ�");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blackOrder() {
		try {
			while (true) {
				pw.println("�浹 ���ΰ� �Է� : ");
				pw.flush();
				int bX = Integer.parseInt(br.readLine());
				pw.println("�浹 ���ΰ� �Է� : ");
				pw.flush();
				int bY = Integer.parseInt(br.readLine());
				boolean bo = oc.insertBlack(bX - 1, bY - 1);
				if (bX < 0 || bX > 19 || bY < 0 || bY > 19) {
					System.out.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				if (bo == false) {
					// pw.println("�ش� ��ġ�� ������ �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				System.out.println("�浹 �Է� �Ϸ�");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
