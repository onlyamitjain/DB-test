package com.test.validation;

import com.test.Util;
import com.test.exception.ValidationException;
import com.test.model.TradeRequest;

public class MaturityValidationRule implements IValdationRule {

    public void validateRule(TradeRequest tradeRequest) throws ValidationException {
        if(tradeRequest.getMaturityDate() != null && Util.isExpired(tradeRequest.getMaturityDate())){
            throw new ValidationException("MaturityDate has been expired");
        }
    }

}
