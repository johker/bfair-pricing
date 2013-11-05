package org.springframework.amqp.rabbit.stocks.service.stubs;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.rabbit.stocks.dto.Item;
import org.springframework.amqp.rabbit.stocks.dto.Market;
import org.springframework.amqp.rabbit.stocks.dto.Price;
import org.springframework.amqp.rabbit.stocks.dto.Runner;
import org.springframework.amqp.rabbit.stocks.service.PriceCalculationService;

public class PriceCalculationServiceStub implements PriceCalculationService {

	 private final AtomicInteger counter = new AtomicInteger();
	
	public Price updatePrice(Market market) {
		for(Runner r : market.getRunners()) {
			if(r == null) continue;
			
			for(Item i : r.getAvailableToBack()) {			
				if(i != null) {
					//System.out.println(i.getPrice());
				}
			}
		}
		
		Price price = new Price();
		price.setMarketId("" + counter.incrementAndGet());
		price.setTheoretical(Math.random() * 100);

		return price;
	}

	
	
}
