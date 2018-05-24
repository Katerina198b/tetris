package Controller;

import View.RatingPanel;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.PrintWriter;

/**
 * Прослушивает нажатие на кнопку отправления на сервис нового результата
 * Если кнопка нажата, отправляет логин и баллы на сервер
 * Убирает с экрана форму ввода
 */
public class RatingListener implements ActionListener {
    private TextField textField;
    private int score;
    private PrintWriter out;
    private Button button;
    private Label label;
    private JPanel panel;
    private RatingPanel ratingPanel;
    private GridBagConstraints c;

    RatingListener(TextField t, int s, PrintWriter pw, Button b, Label l, JPanel p, RatingPanel rp, GridBagConstraints gbc) {
        textField = t;
        score = s;
        out = pw;
        button = b;
        label = l;
        panel = p;
        ratingPanel = rp;
        c = gbc;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String login = textField.getText();
        ratingPanel.remove(button);
        ratingPanel.remove(label);
        ratingPanel.remove(textField);
        panel.add(new Label(login + ": " + score));
        c.gridy += 1;
        ratingPanel.add(new Label("Данные были добавлены"), c);
        out.println(login);
        out.println(score);
    }
}