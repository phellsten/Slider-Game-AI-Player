package aiproj.slider;

import java.util.ArrayList;
import java.util.LinkedList;

import aiproj.slider.Move.Direction;

public class DecisionTree {
	public DecisionTree(Board board, String playerString) {
		// Start construction of DecisionTree, and its root nodes
		this.rootBoard = board;
		this.playerString = playerString;
		// Create a new root node
		this.rootNode = new DecisionNode();
		// Start calculation of possible moves.
	}

	// We can afford 3 ply toilet paper, unlike the University
	public static final int PLY_LENGTH = 3;
	public final String HOR_PLAYER = "H";
	public final String VER_PLAYER = "V";

	// Board the game is to be played on
	private Board rootBoard;

	// The root node of the decision tree
	private DecisionNode rootNode;

	// The string of the player
	String playerString;

	/** Calculates all possible moves from the initial board config */
	public void calculatePossibleMoves(String player) {
		calculateMoves(rootNode, playerString);
	}

	/**
	 * Returns a reconstructed board from a list of moves and the original board
	 */
	public Board constructBoard(LinkedList<Move> moves) {
		System.out.println("Reconstructing Board");
		for (Move mve : moves)
		{
			System.out.println("MOVE " + mve.i + " " + mve.j + " "+ mve.d);
		}
		Board newBoard = new Board(rootBoard);
		int i=1;
		for (Move mve : moves)
		{
			if (mve == null) {
				continue;
			}
			int x = mve.i;
			int y = mve.j;
			Direction d = mve.d;
			
			String piece = newBoard.blocks[x][y];
			newBoard.blocks[x][y] = "+";
			if (d == Direction.UP) {
				y+=1;
			} else if (d == Direction.RIGHT) {
				x+=1;
			} else if (d == Direction.DOWN) {
				y-=1;
			} else if (d == Direction.LEFT) {
				x-=1;
			}
			if(x < newBoard.size && y < newBoard.size) {
				newBoard.blocks[x][y] = piece;
			}
			System.out.println("*"+i+": ");
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
	
	/**
	 * Calculates the moves for the board, and places them in the speicified
	 * node
	 */
	private void calculateMoves(DecisionNode node, String player) {
		int i;
		int j;
		// Check to see if the ply limit has been reached. If so don't process
		// the nodes		
		
		if (node.getMoves().size() >= PLY_LENGTH) {
			System.out.println("Ply limit reached");
			return;
		}
		DecisionNode nde;
		
		// Now calculate the new board
		Board newBoard = constructBoard(node.getMoves());

		for (j = 0; j < newBoard.size; j++) {
			for (i = 0; i < newBoard.size; i++) {
				if (newBoard.blocks[i][j].equals(player)) {
					boolean moved = false;
					if (newBoard.isFree(i + 1, j, player)) {
						System.out.println("Position " + i + " " + j + " Can Move Right");
						Move mve = new Move(i, j, Direction.RIGHT);
						nde = newNode(mve, node);
						// Print the new board
						newBoard.printDebug();
						// Perform recursion on the new node
						moved = true;
						if (nde.getMoves().size() == PLY_LENGTH)
						{
							return;
						}
						newBoard = null;
						calculateMoves(nde, swapPlayer(player));
						newBoard = constructBoard(node.getMoves());
					}
					if (newBoard.isFree(i, j + 1, player)) {
						moved = true;
						Move mve = new Move(i, j, Direction.UP);
						nde = newNode(mve, node);
						System.out.println("Position " + i + " " + j + " Can Move Up");
						newBoard.printDebug();
						if (nde.getMoves().size() == PLY_LENGTH)
						{
							return;
						}
						newBoard = null;
						calculateMoves(nde, swapPlayer(player));
						newBoard = constructBoard(node.getMoves());
					}
					if (newBoard.isFree(i, j - 1, player)) {
						// only H can move down
						if (player == "H") {
							moved = true;
							System.out.println("Position " + i + " " + j + " Can Move Down");
							nde = newNode(new Move(i, j, Direction.DOWN), node);
							newBoard.printDebug();
							if (nde.getMoves().size() == PLY_LENGTH)
							{
								return;
							}
							newBoard = null;
							calculateMoves(nde, swapPlayer(player));
							newBoard = constructBoard(node.getMoves());
						} 
					}
					if (newBoard.isFree(i - 1, j, player)) {
						// only V can move left
						if (player == "V") {
							moved = true;
							System.out.println("Position " + i + " " + j + " Can Move Left");
							nde = newNode(new Move(i, j, Direction.LEFT), node);
							newBoard.printDebug();
							if (nde.getMoves().size() == PLY_LENGTH)
							{
								return;
							}
							newBoard = null;
							calculateMoves(nde, swapPlayer(player));
							newBoard = constructBoard(node.getMoves());
						}
					}

					// If we can't move insert a null move, to indicate a skip
					if (!moved) {
						// Create the null move to represent a skip
						nde = newNode(null, node);
						return;
					}
				}
			}
		}
		// Finished with node, possibly perform clean up
	}

	/**
	 * defined parent node
	 */
	private DecisionNode newNode(Move move, DecisionNode parentNode) {
		DecisionNode newNode = new DecisionNode(parentNode);
		// Add the move in
		newNode.addMove(move);
		return newNode;
	}

	/**DecisionTree
	 * Moves the DecisionTree to the appropriate node, and recalcuate bottom
	 * values
	 */
	public void move(Move move) {

	}

	private int getUtility(Board board, String player) {

		int value = 0;
		// check if accessing correct
		int i, j, numH=0, numV=0, bonus=0;
		for (i = 0; i < board.size; i++) {
			for (j = 0; j < board.size; j++) {
				if (board.blocks[i][j] == "H" && player == "H") {
					value += i;
					numH++;
				} else if (board.blocks[i][j] == "V" && player == "V") {
					value += j;
					numV++;
				} else if (board.blocks[i][j] == "H" && player == "V") {
					value -= i;
					numH++;
				} else if (board.blocks[i][j] == "V" && player == "H") {
					value -= j;
					numV++;
				}
			}
		}
		if(player == "V") {
			bonus += (board.size - numV - 1) * board.size;
			bonus -= (board.size - numH - 1) * board.size;
		}
		else if(player == "H") {
			bonus += (board.size - numH - 1) * board.size;
			bonus -= (board.size - numV - 1) * board.size;
		}
		value += bonus;
		return value;
	}
}
