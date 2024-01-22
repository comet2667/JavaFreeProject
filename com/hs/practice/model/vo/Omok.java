package com.hs.practice.model.vo;

public class Omok {
	private int[][] ground = new int[19][19];
	public static final int BLACK = 1;
	public static final int WHITE = 2;
	private int blackScore;
	private int whiteScore;
	
	public Omok() {
		
	}
	
	public void insert(int x, int y, int color) {
		ground[y][x] = color;
	}
	
	public int[][] groundPrint() {
		return ground;
	}

	public int getWhiteScore() {
		return whiteScore;
	}

	public void setWhiteScore(int whiteScore) {
		this.whiteScore = whiteScore;
	}

	public int getBlackScore() {
		return blackScore;
	}

	public void setBlackScore(int blackScore) {
		this.blackScore = blackScore;
	}
	
	
}
