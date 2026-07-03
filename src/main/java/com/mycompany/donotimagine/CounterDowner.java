/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author gool
 */
public class CounterDowner {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Runnable endOfCountDownTask;
    private int durationInSeconds;
    private int remainingTimeInSeconds;
    private Runnable countdownTask = new Runnable() {
        @Override
        public void run() {
            if (remainingTimeInSeconds > 0) {
                System.out.println("Time left: "
                        + remainingTimeInSeconds % 60
                        + " seconds  and "
                        + remainingTimeInSeconds / 60 + " minutes");
                remainingTimeInSeconds--;
            } else {
                remainingTimeInSeconds = durationInSeconds;
                endOfCountDownTask.run();
            }
        }
    };

    public CounterDowner(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        this.remainingTimeInSeconds = durationInSeconds;
        this.endOfCountDownTask = () -> {
            System.out.println("please set up your endOfCountDownTask by using intance.setendOfCountDownTask ");
        };
    }

    public void setEndOfCountDownTask(Runnable endOfCountDownTask) {
        this.endOfCountDownTask = endOfCountDownTask;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(countdownTask, 0, 1, TimeUnit.SECONDS);

    }

    public void pause() {
        scheduler.close();
    }

    public void stop() {
        scheduler.close();
        remainingTimeInSeconds = durationInSeconds;
    }

    public void setDuration(int duration) {
        durationInSeconds = duration;
        remainingTimeInSeconds = durationInSeconds;
    }

}
