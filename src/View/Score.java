package View;

import javax.swing.*;
import java.awt.*;

/**
 * Cоздает панель очков для информационной панели
 */
public class Score extends JLabel {

    public Score() {
        super("Score: 0");
        setBackground(Color.GREEN);
    }

    void refreshScore(int score) {
        String label = "Score: " + String.valueOf(score);
        setText(label);
    }
}
