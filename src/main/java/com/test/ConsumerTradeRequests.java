package com.test;

import com.test.datastore.TradeStoreFactory;
import com.test.model.TradeRequest;

import java.util.concurrent.*;

public class ConsumerTradeRequests extends Thread    {

    private BlockingQueue<TradeRequest> tradeRquestQueue;
    //ExecutorService executor =  new ThreadPoolExecutor(1, 5, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    public ConsumerTradeRequests(BlockingQueue<TradeRequest> tradeRquestQueue) {
        super("CONSUMER TRADE");
        this.tradeRquestQueue = tradeRquestQueue;
    }

    public void run() {

            while (true) {
                try {
                TradeRequest item = tradeRquestQueue.take();
                if(item.isCompleteProcessing()) {
                    System.out.println("--------------");
                    TradeStoreFactory.getTradeStore().printInfo();
                    break;
                }
                new ConsumerRequestWorker(item).call();
            } catch (Exception e) {
                   System.out.print("Exception -" + e.getMessage());
                }
        }
    }



}
