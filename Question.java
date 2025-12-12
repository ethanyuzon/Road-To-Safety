// Question.java
public class Question {
    public String question;
    public String[] answers;
    public int correctIndex;

    public Question(String q, String[] a, int correct) {
        question = q;
        answers = a;
        correctIndex = correct;
    }
}
