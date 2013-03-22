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
		return null;
	}

	public static ITradingDataAccessService getTradingDataAccessService() {
		return null;
	}

	public static IHedgingPositionDataAccessService getHedgingPositionDataAccessService() {
		return null;
	}

	public static IAnalyticalService getAnalyticalService() {
		return null;
	}
}
