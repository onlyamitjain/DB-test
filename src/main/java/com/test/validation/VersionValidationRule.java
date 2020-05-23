package com.test.validation;

import com.test.datastore.TradeStoreFactory;
import com.test.exception.ValidationException;
import com.test.model.TradeRequest;

public class VersionValidationRule implements IValdationRule {


    public void validateRule(TradeRequest tradeRequest) throws ValidationException {

        TradeRequest lastTreadeRequest = TradeStoreFactory.getTradeStore().getValidLastTradeRequest(tradeRequest.getTradeID());
        if(lastTreadeRequest != null) {
            if(lastTreadeRequest.getVersion() > tradeRequest.getVersion()) {
                throw new ValidationException("Incoming trade is less than current version of");
            }
        }
    }
}
