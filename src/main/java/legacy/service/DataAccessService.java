package legacy.service;

import legacy.service.implementation.AnalyticalService;
import legacy.service.implementation.HedgingPositionDataAccessServiceImpl;
import legacy.service.implementation.TradingDataAccessServiceImpl;
import legacy.service.implementation.TransactionManagerService;

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
		return new TransactionManagerService();
	}

	public static ITradingDataAccessService getTradingDataAccessService() {
		return new TradingDataAccessServiceImpl();
	}

	public static IHedgingPositionDataAccessService getHedgingPositionDataAccessService() {
		return new HedgingPositionDataAccessServiceImpl();
	}

	public static IAnalyticalService getAnalyticalService() {
		return new AnalyticalService();
	}
}
