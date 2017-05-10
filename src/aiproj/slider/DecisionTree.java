package aiproj.slider;

public class DecisionTree {
	private class decisionNode {
		// Value calculated by MiniMax
		int value;
		// The move that results in this value
		Move move;
	}

	// We can afford 3 ply toilet paper, unlike the University
	public static final int PLY_LENGTH = 3;
	
	// Board the game is to be played on
	private Board board;

	DecisionTree(Board board) {
		// Start construction of DecisionTree, and its root nodes
		this.board = board;
		
		// Start calculation of possible moves.
		
	}
	
	private void calculateMoves(Board board)
	{
		
	}
	
	/**
	 * Moves the DecisionTree to the appropriate node, and recalcuate bottom
	 * values
	 */
	public void move(Move move) {

	}
}
