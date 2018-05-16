package View;

import Model.Board;

import java.awt.*;

/**
 * Класс создает окно игры которое содержит:
 * Панель информации
 * Игровое поле
 */
public class Window extends Frame {

    private Field field = new Field();
    private InfoPanel infoPanel = new InfoPanel();
    private Board board;

    public Window(Board b) {

        board = b;
        setTitle("Super Tetris");

        BorderLayout layout = new BorderLayout();

        setLayout(layout);

        add(new Label(), BorderLayout.WEST);
        add(field, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);

        setVisible(true);
        setResizable(false);
    }

    /**
     * Пересобирает поле
     * Раскрашивает клетки поля в белый цвет - если на нем нет блока
     * И в цвет блока - иначе
     */
    public void refresh() {

        // Раскрашивает клетки в белый цвет
        boolean[][] cells = board.getCells();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (!cells[i][j]) {
                    field.colorPanel(i, j, 7);
                }
            }
        }

        // Раскрашивает клетки в цвета
        board.getFigures().forEach(figure ->
                figure.getBlocks().forEach(block -> {
                            field.colorPanel(block.getPosY(), block.getPosX(), block.getColor());
                        }
                ));

        infoPanel.getNextFigurePanel().refreshNextFigure(board.getNextFigure());
        infoPanel.getScore().refreshScore(board.getScore().getScore());
    }
}

