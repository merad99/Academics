package games;

public interface Game {
	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int UNCLEAR = 2;
	
	public static final int HUMAN_WIN = 0;
	public static final int COMPUTER_WIN = 3;
	
	public void init() ;
	public String position();
	public boolean makeMove(int side, int row, int number);
	public Best chooseMove(int side, int depth);


}
