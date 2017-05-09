package aima.core.search.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ravi Mohan
 * 
 */
public class SearchUtils {

	public static List<Action> actionsFromNodes(List<Node> nodeList) {
		List<Action> actions = new ArrayList<Action>();
		if (nodeList.size() == 1) {
			// I'm at the root node, this indicates I started at the
			// Goal node, therefore just return a NoOp
			actions.add(NoOpAction.NO_OP);
		} else {
			// ignore the root node this has no action
			// hence index starts from 1 not zero
			for (int i = 1; i < nodeList.size(); i++) {
				Node node = nodeList.get(i);
				actions.add(node.getAction());
			}
		}
		return actions;
	}
}