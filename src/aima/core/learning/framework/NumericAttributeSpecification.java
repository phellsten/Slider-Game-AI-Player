package aima.core.learning.framework;

/**
 * @author Ravi Mohan
 * 
 */
public class NumericAttributeSpecification implements AttributeSpecification {

	// a simple attribute representing a number reprsented as a double .
	private String name;

	public NumericAttributeSpecification(String name) {
		this.name = name;
	}

	@Override
	public boolean isValid(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getAttributeName() {
		return name;
	}

	@Override
	public Attribute createAttribute(String rawValue) {
		return new NumericAttribute(Double.parseDouble(rawValue), this);
	}
}
