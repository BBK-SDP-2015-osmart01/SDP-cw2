import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * JUnit tests for {@link Board#getPossibleMoves(Player)}
 * <p>
 * Note that as the tested Class is in default package JUnit tests have to be in
 * default as well! Split into separate source directory is all that can be
 * done.
 * 
 * @author Oliver Smart (MSc CS) & Daryl Smith (MSc IT)
 */
public class JUnitTestBoardGetPossibleMoves {

	@Test
	public void testEmptyBoard() {
		Board empty = new Board();
		Player red = Player.RED;
		// get the moves
		Move possibleMoves[] = empty.getPossibleMoves(red);
		// would expected
		Move expectedMoves[] = new Move[] { new Move(red, 0), new Move(red, 1),
				new Move(red, 2), new Move(red, 3), new Move(red, 4),
				new Move(red, 5), new Move(red, 6) };
		assertThat("\nempty board\n" + empty
				+ "So getPossibleMoves should return all 7 columns.",
				possibleMoves, is(expectedMoves));
	}

	@Test
	public void testFillEvenCols() {
		Board board = new Board();
		Player plr = Player.YELLOW;
		// fill up 0,2,4,6 with alternating red, yellow
		for (int cc = 0; cc < Board.NUM_COLS; cc++) {
			if (cc % 2 == 0) // even
				fillColumnAlternating(board, plr, cc);
		}
		Move possibleMoves[] = board.getPossibleMoves(plr);
		// should get back odd columns
		Move expectedMoves[] = new Move[] { new Move(plr, 1), new Move(plr, 3),
				new Move(plr, 5) };
		assertThat("\nfill even columns\n" + board
				+ "So getPossibleMoves should return odd columns.",
				possibleMoves, is(expectedMoves));
	}

	/**
	 * test a board where someone has already won. According to the method
	 * specification:
	 * <p>
	 * If the board has a winner (four things of the same colour in a row), no
	 * move is possible because the game is over. Specification says that
	 * result should be empty array.
	 */
	@Test
	public void testBoardWhereRedHasWon() {
		Board board = new Board();
		Player red = Player.RED;
		// red has cheated and played four into column 1
		board.makeMove(new Move(red, 1));
		board.makeMove(new Move(red, 1));
		board.makeMove(new Move(red, 1));
		board.makeMove(new Move(red, 1));
		System.out.println("check board where red has cheated and won:\n"
				+ board + "\n");
		Move possibleMoves[] = board.getPossibleMoves(red);
		Move expectedMoves[] = new Move[0]; // spec says empty
		assertThat(
				"\nRed has cheated and won\n"
						+ board
						+ "Specification says getPossibleMoves should return empty array.",
				possibleMoves, is(expectedMoves));
	}

	/**
	 * fill a board with a non-winning pattern
	 */
	@Test
	public void testDrawBoard() {
		Board board = new Board();
		Player plr = Player.YELLOW;
		fillBoardDraw(board);
		// check that worked by printing it out
		System.out.println("check board completely filled to a draw:\n" + board
				+ "\n");
		Move possibleMoves[] = board.getPossibleMoves(plr);
		Move expectedMoves[] = new Move[0]; // empty array it is full
		assertThat(
				"\nFilled board with no winner.\n"
						+ board
						+ "Specification says getPossibleMoves should return empty array.",
				possibleMoves, is(expectedMoves));
	}

	/**
	 * fills board with alternating colors from a given start
	 * 
	 * @param b
	 *            the board
	 * @param p
	 *            the first color to place
	 * @param col
	 *            the column to fill.
	 */
	public static void fillColumnAlternating(Board b, Player p, int col) {
		for (int i = 0; i < Board.NUM_ROWS; i++) {
			if (i % 2 == 0) {
				b.makeMove(new Move(p, col));
			} else {
				b.makeMove(new Move(p.opponent(), col));
			}
		}
	}

	/**
	 * fill a board to a draw (I think)
	 * 
	 * @param board
	 */
	public static void fillBoardDraw(Board board) {
		// think that there is a draw if one fills with alternating
		// columns so a row is |R|R|Y|Y|R|R|Y|
		fillColumnAlternating(board, Player.RED, 0);
		fillColumnAlternating(board, Player.RED, 1);
		fillColumnAlternating(board, Player.YELLOW, 2);
		fillColumnAlternating(board, Player.YELLOW, 3);
		fillColumnAlternating(board, Player.RED, 4);
		fillColumnAlternating(board, Player.RED, 5);
		fillColumnAlternating(board, Player.YELLOW, 6);
	}

}