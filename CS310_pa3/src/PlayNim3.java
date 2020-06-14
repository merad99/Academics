import java.util.Scanner;

public class PlayNim3 {
	public PlayNim3() {
		g = new Nim();
	}

	public void doComputerMove() {
		boolean legal;
		printBoard();
		System.out.println("Computer's move");
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("column: ");
			int col = scan.nextInt();
			System.out.println("Computer doing move = " + row + col);
			legal = g.makeMove(TicTacToe1.COMPUTER, row, col);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
	}

	public void doHumanMove() {
		boolean legal;
		printBoard();
		System.out.println("Human's move");
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("column: ");
			int col = scan.nextInt();
			System.out.println("Human doing move = " + row + col);
			legal = g.makeMove(TicTacToe1.HUMAN, row, col);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
	}

	boolean checkAndReportStatus() {
		if (g.winner() == 1) {
			System.out.println("Computer says: I WIN!!");
			return false; // game is done
		}
		if (g.winner() == 0) {
			System.out.println("Human says: I WIN!!");
			return false; // game is done
		}
		if (g.winner() == 2) {
			return false;
		}
		return true;
	}

	// do one round of playing the game, return true at end of game
	public boolean getAndMakeMoves() {
		// let computer go first...
		doComputerMove();
		// System.out.println("count = " + t.getCount());
		if (checkAndReportStatus())
			return false; // game over
		doHumanMove();
		if (checkAndReportStatus())
			return false; // game over
		return true;
	}

	void printBoard() {
		String GameView = g.position();
		System.out.println(GameView);

	}

	void playOneGame() {
		boolean continueGame = true;
		g.init();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayNim3 ui = new PlayNim3();
		ui.playOneGame();
	}

	private Nim g; // g for game
	private Scanner scan = new Scanner(System.in);

}
