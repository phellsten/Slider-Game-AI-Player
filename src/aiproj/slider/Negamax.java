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
	public int negamax(DecisionNode nde) {
		// If there are no child nodes, return evaluation Function
		if (nde.getChildNodes().isEmpty()) {
			return nde.getValue();
		}

		Integer bestValue = null;
		for (DecisionNode childNde : nde.getChildNodes()) {
			int value = -negamax(childNde);
			bestValue = Math.max(bestValue, value);
		}
		return bestValue;
	}
}
