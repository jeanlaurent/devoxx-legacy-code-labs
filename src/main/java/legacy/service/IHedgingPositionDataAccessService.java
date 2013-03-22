package legacy.service;

import legacy.dto.Transaction;

public interface IHedgingPositionDataAccessService {
	String getControl();

	long getPriceQuote(long dId, Transaction transaction);

	String getHedgingPositionIdByPositionKey(String positionKey);

	String getHedgingTransactionIdByTransactionId(int id);

	TradingOrder getTrade(Integer id);
}
