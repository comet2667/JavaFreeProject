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
		if (om.groundPrint()[y][x] !=0) { //|| x < 0 || x > 19 || y < 0 || y > 19) {
			return false;
		} else {
			om.insert(x, y, Omok.BLACK);
			return true;
		}
	}

	public boolean insertWhite(int x, int y) {	//백돌 착수, 중복값 있을 경우 false 반환
		if (om.groundPrint()[y][x] !=0) { //|| x < 0 || x > 19 || y < 0 || y > 19) {
			return false;
		} else {
			om.insert(x, y, Omok.WHITE);
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
				if (om.groundPrint()[i][j] == Omok.BLACK) {
					bxCount += 1;
				}else {
					bxCount = 0;
				}
				if (om.groundPrint()[j][i] == Omok.BLACK) {
					byCount += 1;
				}else {
					byCount = 0;
				}
				if ((j + cntB) < 19) {

					if (om.groundPrint()[j][j + cntB] == Omok.BLACK) {
						bxyCount += 1;
					} else {
						bxyCount = 0;
					}
					if (om.groundPrint()[j + cntB][18 - j] == Omok.BLACK) {
						byxCount += 1;
					} else {
						byxCount = 0;
					}
				}
				if (bxCount == 5 || byCount == 5 || bxyCount == 5 || byxCount == 5) {
					return Omok.BLACK;
				}
				
			}
			cntB += 1;
		}
		
		for(int i = 0; i < 19 ; i++) {
			for(int j = 0; j < 19 ; j++) {
				if (om.groundPrint()[i][j] == Omok.WHITE) {
					wxCount += 1;
				}else {
					wxCount = 0;
				}
				if (om.groundPrint()[j][i] == Omok.WHITE) {
					wyCount += 1;
				}else {
					wyCount = 0;
				}
				if ((j + cntW) < 19) {

					if (om.groundPrint()[j][j + cntW] == Omok.WHITE) {
						wxyCount += 1;
					} else {
						wxyCount = 0;
					}
					if (om.groundPrint()[j + cntW][18 - j] == Omok.WHITE) {
						wyxCount += 1;
					} else {
						wyxCount = 0;
					}
				}
				if (wxCount == 5 || wyCount == 5 || wxyCount == 5 || wyxCount == 5) {
					return Omok.WHITE;
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
	
	public int[] scoreSet(int black, int white) {
		return score;
	}
}