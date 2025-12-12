// QuestionManager.java
import java.util.ArrayList;

public class QuestionManager {
    private ArrayList<Question> walkingQuestions = new ArrayList<>();
    private ArrayList<Question> drivingQuestions = new ArrayList<>();

    private int index = 0;
    private boolean walkingMode;

    public QuestionManager(boolean walking) {
        walkingMode = walking;
        loadQuestions();
    }

    private void loadQuestions() {
        if (walkingMode) {
            walkingQuestions.add(new Question(
                "Where should you cross the road?",
                new String[] {"Pedestrian Crossing", "Between cars", "Mid-street", "Anywhere"},
                0
            ));
            walkingQuestions.add(new Question(
                "What should you avoid while crossing?",
                new String[] {"Using your phone", "Looking around", "Walking slow", "Wearing bright clothes"},
                0
            ));
            // add 3 more...
        } else { // DRIVING QUESTIONS
            drivingQuestions.add(new Question(
                "What does a STOP sign mean?",
                new String[] {"Full stop", "Slow a little", "Yield", "Stop only if cars come"},
                0
            ));
            // add 4 more...
        }
    }

    public Question getCurrent() {
        return walkingMode ? walkingQuestions.get(index)
                           : drivingQuestions.get(index);
    }

    public void nextQuestion() {
        index++;
        // Add scenario completion trigger if needed
    }
}
