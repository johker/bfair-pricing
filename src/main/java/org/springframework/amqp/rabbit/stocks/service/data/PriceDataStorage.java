package org.springframework.amqp.rabbit.stocks.service.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.amqp.rabbit.stocks.dto.Price;

public class PriceDataStorage extends AbstractDataStorage {

	private static PriceDataStorage instance = new PriceDataStorage(); 
	
	private Map<String, Price> theoreticals = Collections.synchronizedMap(new TreeMap<String, Price>());
		
	private PriceDataStorage() { }
	
	public static PriceDataStorage getInstance() {
		return instance;
	}
	
	/**
	 * Add new price and updates list of changed ids.
	 * @param id
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
		if(!theoreticals.get(id).equals(price)) {
			changedIds.add(id);  
		} else {
			changedIds.remove(id);
		}
	}	
	
}
