package Lab1_Task2; 

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;
    private int points = 0;
	public Environment(LocationState locAState, LocationState locBState,LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState,locCState, locDState);
	}

	// add an agent into the environment
	public void addAgent(Agent agent, String location) {
		// TODO
	    this.agent = agent;
		envState.setAgentLocation(location);
		
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		String agentLocation = envState.getAgentLocation();
		if(action == SUCK_DIRT) {
			points+=500;
			envState.setLocationState(agentLocation,LocationState.CLEAN);
		}else if(action == MOVE_LEFT) {
			points-=10;
			switch (agentLocation) {
			case LOCATION_B:
				envState.setAgentLocation(LOCATION_A);
				break;
			case LOCATION_C:
				envState.setAgentLocation(LOCATION_D);
				break;
			}
		}else if(action == MOVE_RIGHT) {
			points+=10;
			switch (agentLocation) {
			case LOCATION_A:
				envState.setAgentLocation(LOCATION_B);
				break;
			case LOCATION_C:
				envState.setAgentLocation(LOCATION_D);
				break;
			}
		}else if(action == MOVE_UP) {
			points-=10;
			switch (agentLocation) {
			case LOCATION_C:
				envState.setAgentLocation(LOCATION_A);
				break;
			case LOCATION_D:
				envState.setAgentLocation(LOCATION_B);
				break;
			}
		}else if(action == MOVE_DOWN) {
			points-=10;
			switch (agentLocation) {
			case LOCATION_A:
				envState.setAgentLocation(LOCATION_C);
				break;
			case LOCATION_B:
				envState.setAgentLocation(LOCATION_D);
				break;
			}
		}else if(action == null) {
			points-=100;
			switch (agentLocation) {
			case LOCATION_A:
				envState.setAgentLocation(LOCATION_A);
				break;
			case LOCATION_B:
				envState.setAgentLocation(LOCATION_B);
				break;
			case LOCATION_C:
				envState.setAgentLocation(LOCATION_C);
				break;
			case LOCATION_D:
				envState.setAgentLocation(LOCATION_D);
				break;
			}			
		}
		// TODO
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		String agentLocation = envState.getAgentLocation();
		LocationState state = envState.getLocationState(agentLocation);
		
		// TODO
		return new Percept(agentLocation, state);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
        System.out.println("Points: "+ points);
		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
