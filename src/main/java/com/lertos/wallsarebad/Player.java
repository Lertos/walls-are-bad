package com.lertos.wallsarebad;

enum Direction {
    LEFT,
    RIGHT,
    UP
}

public class Player {

    private Direction direction;
    private int speed;

    public Player(int speed) {
        this.direction = Direction.UP;
        this.speed = speed;
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }
}
