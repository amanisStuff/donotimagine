/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 *
 * @author gool
 */
public class CounterDowner {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;
    private Runnable endOfCountDownTask;
    private int durationInSeconds;
    private int remainingTimeInSeconds;
    private Consumer<Integer> counterTaskConsumer = (remainingTime) -> {
        System.out.println("Time left: " + (remainingTime / 60) + "m " + (remainingTime % 60) + "s");
    };

    // Define the logic as a Runnable
    private final Runnable countdownTaskLogic = () -> {
        if (remainingTimeInSeconds > 0) {
            counterTaskConsumer.accept(remainingTimeInSeconds);
            remainingTimeInSeconds--;
        } else {
            counterTaskConsumer.accept(0);
            remainingTimeInSeconds = durationInSeconds;
            endOfCountDownTask.run();
        }
    };

    public CounterDowner(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        this.remainingTimeInSeconds = durationInSeconds;
        this.endOfCountDownTask = () -> System.out.println("Countdown finished!");
    }

    public void start() {
        // Only start if not already running
        if (scheduledFuture == null || scheduledFuture.isCancelled() || scheduledFuture.isDone()) {
            scheduledFuture = scheduler.scheduleAtFixedRate(countdownTaskLogic, 0, 1, TimeUnit.SECONDS);
        }
    }

    public void pause() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void stop() {
        pause();
        remainingTimeInSeconds = durationInSeconds;
    }

    public void setCounterTask(Consumer<Integer> counterTaskConsumer) {
        this.counterTaskConsumer = counterTaskConsumer;
    }

    public void setEndOfCountDownTask(Runnable endOfCountDownTask) {
        this.endOfCountDownTask = endOfCountDownTask;
    }

    public void setDuration(int duration) {
        this.durationInSeconds = duration;
        this.remainingTimeInSeconds = duration;
    }

    // Call this when your application shuts down to clean up the thread pool
    public void shutdown() {
        scheduler.shutdown();
    }
}
