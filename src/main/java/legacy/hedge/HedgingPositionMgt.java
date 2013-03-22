package legacy.hedge;

import legacy.error.CheckResult;
import legacy.dto.InputEvent;
import legacy.service.ToweringXMLHTTPServiceClient;
import org.apache.commons.lang3.SerializationUtils;

public class HedgingPositionMgt {

	public static CheckResult<HedgingPosition> hedgingPositionMgt(HedgingPosition hp) {
		CheckResult<HedgingPosition> result = new CheckResult<HedgingPosition>();
		InputEvent event = new InputEvent(hp);
		ToweringXMLHTTPServiceClient.sendTicketToTowering(event);
		result.setCheckIsOk(true);
		result.setResult(SerializationUtils.clone(hp));
		return result;
	}

}
