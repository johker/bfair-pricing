package org.springframework.amqp.rabbit.stocks.gateway;

import org.springframework.amqp.rabbit.stocks.dto.Price;
import org.springframework.amqp.rabbit.stocks.service.PriceDataService;

public class TestPriceDataGateway implements PriceDataService {

	@Override
	public void updatePrice(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Price getPrice(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recalculatePrices(String[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getChangedIds() {
		// TODO Auto-generated method stub
		return null;
	}

}
