package legacy.hedge;

import legacy.DateTimeUtils;
import legacy.dto.Book;
import legacy.dto.Modif;
import legacy.dto.Transaction;
import legacy.error.ARPSystemException;
import legacy.error.CheckResult;
import legacy.persistence.StorageActionEnum;
import legacy.security.User;
import legacy.security.UserSessionsManager;
import legacy.service.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HedgingPositionManagementImpl implements IHedgingPositionManagement {

    private static Logger LOGGER = Logger.getLogger(HedgingPositionManagementImpl.class.getName());

    private HedgingPositionMgt hedgingPositionMgt;
    private DataAccessService dataAccessService;

    private ITransactionManagerService transactionManagerService;

    public HedgingPositionManagementImpl() {
        hedgingPositionMgt = new HedgingPositionMgt();
        dataAccessService = new DataAccessService();
        transactionManagerService = dataAccessService.getTransactionManagerService();
    }

    protected HedgingPositionManagementImpl(DataAccessService dataAccessService, HedgingPositionMgt hedgingPositionMgt) {
        this.dataAccessService = dataAccessService;
        this.hedgingPositionMgt = hedgingPositionMgt;
        transactionManagerService = dataAccessService.getTransactionManagerService();
    }

    @Override
	public CheckResult<HedgingPosition> initAndSendHedgingPosition(HedgingPosition hp) throws ARPSystemException {
        CheckResult<HedgingPosition> result;
		try {
			hp = initHedgingPosition(hp);

            try {
                result = hedgePositionBySendTo3rdParty(hp);
                hp = result.getResult();
                if(result.isCheckIsOk()) {
                    hp.setStatus(HedgingPositionStatusConst.HEDGED);
                } else {
                    switch(hp.getErrorLevel()){
                        case FUNCTIONAL_ERROR:
                        case CONNECT_ERROR:
                            hp.setStatus(HedgingPositionStatusConst.REJECTED);
                            break;
                        case BOOKING_MALFUNCTION:
                        default: {
                            break;
                        }
                    }
                }
            } catch(ARPSystemException e) {
                LOGGER.log(Level.SEVERE,e.getMessage(), e);
                return new CheckResult<>();
            }
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "TECHNICAL ERROR, cannot initialize HP to send", e);
			hp.setHedgeMsg(hp.getErrorLevel().createHMsgFromError());
		    result = new CheckResult<>(false);
		}
        try {
            updateHedgingPosition(hp);
        } catch (ARPSystemException e1) {
            LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
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
                // useless, it's default value in ObjectDTO.
				//modif.setCreDate(new Date());
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
			case INI:
				String transactionWay = transaction.getWay().getValueForDto();
				int bodCode;
				Integer stock = dataAccessService.getAnalyticalService().getRetrieveStockByActiveGK(transaction.getId(), transactionWay);
				TradingOrder evt = hpdas.getTrade(transaction.getId());
				if (stock != null) {
					Book book = dataAccessService.getAnalyticalService().getBookByName(transaction.getBookName());
					bodCode = book.getCode();
				} else {
					Book book = dataAccessService.getAnalyticalService().getBookByName(transaction.getBookName() + "-instock");
					bodCode = Integer.parseInt(book.getPortfolioIdFromRank());
				}
				/*********************************** INPUT DEAL DATA *********************/
				hp.setTransactionWay(transactionWay);
				hp.setCodtyptra(BigInteger.valueOf(bodCode));
				hp.setQuantity(String.valueOf(evt.getPrice().getQuantity()));
				hp.setBasprx(evt.getPrice().getFxPrice() / 100);
				hp.setPrxref(evt.getPrice().getFxPrice());
				hp.setCombck(combck);
				/*********************************** INPUT EVENT DATA *********************/
				hp.setTransactionId(transaction.getId());
                hpdas.getHedgingPositionIdByPositionKey(transaction.getPositionKey());
                hp.setCodetyptkt(20);
                break;
			case CANCEL_TRANSACTION:
				/*********************************** INPUT DEAL DATA *********************/
                hpdas.getHedgingPositionIdByPositionKey(transaction.getPositionKey());
				hp.setCodetyptkt(20);
				/*********************************** INPUT EVENT DATA *********************/
				break;
			case EXT:
				TradingOrder evtExt = hpdas.getTrade(transaction.getId());
				double fxprice = -1d;
				if (evtExt !=null ){
					price = evtExt.getPrice().getPrice();
					fxprice = evtExt.getPrice().getFxPrice();
				}
				if (price > 0) {
					price = price * fxprice;
				}
				/*********************************** INPUT DEAL DATA *********************/
				hp.setBasprx(price / 100);
				hp.setPrxref(price);
				hp.setQuantity(String.valueOf(evtExt.getPrice().getQuantity()));
				/*********************************** INPUT EVENT DATA *********************/
				Date issueDate = transaction.getIssueDate();
				Date tradeDate = transaction.getTradeDate();
                hp.setCreDate(issueDate);
                hp.setDaprx(tradeDate);
                hp.setDatefinthe(valueDate);
                if (!DateTimeUtils.compareDate(issueDate,tradeDate)) {
					hp.setCombck(combck);
				}
                hp.setCodetyptkt(42);
				break;
			case CANCEL_POSITION:
				/*********************************** INPUT DEAL DATA *********************/
				hp.setCodetyptkt(20);
				hp.setHedgingTransactionId(hedgingTransactionId);
				/*********************************** INPUT EVENT DATA *********************/
				break;
        }
        hp.setValueDate(valueDate);

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
