/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.donotimagine;

import javax.swing.JFrame;

import UI.elements.ControlPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DoNotImagine {

    public static void main(String[] args) {
        // use the ui elements called ControlPanel
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        mainWindow mw = new mainWindow();
        mw.setVisible(true);

    }
}

class mainWindow extends JFrame {

    public mainWindow() throws HeadlessException {
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
