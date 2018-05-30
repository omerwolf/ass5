package highScore;

import java.util.Comparator;

/**
 * ScoreInfoComparator class.
 */
public class ScoreInfoComparator implements Comparator {
    public int compare(Object one, Object two) {
        if (one instanceof ScoreInfo && two instanceof ScoreInfo) {
            ScoreInfo first = (ScoreInfo) one;
            ScoreInfo second = (ScoreInfo) two;
            return second.getScore() - first.getScore();
        }
        int inf = Integer.MAX_VALUE;
        return inf;
    }
}