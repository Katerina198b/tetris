package Model.Figures;

import Model.Block;

import java.util.ArrayList;


/**
 * Создает фигуру T
 */
public class Fig_T extends Figure {

    private int phase = -1;
    private int phaseX = 1;
    private int phaseY = 1;
    private int l = 1;

    public Fig_T(int color) {
        super(5, 0, 5, 1, 4, 1, 5, 2, color);
    }


    @Override
    public void rotate() {
        phase = phase * (-1);
        ArrayList<Block> blocks = getBlocks();
        refreshFigureIfItPossible(blocks.get(0).getPosX() + phaseX,
                blocks.get(0).getPosY() + phaseY,
                blocks.get(1).getPosX(), blocks.get(1).getPosY(),
                blocks.get(2).getPosX() + phaseX * phase, blocks.get(2).getPosY() - phaseY * phase,
                blocks.get(3).getPosX() - phaseX,
                blocks.get(3).getPosY() - phaseY);
        switch (l) {
            case 0:
                phaseX = 1;
                phaseY = 1;
                break;
            case 1:
                phaseX = -1;
                phaseY = 1;
                break;
            case 2:
                phaseX = -1;
                phaseY = -1;
                break;
            case 3:
                phaseX = 1;
                phaseY = -1;
                break;
        }

        l = (l + 1) % 4;
    }

}
