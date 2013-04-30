package legacy.service;

import legacy.dto.Amount;

public class TradingOrder {

	private double portfolio_bl;
	private Amount price;
	private String fixedCCY;

	public double getPortfolio_bl() {
		return portfolio_bl;
	}

	public void setPortfolio_bl(double portfolio_bl) {
		this.portfolio_bl = portfolio_bl;
	}

	public Amount getPrice() {
		return price;
	}

	public void setPrice(Amount price) {
		this.price = price;
	}

	public String getFixedCCY() {
		return fixedCCY;
	}

	public void setFixedCCY(String fixedCCY) {
		this.fixedCCY = fixedCCY;
	}
}
