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
	public Move getBestMove(DecisionTree tree) throws Exception {
		// Get the best child node
		DecisionNode bestNde = getBestChildNode(tree);
		// Sanity check for debugging
		assert (bestNde.getMoves().size() == 0);
		return bestNde.getMoves().get(0);
	}

	// TODO Impliment Alpha Beta
	private int negamax(DecisionNode nde) {
		// If there are no child nodes, return evaluation Function
		if (nde.getChildNodes().isEmpty()) {
			//System.out.println("no child nodes");
			return nde.getValue();
		}

		Integer bestValue = null;
		for (DecisionNode childNde : nde.getChildNodes()) {
			
			//System.out.println(childNde.getValue());
			int value = -negamax(childNde);
			if (bestValue == null) {
				bestValue = value;
			}
			childNde.setValue(value);
			bestValue = Math.min(bestValue, value);
		}
		return bestValue;
	}

	public DecisionNode getBestChildNode(DecisionTree tree) throws Exception {
		int bestValue = negamax(tree.getRootNode());
		// Find the node with this value
		for (DecisionNode childNde : tree.getRootNode().getChildNodes()) {
			if (childNde.getValue() == bestValue) {
				System.out.println("best value: " + bestValue);
				return childNde;
			}
		}
		throw new Exception("Cannot find Value");
	}
}
