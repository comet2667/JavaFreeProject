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
import com.hs.omok.view.OmokMenu;

public class Server {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OmokController oc = new OmokController();
		int port = 3000;
		BufferedReader br = null;
		PrintWriter pw = null;
		ObjectOutputStream oos = null;
		ServerSocket server = null;
		OmokMenu om = new OmokMenu();
		
		try {
			server = new ServerSocket(port);
			System.out.println("클라이언트 요청 대기중 ...");
			Socket socket = server.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
