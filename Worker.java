package jokeserverproject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Name: Deliana Escobari       Date: Tuesday January 19th, 2015
 * Java version used: 1.8 
 * Compile with command: javac Worker.java 
 * 		or java *.java once to compile all files in the whole folder
 */
public class Worker extends Thread{
	String mode;
	static String name;
	
	Socket sock; 	
	private static List<String> jokes;
	private static List<String> proverbs;

	
	Worker (Socket s) {
		sock = s;
		jokes = new ArrayList<String>();
		proverbs = new ArrayList<String>();
		addJokes();
		addProverbs();
	}
	
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
			
			//Note this branch might not execute when expected
			try{
				
			    name = in.readLine(); //get user's name from the socket	
			    mode = ModeWorker.mode;
			    
			    printRequest(mode, out);
			    
			    in = new BufferedReader
						(new InputStreamReader(sock.getInputStream()));
			    
			} catch (IOException x){
				System.out.println("Server read error");
				x.printStackTrace();
			}
			sock.close(); //close this connection, but not the server
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
	}

	
	public void printRequest(String mode, PrintStream out){
		if (mode.equals("m"))
			out.println("WARNING: System under maintenance.");
		else if (mode.equals("p"))
			out.println(chooseProverb());
		else 
			out.println(chooseJoke());

	}
	
	public String chooseJoke(){
		int idx = 0; 
		if (jokes.size() > 1)
			idx = ThreadLocalRandom.current().nextInt(0, jokes.size());
		
		String joke;
		
		if (!jokes.isEmpty()){
			joke = jokes.remove(idx);
			
		}
		else {
			addJokes();
			joke = jokes.get(idx);
			jokes.remove(idx);
		}
		
		return joke;
	}
	
	public String chooseProverb(){
		int idx = 0; 
		if (proverbs.size() > 1)
			idx = ThreadLocalRandom.current().nextInt(0, proverbs.size());
		
		String proverb;
		
		if (!proverbs.isEmpty()){
			proverb = proverbs.get(idx);
			proverbs.remove(idx);
		}
		else {
			addProverbs();
			proverb = proverbs.get(idx);
			proverbs.remove(idx);
		}
		
		return proverb;
	}
	
	/*Helper functions*/
	private void addJokes(){
		jokes.add("A. I changed my password to \"incorrect.\" \nSo whenever "
				+ "I forget what it is, the computer will say \n\"Your password is incorrect.\"");
		jokes.add("B. In the 21st century deleting history "
				+ "has become more important than making it.");
		jokes.add("C. I find it ironic that the colors red, white, and blue stand for freedom until "
				+ "they are flashing behind you.");
		jokes.add("D. A clean house is the sign of a broken computer.");
		jokes.add("E. " + name + ", what did the spider do on the computer?\nMade a website!.");
		
	}
	
	private void addProverbs(){
		proverbs.add("A. \"Fear is the path to the dark side…fear leads to anger…\nanger "
				+ "leads to hate…hate leads to suffering\" \n"
				+ "                                    -Master Yoda");
		
		proverbs.add("B. \"Do. Or do not. There is no try.\" \n"
				+ "                  -Master Yoda");
		
		proverbs.add("C. \"Who's more foolish? The fool, \n"
				+ "or the fool that follow him.\" \n"
				+ "                                  -Obi Wan Kenobi");
		
		proverbs.add("D. All we have to decide is what to do with the time that is "
				+ "given to us. \n"
				+ "                                        -Gandalf The Grey");
		
		proverbs.add("E. There is some good in this world, " + name
				+ " and it's worth fighting for. \n"
				+ "                                      -Samwise Gamgee");
	}
}
