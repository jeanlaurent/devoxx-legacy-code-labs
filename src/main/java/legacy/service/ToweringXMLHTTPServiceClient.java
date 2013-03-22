package legacy.service;

import legacy.dto.InputEvent;
import legacy.hedge.HedgingPosition;

public class ToweringXMLHTTPServiceClient {

	public static HedgingPosition sendTicketToTowering(InputEvent event) {
		for (int i = 0; i < 100; i++) {
			buildHttpRequestAndSendToTowering(event);
			System.out.println("[Sending to Towering HedgingPosition part#]" + i);
		}
		return new HedgingPosition();
	}





















	private static void buildHttpRequestAndSendToTowering(InputEvent event) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
