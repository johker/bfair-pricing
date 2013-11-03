package org.springframework.amqp.rabbit.stocks.jsonproducer;

public interface PricingGateway {

	void sendPriceData();
	
}
