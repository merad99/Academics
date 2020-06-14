package SuperGhost;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import sharedCode.SharedGameData;

public class FileManager  implements TurnParserV2 {
	
	private MyIOManager<SharedGameData> myIOManager;
	  private MyFileMonitor myfileMonitor;

	  public FileManager(String path) throws Exception{
		 
	    myIOManager = new MyIOManager<SharedGameData>(path);
	    myfileMonitor = new MyFileMonitor(path);
	  }

	  public void setPath(String path) throws FileNotFoundException, IllegalArgumentException{
	    myIOManager.setPath(path);
	    myfileMonitor.setFilePath(path);
	  }

	  public String getPath(){
	    return  myIOManager.getPath();
	  }
	  
	  public void update() throws IOException, IllegalStateException{
		   myfileMonitor.update();
	  }
	  
	  public boolean hasChanged(){
			return myfileMonitor.hasChanged();
		}

	  public SharedGameData read() throws ClassNotFoundException, IllegalStateException, IOException{
	    return  myIOManager.read();
	  }

	public void write (SharedGameData object) throws IOException, IllegalStateException {
		myIOManager.write(object);
	}
	
	
	
	private boolean isTurn(SharedGameData shareGameData) {
		return isGameStarted(shareGameData) && shareGameData.getGameState().isTurn();
	}
	
	/**
	 * True iff the current GameState of the specified ShareGameData is equal to IS_OVER
	 * @param shareGameData the data to be tested
	 * @return True means the specified ShareGameData indicates the game is over
	 */
	public boolean isGameOver(SharedGameData shareGameData) {
		return isGameStarted(shareGameData) && shareGameData.getGameState().isOver();

	}
	
	/**
	 * True iff the specified ShareGameData is not null
	 * @param shareGameData the data to be tested
	 * @return True means the game has begun
	 */
	public boolean isGameStarted(SharedGameData shareGameData) {
		 return Objects.nonNull(shareGameData);
	}
	
	/**
	 * True iff the current GameState of the specified ShareGameData is equal to IS_TURN, the 
	 * receiver of the state is equal to myTeamName and the last GameAction's owner is not 
	 * equal to myTeamName (or there are no game actions) 
	 * @param shareGameData the data to be tested
	 * @return True means it my time to play
	 */
	public boolean isMyTurn(String myTeamName, SharedGameData shareGameData) {
		
		return 
				//Its someone's turn
				isTurn(shareGameData) &&
				// the receiver of the state = my team name
				shareGameData.getGameState().getReceiver().equalsIgnoreCase(myTeamName) && 
				// check to make sure the current game action is empty
				(shareGameData.gameActions().isEmpty() ||
						//check to make sure the last game action is not me (Make sure I haven't already played)
						!shareGameData.gameActions().get(shareGameData.gameActions().size()-1).getOwner().equalsIgnoreCase(myTeamName) );
	}
	
	/**
	 * True iff the current GameState of the specified ShareGameData is equal to IS_TURN, the 
	 * receiver of the state is not equal to myTeamName 
	 * @param shareGameData the data to be tested
	 * @return True means its the other team's turn
	 */
	public boolean isOtherPlayersTurn(String myTeamName, SharedGameData shareGameData) {
		
		return  isTurn(shareGameData) && 
				!shareGameData.getGameState().getReceiver().equalsIgnoreCase(myTeamName);
	}
	
	/**
	 * True iff the current GameState of the specified ShareGameData is equal to IS_TURN, the 
	 * receiver of the state is not equal to myTeamName and the last GameAction's owner is not 
	 * equal to myTeamName (or there are no game actions) 
	 * @param shareGameData the data to be tested
	 * @return True means its the other team's turn
	 */
	public boolean isOtherPlayersTurnFinished(String myTeamName, SharedGameData shareGameData) {
		
		return  isTurn(shareGameData) && 
				!shareGameData.getGameState().getReceiver().equalsIgnoreCase(myTeamName) &&
				!shareGameData.gameActions().get(shareGameData.gameActions().size()-1).getOwner().equalsIgnoreCase(myTeamName) ;


	}


}
