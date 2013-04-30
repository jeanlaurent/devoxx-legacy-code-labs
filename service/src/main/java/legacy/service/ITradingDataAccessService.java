package legacy.service;

import legacy.dto.Transaction;

public interface ITradingDataAccessService {

	Transaction getTransactionById(int id);

	int getOptionalIdFromTransaction(Transaction transaction);

	long computeDPSOnTheGrid(Long id);
}
