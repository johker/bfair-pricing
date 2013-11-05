package org.springframework.amqp.rabbit.stocks.service;

import org.springframework.amqp.rabbit.stocks.dto.Market;
import org.springframework.amqp.rabbit.stocks.dto.Price;

public interface PriceCalculationService {

	Price updatePrice(Market market);
	
	
}
