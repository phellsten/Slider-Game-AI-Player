package aiproj.slider;

import aiproj.slider.SliderPlayer;
import aiproj.slider.Move;




public class SliderPhai implements SliderPlayer {

	String player;
	Negamax nmax;
	DecisionTree tree;
	
	public SliderPhai() {
		
	}

	@Override
	public void init(int dimension, String board, char player) {
		Board nBoard = new Board(board, dimension);
		this.player = Character.toString(player);
		nmax = new Negamax();
		tree = new DecisionTree(nBoard, this.player);
		//System.out.println("0, 0: " + this.board.blocks[0][0]);
		//System.out.println("1, 2: " + this.board.blocks[1][2]);
		//System.out.println("3, 1: " + this.board.blocks[3][1]);

	}

	@Override
	public void update(Move move) {
		// current board representation : board;
		// opponents move: move;
		// opponent moved piece board[move.i][move.j] in direction move.d
		// new board representation:...
		if(move == null) {
			return;
		}
		System.out.println("Updating with move: " + move);
		tree.getRootBoard().movePiece(move.i, move.j, move.d);
		//tree.move(move);
		tree.extendNodes();
		
	}

	@Override
	public Move move() {
		System.out.println("*****");
		tree.getRootBoard().printDebug();
		System.out.println("*****");

		tree.calculatePossibleMoves(this.player);
		try {
			Move bestMove = nmax.getBestMove(tree);
			System.out.println("Best move is: " + bestMove);
			update(bestMove);
			System.out.println("*****");
			tree.getRootBoard().printDebug();
			System.out.println("*****");
			return bestMove;
		} catch (Exception e) {
			//System.out.println("ERROR FINDING MOVE");
			return null;
		}
	}
	
}
