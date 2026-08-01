/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine;

/**
 *
 * @author gool
 */
public class ClassModeCounterDowner extends CounterDowner {

    private int index = -1;
    private ClassPeriod classPerriod = new ClassPeriod();

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
                    index = index + 1;
                    super.setDuration(classPerriod.getDuration(index));
//                    look for the repetition with the equal or greater value and use it's index to set duration
                    endOfCountDownTask.run();
                }
        );

    }

    public ClassModeCounterDowner(ClassPeriod classPerriod) {
        super(classPerriod.getDuration(0));
        setClassPerriod(classPerriod);

    }

    public void setClassPerriod(ClassPeriod classPerriod) {

        this.classPerriod = classPerriod;
    }

}
