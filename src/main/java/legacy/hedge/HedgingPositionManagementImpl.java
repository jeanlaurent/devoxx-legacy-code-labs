package legacy.hedge;

import legacy.DateTimeUtils;
import legacy.dto.Modif;
import legacy.security.User;
import legacy.error.ARPSystemException;
import legacy.error.CheckResult;
import legacy.dto.Book;
import legacy.security.UserSessionsManager;
import legacy.service.DataAccessService;
import legacy.service.IHedgingPositionDataAccessService;
import legacy.service.ITradingDataAccessService;
import legacy.service.ITransactionManagerService;
import legacy.service.TradingOrder;
import legacy.dto.Transaction;
import legacy.persistence.StorageActionEnum;
import org.apache.commons.lang3.SerializationUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HedgingPositionManagementImpl implements IHedgingPositionManagement {

    private static Logger LOGGER = Logger.getLogger(HedgingPositionManagementImpl.class.getName());

    private HedgingPositionMgt hedgingPositionMgt = new HedgingPositionMgt();

    public void setHedgingPositionMgt(HedgingPositionMgt hedgingPositionMgt) {
        this.hedgingPositionMgt = hedgingPositionMgt;
    }

    private DataAccessService dataAccessService = new DataAccessService();

    public void setDataAccessService(DataAccessService dataAccessService) {
        this.dataAccessService = dataAccessService;
    }

    private ITransactionManagerService transactionManagerService = dataAccessService.getTransactionManagerService();

    @Override
	public CheckResult<HedgingPosition> initAndSendHedgingPosition(HedgingPosition hp) throws ARPSystemException {
		CheckResult<HedgingPosition> result = new CheckResult<>();
		try {
			hp = initHedgingPosition(hp);
		} catch (Exception e) {
			String errorMsg = "TECHNICAL ERROR, cannot initialize HP to send";
			LOGGER.log(Level.SEVERE, errorMsg, e);
			String msg = hp.getErrorLevel().createHMsgFromError();
			hp.setHedgeMsg(msg);
			result.setCheckIsOk(false);
			try {
				updateHedgingPosition(hp);
			} catch (ARPSystemException e1) {
				LOGGER.log(Level.SEVERE, errorMsg, e1);
			}
			return result;
		}
		try {
			result = hedgePositionBySendTo3rdParty(hp);
			if(result.isCheckIsOk()) {
				hp = result.getResult();
				hp.setStatus(HedgingPositionStatusConst.HEDGED);
				updateHedgingPosition(hp);
			} else {
				hp = result.getResult();
				switch(hp.getErrorLevel()){
					case FUNCTIONAL_ERROR:{
						hp.setStatus(HedgingPositionStatusConst.REJECTED);
						break;
					}
					case CONNECT_ERROR: {
						hp.setStatus(HedgingPositionStatusConst.REJECTED);
						break;
					}
					case BOOKING_MALFUNCTION: {
						//TO DO
						break;
					}
					default: {
						break;
					}
				}
				updateHedgingPosition(hp);
			}
		} catch(ARPSystemException e) {
			LOGGER.log(Level.SEVERE,e.getMessage(), e);
		}
		return result;
	}

	private CheckResult<HedgingPosition> hedgePositionBySendTo3rdParty(HedgingPosition hp) {
		return hedgingPositionMgt.hedgingPositionMgt(hp);
	}

	private HedgingPosition updateHedgingPosition(HedgingPosition hp) {
		try {
			if (hp.getType().equals(HedgingPositionTypeConst.INI)) {
				Modif modif = new Modif();
				modif.setCreDate(new Date());
				hp.setLastModification(modif);
				hp.setStorageUpdate(StorageActionEnum.CREATE);
			} else {
				hp.setStorageUpdate(StorageActionEnum.UPDATE);
			}
            return transactionManagerService.classStorageAction(hp);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
			throw e;
		}
	}

	private HedgingPosition initHedgingPosition(HedgingPosition hp) {
		ITradingDataAccessService trading = dataAccessService.getTradingDataAccessService();
		IHedgingPositionDataAccessService hpdas = dataAccessService.getHedgingPositionDataAccessService();
		Transaction transaction = trading.getTransactionById(hp.getId());
		long dId = trading.getOptionalIdFromTransaction(transaction);

		double price = hpdas.getPriceQuote(dId, transaction);
        trading.computeDPSOnTheGrid(transaction.getOuterEdge());
		String combck = dId + " " + transaction.getId() + " CONTROL: [" + hpdas.getControl() + "]";
		Date valueDate;
		try {
			valueDate = hp.getValueDate();
		} catch(Exception e) {
			valueDate = transaction.getValueDate();
		}

		String hedgingTransactionId = "";
		if (!HedgingPositionTypeConst.INI.equals(hp.getType())) {
			hedgingTransactionId = hpdas.getHedgingTransactionIdByTransactionId(transaction.getId());
		}
		String userIni = getUser();
		hp.setIkRtH(userIni);
		switch (hp.getType()) {
			case INI: {
				String transactionWay = "";
				switch (transaction.getWay()) {
					case LONG:
						transactionWay = "L";
						break;
					case SHORT:
						transactionWay = "S";
						break;
					default:
						break;
				}
				int bodCode;
				Integer stock = dataAccessService.getAnalyticalService().getRetrieveStockByActiveGK(transaction.getId(), transactionWay);
				TradingOrder evt = hpdas.getTrade(transaction.getId());
				boolean isStockForbidden = false;
				if (stock == null) {
					isStockForbidden = true;
				}
				if (!isStockForbidden) {
					Book book = dataAccessService.getAnalyticalService().getBookByName(transaction.getBookName());
					bodCode = book.getCode();
				} else {
					Book book = dataAccessService.getAnalyticalService().getBookByName(transaction.getBookName() + "-instock");
					bodCode = Integer.parseInt(book.getPortfolioIdFromRank());
				}
				/*********************************** INPUT DEAL DATA *********************/
				hp.setTransactionWay(transactionWay);
				hp.setCodetyptkt(34);
				hp.setCodtyptra(BigInteger.valueOf(bodCode));
				hp.setQuantity(String.valueOf(evt.getPrice().getQuantity()));
				hp.setBasprx(evt.getPrice().getFxPrice() / 100);
				hp.setPrxref(evt.getPrice().getFxPrice());
				hp.setCombck(combck);
				/*********************************** INPUT EVENT DATA *********************/
				hp.setTransactionId(transaction.getId());
				hp.setValueDate(valueDate);
			}
			case CANCEL_TRANSACTION:
				/*********************************** INPUT DEAL DATA *********************/
                hpdas.getHedgingPositionIdByPositionKey(transaction.getPositionKey());

				hp.setCodetyptkt(20);
				/*********************************** INPUT EVENT DATA *********************/
				hp.setValueDate(valueDate);
				break;
			case EXT:
				TradingOrder evt = hpdas.getTrade(transaction.getId());
				double fxprice = -1d;
				if (evt !=null ){
					price = evt.getPrice().getPrice();
					fxprice = evt.getPrice().getFxPrice();
				}
				if (price > 0) {
					price = price * fxprice;
				}
				/*********************************** INPUT DEAL DATA *********************/
				hp.setBasprx(price / 100);
				hp.setPrxref(price);
				hp.setCodetyptkt(42);
				hp.setQuantity(String.valueOf(evt.getPrice().getQuantity()));
				/*********************************** INPUT EVENT DATA *********************/
				Date issueDate = transaction.getIssueDate();
				Date tradeDate = transaction.getTradeDate();
				if (DateTimeUtils.compareDate(issueDate,tradeDate)) {
					hp.setCreDate(issueDate);
					hp.setDaprx(tradeDate);
					hp.setDatefinthe(valueDate);
				} else {
					hp.setCreDate(issueDate);
					hp.setDaprx(tradeDate);
					hp.setDatefinthe(tradeDate);
					hp.setCombck(combck);
				}
				hp.setValueDate(valueDate);
				break;
			case CANCEL_POSITION:
				/*********************************** INPUT DEAL DATA *********************/
				hp.setCodetyptkt(20);
				hp.setHedgingTransactionId(hedgingTransactionId);
				/*********************************** INPUT EVENT DATA *********************/
				hp.setValueDate(valueDate);
				break;
		}

		return hp;
 	}

	private String getUser() {
		User user = UserSessionsManager.getInstance().getCurrentUser();
		if (user !=null) {
			return user.getName();
		} else {
			return "autobot";
		}
	}


}
