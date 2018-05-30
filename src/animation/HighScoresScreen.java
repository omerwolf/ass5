package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import highScore.HighScoresTable;
import highScore.ScoreInfo;
import indicators.Counter;

import java.awt.Color;

public class HighScoresScreen implements Animation{
    private HighScoresTable scoresTable;
    /**
     * @param highScoreTable - score table
     */
    public HighScoresScreen(HighScoresTable highScoreTable) {

        this.scoresTable = highScoreTable;
    }
    /**
     * Doing frame of HighScores.
     * Note: showing the top 10 all time score list.
     *
     * @param d  is the surface.
     * @param dt is the frames per second.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        // Setting the table.
        d.setColor(Color.YELLOW);
        d.drawText(250, 60, "High Scores", 32);
        d.setColor(Color.GREEN);
        d.drawText(100, 120, "Player Name", 24);
        d.setColor(Color.GREEN);
        d.drawText(450, 120, "Score", 24);
        d.setColor(Color.GREEN);
        d.drawLine(100, 131, 700, 131);
        d.setColor(Color.BLACK);
        for (int i = 0; i < this.scoresTable.getHighScores().size(); i++) {
            ScoreInfo scoreInfo = this.scoresTable.getHighScores().get(i);
            d.setColor(Color.black);
            d.drawText(100, 30 * i + 160, (i + 1) + ") " + scoreInfo.getName(), 28);
            d.setColor(Color.black);
            d.drawText(450, 30 * i + 160, String.valueOf(scoreInfo.getScore()), 24);
        }
        d.setColor(Color.BLACK);
        d.drawText(200, 500, "Press space to continue", 32);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
