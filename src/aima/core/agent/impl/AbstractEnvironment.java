package aima.core.agent.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public abstract class AbstractEnvironment implements Environment, NotifyEnvironmentViews {

	// Note: Use LinkedHashSet's in order to ensure order is respected as
	// provide
	// access to these elements via List interface.
	protected Set<EnvironmentObject> envObjects = new LinkedHashSet<EnvironmentObject>();

	protected Set<Agent> agents = new LinkedHashSet<Agent>();

	protected Set<EnvironmentView> views = new LinkedHashSet<EnvironmentView>();

	protected Map<Agent, Double> performanceMeasures = new LinkedHashMap<Agent, Double>();

	//
	// PRUBLIC METHODS
	//

	// 
	// Methods to be implemented by subclasses.
	public abstract EnvironmentState getCurrentState();

	public abstract EnvironmentState executeAction(Agent agent, Action action);

	public abstract Percept getPerceptSeenBy(Agent anAgent);

	//
	// START-Environment
	public List<Agent> getAgents() {
		// Return as a List but also ensures the caller cannot modify
		return new ArrayList<Agent>(agents);
	}

	public void addAgent(Agent a) {
		addEnvironmentObject(a);
	}

	public void removeAgent(Agent a) {
		removeEnvironmentObject(a);
	}

	public List<EnvironmentObject> getEnvironmentObjects() {
		// Return as a List but also ensures the caller cannot modify
		return new ArrayList<EnvironmentObject>(envObjects);
	}

	public void addEnvironmentObject(EnvironmentObject eo) {
		envObjects.add(eo);
		if (eo instanceof Agent) {
			Agent a = (Agent) eo;
			if (!agents.contains(a)) {
				agents.add(a);
				this.updateEnvironmentViewsAgentAdded(a);
			}
		}
	}

	public void removeEnvironmentObject(EnvironmentObject eo) {
		envObjects.remove(eo);
		agents.remove(eo);
	}

	public void step() {
		if (!isDone()) {
			for (Agent agent : agents) {
				if (agent.isAlive()) {
					Action anAction = agent.execute(getPerceptSeenBy(agent));

					EnvironmentState es = executeAction(agent, anAction);

					updateEnvironmentViewsAgentActed(agent, anAction, es);
				}
			}
		}
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
		}
	}

	public void stepUntilDone() {
		while (!isDone()) {
			step();
		}
	}

	public boolean isDone() {
		for (Agent agent : agents) {
			if (agent.isAlive()) {
				return false;
			}
		}
		return true;
	}

	public double getPerformanceMeasure(Agent forAgent) {
		Double pm = performanceMeasures.get(forAgent);
		if (null == pm) {
			pm = new Double(0);
			performanceMeasures.put(forAgent, pm);
		}

		return pm;
	}

	public void addEnvironmentView(EnvironmentView ev) {
		views.add(ev);
	}

	public void removeEnvironmentView(EnvironmentView ev) {
		views.remove(ev);
	}

	public void notifyViews(String msg) {
		for (EnvironmentView ev : views) {
			ev.notify(msg);
		}
	}

	// END-Environment
	//

	//
	// PROTECTED METHODS
	//

	protected void updatePerformanceMeasure(Agent forAgent, double addTo) {
		performanceMeasures.put(forAgent, getPerformanceMeasure(forAgent)
				+ addTo);
	}

	protected void updateEnvironmentViewsAgentAdded(Agent agent) {
		for (EnvironmentView view : views) {
			view.agentAdded(agent, getCurrentState());
		}
	}

	protected void updateEnvironmentViewsAgentActed(Agent agent, Action action,
			EnvironmentState state) {
		for (EnvironmentView view : views) {
			view.agentActed(agent, action, state);
		}
	}
}