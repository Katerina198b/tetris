package View;

import Model.Figures.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Отображает панель следующий фигуры - поле 4х4
 */
public class NextFigure extends Panel {

    private JPanel[][] boxes;
    private Panel mainBox;
    private Color color;

    NextFigure() {
        mainBox = new Panel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(170, 200);
            }
        };
        boxes = new JPanel[4][4];
        mainBox.setLayout(new GridLayout(5, 5));

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j] = new JPanel();
                boxes[i][j].setSize(new Dimension(10, 10));
                boxes[i][j].setBackground(Color.WHITE);
                boxes[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
                mainBox.add(boxes[i][j]);
            }
        }
        add(mainBox);
    }

    /**
     * Перерисовывает панель следующей фигуры
     *
     * @param figure - следующая фигура
     */
    void refreshNextFigure(Figure figure) {

        color = Field.getColor()[figure.getBlock(0).getColor()];

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j].setBackground(Color.white);
            }
        }

        if (figure instanceof Fig_I) {
            boxes[2][0].setBackground(color);
            boxes[2][1].setBackground(color);
            boxes[2][2].setBackground(color);
            boxes[2][3].setBackground(color);
        }
        if (figure instanceof Fig_J) {
            boxes[1][2].setBackground(color);
            boxes[2][2].setBackground(color);
            boxes[3][2].setBackground(color);
            boxes[3][1].setBackground(color);
        }
        if (figure instanceof Fig_L) {
            boxes[1][1].setBackground(color);
            boxes[2][1].setBackground(color);
            boxes[3][1].setBackground(color);
            boxes[3][2].setBackground(color);
        }
        if (figure instanceof Fig_O) {
            boxes[2][1].setBackground(color);
            boxes[2][2].setBackground(color);
            boxes[3][1].setBackground(color);
            boxes[3][2].setBackground(color);
        }
        if (figure instanceof Fig_S) {
            boxes[1][1].setBackground(color);
            boxes[2][1].setBackground(color);
            boxes[2][2].setBackground(color);
            boxes[3][2].setBackground(color);
        }
        if (figure instanceof Fig_T) {
            boxes[1][2].setBackground(color);
            boxes[2][1].setBackground(color);
            boxes[2][2].setBackground(color);
            boxes[3][2].setBackground(color);
        }
        if (figure instanceof Fig_Z) {
            boxes[1][2].setBackground(color);
            boxes[2][1].setBackground(color);
            boxes[2][2].setBackground(color);
            boxes[3][1].setBackground(color);
        }
    }

}
