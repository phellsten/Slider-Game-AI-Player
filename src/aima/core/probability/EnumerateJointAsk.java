package aima.core.probability;

import java.util.Hashtable;

/**
 * @author Ravi Mohan
 * 
 */
public class EnumerateJointAsk {

	public static double[] ask(Query q, ProbabilityDistribution pd) {
		double[] probDist = new double[2];
		Hashtable<String, Boolean> h = q.getEvidenceVariables();

		// true probability
		h.put(q.getQueryVariable(), new Boolean(true));
		probDist[0] = pd.probabilityOf(h);
		// false probability
		h.put(q.getQueryVariable(), new Boolean(false));
		probDist[1] = pd.probabilityOf(h);
		return Util.normalize(probDist);
	}
}