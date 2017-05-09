package aima.core.search.framework;

/**
 * @author Ravi Mohan
 * 
 */
public class DefaultStepCostFunction implements StepCostFunction {

	//
	// START-StepCostFunction
	public double c(Object stateFrom, Action action, Object stateTo) {
		return 1;
	}

	// END-StepCostFunction
	//
}