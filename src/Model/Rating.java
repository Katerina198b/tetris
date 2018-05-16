package Model;

import java.util.ArrayList;

/**
 * Модель хранит в себе список строк вида "login: score"
 */
public class Rating {
    private ArrayList<String> rating;

    public Rating(ArrayList<String> r) {
        rating = r;
    }

    public ArrayList<String> getRating() {
        return rating;
    }

}
