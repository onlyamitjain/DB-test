package com.test.com.test.timertask;

import com.test.Util;
import com.test.datastore.TradeStoreFactory;
import com.test.model.TradeRequest;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

public class UpdateMaturityDateTask extends TimerTask {

    @Override
    public void run() {
        Set<String> keys = TradeStoreFactory.getTradeStore().getAllTradeRequestKeys();

         keys.parallelStream().forEach(key ->
         {
             List<TradeRequest> tradeRequests =  TradeStoreFactory.getTradeStore().getTradeRequest(key);
             tradeRequests.parallelStream().filter(tradeRequest -> !tradeRequest.isExpiry()).forEach(tradeRequest -> {
                        Date maturityDate =   tradeRequest.getMaturityDate();
                        if(maturityDate != null && Util.isExpired(maturityDate)) {
                            tradeRequest.setExpiry(true);
                        }
                     }
             );
         });
    }
}
