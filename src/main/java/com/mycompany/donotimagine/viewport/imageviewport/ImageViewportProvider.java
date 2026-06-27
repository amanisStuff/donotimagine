/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine.viewport.imageviewport;

import com.mycompany.donotimagine.ViewportControl;
import com.mycompany.donotimagine.viewport.ViewportProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.JPanel;
import com.mycompany.donotimagine.viewport.imageviewport.ImageViewport;

/**
 *
 * @author amani kherraz
 */
public class ImageViewportProvider implements ViewportProvider{
    private HashMap<ViewportControl, Consumer> controls;
    private ArrayList<Object> imageList; // make this an image object later
    private int activeImage = -1;
    private boolean isGallery = true;
    private JPanel viewport;
    private void selectImage(int index){
        if (index >=0 && imageList.size()>index && index!=activeImage) {
            activeImage = index;
        }
        activeImage = -1;
    }
    private void removeImage(int index){
        if (index >=0 && imageList.size()>index && index!=activeImage) {
            imageList.remove(index);
        }
    }
    private void play(){
       isGallery = false;
    };    
    private void pause(){
       isGallery = true;
    };
    public ImageViewportProvider() {
        imageList = new ArrayList(); 
        controls = new HashMap<>();
        viewport = new ImageViewport();
    }
    @Override
    public HashMap<ViewportControl, Consumer> getControls() {
        return controls;
    };
    @Override
    public JPanel getViewport() {
        return viewport;
    }
    
    
}