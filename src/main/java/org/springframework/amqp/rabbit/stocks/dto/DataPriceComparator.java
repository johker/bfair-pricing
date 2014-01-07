package org.springframework.amqp.rabbit.stocks.dto;

import java.util.Comparator;

public class DataPriceComparator implements Comparator<IData> {

	public int compare(IData id1, IData id2) {
		return (int) Math.ceil(id1.getPrice() - id2.getPrice());
	}



	
	
}
