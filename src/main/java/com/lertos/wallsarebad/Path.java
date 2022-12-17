package com.lertos.wallsarebad;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Line> pathOfLines;
    private double lineWidth = 20;
    private final double canvasWidth;
    private final double canvasHeight;

    public Path(Canvas canvas, int numOfInitialLines) {
        this.pathOfLines = new ArrayList<>();
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();

        generateInitialLines(numOfInitialLines);
    }

    public double getLineWidth() {
        return lineWidth;
    }

    private void generateInitialLines(int numOfInitialLines) {
        //Add the initial line which is always in the same place
        addInitialLine();

        /*
        for (int i=0; i<numOfInitialLines; i++) {

        }
        */
        addLine();
    }

    public void draw(GraphicsContext gc) {
        for (Line line : pathOfLines)
            line.draw(gc);
    }

    private void addInitialLine() {
        double middleX = canvasWidth / 2;

        pathOfLines.add(new Line(Direction.UP, middleX, canvasHeight, middleX, 0));
    }

    //Takes the current end point and adds a line to it
    public void addLine() {

    }

    public void moveLines(Direction direction, int speed) {
        for (Line line : pathOfLines) {
            switch (direction) {
                case UP -> line.moveLine(0, speed);
                case LEFT -> line.moveLine(speed, 0);
                case RIGHT -> line.moveLine(-speed, 0);
            }
        }
    }
}
