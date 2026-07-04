/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UI.elements;

import UI.elements.Controllers.ImageVieportController;
import UI.elements.Controllers.ViewportController;
import com.mycompany.donotimagine.CounterDowner;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import java.util.Arrays;
import javax.swing.SwingUtilities;

/**
 *
 * @author gool
 */
public class MainWindow extends JFrame {

    // --- Attributes ---
    private final ViewportController viewportController = new ImageVieportController();
    private final ControlPanel cp = new ControlPanel();
    private CounterDowner counter; // Now a field so all actions can see it

    private int selectedDurationIndex = 0;
    private final int[] durationOptions = {10, 30, 60, 120, 300};
    private boolean isPlaying = false;

    // --- Action Attributes ---
    private final Runnable playAction = () -> {
        isPlaying = true;
        counter.start();
    };

    private final Runnable pauseAction = () -> {
        isPlaying = false;
        counter.pause();
    };

    private final Runnable nextAction = () -> {
        viewportController.next();
        handleNavigation();
    };

    private final Runnable prevAction = () -> {
        viewportController.previous();
        handleNavigation();
    };

    // --- Constructor ---
    public MainWindow() throws HeadlessException {
        // Assume initComponents() exists here if using a GUI Builder
        // initComponents();

        this.counter = new CounterDowner(durationOptions[selectedDurationIndex]);

        setupControllerLogic();
        setupControlPanelActions();

        // Final Layout
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(cp, BorderLayout.EAST);
        this.add(viewportController.getPanel(), BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(500, 500));
    }

    // --- Setup Methods ---
    private void setupControlPanelActions() {
        cp.setPlayButtonAction(playAction);
        cp.setPauseButtonAction(pauseAction);
        cp.setNextButtonAction(nextAction);
        cp.setPrevButtonAction(prevAction);
        cp.setDurationOptions(durationOptionsText());
        cp.setTimeLabeText("00m 00s");

        cp.setPickDurationOptionAction((index) -> {
            counter.setDuration(durationOptions[index]);
            this.selectedDurationIndex = index;
            cp.setProgress(100);
            cp.setTimeLabeText(DurationToText(durationOptions[index]));
        });
    }

    private void setupControllerLogic() {
        counter.setEndOfCountDownTask(viewportController::next);

        counter.setCounterTask((remainingTime) -> {
            SwingUtilities.invokeLater(() -> {
                cp.setTimeLabeText(DurationToText(remainingTime));
                cp.setProgress(calcProgress(remainingTime));
            });
        });
    }

    private void handleNavigation() {
        counter.stop();
        if (isPlaying) {
            counter.start();
        }
        cp.setProgress(100);
        cp.setTimeLabeText(DurationToText(durationOptions[selectedDurationIndex]));
    }

    // --- Helpers ---
    private String[] durationOptionsText() {
        return Arrays.stream(durationOptions)
                .mapToObj(this::DurationToText)
                .toArray(String[]::new);
    }

    private int calcProgress(int remainingTime) {
        return (int) ((float) remainingTime / durationOptions[selectedDurationIndex] * 100);
    }

    private String DurationToText(int duration) {
        return String.format("%02dm %02ds", duration / 60, duration % 60);
    }
}
