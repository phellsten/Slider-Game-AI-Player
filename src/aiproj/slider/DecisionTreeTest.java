package aiproj.slider;

public class DecisionTreeTest {
	public static void main(String[] args) {
		System.out.println("TEST");
		// Create a normal 3x3 board
		// Board testBoard = new Board("H + + + \nH + + + \nH + + +\n+ V V V\n", 4);
		Board testBoard = new Board("+ + + H \n+ + + + \n+ + + +\n+ + + +\n", 4);
		// Make the Decision Tree Great Again
		DecisionTree testTree = new DecisionTree(testBoard, "H");
		// Test the moves
		testTree.calculatePossibleMoves("H");
		testTree.move(new Move(3,3,Move.Direction.DOWN));
	}
}
