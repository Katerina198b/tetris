package Model.Figures;

import Model.Block;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

/**
 * Создает фигуру J
 */
public class Fig_J extends Figure {

    private int phaseX = 1;
    private int phaseY = 1;
    private int phase = 1;
    private int l = 0;

    public Fig_J(int color) {
        super(5, 0, 5, 1, 5, 2, 4, 2, color);
    }

    @Override
    public void rotate() {
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX() + phaseX,
                blocks.get(0).getPosY() + phaseY,
                blocks.get(1).getPosX(), blocks.get(1).getPosY(),
                blocks.get(2).getPosX() - phaseX,
                blocks.get(2).getPosY() - phaseY,
                blocks.get(3).getPosX() - 2 * phaseX * (l % 2),
                blocks.get(3).getPosY() - 2 * phaseY * ((l + 1) % 2));

        phaseX = (int) (phaseX * Math.pow(-1, (phase) % 4));
        phaseY = (int) (phaseY * Math.pow(-1, (phase + 1) % 4));
        phase += 1;
        l += 1;

    }


}
