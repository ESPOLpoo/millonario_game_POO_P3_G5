package org.game.model.data;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

public class Chronometer {
    ProgressIndicator pi;
    private Timeline timeline;
    private int remainingSeconds = 60;
    private double x = 0;

    public Chronometer(ProgressIndicator p){
        pi = p;

        timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> updateProgress())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        remainingSeconds = 60;
        pi.setProgress(1.0);
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void resume() {
        timeline.play();
    }

    public void stop(){ timeline.stop(); }

    private void updateProgress() {
        remainingSeconds--;
        double progress = (double) 1 - (x / 600);
        pi.setProgress(progress);

        if (progress <= 0) {
            timeline.stop();
        }
        x++;
    }

    public void restartProgress() {
        timeline.stop();

        remainingSeconds = 60;
        x = 0;
        pi.setProgress(1.0);

        timeline.play();
    }

}
