package com.lertos.wallsarebad;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TitleScreen {

    private int minPathSize = 10;
    private int maxPathSize = 60;
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
        container.setSpacing(20);

        Label titleLabel = new Label("DON'T FALL");

        //The size comparison of the path and the player controls
        StackPane difficultyPane = new StackPane();

        Rectangle pathWidth = new Rectangle(60, 60);
        pathWidth.setStyle("-fx-fill: " + Main.fgColor + ";");
        Rectangle playerSize = new Rectangle(20, 20);
        playerSize.setStyle("-fx-fill: " + Main.otherColor + ";");

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
        container.setStyle("-fx-background-color: " + Main.bgColor + ";");

        titleLabel.setStyle("-fx-font: normal bold 36px 'serif'; -fx-text-fill: " + Main.textColor + "; -fx-label-padding: 20;");

        radioGroup.setSpacing(20);

        //Add the controls to the container
        container.getChildren().add(titleLabel);
        container.getChildren().add(new Label("DIFFICULTY"));
        container.getChildren().add(difficultyPane);
        container.getChildren().add(addDifficultySlider());
        container.getChildren().add(radioGroup);
        container.getChildren().add(addStartButton());
    }

    private Button addStartButton() {
        Button startButton = new Button("START");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //TODO: Set the game control values of the player, path, etc

                Main.currentState = GameState.GAME;
                hideTitleScreen();
            }
        });

        return startButton;
    }

    private Slider addDifficultySlider() {
        Slider slider = new Slider();

        slider.setMin(minPathSize);
        slider.setMax(maxPathSize);
        slider.setValue((maxPathSize - minPathSize) / 2);

        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount((maxPathSize - minPathSize));

        slider.setMaxWidth(Main.canvasWidth / 2);

        slider.setStyle("-fx-font: normal bold 16px 'serif'; -fx-text-fill: " + Main.textColor + "; -fx-label-padding: 20;");

        return slider;
    }

    public void showTitleScreen() {
        container.setVisible(true);
    }

    public void hideTitleScreen() {
        container.setVisible(false);
    }

}
