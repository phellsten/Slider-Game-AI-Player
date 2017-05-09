package aima.core.logic.propositional.visitors;

/**
 * @author Ravi Mohan
 * 
 */
public class AndDetector implements PLVisitor {

	public Object visitSymbol(Symbol s, Object arg) {

		return new Boolean(false);
	}

	public Object visitTrueSentence(TrueSentence ts, Object arg) {
		return new Boolean(false);
	}

	public Object visitFalseSentence(FalseSentence fs, Object arg) {
		return new Boolean(false);
	}

	public Object visitNotSentence(UnarySentence fs, Object arg) {
		return fs.getNegated().accept(this, null);
	}

	public Object visitBinarySentence(BinarySentence fs, Object arg) {
		if (fs.isAndSentence()) {
			return new Boolean(true);
		} else {
			boolean first = ((Boolean) fs.getFirst().accept(this, null))
					.booleanValue();
			boolean second = ((Boolean) fs.getSecond().accept(this, null))
					.booleanValue();
			return new Boolean((first || second));
		}
	}

	public Object visitMultiSentence(MultiSentence fs, Object arg) {
		throw new RuntimeException("can't handle multisentences");
	}

	public boolean containsEmbeddedAnd(Sentence s) {
		return ((Boolean) s.accept(this, null)).booleanValue();
	}
}