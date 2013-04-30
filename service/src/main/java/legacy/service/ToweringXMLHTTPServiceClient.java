package legacy.service;

import legacy.dto.InputEvent;

public class ToweringXMLHTTPServiceClient {

	public static void sendTicketToTowering(InputEvent event) {
		for (int i = 1; i <= 100; i++) {
			buildHttpRequestAndSendToTowering(event);
			System.out.println("[remote] Sending HedgingPosition chunk #"+i+" to Towering");
		}
	}





















	private static void buildHttpRequestAndSendToTowering(InputEvent event) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
