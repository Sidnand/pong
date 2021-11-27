package model;

import observer.Observable;

import java.awt.*;

public class Ball extends Component {

    public Ball(int x0, int y0, Color c) {
        super(x0, y0, 10, 10, 5, c);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.fillOval(this.x, this.y, this.WIDTH, this.HEIGHT);
    }

    @Override
    public void update() {
        super.update();
        this.x += this.SPEED;
    }
}
