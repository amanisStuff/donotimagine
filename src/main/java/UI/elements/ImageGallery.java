/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.elements;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import shared.WrapLayout;

/**
 *
 * @author gool
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ImageGallery extends JPanel {

    private final List<BufferedImage> images = new ArrayList<>();

    public ImageGallery() {
        this.setLayout(new WrapLayout(WrapLayout.LEADING, 8, 8));
    }

    public ImageGallery(List<BufferedImage> images) {
        this(); // Call the default constructor for layout setup
        for (BufferedImage img : images) {
            addImage(img);
        }
    }

    public void addImage(BufferedImage image) {
        images.add(image);

        JLabel label = new JLabel();
        // Scale the icon to fit the fixed dimension
        Image scaled = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
        label.setPreferredSize(new Dimension(100, 100));
        ImageWrapper imageWrapper = new ImageWrapper(label, true);
        this.add(imageWrapper);

        // Refresh UI
        refreshUI();
    }

    public void removeImage(int index) {
        if (index >= 0 && index < images.size()) {
            images.remove(index);
            this.remove(index);
            // Refresh UI
            refreshUI();
        }
    }

    private void refreshUI() {
        this.revalidate();
        this.repaint();
    }

    private class ImageWrapper extends JLayeredPane {

        JLabel image;
        JButton deleteButton = new JButton();
        Border line = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
        Border selectedLine = BorderFactory.createLineBorder(new Color(200, 200, 255, 255), 2);

        boolean isSelected;

        public ImageWrapper(JLabel imageLabel, boolean selected) {
            isSelected = selected;
            this.setPreferredSize(new Dimension(104, 104));
            image = imageLabel;
            image.setBounds(2, 2, 100, 100);
            deleteButton.setBounds(54, 75, 41, 20);
            deleteButton.setText("×");
            this.add(image, JLayeredPane.DEFAULT_LAYER);
            this.add(deleteButton, JLayeredPane.DRAG_LAYER);
            this.setBorder(selectedLine);
        }
    }
}
