package us.techno.listeners;


import us.techno.game.Game;
import us.techno.game.GameStatus;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Locale;

public class KeyPressListener implements KeyListener {

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
                Game game = Game.getGame();
                if (game.getGameStatus() != GameStatus.PLAYING) return;
                int index = game.getSquareIndex();
                String keyPressed = String.valueOf(e.getKeyChar()).toUpperCase(Locale.ROOT);
                boolean isTimeToGuess = index == 4 || index == 9 || index == 14 || index == 19 || index == 24 || index == 29;
                System.out.println(index);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (isTimeToGuess) {
                                try {
                                        game.guess(index);
                                } catch (URISyntaxException ex) {
                                        ex.printStackTrace();
                                        //this shouldn't happen but you never know!
                                }
                                return;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if (index == 0 || index == 5 || index == 10 || index == 15 || index == 20 || index == 25) return;
                        if (isTimeToGuess) {
                                game.setCanEditGuess(true);
                                JLabel square = Arrays.stream(game.getSquares()).toList().get(index);
                                if (square.getText().equalsIgnoreCase("")) {
                                        JLabel correctSquare = Arrays.stream(game.getSquares()).toList().get(index - 1);
                                        correctSquare.setText("");
                                        game.setSquareIndex(index - 1);
                                } else {
                                        square.setText("");
                                        game.setSquareIndex(index);
                                }
                        } else {
                                index = index - 1;
                                game.setSquareIndex(index);
                                JLabel square = Arrays.stream(game.getSquares()).toList().get(index);
                                square.setText("");
                        }
                        return;
                }
                if (!game.isCanEditGuess()) return;

                if (!Arrays.stream(game.getAlphabet()).toList().contains(keyPressed)) {
                        return;
                }

                JLabel square = Arrays.stream(game.getSquares()).toList().get(index);
                square.setText(keyPressed);

                if (isTimeToGuess) {
                        game.setCanEditGuess(false);
                        return;
                }
                game.setSquareIndex(index + 1);
        }

        public void keyReleased(KeyEvent e) {

        }
    }
