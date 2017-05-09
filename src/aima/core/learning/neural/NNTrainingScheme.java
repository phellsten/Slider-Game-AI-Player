package aima.core.learning.neural;

/**
 * @author Ravi Mohan
 * 
 */
public interface NNTrainingScheme {
	Vector processInput(FeedForwardNeuralNetwork network, Vector input);

	void processError(FeedForwardNeuralNetwork network, Vector error);

	void setNeuralNetwork(FunctionApproximator ffnn);
}
