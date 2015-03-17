import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests for {@link State#initializeChildren()}
 * <p>
 * Part JUnit test but with print out to of information to console to
 * understand and eyeball check
 *  
 * @author Oliver Smart (MSc CS) & Daryl Smith (MSc IT)
 */
public class JUnitTestStateInitialiseChildren {

	@Test
	public void testEmptyBoard() {
		System.out.println("testEmptyBoard console output:");
		Board empty = new Board();
		Player red = Player.RED;
		// test states
		System.out.println("State testState = new State(red, empty_board, null):");
		State testState = new State(red,empty,null);
		System.out.println("now print testState:\n");
		System.out.println(testState);
		
		// initialize the children
		testState.initializeChildren();
		
		// would expect there to be 7 children for the empty board.
		// first with red in column 0, 2nd with red in column 1 ... 
		assertThat(testState.getChildren().length, is(7));
		
		System.out.println("success testState.initializeChildren() created 7 children\n"
				+ "testState is now " + testState);
		
		
	}


}