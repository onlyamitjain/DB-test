package com.test.datastore.impl;

import com.test.Util;
import com.test.datastore.ITradeStore;
import com.test.model.TradeRequest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapBasedTradeStoreImpl implements ITradeStore {

    private static final MapBasedTradeStoreImpl INSTANCE = new MapBasedTradeStoreImpl();

    public ConcurrentHashMap<String, LinkedHashMap<Integer, TradeRequest>> tradeStoreMap = new ConcurrentHashMap<>();

    private MapBasedTradeStoreImpl() {

    }

    public static MapBasedTradeStoreImpl getInstance() {
        return INSTANCE;
    }

    public void saveTradeStore(TradeRequest request) {
       if(tradeStoreMap.containsKey(request.getTradeID())) {
           tradeStoreMap.get(request.getTradeID()).put(request.getVersion(), request);
        } else {
           LinkedHashMap<Integer, TradeRequest> map = new LinkedHashMap<>();
           map.put(request.getVersion(), request);
           tradeStoreMap.put(request.getTradeID(), map);
        }
    }

    public List<TradeRequest> getTradeRequest(String tradeId) {

     return new ArrayList<>(tradeStoreMap.get(tradeId).values());
    }

    public void updateTradeRquest(TradeRequest tradeRequest) {

    }

    public List<String> getValidationRules() {
        List<String> validationRules = new ArrayList<>();
        validationRules.add("com.test.validation.VersionValidationRule");
        validationRules.add("com.test.validation.MaturityValidationRule");
        return validationRules;
    }

    public TradeRequest getValidLastTradeRequest(String tradeId) {

        if(tradeStoreMap.get(tradeId) != null && !tradeStoreMap.get(tradeId).isEmpty()) {
            List<Integer> alKeys = new ArrayList<Integer>(tradeStoreMap.get(tradeId).keySet());
            Collections.reverse(alKeys);
            for(Integer intKey : alKeys){
                if(!tradeStoreMap.get(tradeId).get(intKey).isExpiry()) {
                    return tradeStoreMap.get(tradeId).get(intKey);
                }
            }
        }

     return null;
    }

    public Set<String> getAllTradeRequestKeys() {
        Enumeration<String> enu  =  tradeStoreMap.keys();
        Set<String> keys = new HashSet<>();
        while (enu.hasMoreElements()) {
            keys.add(enu.nextElement());
        }

        return keys;

    }


    public void clearMap() {
        tradeStoreMap.clear();
    }

    public void printInfo() {
        List<String> alKeys = new ArrayList<String>(tradeStoreMap.keySet());
        for(String strKey : alKeys){
            List<Integer> versionKeys = new ArrayList<Integer>(tradeStoreMap.get(strKey).keySet());
            for(Integer versonKey : versionKeys) {
                TradeRequest tradeRequest = tradeStoreMap.get(strKey).get(versonKey);
                System.out.println(tradeRequest.getTradeID() + "," + tradeRequest.getVersion() + "," + tradeRequest.getCouterPartyId() + "," + tradeRequest.getBookId() + ","
                        + Util.getDateAsString(tradeRequest.getMaturityDate()) + "," + Util.getDateAsString(tradeRequest.getCreateDate()) + "," + tradeRequest.isExpiry());

            }
            System.out.println();

        }
    }

}
