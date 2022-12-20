package com.lertos.wallsarebad;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TitleScreen {

    private VBox container;

    public TitleScreen() {
        container = new VBox();

        setupScreen();
    }

    public VBox getContainer() {
        return container;
    }

    private void setupScreen() {
        container.setPrefSize(600,600);
        container.setFillWidth(true);
        container.setAlignment(Pos.CENTER);

        container.getChildren().add(addGameTitle());
        container.getChildren().add(addStartButton());
        container.getChildren().add(addHighscoreLabel());
        container.getChildren().add(new Label("DIFFICULTY"));
    }

    private Button addStartButton() {
        Button startButton = new Button("START");

        return startButton;
    }

    private Label addGameTitle() {
        Label titleLabel = new Label("Don't Fall");

        return titleLabel;
    }

    private Label addHighscoreLabel() {
        Label highscoreLabel = new Label("Highscore: " + 100);

        highscoreLabel.setStyle("-fx-font: normal bold 20px 'serif';-fx-text-fill: white; ");

        return highscoreLabel;
    }


}
