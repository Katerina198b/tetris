package View;

import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.GridLayout;

/**
 * Создает панель информации
 * Она включает в себя текущие очки
 * Следующую фигуру
 * Инструкцию
 */
public class InfoPanel extends Panel {

    private NextFigure nextFigPanel;
    private Score score;

    public InfoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 10, 10, 10);
        c.fill = GridBagConstraints.VERTICAL;

        nextFigPanel = new NextFigure();
        score = new Score();

        add(new Label("Welcome!"));

        c.gridy = 0;
        Panel instruction = new Panel();
        instruction.setLayout(new GridLayout(2, 1));
        instruction.add(new Label("Use arrows to move left or right", Label.CENTER));
        instruction.add(new Label("Use space to turn figure", Label.CENTER));
        add(instruction);

        c.gridy = 1;
        add(instruction, c);

        c.gridy = 2;
        add(score, c);

        c.gridy = 3;
        add(nextFigPanel, c);

    }

    public Score getScore() {
        return score;
    }

    NextFigure getNextFigurePanel() {
        return nextFigPanel;
    }
}

