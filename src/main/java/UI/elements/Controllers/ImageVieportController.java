/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.elements.Controllers;

import UI.elements.ImageScreen;
import interfaces.IOHandeling;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author gool
 */
public class ImageVieportController implements ViewportController {

    ImageScreen imageScreen = new ImageScreen();
    List<BufferedImage> images = new ArrayList<BufferedImage>();
    int currentImage = -1;

    public ImageVieportController() {

        addImageFromWebLink("https://assets.bucketlistly.blog/sites/5adf778b6eabcc00190b75b1/assets/6075182186d092000b192cee/best-free-travel-images-image-2.jpg");
        addImageFromWebLink("https://thumbs.dreamstime.com/b/beautiful-rain-forest-ang-ka-nature-trail-doi-inthanon-national-park-thailand-36703721.jpg");
        if (!images.isEmpty()) {
            currentImage = 0;
            imageScreen.setImage(images.get(currentImage));
        } else {
            currentImage = -1;
        }

    }

    @Override
    public void next() {
        if (currentImage < 0 || currentImage == -1) {
            return;
        }
        currentImage = (currentImage + 1) % images.size();
        System.out.println("currentImage: " + currentImage);
        imageScreen.setImage(images.get(currentImage));
    }

    @Override
    public void previous() {
        if (images == null || currentImage == -1) {
            return;
        }
        currentImage = (currentImage + images.size() - 1) % images.size();
        imageScreen.setImage(images.get(currentImage));
    }

    @Override
    public JPanel getPanel() {
        return imageScreen;
    }

    public void addImage(BufferedImage newImage) {
        images.add(newImage);
        if (images.size() == 1) {
            currentImage = 0;
            imageScreen.setImage(images.get(currentImage));
        }
    }

    public void removeImage(int index) {
        images.remove(index);
        if (images.isEmpty()) {
            currentImage = -1;
        }
    }

    public void addImageFromWebLink(String url) {
        try {
            BufferedImage newImage = IOHandeling.loadImage(url, IOHandeling.imageOptions.WEB);
            addImage(newImage);
        } catch (IOException ex) {
            System.getLogger(ImageVieportController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (AssertionError ex) {
            System.getLogger(ImageVieportController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
