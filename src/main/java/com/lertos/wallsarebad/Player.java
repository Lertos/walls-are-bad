package com.lertos.wallsarebad;

import javafx.scene.canvas.GraphicsContext;

enum Direction {
    LEFT,
    RIGHT,
    UP
}

public class Player {

    private final double startX;
    private final double startY;
    private double currentX;
    private double currentY;
    private Direction direction;
    private Line currentLine;
    private Line nextLine;
    private Corner currentCorner;
    private final int speed = 2;
    private final double startingSize = 10;
    private double size;

    public Player(double startX, double startY) {
        this.size = this.startingSize;
        this.startX = startX - (size / 2);
        this.startY = startY - (size / 2);
        this.currentX = startX;
        this.currentY = startY;
        this.direction = Direction.UP;
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public double getSize() { return size; }

    public void setSize(double size) {
        if (this.size >= -0.05 && this.size <= 0.05 )
            this.size = 0.0;
        else
            this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.fillRect(startX, startY, size, size);
    }

    public Line getNextLine() {
        return nextLine;
    }

    public void setCurrentLine(Line currentLine) {
        this.currentLine = currentLine;
    }

    public void setNextLine(Line nextLine) {
        this.nextLine = nextLine;
    }

    public void updateCurrentCorner() {
        this.currentCorner = new Corner(currentLine, nextLine);
    }

    public void startNewGame() {
        this.size = this.startingSize;

        setCurrentLine(Main.path.getLine(0));
        setNextLine(Main.path.getLine(1));
        updateCurrentCorner();
    }

    public void moveCorner(Direction direction, int speed) {
        switch (direction) {
            case UP -> currentCorner.setY(speed);
            case LEFT -> currentCorner.setX(speed);
            case RIGHT -> currentCorner.setX(-speed);
        }
    }

    public boolean collided() {
        return currentLine.isOutsideBounds(currentX, currentY);
    }

    public boolean movedToNextLine() {
        return currentCorner.isCollisionTheCross(currentX, currentY);
    }
}
