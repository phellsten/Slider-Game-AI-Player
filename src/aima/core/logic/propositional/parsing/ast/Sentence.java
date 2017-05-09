package aima.core.logic.propositional.parsing.ast;

/**
 * @author Ravi Mohan
 * 
 */
public abstract class Sentence implements ParseTreeNode {

	public abstract Object accept(PLVisitor plv, Object arg);
}