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
public class ClassPeriod {

    private int[] durations = {30, 60, 120, 300};
    private int[] repetitions = {5, 3, 2, 1};
    public int getTotalTimeSpent() {
        int totalTime = 0;
        for (int i = 0; i < durations.length; i++) {
            totalTime += durations[i] * repetitions[i];
        }
        return totalTime;
    }
    public String getTimeString(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%d-h %d-m %d-s", hours, minutes, seconds);
    }
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        for (int i = 0; i < durations.length; i++) {
            summary.append("Duration: ").append(durations[i]).append("s, Repetitions: ").append(repetitions[i]).append("\n");
        }
        return summary.toString();
    }
    public String getPeriodSummary() {
        StringBuilder periodSummary = new StringBuilder();
        for (int i = 0; i < durations.length; i++) {
            periodSummary.append("Duration: ").append(durations[i]).append("s, Repetitions: ").append(repetitions[i]).append(", Total Time: ").append(getTimeString(durations[i] * repetitions[i])).append("\n");
        }
        return periodSummary.toString();
    }
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
        for (int i = 0; i < repetitions.length; i++) {
            sum = sum + repetitions[i];
        }
        return sum;
    }

    private int findDurrationindexByRepetitionAndIndex(int index) {
        int tracker = index;
        System.out.println("tracker: " + tracker);
        for (int i = 0; i < repetitions.length; i++) {
            tracker = tracker - repetitions[i];
            if (tracker < 0) {
                System.out.println("resulting index: " + i);

                return i;
            }
        }
        return 0;
    }

    public int getDuration(int index) {
        return durations[findDurrationindexByRepetitionAndIndex(index % addUpRepetition())];
    }

    public ClassPeriod() {
    }

    public ClassPeriod(int[] durations, int[] repetitions) {
        this.durations = durations;
        this.repetitions = repetitions;

    }
}
