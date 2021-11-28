package model.object.paddle;

import observer.Observer;
import ui.GameCanvas.UPDATE_STATE;

import java.awt.*;

public class LeftPaddle extends Paddle implements Observer {
    public LeftPaddle(int x0, int h, int canvasH, Color c) {
        super(x0, h, canvasH, c);
    }

    @Override
    public void update(Object args) {
        if (args == UPDATE_STATE.P1_MOVE_UP) {
            this.moveUp();
        } else if (args == UPDATE_STATE.P1_MOVE_DOWN) {
            this.moveDown();
        }
    }
}
