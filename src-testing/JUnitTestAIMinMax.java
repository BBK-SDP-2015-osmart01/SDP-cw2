import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Additional tests for AI.minmax
 * <p>
 * Start just printing out information to console and eyeball check that values
 * correctly go up tree.
 * 
 * Add junit asserts once behaviour is established.
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
		// JUnit check for normal behaviour, after eyeball
		assertTrue("\nyellow winning next move should result in a large +ve value\n"
				+ "but tstate.getValue()= "+ tstate.getValue(),  tstate.getValue()>1000);
	}

	@Test
	public void testRedMustBlockYellow() {
		System.out.println("testRedMustBlockYellow console output:");
		State tstate = new State(Player.RED, board2w, null);
		AI.createGameTree(tstate, 2);
		AI.minimax(new AI(Player.RED, 2), tstate);
		System.out.println(tstate);
		assertTrue("\nyellow blocking next move should result in a small -ve value\n"
				+ "but tstate.getValue()= "+ tstate.getValue(),  tstate.getValue()<0);
	}



}