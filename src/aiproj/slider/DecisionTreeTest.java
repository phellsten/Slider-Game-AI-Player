package aiproj.slider;

public class DecisionTreeTest {
	public static void main(String[] args) {
		System.out.println("TEST");
		// Create a normal 3x3 board
		Board testBoard = new Board("6\nH + + + + + \n" + "+ H B + + + \n" + "H + + + + + \n" + "H + V B V + \n"
				+ "+ + + + H + \n" + "+ V + + V V \n", 6);
		// Make the Decision Tree Great Again
		DecisionTree testTree = new DecisionTree(testBoard, "H");
		
		// Test the moves
		testTree.calculatePossibleMoves("H");
	}
}
