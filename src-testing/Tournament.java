/**
 * run tournaments of 100 or maybe 1000 games to check performance.
 *
 */
public class Tournament {

	/*
	 * Number of games to be played
	 */
	private static final int NGAMES = 300;

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("specify the depth as an integer arg 1...6?");
		} else {
			
			int depth = Integer.parseInt(args[0]);
			long startTime = System.currentTimeMillis();
			int nWinRed = 0;
			int nWinYellow = 0;
			int nDraw = 0;

			for (int gc = 0; gc < NGAMES; gc++) {
				Solver p1 = new Dummy(Player.RED);
				Solver p2 = new AI(Player.YELLOW, depth);
				// take turns who goes first to be fair
				Game game;
				if (gc % 2 == 0)
					game = new Game(p1, p2);
				else
					game = new Game(p2, p1);
				game.runGame();
				Player winner = game.getWinner();
				if (winner.equals(Player.RED))
					nWinRed++;
				else if (winner.equals(Player.YELLOW))
					nWinYellow++;
				else
					nDraw++;
			}
			// print to STDERR so we can discard STDOUT to /dev/null!
			System.err.println("\n\nResults ran " + NGAMES + " games, with RED depth " + depth);
			System.err.println("red won \t" + nWinRed + "\t games = \t"
					+ (100. * (double) nWinRed) / ((double) NGAMES) + " %");
			System.err.println("yellow won \t" + nWinYellow + "\t games = \t"
					+ (100. * (double) nWinYellow) / ((double) NGAMES) + " %");
			System.err.println("draw       \t" + nDraw + "\t games = \t"
					+ (100. * (double) nDraw) / ((double) NGAMES) + " %");
			double cpu = (double) (System.currentTimeMillis() - startTime) / 1000.;
			System.err.println("cpu " + cpu + " seconds");
		}

	}
}
