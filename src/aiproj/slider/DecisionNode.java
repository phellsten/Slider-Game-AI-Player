package aiproj.slider;

import java.util.ArrayList;
import java.util.LinkedList;


public class DecisionNode {
	// Value calculated by MiniMax
			private int value;

			// Arraylist of Moves which have got us to the current position
			private LinkedList<Move> moves;
			
			boolean visited;

			private DecisionNode parentNode;
			
			/** Constructor for the root node */
			DecisionNode() {
				this.childNodes = new ArrayList<DecisionNode>();
				this.parentNode = null;
				moves = new LinkedList<Move>();
			}

			/**
			 * Constructor for a child node, with the parent node taken as an arguement
			 */
			DecisionNode(DecisionNode parentNode) {
				this.parentNode = parentNode;
				parentNode.addChildNode(this);
				moves = new LinkedList<Move>();
				moves.addAll(parentNode.moves);
			}

			// The children of the node
			ArrayList<DecisionNode> childNodes;
			
			/** Returns an unvisited child node */
			public DecisionNode getNotVisitedChild()
			{
				for (DecisionNode decNde : childNodes)
				{
					if (decNde.visited == true)
					{
						return decNde;
					}
				}
				return null;
			}

			public int getValue() {
				return value;
			}

			public void setValue(int value) {
				this.value = value;
			}

			public LinkedList<Move> getMoves() {
				return moves;
			}

			public void addMove(Move move) {
				this.moves.add(move);
			}

			/** Adds in a new child node */
			public void addChildNode(DecisionNode nde) {
				childNodes.add(nde);
			}
}
