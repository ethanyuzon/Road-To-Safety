// DrivingScenario.java
import java.awt.*;
import java.awt.event.KeyEvent;

public class DrivingScenario {

    private PlayerCar car;
    private QuestionManager questions;

    public DrivingScenario() {
        car = new PlayerCar(2);  // start in lane 2
        questions = new QuestionManager(false);
    }

    public void update() {
        car.update();

        // Check answer if player crosses checkpoint
        if (car.isAtAnswerZone()) {
            int lane = car.getLane();
            checkAnswer(lane);
        }
    }

    private void checkAnswer(int lane) {
        Question q = questions.getCurrent();
        if (lane == q.correctIndex) {
            car.showFeedback(true);
        } else {
            car.showFeedback(false);
        }
        questions.nextQuestion();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(120, 120, 120));
        g.fillRect(0, 0, 800, 600);

        drawLanes(g);
        drawQuestion(g);
        car.draw(g);
    }

    private void drawLanes(Graphics g) {
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            g.drawLine(200 * i + 200, 0, 200 * i + 200, 600);
        }
    }

    private void drawQuestion(Graphics g) {
        Question q = questions.getCurrent();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(q.question, 50, 50);

        for (int i = 0; i < 4; i++) {
            g.drawString((char)('A' + i) + ": " + q.answers[i],
                        210 + i * 200, 100);
        }
    }

    public void keyPressed(KeyEvent e) {
        car.keyPressed(e);
    }
}
