package com.test.validation;

import com.test.Util;
import com.test.datastore.TradeStoreFactory;
import com.test.exception.ValidationException;
import com.test.model.TradeRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class VersionValidationRuleTest {

    @Test
    public void versionValidationRuleTest() throws Exception {

        VersionValidationRule validationRule = new VersionValidationRule();

        TradeRequest request1 = new TradeRequest("T1", 2,"CP-1","B1", "20/05/2020", Util.getDateAsString(new Date()));
        TradeStoreFactory.getTradeStore().saveTradeStore(request1);


        try {
            TradeRequest request2 = new TradeRequest("T1", 3,"CP-1","B1", "20/05/2020", Util.getDateAsString(new Date()));
            validationRule.validateRule(request2);
            Assert.assertTrue(true);
        }catch (ValidationException va){
            Assert.assertFalse(true);
        }


       TradeRequest request3 = new TradeRequest("T1", 1,"CP-1","B1", "20/05/2020", Util.getDateAsString(new Date()));
       try {
           validationRule.validateRule(request3);
           Assert.assertFalse(true);
       } catch (ValidationException va){
           Assert.assertEquals("Incoming trade is less than current version of", va.getMessage());
       }

       TradeStoreFactory.getTradeStore().clearMap();

    }
}
