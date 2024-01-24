package com.hs.omok.Run;

import com.hs.omok.controller.OmokController;
import com.hs.omok.network.Server;
import com.hs.omok.view.OmokMenu;

public class Run {
	public static void main(String[] args) {
	
		OmokController oc = new OmokController();
		OmokMenu om = new OmokMenu();
		//om.playMenu();
		
		Server s = new Server();
		s.serverMain();
		
	}
	
	
}
