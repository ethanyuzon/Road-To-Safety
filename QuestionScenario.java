// WalkingScenario.java
import java.awt.*;
import java.awt.event.KeyEvent;

public class WalkingScenario {

    private PlayerWalker player;
    private QuestionManager questions;

    private int selectedLane = -1;

    public WalkingScenario() {
        player = new PlayerWalker(400, 500);
        questions = new QuestionManager(true);
    }

    public void update() {
        player.update();

        // If player enters a lane, check answer
        int lane = player.getLaneSelected();
        if (lane != -1) {
            selectedLane = lane;
            checkAnswer();
        }
    }

    private void checkAnswer() {
        Question q = questions.getCurrent();
        if (selectedLane == q.correctIndex) {
            player.showFeedback(true);
        } else {
            player.showFeedback(false);
        }
        questions.nextQuestion();
        selectedLane = -1;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(140, 190, 230));
        g.fillRect(0, 0, 800, 600);

        player.draw(g);

        drawQuestion(g);
        drawCrosswalks(g);
    }

    private void drawQuestion(Graphics g) {
        Question q = questions.getCurrent();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(q.question, 50, 50);

        for (int i = 0; i < 4; i++) {
            g.drawString((char)('A' + i) + ": " + q.answers[i], 50, 80 + i * 25);
        }
    }

    private void drawCrosswalks(Graphics g) {
        int y = 300;
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            g.fillRect(150 + i * 120, y, 80, 60);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf((char)('A' + i)), 180 + i * 120, y + 30);
        }
    }

    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
}
