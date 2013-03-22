package legacy.dto;

import legacy.persistence.BaseDTO;

public class Amount extends BaseDTO {
	private double quantity;
	private double price;
	private double fxPrice;
	private String Currency;

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getFxPrice() {
		return fxPrice;
	}

	public void setFxPrice(double fxPrice) {
		this.fxPrice = fxPrice;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}
}
