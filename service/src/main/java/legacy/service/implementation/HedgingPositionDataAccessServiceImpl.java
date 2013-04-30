package legacy.service.implementation;

import com.google.common.base.Strings;
import legacy.dto.Amount;
import legacy.dto.Transaction;
import legacy.service.IHedgingPositionDataAccessService;
import legacy.service.TradingOrder;

import java.util.Date;

public class HedgingPositionDataAccessServiceImpl implements IHedgingPositionDataAccessService {

	@Override
	public String getControl() {
		return "0x0x0x01h";
	}

	@Override
	public long getPriceQuote(long dId, Transaction transaction) {
		long tmp = 0;
		switch (transaction.getWay()) {
			case LONG:
				tmp = transaction.getOuterEdge() * 1000;
				break;
			case SHORT:
				tmp = transaction.getOuterEdge() * 10;
				break;
		}
		tmp += dId * 24;
		return tmp;
	}

	@Override
	public String getHedgingTransactionIdByTransactionId(int id) {
		id = id >> 2;
		id = id * 24 + 42;
		return "0xid." + String.valueOf(id);
	}

	@Override
	public TradingOrder getTrade(Integer id) {
		TradingOrder tradingOrder = new TradingOrder();
		tradingOrder.setFixedCCY("2UP");
		if (id > 10) {
			tradingOrder.setFixedCCY("1UP");
		}
		tradingOrder.setPortfolio_bl((id * 47)^(id % 3));
		Amount amount = new Amount();
		amount.setCreDate(new Date());
		amount.setCurrency("USD");
		if (id > 100) {
			amount.setCurrency("EUR");
		}
		amount.setQuantity(id * 9);
		amount.setPrice(90^(id%3)+ id * 7);
		tradingOrder.setPrice(amount);
		return tradingOrder;
	}

	@Override
	public String getHedgingPositionIdByPositionKey(String positionKey) {
		if(Strings.isNullOrEmpty(positionKey)) {
			return "-1";
		}
		return "HP_" + String.valueOf(positionKey.length() * 4);
	}
}
