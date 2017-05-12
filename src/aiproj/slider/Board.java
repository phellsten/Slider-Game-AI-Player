// Patrick Hudgell phudgell
// Paul Hellsten phellsten
package aiproj.slider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import aiproj.slider.Move.Direction;

public class Board {
	String blocks[][];
	int size;

	Board(Board anotherBoard) {
		this.blocks = anotherBoard.blocks;
		this.size = anotherBoard.size;
	}
	/** Prints the board to assist with debugging */
	public void printDebug()
	{
		// Process the board one by one to print it out
		for (int y = this.size-1; y >= 0; y--)
		{
			for (int x = 0; x < this.size; x++)
			{
				System.out.print(blocks[x][y]);
			}
			System.out.print('\n');
		}
	}

	public Board(String args, int size) {

		LinkedList<String> vars = new LinkedList<String>();
		ArrayList<String> ar = new ArrayList<String>();

		// Convert args to mutable LinkedList

		String[] line = args.split("\n");
		// line = {"H + + +", "H + B +", ...

		List<String> list = Arrays.asList(line);
		Collections.reverse(list);
		line = (String[]) list.toArray();

		for (int i = 0; i < size; i++) {
			for (String j : line[i].split(" ")) {
				ar.add(j);
			}
		}

		for (String i : ar) {
			vars.add(i);
		}

		// Read pieces of board into 2D Array
		String board[][] = new String[size][size];
		int i;
		int j;

		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				board[j][i] = vars.pop();
			}
		}
		this.blocks = board;
		this.size = size;

	}

	public void movePiece(int x, int y, Direction d) {
		String piece = blocks[x][y];
		blocks[x][y] = "+";
		if (d == Direction.UP) {
			y++;
		} else if (d == Direction.RIGHT) {
			x++;
		} else if (d == Direction.DOWN) {
			y--;
		} else if (d == Direction.LEFT) {
			x--;
		}
		blocks[x][y] = piece;

	}

	public boolean isFree(int x, int y, String piece) {

		// No piece can move off the left of the board
		if (x < 0) {
			return false;
		}

		// No piece can move off the bottom of the board
		if (y >= size) {
			return false;
		}

		// Only 'V' can move off the top of the board
		if (y < 0) {
			if (piece.equals("V")) {
				return true;
			} else {
				return false;
			}
		}

		// Only 'H' can move off the right of the board
		if (x >= size) {
			if (piece.equals("H")) {
				return true;
			} else {
				return false;
			}
		}

		// If tile is on the board, check if it is a free tile
		if (blocks[x][y].equals("+")) {
			return true;
		} else {
			return false;
		}
	}

}
