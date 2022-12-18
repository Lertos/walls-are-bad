package com.lertos.wallsarebad;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Path {

    private final List<Line> pathOfLines;
    private double lineWidth = 20;
    private final int minLineTiles = 4;
    private final int maxLineTiles = 12;
    private final double canvasWidth;
    private final double canvasHeight;
    private Random rng;

    public Path(Canvas canvas, int numOfInitialLines) {
        this.pathOfLines = new ArrayList<>();
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();

        this.rng = new Random();

        generateInitialLines(numOfInitialLines);
    }

    public double getLineWidth() {
        return lineWidth;
    }

    private void generateInitialLines(int numOfInitialLines) {
        //Add the initial line which is always in the same place
        addInitialLine();

        for (int i=0; i<numOfInitialLines; i++)
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
        Line prevLine = pathOfLines.get(pathOfLines.size()-1);
        Direction prevDir = prevLine.getDirection();

        //Get the new direction
        Direction newDir = Direction.UP;

        if (prevDir.equals(Direction.UP)) {
            int num = rng.nextInt(0,2);

            if (num == 0)
                newDir = Direction.LEFT;
            else
                newDir = Direction.RIGHT;
        }

        //Get the line length as well as start and end points
        int lineLength = rng.nextInt(minLineTiles,maxLineTiles);
        double startX = prevLine.getEndX();
        double startY = prevLine.getEndY();
        double endX = startX, endY = startY;

        if (newDir.equals(Direction.LEFT)) {
            endX -= lineLength * lineWidth;
        } else if (newDir.equals(Direction.UP)) {
            endY -= lineLength * lineWidth;
        } else if (newDir.equals(Direction.RIGHT)) {
            endX += lineLength * lineWidth;
        }

        Line newLine = new Line(newDir, startX, startY, endX, endY);
        pathOfLines.add(newLine);
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
