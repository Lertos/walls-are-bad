package com.lertos.wallsarebad;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private double canvasWidth;
    private double canvasHeight;
    private boolean playerIsDead = false;

    static Player player;
    static Path path;

    public static void main(String[] args) { launch(); }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Set up the game loop
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> draw(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        //Setup initial map and player
        player = new Player(canvasWidth / 2, canvasHeight / 2, 2);
        path = new Path(canvas, 10);

        player.setCurrentLine(path.getLine(0));
        player.setNextLine(path.getLine(1));
        player.updateCurrentCorner();

        setupHandlers(stage);

        //Finish setting up the stage and then present it
        root.getChildren().add(canvas);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void setupHandlers(Stage stage) {
        EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.A)
                    player.changeDirection(Direction.LEFT);
                else if (event.getCode() == KeyCode.W)
                    player.changeDirection(Direction.UP);
                else if (event.getCode() == KeyCode.D)
                    player.changeDirection(Direction.RIGHT);

                event.consume();
            }
        };

        stage.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }

    private void draw(GraphicsContext gc) {
        //Clear the canvas each frame with a background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        //Set the properties of the other objects in the canvas
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(path.getLineWidth());

        if (!playerIsDead) {
            //Check for collisions
            if (player.collided()) {
                //Set the new current and next lines (as well as the corner)
                if (player.movedToNextLine())
                    path.changePlayerLines();
                else {
                    playerIsDead = true;
                    //TODO: Add a death animation
                    System.out.println("YOU DIED");
                    return;
                }
            }
        }
        //Draw the lines of the path
        path.draw(gc);

        //The player object - always in the middle
        player.draw(gc);

        //Move the lines
        if (!playerIsDead)
            path.moveObjects(player.getDirection(), player.getSpeed());
    }
}