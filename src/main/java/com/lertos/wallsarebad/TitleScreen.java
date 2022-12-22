package com.lertos.wallsarebad;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TitleScreen {

    private int minPathSize = 10;
    private int maxPathSize = 60;
    private double width;
    private double height;

    private VBox container;
    private Rectangle pathWidth;

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
        container.setSpacing(20);

        Label titleLabel = new Label("STAY ON THE PATH");
        Label difficultyLabel = new Label("Difficulty (Path Size)");

        //The size comparison of the path and the player controls
        StackPane difficultyPane = new StackPane();

        pathWidth = new Rectangle((maxPathSize - minPathSize) / 2, (maxPathSize - minPathSize) / 2);
        pathWidth.setStyle("-fx-fill: " + Main.otherColor + ";");

        Rectangle playerSize = new Rectangle(Main.player.getSize(), Main.player.getSize());
        playerSize.setStyle("-fx-fill: " + Main.fgColor + ";");

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
        radioGroup.setSpacing(20);

        //Set the styling of the controls
        container.setStyle("-fx-background-color: " + Main.bgColor + ";");

        titleLabel.setStyle("-fx-font: normal bold 40px 'serif'; -fx-text-fill: " + Main.textColor + "; -fx-label-padding: 20;");
        difficultyLabel.setStyle("-fx-font: normal bold 20px 'serif'; -fx-text-fill: " + Main.textColor + ";");

        //Add the controls to the container
        container.getChildren().add(titleLabel);
        container.getChildren().add(difficultyLabel);
        container.getChildren().add(difficultyPane);
        container.getChildren().add(addDifficultySlider());
        container.getChildren().add(radioGroup);
        container.getChildren().add(addStartButton());
    }

    private Button addStartButton() {
        Button startButton = new Button("START");

        startButton.setOnAction(e -> {
            //TODO: Set the game control values of the player, path, etc

            Main.currentState = GameState.GAME;
            hideTitleScreen();
        });

        return startButton;
    }

    private Slider addDifficultySlider() {
        Slider slider = new Slider();

        slider.setMin(minPathSize);
        slider.setMax(maxPathSize);
        slider.setValue((maxPathSize - minPathSize) / 2);

        slider.setSnapToTicks(true);
        slider.setMinorTickCount((maxPathSize - minPathSize));
        slider.setMaxWidth(Main.canvasWidth / 2);

        slider.valueProperty().addListener((ov, oldVal, newVal) -> {
            pathWidth.setWidth(newVal.doubleValue());
            pathWidth.setHeight(newVal.doubleValue());
        });

        return slider;
    }

    public void showTitleScreen() {
        container.setVisible(true);
    }

    public void hideTitleScreen() {
        container.setVisible(false);
    }

}
