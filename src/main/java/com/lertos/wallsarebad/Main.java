package com.lertos.wallsarebad;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private TitleScreen titleScreen;
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

        //Set up the handlers, title screen, and game over screen
        setupHandlers(stage);
        titleScreen = new TitleScreen();


        //Finish setting up the stage and then present it
        root.getChildren().add(canvas);
        root.getChildren().add(titleScreen.getContainer());

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

                titleScreen.getContainer().setVisible(false);
                event.consume();
            }
        };

        stage.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }

    private void draw(GraphicsContext gc) {
        //Clear the canvas each frame with a background
        gc.setFill(Color.web("#1A120B"));
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        //Set the properties of the other objects in the canvas
        gc.setFill(Color.web("D5CEA3"));
        gc.setStroke(Color.web("3C2A21"));
        gc.setLineWidth(path.getLineWidth());

        //TODO: Text color should be "E5E5CB"

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