import Model.Board;
import View.View;

import static java.lang.Thread.sleep;


/**
 * Создает цикл игры, пока loose != 0
 * После открывает модальное окно с рэйтингом
 */
public class Controller {
    private Board board;
    private View view;

    Controller(Board b, View v) {
        board = b;
        view = v;
    }

    public void start() throws InterruptedException {
        while (!board.getLoose()) {
            view.window.refresh();
            sleep(500);

            if (!board.currentFigureMustStopDown()) {
                board.currentFigureDown();

            } else {
                board.cleanLinesIfNeeded();
                board.refreshCurrentFigure();
            }
        }
        view.ratingSetVisible();
    }
}
