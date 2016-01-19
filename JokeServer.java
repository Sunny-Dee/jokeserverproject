package jokeserverproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 Create a Mode thread and send it off,
   asynchronously, to get MODE instructions.

 Create a server socket listening at port X

 Loop while loopvar is true
    Block while waiting for a connection to X
    Spawn a new worker thread and pass it the connection.

 Exit
 * @author delianaescobari
 *
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
				sock = servsock.accept(); 
				new Worker(sock).start();
			}
			
		} finally {servsock.close();} 
		
	}
}
