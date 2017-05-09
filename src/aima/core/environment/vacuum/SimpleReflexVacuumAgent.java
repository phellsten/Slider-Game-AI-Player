package aima.core.environment.vacuum;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class SimpleReflexVacuumAgent extends AbstractAgent {

	public SimpleReflexVacuumAgent() {
		super(new SimpleReflexAgentProgram(getRuleSet()));
	}

	//
	// PRIVATE METHODS
	//
	private static Set<Rule> getRuleSet() {
		// Note: Using a LinkedHashSet so that the iteration order (i.e. implied
		// precedence) of rules can be guaranteed.
		Set<Rule> rules = new LinkedHashSet<Rule>();

		// Rules based on REFLEX-VACUUM-AGENT:
		// Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.8,
		// page 48.

		rules.add(new Rule(new EQUALCondition(VacuumEnvPercept.ATTRIBUTE_STATE,
				VacuumEnvironment.LocationState.Dirty),
				VacuumEnvironment.ACTION_SUCK));
		rules.add(new Rule(new EQUALCondition(
				VacuumEnvPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment.LOCATION_A),
				VacuumEnvironment.ACTION_MOVE_RIGHT));
		rules.add(new Rule(new EQUALCondition(
				VacuumEnvPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment.LOCATION_B),
				VacuumEnvironment.ACTION_MOVE_LEFT));

		return rules;
	}
}
