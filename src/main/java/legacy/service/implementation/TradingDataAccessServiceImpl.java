package legacy.service.implementation;

import legacy.dto.DateRange;
import legacy.dto.Modif;
import legacy.dto.Transaction;
import legacy.service.ITradingDataAccessService;
import legacy.service.TransactionWay;

import java.util.Date;

public class TradingDataAccessServiceImpl implements ITradingDataAccessService {

	@Override
	public Transaction getTransactionById(int id) {
		System.out.println("[remote] Enabling transaction to data center FX01 (Alabama, USA). Please wait.");
		synchronizationTimer(2);
		System.out.println("[remote] FX01 not responding");
		System.out.println("[remote] Enabling transaction to data center FX07 (Honk-Kong). Please wait.");
		synchronizationTimer(3);
		System.out.println("[remote] Connection under way.");
		synchronizationTimer(1);
		System.out.println("[remote] Connection established.");
		synchronizationTimer(1);
		System.out.println("[remote] Checking Credentials.");
		synchronizationTimer(1);
		System.out.println("[remote] All systems go.");
		Transaction transaction = new Transaction();
		transaction.setCreDate(new Date());
		Modif modif = new Modif();
		modif.setCreDate(new Date());
		modif.setAuditableClassName("transaction.class");
		modif.setModificationDate(new Date());
		modif.setVersion(id % 10);
		modif.setUpdateVersion(modif.getVersion() + 1);
		transaction.setLastModification(modif);
		transaction.setUpdateVersion(id % 5);
		transaction.setBookName("Golgoth Supra Book 200" + id % 10);
		transaction.setOuterEdge(id * 9L);
		transaction.setIssueDate(new Date());
		DateRange dateRange = new DateRange();
		dateRange.setCreDate(new Date());
		dateRange.setUpdateVersion(93);
		dateRange.setUpdateDate(new Date(2013,9,9));
		transaction.setDateRange(dateRange);
		transaction.setIssueDate(new Date(2010,9,9));
		transaction.setPositionKey("POS_" + id + "_cheval");
		transaction.setWay(TransactionWay.LONG);
		if (id % 2 == 0) {
			transaction.setWay(TransactionWay.SHORT);
		}
		transaction.setVersion(3);
		transaction.setId(id);
		System.out.println("[remote] MOTD: Cobol FTW !");
		return transaction;
	}

	protected void synchronizationTimer(int countInSeconds) {
		try {
			Thread.sleep(countInSeconds * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int getOptionalIdFromTransaction(Transaction transaction) {
		return transaction.getId() * 5;
	}

	@Override
	public long computeDPSOnTheGrid(Long id) {
		return (long) Math.ceil(Math.random() * 100) ^ 2;
	}
}
