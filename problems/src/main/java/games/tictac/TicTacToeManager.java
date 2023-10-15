package games.tictac;

import games.common.GameEvent;
import games.common.GameManager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Flow;

public class TicTacToeManager implements GameManager {
    private final Set<Flow.Subscriber<? super GameEvent>> subscribers = new HashSet<>();

    private Game game;

    public void newGame() {
        game = new Game();
        for (Flow.Subscriber<? super GameEvent> sub : subscribers) {
            GameEvent item = new GameEvent("New Game begins!");
            item.setField(game.getField());
            sub.onNext(item);
        }
    }

    @Override
    public int fieldSize() {
        return 3;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super GameEvent> subscriber) {
        subscribers.add(subscriber);
    }
}
