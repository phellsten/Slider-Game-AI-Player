package aima.core.logic.propositional.parsing;

/**
 * @author Ravi Mohan
 * 
 */
public interface PLVisitor extends Visitor {
	public Object visitSymbol(Symbol s, Object arg);

	public Object visitTrueSentence(TrueSentence ts, Object arg);

	public Object visitFalseSentence(FalseSentence fs, Object arg);

	public Object visitNotSentence(UnarySentence fs, Object arg);

	public Object visitBinarySentence(BinarySentence fs, Object arg);

	public Object visitMultiSentence(MultiSentence fs, Object arg);
}