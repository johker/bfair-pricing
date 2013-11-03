package org.springframework.amqp.rabbit.stocks.dto;

public class Price {
	
	private double theoretical;
	
	private String marketId;

	public double getTheoretical() {
		return theoretical;
	}

	public void setTheoretical(double theoretical) {
		this.theoretical = theoretical;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	
	

}
