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

import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Возращает содержимое для RatingDialog
 */
class RatingPanel extends JPanel {
    private Rating ratingModel;
    private Label scoreLabel;

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

    RatingPanel(Board board, Rating r, PrintWriter out) {

        int score = board.getScore().getScore();
        ratingModel = r;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5);
        c.fill = GridBagConstraints.VERTICAL;

        JPanel ratingPanel = makeRatingPanel(null);

        c.gridx = 0;
        c.gridy = 0;
        add(ratingPanel, c);

        c.gridy += 1;
        scoreLabel = new Label("Your score is " + String.valueOf(score), Label.CENTER);
        add(scoreLabel, c);

        c.gridy += 1;
        Label l = new Label("Enter your login", Label.CENTER);
        add(l, c);

        c.gridy += 1;
        TextField input = new TextField(12);
        add(input, c);

        c.gridy += 1;
        Button button = new Button("Add to rating");

        RatingListener listener = new RatingListener(input, score, out, button, l, ratingPanel, this, c);
        button.addActionListener(listener);
        add(button, c);

    }
}
