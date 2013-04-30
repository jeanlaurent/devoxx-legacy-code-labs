package legacy.hedge; /**
 *
 *
 */

import legacy.dto.ErrorLevel;
import legacy.persistence.AuditedField;
import legacy.persistence.BaseDTO;
import legacy.persistence.StorageActionEnum;
import legacy.service.Position;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 *     Title: legacy.hedge.HedgingPosition
 * </p>
 * <P>
 *     Description: bug, n: A son of a glitch.
 * </P>
 * @author mfugain
 * @version 10
 * @creationDate May 12, 2007
 */
public class HedgingPosition extends BaseDTO implements Position {

	/******************* Data required for processing*************************/
	@AuditedField
	private int transactionId;
	@AuditedField
	private HedgingPositionTypeConst type = HedgingPositionTypeConst.INI;
	@AuditedField
	private HedgingPositionStatusConst status;
	@AuditedField
	private Date valueDate;
	@AuditedField
	private Date noticePeriodEndDate;
	@AuditedField
	private String combck;
	@AuditedField
	private int codetyptkt;

	private String transactionWay;

	private ErrorLevel errorLevel;

	//private int assetCopy;
	private String hedgeMsg;

	private StorageActionEnum storageAction;

	// Not Stored in database but use in Position processing order form 3
	private double prxref;

	private double basprx = 100;
	private Date daprx;
	/******************** Input Valuation Data *********************/
	private String quantity;

	/******************* Inpupt Front data ********************/
	private Date datefinthe;

	private BigInteger codtyptra = new BigInteger("42");
	/************************** Return Code Type **************/
	private String msgdev;

	private String msgerr;
	private Integer niverr;
	private String msgusr;
	private String ikRtH;
	private String hedgingTransactionId;

	/**
	 * HedgingPosition Constructor
	 */
	public HedgingPosition() {

	}

	/**
	 * @return the transactionId
	 */
	@Override
  public int getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 * 			the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 *
	 * @return type
	 */
  public HedgingPositionTypeConst getType() {
		return type;
	}

	/**
	 * @param type
	 * 	the type to set
	 */
	public void setType(HedgingPositionTypeConst type) {
		this.type = type;
	}

  public HedgingPositionStatusConst getStatus() {
		return status;
	}

	public void setStatus(HedgingPositionStatusConst status) {
		this.status = status;
	}

	@Override
  public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	@Override
  public Date getNoticePeriodEndDate() {
		return noticePeriodEndDate;
	}

	public void setNoticePeriodEndDate(Date noticePeriodEndDate) {
		this.noticePeriodEndDate = noticePeriodEndDate;
	}

	// TODO: Rename this to CombockArray
	@Override
  public String getCombck() {
		return combck;
	}

	public void setCombck(String combck) {
		this.combck = combck;
	}

	@Override
  public BigInteger getCodtyptra() {
		return codtyptra;
	}

	public void setCodtyptra(BigInteger codtyptra) {
		this.codtyptra = codtyptra;
	}

	@Override
  public int getCodetyptkt() {
		return codetyptkt;
	}

	public void setCodetyptkt(int codetyptkt) {
		this.codetyptkt = codetyptkt;
	}

	@Override
  public double getPrxref() {
		return prxref;
	}

	public void setPrxref(double prxref) {
		this.prxref = prxref;
	}

	@Override
  public double getBasprx() {
		return basprx;
	}

	public void setBasprx(double basprx) {
		this.basprx = basprx;
	}

	@Override
  public Date getDaprx() {
		return daprx;
	}

	public void setDaprx(Date daprx) {
		this.daprx = daprx;
	}

	@Override
  public String getQuantity() {
		return quantity;
	}

	// FIXME: Quantity should be an integer
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
  public String getTransactionWay() {
		return transactionWay;
	}

	public void setTransactionWay(String transactionWay) {
		this.transactionWay = transactionWay;
	}

	@Override
  public String getMsgdev() {
		return msgdev;
	}

	public void setMsgdev(String msgdev) {
		this.msgdev = msgdev;
	}

	@Override
  public String getMsgerr() {
		return msgerr;
	}

	public void setMsgerr(String msgerr) {
		this.msgerr = msgerr;
	}

	@Override
  public Integer getNiverr() {
		return niverr;
	}

	public void setNiverr(Integer niverr) {
		this.niverr = niverr;
	}

	@Override
  public String getMsgusr() {
		return msgusr;
	}

	public void setMsgusr(String msgusr) {
		this.msgusr = msgusr;
	}

	@Override
  public ErrorLevel getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(ErrorLevel errorLevel) {
		this.errorLevel = errorLevel;
	}

	@Override
  public String getHedgeMsg() {
		return hedgeMsg;
	}

	public void setHedgeMsg(String hedgeMsg) {
		this.hedgeMsg = hedgeMsg;
	}

	@Override
  public Date getDatefinthe() {
		return datefinthe;
	}

	public void setDatefinthe(Date datefinthe) {
		this.datefinthe = datefinthe;
	}

	@Override
  public StorageActionEnum getStorageUpdate() {
		return storageAction;
	}

	public void setStorageUpdate(StorageActionEnum storageAction) {
		this.storageAction = storageAction;
	}

	public void setIkRtH(String ikRtH) {
		this.ikRtH = ikRtH;
	}

	@Override
  public String getIkRtH() {
		return ikRtH;
	}

	public void setHedgingTransactionId(String hedgingTransactionId) {
		this.hedgingTransactionId = hedgingTransactionId;
	}

	@Override
  public String getHedgingTransactionId() {
		return hedgingTransactionId;
	}
}
