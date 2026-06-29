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
import org.xml.sax.InputSource;

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

    static Image loadImage(String source, imageOptions imageOption) throws IOException, AssertionError {
        BufferedImage loaded;
        switch (imageOption) {
            case ASSET:
                InputStream is = Image.class.getResourceAsStream("/images/logo.png");
                BufferedImage imgFromResource = ImageIO.read(is);
                break;
            case SYSTEM:
                loaded = ImageIO.read(new File(source));
                break;
            case WEB:
                loaded = ImageIO.read(new URL("https://example.com"));
                break;
            default:
                throw new AssertionError();
        }
        return ImageIO.read(new File(source));
    }

    static public void saveImage(Image image) {

    }

}
