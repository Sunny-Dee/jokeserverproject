package jokeserverproject;

import java.io.*;
import java.net.Socket;

/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac ModeWorker.java 
 * 		or java *.java once to compile all files in the whole folder
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
 * Notes: Thread that processes a mode change. It uses a global variable mode
 * so it can share the change with Worker class. 
 */
public class ModeWorker extends Thread{
	
	Socket sock;
	volatile static String mode = "j";
	
	ModeWorker (Socket s) {sock = s;}
	
	
	public void run(){
		//Get I/O streams in/out from the socket
		PrintStream out = null; 
		BufferedReader in = null;
		
		try{
			//input to the socket
			in = new BufferedReader
					(new InputStreamReader(sock.getInputStream()));
			//output from the socket
			out = new PrintStream(sock.getOutputStream());
			
			try {
				
			    mode = in.readLine(); //get that line from the socket
			    out.print(mode);
			    System.out.println("mode: " + mode);
//			    if (mode == "p"){
//			    	out.println("Proverb Mode");
//			    }
//			    else if (mode == "m"){
//			    	out.println("Maintenance Mode");
//			    }
//			    else {
//			    	out.println("Default Joke Mode");
//			    }
			    
			    
			} catch (IOException x){
				System.out.println("Server read error");
				x.printStackTrace();
			}
	
			sock.close(); //close this connection, but not the server
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
		

}
