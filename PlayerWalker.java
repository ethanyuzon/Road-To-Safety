// PlayerWalker.java
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerWalker {
    private int x, y;
    private boolean up, down, left, right;
    private String feedback = "";

    public PlayerWalker(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (up) y -= 3;
        if (down) y += 3;
        if (left) x -= 3;
        if (right) x += 3;
    }

    // Detects which crosswalk lane player is in (0–3)
    public int getLaneSelected() {
        if (y < 320 && y > 280) {
            if (x > 150 && x < 230) return 0;
            if (x > 270 && x < 350) return 1;
            if (x > 390 && x < 470) return 2;
            if (x > 510 && x < 590) return 3;
        }
        return -1;
    }

    public void showFeedback(boolean correct) {
        feedback = correct ? "Correct!" : "Incorrect!";
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);

        g.setColor(Color.BLACK);
        g.drawString(feedback, x - 20, y - 10);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = true; break;
            case KeyEvent.VK_S: down = true; break;
            case KeyEvent.VK_A: left = true; break;
            case KeyEvent.VK_D: right = true; break;
        }
    }
}
