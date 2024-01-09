import javax.swing.*;
import java.awt.event.ActionListener;

class QuizTimer {
    private Timer timer;
    private int seconds;

    public QuizTimer(int seconds, ActionListener actionListener) {
        this.seconds = seconds;
        this.timer = new Timer(1000, actionListener);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}