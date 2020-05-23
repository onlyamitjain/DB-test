package com.test.com.test.timertask;

import java.util.Timer;

public class ConfigureTasks {

    private static final ConfigureTasks INSTANCE = new ConfigureTasks();

    private ConfigureTasks() {

    }

    public static ConfigureTasks getInstance() {
        return INSTANCE;
    }

    public void configureScheduledTask(){
        Timer t=new Timer();
        t.scheduleAtFixedRate(new UpdateMaturityDateTask(), 0,24 * 60 * 60 *1000);
    }




}
