
import java.util.ArrayList;
import java.util.List;

/**
 * An instance represents a Solver that intelligently determines
 * Moves using the Minimax algorithm.
 */
public class AI implements Solver {

    private Player player; // the current player

    /**
     * The depth of the search in the game space when evaluating moves.
     */
    private int depth;

    /**
     * Constructor: an instance with player p who searches to depth d
     * when searching the game space for moves.
     */
    public AI(Player p, int d) {
        player = p;
        depth = d;
    }

    /**
     *  {@inheritDoc}
     *  
     * See Solver.getMoves for the specification.
     * 
     * @author Daryl Smith and Oliver Smart
     */
    @Override
	public Move[] getMoves(Board b) {
		// setup the move tree for this board
		State moveState = new State(player, b, null);
		createGameTree(moveState, depth);
		// then determine the best possible move(s)
		minimax(moveState);
		// moveState.getValue() is the value of the best move(s). 
		// need to select it or them.

		// go through children
		List<Move> myMoves = new ArrayList<Move>();
		for (State child : moveState.getChildren()) {
			if (child.getValue() == moveState.getValue()) {
				myMoves.add(child.getLastMove());
			}
		}
		return myMoves.toArray(new Move[0]);
	}

    /**
     * Generate the game tree with root s of depth d.
     * The game tree's nodes are State objects that represent the state of a game
     * and whose children are all possible States that can result from the next move.
     * <p/>
     * NOTE: this method runs in exponential time with respect to d.
     * With d around 5 or 6, it is extremely slow and will start to take a very
     * long time to run.
     * <p/>
     * Note: If s has a winner (four in a row), it should be a leaf.
     * 
     * @author Daryl Smith and Oliver Smart 
     */
	public static void createGameTree(State s, int d) {
		// Note: This method must be recursive, recurse on d,
		// which should get smaller with each recursive call
		// d = 0: return
		if (d == 0)
			return;

		// d > 0: recurse making d small
		s.initializeChildren();
		for (State itS : s.getChildren()) {
			createGameTree(itS, d - 1);
		}
	}
  
    /**
     * Call minimax in ai with state s.
     */
    public static void minimax(AI ai, State s) {
        ai.minimax(s);
    }

    /**
     * State s is a node of a game tree (i.e. the current State of the game).
     * Use the Minimax algorithm to assign a numerical value to each State of the
     * tree rooted at s, indicating how desirable that java.State is to this player.
     * 
     * @author Daryl Smith and Oliver Smart
     */
	public void minimax(State s) {
		// if this is a leaf (no children) evaluate the board and ret
		if (s.getChildren().length == 0) {
			s.setValue(evaluateBoard(s.getBoard()));
			return;
		}

		// not a leaf so get the minimum or maximum value from the child nodes.

		// if s has children, recurse through child nodes, calling minimax and
		// picking the best value of the child nodes
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (State child : s.getChildren()) {
			minimax(child);
			int value = child.getValue();
			max = Math.max(max, value);
			min = Math.min(min, value);
		}

		// set best value of s based on who is to play
		if (s.getPlayer() == player) {
			s.setValue(max);
		} else {
			s.setValue(min);
		}
	}

    /**
     * Evaluate the desirability of Board b for this player
     * Precondition: b is a leaf node of the game tree (because that is most
     * effective when looking several moves into the future).
     */
    public int evaluateBoard(Board b) {
        Player winner = b.hasConnectFour();
        int value = 0;
        if (winner == null) {
            // Store in sum the value of board b. 
            List<Player[]> locs = b.winLocations();
            for (Player[] loc : locs) {
                for (Player p : loc) {
                    value += (p == player ? 1 : p != null ? -1 : 0);
                }
            }
        } else {
            // There is a winner
            int numEmpty = 0;
            for (int r = 0; r < Board.NUM_ROWS; r = r + 1) {
                for (int c = 0; c < Board.NUM_COLS; c = c + 1) {
                    if (b.getTile(r, c) == null) numEmpty += 1;
                }
            }
            value = (winner == player ? 1 : -1) * 10000 * numEmpty;
        }
        return value;
    }
}
