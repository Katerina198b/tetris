import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Сервер - записывает результаты пользователей в файл rating.txt
 * Для пользователя возвращает пять лучших результатов
 */
public class Server {
    private static final String FILENAME = "rating.txt";
    private static final int DEFAULT_MAX_CONNECT = Runtime.getRuntime().availableProcessors();
    private final static int SOCKET_PORT = 11111;

    public static void main(String[] args) {
        Comparator<String> RatingComparator = new Comparator<String>() {

            public int compare(String s1, String s2) {

                Integer score1 = Integer.valueOf(s1.split(": ")[1]);
                Integer score2 = Integer.valueOf(s2.split(": ")[1]);
                return score2 - score1;
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(DEFAULT_MAX_CONNECT);
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(SOCKET_PORT);
            while (!Thread.currentThread().isInterrupted()) {
                ServerSocket finalServerSocket = serverSocket;
                pool.submit(() -> {
                    Socket socket = null;
                    try {
                        try {
                            socket = finalServerSocket.accept();
                        } catch (IOException e) {
                            System.err.println("Не удалось создать сокет");
                            e.printStackTrace();
                        }
                        System.out.println("Соединение с новым клиентом");

                        FileWriter fileWriter = null;
                        FileReader fileReader = null;
                        BufferedReader bufferedReader = null;
                        BufferedWriter bufferedWriter = null;
                        PrintWriter printWriter = null;
                        BufferedReader bufferIn = null;

                        try {
                            fileWriter = new FileWriter(FILENAME, true);
                            fileReader = new FileReader(FILENAME);
                            bufferedReader = new BufferedReader(fileReader);
                            printWriter = new PrintWriter(socket.getOutputStream(), true);

                            String line;
                            ArrayList<String> lines = new ArrayList<>();
                            while ((line = bufferedReader.readLine()) != null && !line.equals("")) {
                                lines.add(line);
                            }
                            Collections.sort(lines, RatingComparator);
                            for (int i = 0; i < 5; i++) {
                                printWriter.println(lines.get(i));
                            }

                            printWriter.println();
                            bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String login = bufferIn.readLine();
                            String score = bufferIn.readLine();

                            try {
                                bufferedWriter = new BufferedWriter(fileWriter);
                                bufferedWriter.write(login + ": " + score + '\n');
                                printWriter.println(login + ": " + score);

                            } catch (IOException e) {
                                System.err.println("Не получилось добавить данные" + login + ' ' + score);
                                e.printStackTrace();
                            }

                        } catch (IOException e) {
                            System.err.println("Не удалось обменяться информацией с клиентом");
                            e.printStackTrace();

                        } finally {
                            try {
                                if (bufferedWriter != null) {
                                    bufferedWriter.close();
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (fileReader != null) {
                                    fileReader.close();
                                }
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                                if (printWriter != null) {
                                    printWriter.close();
                                }
                                if (bufferIn != null) {
                                    bufferIn.close();
                                }
                            } catch (IOException e) {
                                System.err.println("Не получилось закрыть buffer или file");
                                e.printStackTrace();
                            }
                        }
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.err.println("Не получилось закрыть сокет");
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            System.err.println("Не удалось подключиться по указанному порту");
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.err.println("Не удалось закрыть serverSocket");
                    e.printStackTrace();
                }
            }
        }
    }
}
