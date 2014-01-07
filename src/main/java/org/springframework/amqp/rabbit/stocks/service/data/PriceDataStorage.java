package org.springframework.amqp.rabbit.stocks.service.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.amqp.rabbit.stocks.dto.Price;
import org.springframework.amqp.rabbit.stocks.dto.utils.PriceComparator;

public class PriceDataStorage extends AbstractDataStorage {

	private static PriceDataStorage instance = new PriceDataStorage(); 
	
	private Map<String, Price> theoreticals = Collections.synchronizedMap(new TreeMap<String, Price>());
		
	private Comparator<Price> priceComparator = new PriceComparator();
	
	private PriceDataStorage() { }
	
	public static PriceDataStorage getInstance() {
		return instance;
	}
	
	/**
	 * Add new price and updates list of changed selection ids.
	 * @param selection id
	 * @param price
	 */
	public void addPrice(String id, Price price) {
		theoreticals.put(id, price);
		changedIds.add(id);  
	}
	
	public Price getPrice(String id) {
		return theoreticals.get(id);
	}
	
	/**
	 * Updates price if it exists, otherwise adds new entry 
	 * @param id
	 * @param price
	 * @return true if price already exists, false otherwise
	 */
	public boolean updatePrice(String id, Price price) {
		if(theoreticals.containsKey(id)) {
			updateChanges(id, price);
			theoreticals.remove(id);
			theoreticals.put(id, price);			
			return true;
		}
		addPrice(id, price);
		return false;
	}
	
	private void updateChanges(String id, Price price) {
		if(priceComparator.compare(theoreticals.get(id), price) != 0) {
			changedIds.add(id);  
		} else {
			changedIds.remove(id);
		}
	}	
	
}
