package Bt_Lab1;

public class Agent {
	private AgentProgram program;

	public Agent() {
	}

	public Agent(AgentProgram tProgram) {
		program = tProgram;
	}

	public Action execute(Percept p) {
		if (program != null) {
			return program.execute(p);
		}
		return NoOpAction.NO_OP;
	}

}
