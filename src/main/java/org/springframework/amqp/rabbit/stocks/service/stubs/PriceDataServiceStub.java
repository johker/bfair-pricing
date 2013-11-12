package org.springframework.amqp.rabbit.stocks.service.stubs;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.rabbit.stocks.dto.Item;
import org.springframework.amqp.rabbit.stocks.dto.Market;
import org.springframework.amqp.rabbit.stocks.dto.Price;
import org.springframework.amqp.rabbit.stocks.dto.Runner;
import org.springframework.amqp.rabbit.stocks.service.PriceDataService;
import org.springframework.amqp.rabbit.stocks.service.data.MarketDataStorage;
import org.springframework.amqp.rabbit.stocks.service.data.PriceDataStorage;

public class PriceDataServiceStub implements PriceDataService {

	 private final AtomicInteger updates = new AtomicInteger();
	
	 private Market getMarket(String id) {
		 return MarketDataStorage.getInstance().getMarket(id);
	 }
	 
	 private void updatePrice(Price price) {
		 PriceDataStorage.getInstance().updatePrice(price.getMarketId(), price);
	 }
	 
	 public Price getPrice(String id) {
			return PriceDataStorage.getInstance().getPrice(id);
		}
	 
	public void updatePrice(String id) {
		double[] range = new double[2];
		Market market = getMarket(id);
		for(Runner r : market.getRunners()) {
			if(r == null) continue;
			range = getPriceRange(r);
		}
		Price price = new Price();
		price.setMarketId(id);
		price.setTheoretical(range[0] + Math.random() * (range[1] - range[0]));
		updatePrice(price);
	}

	/**
	 * 
	 * @param runner
	 * @return
	 */
	private double[] getPriceRange(Runner runner) {
		double min = -1, max = -1;
		for(Item i : runner.getAvailableToBack()) {			
			if(i != null && (min == -1 || i.getPrice() < min)) {
				max = i.getPrice();
			}
		}
		for(Item i : runner.getAvailableToLay()) {			
			if(i != null && (max == -1 || i.getPrice() > max)) {
				min = i.getPrice();
			}
		}
		return new double[]{Math.max(0, min),Math.max(0, max)};
	}

	public String[] getChangedIds() {
		return PriceDataStorage.getInstance().getChangedIds();
	}

	public void recalculatePrices(String[] ids) {
		for(String id : ids) {
			 updatePrice(id);
		 }
		
	}



	


	
}
