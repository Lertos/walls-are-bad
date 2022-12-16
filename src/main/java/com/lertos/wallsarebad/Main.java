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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> draw(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        root.getChildren().add(canvas);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void draw(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        //Clear the canvas each frame with a background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        //Set the properties of the other objects in the canvas
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(5);

        //TODO: Make this into a class to easily check collision and coordinates
        //The player object - always in the middle
        double middleX = canvasWidth / 2;
        double middleY = canvasHeight / 2;

        gc.fillRect(middleX - 10, middleY - 10, 20, 20);

        //DEBUG: Testing a line
        gc.strokeLine(currentX, currentY, currentX, currentY + 60);

        //TODO: Move to another method that handles movement
        currentX ++;
    }
}