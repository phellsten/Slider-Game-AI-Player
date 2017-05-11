package aiproj.slider;

import java.util.ArrayList;
import java.util.LinkedList;

import aiproj.slider.Move.Direction;

public class DecisionTree {
	private class decisionNode {
		// Value calculated by MiniMax
		private int value;

		private decisionNode parentNode;

		/** Constructor for the root node */
		decisionNode() {
			this.parentNode = null;
		}

		/**
		 * Constructor for a child node, with parent node taken as an argumenet
		 */
		decisionNode(decisionNode parentNode) {
			this.parentNode = parentNode;
		}

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
		// Create a new root node
		rootNode = new decisionNode();
		// Start calculation of possible moves.
	}

	public void addMoveToTree(Move move, String Player, decisionNode node) {

	}

	/**
	 * Returns a reconstructed board from a list of moves and the original board
	 */
	public Board constructBoard(Board currentboard, LinkedList<Move> moves) {
		Board newBoard = new Board(currentboard);

		LinkedList<Move> newMoves = new LinkedList<>(moves);
		while (!moves.isEmpty()) {
			Move nextMove = newMoves.pop();

			if (nextMove == null) {
				continue;
			}

			int x = nextMove.i;
			int y = nextMove.j;
			Direction d = nextMove.d;

			String piece = newBoard.blocks[x][y];
			newBoard.blocks[x][y] = "+";
			if (d == Direction.UP) {
				newBoard.blocks[x][y + 1] = piece;
			} else if (d == Direction.RIGHT) {
				newBoard.blocks[x + 1][y] = piece;
			} else if (d == Direction.DOWN) {
				newBoard.blocks[x][y - 1] = piece;
			} else if (d == Direction.LEFT) {
				newBoard.blocks[x - 1][y] = piece;
			}

		}

		return newBoard;
	}

	/** Creates a new simulated move */

	/**
	 * Calculates the moves for the board, and places them in the speicified
	 * node
	 */
	private void calculateMoves(Board board, decisionNode node, String player) {

		int i;
		int j;
		// int numLegalH = 0;
		// int numLegalV = 0;

		// For each piece on the board
		for (j = 0; j < board.size; j++) {
			for (i = 0; i < board.size; i++) {
				if (board.blocks[i][j].equals(player)) {
					if (board.isFree(i + 1, j, player)) {
						// H can move right
						// V can move right
						addMoveToTree(new Move(i + 1, j, Direction.RIGHT), player, node);
					}
					if (board.isFree(i, j + 1, player)) {
						// H can move up
						// V can move up
						addMoveToTree(new Move(i, j + 1, Direction.UP), player, node);

					}
					if (board.isFree(i, j - 1, player)) {
						// only H can move down
						if (player == "H") {
							addMoveToTree(new Move(i, j - 1, Direction.DOWN), player, node);
						}

					}
					if (board.isFree(i - 1, j, player)) {
						// only V can move left
						if (player == "V") {
							addMoveToTree(new Move(i - 1, j, Direction.LEFT), player, node);
						}
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
