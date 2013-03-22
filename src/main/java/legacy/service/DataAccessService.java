package legacy.service;

import legacy.service.ITransactionManagerService;

import java.util.logging.Logger;

public class DataAccessService {

	private static Logger log = Logger.getLogger(DataAccessService.class.getName());

	static {
		log.info("==================================================");
		log.info("=******===****===****===******===******===*******=");
		log.info("=          DATA ACCESS SERVICE INITIATED         =");
		log.info("=******===****===****===******===******===*******=");
		log.info("==================================================");
	}

	/**
	 * get the transaction manager service
	 * @return
	 */
	public static ITransactionManagerService getTransactionManagerService() {
		throw new RuntimeException("Can't reach service.");
	}

	public static ITradingDataAccessService getTradingDataAccessService() {
		throw new RuntimeException("Can't reach service.");
	}

	public static IHedgingPositionDataAccessService getHedgingPositionDataAccessService() {
		throw new RuntimeException("Can't reach service.");
	}

	public static IAnalyticalService getAnalyticalService() {
		throw new RuntimeException("Can't reach service.");
	}
}
