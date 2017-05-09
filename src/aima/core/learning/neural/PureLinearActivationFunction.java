package aima.core.learning.neural;

/**
 * @author Ravi Mohan
 * 
 */
public class PureLinearActivationFunction implements ActivationFunction {

	@Override
	public double activation(double parameter) {
		return parameter;
	}

	@Override
	public double deriv(double parameter) {

		return 1;
	}
}
