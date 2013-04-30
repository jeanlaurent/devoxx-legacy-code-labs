package legacy.dto;

import legacy.persistence.BaseDTO;

public class Book extends BaseDTO {
	private String name;
	private int code;
	private String portfolioIdByRank;

	public Book(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPortfolioIdFromRank() {
		return portfolioIdByRank;
	}

	public void setPortfolioIdByRank(String portfolioIdByRank) {
		this.portfolioIdByRank = portfolioIdByRank;
	}
}
