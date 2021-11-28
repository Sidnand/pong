package model;

import observer.Observable;
import ui.GameCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard extends Observable implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            notifyObservers(GameCanvas.UPDATE_STATE.P2_MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            notifyObservers(GameCanvas.UPDATE_STATE.P2_MOVE_DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            notifyObservers(GameCanvas.UPDATE_STATE.P1_MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            notifyObservers(GameCanvas.UPDATE_STATE.P1_MOVE_DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
