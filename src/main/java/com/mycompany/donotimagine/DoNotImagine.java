/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.donotimagine;
import com.mycompany.donotimagine.viewport.imageviewport.ImageViewportProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DoNotImagine {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImageViewportProvider imv = new ImageViewportProvider();
        frame.setTitle("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(250,250));
        frame.setLayout(new BorderLayout());
        frame.add(imv.getViewport());
        frame.setVisible(true);
    }
}
