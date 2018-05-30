package highScore;

import java.io.Serializable;

public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    public ScoreInfo(String newName, int newScore) {
        this.name = newName;
        this.score = newScore;
    }

    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }
}
