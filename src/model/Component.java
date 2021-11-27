package model;

import observer.Observable;

import java.awt.*;

public abstract class Component extends Observable {

    protected final Color COLOUR;
    protected final int WIDTH, HEIGHT, SPEED;

    protected int x, y;

    public Component(int x0, int y0, int w, int h, int s, Color c) {
        this.x = x0;
        this.y = y0;

        this.SPEED = s;

        this.COLOUR = c;

        this.WIDTH = w;
        this.HEIGHT = h;
    }

    // sets the colour
    public void render(Graphics g) {
        g.setColor(this.COLOUR);
    }

    // moves component in y dir
    public void update() {
        this.y += this.SPEED;
    }

    // check if this component collided with another component
    public void collided(Component c) {
        // todo: implement this method
    }

}
