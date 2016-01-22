package jokeserverproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac ClientState.java 
 * 		or java *.java once to compile all files in the whole folder
 * 
 * How to run this project:
 * 		In separate shell window open:
 * 				java JokeServer
 * 				java JokeClient
 * 				java JokeClientAdmin
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
 * 				ClientState.java
 * 
 * Notes: This is a class to help store in the hash table the proverb 
 * and joke list pertaining to a particular client. 
 */

public class ClientState {
	private volatile List<String> jokes;
	private volatile List<String> proverbs;
	
	public ClientState() {
		jokes = new ArrayList<String>();
		proverbs = new ArrayList<String>();
	}
	
	public List<String> getJokeList(){
		return jokes;
	}
	
	public List<String> getProverbList(){
		return proverbs;
	}
	
	public void updateJokeList(List<String> list){
		jokes = list;
	}
	
	public void updateProverbList(List<String> list){
		proverbs = list;
	}
}
