package us.techno.events;

import us.techno.events.handlers.EventHandler;

import java.awt.event.ActionEvent;

public class ActionListener implements java.awt.event.ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        EventHandler.handleEvent(e);
    }
}
