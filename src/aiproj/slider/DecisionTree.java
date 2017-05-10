package aiproj.slider;

import java.util.ArrayList;

public class DecisionTree {
	private class decisionNode {
		// Value calculated by MiniMax
		private int value;
		
		// The children of the node
		ArrayList<decisionNode> childNodes;

		// The move that results in this value
		private Move move;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Move getMove() {
			return move;
		}

		public void setMove(Move move) {
			this.move = move;
		}
	}

	// We can afford 3 ply toilet paper, unlike the University
	public static final int PLY_LENGTH = 3;

	// Board the game is to be played on
	private Board board;

	// The root node of the decision tree
	private decisionNode rootNode;
	
	// The string of the player
	String playerString;

	DecisionTree(Board board, String playerString) {
		// Start construction of DecisionTree, and its root nodes
		this.board = board;
		this.playerString = playerString;
		// Start calculation of possible moves.
		calculateMoves(board, rootNode, this.playerString);
	}

	/** Creates a new simulated move */

	/**
	 * Calculates the moves for the board, and places them in the speicified
	 * node
	 */
	private void calculateMoves(Board board, decisionNode node, String player) {

		int i;
		int j;
		int numLegalH = 0;
		int numLegalV = 0;

		// For each piece on the board
		for (j = 0; j < board.size; j++) {
			for (i = 0; i < board.size; i++) {
				// If piece is 'H'
				if (board.blocks[i][j].equals(player)) {
					if (board.isFree(i + 1, j, "H")) {
						// Create a new node with the move
						
						
						// H can move right
						numLegalH++;
					}
					if (board.isFree(i, j - 1, player)) {
						// H can move up
						numLegalH++;
					}
					if (board.isFree(i, j + 1, player)) {
						// H can move down
						numLegalH++;
					}
				}
			}
		}
	}

	/**
	 * Moves the DecisionTree to the appropriate node, and recalcuate bottom
	 * values
	 */
	public void move(Move move) {

	}
}
