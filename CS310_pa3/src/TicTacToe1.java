
import java.util.HashMap;
import java.util.Map;



class TicTacToe1 {

	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int EMPTY = 2;

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;

	// Constructor
	public TicTacToe1() {
		clearBoard();
	}
	
	final private class Position {
		private int[][] board;

		public Position(int[][] theBoard) {
			board = new int[3][3];
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					board[i][j] = theBoard[i][j];
		}

		public boolean equals(Object rhs) {
			if (!(rhs instanceof Position))
				return false;

			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (board[i][j] != ((Position) rhs).board[i][j])
						return false;
			return true;
		}

		public int hashCode() {
			int hashVal = 0;

			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					hashVal = hashVal * 4 + board[i][j];

			return hashVal;
		}
	}

	public String positionString() {
		
		String boardStr = "";
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				String spot = "";
				switch (board[row][col]) {
				case TicTacToe1.HUMAN:
					spot = " " + "X" + " ";
					break;
				case TicTacToe1.COMPUTER:
					spot = " " + "O" + " ";
					break;
				case TicTacToe1.EMPTY:
					spot = "   ";
					break;
				default:
					System.out.println("Bad board entry");
				}
				boardStr = boardStr + spot;
				if (col < 2)
					boardStr = boardStr + "|";
			}
			if (row < 2)
				boardStr = boardStr + "\n-----------\n";
		}
		
		return boardStr;
	}
	

	// Find optimal move
	public Best chooseMove(int side, int depth) {
		int opp; // The other side
		Best reply; // Opponent's best reply
		int simpleEval; // Result of an immediate evaluation
		int bestRow = -1; // Initialize running value with out-of-range value
		int bestColumn = -1;
		int value;
		Position thisPosition = new Position(board);

		if ((simpleEval = positionValue()) != UNCLEAR)
			return new Best(simpleEval);
		
		// Don't look up top-level value: can't use it (already used for last move)
		if (depth > 0) {
			Integer lookupVal = store.get(thisPosition);
			if (lookupVal != null)
				return new Best(lookupVal);
		}
		// Initialize running values with out-of-range values (good software practice)
		// Here also to ensure that *some* move is chosen, even if a hopeless case
		if (side == COMPUTER) {
			opp = HUMAN;
			value = HUMAN_WIN - 1; // impossibly low value
		} else {
			opp = COMPUTER;
			value = COMPUTER_WIN + 1; // impossibly high value
		}

		for (int row = 0; row < 3; row++)
			for (int column = 0; column < 3; column++)
				if (squareIsEmpty(row, column)) {
					place(row, column, side);
					reply = chooseMove(opp, depth+1);
					place(row, column, EMPTY);
					// Update if side gets better position
					if (side == COMPUTER && reply.val > value || side == HUMAN && reply.val < value) {
						value = reply.val;
						bestRow = row;
						bestColumn = column;
					}
				}
		store.put(thisPosition, value);
		return new Best(value, bestRow, bestColumn);
	}

	// Play move, including checking legality
	public boolean playMove(int side, int row, int column) {
		if (row < -1 || row >= 3 || column < 0 || column >= 3 || board[row][column] != EMPTY)
			return false;
		board[row][column] = side;
		return true;
	}

	// Simple supporting routines
	public void clearBoard() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = EMPTY;
	}

	public boolean boardIsFull() {
		for (int row = 0; row < 3; row++)
			for (int column = 0; column < 3; column++)
				if (board[row][column] == EMPTY)
					return false;
		return true;
	}

	public boolean isAWin(int side) {
		int row, column;

		/* Look for all in a row */
		for (row = 0; row < 3; row++) {
			for (column = 0; column < 3; column++)
				if (board[row][column] != side)
					break;
			if (column >= 3)
				return true;
		}

		/* Look for all in a column */
		for (column = 0; column < 3; column++) {
			for (row = 0; row < 3; row++)
				if (board[row][column] != side)
					break;
			if (row >= 3)
				return true;
		}

		/* Look on diagonals */
		if (board[1][1] == side && board[2][2] == side && board[0][0] == side)
			return true;

		if (board[0][2] == side && board[1][1] == side && board[2][0] == side)
			return true;

		return false;
	}

	private int[][] board = new int[3][3];
	private Map<Position, Integer> store = new HashMap<Position, Integer>();
	
	// Play a move, possibly clearing a square
	private void place(int row, int column, int piece) {
		board[row][column] = piece;
	}

	private boolean squareIsEmpty(int row, int column) {
		return board[row][column] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue() {
		return isAWin(COMPUTER) ? COMPUTER_WIN : isAWin(HUMAN) ? HUMAN_WIN : boardIsFull() ? DRAW : UNCLEAR;
	}
}
