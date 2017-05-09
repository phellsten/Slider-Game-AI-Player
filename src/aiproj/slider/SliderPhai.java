package aiproj.slider;

import aiproj.slider.SliderPlayer;
import aiproj.slider.Move;




public class SliderPhai implements SliderPlayer {

	Board board;
	
	public SliderPhai() {
		
	}

	@Override
	public void init(int dimension, String board, char player) {
		this.board = new Board(board, dimension);
		System.out.println("0, 0: " + this.board.blocks[0][0]);
		System.out.println("1, 2: " + this.board.blocks[1][2]);
		System.out.println("3, 1: " + this.board.blocks[3][1]);
	}

	@Override
	public void update(Move move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Move move() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
