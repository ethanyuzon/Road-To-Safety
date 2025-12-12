// GameWindow.java
import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Safe Steps & Smart Lanes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.startGameLoop();
    }
}
