package model.object.paddle;

import observer.Observable;
import observer.Observer;
import ui.GameCanvas;

import java.awt.*;

public class RightPaddle extends Paddle implements Observer {
    public RightPaddle(int x0, int h, int canvasH, Color c) {
        super(x0, h, canvasH, c);
    }

    @Override
    public void update(Observable s, Object args) {
        if (args == GameCanvas.UPDATE_STATE.P2_MOVE_UP) {
            this.moveUp();
        } else if (args == GameCanvas.UPDATE_STATE.P2_MOVE_DOWN) {
            this.moveDown();
        }

    }
}
