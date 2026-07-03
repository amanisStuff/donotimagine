/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UI.elements;

import UI.elements.ImageScreen;
import UI.elements.ControlPanel;
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
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(cp, BorderLayout.EAST);
        ImageScreen screen = new ImageScreen();
        screen.setSize(500, 500);
        this.add(screen, BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(500, 500));
    }
}

