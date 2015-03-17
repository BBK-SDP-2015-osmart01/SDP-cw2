import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Additonal tests for AI.minmax
 * <p>
 * Just printing out information to console and eyeball check that values
 * correctly go up tree.
 * 
 */
public class JUnitTestAIMinMax {

	/**
     * board will first two columns free yellow. Yellow can win if next 
     * turn by playing column 1, Red must block 
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
		System.out.println("testYellowToWin console output:");
		State tstate = new State(Player.YELLOW, board2w, null);
		AI.createGameTree(tstate, 3);
		AI.minimax(new AI(Player.YELLOW, 3), tstate);
		System.out.println(tstate);
	}

	@Test
	public void testRedMustBlockYellow() {
		System.out.println("testRedMustBlockYellow console output:");
		State tstate = new State(Player.RED, board2w, null);
		AI.createGameTree(tstate, 2);
		AI.minimax(new AI(Player.RED, 2), tstate);
		System.out.println(tstate);
	}



}