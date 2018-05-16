package Model;

/**
 * Хранит очки пользователя
 * Если пользоваптель разом уничтожил несколько линий, то за каждую новую линию очки умножаются на номер линии
 */
public class Score {

    private int score = 0;

    void addPoints(int k) {
        for (int i = 1; i <= k; i++) {
            score += i * 10;
        }
    }

    public int getScore() {
        return score;
    }
}
