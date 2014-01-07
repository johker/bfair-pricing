package org.springframework.amqp.rabbit.stocks.dto.utils;

import java.util.Comparator;

import org.springframework.amqp.rabbit.stocks.dto.IData;


public class DataPriceComparator implements Comparator<IData> {

	public int compare(IData id1, IData id2) {
		return (int) Math.ceil(id1.getPrice() - id2.getPrice());
	}



	
	
}
