package aiproj.slider;

public class Minimax {
	public void performMinimax(DecisionTree tree) {

	}

	public int getBestValue(DecisionNode nde, boolean max) {
		if (nde.getChildNodes().size() == 0) {
			return nde.getValue();
		}

		if (max) {
			Integer bestValue = null;
			for (DecisionNode chiNode : nde.getChildNodes()) {
				int value = getBestValue(chiNode, false);
				if (bestValue == null) {
					bestValue = value;
				} else {
					bestValue = Math.max(bestValue, value);
				}
			}
			return bestValue;
		} else {
			Integer bestValue = null;
			for (DecisionNode chiNode : nde.getChildNodes()) {
				int value = getBestValue(chiNode, true);
				if (bestValue == null) {
					bestValue = value;
				} else {
					bestValue = Math.min(bestValue, value);
				}
			}
			return bestValue;
		}
	}
}
