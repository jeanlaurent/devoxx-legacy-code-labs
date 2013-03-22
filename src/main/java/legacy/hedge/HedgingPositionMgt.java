package legacy.hedge;

import legacy.error.CheckResult;
import legacy.dto.InputEvent;
import legacy.service.ToweringXMLHTTPServiceClient;

public class HedgingPositionMgt {

	public static CheckResult<HedgingPosition> hedgingPositionMgt(HedgingPosition hp) {
		CheckResult<HedgingPosition> result = new CheckResult<HedgingPosition>();
		HedgingPosition returned = new HedgingPosition();
		InputEvent event = new InputEvent(hp);
		ToweringXMLHTTPServiceClient.sendTicketToTowering(event);
		return result;
	}

}
