// PlayerCar.java
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerCar {
    private int lane;  
    private int y = 500;
    private String feedback = "";

    public PlayerCar(int lane) {
        this.lane = lane;
    }

    public void update() {
        // Car moves up automatically
        y -= 2;
        if (y < 100) { 
            y = 500; // reset zone for next question
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && lane > 0)
            lane--;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && lane < 3)
            lane++;
    }

    public boolean isAtAnswerZone() {
        return y == 150;
    }

    public void showFeedback(boolean correct) {
        feedback = correct ? "Correct!" : "Incorrect!";
    }

    public int getLane() { return lane; }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        int x = 200 * lane + 100;
        g.fillRect(x, y, 40, 60);

        g.setColor(Color.WHITE);
        g.drawString(feedback, x, y - 10);
    }
}
