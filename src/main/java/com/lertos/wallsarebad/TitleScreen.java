package com.lertos.wallsarebad;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TitleScreen {

    private double width;
    private double height;

    private VBox container;

    public TitleScreen(double width, double height) {
        this.width = width;
        this.height = height;

        container = new VBox();

        setupScreen();
    }

    public VBox getContainer() {
        return container;
    }

    private void setupScreen() {
        container.setPrefSize(width,height);
        container.setFillWidth(true);
        container.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("DON'T FALL");

        Slider slider = new Slider();

        //The size comparison of the path and the player controls
        StackPane difficultyPane = new StackPane();

        Rectangle pathWidth = new Rectangle(60, 60);
        pathWidth.setStyle("-fx-fill: brown;");
        Rectangle playerSize = new Rectangle(20, 20);
        playerSize.setStyle("-fx-fill: white;");

        difficultyPane.getChildren().addAll(pathWidth, playerSize);

        //The radio button grouping for the path length difficulty setting
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton easyButton = new RadioButton("Easy");
        RadioButton mediumButton = new RadioButton("Medium");
        RadioButton hardButton = new RadioButton("Hard");

        easyButton.setToggleGroup(toggleGroup);
        mediumButton.setToggleGroup(toggleGroup);
        hardButton.setToggleGroup(toggleGroup);

        HBox radioGroup = new HBox(easyButton, mediumButton, hardButton);

        radioGroup.setAlignment(Pos.CENTER);

        //Set the styling of the controls
        titleLabel.setStyle("-fx-font: normal bold 36px 'serif';-fx-text-fill: white; -fx-label-padding: 20;");

        //Add the controls to the container
        container.getChildren().add(titleLabel);
        container.getChildren().add(new Label("DIFFICULTY"));
        container.getChildren().add(difficultyPane);
        container.getChildren().add(slider);
        container.getChildren().add(radioGroup);
        container.getChildren().add(addStartButton());
    }

    private Button addStartButton() {
        Button startButton = new Button("START");

        return startButton;
    }

}
