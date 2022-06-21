package com.example.arkanoid;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Lopta extends ImageView {

    private double ballSize;
    private double WIDTH, HEIGHT;
    private double ballSpeed = 5;
    private int hp = 3;
    private double smerX = 0, smerY = -ballSpeed;
    Random rand;

    public Lopta(double w, double h, double ballSize) {
        super(new Image("lopta.png"));

        this.ballSize = ballSize;
        WIDTH = w;
        HEIGHT = h;

        this.setFitWidth(this.ballSize);
        this.setFitHeight(this.ballSize);

        this.setLayoutX(WIDTH/2 - this.ballSize/2);
        this.setLayoutY(HEIGHT - this.ballSize - 50);

    }

    public void setSmerX(double smerX) {
        if (this.smerX == 0) {
            this.smerX = -ballSpeed;
            return;
        }
        this.smerX = smerX;
    }

    public void setSmerY(double smerY) {
        if (this.smerX == 0) {
            this.smerX = -ballSpeed;
        }
        this.smerY = smerY;
    }

    public void move() {
        this.setLayoutX(this.getLayoutX() + smerX);
        this.setLayoutY(this.getLayoutY() + smerY);
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public double getSmerX() {
        return smerX;
    }

    public double getSpeed() {
        return ballSpeed;
    }

    public double getSmerY() {
        return smerY;
    }
}
