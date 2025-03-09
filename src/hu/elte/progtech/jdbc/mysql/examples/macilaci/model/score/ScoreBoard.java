package hu.elte.progtech.jdbc.mysql.examples.macilaci.model.score;

import java.util.List;

public class ScoreBoard {

    private int score;
    private int topScore;
    private List<String> top10scores;

    public void reset() {
        setTopScore();
        score = 0;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public void setTopScore() {
        if (score > topScore) {
            topScore = score;
        }
    }

    public int getTopScore() {
        return topScore;
    }

    public List<String> getTop10Scores() {
        return top10scores;
    }

    public void setTop10scores(List<String> top10scores) {
        this.top10scores = top10scores;
    }

}