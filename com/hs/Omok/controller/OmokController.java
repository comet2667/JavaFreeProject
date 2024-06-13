package com.hs.Omok.controller;

import java.util.ArrayList;

import com.hs.Omok.model.vo.Omok;
import com.hs.Omok.model.vo.User;
import com.hs.Omok.service.Service;

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
	public void insertSet(int x, int y) {
		om.insert(x, y, 0);
	}
	
	public int omokWin() {	//Omok 2차원 배열을 반복문으로 확인 후 승자 반환
		
		int bxCount = 0;
		int byCount = 0;
		int bxyCount1 = 0;
		int byxCount1 = 0;
		int bxyCount2 = 0;
		int byxCount2 = 0;
		int wxCount = 0;
		int wyCount = 0;
		int wxyCount1 = 0;
		int wyxCount1 = 0;
		int wxyCount2 = 0;
		int wyxCount2 = 0;
		
		//int cntB = 0;
		//int cntW = 0;
		
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
				if ((j + i) < 19) {

					if (om.groundPrint()[j + i][j] == Omok.BLACK) {
						bxyCount1 += 1;
					} else {
						bxyCount1 = 0;
					}
					if (om.groundPrint()[j][18 - (j + i)] == Omok.BLACK) {
						byxCount1 += 1;
					} else {
						byxCount1 = 0;
					}
					if (om.groundPrint()[j][j + i] == Omok.BLACK) {
						bxyCount2 += 1;
					} else {
						bxyCount2 = 0;
					}
					if (om.groundPrint()[18 - (j + i)][j] == Omok.BLACK) {
						byxCount2 += 1;
					} else {
						byxCount2 = 0;
					}
				}
				if (bxCount >= 5 || byCount >= 5 || bxyCount1 >= 5 || byxCount1 >= 5 ||
						bxyCount2 >= 5 || byxCount2 >= 5) {
					return Omok.BLACK;
				}
				
			}
			//cntB += 1;
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
				if ((j + i) < 19) {

					if (om.groundPrint()[j][j + i] == Omok.WHITE) {
						wxyCount1 += 1;
					} else {
						wxyCount1 = 0;
					}
					if (om.groundPrint()[j][18 - (j + i)] == Omok.WHITE) {
						wyxCount1 += 1;
					} else {
						wyxCount1 = 0;
					}
					if (om.groundPrint()[j][j + i] == Omok.WHITE) {
						wxyCount2 += 1;
					} else {
						wxyCount2 = 0;
					}
					if (om.groundPrint()[18 - (j + i)][j] == Omok.WHITE) {
						wyxCount2 += 1;
					} else {
						wyxCount2 = 0;
					}
				}
				if (wxCount >= 5 || wyCount >= 5 || wxyCount1 >= 5 || wyxCount1 >= 5 ||
						wxyCount2 >= 5 || wyxCount2 >= 5) {
					return Omok.WHITE;
				}
			}
			//cntW += 1;

		}
		return 0;
	}
	
	public int omokWin(int order) {	//Omok 2차원 배열을 반복문으로 확인 후 승자 반환
		if(order == Omok.BLACK) {
			return Omok.BLACK;
		}else if(order == Omok.WHITE) {
			return Omok.WHITE;
		}
		return 0;
	}
	
	public void samSam() {	//	삼삼 감지
		
	}
	
	public int giveUp(int order) {	//	기권 처리
		if(order == Omok.BLACK) {
			return Omok.BLACK;
		}else if(order == Omok.WHITE) {
			return Omok.WHITE;
		}
		return 0;
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
	
	public void groundClear() {
		for(int i = 0 ; i < 19 ; i++) {
			for(int j = 0 ; j < 19 ; j++) {
				om.insert(i, j, 0);
			}
		}
	}
	
	public void insertUser(String userName, String stone, String result, int count) {
		User u = new User(userName, stone, result, count);
		
		int rslt = new Service().insertUser(u);
		
		if (rslt > 0) {
			
		} else {
			
		}
	}
	
	public void selectAllList() {
		ArrayList<User> list = new Service().selectAllList();
		
		if (list.size() > 0) {
			
		} else {
			
		}
		
	}
}