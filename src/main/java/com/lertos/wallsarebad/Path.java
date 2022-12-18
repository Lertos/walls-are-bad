package com.lertos.wallsarebad;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Path {

    private final List<Line> pathOfLines;
    private double lineWidth = 30;
    private final int minLineTiles = 2;
    private final int maxLineTiles = 6;
    private final double canvasWidth;
    private final double canvasHeight;
    private Random rng;

    public Path(Canvas canvas, int numOfInitialLines) {
        this.pathOfLines = new LinkedList<>();
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();

        this.rng = new Random();

        generateInitialLines(numOfInitialLines);
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public Line getLine(int index) {
        if (index > pathOfLines.size())
            return null;
        return pathOfLines.get(index);
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

        pathOfLines.add(new Line(Direction.UP, middleX, 0, middleX, canvasHeight));
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
        Pair<Double, Double> startPosition = getPrevEndPoint(prevLine, prevDir);

        double startX = startPosition.getKey(), startY = startPosition.getValue();
        double endX = startX, endY = startY;

        int lineLength = rng.nextInt(minLineTiles, maxLineTiles);

        //Get the end points based on the line length and direction
        if (newDir.equals(Direction.LEFT))
            endX -= lineLength * lineWidth;
        else if (newDir.equals(Direction.UP))
            endY -= lineLength * lineWidth;
        else if (newDir.equals(Direction.RIGHT))
            endX += lineLength * lineWidth;

        Line newLine = new Line(newDir, Math.min(startX, endX), Math.min(startY, endY), Math.max(startX, endX), Math.max(startY, endY));
        pathOfLines.add(newLine);
    }

    private Pair<Double, Double> getPrevEndPoint(Line prevLine, Direction prevDir) {
        double x = prevLine.getMinX();
        double y = prevLine.getMinY();

        //Only time when you wouldn't get the min is when you go right as X increases at the end
        if (prevDir.equals(Direction.RIGHT))
            x = prevLine.getMaxX();

        return new Pair<>(x, y);
    }

    public void changePlayerLines() {
        int index = pathOfLines.indexOf(Main.player.getNextLine());

        if (index == -1 || index + 1 >= pathOfLines.size())
            return;

        Main.player.setCurrentLine(pathOfLines.get(index));
        Main.player.setNextLine(pathOfLines.get(index + 1));
        Main.player.updateCurrentCorner();
    }

    public void moveObjects(Direction direction, int speed) {
        for (Line line : pathOfLines) {
            switch (direction) {
                case UP -> line.moveLine(0, speed);
                case LEFT -> line.moveLine(speed, 0);
                case RIGHT -> line.moveLine(-speed, 0);
            }
        }
        Main.player.moveCorner(direction, speed);
    }
}
