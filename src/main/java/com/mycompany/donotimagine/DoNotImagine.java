/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.donotimagine;

import javax.swing.JFrame;

import UI.elements.ControlPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class DoNotImagine {

    public static void main(String[] args) {
        // use the ui elements called ControlPanel
        JFrame jframe = new JFrame();
        ControlPanel cp = new ControlPanel();
        jframe.setLayout(new BorderLayout());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(cp, BorderLayout.EAST);
        jframe.add(new JPanel(), BorderLayout.CENTER);
        jframe.setVisible(true);
    }
}
