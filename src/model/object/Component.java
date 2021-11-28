package model.object;

import observer.Observable;

import java.awt.*;

public abstract class Component extends Observable {

    protected final Color COLOUR;
    protected final int WIDTH;
    protected final int HEIGHT;

    protected int DY;

    protected int x, y;

    public Component(int x0, int y0, int w, int h, int dy, Color c) {
        this.x = x0;
        this.y = y0;

        this.DY = dy;

        this.COLOUR = c;

        this.WIDTH = w;
        this.HEIGHT = h;
    }

    // sets the colour
    public void render(Graphics g) {
        g.setColor(this.COLOUR);
    }

    // moves component in y dir
    public void updateY() {
        this.y += this.DY;
    }

    // check if this component collided with another component
    public boolean collided(Component c) {
        // todo: break conditions into variables
        return this.x + this.WIDTH > c.x &&
                this.x < c.x + c.WIDTH &&
                this.y + this.HEIGHT > c.y &&
                this.y < c.y + c.HEIGHT;
    }

}
