package jokeserverproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac JokeSClient.java 
 * 		or java *.java once to compile all files in the whole folder
 * Run with command: java JokeClient
 */

public class JokeClient {
	private static final int port = 4001;
	static String name;
	static Socket sock;
	
	public static void main(String args[]){
		String serverName;
		if (args.length < 1)
			serverName = "localhost";
		else serverName = args[0];
		
		System.out.println("Is your name WIFI? Because I'm feeling "
				+ "a connection.");
		System.out.println("Using server: " + serverName + ", listening at port: " + port);
		//Start an in variable to ask the user to input a host 
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //get's name from user
		try {
			
			System.out.print("Please enter your name " +
					"or (quit) to end: ");
			System.out.flush();
			name = in.readLine();
			System.out.print("Hi " + name + " let me share with you some "
					+ "knowledge and/or laughs.");
			System.out.println("Press enter to get a joke or proverb: ");
			
			getJokeOrProb(name, serverName);
			
		} catch (IOException x) {x.printStackTrace();}
			
		

			
	}
	

	private static void getJokeOrProb(String name, String serverName) {
//		Socket sock; ///////
		BufferedReader fromServer; 
		PrintStream toServer; 
		String textFromServer;
		
		try {
			/* Open our connection to server port. Choose our the port number we established in InetServer */
			sock = new Socket(serverName, port); 
			
			//Create filter I/O streams for the socket
			fromServer = 
					new BufferedReader(new InputStreamReader(sock.getInputStream()));/////
			toServer = new PrintStream(sock.getOutputStream()); /////
			
			//Send machine name or IP address to server:
			toServer.println(name); 

			

				//Read two or three lines of response from server,
				//and block while synchronously waiting
			for (int i = 1; i <= 3; i++){
				textFromServer = fromServer.readLine();
				if (textFromServer != null)
					System.out.println(textFromServer);
				}
	
			
//			toServer.flush(); 

			
//			sock.close();
			
		}  catch (IOException x) {
			System.out.println("Socket error.");
			x.printStackTrace();
		}
		
	}

}
