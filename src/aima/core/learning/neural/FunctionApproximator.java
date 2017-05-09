package aima.core.learning.neural;

/**
 * @author Ravi Mohan
 * 
 */
public interface FunctionApproximator {
	/*
	 * accepts input pattern and processe it returning an output value
	 */
	Vector processInput(Vector input);

	/*
	 * accept an error and change the parameters to accomodate it
	 */
	void processError(Vector error);
}
