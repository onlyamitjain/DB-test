package com.test;

import com.test.datastore.TradeStoreFactory;
import com.test.model.TradeRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ConsumerRequestWorkerTest {

    @Test
    public void consumerRequestWorkerTest() throws Exception {

        TradeStoreFactory.getTradeStore().clearMap();

        TradeRequest request1 = new TradeRequest("T1", 1,"CP-1","B1", "20/05/2021", Util.getDateAsString(new Date()));

        ConsumerRequestWorker consumerRequestWorker = new ConsumerRequestWorker(request1);
        consumerRequestWorker.call();
        List<TradeRequest> tradeRequestList = TradeStoreFactory.getTradeStore().getTradeRequest("T1");
        Assert.assertEquals(1, tradeRequestList.size());
        Assert.assertEquals("20/05/2021", Util.getDateAsString(tradeRequestList.get(0).getMaturityDate()));


        TradeRequest request2 = new TradeRequest("T2", 1,"CP-1","B1", "18/05/2021",Util.getDateAsString(new Date()));
        consumerRequestWorker = new ConsumerRequestWorker(request2);
        consumerRequestWorker.call();
        tradeRequestList = TradeStoreFactory.getTradeStore().getTradeRequest("T2");
        Assert.assertEquals(1, tradeRequestList.size());
        Assert.assertEquals("18/05/2021", Util.getDateAsString(tradeRequestList.get(0).getMaturityDate()));

        TradeRequest request3 = new TradeRequest("T2", 2,"CP-1","B1", "20/05/2021",Util.getDateAsString(Util.formatDate("14/03/2015")));
        consumerRequestWorker = new ConsumerRequestWorker(request3);
        consumerRequestWorker.call();
        tradeRequestList = TradeStoreFactory.getTradeStore().getTradeRequest("T2");
        Assert.assertEquals(2, tradeRequestList.size());
        Assert.assertEquals(1, tradeRequestList.get(0).getVersion().intValue());
        Assert.assertEquals(2, tradeRequestList.get(1).getVersion().intValue());


        TradeRequest request4 = new TradeRequest("T2", 1,"CP-1","B1", "20/05/2022",Util.getDateAsString(Util.formatDate("14/03/2015")));
        consumerRequestWorker = new ConsumerRequestWorker(request4);
        try {
            consumerRequestWorker.call();
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertEquals("Incoming trade is less than current version of", e.getMessage());
        }

        TradeRequest request5 = new TradeRequest("T2", 2,"CP-1","B1", "20/05/2023",Util.getDateAsString(Util.formatDate("14/03/2015")));
        consumerRequestWorker = new ConsumerRequestWorker(request5);
        consumerRequestWorker.call();
        tradeRequestList = TradeStoreFactory.getTradeStore().getTradeRequest("T2");
        Assert.assertEquals(2, tradeRequestList.size());
        Assert.assertEquals(1, tradeRequestList.get(0).getVersion().intValue());
        Assert.assertEquals(2, tradeRequestList.get(1).getVersion().intValue());
        Assert.assertEquals("20/05/2023", Util.getDateAsString(tradeRequestList.get(1).getMaturityDate()));


        TradeRequest request6 = new TradeRequest("T2", 2,"CP-1","B1", "20/05/2019",Util.getDateAsString(Util.formatDate("14/03/2015")));
        consumerRequestWorker = new ConsumerRequestWorker(request6);

        try {
            consumerRequestWorker.call();
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertEquals("MaturityDate has been expired", e.getMessage());
            Assert.assertEquals(2, tradeRequestList.size());
    }


        TradeRequest request7 = new TradeRequest("T2", 3,"CP-1","B1", "20/05/2024",Util.getDateAsString(Util.formatDate("14/03/2015")));
        consumerRequestWorker = new ConsumerRequestWorker(request7);
        consumerRequestWorker.call();
        tradeRequestList = TradeStoreFactory.getTradeStore().getTradeRequest("T2");
        Assert.assertEquals(3, tradeRequestList.size());
        Assert.assertEquals(1, tradeRequestList.get(0).getVersion().intValue());
        Assert.assertEquals(2, tradeRequestList.get(1).getVersion().intValue());
        Assert.assertEquals(3, tradeRequestList.get(2).getVersion().intValue());

        TradeStoreFactory.getTradeStore().clearMap();
    }
}
