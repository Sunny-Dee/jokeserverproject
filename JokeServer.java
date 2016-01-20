package jokeserverproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac JokeServer.java 
 * 		or java *.java once to compile all files in the whole folder
 * Run with command: java JokeServer
 */

public class JokeServer {
	
	public static void main(String args[]) throws IOException{
		int q_len = 6; 
		
		int port = 4001;
		Socket sock;
		
		
		ModeServer modeserver = new ModeServer(); //this is the separate thread for the mode portion
		Thread t = new Thread(modeserver); 
		t.start(); //this calls the run method on modeserver
		
		
		ServerSocket servsock = new ServerSocket(port, q_len);
		
		System.out.println("Joke Server starting up.\nReady to"
				+ "brighten someone's day!\nListening at port: " + port + ".");
		
		try{
			while(true){ //figure out a way to quit
//				System.out.println("Press enter to get joke or proverb");
//				new Scanner(System.in).nextLine();
				System.out.println("Checking for some good stuff to share");
				sock = servsock.accept(); 
				new Worker(sock).start();
			}
			
		} finally {servsock.close();} 
		
	}
}
