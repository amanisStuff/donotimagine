/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donotimagine;
import java.util.logging.Logger;

/**
 *
 * @author amani kherraz
 */
public class ViewportControl {
    private String title;
    private String iconPath;
    private ViewportControlType type;
    private String[] options;

    private static final Logger logger = Logger.getLogger(ViewportControl.class.getName());

    // Private constructor: Forces usage of factory methods
    private ViewportControl(String title, String iconPath, ViewportControlType type) {
        this.title = title;
        this.iconPath = iconPath;
        this.type = type;
    }

    // --- Factory Methods ---

    public static ViewportControl optionControl(String title, String iconPath, String[] options) {
        ViewportControl control = new ViewportControl(title, iconPath, ViewportControlType.OPTIONS);
        control.options = options;
        return control;
    }

    public static ViewportControl textControl(String title, String iconPath) {
        return new ViewportControl(title, iconPath, ViewportControlType.TEXT);
    }

    public static ViewportControl playbackControl(String title, String iconPath) {
        return new ViewportControl(title, iconPath, ViewportControlType.PLAYBACK);
    }

    public static ViewportControl sliderControl(String title, String iconPath) {
        return new ViewportControl(title, iconPath, ViewportControlType.SLIDER);
    }

    public static ViewportControl checkboxControl(String title, String iconPath) {
        return new ViewportControl(title, iconPath, ViewportControlType.CHECKBOX);
    }

    // --- Getters ---
    public String getTitle() { return title; }
    public String getIconPath() { return iconPath; }
    public ViewportControlType getType() { return type; }
    public String[] getOptions() { return options; }
}
