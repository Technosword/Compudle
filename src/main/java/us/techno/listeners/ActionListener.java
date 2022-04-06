package us.techno.listeners;

import us.techno.game.Game;

import java.awt.event.ActionEvent;

public class ActionListener implements java.awt.event.ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Game.getGame().getPlayAgainButton()) {
            Game.getGame().newGame();
        }
    }
}
