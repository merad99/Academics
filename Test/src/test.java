
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class test {
	

	  public static void main(String[] args) {
		  
		  
		  
		
		Set<Integer>  clients = webIF.getClients();
		for(int id : clients) {
			Set<Integer>  prefs = webIF.getPrefs(id);
			Map<Integer, Set<Integer>> clientIdByPerf  = new HashMap<Integer, Set<Integer>>();
			clientIdByPerf.put(id, prefs);
		}
			
		
		
		
		//findBestMatch( clientId );

	  }

	private static int findBestMatch(int clientId) {
		
		Set<Integer> copy = new HashSet<>();
		
		Set<Integer> test = new HashSet<>();
		
		Set<Integer> prefX = webIF.getPrefs(clientId);
		
		Set<Integer>  clients = webIF.getClients();
		for(int id : clients) {
			Set<Integer>  prefs = webIF.getPrefs(id);
			copy.addAll(prefs);
			prefX.retainAll(copy);
			

		}
		
		return clientId;
		
	}

}
