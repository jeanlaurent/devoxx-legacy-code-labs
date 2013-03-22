package legacy.dto;

import legacy.persistence.BaseDTO;
import legacy.service.TransactionWay;

import java.util.Date;

public class Transaction extends BaseDTO {
	private Date valueDate;
	private Long outerEdge;
	private TransactionWay way;
	private String bookName;
	private String positionKey;
	private Date issueDate;
	private Date tradeDate;
	private DateRange dateRange;

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Long getOuterEdge() {
		return outerEdge;
	}

	public TransactionWay getWay() {
		return way;
	}

	public void setWay(TransactionWay way) {
		this.way = way;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPositionKey() {
		return positionKey;
	}

	public void setPositionKey(String positionKey) {
		this.positionKey = positionKey;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public void setOuterEdge(Long outerEdge) {
		this.outerEdge = outerEdge;
	}
}
