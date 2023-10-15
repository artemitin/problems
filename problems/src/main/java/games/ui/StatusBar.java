package games.ui;

import games.common.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class StatusBar extends JPanel implements Flow.Subscriber<GameEvent> {
    private static StatusBar statusBar;
    private JLabel label;

    public StatusBar() {
        setPreferredSize(new Dimension(800, 30)); // Set the preferred height of the status panel
        setBackground(Color.LIGHT_GRAY); // Set a background color for the status panel
        // Add status information or components here
        setBorder(BorderFactory.createEtchedBorder());
        this.label = new JLabel();
        add(label);
    }

    public static StatusBar getInstance() {
        if (statusBar == null) {
            statusBar = new StatusBar();
        }

        return statusBar;
    }

    public void setMessage(String message) {
        this.label.setText(message);
        this.label.repaint();
    }

    @Override
    public void onNext(GameEvent item) {
        setMessage(item.getMessage());
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
