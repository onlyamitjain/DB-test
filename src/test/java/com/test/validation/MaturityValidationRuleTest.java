package com.test.validation;

import com.test.Util;
import com.test.datastore.TradeStoreFactory;
import com.test.exception.ValidationException;
import com.test.model.TradeRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MaturityValidationRuleTest {

    @Test
    public void maturityValidationRuleTest() throws Exception {
        MaturityValidationRule maturityValidationRule = new MaturityValidationRule();

        TradeStoreFactory.getTradeStore().clearMap();

        try {
            TradeRequest request2 = new TradeRequest("T1", 3,"CP-1","B1", "18/05/2021", Util.getDateAsString(new Date()));
            maturityValidationRule.validateRule(request2);
            Assert.assertTrue(true);
        }catch (ValidationException va){
            Assert.assertFalse(true);
        }

        try {
            TradeRequest request2 = new TradeRequest("T1", 3,"CP-1","B1", "20/04/2020", Util.getDateAsString(new Date()));
            maturityValidationRule.validateRule(request2);
            Assert.assertTrue(false);
        }catch (ValidationException va){
            Assert.assertEquals("MaturityDate has been expired", va.getMessage());
        }

        TradeStoreFactory.getTradeStore().clearMap();
    }
}
