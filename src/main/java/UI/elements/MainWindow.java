/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UI.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author gool
 */
public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException {
        ControlPanel cp = new ControlPanel();
        String[] durationoptions = {"a", "b", "c"};
        cp.setDurationOptions(durationoptions);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(cp, BorderLayout.EAST);
        ImageScreen screen = new ImageScreen();
        this.add(screen, BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(500, 500));
    }
}
