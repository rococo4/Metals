package com.example.metals.OracleUpdater;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start(PriceUpdaterService priceUpdaterService) {
        scheduler.scheduleAtFixedRate(priceUpdaterService, 0, 1, TimeUnit.HOURS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
