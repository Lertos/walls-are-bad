package com.lertos.wallsarebad;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EndScreen {

    private final double width;
    private final double height;
    private int score;

    private VBox container;

    public EndScreen(double width, double height) {
        this.width = width;
        this.height = height;
        this.score = 0;

        container = new VBox();

        hide();
        setupScreen();
    }

    public VBox getContainer() {
        return container;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void setupScreen() {
        container.setPrefSize(width, height);
        container.setFillWidth(true);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);

        Button restartButton = addRestartButton();

        Label scoreLabel = new Label("FINAL SCORE: " + score);

        //Set the styling of the controls
        container.setStyle("-fx-background-color: " + Main.bgColor + ";");

        restartButton.setStyle("-fx-font: normal bold 24px 'serif'; -fx-text-fill: " + Main.textColor + ";-fx-background-color: " + Main.otherColor + ";");
        scoreLabel.setStyle("-fx-border-width: 6; -fx-border-color: " + Main.otherColor + ";-fx-font: normal bold 40px 'serif'; -fx-text-fill: " + Main.textColor + "; -fx-label-padding: 20;");

        //Add the controls to the container
        container.getChildren().add(scoreLabel);
        container.getChildren().add(restartButton);
    }

    private Button addRestartButton() {
        Button restartButton = new Button("Play Again");

        restartButton.setOnAction(e -> {
            Main.currentState = GameState.TITLE_SCREEN;

            hide();

            Main.titleScreen.show();
        });

        return restartButton;
    }

    public void show() {
        container.setVisible(true);
    }

    public void hide() {
        container.setVisible(false);
    }
}