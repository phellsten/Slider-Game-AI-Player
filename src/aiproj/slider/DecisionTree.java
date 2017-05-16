package aiproj.slider;

import java.util.ArrayList;
import java.util.LinkedList;

import aiproj.slider.Move.Direction;

public class DecisionTree {
	public DecisionTree(Board board, String playerString) {
		// Start construction of DecisionTree, and its root nodes
		this.rootBoard = board;
		this.playerString = playerString;
		if (playerString.equals(VER_PLAYER)) {
			System.out.println("SKIP BRANCH GENERATION");
		}
		// Create a new root node
		this.rootNode = new DecisionNode();
		// Start calculation of possible moves.
	}

	// We can afford 3 ply toilet paper, unlike the University
	public static final int PLY_LENGTH = 5;
	public final String HOR_PLAYER = "H";
	public final String VER_PLAYER = "V";

	// Board the game is to be played on
	private Board rootBoard;

	/** Unprunes a node from the decision tree */
	private void unprune(DecisionNode nde) {
		recNodeExtension(nde);
	}

	/** Prunes a node from the decision tree */
	private void prune(DecisionNode nde) {
		nde.prune();
	}

	public Board getRootBoard() {
		return this.rootBoard;
	}

	public void debug() {
	}

	// The root node of the decision tree
	private DecisionNode rootNode;

	/** Fetches the root node of the tree */
	public DecisionNode getRootNode() {
		return rootNode;
	}

	// The string of the player
	String playerString;

	/** Calculates all possible moves from the initial board config */
	public void calculatePossibleMoves(String player) {
		// rootBoard.printDebug();
		calculateMoves(rootNode, playerString);
	}

	/** Extends node out. Used after a board move to make the new nodes */
	public void extendNodes() {
		// Find the bottom of each branch
		recNodeExtension(this.rootNode);
	}

	private void recNodeExtension(DecisionNode nde) {
		if (nde.getChildNodes().size() == 0) {
			// Have to create new nodes
			calculateMoves(nde, this.playerString);
			return;
		}
		// Recurse through each branch to find end
		for (DecisionNode child : nde.getChildNodes()) {
			recNodeExtension(child);
		}
	}

	/**
	 * Returns a reconstructed board from a list of moves and the original board
	 */
	public Board constructBoard(LinkedList<Move> moves) {
		Board newBoard = new Board(rootBoard);
		int i = 1;
		for (Move mve : moves) {
			if (mve == null) {
				continue;
			}
			int x = mve.i;
			int y = mve.j;
			Direction d = mve.d;

			String piece = newBoard.blocks[x][y];
			newBoard.blocks[x][y] = "+";
			if (d == Direction.UP) {
				y += 1;
			} else if (d == Direction.RIGHT) {
				x += 1;
			} else if (d == Direction.DOWN) {
				y -= 1;
			} else if (d == Direction.LEFT) {
				x -= 1;
			}
			if (x < newBoard.size && y < newBoard.size) {
				newBoard.blocks[x][y] = piece;
			}
			// System.out.println("*" + i + ": ");
			i++;
		}
		return newBoard;
	}

	/** Swaps the player strings around from H to V and vice versa */
	private String swapPlayer(String player) {
		if (player.equals(VER_PLAYER)) {
			return HOR_PLAYER;
		} else {
			return VER_PLAYER;
		}
	}

	/** Constructs a tree using the alpha Beta algorithm */
	public void alphaBetaConstruct(DecisionNode nde, String playerStr, double alpha, double beta) {
		// Detect terminal node
		if (nde.getMoves().size() + 1 >= PLY_LENGTH) {
			return;
		}
	}

	/** Calculates possible moves from a board specififed in board */
	private void boardCalculateMoves(Board board, String playerStr) {

	}

	private boolean alphaBetaPrune() {
		return false;
	}

	/**
	 * Calculates the moves for the board, and places them in the speicified
	 * node
	 */
	private void calculateMoves(DecisionNode node, String player) {
		int i;
		boolean moved = false;
		int j;
		// Check to see if the ply limit has been reached. If so don't process
		// the nodes
		int size = node.getMoves().size();
		if (size >= PLY_LENGTH) {
			System.out.println("(((Ply limit reached");
			return;
		}
		DecisionNode nde;

		// Now calculate the new board
		Board newBoard = constructBoard(node.getMoves());
		// newBoard.printDebug();
		for (j = 0; j < newBoard.size; j++) {
			for (i = 0; i < newBoard.size; i++) {
				if (newBoard.blocks[i][j].equals(player)) {
					// System.out.println("PLAYER FOUND");
					if (newBoard.isFree(i + 1, j, player)) {
						moved = true;
						if (node.getMoves().size() + 1 >= PLY_LENGTH) {
						} else {
							Move mve = new Move(i, j, Direction.RIGHT);

							// Create the new child node
							nde = addNewChildNode(mve, node);

							if (nde.getMoves().size() + 1 == PLY_LENGTH) {
								nde.setValue(getUtility(newBoard, player));
							}

							// Board cleared to reduce memory usage during
							// recursion
							newBoard = null;
							calculateMoves(nde, swapPlayer(player));
							newBoard = constructBoard(node.getMoves());
						}
					}
					if (newBoard.isFree(i, j + 1, player)) {
						moved = true;
						if (node.getMoves().size() + 1 < PLY_LENGTH) {
							Move mve = new Move(i, j, Direction.UP);
							nde = addNewChildNode(mve, node);
							if (nde.getMoves().size() + 1 == PLY_LENGTH) {
								// At final node, set its utility function value
								nde.setValue(getUtility(newBoard, player));
							}
							newBoard = null;
							calculateMoves(nde, swapPlayer(player));
							newBoard = constructBoard(node.getMoves());
						}
					}
					if (newBoard.isFree(i, j - 1, player)) {
						// only H can move down
						if (player == "H") {
							moved = true;
							if (node.getMoves().size() + 1 < PLY_LENGTH) {
								nde = addNewChildNode(new Move(i, j, Direction.DOWN), node);
								if (nde.getMoves().size() + 1 == PLY_LENGTH) {
									nde.setValue(getUtility(newBoard, player));
								}
								newBoard = null;
								calculateMoves(nde, swapPlayer(player));
								newBoard = constructBoard(node.getMoves());
							}
						}
					}
					if (newBoard.isFree(i - 1, j, player)) {
						// only V can move left
						if (player == "V") {
							moved = true;
							if (node.getMoves().size() + 1 < PLY_LENGTH) {
								nde = addNewChildNode(new Move(i, j, Direction.LEFT), node);
								if (nde.getMoves().size() + 1 == PLY_LENGTH) {
									// System.out.println("GETTING HEURISTIC
									// VALUE @ Size " + nde.getMoves().size());
									nde.setValue(getUtility(newBoard, player));
								}
								newBoard = null;
								calculateMoves(nde, swapPlayer(player));
								newBoard = constructBoard(node.getMoves());
							}
						}
					}

					// If we can't move insert a null move, to indicate a skip
					if (!moved) {
						// nde = newNode(null, node);
					}
				}
			}
		}
		// Code to perform Alpha beta pruning goes here
		//
		System.out.println("DONE");
		alphaBetaPrune(node, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, player);
		// Finished with node, possibly perform clean up
	}

	private double alphaBetaPrune(DecisionNode nde, double alpha, double beta, String curPlay) {
		if (nde.getMoves().size() + 1 >= PLY_LENGTH || nde.getChildNodes().size() == 0) {
			return nde.getValue();
		}
		if (curPlay.equals(this.playerString)) {
			// Maximize the player
			Double value = Double.NEGATIVE_INFINITY;
			for (DecisionNode chiNde : nde.getChildNodes()) {
				value = Double.max(value, alphaBetaPrune(chiNde, alpha, beta, swapPlayer(curPlay)));
				alpha = Double.max(alpha, value);
				if (beta <= alpha) {
					// Beta Cut off
					System.out.println("CUT OFF BOARD ");
					System.out.println(chiNde.getMoves().get(0));
				}
			}
			return value;
		} else {
			// Minimizing
			Double value = Double.POSITIVE_INFINITY;
			for (DecisionNode chiNode : nde.getChildNodes())
			{
				value = Double.min(value, alphaBetaPrune(chiNode, alpha, beta, swapPlayer(curPlay)));
				beta = Double.min(beta, value);
				if (beta <= alpha)
				{
					System.out.println("CUT OFF BOARD");
				}
			}
			return value;
		}
	}

	/**
	 * Creates the new child node, from the move specificed
	 */
	private DecisionNode addNewChildNode(Move move, DecisionNode parentNode) {
		DecisionNode newNode = new DecisionNode(parentNode);
		// Add the move in
		newNode.addMove(move);
		return newNode;
	}

	/**
	 * DecisionTree Moves the DecisionTree to the appropriate node, and
	 * recalcuate bottom values
	 */
	public int move(Move move) {
		if (move == null) {
			System.out.println("SKIP");
			return -1;
		}

		// Shift the parent node
		for (DecisionNode nde : rootNode.getChildNodes()) {
			if (!nde.getMoves().isEmpty()) {
				Move mve = nde.getMoves().get(0);
				System.out.println(mve);
				if (mve.i == move.i && move.j == mve.j && move.d == mve.d) {
					System.out.println("FOUND");
					this.rootNode = nde;
					this.rootBoard = constructBoard(nde.getMoves());
					System.out.println("NEW ROOT BOARD");
					rootBoard.printDebug();
					rootNode.getMoves().remove(0);
					removeRedundantMoves(rootNode);
					return 1;
				}
			}
		}
		System.out.println("NEW DEBUG BOARD");
		return 0;
	}

	/** Recurses down the tree to remove uneeded moves */
	private void removeRedundantMoves(DecisionNode nde) {
		for (DecisionNode childNode : nde.getChildNodes()) {
			childNode.getMoves().remove(0);
			removeRedundantMoves(childNode);
		}
	}

	public int getUtility(Board board, String player) {
		// System.out.println("UTILITY CALLED");
		int value = 0;
		// check if accessing correct
		int i, j, numH = 0, numV = 0, bonus = 0;
		for (i = 0; i < board.size; i++) {
			for (j = 0; j < board.size; j++) {

				if (player == "H") {
					// System.out.println("checking i = " + i + ", j = " + j +
					// ".. = " + board.blocks[i][j]);
					// System.out.println(player);

					if (board.blocks[i][j].equals("H")) {
						// System.out.println("H at " + i + "," + j + ", +=" +
						// i);
						value += i;
						numH++;

					} else if (board.blocks[i][j].equals("V")) {
						// System.out.println("V at " + i + "," + j + ", -=" +
						// j);
						value -= j;
						numV++;
					}

				} else {
					if (board.blocks[i][j].equals("V")) {
						// System.out.println("V at " + i + "," + j + ", +=" +
						// j);
						value += j;
						numV++;
					}
					if (board.blocks[i][j].equals("H")) {
						// System.out.println("V at " + i + "," + j + ", -=" +
						// i);
						value -= i;
						numH++;
					}
				}
			}
		}

		if (player == "V") {
			bonus += (board.size - numV - 1) * board.size;
			bonus -= (board.size - numH - 1) * board.size;
		} else if (player == "H") {

			bonus += (board.size - numH - 1) * board.size;
			bonus -= (board.size - numV - 1) * board.size;

		}
		value += bonus;

		return -value;
	}
}
