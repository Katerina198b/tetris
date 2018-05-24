package Controller;

import Model.Board;
import Model.Rating;
import View.View;
import View.RatingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.Label;
import java.awt.TextField;

import java.io.PrintWriter;

import static java.lang.Thread.sleep;

/**
 * Считывает 5 лучший результатов пользователей с сервера
 * Создается модель доски, визульаную составляющую и реализует контроллер
 * (Используется MVC модель)
 */
public class Controller {
    private final static int PORT = 11111;
    private final static String HOST = "localhost";

    /**
     * Прослушивает нажатие на кнопку отправления на сервис нового результата
     * Если кнопка нажата, отправляет логин и баллы на сервер
     * Убирает с экрана форму ввода
     */
    public static class RatingListener implements ActionListener {
        private int score;
        private RatingPanel ratingPanel;
        private PrintWriter out;

        RatingListener(RatingPanel ratingPanel, int score, PrintWriter out) {
            this.score = score;
            this.ratingPanel = ratingPanel;
            this.out = out;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String login = ratingPanel.textField.getText();
            ratingPanel.clearButton();
            out.println(login);
            out.println(score);
        }
    }

    public static void main(String[] args) {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            Socket socket = null;
            try {
                socket = new Socket(HOST, PORT);

            } catch (IOException e) {
                System.err.println("Не удалось создать сокет");
                e.printStackTrace();
            }

            Board board = new Board();
            ArrayList<String> ratingList = null;

            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.err.println("Не удалось получить outputStream");
                e.printStackTrace();
            }
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ratingList = new ArrayList<>();

                String line;
                for (line = in.readLine(); line != null && !line.equals(""); line = in.readLine()) {
                    ratingList.add(line);
                }
            } catch (IOException e) {
                System.err.println("Не удалось воспользоваться inputStream");
                e.printStackTrace();
            }

            Rating rating = new Rating(ratingList);
            View view = new View(board, rating);


            RatingListener listener = new RatingListener(view.getPanel(), board.getScore().getScore(), out);

            view.getPanel().button.addActionListener(listener);

            try {
                while (!board.getLoose()) {
                    view.window.refresh();
                    sleep(500);
                    board.refresh();
                }
                view.ratingSetVisible();
            } catch (InterruptedException e) {
                System.err.println("Произошла ошибка в контроллере");
                e.printStackTrace();
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Не удалось закрыть BufferedReader");
                    e.printStackTrace();
                }
            }
        }
    }
}