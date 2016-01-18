package jokeserverproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ModeServer implements Runnable{

	public void run() {
		int q_len = 6;
		int port = 4500;
		Socket sock;
		
		try{
			@SuppressWarnings("resource")
			ServerSocket servsock = new ServerSocket(port, q_len);
			while(servsock.isBound()){ // consider changing this to just TRUE. 
				sock = servsock.accept();
				new ModeWorker(sock).start();
			}
		} catch (IOException ioe) {System.out.println(ioe);}
	}
	
	

}
