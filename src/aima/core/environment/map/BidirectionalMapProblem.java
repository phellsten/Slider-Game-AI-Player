package aima.core.environment.map;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class BidirectionalMapProblem extends Problem implements
		BidirectionalProblem {

	Map map;

	Problem reverseProblem;

	public BidirectionalMapProblem(Map aMap, String initialState,
			String goalState) {
		super(initialState, MapFunctionFactory.getActionsFunction(aMap),
				MapFunctionFactory.getResultFunction(), new MapGoalTest(
						goalState), new MapStepCostFunction(aMap));

		map = aMap;

		reverseProblem = new Problem(goalState, MapFunctionFactory
				.getActionsFunction(aMap), MapFunctionFactory
				.getResultFunction(), new MapGoalTest(initialState),
				new MapStepCostFunction(aMap));
	}

	//
	// START Interface BidrectionalProblem
	public Problem getOriginalProblem() {
		return this;
	}

	public Problem getReverseProblem() {
		return reverseProblem;
	}
	// END Interface BirectionalProblem
	//
}
