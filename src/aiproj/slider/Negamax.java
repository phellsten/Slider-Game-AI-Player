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
	public Move getBestMove(DecisionTree tree) throws Exception
	{
		// Get the best child node
		DecisionNode bestNde = getBestChildNode(tree);
		return bestNde.getMoves().get(0);
	}
	
	private int negamax(DecisionNode nde) {
		// If there are no child nodes, return evaluation Function
		if (nde.getChildNodes().isEmpty()) {
			return nde.getValue();
		}

		Integer bestValue = null;
		for (DecisionNode childNde : nde.getChildNodes()) {
			int value = -negamax(childNde);
			if (bestValue == null) {
				bestValue = value;
			}
			childNde.setValue(value);
			bestValue = Math.max(bestValue, value);
		}
		return bestValue;
	}
	
	public DecisionNode getBestChildNode(DecisionTree tree) throws Exception
	{
		int bestValue = negamax(tree.getRootNode());
		// Find the node with this value
		for (DecisionNode childNde : tree.getRootNode().getChildNodes())
		{
			if (childNde.getValue() == bestValue)
			{
				return childNde;
			}
		}
		throw new Exception("Cannot find Value");
	}
}
