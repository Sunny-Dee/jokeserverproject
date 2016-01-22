package jokeserverproject;

import java.util.ArrayList;
import java.util.List;

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
