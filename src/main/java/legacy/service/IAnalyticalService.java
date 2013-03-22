package legacy.service;

import legacy.dto.Book;

public interface IAnalyticalService {
	Integer getRetrieveStockByActiveGK(Object id, String transactionWay);

	Book getBookByName(String bookName);
}
