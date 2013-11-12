package org.springframework.amqp.rabbit.stocks.service.data;


import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.amqp.rabbit.stocks.dto.Market;
import org.springframework.amqp.rabbit.stocks.dto.Price;

public class MarketDataStorage extends AbstractDataStorage {

	private static MarketDataStorage instance = new MarketDataStorage(); 
	
	private Map<String, Market> markets = Collections.synchronizedMap(new TreeMap<String, Market>());
	
	private MarketDataStorage() { }
	
	public static MarketDataStorage getInstance() {
		return instance;
	}
	
	public void addMarket(String id, Market market) {
		markets.put(id, market);
		changedIds.add(id); 
	}
	
	public Market getMarket(String id) {
		return markets.get(id);
	}
	
	public Set<String> getMarkets() {
		return markets.keySet();
	}
	
	/**
	 * 
	 * @param id
	 * @param market
	 * @return true if market already exists, false otherwise
	 */
	public boolean updateMarket(String id, Market market) {
		if(markets.containsKey(id)) {
			updateChanges(id, market);
			markets.remove(id);
			markets.put(id, market);
			return true;
		}
		addMarket(id, market);
		return false;
	}
	
	private void updateChanges(String id, Market market) {
		if(!markets.get(id).equals(market)) {
			changedIds.add(id);  
		} else {
			changedIds.remove(id);
		}
	}
	
	
	
}
