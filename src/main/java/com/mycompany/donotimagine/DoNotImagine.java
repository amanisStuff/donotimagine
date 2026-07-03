/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.donotimagine;

import UI.elements.MainWindow;

public class DoNotImagine {

    public static void main(String[] args) {
        // use the ui elements called ControlPanel
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
        CounterDowner counter = new CounterDowner(3);
        counter.start();
        counter.setEndOfCountDownTask(() -> {
            System.out.println("user set end of task");
        });
    }

}
