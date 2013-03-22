package legacy.service;

import legacy.dto.Book;

public interface IAnalyticalService {
	Integer getRetrieveStockByActiveGK(Integer id, String transactionWay);

	Book getBookByName(String bookName);
}
