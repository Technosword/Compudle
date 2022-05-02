package us.techno.events;


import us.techno.events.handlers.EventHandler;
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
                EventHandler.handleEvent(e);
        }

        public void keyReleased(KeyEvent e) {

        }
    }
