package com.test.datastore;

import com.test.datastore.impl.MapBasedTradeStoreImpl;

public class TradeStoreFactory {

    private static DataStoreType CURRENT_DATA_STORE = DataStoreType.Map;

 public static ITradeStore getTradeStore() {

     switch (CURRENT_DATA_STORE) {
         case Map:
             return MapBasedTradeStoreImpl.getInstance();
         default:
             throw new IllegalArgumentException("No data store type supported, provided data store type " + CURRENT_DATA_STORE);
     }

 }
}
