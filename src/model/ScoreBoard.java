package model;

import observer.Observable;
import observer.Observer;
import ui.GameCanvas.UPDATE_STATE;

public class ScoreBoard implements Observer {
    private int p1Score;
    private int p2Score;

    public ScoreBoard() {
        p1Score = 0;
        p2Score = 0;
    }

    @Override
    public void update(Observable s, Object args) {
        UPDATE_STATE state = (UPDATE_STATE) args;

        if (state == UPDATE_STATE.P1_SCORE) {
            p1Score++;
        } else if (state == UPDATE_STATE.P2_SCORE) {
            p2Score++;
        }
    }

    public int getP1Score() {
        return p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }
}
