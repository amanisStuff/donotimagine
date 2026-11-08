/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.elements.Controllers;

import javax.swing.JPanel;

public interface ViewportController {

    public void next();

    public void previous();

    public void play();

    public void pause();

    public JPanel getPanel();
}
