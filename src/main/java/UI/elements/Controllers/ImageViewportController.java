/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.elements.Controllers;

import UI.elements.ImageGallery;
import UI.elements.ImageScreen;
import interfaces.IOHandeling;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author gool
 */
public class ImageViewportController implements ViewportController {

    private ImageScreen imageScreen = new ImageScreen();
    private List<BufferedImage> images = new ArrayList<BufferedImage>();
    private ImageGallery imageGallery;
    private int currentImage = -1;
    private JPanel current_view;

    public ImageViewportController() {

        current_view = new JPanel(new BorderLayout());
        imageGallery = new ImageGallery(images);
        current_view.add(imageGallery);
        imageGallery.setClickRemoveAction((index) -> {
            removeImage(index);
        });
        imageGallery.setClickAddAction(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.resetChoosableFileFilters();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images (*.jpg, *.png)", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(imageFilter);
            // 2. Open the file dialog (pass 'null' or your parent component/frame)
            int response = fileChooser.showOpenDialog(null);
            // 3. Check if the user selected a file
            if (response == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String url = selectedFile.getAbsolutePath();
                addImageFromFilePath(url);
                System.out.println("Selected file path: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("File selection was cancelled.");
            }

        });
        imageGallery.setWebLinkDropped((url) -> {
            addImageFromWebLink(url);
        });
        imageGallery.setFileDropped((path) -> {
            addImageFromFilePath(path);
        });

        if (!images.isEmpty()) {
            currentImage = 0;
            imageScreen.setImage(images.get(currentImage));
        } else {
            currentImage = -1;
        }

    }

    @Override
    public void play() {
        current_view.remove(imageGallery);
        current_view.add(imageScreen);
        current_view.repaint();

    }

    @Override
    public void pause() {
        current_view.add(imageGallery);
        current_view.remove(imageScreen);
        current_view.repaint();

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
        return current_view;
    }

    public void addImage(BufferedImage newImage) {
        images.add(newImage);
        if (images.size() == 1) {
            currentImage = 0;
            imageScreen.setImage(images.get(currentImage));
        }
        imageGallery.addImage(newImage);
        this.current_view.repaint();
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
            System.getLogger(ImageViewportController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (AssertionError ex) {
            System.getLogger(ImageViewportController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void addImageFromFilePath(String url) {
        try {
            BufferedImage newImage = IOHandeling.loadImage(url, IOHandeling.imageOptions.SYSTEM);
            addImage(newImage);
        } catch (IOException ex) {
            System.getLogger(ImageViewportController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (AssertionError ex) {
            System.getLogger(ImageViewportController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
