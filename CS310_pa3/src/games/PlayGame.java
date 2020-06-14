package games;

import java.util.Scanner;

public class PlayGame {
	
	public PlayGame() {
			
	}
	
	
	public void doComputerMove() {
		Best compMove = t.chooseMove(Game.COMPUTER, 0);  // level 0 call
		System.out.println("Computer plays ROW = " + compMove.row + " COL = " + compMove.column);
		t.makeMove(Game.COMPUTER, compMove.row, compMove.column);
		printBoard();
	}
	
	public void doHumanMove() {
		boolean legal = false;
		System.out.println("Human's move");
		
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			if (getArgs().length == 0 && row == -1) {
				Best compMove = t.chooseMove(Game.HUMAN, 0);  // level 0 call
				System.out.println("Human plays ROW = " + compMove.row + " COL = " + compMove.column);
				t.makeMove(Game.HUMAN, compMove.row, compMove.column);
				legal = true;
			}
			else {
				System.out.println("column: ");
				int col = scan.nextInt();
				legal = t.makeMove(Game.HUMAN, row, col);
				if (!legal)
					System.out.println("Illegal move, try again");
			}
			//legal = g.playMove(TicTacToe1.HUMAN, row, col);
			
		} while (!legal);
		printBoard();
	}
	
	boolean checkAndReportStatus() {
		//boolean Status = true;
			if (getArgs().length > 0 && getArgs()[0] != "Nim" && ((Nim5) t).winner() == 1) {
				System.out.println("Computer says: I WIN!!");
				return false; // game is done
			}
			if (getArgs().length > 0 && getArgs()[0] != "Nim" && ((Nim5) t).winner() == 0) {
				System.out.println("Human says: I WIN!!");
				return false; // game is done
			}
	       if (getArgs().length == 0 && ((TicTacToe5) t).isAWin(Game.COMPUTER)) {
				System.out.println("Computer says: I WIN!!");
				return false; // game is done
			}
			if (getArgs().length == 0 && ((TicTacToe5) t).isAWin(Game.HUMAN)) {
				System.out.println("Computer says: You WIN!!");
				return false; // game is done
			}
			if (getArgs().length == 0 && ((TicTacToe5) t).boardIsFull()) {
				System.out.println(" Game is a DRAW");
				return false;
			}
			
		
		return true;
	}
	
	// do one round of playing the game, return true at end of game
	public boolean getAndMakeMoves() {
		// let computer go first...
		doComputerMove();
		// System.out.println("count = " + t.getCount());
		if (!checkAndReportStatus())
			return false; // game over
		doHumanMove();
		if (!checkAndReportStatus())
			return false; // game over
		return true;
	}
	
	void printBoard() {
		String Board = t.position();
		System.out.println(Board);
	}

	void playOneGame() {
		boolean continueGame = true;
		t.init();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}
	
	private static String[] savedArgs;
    public static String[] getArgs() {
        return savedArgs;
    }
	
	public static void main(String[] args) {
		System.out.println("Start of game:");
		
		savedArgs = args;
		if (args.length > 0 && args[0] != "Nim") {
			t = new Nim5();
		}
		else
			t = new TicTacToe5();
		
		
		PlayGame ui = new PlayGame();
		ui.playOneGame();
	}

	//private Game t; // g for game
	private static Game t;
	private Scanner scan = new Scanner(System.in);

}
