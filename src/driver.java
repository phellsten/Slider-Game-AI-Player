// Patrick Hudgell phudgell

import java.util.ArrayList;
import java.util.Scanner;

public class driver {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ArrayList<String> strings = new ArrayList<String>();
		
		while(scan.hasNext())
		{
			strings.add(scan.next());
		}
		scan.close();
		
		String[] arr = new String[strings.size()];
		arr = strings.toArray(arr);
		
		Board board = new Board(arr);

		int i;
		int j;
		int numLegalH = 0;
		int numLegalV = 0;
		
		// For each piece on the board
		for(j=0; j < board.size; j++) {
			for(i=0; i < board.size; i++) {
				// If piece is 'H'
				if(board.blocks[i][j].equals("H")) {
					if(board.isFree(i+1,j,"H")) {
						// H can move right
						numLegalH++;
					}
					if(board.isFree(i,j-1,"H")) {
						// H can move up
						numLegalH++;
					}
					if(board.isFree(i,j+1,"H")) {
						// H can move down
						numLegalH++;
					}
				}
				// If piece is 'V'
				else if (board.blocks[i][j].equals("V")) {
					if(board.isFree(i-1,j,"V")) {
						// V can move left
						numLegalV++;
					}
					if(board.isFree(i+1,j,"V")) {
						// V can move right
						numLegalV++;
					}
					if(board.isFree(i,j-1,"V")) {
						// V can move up
						numLegalV++;
					}
				}
			}
		}
			
		System.out.println(numLegalH);
		System.out.println(numLegalV);
	}
}
