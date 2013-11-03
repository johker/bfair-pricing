/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.amqp.rabbit.stocks.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.stocks.domain.TradeRequest;
import org.springframework.amqp.rabbit.stocks.domain.TradeResponse;
import org.springframework.amqp.rabbit.stocks.service.CreditCheckService;
import org.springframework.amqp.rabbit.stocks.service.ExecutionVenueService;
import org.springframework.amqp.rabbit.stocks.service.TradingService;
import org.springframework.util.StringUtils;


/**
 * POJO handler that receives trade requests and sends back a trade response.  Main application
 * logic sits here which coordinates between {@link ExecutionVenueService}, {@link CreditCheckService}, 
 * and {@link TradingService}.
 * 
 * @author Mark Pollack
 *
 */
public class ServerHandler {

    private ExecutionVenueService executionVenueService;

    private CreditCheckService creditCheckService;

    private TradingService tradingService;
    
    private final AtomicInteger counter = new AtomicInteger();
	
	public ServerHandler(ExecutionVenueService executionVenueService,
						 CreditCheckService creditCheckService, 
						 TradingService tradingService) {
		this.executionVenueService = executionVenueService;
		this.creditCheckService = creditCheckService;
		this.tradingService = tradingService;
	}
	
	
	public org.springframework.amqp.rabbit.stocks.dto.Price handleMessage(org.springframework.amqp.rabbit.stocks.dto.Market data) {	
			
			for(org.springframework.amqp.rabbit.stocks.dto.Runner r : data.getRunners()) {
				if(r == null) continue;
				
				for(org.springframework.amqp.rabbit.stocks.dto.Item i : r.getAvailableToBack()) {			
					if(i != null) {
						System.out.println(i.getPrice());
					}
				}
			}
			
			org.springframework.amqp.rabbit.stocks.dto.Price price = new org.springframework.amqp.rabbit.stocks.dto.Price();
			price.setMarketId("" + counter.incrementAndGet());
			price.setTheoretical(Math.random() * 100);

			
			return price;
			
		}
	

	public TradeResponse handleMessage(TradeRequest tradeRequest)
	{
        TradeResponse tradeResponse;
        List<?> errors = new ArrayList<Object>();
        if (creditCheckService.canExecute(tradeRequest, errors))
        {
            tradeResponse = executionVenueService.executeTradeRequest(tradeRequest);
        }
        else
        {
            tradeResponse = new TradeResponse();
            tradeResponse.setError(true);
            tradeResponse.setErrorMessage(StringUtils.arrayToCommaDelimitedString(errors.toArray()));
            
        }
        tradingService.processTrade(tradeRequest, tradeResponse);
        return tradeResponse;
	}
	
	
	
}
