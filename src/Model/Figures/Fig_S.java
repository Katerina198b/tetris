package Model.Figures;

import Model.Block;

import java.awt.*;
import java.util.ArrayList;

/**
 * Создает фигуру S
 */
public class Fig_S extends Figure {

    private int phase = -1;

    public Fig_S(int color) {
        super(4, 0, 4, 1, 5, 1, 5, 2, color);
    }

    @Override
    public void rotate() {
        phase = phase * (-1);
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX() + 2 * phase,
                blocks.get(0).getPosY(),
                blocks.get(1).getPosX() + phase, blocks.get(1).getPosY() - phase,
                blocks.get(2).getPosX(),
                blocks.get(2).getPosY(),
                blocks.get(3).getPosX() - phase,
                blocks.get(3).getPosY() - phase);

    }

}
