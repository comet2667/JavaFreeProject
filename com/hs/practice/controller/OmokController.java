package com.hs.practice.controller;

import com.hs.practice.model.vo.Omok;

public class OmokController {
	
	private Omok om = new Omok();
	private int[] score = new int[2];
	private String empty = "\t¡¤";
	private String white = "\t¡Û";
	private String black = "\t¡Ü";
	
	public OmokController() {
		
	}
	
	public void insertBlack(int x, int y) {
		om.insert(x, y, om.BLACK);
	}
	
	public void insertWhite(int x, int y) {
		om.insert(x, y, om.WHITE);
	}
	
	public int omokWin() {
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
				if (om.groundPrint()[i][j] == om.BLACK) {
					bxCount += 1;
				}else {
					bxCount = 0;
				}
				if (om.groundPrint()[j][i] == om.BLACK) {
					byCount += 1;
				}else {
					byCount = 0;
				}
				if ((j + cntB) < 19) {

					if (om.groundPrint()[j][j + cntB] == om.BLACK) {
						bxyCount += 1;
					} else {
						bxyCount = 0;
					}
					if (om.groundPrint()[j + cntB][18 - j] == om.BLACK) {
						byxCount += 1;
					} else {
						byxCount = 0;
					}
				}
				if (bxCount == 5 || byCount == 5 || bxyCount == 5 || byxCount == 5) {
					return om.BLACK;
				}
				
			}
			cntB += 1;
		}
		
		for(int i = 0; i < 19 ; i++) {
			for(int j = 0; j < 19 ; j++) {
				if (om.groundPrint()[i][j] == om.WHITE) {
					wxCount += 1;
				}else {
					wxCount = 0;
				}
				if (om.groundPrint()[j][i] == om.WHITE) {
					wyCount += 1;
				}else {
					wyCount = 0;
				}
				if ((j + cntW) < 19) {

					if (om.groundPrint()[j][j + cntW] == om.WHITE) {
						wxyCount += 1;
					} else {
						wxyCount = 0;
					}
					if (om.groundPrint()[j + cntW][18 - j] == om.WHITE) {
						wyxCount += 1;
					} else {
						wyxCount = 0;
					}
				}
				if (wxCount == 5 || wyCount == 5 || wxyCount == 5 || wyxCount == 5) {
					return om.WHITE;
				}
			}
			cntW += 1;

		}
		return 0;
	}
	
	public void samSam() {
		
	}
	
	public void giveUp() {
		
	}
	
	public void groundAllPrint() {
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
