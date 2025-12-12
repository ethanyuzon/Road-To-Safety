// GamePanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Thread gameThread;
    private boolean running = false;

    private GameState gameState = GameState.WALKING;

    private WalkingScenario walkingScenario;
    private DrivingScenario drivingScenario;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        walkingScenario = new WalkingScenario();
        drivingScenario = new DrivingScenario();
    }

    public void startGameLoop() {
        if (gameThread == null) {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        double FPS = 60.0;
        double nsPerFrame = 1_000_000_000 / FPS;
        long lastTime = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (delta >= 1) {
                updateGame();
                repaint();
                delta--;
            }
        }
    }

    private void updateGame() {
        switch (gameState) {
            case WALKING:
                walkingScenario.update();
                break;
            case DRIVING:
                drivingScenario.update();
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (gameState) {
            case WALKING:
                walkingScenario.draw(g);
                break;
            case DRIVING:
                drivingScenario.draw(g);
                break;
        }
    }

    // Input
    @Override
    public void keyPressed(KeyEvent e) {
        switch (gameState) {
            case WALKING:
                walkingScenario.keyPressed(e);
                break;
            case DRIVING:
                drivingScenario.keyPressed(e);
                break;
        }

        // Switch scenarios manually w/ TAB (for testing)
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            gameState = (gameState == GameState.WALKING)
                ? GameState.DRIVING : GameState.WALKING;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
