public class driver {
	public static void main(String[] args) {
		Board board = new Board(args);
		
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
						numLegalV++;
					}
					if(board.isFree(i,j+1,"H")) {
						// H can move down
						numLegalV++;
					}
				}
				// If piece is 'V'
				else if (board.blocks[i][j].equals("V")) {
					if(board.isFree(i-1,j,"V")) {
						// V can move left
						numLegalH++;
					}
					if(board.isFree(i+1,j,"V")) {
						// V can move right
						numLegalH++;
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