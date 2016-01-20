package jokeserverproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac ModeServer.java 
 * 		or java *.java once to compile all files in the whole folder
 */
public class ModeServer implements Runnable{
	
	volatile static String mode = "j";

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
