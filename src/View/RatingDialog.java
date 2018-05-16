package View;

import java.awt.Dialog;

/**
 * Создает подальное окно под названием Rating
 */
class RatingDialog extends Dialog {

    RatingDialog(Window window) {
        super(window, "Rating", true);
        setBounds(200, 150, 200, 400);
    }

}
