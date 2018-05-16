package Model.Figures;

import Model.Block;

import java.util.ArrayList;

/**
 * Создает фигуру L
 */
public class Fig_L extends Figure {

    private int phaseX = 1;
    private int phaseY = 1;
    private int phase = 1;
    private int l = 0;

    public Fig_L(int color) {
        super(4, 0, 4, 1, 4, 2, 5, 2, color);
    }

    @Override
    public void rotate() {
        phase = phase * (-1);
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX() + phaseX,
                blocks.get(0).getPosY() + phaseY,
                blocks.get(1).getPosX(), blocks.get(1).getPosY(),
                blocks.get(2).getPosX() - phaseX,
                blocks.get(2).getPosY() - phaseY,
                blocks.get(3).getPosX() + 2 * phaseX * (l % 2),
                blocks.get(3).getPosY() - 2 * phaseY * ((l + 1) % 2));

        phaseX = (int) (phaseX * Math.pow(-1, (phase) % 4));
        phaseY = (int) (phaseY * Math.pow(-1, (phase + 1) % 4));
        phase += 1;
        l += 1;

    }

}
