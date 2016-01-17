package jokeserverproject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Worker extends Thread{
	Socket sock; 
	private int mode;
	String name = "Deliana"; //Implement this
	
	List<String> jokes;
	List<String> proverbs;
	List<String> proverbsTemp;
	
	Worker (Socket s) {
		sock = s;
		jokes = new ArrayList<String>();
		jokesTemp =  new ArrayList<String>();
		proverbs = new ArrayList<String>();
		proverbsTemp =  new ArrayList<String>();
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
			try{//Print input. Catch exceptions.
				
				String name;
			    name = in.readLine(); //get that line from the socket
				System.out.println("Looking up " + name);
				
				//Find website's IP address and print it. See method below
				printRemoteAddress(name, out);
			} catch (IOException x){
				System.out.println("Server read error");
				x.printStackTrace();
			}
			sock.close(); //close this connection, but not the server
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
	}

	static void printRemoteAddress(String name, PrintStream out) {
		try{
			out.println("Looking up " + name + "...");
			/* InetAddress class represents an IP address
			the getByName method returns the IP address given a host name
			more info on InetAddress and it's methods here:
			https://docs.oracle.com/javase/7/docs/api/java/net/InetAddress.html */
			InetAddress machine = InetAddress.getByName(name);
			
			// printing out this statement from the socket
			out.println("Host name: " + machine.getHostName()); // To client
			out.println("Host IP: " + toText(machine.getAddress())); //toText formats the IP
		} catch (UnknownHostException ex){
			out.println("Failed in atempt to look up " + name);
		}
		
	}
	
	//This formats the IP address so it looks pretty
	static String toText(byte ip[]) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < ip.length; ++i){
			if (i > 0) result.append(".");
			//append the resulting IP and masking the first two bits 
			result.append(0xff & ip[i]);
		}
		
		//Worker returns a string version of the IP address
		return result.toString();
	}
	
	
	public String chooseJoke(){
		int idx = ThreadLocalRandom.current().nextInt(0, jokes.size());
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
		return null;
	}
	
	/*Helper functions*/
	private void addJokes(){
		jokes.add("I changed my password to \"incorrect\". So whenever "
				+ "I forget what it is the computer will say \"Your password is incorrect\"");
		jokes.add("Isn't it great to live in the 21st century? Where deleting history "
				+ "has become more important than making it.");
		jokes.add("I find it ironic that the colors red, white, and blue stand for freedom until "
				+ "they are flashing behind you.");
		jokes.add("A clean house is the sign of a broken computer.");
		jokes.add("Is your name Wi-Fi? Because I'm feeling a connection.");
		
	}
	
	private void addProverbs(){
		proverbs.add("\"Fear is the path to the dark side…fear leads to anger…anger "
				+ "leads to hate…hate leads to suffering\" \n"
				+ "                        -Master Yoda");
		
		proverbs.add("Do. Or do not. There is no try. \n"
				+ "                        -Master Yoda");
		
		proverbs.add("Who's more foolish? The fool, or the fool that follow him. \n"
				+ "                        -Obi Wan Kenobi");
		
		proverbs.add("All we have to decide is what to do with the time that is"
				+ "given to us. \n                        -Gandalf The Grey");
		
		proverbs.add("There is some good in this world, " + name
				+ " and it's worth fighting for. \n"
				+ "                        -Samwise Gamgee");
	}
}
