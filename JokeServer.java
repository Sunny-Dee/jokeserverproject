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
 * 
 * How to run this project:
 * 		In separate shell window open:
 * 				java JokeServer
 * 				java JokeClient
 * 				java 
 * 
 * 		All acceptable commands are displayed on the various consoles.
 * 		This runs across machines, in which case you have to pass the IP address of
 * 		the server to the clients. For example, if the server is running at
 * 		140.192.1.22 then you would type:
 * 				java JokeClient 140.192.1.22
 * 				java JokeClientAdmin 140.192.1.22
 * 
 * List of files needed for running the program.
 * 				JokeClient.java
 * 				JokeClientAdmin.java
 * 				JokeServer.java
 * 				ModeServer.java
 * 				ModeWorker.java
 * 				Worker.java
 * 
 * Notes: This is the connection coming from JokeClient. The server waits for the
 * connection and once it happens if spawns a mode server administrator and a Worker thread
 * so the user can ask for a joke. 
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
			while(true){ //TODO figure out a way to quit

				System.out.println("Checking for some good stuff to share");
				sock = servsock.accept(); 
				new Worker(sock).start();
			}
			
		} finally {servsock.close();} 
		
	}
}
