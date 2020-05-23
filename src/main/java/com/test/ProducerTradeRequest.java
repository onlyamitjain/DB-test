package com.test;

import com.test.model.TradeRequest;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class ProducerTradeRequest extends Thread {
    private BlockingQueue<TradeRequest> tradeRquestQueue;



    public ProducerTradeRequest(BlockingQueue<TradeRequest> tradeRquestQueue) {
        super("PRDOCUER TRADE");
        this.tradeRquestQueue = tradeRquestQueue;
    }

    public void run() {
            try {
                TradeRequest request1 = new TradeRequest("T1", 1,"CP-1","B1", "20/05/2021", Util.getDateAsString(new Date()));
                TradeRequest request2 = new TradeRequest("T2", 1,"CP-1","B1", "20/05/2021",Util.getDateAsString(new Date()));
                TradeRequest request3 = new TradeRequest("T2", 2,"CP-1","B1", "20/05/2020",Util.getDateAsString(Util.formatDate("14/03/2015")));
                TradeRequest request4 = new TradeRequest("T3", 3,"CP-3","B2", "20/05/2014",Util.getDateAsString(new Date()));

                TradeRequest request5 = new TradeRequest();
                request5.setCompleteProcessing(true);

                tradeRquestQueue.put(request1);
                tradeRquestQueue.put(request2);
                tradeRquestQueue.put(request3);
                tradeRquestQueue.put(request4);
                tradeRquestQueue.put(request5);


            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
