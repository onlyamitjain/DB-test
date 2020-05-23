package com.test;

import com.test.datastore.TradeStoreFactory;
import com.test.model.TradeRequest;
import com.test.validation.ExecuteRuleFactory;

public class ConsumerRequestWorker {

    private TradeRequest tradeRequest;


    public ConsumerRequestWorker(TradeRequest tradeRequest) {
        this.tradeRequest = tradeRequest;
    }

    public Object call() throws Exception {
        ExecuteRuleFactory.getInstance().executeValidationRule(tradeRequest);
        TradeStoreFactory.getTradeStore().saveTradeStore(tradeRequest);
        return null;
    }
}
