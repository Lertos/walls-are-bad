package com.lertos.wallsarebad;

import javafx.scene.canvas.GraphicsContext;

public class ScoreOverlay {

    private final double xPos;
    private final double yPos;
    private int currentScore;
    private final double lineWidth;
    private final Difficulty difficulty;

    public ScoreOverlay(double lineWidth, Difficulty difficulty) {
        this.xPos = 15;
        this.yPos = 30;
        this.currentScore = 0;

        this.lineWidth = lineWidth;
        this.difficulty = difficulty;
    }

    public void increaseScore() {
        double score = (100 - lineWidth) * 7;

        switch (difficulty) {
            case EASY -> score *= 1.25;
            case MEDIUM -> score *= 1.55;
            case HARD -> score *= 1.75;
        }

        currentScore += (int) Math.round(score);
    }

    public void draw(GraphicsContext gc) {
        gc.fillText("SCORE: " + currentScore, xPos, yPos);
    }

}
