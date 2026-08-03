/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UI.elements;

import UI.elements.Controllers.ImageViewportController;
import UI.elements.Controllers.ViewportController;
import com.mycompany.donotimagine.ClassModeCounterDowner;
import com.mycompany.donotimagine.ClassPeriod;
import com.mycompany.donotimagine.CounterDowner;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import java.util.Arrays;
import javax.swing.SwingUtilities;

enum modes {
    classMode,
    constant
}

/**
 *
 * @author gool
 */
public class MainWindow extends JFrame {

    // --- Attributes ---
    private final ViewportController viewportController = new ImageViewportController();
    private final ControlPanel cp = new ControlPanel();
    private CounterDowner counter; // Now a field so all actions can see it
    private int selectedDurationIndex = 0;
    private final int[] durationOptions = {10, 30, 60, 120, 300};
    private int[][][] classPeriodTabla = {
        {{30, 60, 300, 600}, {10, 5, 2, 1}},
        {{30, 60, 300, 600, 1500}, {10, 5, 2, 1, 1}},
        {{30, 60, 180, 600, 1500, 2100}, {6, 3, 2, 1, 1, 1}},};
    private ClassPeriod[] classPeriods = {
        new ClassPeriod(classPeriodTabla[0][0], classPeriodTabla[0][1]),
        new ClassPeriod(classPeriodTabla[1][0], classPeriodTabla[1][1]),
        new ClassPeriod(classPeriodTabla[2][0], classPeriodTabla[2][1])};
    private boolean isPlaying = false;

    // --- Action Attributes ---
    private final Runnable playAction = () -> {
        isPlaying = true;
        viewportController.play();
        counter.start();
    };

    private final Runnable pauseAction = () -> {
        isPlaying = false;
        viewportController.pause();
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

    private void switchToclassMode() {
    }

    // --- Constructor ---
    public MainWindow() throws HeadlessException {
        int[] durations = {1, 2, 3, 4};
        int[] repetitions = {5, 4, 3, 2};
        this.counter = new ClassModeCounterDowner(new ClassPeriod(durations, repetitions));
        setupControllerLogic();
        setupControlPanelActions();
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
