/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UI.elements;

import UI.elements.components.ControlPanel;
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

public class MainWindow extends JFrame {

    // --- Attributes ---
    private CountDownMode countDownMode = CountDownMode.Constant;
    private final ViewportController viewportController = new ImageViewportController();
    private final ControlPanel cp = new ControlPanel(countDownMode);
    private CounterDowner counter;
    private int selectedDurationIndex = 0;

    // Constant mode duration options
    private final int[] constantDurationOptions = {10, 30, 60, 120, 300};

    // ClassPeriod preset datasets
    private int[][][] classPeriodTabla = {
        {{30, 60, 300, 600}, {10, 5, 2, 1}},
        {{30, 60, 300, 600, 1500}, {10, 5, 2, 1, 1}},
        {{30, 60, 180, 600, 1500, 2100}, {6, 3, 2, 1, 1, 1}},};

    private ClassPeriod[] classPeriods = {
        new ClassPeriod(classPeriodTabla[0][0], classPeriodTabla[0][1]),
        new ClassPeriod(classPeriodTabla[1][0], classPeriodTabla[1][1]),
        new ClassPeriod(classPeriodTabla[2][0], classPeriodTabla[2][1])
    };

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

    // --- Constructor ---
    public MainWindow() throws HeadlessException {
        // Initialize with default Constant mode counter
        this.counter = new CounterDowner(0); // Assuming standard Constant CounterDowner exists, or use your default implementation
        this.counter.setDuration(constantDurationOptions[selectedDurationIndex]);

        setupControllerLogic();
        setupControlPanelActions();

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(cp, BorderLayout.EAST);
        this.add(viewportController.getPanel(), BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(500, 500));

        // Push initial options to control panel based on starting mode
        updateControlPanelModeUI();
    }

    // --- Setup Methods ---
    private void setupControlPanelActions() {
        cp.setPlayButtonAction(playAction);
        cp.setPauseButtonAction(pauseAction);
        cp.setNextButtonAction(nextAction);
        cp.setPrevButtonAction(prevAction);

        cp.setSwitchModeAction((mode) -> {
            this.countDownMode = mode;
            this.selectedDurationIndex = 0; // Reset index on switch
            counter.stop();
            isPlaying = false;

            if (countDownMode == CountDownMode.Classic) {
                counter = new ClassModeCounterDowner(classPeriods[selectedDurationIndex]);
            } else {
                counter = new CounterDowner(0);
                counter.setDuration(constantDurationOptions[selectedDurationIndex]);
            }

            setupControllerLogic();
            updateControlPanelModeUI();

            // Reset display view for the new mode
            cp.setProgress(100);
            cp.setTimeLabeText(DurationToText(getCurrentDurationValue()));
        });

        cp.setPickDurationOptionAction((index) -> {
            this.selectedDurationIndex = index;
            counter.stop();
            isPlaying = false;

            if (countDownMode == CountDownMode.Classic) {
                counter = new ClassModeCounterDowner(classPeriods[selectedDurationIndex]);
            } else {
                counter.setDuration(constantDurationOptions[selectedDurationIndex]);
            }

            cp.setProgress(100);
            cp.setTimeLabeText(DurationToText(getCurrentDurationValue()));
        });
    }

    private void updateControlPanelModeUI() {
        if (countDownMode == CountDownMode.Classic) {
            cp.setDurationOptions(classPeriodOptionsText());
        } else {
            cp.setDurationOptions(constantDurationOptionsText());
        }
        cp.setTimeLabeText(DurationToText(getCurrentDurationValue()));
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
        cp.setTimeLabeText(DurationToText(getCurrentDurationValue()));
    }

    // --- Helpers ---
    private int getCurrentDurationValue() {
        if (countDownMode == CountDownMode.Classic) {
            return classPeriods[selectedDurationIndex].getTotalTimeSpent();
        } else {
            return constantDurationOptions[selectedDurationIndex];
        }
    }

    private String[] constantDurationOptionsText() {
        return Arrays.stream(constantDurationOptions)
                .mapToObj(this::DurationToText)
                .toArray(String[]::new);
    }

    private String[] classPeriodOptionsText() {
        return Arrays.stream(classPeriods)
                .map(cpObj -> DurationToText(cpObj.getTotalTimeSpent()))
                .toArray(String[]::new);
    }

    private int calcProgress(int remainingTime) {
        int total = getCurrentDurationValue();
        if (total == 0) {
            return 0;
        }
        return (int) ((float) remainingTime / total * 100);
    }

    private String DurationToText(int duration) {
        return String.format("%02dm %02ds", duration / 60, duration % 60);
    }
}
