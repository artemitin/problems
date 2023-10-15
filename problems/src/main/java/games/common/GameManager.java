package games.common;

import games.common.GameEvent;

import java.util.concurrent.Flow;

public interface GameManager extends Flow.Publisher<GameEvent> {
    void newGame();

    int fieldSize();
}
