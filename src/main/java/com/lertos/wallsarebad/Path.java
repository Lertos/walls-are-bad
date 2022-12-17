package com.lertos.wallsarebad;

import java.util.ArrayList;
import java.util.List;

public class Path {

    List<Line> pathOfLines;

    public Path() {
        this.pathOfLines = new ArrayList<>();
    }

    //Takes the current end point and adds a line to it
    public void addLine() {

    }

    public void moveLines(Direction direction, int speed) {
        for (Line line : pathOfLines) {
            //Move the line based on the current player direction
        }
    }
}
