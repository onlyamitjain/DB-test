package com.test;

import com.test.com.test.timertask.ConfigureTasks;
import com.test.model.TradeRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeStoreApplication {

    public static void main(String[] args) {
        BlockingQueue<TradeRequest> sharedQ = new LinkedBlockingQueue<>();

        ProducerTradeRequest p = new ProducerTradeRequest(sharedQ);
        ConsumerTradeRequests c = new ConsumerTradeRequests(sharedQ);

        p.start();
        c.start();
        ConfigureTasks.getInstance().configureScheduledTask();

    }
}
