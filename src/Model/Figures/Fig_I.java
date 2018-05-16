package Model.Figures;

import Model.Block;

import java.util.ArrayList;

/**
 * Создает фигуру палочку
 */
public class Fig_I extends Model.Figures.Figure {

    private int phase = -1;

    public Fig_I(int color) {
        super(3, 0, 4, 0, 5, 0, 6, 0, color);
    }

    @Override
    public void rotate() {
        phase = phase * (-1);
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX() + 2 * phase,
                blocks.get(0).getPosY() + 2 * phase,
                blocks.get(1).getPosX() + phase,
                blocks.get(1).getPosY() + phase,
                blocks.get(2).getPosX(), blocks.get(2).getPosY(),
                blocks.get(3).getPosX() - phase,
                blocks.get(3).getPosY() - phase);

    }
}
