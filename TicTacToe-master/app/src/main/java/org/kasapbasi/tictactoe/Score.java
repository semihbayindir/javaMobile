package org.kasapbasi.tictactoe;

public class Score {
    private int scoreX;
    private int scoreY;
    private int drawScore;

    public Score(int scoreX, int scoreY, int drawScore) {
        this.scoreX = scoreX;
        this.scoreY = scoreY;
        this.drawScore = drawScore;
    }
    public int getScoreX() {
        return scoreX;
    }
    public void setScoreX(int scoreX) {
        this.scoreX = scoreX;
    }
    public int getScoreY() {
        return scoreY;
    }
    public void setScoreY(int scoreY) {
        this.scoreY = scoreY;
    }
    public int getDrawScore() {
        return drawScore;
    }
    public void setDrawScore(int drawScore) {
        this.drawScore = drawScore;
    }
}
