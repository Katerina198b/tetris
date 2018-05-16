package View;

import Model.Board;
import Model.Rating;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;

/**
 * Класс инициализирует в себе весь пользовательский интерфейс
 * Это окно самой игры и модальное окно рэйтинга
 * Игра заканчивается при закрытии любого из окон
 */
public class View {
    private Board board;
    public Window window;
    private RatingDialog ratingDialog;
    private RatingPanel panel;


    public void ratingSetVisible() {
        panel.setScore(board.getScore().getScore());
        ratingDialog.setVisible(true);
        panel.setScore(board.getScore().getScore());
    }

    public View(Board b, Rating r, PrintWriter out) {
        board = b;
        window = new Window(board);
        window.setBounds(100, 100, 470, 500);

        WindowAdapter closeAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        };

        KeyListener moveListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {
                    board.currentFigureLeft();
                }

                if (keyCode == KeyEvent.VK_RIGHT) {
                    board.currentFigureRight();
                }

                if (keyCode == KeyEvent.VK_DOWN) {
                    board.currentFigureDown();
                }

                if (keyCode == KeyEvent.VK_SPACE) {
                    board.rotateCurrentFigure();
                }
                e.consume();
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
            }

            @Override
            public void keyTyped(KeyEvent arg0) {
            }

        };

        window.addWindowListener(closeAdapter);
        window.addKeyListener(moveListener);

        ratingDialog = new RatingDialog(window);
        panel = new RatingPanel(board, r, out);
        ratingDialog.add(panel);
        ratingDialog.addWindowListener(closeAdapter);
        ratingDialog.setVisible(false);

    }
}
