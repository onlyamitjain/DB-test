package com.test.validation;

import com.test.exception.ValidationException;
import com.test.model.TradeRequest;

public interface IValdationRule {

    public void validateRule(TradeRequest tradeRequest) throws ValidationException;
}
