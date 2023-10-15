package games;

import games.common.GameManager;
import games.tictac.TicTacToeManager;
import games.ui.GamePanel;
import games.ui.MenuPanel;
import games.ui.StatusBar;

import javax.swing.*;
import java.awt.*;

public class GameRunner {
    public static void main(String[] args) {
        //todo allow for various games
        GameManager gameManager = new TicTacToeManager();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Set your preferred window size

            // Create panels
            GamePanel gamePanel = new GamePanel(gameManager.fieldSize());
            MenuPanel menuPanel = new MenuPanel(gamePanel, gameManager);
            StatusBar statusPanel = new StatusBar();

            gameManager.subscribe(gamePanel);
            gameManager.subscribe(statusPanel);

            // Set layout for the main frame
            frame.setLayout(new BorderLayout());

            // Add panels to the main frame
            frame.add(menuPanel, BorderLayout.WEST);
            frame.add(gamePanel, BorderLayout.CENTER);
            frame.add(statusPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

}

