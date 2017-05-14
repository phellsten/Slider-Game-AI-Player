package aiproj.slider;

public class DecisionTreeTest {
	public static void main(String[] args) {
		System.out.println("TEST");
		Negamax nmax = new Negamax();
		// Create a normal 3x3 board
		Board testBoard = new Board("+ H + +\n"
							      + "+ H B +\n" 
							      + "+ + + +\n" 
							      + "V + V V\n", 4);
		
		Board testBoard2 = new Board("+ + H +\n"
			      + "+ H B +\n" 
			      + "+ + + +\n" 
			      + "V + V V\n", 4);
		
		Board testBoard3 = new Board("+ H + +\n"
			      + "+ + B +\n" 
			      + "+ H + +\n" 
			      + "V + V V\n", 4);
		// Board testBoard = new Board("H + + + \nH + + + \nH + + +\n+ V V V\n",
		// 4);
		// Board testBoard = new Board("+ + + H \n+ + + + \n+ + + +\n+ + + +\n",
		// 4);
		// Make the Decision Tree Great Again
		DecisionTree testTree = new DecisionTree(testBoard, "H");
		// Test the moves
		testTree.calculatePossibleMoves("H");
		testTree.debug();
		System.out.println("________________BEFORE MOVE __________________");
		// I wonder what the best move is
		try {
			System.out.println(nmax.getBestMove(testTree));
		} catch (Exception e) {
			System.out.println("ERROR FINDING MOVE");
		}
		// Our Move
		// testTree.move(new Move(0,1,Move.Direction.RIGHT));
		// Our Oppoents Move
		// testTree.move(new Move(1,0,Move.Direction.LEFT));
		// testTree.extendNodes();
		//testTree.debug();
		System.out.println(testTree.getUtility(testBoard2, "H"));
		System.out.println(testTree.getUtility(testBoard3, "H"));

	}
}
