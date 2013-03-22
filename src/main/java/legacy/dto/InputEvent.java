package legacy.dto;

import legacy.hedge.HedgingPosition;
import legacy.persistence.BaseDTO;

public class InputEvent extends BaseDTO {

	private HedgingPosition hp;

	public InputEvent(HedgingPosition hp) {
		this.hp = hp;
	}

	public HedgingPosition getPosition() {
		return hp;
	}
}
