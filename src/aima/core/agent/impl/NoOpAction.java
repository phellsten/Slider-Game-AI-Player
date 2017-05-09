package aima.core.agent.impl;

public class NoOpAction extends DynamicAction {

	public static final NoOpAction NO_OP = new NoOpAction();

	//
	// START-Action
	@Override
	public boolean isNoOp() {
		return true;
	}

	// END-Action
	//

	private NoOpAction() {
		super("NoOp");
	}
}
