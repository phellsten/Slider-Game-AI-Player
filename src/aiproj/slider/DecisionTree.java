package aiproj.slider;

import java.util.ArrayList;

public class DecisionTree {
	private class decisionNode {

		// Value calculated by MiniMax
		private int value;

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

		ArrayList <decisionNode> childNodes;
	}
	
	// We can afford 3 ply toilet paper, unlike the University
	public static final int PLY_LENGTH = 3;

	// Board the game is to be played on
	private Board board;

	// The root node of the decision tree
	private decisionNode rootNode;

	DecisionTree(Board board) {
		// Start construction of DecisionTree, and its root nodes
		this.board = board;

		// Start calculation of possible moves.
		calculateMoves(board, rootNode);
	}

	/**
	 * Calculates the moves for the board, and places them in the speicified
	 * node
	 */
	private void calculateMoves(Board board, decisionNode node) {

	}

	/**
	 * Moves the DecisionTree to the appropriate node, and recalcuate bottom
	 * values
	 */
	public void move(Move move) {

	}
}
