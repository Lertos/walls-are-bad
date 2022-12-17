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
    private Direction direction;
    private int speed;
    private final int size = 10;

    public Player(double startX, double startY, int speed) {
        this.startX = startX - (size / 2);
        this.startY = startY - (size / 2);
        this.direction = Direction.UP;
        this.speed = speed;
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


    public void draw(GraphicsContext gc) {
        gc.fillRect(startX, startY, size, size);
    }
}
