package Model.Figures;

import Model.Block;

import java.util.ArrayList;

/**
 * Создает фигуру Z
 */
public class Fig_Z extends Figure {

    private int phase = -1;

    public Fig_Z(int color) {
        super(5, 0, 5, 1, 4, 1, 4, 2, color);

    }

    @Override
    public void rotate() {
        phase = phase * (-1);
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX() + phase,
                blocks.get(0).getPosY() + phase,
                blocks.get(1).getPosX(), blocks.get(1).getPosY(),
                blocks.get(2).getPosX() + phase,
                blocks.get(2).getPosY() - phase,
                blocks.get(3).getPosX(), blocks.get(3).getPosY() - 2 * phase);
    }

}
