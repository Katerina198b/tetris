package Model;

/**
 * Описывает одну клетку фигуры
 * Имеет свой цвет и позицию (x,y)
 */
public class Block {
    private int posX;
    private int posY;
    private int color;

    public Block(int x, int y, int c) {
        posX = x;
        posY = y;
        color = c;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int x) {
        posX = x;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int y) {
        posY = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int c) {
        this.color = c;
    }

    public void setXY(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

}
