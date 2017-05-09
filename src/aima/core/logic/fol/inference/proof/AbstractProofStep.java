package aima.core.logic.fol.inference.proof;

import java.util.List;

/**
 * @author Ciaran O'Reilly
 * 
 */
public abstract class AbstractProofStep implements ProofStep {
	private int step = 0;

	public AbstractProofStep() {

	}

	//
	// START-ProofStep
	@Override
	public int getStepNumber() {
		return step;
	}

	@Override
	public void setStepNumber(int step) {
		this.step = step;
	}

	@Override
	public abstract List<ProofStep> getPredecessorSteps();

	@Override
	public abstract String getProof();

	@Override
	public abstract String getJustification();

	// END-ProofStep
	//
}
