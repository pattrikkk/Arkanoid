package com.example.arkanoid;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Group {
    private double WIDTH, HEIGHT;
    private double vausW = 75, vausH = 30;
    private double ballSize = 16;
    private double tehlaW = 53, tehlaH = 25;
    private double rows = 4, cols = 9;
    private int score = 0;
    private String[] farby_tehal = {"modra", "zelena"};


    ImageView background;
    Hrac vaus;
    Lopta ball;
    Timeline t;
    ArrayList<KeyCode> input;
    ArrayList<Tehla> tehly;

    Text scoreLabel;
    Text hpLabel;

    public Game(double w, double h) {
        WIDTH = w;
        HEIGHT = h;

        Image bg = new Image("background.png", WIDTH, HEIGHT, false, false);

        background = new ImageView(bg);
        vaus = new Hrac(WIDTH, HEIGHT, vausW, vausH);
        ball = new Lopta(WIDTH, HEIGHT, ballSize);

        getChildren().addAll(background, ball, vaus);

        t = new Timeline(new KeyFrame(Duration.millis(16), e -> update()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        spawnTehly();

        VBox vb = new VBox();
        scoreLabel = new Text("Score: " + score);
        hpLabel = new Text("HP: " + ball.getHp());

        scoreLabel.setStyle("-fx-fill: white; -fx-font-size: 20px");
        hpLabel.setStyle("-fx-fill: white; -fx-font-size: 20px");

        vb.setLayoutX(15);
        vb.setLayoutY(15);

        vb.getChildren().addAll(scoreLabel, hpLabel);
        getChildren().add(vb);

        input = new ArrayList<>();
        setOnKeyPressed(e -> {
            if (input.contains(e.getCode())) return;
            input.add(e.getCode());
        });
        setOnKeyReleased(e -> input.remove(e.getCode()));
        requestFocus();
        setFocusTraversable(true);
    }

    public void spawnTehly() {
        tehly = new ArrayList<>();
        for (int j = 0; j < rows; j++) {
            String farba = farby_tehal[j % 2];
            for (int i = 0; i < cols; i++) {
                Tehla t = new Tehla(WIDTH, HEIGHT, 15 + i * tehlaW, 75 + j * tehlaH, tehlaW, tehlaH, farba);
                tehly.add(t);
                getChildren().add(t);
            }
        }
    }

    public void update() {
        if (!input.isEmpty()) {
            if (input.get(input.size() - 1) == KeyCode.LEFT)
                vaus.posunVlavo();
            if (input.get(input.size() - 1) == KeyCode.RIGHT)
                vaus.posunVpravo();
            if (input.get(input.size() - 1) == KeyCode.ESCAPE)
                System.exit(0);
        }

        ball.move();

        if (ball.getLayoutX() <= 15 || ball.getLayoutX() + ball.getFitWidth() >= WIDTH - 15){
            ball.setSmerX(-ball.getSmerX());
        }

        if (ball.getLayoutY() <= 15) {
            ball.setSmerY(-ball.getSmerY());
        }

        if (ball.getLayoutY() + ball.getFitHeight() >= HEIGHT) {
            ball.setSmerY(-ball.getSmerY());
            ball.setHP(ball.getHp() - 1);
            hpLabel.setText("HP: " + ball.getHp());
            if (ball.getHp() <= 0) {
                gameOver();
            }
        }

        for (Tehla t : tehly) {
            if (ball.getBoundsInParent().intersects(t.getBoundsInParent())) {
                if (t.getLayoutY() <= ball.getLayoutY()) {
                    ball.setSmerY(ball.getSpeed());
                }
                if (t.getLayoutY() > ball.getLayoutY()) {
                    ball.setSmerY(-ball.getSpeed());
                }

                score++;
                scoreLabel.setText("Score: " + score);

                getChildren().remove(t);
                tehly.remove(t);
                if (tehly.isEmpty()) {
                    spawnTehly();
                }
                return;
            }
        }
        if (ball.getBoundsInParent().intersects(vaus.getBoundsInParent())) {
            ball.setSmerY(-ball.getSpeed());
        }

    }

    public void gameOver() {
        getChildren().clear();
        getChildren().addAll(background, new ImageView(new Image("gameover.png", WIDTH, HEIGHT, false, false)));
    }

}
