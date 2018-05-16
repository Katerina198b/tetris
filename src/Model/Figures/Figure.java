package Model.Figures;

import Model.Block;

import java.util.ArrayList;

/**
 * Создает фигуру по ее координатам
 * Заполняет клетки цветом и определяет для них координаты
 */
public class Figure {

    private ArrayList<Block> blocks;

    public Figure() {
    }

    public Figure(int oneX, int oneY, int twoX, int twoY, int threeX,
                  int threeY, int fourX, int fourY, int color) {
        blocks = new ArrayList<>();
        blocks.add(0, new Block(oneX, oneY, color));
        blocks.add(1, new Block(twoX, twoY, color));
        blocks.add(2, new Block(threeX, threeY, color));
        blocks.add(3, new Block(fourX, fourY, color));
    }

    public int getBlockX(int i) {
        return blocks.get(i).getPosX();
    }

    public int getBlockY(int i) {
        return blocks.get(i).getPosY();
    }

    public void setBlockX(int x, int i) {
        blocks.get(i).setPosX(x);
    }

    public void setBlockY(int y, int i) {
        blocks.get(i).setPosY(y);
    }

    public Block getBlock(int i) {
        return this.blocks.get(i);
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }


    void refreshFigureIfItPossible(int oneX, int oneY, int twoX, int twoY, int threeX, int threeY, int fourX, int fourY) {
        if ((oneX < 10) && (twoX < 10) && (threeX < 10) && (fourX < 10) &&
                (oneX >= 0) && (twoX >= 0) && (threeX >= 0) && (fourX >= 0) &&
                (oneY < 20) && (twoY < 20) && (threeY < 20) && (fourY < 20) &&
                (oneY >= 0) && (twoY >= 0) && (threeY >= 0) && (fourY >= 0)) {
            blocks.get(0).setXY(oneX, oneY);
            blocks.get(1).setXY(twoX, twoY);
            blocks.get(2).setXY(threeX, threeY);
            blocks.get(3).setXY(fourX, fourY);

        }
    }

    public void rotate() {
    }

}
