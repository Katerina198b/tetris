package View;

import Model.Board;
import Model.Rating;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;

import java.util.ArrayList;


/**
 * Возращает содержимое для RatingDialog
 */
public class RatingPanel extends JPanel {
    private Rating ratingModel;
    private Label scoreLabel;
    public Button button;
    public TextField textField;
    private Label label;
    private JPanel ratingPanel;
    public  GridBagConstraints c;

    public void clearButton() {
        remove(button);
        remove(label);
        remove(textField);
        c.gridy += 1;
        add(new Label("Данные были добавлены"), c);
    }

    private JPanel makeRatingPanel(String str) {
        ArrayList<String> rating = ratingModel.getRating();
        JPanel ratingPanel = new JPanel();
        ratingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10, true));
        ratingPanel.setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(rating.size(), 1);
        ratingPanel.setLayout(layout);
        layout.minimumLayoutSize(this);
        layout.setVgap(12);
        for (String line : rating) {
            ratingPanel.add(new Label(line, Label.CENTER));
        }
        if (str != null) {
            ratingPanel.add(new Label(str, Label.CENTER));
        }
        return ratingPanel;
    }

    public void setScore(int score) {
        scoreLabel.setText("Your score is " + String.valueOf(score));
    }

    RatingPanel(Board board, Rating r) {

        int score = board.getScore().getScore();
        ratingModel = r;

        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5);
        c.fill = GridBagConstraints.VERTICAL;

        ratingPanel = makeRatingPanel(null);

        c.gridx = 0;
        c.gridy = 0;
        add(ratingPanel, c);

        c.gridy += 1;
        scoreLabel = new Label("Your score is " + String.valueOf(score), Label.CENTER);
        add(scoreLabel, c);

        c.gridy += 1;
        label = new Label("Enter your login", Label.CENTER);
        add(label, c);

        c.gridy += 1;
        textField = new TextField(12);
        add(textField, c);

        c.gridy += 1;
        button = new Button("Add to rating");

        add(button, c);

    }
}
