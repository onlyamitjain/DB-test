package com.test.com.test.timertask;

import com.test.Util;
import com.test.datastore.TradeStoreFactory;
import com.test.model.TradeRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UpdateMaturityDateTaskTest {

    @Test
    public void updateMaturityDateTaskTest() throws Exception {
        TradeStoreFactory.getTradeStore().clearMap();
        TradeRequest request1 = new TradeRequest("T1", 1,"CP-1","B1", "20/05/2021", Util.getDateAsString(new Date()));
        TradeRequest request2 = new TradeRequest("T2", 1,"CP-1","B1", "20/05/2020",Util.getDateAsString(new Date()));
        TradeRequest request3 = new TradeRequest("T2", 2,"CP-1","B1", "20/05/2021",Util.getDateAsString(Util.formatDate("14/03/2015")));
        TradeRequest request4 = new TradeRequest("T3", 3,"CP-3","B2", "20/05/2014",Util.getDateAsString(new Date()));

        TradeStoreFactory.getTradeStore().saveTradeStore(request1);
        TradeStoreFactory.getTradeStore().saveTradeStore(request2);
        TradeStoreFactory.getTradeStore().saveTradeStore(request3);
        TradeStoreFactory.getTradeStore().saveTradeStore(request4);

        Assert.assertFalse(request4.isExpiry());
        Assert.assertFalse(request2.isExpiry());
        Assert.assertFalse(request1.isExpiry());
        Assert.assertFalse(request3.isExpiry());

        new UpdateMaturityDateTask().run();

        Assert.assertTrue(request4.isExpiry());
        Assert.assertTrue(request2.isExpiry());
        Assert.assertFalse(request1.isExpiry());

        List<TradeRequest> updatedRequest = TradeStoreFactory.getTradeStore().getTradeRequest("T2");
        Assert.assertTrue(updatedRequest.get(0).isExpiry());
        Assert.assertFalse(updatedRequest.get(1).isExpiry());


    }
}
