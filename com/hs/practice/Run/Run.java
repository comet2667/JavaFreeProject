package com.hs.practice.Run;

import com.hs.practice.controller.OmokController;
import com.hs.practice.view.OmokMenu;

public class Run {
	public static void main(String[] args) {
	
		OmokController oc = new OmokController();
		OmokMenu om = new OmokMenu();
		om.playMenu();
		
	}
	
	
}
