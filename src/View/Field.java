package View;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * Возращает поле игры - 20х20
 * Определяет возможные цвета каждой клетки -
 * BLUE, CYAN, GREEN, MAGENTA, ORANGE, RED, YELLOW, WHITE
 */
public class Field extends Panel {

    private JPanel[][] gameF;

    private static Color[] color;

    public Field() {
        GridLayout layout = new GridLayout(20, 10);
        setLayout(layout);
        gameF = new JPanel[20][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                gameF[i][j] = new JPanel();
                add(gameF[i][j]);
                gameF[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
            }
        }

        color = new Color[8];
        color[0] = Color.BLUE;
        color[1] = Color.CYAN;
        color[2] = Color.GREEN;
        color[3] = Color.MAGENTA;
        color[4] = Color.ORANGE;
        color[5] = Color.RED;
        color[6] = Color.YELLOW;
        color[7] = Color.WHITE;

    }


    void colorPanel(int x, int y, int c) {
        this.gameF[x][y].setBackground(color[c]);
    }

    public static Color[] getColor() {
        return color;
    }
}
