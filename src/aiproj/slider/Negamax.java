// Patrick Hudgell phudgell
// Paul Hellsten phellsten
package aiproj.slider;

/**
 * An implimentation of the Minimax algorithm, using the property
 * 
 * This is a simplified version of minimax, using the property max(a,b) =
 * -min(-a, -b)
 *
 * This function makes the assumption that it is always called at your turn!
 * 
 */
public class Negamax {
	/** Returns the best move that we can make */
	public Move getBestMove(DecisionTree tree) throws Exception {
		// Get the best child node
		DecisionNode bestNde = getBestChildNode(tree);
		// Sanity check for debugging
		assert (bestNde.getMoves().size() == 0);
		return bestNde.getMoves().get(0);
	}

	/** Returns the value of the best node */
	private int negamax(DecisionNode nde) {
		// If there are no child nodes, return evaluation Function
		if (nde.getChildNodes().isEmpty()) {
			return nde.getValue();
		}

		Integer bestValue = null;
		for (DecisionNode childNde : nde.getChildNodes()) {
			int value = negamax(childNde);
			if (bestValue == null) {
				bestValue = value;
			}
			bestValue = Math.max(bestValue, value);
			childNde.setValue(bestValue);
		}
		return bestValue;
	}

	/** Find the best node using this value */
	public DecisionNode getBestChildNode(DecisionTree tree) throws Exception {
		int bestValue = negamax(tree.getRootNode());
		// Find the node with this value
		for (DecisionNode childNde : tree.getRootNode().getChildNodes()) {
			if (childNde.getValue() == bestValue) {
				return childNde;
			}
		}
		throw new Exception("Cannot find Value");
	}
}
