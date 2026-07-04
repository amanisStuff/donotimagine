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

/**
 *
 * @author gool
 */
public class MainWindow extends JFrame {

    ViewportController viewportController = new ImageVieportController();
    ControlPanel cp = new ControlPanel();
    int selectedDurationIndex = 0;
    int[] durationOptions = {10, 30, 60, 120, 300};
    boolean isPlaying = false;

    private String[] durationOptionsText() {
        return Arrays.stream(durationOptions)
                .mapToObj(this::DurationToText)
                .toArray(String[]::new);
    }

    private int calcProgress(int remainingTime) {
        float progress = (float) remainingTime / durationOptions[selectedDurationIndex] * 100;
        return (int) progress;
    }

    private String DurationToText(int duration) {
        return String.format("%02dm %02ds", duration / 60, duration % 60);
    }

    public MainWindow() throws HeadlessException {

        CounterDowner counter = new CounterDowner(3);
        counter.setEndOfCountDownTask(() -> {
            viewportController.next();
        });
        counter.setDuration(durationOptions[selectedDurationIndex]);
        cp.setTimeLabeText("00m 00s");
        cp.setDurationOptions(durationOptionsText());
        cp.setFullscreenButtonAction(() -> System.out.println("fullscreen not implemented yet"));
        cp.setSaveButtonAction(() -> System.out.println("save not implemented yet"));
        cp.setLoadButtonAction(() -> System.out.println("load not implemented yet"));
        cp.setPlayButtonAction(() -> {
            isPlaying = true;
            counter.start();
        });
        cp.setPauseButtonAction(() -> {
            isPlaying = false;
            counter.pause();
        });
        cp.setNextButtonAction(() -> {
            viewportController.next();
            counter.stop();
            if (isPlaying) {
                counter.start();
            }
        });
        cp.setPrevButtonAction(() -> {
            viewportController.previous();
            counter.stop();
            if (isPlaying) {
                counter.start();
            }
        });

        cp.setPickDurationOptionAction((index) -> {
            counter.setDuration(durationOptions[index]);
            this.selectedDurationIndex = index;
        });
        counter.setCounterTask((remainingTime) -> {
            String timeLabelText = DurationToText(remainingTime);
            cp.setTimeLabeText(timeLabelText);
            cp.setProgress(calcProgress(remainingTime));
        });

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(cp, BorderLayout.EAST);
        ImageScreen screen = new ImageScreen();
        this.add(screen, BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(500, 500));
    }

}
