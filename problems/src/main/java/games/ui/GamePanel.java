package games.ui;

import games.common.GameEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.concurrent.Flow;

public class GamePanel extends JPanel implements Flow.Subscriber<GameEvent> {

    public GamePanel(int size) {
        setBackground(Color.WHITE); // Set a background color for the game panel
        GridLayout layout = new GridLayout(size, size);
        setLayout(layout);
        setBorder(BorderFactory.createEtchedBorder());

        //todo initialize field here
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
    }

    @Override
    public void onNext(GameEvent item) {
        for (int i = 0; i < item.getField().length; i++) {
            for (int j = 0; j < item.getField().length; j++) {
                add(new Xpanel());
            }
        }
        repaint();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

class Xpanel extends JPanel {
    public Xpanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void paint(Graphics g) {
        g.drawLine(0, 0, this.getWidth(), this.getHeight());
        g.drawLine(0, this.getHeight(), this.getWidth(), 0);
    }
}

class Opanel extends JPanel {
    public Opanel() {
        setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    public void paint(Graphics g) {
        g.drawOval(0, 0, this.getWidth(), this.getHeight());
    }
}