package legacy.service;

import legacy.service.implementation.AnalyticalService;
import legacy.service.implementation.HedgingPositionDataAccessServiceImpl;
import legacy.service.implementation.TradingDataAccessServiceImpl;
import legacy.service.implementation.TransactionManagerService;

public class DataAccessService {

    public DataAccessService() {
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
	public ITransactionManagerService getTransactionManagerService() {
		return new TransactionManagerService();
	}

	public ITradingDataAccessService getTradingDataAccessService() {
		return new TradingDataAccessServiceImpl();
	}

	public IHedgingPositionDataAccessService getHedgingPositionDataAccessService() {
		return new HedgingPositionDataAccessServiceImpl();
	}

	public IAnalyticalService getAnalyticalService() {
		return new AnalyticalService();
	}
}
