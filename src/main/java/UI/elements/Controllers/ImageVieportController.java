/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.elements.Controllers;

import UI.elements.ImageScreen;
import java.awt.image.BufferedImage;
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
        imageScreen.setImage(images.get(currentImage));
    }

    @Override
    public void previous() {
        if (images == null || currentImage == -1) {
            return;
        }
        currentImage = (currentImage - 1) % images.size();
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
        }
    }

    public void removeImage(int index) {
        images.remove(index);
        if (images.isEmpty()) {
            currentImage = -1;
        }
    }
}
