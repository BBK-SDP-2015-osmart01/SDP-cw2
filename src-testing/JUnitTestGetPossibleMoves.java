import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class JUnitTestGetPossibleMoves {

	@Test
	public void testEmptyBoard() {
		Board empty = new Board();
		// get the moves
		Move possibleMoves[] = empty.getPossibleMoves(Player.RED);
		assertThat("all columns available for move in empty board",
				possibleMoves.length, is(Board.NUM_COLS));
	}

}
