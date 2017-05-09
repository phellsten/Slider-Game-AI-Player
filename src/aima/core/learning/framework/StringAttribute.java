package aima.core.learning.framework;

/**
 * @author Ravi Mohan
 * 
 */
public class StringAttribute implements Attribute {
	private StringAttributeSpecification spec;

	private String value;

	public StringAttribute(String value, StringAttributeSpecification spec) {
		this.spec = spec;
		this.value = value;
	}

	@Override
	public String valueAsString() {
		return value.trim();
	}

	@Override
	public String name() {
		return spec.getAttributeName().trim();
	}
}
