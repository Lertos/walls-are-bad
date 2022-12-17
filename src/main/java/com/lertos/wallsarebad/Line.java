package com.lertos.wallsarebad;

import javafx.scene.canvas.GraphicsContext;

public class Line {

    private final double startX;
    private final double startY;
    private final double endX;
    private final double endY;

    public Line(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void draw(GraphicsContext gc) {
        gc.strokeLine(this.startX, this.startY, this.endX, this.endY);
    }
}
