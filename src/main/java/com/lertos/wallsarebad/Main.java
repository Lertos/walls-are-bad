package com.lertos.wallsarebad;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private double currentX = 0;
    private double currentY = 20;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Set up the game loop
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> draw(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void draw(GraphicsContext gc) {
        //Clear the canvas each frame with a background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        //Set the properties of the other objects in the canvas
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(5);

        //DEBUG: Testing a line
        gc.strokeLine(currentX, currentY, currentX, currentY + 60);

        //TODO: Move to another method that handles movement
        currentX ++;
    }
}