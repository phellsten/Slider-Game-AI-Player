package aiproj.slider;

import java.util.ArrayList;
import java.util.LinkedList;


public class DecisionNode {
	// Value calculated by MiniMax
			private int value;

			// Arraylist of Moves which have got us to the current position
			private LinkedList<Move> moves;

			DecisionNode parentNode;

			/** Constructor for the root node */
			DecisionNode() {
				this.parentNode = null;
			}

			/**
			 * Constructor for a child node, with parent node taken as an argumenet
			 */
			DecisionNode(DecisionNode parentNode) {
				this.parentNode = parentNode;
				moves.addAll(parentNode.moves);
			}

			// The children of the node
			ArrayList<DecisionNode> childNodes;

			// The move that results in this value
			private Move move;

			public int getValue() {
				return value;
			}

			public void setValue(int value) {
				this.value = value;
			}

			public Move getMove() {
				return move;
			}

			public void setMove(Move move) {
				this.move = move;
			}

			/** Adds in a new child node */
			public void addChildNode() {

			}
}