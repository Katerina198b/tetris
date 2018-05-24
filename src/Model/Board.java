package Model;

import Model.Figures.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Содержит в себе состояния всех элементов на игровом поле
 */
public class Board {
    private ArrayList<Figure> figures = new ArrayList<>();
    private Figure currentFigure;
    private Figure nextFigure;
    private boolean[][] cells = new boolean[20][10];
    private Score score = new Score();
    private Random random = new Random();
    private boolean loose = false;

    public boolean getLoose() {
        return loose;
    }

    public Board() {
        currentFigure = giveRandomFigure();
        nextFigure = giveRandomFigure();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = false;
            }
        }
        figures.add(currentFigure);
    }

    public Score getScore() {
        return score;
    }

    public boolean[][] getCells() {
        return cells;
    }

    public Figure getNextFigure() {
        return nextFigure;
    }

    private Figure giveRandomFigure() {
        int i = random.nextInt(6);
        int color = random.nextInt(7);
        switch (i) {
            case 0:
                return new Fig_I(color);
            case 1:
                return new Fig_J(color);
            case 2:
                return new Fig_O(color);
            case 3:
                return new Fig_S(color);
            case 4:
                return new Fig_T(color);
            case 5:
                return new Fig_L(color);
            case 6:
                return new Fig_Z(color);

        }
        return null;
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }


    private void refreshStatus(Figure figure) {
        for (Block block : figure.getBlocks()) {
            if (cells[block.getPosY()][block.getPosX()]) {
                loose = true;
            }
        }
    }

    /**
     * Добавляет новую фигуру на поле и обновляет следующую
     * Если новой фигуре некуда вставать - выставляет loose = true
     */
    public void refreshCurrentFigure() {
        Figure possibleCurrentFigure = nextFigure;
        nextFigure = giveRandomFigure();
        refreshStatus(possibleCurrentFigure);
        figures.add(possibleCurrentFigure);
        currentFigure = possibleCurrentFigure;
        recurrentCells();

    }

    /**
     * Решает, может ли фигура продолжать движение вниз
     *
     * @return Если фигура не может далее двигаться вниз - возращает true
     * Иначе - false
     */
    public boolean currentFigureMustStopDown() {
        boolean[][] newCells = getNewCells();

        for (Block block : currentFigure.getBlocks()) {
            if (block.getPosY() == 19 || newCells[block.getPosY() + 1][block.getPosX()]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Перемещает фигуру на одну клетку ниже, если это возможно
     */
    public void currentFigureDown() {
        if (!currentFigureMustStopDown()) {
            for (int i = 0; i < 4; i++) {
                currentFigure.setBlockY(this.currentFigure.getBlockY(i) + 1, i);
            }
        }
        recurrentCells();
    }

    private boolean[][] getNewCells() {
        boolean[][] newCells = new boolean[20][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                newCells[i][j] = cells[i][j];
            }
        }
        for (Block block : currentFigure.getBlocks()) {
            newCells[block.getPosY()][block.getPosX()] = false;
        }
        return newCells;
    }


    private boolean currentFigureMustStopLeft() {
        boolean[][] newCells = getNewCells();

        for (Block block : currentFigure.getBlocks()) {
            if (block.getPosX() == 0 || newCells[block.getPosY()][block.getPosX() - 1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Перемещает фигуру на одну клетку влево, если это возможно
     */
    public void currentFigureLeft() {
        if (!currentFigureMustStopLeft()) {
            for (int i = 0; i < 4; i++) {
                currentFigure.setBlockX(currentFigure.getBlockX(i) - 1, i);
            }
        }
        recurrentCells();
    }

    /**
     * Сделать ход вниз
     */
    public void refresh() {
        if (!currentFigureMustStopDown()) {
           currentFigureDown();

        } else {
           cleanLinesIfNeeded();
           refreshCurrentFigure();
        }
    }

    private boolean currentFigureMustStopRight() {
        boolean[][] newCells = getNewCells();

        for (Block block : currentFigure.getBlocks()) {
            if (block.getPosX() == 9 || (newCells[block.getPosY()][block.getPosX() + 1])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Перемещает фигуру на одну клетку вправо, если это возможно
     */
    public void currentFigureRight() {

        if (!currentFigureMustStopRight()) {
            for (int i = 0; i < 4; i++) {
                currentFigure.setBlockX(this.currentFigure.getBlockX(i) + 1, i);
            }
        }
        recurrentCells();
    }

    /**
     * Отмечает пустые клетки как false а заполненные как true
     */
    public void recurrentCells() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = false;
            }
        }

        for (Figure figure : figures) {
            for (Block block : figure.getBlocks()) {
                cells[block.getPosY()][block.getPosX()] = true;
            }
        }
    }


    /**
     * Удаляет линию - если она полностью заполнена
     * Удаляет фигуры - если ее блоков на доске больше нет
     * Удалеят у фигуры блоки, которые были в заполненной линии
     * Добавляет очки пользователю
     */
    public void cleanLinesIfNeeded() {
        recurrentCells();
        ArrayList<Integer> cleanLines = new ArrayList<>();
        int s = 0;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (!cells[i][j]) {
                    break;
                }
                if (j == 9) {
                    cleanLines.add(i);
                    s++;
                }
            }
        }

        for (Integer line : cleanLines) {
            for (Figure figure : figures) {
                figure.getBlocks().removeIf((block) -> line == block.getPosY());
            }
        }

        figures.removeIf((figure) -> figure.getBlocks().size() == 0);

        for (Integer line : cleanLines) {
            for (Figure figure : figures) {
                for (Block block : figure.getBlocks()) {
                    if (block.getPosY() < line) {
                        block.setPosY(block.getPosY() + 1);
                    }
                }
            }
        }
        score.addPoints(s);
        recurrentCells();
    }

    /**
     * Поворачивает текущую фигуру
     */
    public void rotateCurrentFigure() {
        currentFigure.rotate();
        recurrentCells();
    }
}
