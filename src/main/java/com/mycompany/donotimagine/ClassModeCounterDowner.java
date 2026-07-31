/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine;

import java.util.Arrays;

/**
 *
 * @author gool
 */
public class ClassModeCounterDowner extends CounterDowner {

    private int[] durations = {30, 60, 120, 300};
    private int[] repetitions = {5, 3, 2, 1};
    private int index = -1;

    public void setClassPerameters(int[] durations, int[] repetitions) {
        if (durations.length == repetitions.length) {
            this.durations = durations;
            this.repetitions = repetitions;
        } else if (durations.length > repetitions.length) {
            this.repetitions = repetitions;
            this.durations = Arrays.copyOfRange(durations, 0, repetitions.length);
        } else if (durations.length < repetitions.length) {
            this.durations = durations;
            this.repetitions = Arrays.copyOfRange(repetitions, 0, durations.length);
        }
    }

    private int addUpRepetition() {
        int sum = 0;
        for (int i = 0; i < durations.length; i++) {
            sum = sum + durations[i];
        }
        return sum;
    }

    private int findDurrationindexByRepetitionAndIndex(int index) {
        int tracker = index;
        for (int i = 0; i < repetitions.length; i++) {
            tracker = tracker - repetitions[i];
            if (tracker < 0) {
                return i;
            }
        }
        return 0;
    }

    @Override

    public void start() {
        if (index == -1) {
            index = 0;
        }
        super.start();
    }

    @Override
    public void setEndOfCountDownTask(Runnable endOfCountDownTask) {
        super.setEndOfCountDownTask(
                () -> {
                    index = (index + 1) % addUpRepetition();
                    super.setDuration(durations[findDurrationindexByRepetitionAndIndex(index)]);
//                    look for the repetition with the equal or greater value and use it's index to set duration
                    endOfCountDownTask.run();
                }
        );

    }

    private ClassModeCounterDowner(int durationInSeconds) {
        super(durationInSeconds);
    }

    public ClassModeCounterDowner(int[] durations, int[] repetitions) {
        super(durations[0]);
        setClassPerameters(durations, repetitions);
    }

}
