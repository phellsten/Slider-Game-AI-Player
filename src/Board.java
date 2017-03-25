import java.util.LinkedList;

public class Board {
	String blocks[][];
	int size;
	
	public Board(String[] args) {
		LinkedList<String> vars = new LinkedList<String>();
		// Convert args to mutable LinkedList
		for(String i : args) {
			vars.add(i);
		}
		
		int size = Integer.parseInt(vars.remove(0));
		
		// Read pieces of board into 2D Array
		String board[][] = new String[size][size];
		int i;
		int j;
		
		for(i=0; i < size; i++) {
			for(j=0; j < size; j++) {
				board[j][i] = vars.pop();
			}
		}
		
		this.blocks = board;
		this.size = size;
	}
	
	public boolean isFree(int x, int y, String piece) {
		
		// No piece can move off the left of the board
		if(x < 0) {
			return false;
		}
		
		// No piece can move off the bottom of the board
		if (y >= size) {
			return false;
		}
		
		// Only 'V' can move off the top of the board
		if(y < 0) {
			if (piece.equals("V")) {
				return true;
			}
			else {
				return false;
			}
		}
		
		// Only 'H' can move off the right of the board
		if(x >= size) {
			if (piece.equals("H")) {
				return true;
			}
			else {
				return false;
			}
		}
		
		// If tile is on the board, check if it is a free tile
		if(blocks[x][y].equals("+")) {
			return true;
		}
		else {
			return false;
		}
	}

}
