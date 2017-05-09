package aima.core.search.uninformed;

import java.util.List;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 85.
 * 
 * Depth-first search always expands the deepest node in the current frontier of the search tree.
 * 
 * Note: Supports both Tree and Graph based versions by assigning an instance
 * of TreeSearch or GraphSearch to its constructor.
 */

/**
 * @author Ravi Mohan
 * 
 */
public class DepthFirstSearch implements Search {

	QueueSearch search;

	public DepthFirstSearch(QueueSearch search) {
		this.search = search;
	}

	public List<Action> search(Problem p) {
		return search.search(p, new LIFOQueue<Node>());
	}

	public Metrics getMetrics() {
		return search.getMetrics();
	}
}