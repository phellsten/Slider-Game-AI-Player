package aiproj.slider;

import aiproj.slider.SliderPlayer;
import aiproj.slider.Move;




public class SliderPhai implements SliderPlayer {

	Board board;
	char player;
	
	public SliderPhai() {
		
	}

	@Override
	public void init(int dimension, String board, char player) {
		this.board = new Board(board, dimension);
		this.player = player;
		System.out.println("0, 0: " + this.board.blocks[0][0]);
		System.out.println("1, 2: " + this.board.blocks[1][2]);
		System.out.println("3, 1: " + this.board.blocks[3][1]);
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
		
		int x = move.i;
		int y = move.j;
		String piece = board.blocks[x][y];
		board.blocks[x][y] = " ";
		if(move.d == Move.Direction.UP) {
			y++;
		}
		else if(move.d == Move.Direction.RIGHT) {
			x++;
		}
		else if(move.d == Move.Direction.DOWN) {
			y--;
		}
		else if(move.d == Move.Direction.LEFT) {
			x--;
		}
		board.blocks[x][y] = piece;
	}

	@Override
	public Move move() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
