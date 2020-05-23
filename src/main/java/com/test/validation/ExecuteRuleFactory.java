package com.test.validation;

import com.test.datastore.TradeStoreFactory;
import com.test.exception.ValidationException;
import com.test.model.TradeRequest;

import java.util.ArrayList;
import java.util.List;

public class ExecuteRuleFactory {

   private static final ExecuteRuleFactory INSTANCE = new ExecuteRuleFactory();

   private List<IValdationRule> validationRules = new ArrayList<>();

    private ExecuteRuleFactory() {
        configureRule();
   }

   public static ExecuteRuleFactory getInstance() {
       return INSTANCE;
   }

   private void configureRule() {
       try {
           List<String> validationRulesAsStr = TradeStoreFactory.getTradeStore().getValidationRules();
           for(String rule : validationRulesAsStr){
               validationRules.add((IValdationRule) Class.forName(rule).newInstance());
           }
       } catch (Exception e){
           e.printStackTrace();
           throw new RuntimeException("Exception occured while getting the validation rule -" + e.getMessage());

       }

   }

   public void executeValidationRule(TradeRequest tradeRequest) throws ValidationException {
        for(IValdationRule valdationRule : validationRules){
            valdationRule.validateRule(tradeRequest);
        }
   }

}
