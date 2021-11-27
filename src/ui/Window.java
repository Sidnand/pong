package ui;

import javax.swing.*;

public class Window {
    private final JFrame frame;

    public Window() {
        frame = new JFrame();
        GameCanvas canvas = new GameCanvas();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
    }

    // shows the frame
    public void show() {
        frame.setVisible(true);
    }
}
