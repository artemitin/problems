package games.ui;

import games.common.GameManager;

import javax.swing.*;

public class MenuPanel extends JPanel {

    public MenuPanel(JPanel gamePanel, GameManager gameManager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEtchedBorder());

        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to the buttons
        newGameButton.addActionListener(e -> {
            // Handle New Game button click
            JOptionPane.showMessageDialog(gamePanel, "New Game button clicked!");
            gameManager.newGame();
        });

        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(gamePanel, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (result == 0) {
                System.exit(0);
            }
        });

        // Add buttons to the menu panel
        add(newGameButton);
        add(exitButton);
    }
}
