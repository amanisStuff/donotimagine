/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.elements.Controllers;

import UI.elements.ImageScreen;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author gool
 */
public class ImageVieportController implements ViewportController {

    ImageScreen imageScreen;
    BufferedImage[] images;
    int currentImage;

    @Override
    public void next() {
        if (images == null) {
            return;
        }
        currentImage = (currentImage + 1) % images.length;
        imageScreen.setImage(images[currentImage]);
    }

    @Override
    public void previous() {
        if (images == null) {
            return;
        }
        currentImage = (currentImage - 1) % images.length;
        imageScreen.setImage(images[currentImage]);
    }

    @Override
    public JPanel getPanel() {
        return imageScreen;
    }

}
