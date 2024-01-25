package com.hs.omok.Run;

import com.hs.omok.network.Client2;

public class ClientRun {
	//서버와 클라이언트 동시 접속이 필요할 때
	public static void main(String[] args) {
		Client2 c = new Client2();
		
		c.clientMain();

	}

}
