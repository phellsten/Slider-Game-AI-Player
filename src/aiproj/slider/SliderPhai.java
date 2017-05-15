package aiproj.slider;

import aiproj.slider.SliderPlayer;

import java.util.LinkedList;

import aiproj.slider.Move;

public class SliderPhai implements SliderPlayer {

	String player;
	Negamax nmax;
	DecisionTree tree;
	Board tmpBoard = null;

	public SliderPhai() {

	}

	@Override
	public void init(int dimension, String board, char player) {
		Board nBoard = new Board(board, dimension);
		this.player = Character.toString(player);
		nmax = new Negamax();
		// Detect second player
		if (player == 'V') {
			tree = null;
			tmpBoard = nBoard;
		} else {
			// System.out.println(this.player);
			tree = new DecisionTree(nBoard, "H");
			tree.calculatePossibleMoves(this.player);

			// Test the moves

		}
	}

	boolean firstMove = true;

	/** Updates the other oppoent */
	@Override
	public void update(Move move) {
		System.out.println("PLAYER " + player + " UPDATING");
		// current board representation : board;
		// opponents move: move;
		// opponent moved piece board[move.i][move.j] in direction move.d
		// new board representation:...
		if (move == null) {
			// Oppoent hasn't made a move
			// Board stays the same
			// However still make it our board at the root
			System.out.println("^^^^^^^ NULL MOVE ^^^^");
			if (firstMove) {
				tree.move(move);
				firstMove = false;
			} else {
				System.out.println("RECONSTRUCTING " + this.player);
				tree = new DecisionTree(tree.getRootBoard(), this.player);
				tree.calculatePossibleMoves(this.player);
			}
			return;
		}

		// Handle the case if we are going second
		if (tree == null) {
			// Update the physical game board
			tmpBoard.movePiece(move.i, move.j, move.d);
			// Make the decision tree
			tree = new DecisionTree(tmpBoard, "V");
			tree.calculatePossibleMoves(this.player);

			// tree.getRootBoard().printDebug();
			// Clear it for the Garbage Collector
			tmpBoard = null;
		} else {
			System.out.println("BEFORE UPDATE");
			tree.getRootBoard().printDebug();
			System.out.println("POSSIBLE MOVES ");
			for (DecisionNode chd : tree.getRootNode().getChildNodes()) {
				for (Move mve : chd.getMoves()) {
					System.out.println(mve);
				}
			}
			// Ordinary move
			System.out.println("APPLYING MOVE " + move);

			if (tree.move(move) == 0) {
				System.out.println("OH NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				LinkedList<Move> test = new LinkedList<>();
				test.add(move);
				Board newBoard = tree.constructBoard(test);
				if (this.player.equals("H")) {
					this.tree = new DecisionTree(newBoard, "H");

				} else {
					this.tree = new DecisionTree(newBoard, "V");

				}
				tree.calculatePossibleMoves(player);
				// tree.extendNodes();

				int i = 1;
				System.out.println("Moves we can make: ");
				for (DecisionNode m : tree.getRootNode().childNodes) {
					System.out.println(i + ": " + m.getMoves() + ": " + m.getValue());
					i++;
				}

			}
			tree.extendNodes();
			System.out.println("PRINTING DEBUG TREE");
			tree.getRootBoard().printDebug();
			System.out.println("TREE SZE " + tree.getRootNode().getChildNodes().size());
		}
	}

	/** Makes a move for ourself */
	@Override
	public Move move() {
		System.out.println("PLAYER " + player + " IS MAKING MOVE");
		try {
			int i = 1;
			System.out.println("Moves we can make: ");
			for (DecisionNode m : tree.getRootNode().childNodes) {
				System.out.println(i + ": " + m.getMoves() + ": " + m.getValue());
				i++;
			}
			Move bestMove = nmax.getBestMove(tree);
			System.out.println("OUR Best move is: " + bestMove);
			tree.move(bestMove);
			return bestMove;
		} catch (Exception e) {

			System.out.println("ERROR  BOARD MOVE");
			tree.getRootBoard().printDebug();

			return null;
		}
	}

}
