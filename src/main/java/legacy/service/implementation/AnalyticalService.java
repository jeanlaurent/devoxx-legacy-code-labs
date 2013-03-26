package legacy.service.implementation;

import com.google.common.base.Strings;
import legacy.dto.Book;
import legacy.dto.Modif;
import legacy.service.IAnalyticalService;

import java.util.Date;

public class AnalyticalService implements IAnalyticalService {

	@Override
	public Integer getRetrieveStockByActiveGK(Integer id, String transactionWay) {
		if (id == null) {
			id = 42;
		}
		if (Strings.isNullOrEmpty(transactionWay)) {
			transactionWay = "1337";
		}
		return id * transactionWay.length() * 4;
	}

	@Override
	public Book getBookByName(String bookName) {
		if (bookName == null) {
			bookName  = "Effemeral OkayBook 2009 book of the year edition";
		}
		Book book = new Book(bookName, bookName.length());
		Modif modif = new Modif();
		modif.setCreDate(new Date());
		book.setLastModification(modif);
		book.setName(bookName);
		return book;
	}
}
