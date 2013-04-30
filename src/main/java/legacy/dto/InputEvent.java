package legacy.dto;

import legacy.persistence.BaseDTO;
import legacy.service.Position;

public class InputEvent extends BaseDTO {

	private Position pos;

	public InputEvent(Position pos) {
		this.pos = pos;
	}

	public Position getPosition() {
		return pos;
	}
}
