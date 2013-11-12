package org.springframework.amqp.rabbit.stocks.service;

import org.springframework.amqp.rabbit.stocks.dto.Market;

public interface MarketDataService {

	void updateMarketData(Market market);
	
	String[] getChangedIds();
	
}
