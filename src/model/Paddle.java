package model;

import java.awt.*;

// represents a paddle with an inital x, y, and colour
public class Paddle extends Component {

    public Paddle(int x0, int h, int canvasH, Color c) {
        super(x0, canvasH / 2 - h / 2, 10, h, -1, c);
    }

    // renders the paddle
    @Override
    public void render(Graphics g) {
        super.render(g);
        g.fillRect(this.x, this.y, this.WIDTH, this.HEIGHT);
    }
}
