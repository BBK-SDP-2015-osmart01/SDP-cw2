import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * JUnit tests for {@link Board#getPossibleMoves(Player)}
 * <p>
 * Note that as the tested Class in default package JUnit tests have to be in
 * default too.
 * 
 * @author Oliver Smart (MSc CS) & Daryl Smith (MSc IT)
 */
public class JUnitTestBoardGetPossibleMoves {

	@Test
	public void testEmptyBoard() {
		Board empty = new Board();
		System.out.println("check empty board :\n" + empty + "\n");
		Player red = Player.RED;
		// get the moves
		Move possibleMoves[] = empty.getPossibleMoves(red);
		// would expected
		Move expectedMoves[] = new Move[] { new Move(red, 0), new Move(red, 1),
				new Move(red, 2), new Move(red, 3), new Move(red, 4),
				new Move(red, 5), new Move(red, 6) };
		assertThat(
				"\nall 7 columns should be available for move in empty board",
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
		// check that worked by printing it out
		System.out.println("check board where even columns filled:\n" + board
				+ "\n");

		Move possibleMoves[] = board.getPossibleMoves(plr);
		// should get back odd columns
		Move expectedMoves[] = new Move[] { new Move(plr, 1), new Move(plr, 3),
				new Move(plr, 5) };
		assertThat("\nfilled even columns, odds should be available.",
				possibleMoves, is(expectedMoves));
	}

	/**
	 * test a board where someone has won.
	 */
	
	
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
		Move expectedMoves[] = new Move[0]; // empty array
		assertThat(
				"\nDrawn filled board: 'if all columns are full, return an array of length 0.'",
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