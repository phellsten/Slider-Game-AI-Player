package aima.core.logic.propositional.visitors;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ravi Mohan
 * 
 */
public class NegativeSymbolCollector extends BasicTraverser {
	@Override
	public Object visitNotSentence(UnarySentence ns, Object arg) {
		Set<Symbol> s = (Set<Symbol>) arg;
		if (ns.getNegated() instanceof Symbol) {
			s.add((Symbol) ns.getNegated());
		} else {
			s = SetOps
					.union(s, (Set<Symbol>) ns.getNegated().accept(this, arg));
		}
		return s;
	}

	public Set<Symbol> getNegativeSymbolsIn(Sentence s) {
		return (Set<Symbol>) s.accept(this, new HashSet());
	}
}
