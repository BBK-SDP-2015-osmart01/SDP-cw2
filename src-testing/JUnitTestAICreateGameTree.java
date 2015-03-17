import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests for {@link AI#createGameTree}
 * <p>
 * Part JUnit test but with print out to of information to console to understand
 * and eyeball check
 * 
 */
public class JUnitTestAICreateGameTree {

	/**
	 * to keep tree manageable work with a board with the first two columns
	 * empty but the last 5 filled with a "drawing pattern".
	 */
	private Board board2e = new Board();

	@Before
	public void init() {
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2e,
				Player.YELLOW, 2);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2e,
				Player.YELLOW, 3);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2e,
				Player.RED, 4);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2e,
				Player.RED, 5);
		JUnitTestBoardGetPossibleMoves.fillColumnAlternating(board2e,
				Player.YELLOW, 6);
	}

	@Test
	public void testTwoColsEmpty() {
		System.out.println("testTwoColsEmpty console output:");
		System.out.println("board with two columns empty:\n");
		System.out.println(board2e);
		State tstate = new State(Player.YELLOW, board2e, null);
		System.out.println("tstate before calling AI.createGameTree:\n");
		System.out.println(tstate);

		// create tree depth 1 - there should be two children
		AI.createGameTree(tstate, 1);
		assertThat("AI.createGameTree(tstate,1) should create two children",
				tstate.getChildren().length, is(2));

		System.out
				.println("Good AI.createGameTree(tstate,1) created two children:\n");
		System.out.println(tstate);

	}

}