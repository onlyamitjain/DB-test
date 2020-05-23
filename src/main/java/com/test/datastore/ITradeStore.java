package com.test.datastore;

import com.test.model.TradeRequest;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface ITradeStore {
    public void saveTradeStore(TradeRequest request);
    public List<TradeRequest> getTradeRequest(String tradeId);
    public  Set<String> getAllTradeRequestKeys();
    public void updateTradeRquest(TradeRequest tradeRequest);
    public List<String> getValidationRules();
    public TradeRequest getValidLastTradeRequest(String tradeId);
    public void clearMap();
    public void printInfo();
}
