import Model.Board;
import Model.Rating;
import View.View;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Считывает 5 лучший результатов пользователей с сервера
 * Создается модель доски, визульаную составляющую и контроллер
 * (Используется MVC модель)
 */
public class Main {
    private final static int PORT = 11111;
    private final static String HOST = "localhost";

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
            View view = new View(board, rating, out);

            try {
                Controller controller = new Controller(board, view);
                controller.start();
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