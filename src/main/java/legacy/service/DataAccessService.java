package legacy.service;

import legacy.service.funky.FunkyAnalyticalService;
import legacy.service.funky.FunkyHedgingPositionDataAccessServiceImpl;
import legacy.service.funky.FunkyTradingDataAccessServiceImpl;
import legacy.service.funky.FunkyTransactionManagerService;

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
		return new FunkyTransactionManagerService();
	}

	public static ITradingDataAccessService getTradingDataAccessService() {
		return new FunkyTradingDataAccessServiceImpl();
	}

	public static IHedgingPositionDataAccessService getHedgingPositionDataAccessService() {
		return new FunkyHedgingPositionDataAccessServiceImpl();
	}

	public static IAnalyticalService getAnalyticalService() {
		return new FunkyAnalyticalService();
	}
}
