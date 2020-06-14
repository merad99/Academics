import java.util.Scanner;

public class PlayNim4 {
	public PlayNim4() {
		g = new Nim4();
	}

	public void doComputerMove() {
		Best compMove = g.chooseMove(Nim4.COMPUTER, 0);  // level 0 call
		char c = (char) ((int) 'A' + compMove.row);
		System.out.println("Computer plays ROW = " + c + " with a SUM of = " + compMove.column);
		g.makeMove(Nim4.COMPUTER, compMove.row, compMove.column);
		printBoard();
	}

	public void doHumanMove() {
		boolean legal = false;
		System.out.println("Human's move");
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			char c = (char) ((int) 'A' + row);
			System.out.println("column: ");
			int col = scan.nextInt();
			System.out.println("Human plays ROW = " + c + " with a SUM of = " + col);
			legal = g.makeMove(Nim4.HUMAN, row, col);
			if (!legal)
				System.out.println("Illegal move, try again");

		   } while (!legal);
		   printBoard();
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
		System.out.println("Start of game:");
		PlayNim4 ui = new PlayNim4();
		ui.playOneGame();
	}

	private Nim4 g; // g for game
	private Scanner scan = new Scanner(System.in);

}
