package model.object;

import ui.GameCanvas;

import java.awt.*;

public class Ball extends Component {

    private int DX = 5;

    public Ball(int x0, int y0, int d, Color c) {
        super(x0, y0, d, d, 5, c);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.fillOval(this.x, this.y, this.WIDTH, this.HEIGHT);
    }

    @Override
    public void updateY() {
        super.updateY();
        this.x += this.DX;
    }

    // checks if ball is outside w * h
    public void checkOutOfBounds(int w, int h) {
        int right = this.x + this.WIDTH;
        int left = this.x;
        int top = this.y;
        int bottom = this.y + this.HEIGHT;

        if (right < 0) {
            notifyObservers(GameCanvas.UPDATE_STATE.P2_SCORE);
            this.reset(w, h);
        } else if (left > w) {
            notifyObservers(GameCanvas.UPDATE_STATE.P1_SCORE);
            this.reset(w, h);
        } else if (top <= 0 || bottom >= h)  {
            this.DY *= -1;
        }
    }

    // resets the ball
    public void reset(int w, int h) {
        this.x = w / 2 - this.WIDTH / 2;
        this.y = h / 2 - this.HEIGHT / 2;
        this.DX *= -1;
    }

    public void checkCollisionWithPaddle(Component p) {
        if (this.collided(p)) {
            this.DX *= -1;
        }
    }
}
