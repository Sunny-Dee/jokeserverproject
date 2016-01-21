package jokeserverproject;

import java.io.*;
import java.net.Socket;

/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac ModeWorker.java 
 * 		or java *.java once to compile all files in the whole folder
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
