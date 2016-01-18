package jokeserverproject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Worker extends Thread{
	Socket sock; 
	String name = "Deliana"; //Implement this
	
	List<String> jokes;
	List<String> proverbs;
	
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
			//TODO get the mode from the other client 
			String mode = JokeServer.mode;
			
			//input to the socket
			in = new BufferedReader
					(new InputStreamReader(sock.getInputStream()));
			
			
			//output from the socket
			out = new PrintStream(sock.getOutputStream());
			
			//Note this branch might not execute when expected
			try{
				
				String name;
			    name = in.readLine(); //get user's name from the socket	
				printRequest(mode, out);
				
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
		if (mode == "m")
			out.println("WARNING: System under maintenance.");
		else if (mode == "p")
			out.println(chooseProverb());
		else 
			out.println(chooseJoke());

	}
	
	public String chooseJoke(){
		int idx = 0; 
		if (jokes.size() > 0)
			idx = ThreadLocalRandom.current().nextInt(0, jokes.size());
		
		String joke;
		
		if (!jokes.isEmpty()){
			joke = jokes.get(idx);
			jokes.remove(idx);
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
		if (proverbs.size() > 0)
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
		jokes.add("I changed my password to \"incorrect.\" So whenever "
				+ "I forget what it is, the computer will say \"Your password is incorrect.\"");
		jokes.add("Isn't it great to live in the 21st century? Where deleting history "
				+ "has become more important than making it.");
		jokes.add("I find it ironic that the colors red, white, and blue stand for freedom until "
				+ "they are flashing behind you.");
		jokes.add("A clean house is the sign of a broken computer.");
		jokes.add("Is your name Wi-Fi? Because I'm feeling a connection.");
		
	}
	
	private void addProverbs(){
		proverbs.add("\"Fear is the path to the dark side…fear leads to anger…\nanger "
				+ "leads to hate…hate leads to suffering\" \n"
				+ "                                    -Master Yoda");
		
		proverbs.add("Do. Or do not. There is no try. \n"
				+ "                  -Master Yoda");
		
		proverbs.add("Who's more foolish? The fool, or the fool that follow him. \n"
				+ "                                  -Obi Wan Kenobi");
		
		proverbs.add("All we have to decide is what to do with the time that is "
				+ "given to us. \n"
				+ "                                        -Gandalf The Grey");
		
		proverbs.add("There is some good in this world, " + name
				+ " and it's worth fighting for. \n"
				+ "                                      -Samwise Gamgee");
	}
}
