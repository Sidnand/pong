package ui;

import model.*;
import model.object.Ball;
import model.object.paddle.LeftPaddle;
import model.object.paddle.RightPaddle;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends Canvas implements Runnable {

    // stores different states that are being observed
    public enum UPDATE_STATE {
        P1_SCORE, P2_SCORE,
        P1_MOVE_UP, P1_MOVE_DOWN,
        P2_MOVE_UP, P2_MOVE_DOWN
    };

    private String ROBOTO_MONO_PATH = "./asset/font/roboto_mono.ttf";

    private Font ROBOTO_MONO;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int FPS = 15;

    private final int SCORE_TEXT_X = 100;
    private final int SCORE_TEXT_Y = 100;

    private final int PADDLE_HEIGHT = 50;
    private final int PADDLE_OFFSET = 10;

    private final int BALL_DIAMETER = 10;

    private final Color BLACK = new Color(0, 0, 0);
    private final Color WHITE = new Color(255, 255, 255);

    private final ScoreBoard scoreBoard;
    private final Keyboard keyboard;
    private final Ball ball;
    private final LeftPaddle p1;
    private final RightPaddle p2;

    private boolean running; // whether the game loop is running

    public GameCanvas() {
        setSize(WIDTH, HEIGHT);
        setBackground(BLACK);

        scoreBoard = new ScoreBoard();
        keyboard = new Keyboard();
        ball = new Ball(WIDTH / 2 - BALL_DIAMETER / 2, HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, WHITE);
        p1 = new LeftPaddle(PADDLE_OFFSET, PADDLE_HEIGHT, HEIGHT, WHITE);
        p2 = new RightPaddle(WIDTH - PADDLE_OFFSET, PADDLE_HEIGHT, HEIGHT, WHITE);

        this.addKeyListener(keyboard);

        ball.addObserver(scoreBoard);
        keyboard.addObserver(p1);
        keyboard.addObserver(p2);

        ROBOTO_MONO = new Font("Roboto Mono", Font.PLAIN, 30);

        loadFont(ROBOTO_MONO_PATH);

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

    // loads a font from a file
    public void loadFont(String path) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
        } catch (IOException |FontFormatException e) {
            System.out.println(e);
        }
    }

    private void update() {
        ball.checkCollisionWithPaddle(p1);
        ball.checkCollisionWithPaddle(p2);
        ball.checkOutOfBounds(WIDTH, HEIGHT);
        ball.updateY();
    }

    public void paint(Graphics g) {
        ball.render(g);
        p1.render(g);
        p2.render(g);

        displayScore(g);
    }

    public void displayScore(Graphics g) {
        g.setFont(ROBOTO_MONO);

        g.drawString(Integer.toString(scoreBoard.getP1Score()), SCORE_TEXT_X, SCORE_TEXT_Y);
        g.drawString(Integer.toString(scoreBoard.getP2Score()), WIDTH - SCORE_TEXT_X, SCORE_TEXT_Y);
    }

}
