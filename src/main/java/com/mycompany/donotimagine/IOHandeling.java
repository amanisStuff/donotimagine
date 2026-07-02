/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 *
 * @author amani
 */
public class IOHandeling {

    public static enum imageOptions {
        WEB,
        SYSTEM,
        ASSET
    };

    public static BufferedImage loadImage(String source, imageOptions imageOption) throws IOException, AssertionError {
        BufferedImage loaded;
        switch (imageOption) {
            case ASSET -> {
                InputStream is = Image.class.getResourceAsStream("/images/logo.png");
                loaded = ImageIO.read(is);
            }
            case SYSTEM ->
                loaded = ImageIO.read(new File(source));
            case WEB ->
                loaded = ImageIO.read(new URL("https://example.com"));
            default ->
                throw new AssertionError();
        }
        return loaded;
    }

    public static void saveImage(BufferedImage image, String location, String format) throws IOException {
        File outputFile = new File(location + "." + format);

        boolean success = ImageIO.write(image, format, outputFile);
        if (!success) {
            throw new Error("failed to save image to location");
        }
    }

}
