/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.donotimagine;

import javax.swing.JFrame;

import UI.elements.ControlPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageScreen extends JPanel {

    private BufferedImage image;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private void drawStringAtCenter(Graphics2D g2d, String string) {
        float fontsize = this.getHeight() / 20;
        g2d.setStroke(new BasicStroke(2));
        g2d.setFont(new Font("Noto Serif", Font.BOLD, (int) fontsize));
        g2d.setPaint(new Color(200, 200, 200));
        g2d.drawString(
                string,
                this.getWidth() / 2 - (fontsize * string.length() / 5),
                this.getHeight() / 2 + fontsize
        );
    }

    private void drawImage(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (image == null) {
            return;
        }

        // Calculate image aspect ratio (Width / Height)
        double imageRatio = (double) image.getWidth(this) / image.getHeight(this);
        double panelRatio = (double) getWidth() / getHeight();

        int drawWidth;
        int drawHeight;
        int x = 0;
        int y = 0;

        // Scale image to fit inside the panel while maintaining aspect ratio
        if (panelRatio > imageRatio) {
            // Panel is wider than the image -> fit to height
            drawHeight = getHeight();
            drawWidth = (int) (drawHeight * imageRatio);
            x = (getWidth() - drawWidth) / 2; // Center horizontally
        } else {
            // Panel is taller than the image -> fit to width
            drawWidth = getWidth();
            drawHeight = (int) (drawWidth / imageRatio);
            y = (getHeight() - drawHeight) / 2; // Center vertically
        }

        // Draw the scaled and centered image
        g2d.drawImage(image, x, y, drawWidth, drawHeight, this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image == null) {
            drawStringAtCenter(g2d, "you shouldnt be seeing this...");
        } else {
            drawImage(g2d);
        }
    }

}
