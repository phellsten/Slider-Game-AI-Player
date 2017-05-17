// Patrick Hudgell phudgell
// Paul Hellsten phellsten
package aiproj.slider;

import aiproj.slider.SliderPlayer;

import java.util.LinkedList;

import aiproj.slider.Move;

public class SliderPhai implements SliderPlayer {

	String player;
	Negamax nmax;
	DecisionTree tree;
	Board tmpBoard = null;
	public static final String VER_PLAYER = "V";
	public static final String HOR_PLAYER = "H";

	public SliderPhai() {

	}

	@Override
	public void init(int dimension, String board, char player) {
		Board nBoard = new Board(board, dimension);
		this.player = Character.toString(player);
		nmax = new Negamax();
		// Detect second player
		if (this.player.equals(VER_PLAYER)) {
			tree = null;
			tmpBoard = nBoard;
		} else {
			tree = new DecisionTree(nBoard, HOR_PLAYER);
			tree.calculatePossibleMoves(this.player);

		}
	}

	boolean firstMove = true;

	/** Updates the other oppoent */
	@Override
	public void update(Move move) {
		// current board representation : board;
		// opponents move: move;
		// opponent moved piece board[move.i][move.j] in direction move.d
		if (move == null) {
			// Oppoent hasn't made a move
			// Board stays the same
			// However still make it our board at the root
			if (firstMove) {
				tree.move(move);
			} else {
				tree = new DecisionTree(tree.getRootBoard(), this.player);
				tree.calculatePossibleMoves(this.player);
			}
			return;
		}
		firstMove = false;

		// Handle the case if we are going second
		if (tree == null) {
			// Update the physical game board
			tmpBoard.movePiece(move.i, move.j, move.d);
			// Make the decision tree
			tree = new DecisionTree(tmpBoard, VER_PLAYER);
			tree.calculatePossibleMoves(this.player);

			// Clear it for the Garbage Collector
			tmpBoard = null;
		} else {
			if (tree.move(move) == 0) {
				LinkedList<Move> test = new LinkedList<>();
				test.add(move);
				Board newBoard = tree.constructBoard(test);
				if (this.player.equals(HOR_PLAYER)) {
					this.tree = new DecisionTree(newBoard, HOR_PLAYER);

				} else {
					this.tree = new DecisionTree(newBoard, VER_PLAYER);

				}
				tree.calculatePossibleMoves(player);
				// tree.extendNodes();
			}
			tree.extendNodes();
		}
	}

	/** Makes a move for ourself */
	@Override
	public Move move() {
		try {
			Move bestMove = nmax.getBestMove(tree);
			tree.move(bestMove);
			return bestMove;
		} catch (Exception e) {
			// No Move to make
			return null;
		}
	}

}
