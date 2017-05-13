package aiproj.slider;

public class DecisionTreeTest {
	public static void main(String[] args) {
		System.out.println("TEST");
		// Create a normal 3x3 board
		Board testBoard = new Board("H + + + \nH + + + \nH + + +\n+ V V V\n", 4);
		//Board testBoard = new Board("+ + + H \n+ + + + \n+ + + +\n+ + + +\n", 4);
		// Make the Decision Tree Great Again
		DecisionTree testTree = new DecisionTree(testBoard, "H");
		// Test the moves
		testTree.calculatePossibleMoves("H");
		testTree.debug();
		System.out.println("________________BEFORE MOVE __________________");
		//testTree.move(new Move(0,1,Move.Direction.RIGHT));
		//testTree.debug();
		// Print the new board
		// testTree.extendNodes();
	}
}
