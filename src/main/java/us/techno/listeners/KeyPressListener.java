package us.techno.listeners;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPressListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
            // Invoked when a key has been typed.
        }

        public void keyPressed(KeyEvent e) {
System.out.println("debug");        }

        public void keyReleased(KeyEvent e) {
            // Invoked when a key has been released.
        }
    }
