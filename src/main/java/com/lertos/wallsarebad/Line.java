package com.lertos.wallsarebad;

import javafx.scene.canvas.GraphicsContext;

public class Line {

    private final Direction direction;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public Line(Direction direction, double startX, double startY, double endX, double endY) {
        this.direction = direction;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void draw(GraphicsContext gc) {
        gc.strokeLine(this.startX, this.startY, this.endX, this.endY);
    }

    public void moveLine(double moveX, double moveY) {
        if (moveX != 0) {
            startX += moveX;
            endX += moveX;
        }
        else if (moveY != 0) {
            startY += moveY;
            endY += moveY;
        }
    }
}
