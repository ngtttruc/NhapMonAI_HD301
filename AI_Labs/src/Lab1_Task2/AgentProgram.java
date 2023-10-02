package Lab1_Task2; 

import java.util.Random;
import static Lab1_Task2.Environment.MOVE_DOWN;
import static Lab1_Task2.Environment.MOVE_LEFT;
import static Lab1_Task2.Environment.MOVE_RIGHT;
import static Lab1_Task2.Environment.MOVE_UP;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		String location = p.getAgentLocation();
		Action action = randomAction();
		if (p.getLocationState() == Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}else if(location == Environment.LOCATION_A) {
			if(action == MOVE_UP || action == MOVE_LEFT) {
				return null;
			}
			return action;
		}else if(location == Environment.LOCATION_B) {
			if(action == MOVE_UP || action == MOVE_RIGHT) {
				return null;
			}
			return action;
		}else if(location == Environment.LOCATION_C) {
			if(action == MOVE_DOWN || action == MOVE_LEFT) {
				return null;
			}
			return action;
		}else if(location == Environment.LOCATION_D) {
			if(action == MOVE_DOWN || action == MOVE_RIGHT) {
				return null;
			}
			return action;
		}
		return NoOpAction.NO_OP;
	}

	private Action randomAction() {
		Random random = new Random();
		Action action = null;
		int randomIndex = random.nextInt(4);
		switch (randomIndex) {
		case 0:
			action = MOVE_RIGHT;
			break;
		case 1:
			action = MOVE_LEFT;
			break;
		case 2:
			action = MOVE_UP;
			break;
		case 3:
			action = MOVE_DOWN;
			break;

		default:
			action = null;
			break;
		
       }
	return action;

	}
}