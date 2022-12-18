package com.lertos.wallsarebad;

public class Corner {

    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private final Direction current;
    private final Direction next;

    public Corner(double lineWidth, Line current, Line next) {
        getPointsFromLines(lineWidth, current, next);

        this.current = current.getDirection();
        this.next = next.getDirection();
    }

    private void getPointsFromLines(double lineWidth, Line current, Line next) {
        double x = current.getEndX();
        double y = current.getEndY();

        startX = x - lineWidth;
        startY = y - lineWidth;
        endX = x + lineWidth;
        endY = y + lineWidth;
    }

    //This checks to make sure if there is a collision on a line that it is the one between the two lines
    //true = it's the cross of both lines, false = it's not the cross and the collision was bad
    public boolean isCollisionTheCross(double x, double y) {
        if (current.equals(Direction.LEFT) || current.equals(Direction.RIGHT)) {
            if (x > startX && x < endX && y < endY)
                return true;
        } else if (current.equals(Direction.UP)) {
            if (next.equals(Direction.LEFT)) {
                if (x < endX && y < endY && y > startY)
                    return true;
            } else if (next.equals(Direction.RIGHT)) {
                if (x > startX && y < endY && y > startY)
                    return true;
            }
        }
        return false;
    }
}
