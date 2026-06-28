/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.donotimagine.viewport;

import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.JPanel;

/**
 *
 * @author amani kherraz
 *
 */
public interface ViewportProvider {

    /**
     * return the controls for managing the view port.
     * <p>
     * This method returns the controls associated with this view port.
     * </p>
     * @return String[] [ The title of the control, the type of element it controls, icon path ]
     * @since 0.0
     */
    HashMap<ViewportControl, Consumer> getControls();
    JPanel getViewport();
    
}

