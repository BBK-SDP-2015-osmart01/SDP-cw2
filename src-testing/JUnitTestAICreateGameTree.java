import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

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

		System.out
				.println("OK lets try going to depth of 2 this should create:");
		System.out.println("   depth 1: two children");
		System.out.println("   depth 2: 2*2 = 4 children");

		// get minimax to fill in values for yellow
		AI.createGameTree(tstate, 2);
		AI.minimax(new AI(Player.YELLOW, 2), tstate);
		assertThat(
				"minimax unlikely to have propogated value zero to the head",
				tstate.getValue(), not(0));

		System.out.println("Check following output by eyeball:\n");
		System.out.println(tstate);

	}

	@Test
	public void checkThatWinnerIsALeaf() {
		// createGameTree specification has an additional instruction
		// "Note: If s has a winner (four in a row), it should be a leaf."
		// so lets create a winning board
		Board winner = board2e;
		winner.makeMove(new Move(Player.YELLOW, 0));
		winner.makeMove(new Move(Player.YELLOW, 1)); // yellow has now won
		// red can play next, does not matter
		State winnerState = new State(Player.RED, winner, null);
		// Create tree of this state
		AI.createGameTree(winnerState, 4);
		assertThat("winnerState should have no children it should be a leaf!",
				winnerState.getChildren().length, is(0));
		System.out.println("checkThatWinnerIsALeaf passes no children:\n");
		System.out.println(winnerState);

	}

}