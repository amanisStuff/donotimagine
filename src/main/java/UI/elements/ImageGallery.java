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
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.Border;

public class ImageGallery extends JPanel {

    private final List<BufferedImage> images = new ArrayList<>();
    private AddImageBox addImageBox = new AddImageBox();
    private Consumer<String> webLinkDropped = (url) -> {
        System.out.println("url: " + url);
    };
    private Consumer<String> fileDropped = (path) -> {
        System.out.println("path: " + path);
    };

    private Runnable clickAddAction = () -> {
        System.out.println("add image actions");
    };
    private Consumer<Integer> clickRemoveAction = (index) -> {
        System.out.println("deleted an image from gallery");
        System.out.println("this does not mean it was deleted in other places");
    };

    public void setClickAddAction(Runnable clickAddAction) {
        this.clickAddAction = clickAddAction;
        addImageBox.setClickAction(clickAddAction);
    }

    public void setWebLinkDropped(Consumer<String> webLinkDropped) {
        this.webLinkDropped = webLinkDropped;
    }

    public void setFileDropped(Consumer<String> fileDropped) {
        this.fileDropped = fileDropped;
    }

    public void setClickRemoveAction(Consumer<Integer> clickRemoveAction) {
        this.clickRemoveAction = clickRemoveAction;
    }

    public ImageGallery() {
        this.setLayout(new WrapLayout(WrapLayout.LEADING, 8, 8));
        addImageBox.setClickAction(clickAddAction);
        this.add(addImageBox);
        this.setDropTarget(new DropTarget(this, new CustomDropTargetAdapter()));
    }

    public ImageGallery(List<BufferedImage> images) {
        this(); // Call the default constructor for layout setup
        for (BufferedImage img : images) {
            addImage(img);
        }
    }

    public final void addImage(BufferedImage image) {
        images.add(image);
        this.remove(addImageBox);
        JLabel label = new JLabel();
        // Scale the icon to fit the fixed dimension
        Image scaled = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
        label.setPreferredSize(new Dimension(100, 100));
        ImageWrapper imageWrapper = new ImageWrapper(label, true);
        this.add(imageWrapper);
        imageWrapper.setDeleteRunnable(() -> {
            int index = this.getComponentZOrder(imageWrapper);
            clickRemoveAction.accept(index);
            removeImage(index);
        });
        this.add(addImageBox);

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

    private class CustomDropTargetAdapter extends DropTargetAdapter {

        @Override
        public void drop(DropTargetDropEvent event) {
            System.out.println("item dropped");
            try {
                event.acceptDrop(event.getDropAction());
                if (event.isDataFlavorSupported(DataFlavor.fragmentHtmlFlavor)) {
                    String htmlContent = (String) event.getTransferable()
                            .getTransferData(DataFlavor.fragmentHtmlFlavor);
                    String url = extractUrl(htmlContent);
                    if (url != null && !url.isEmpty()) {
                        webLinkDropped.accept(url);
                        event.dropComplete(true);
                    } else {
                        event.dropComplete(false);
                    }
                } else if (event.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    List<File> files = (List<File>) event.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : files) {
                        String name = file.getAbsolutePath();
                        fileDropped.accept(name);
                    }
                    event.dropComplete(true);
                } else {
                    System.out.println("the data type rejected");
                    event.rejectDrop();
                }
            } catch (Exception e) {
                System.out.println("an error happened" + e.getMessage());
                e.printStackTrace();
                event.dropComplete(false);
            }

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
        Border selectedLine = BorderFactory.createLineBorder(new Color(150, 150, 255, 255), 2);
        boolean isSelected;
        Runnable deleteRunnable = () -> {
            System.out.println(" delete clicked!");
        };

        public void setDeleteRunnable(Runnable deleteRunnable) {
            this.deleteRunnable = deleteRunnable;
        }

        public ImageWrapper(JLabel imageLabel, boolean selected) {
            isSelected = selected;
            this.setPreferredSize(new Dimension(104, 104));
            image = imageLabel;
            image.setBounds(2, 2, 100, 100);
            deleteButton.setBounds(54, 75, 41, 20);
            deleteButton.setText("×");
            deleteButton.addActionListener((e) -> {
                deleteRunnable.run();
            });
            this.add(image, JLayeredPane.DEFAULT_LAYER);
            this.add(deleteButton, JLayeredPane.DRAG_LAYER);
            this.setBorder(selectedLine);
        }
    }

    private class AddImageBox extends JPanel {

        Border line = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
        JLabel label = new JLabel("+");
        Runnable clickAction = () -> {
            System.out.println("Panel clicked!");
        };

        public void setClickAction(Runnable action) {
            this.clickAction = action;
        }

        public AddImageBox() {
            Font font = new Font("Sans Serif", Font.BOLD, 60);
            label.setBounds(25, 25, 100, 100);
            label.setFont(font);
            label.setForeground(Color.LIGHT_GRAY);
            this.setBorder(line);
            this.setPreferredSize(new Dimension(100, 100));
            this.add(label);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickAction.run();
                }
            }
            );
        }
    }

    private String extractUrl(String html) {
        if (html == null) {
            return null;
        }

        // Try to extract URL from the src attribute
        Pattern pattern = Pattern.compile("src=\"(.*?)\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1); // Returns the URL inside src
        }

        // Fallback to href if src isn't found
        pattern = Pattern.compile("href=\"(.*?)\"", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return html.strip(); // Final fallback
    }
}
