package org.springframework.amqp.rabbit.stocks.service.stubs;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.stocks.dto.Market;
import org.springframework.amqp.rabbit.stocks.service.MarketDataService;
import org.springframework.amqp.rabbit.stocks.service.data.MarketDataStorage;



public class MarketDataServiceStub implements MarketDataService {

	static Logger log = Logger.getLogger(MarketDataServiceStub.class.getName());
	
	public void updateMarketData(Market market) {		
		MarketDataStorage.getInstance().updateMarket(market.getMarketId(), market);
	}

	public String[] getChangedIds() {
		return MarketDataStorage.getInstance().getChangedIds();
	}

}
