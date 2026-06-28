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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import shared.WrapLayout;
/**
 *
 * @author amani kherraz
 */
public class ImageViewport extends JPanel{
    boolean isGallery =true;
    String[] images= {"1","2","3","4","5"};
    int currentImageIndex= 0;
    Gallery gallery;
    JPanel imageView;

    public ImageViewport() {
        Gallery myGallery = new Gallery(images, currentImageIndex);
        JScrollPane scrollPane = new JScrollPane(myGallery);

        // Force vertical scrollbar and disable horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Important: Set the preferred size of the scrollPane, not the gallery
        scrollPane.setPreferredSize(new Dimension(200, 400));
        //frame.add(scrollPane);
        gallery = new Gallery(images, currentImageIndex);
        imageView = new JPanel();
        JButton nextButton = new JButton("next!!!");
        nextButton.addActionListener(e->{
            setCurrentImage(currentImageIndex+1);
        });
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(nextButton,BorderLayout.SOUTH);
    }
    public void setCurrentImage(int index){
        currentImageIndex = index % images.length;
        gallery.setfocus(index);
    }
    
    
}
class ImageContainer extends JPanel{

    public ImageContainer() {
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
    }
}
class Gallery extends JPanel{
    int focused_image;
    String[] images;

    public Gallery() {
    }
    public Gallery(String[] images ,int focused_image) {
        this.images=images;
        this.focused_image =focused_image;
        renderGallery();
    }
    public void setfocus(int index){
        focused_image = index;
        renderGallery();
    }
    private void renderGallery(){
        this.removeAll();
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        for (int i = 0; i < images.length; i++) {
            ImageContainer imageContainer = new ImageContainer();
            imageContainer.setPreferredSize(new Dimension(100,100));
            JLabel imagelabel = new JLabel("this is image number " + images[i]);
            
            if (i == focused_image) {
                imagelabel.setForeground(Color.red);
            }
            imageContainer.add(imagelabel);
            this.add(imageContainer);
        }
        this.revalidate();
        this.repaint();
    }
}