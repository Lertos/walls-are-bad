package com.lertos.wallsarebad;

public class ScoreOverlay {

    private final int fontSize;
    private final double xPos;
    private final double yPos;
    private int currentScore;
    private final double lineWidth;
    private final Difficulty difficulty;

    public ScoreOverlay(double lineWidth, Difficulty difficulty) {
        this.fontSize = 22;
        this.xPos = 30;
        this.yPos = 30;
        this.currentScore = 0;

        this.lineWidth = lineWidth;
        this.difficulty = difficulty;
    }

}
