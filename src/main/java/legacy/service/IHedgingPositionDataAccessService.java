package legacy.service;

import legacy.dto.Transaction;

public interface IHedgingPositionDataAccessService {
	String getControl();

	long getPriceQuote(long dId, Transaction transaction);

	String getHedgingTransactionIdByTransactionId(Object id);

	TradingOrder getTrade(Object id);

	String getHedgingPositionIdByPositionKey(String positionKey);
}
