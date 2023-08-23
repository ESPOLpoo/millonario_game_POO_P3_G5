package util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

public class Chronometer {
    ProgressIndicator pi;
    private Timeline timeline;
    private int remainingSeconds = 60;
    private int updateFrecuency = 30;

    public Chronometer(ProgressIndicator p){
        pi = p;

        Duration frameDuration = Duration.seconds(1.0 / updateFrecuency);
        timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> updateProgress())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        remainingSeconds = 60;
        pi.setProgress(360.0);
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void resume() {
        timeline.play();
    }

    /*private void updateProgress() {
        remainingSeconds--;
        double progress = (double) remainingSeconds / 60.0;
        pi.setProgress(progress);

        if (remainingSeconds <= 0) {
            timeline.stop();
        }
    }*/

    private void updateProgress() {
        double elapsedTime = (double) (remainingSeconds - remainingSeconds);
        double progress = elapsedTime / remainingSeconds;
        pi.setProgress(progress);

        remainingSeconds--;

        if (remainingSeconds <= 0) {
            timeline.stop();
        }
    }
}
