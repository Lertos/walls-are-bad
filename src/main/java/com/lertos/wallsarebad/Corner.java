package com.lertos.wallsarebad;

public class Corner {

    private double x;
    private double y;
    private final Direction current;
    private final Direction next;

    public Corner(Line current, Line next) {
        this.current = current.getDirection();
        this.next = next.getDirection();

        getPointsFromLines(current);
    }

    private void getPointsFromLines(Line currentLine) {
        double currX = currentLine.getMinX();
        double currY = currentLine.getMinY();

        if (current.equals(Direction.RIGHT))
            currX = currentLine.getMaxX();

        x = currX;
        y = currY;
    }

    //This checks to make sure if there is a collision on a line that it is the one between the two lines
    //true = it's the cross of both lines, false = it's not the cross and the collision was bad
    public boolean isCollisionTheCross(double playerX, double playerY) {
        double lineWidth = Main.path.getLineWidth();

        if (current.equals(Direction.LEFT) || current.equals(Direction.RIGHT)) {
            if (playerX >= x - lineWidth && playerX <= x + lineWidth && playerY <= y + lineWidth)
                return true;
        } else if (current.equals(Direction.UP)) {
            if (next.equals(Direction.LEFT)) {
                if (playerX <= x + lineWidth && playerY >= y - lineWidth && playerY <= y + lineWidth)
                    return true;
            } else if (next.equals(Direction.RIGHT)) {
                if (playerX >= x - lineWidth && playerY <= y + lineWidth && playerY >= y - lineWidth)
                    return true;
            }
        }
        return false;
    }

    public void setX(double x) {
        this.x += x;
    }

    public void setY(double y) {
        this.y += y;
    }
}
