package org.springframework.amqp.rabbit.stocks.service.stubs;

import org.springframework.amqp.rabbit.stocks.dto.Market;
import org.springframework.amqp.rabbit.stocks.service.MarketDataService;
import org.springframework.amqp.rabbit.stocks.service.data.MarketDataStorage;
import org.springframework.amqp.rabbit.stocks.service.data.PriceDataStorage;

public class MarketDataServiceStub implements MarketDataService {


	public void updateMarketData(Market market) {
		MarketDataStorage.getInstance().updateMarket(market.getMarketId(), market);
	}

	public String[] getChangedIds() {
		return MarketDataStorage.getInstance().getChangedIds();
	}

}
