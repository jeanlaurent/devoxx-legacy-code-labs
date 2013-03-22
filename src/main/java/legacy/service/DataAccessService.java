package legacy.service;

import legacy.service.funky.FunkyAnalyticalService;
import legacy.service.funky.FunkyHedgingPositionDataAccessServiceImpl;
import legacy.service.funky.FunkyTradingDataAccessServiceImpl;
import legacy.service.funky.FunkyTransactionManagerService;

public class DataAccessService {

	static {
		System.out.println("==================================================");
		System.out.println("=******===****===****===******===******===*******=");
		System.out.println("=          DATA ACCESS SERVICE INITIATED         =");
		System.out.println("=******===****===****===******===******===*******=");
		System.out.println("==================================================");
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
