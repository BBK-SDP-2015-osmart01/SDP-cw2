import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests on AI.getMoves(board) proper Unit tests so far
 */
public class JUnitTestAIGetMoves {

	/**
	 * board will first two columns free yellow. Yellow can win if next turn by
	 * playing column 1, Red must block
	 */
	private Board board2w = new Board();

	@Before
	public void init() {
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2w,
				Player.YELLOW, 2);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2w,
				Player.YELLOW, 3);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2w,
				Player.YELLOW, 4);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2w,
				Player.RED, 5);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2w,
				Player.RED, 6);
	}

	@Test
	public void testYellowToWin() {
		// should be able to work out how to win with depth 1
		AI ai = new AI(Player.YELLOW, 1);
		Move getMoves[] = ai.getMoves(board2w);
		Move expectedMoves[] = { new Move(Player.YELLOW, 1) };
		assertThat("\nboard is: \n" + board2w
				+ "so yellow can win by playing column 1.\n"
				+ "but getMoves() ....", getMoves,
				is(expectedMoves));
	}

	@Test
	public void testRedMustBlockYellow() {
		// should be able to work out how to block with depth 2?
		AI ai = new AI(Player.RED, 1);
		Move getMoves[] = ai.getMoves(board2w);
		Move expectedMoves[] = { new Move(Player.RED, 1) };
		assertThat("\nboard is: \n" + board2w
				+ "so red must block by playing column 1.\n"
				+ "but getMoves() ....", getMoves,
				is(expectedMoves));
	}

}