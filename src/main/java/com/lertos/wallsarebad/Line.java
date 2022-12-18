package com.lertos.wallsarebad;

import javafx.scene.canvas.GraphicsContext;

public class Line {

    private final Direction direction;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    public Line(Direction direction, double minX, double minY, double maxX, double maxY) {
        this.direction = direction;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void draw(GraphicsContext gc) {
        gc.strokeLine(this.minX, this.minY, this.maxX, this.maxY);
    }

    public void moveLine(double moveX, double moveY) {
        if (moveX != 0) {
            minX += moveX;
            maxX += moveX;
        }
        else if (moveY != 0) {
            minY += moveY;
            maxY += moveY;
        }
    }

    public boolean isOutsideBounds(double x, double y) {
        //Offset is taking half of the line width and half of the player size into account as positions are centered
        double offset = (Main.path.getLineWidth() + Main.player.getSize()) / 2;

        return (x < minX - offset || x > maxX + offset || y < minY - offset || y > maxY + offset);
    }

    public Direction getDirection() {
        return direction;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }
}
