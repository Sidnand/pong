package ui;

import model.*;

import java.awt.*;

public class GameCanvas extends Canvas implements Runnable {

    // stores different states that are being observed
    public enum UPDATE_STATE {
            P1_SCORE, P2_SCORE
    };

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int FPS = 15;

    private final int PADDLE_HEIGHT = 100;
    private final int PADDLE_OFFSET = 10;

    private final int BALL_DIAMETER = 10;

    private final Color BLACK = new Color(0, 0, 0);
    private final Color WHITE = new Color(255, 255, 255);

    private final Game game;
    private final ScoreBoard scoreBoard;
    private final Ball ball;
    private final Paddle p1;
    private final Paddle p2;

    private boolean running; // whether the game loop is running

    public GameCanvas() {
        setSize(WIDTH, HEIGHT);
        setBackground(BLACK);

        game = new Game();
        scoreBoard = new ScoreBoard();
        ball = new Ball(WIDTH / 2 - BALL_DIAMETER / 2, HEIGHT / 2 - BALL_DIAMETER / 2, WHITE);
        p1 = new Paddle(PADDLE_OFFSET, PADDLE_HEIGHT, HEIGHT, WHITE);
        p2 = new Paddle(WIDTH - PADDLE_OFFSET, PADDLE_HEIGHT, HEIGHT, WHITE);

        ball.addObserver(scoreBoard);

        start();
    }

    public synchronized void start() {
        Thread thread = new Thread(this);
        thread.start();

        running = true;
    }

    public synchronized void stop() {
        running = false;
    }

    @Override
    public void run() {
        final float milliSec = 1000;
        final float sec = 1;
        final float nsPerSecond = 1000000000f;

        long lastTime = System.nanoTime();
        double nsPerCycle = nsPerSecond / FPS;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();

            delta += (now - lastTime) / nsPerCycle;
            lastTime = now;

            // if 1 second has passed
            while (delta >= sec) {
                delta -= sec;
                update();
            }

            repaint();

            if (System.currentTimeMillis() - lastTimer >= milliSec) {
                lastTimer += milliSec;
            }
        }

        stop();
    }

    private void update() {
        ball.update();
    }

    public void paint(Graphics g) {
        ball.render(g);
        p1.render(g);
        p2.render(g);
    }

}
