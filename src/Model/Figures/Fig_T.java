package Model.Figures;

import Model.Block;

import java.util.ArrayList;


/**
 * Создает фигуру T
 */
public class Fig_T extends Figure {

    private int phase = -1;

    public Fig_T(int color) {
        super(5, 0, 5, 1, 4, 1, 5, 2, color);
    }


    @Override
    public void rotate() {
        phase = phase * (-1);
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX(),
                blocks.get(0).getPosY() + 2 * phase,
                blocks.get(1).getPosX() - phase,
                blocks.get(1).getPosY() + phase,
                blocks.get(2).getPosX(), blocks.get(2).getPosY(),
                blocks.get(3).getPosX() - 2 * phase,
                blocks.get(3).getPosY());
    }

}
