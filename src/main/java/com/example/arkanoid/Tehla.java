package com.example.arkanoid;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tehla extends ImageView {
    private double WIDTH, HEIGHT;

    public Tehla(double w, double h, double x, double y, double tehlaW, double tehlaH, String farba) {
        super(new Image("tehla_" + farba + ".png"));

        WIDTH = w;
        HEIGHT = h;

        setFitWidth(tehlaW);
        setFitHeight(tehlaH);
        setLayoutX(x);
        setLayoutY(y);
    }

    public Boolean detekciaKolizie(Lopta ball) {
        return ball.getLayoutX() + ball.getFitWidth() >= getLayoutX() &&
                ball.getLayoutX() <= getLayoutX() + getFitWidth() &&
                ball.getLayoutY() + ball.getFitHeight() >= getLayoutY() &&
                ball.getLayoutY() <= getLayoutY() + getFitHeight();
    }
}
