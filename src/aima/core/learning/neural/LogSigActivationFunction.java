package aima.core.learning.neural;

/**
 * @author Ravi Mohan
 * 
 */
public class LogSigActivationFunction implements ActivationFunction {

	@Override
	public double activation(double parameter) {

		return 1.0 / (1.0 + Math.pow(Math.E, (-1.0 * parameter)));
	}

	@Override
	public double deriv(double parameter) {
		// parameter = induced field
		// e == activation
		double e = 1.0 / (1.0 + Math.pow(Math.E, (-1.0 * parameter)));
		return e * (1.0 - e);
	}
}
