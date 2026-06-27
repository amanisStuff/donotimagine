/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.donotimagine.viewport.imageviewport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author amani kherraz
 */
public class ImageViewport extends JPanel{
    boolean isGallery =true;
    String[] images= {"1","2","3","4","5"};
    int currentImageIndex= 0;
    JPanel gallery;
    JPanel imageView;

    public ImageViewport() {
        gallery = new JPanel();
        renderGallery();
        imageView = new JPanel();
        JButton nextButton = new JButton("next!!!");
        nextButton.addActionListener(e->{
            setCurrentImage(currentImageIndex+1);
        });
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());
        this.add(gallery,BorderLayout.CENTER);
        this.add(nextButton,BorderLayout.SOUTH);
    }
    public void setCurrentImage(int index){
        currentImageIndex = index % images.length;
        renderGallery();
    }
    private void renderGallery(){
        gallery.removeAll();
        gallery.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < images.length; i++) {
            ImageContainer imageContainer = new ImageContainer();
            imageContainer.setBackground(Color.blue);
            imageContainer.setPreferredSize(new Dimension(100,100));
            JLabel imagelabel = new JLabel("this is image number " + images[i]);
            if (i == currentImageIndex) {
                imagelabel.setForeground(Color.red);
            }
            imageContainer.add(imagelabel);
            gallery.add(imageContainer);
        }
        gallery.revalidate();
        gallery.repaint();
    }
    
}
class ImageContainer extends JPanel{

    public ImageContainer() {
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {

        // Create a rendering hint for smooth edges (Anti-aliasing)
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        
    }
}
