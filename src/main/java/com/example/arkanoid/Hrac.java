package com.example.arkanoid;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hrac extends ImageView {
    private double WIDTH, HEIGHT;

    public Hrac(double w, double h, double vausW, double vausH) {
        super(new Image("hrac.png"));

        WIDTH = w;
        HEIGHT = h;

        setFitWidth(vausW);
        setFitHeight(vausH);
        setLayoutX(WIDTH / 2 - getFitWidth() / 2);
        setLayoutY(HEIGHT - getFitHeight() - 10);
    }

    public void posunVpravo() {
        if (jeVpravo()) return;
        setLayoutX(getLayoutX() + 5);
    }

    public void posunVlavo() {
        if (jeVlavo()) return;
        setLayoutX(getLayoutX() - 5);
    }

    public Boolean jeVlavo() {
        return getLayoutX() <= 15;
    }

    public Boolean jeVpravo() {
        return getLayoutX() + getFitWidth() >= WIDTH - 15;
    }

    public Boolean detekciaKolizie(Lopta ball) {
        return ball.getLayoutX() + ball.getFitWidth() >= getLayoutX() &&
                ball.getLayoutX() <= getLayoutX() + getFitWidth() &&
                ball.getLayoutY() + ball.getFitHeight() >= getLayoutY() &&
                ball.getLayoutY() <= getLayoutY() + getFitHeight();
    }


}
