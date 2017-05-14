package aiproj.slider;

import aiproj.slider.SliderPlayer;
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
		if(player == 'V')
		{
			tree = null;
			tmpBoard = nBoard;
		}
		else
		{
			tree = new DecisionTree(nBoard, this.player);
		}
	}

	/** Updates the other oppoent */
	@Override
	public void update(Move move) {
		System.out.println("PLAYER " + player + " UPDATING");
		// current board representation : board;
		// opponents move: move;
		// opponent moved piece board[move.i][move.j] in direction move.d
		// new board representation:...
		if(move == null) {
			return;
		}
		
		if (tree == null)
		{
			// Update the physical game board
			tmpBoard.movePiece(move.i, move.j, move.d);
			// Make the decision tree
			tree = new DecisionTree(tmpBoard, this.player);
			tree.getRootBoard().printDebug();
			// Clear it for the Garbage Collector
			tmpBoard = null;
		}
		else
		{
			// Ordinary move
			tree.move(move);
			tree.extendNodes();
		}
	}

	/** Makes a move for ourself */
	@Override
	public Move move() {
		System.out.println("PLAYER " + player + " IS MAKING MOVE");
		tree.calculatePossibleMoves(this.player);
		try {
			Move bestMove = nmax.getBestMove(tree);
			System.out.println("OUR Best move is: " + bestMove);
			tree.move(bestMove);
			return bestMove;
		} catch (Exception e) {
			//System.out.println("ERROR FINDING MOVE");
			return null;
		}
	}
	
}
