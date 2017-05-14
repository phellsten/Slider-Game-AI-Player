package aiproj.slider;

import aiproj.slider.SliderPlayer;
import aiproj.slider.Move;




public class SliderPhai implements SliderPlayer {

	Board board;
	String player;
	
	public SliderPhai() {
		
	}

	@Override
	public void init(int dimension, String board, char player) {
		this.board = new Board(board, dimension);
		this.player = Character.toString(player);
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
		
		board.movePiece(move.i, move.j, move.d);
		
	}

	@Override
	public Move move() {
		Negamax nmax = new Negamax();
		DecisionTree tree = new DecisionTree(this.board, this.player);
		tree.calculatePossibleMoves(this.player);
		try {
			update(nmax.getBestMove(tree));
			return nmax.getBestMove(tree);
		} catch (Exception e) {
			//System.out.println("ERROR FINDING MOVE");
			return null;
		}
	}
	
}
