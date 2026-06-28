/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.donotimagine;
import com.mycompany.donotimagine.viewport.imageviewport.ImageViewportProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class DoNotImagine {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImageViewportProvider imv = new ImageViewportProvider();
        frame.setTitle("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(250,250));
        frame.setLayout(new BorderLayout());
        //frame.add(imv.getViewport());
        JPanel jp = new JPanel();
        JScrollPane scrollpanel = new JScrollPane(jp);
        
        scrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);        
        scrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jp.setSize(5000, 5000);
        jp.setBackground(Color.red);
        frame.add(scrollpanel);
        frame.setVisible(true);
    }
}
