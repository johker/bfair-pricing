package org.springframework.amqp.rabbit.stocks.jsonproducer;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;
import org.springframework.amqp.rabbit.stocks.dto.Price;
import org.springframework.amqp.support.converter.MessageConverter;


public class RabbitPricingGateway extends RabbitGatewaySupport implements PricingGateway {
	
	
	private static Log logger = LogFactory.getLog(RabbitPricingGateway.class); 
	
	MessageProperties props = new MessageProperties();

	private MessageConverter messageConverter;
	
	private final AtomicInteger counter = new AtomicInteger();
	
	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	public void sendPriceData() {
		props.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		props.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		Price price = new Price();
		price.setMarketId("" + counter.incrementAndGet());
		price.setTheoretical(Math.random() * 100);		
		getRabbitTemplate().convertAndSend(getMessageConverter().toMessage(price, props));
	}

	
	
	
}
