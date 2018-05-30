package highScore;

import java.io.File;
import java.util.List;

public class packageTest {
    /**
     *
     * @param args arguments.
     */
    public static void main(String[] args) {

        HighScoresTable table = new HighScoresTable(3);
        /*
        ScoreInfo scoreInfo1 = new ScoreInfo("gal", 2);
        ScoreInfo scoreInfo2 = new ScoreInfo("yoav", 4);
        ScoreInfo scoreInfo3 = new ScoreInfo("thor", 5);
        ScoreInfo scoreInfo4 = new ScoreInfo("batman", 1);
        ScoreInfo scoreInfo5 = new ScoreInfo("spiderman", 3);
        table.add(scoreInfo1);
        table.add(scoreInfo2);
        table.add(scoreInfo3);
        table.add(scoreInfo4);
        table.add(scoreInfo5);
        HighScoresTable s = table;

        /*
        for (int i = 0; i < table.size(); i++) {
            System.out.print(s.getHighScores().get(i).getName() + " holds the score of: ");
            System.out.println(s.getHighScores().get(i).getScore());
        }
         */
        //System.out.println(table.getRank(5));
        File f = new File("scores.txt");
        /*
        try {
            table.save(f);
        } catch (Exception e) {
        }
*/
        //HighScoresTable s = null;
        List l = null;
        try {
            table.load(f);
            //s = table.getHighScores();
            //s = HighScoresTable.loadFromFile(f);
            l = table.getHighScores();
        } catch (Exception e) {

        }
        System.out.print("Top scores:\n");
        for (int i = 0; i < table.size(); i++) {
            System.out.print(((ScoreInfo) l.get(i)).getName() + " holds the score of: ");
            System.out.println(((ScoreInfo) l.get(i)).getScore());
        }
    }
}
