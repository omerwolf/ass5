package highScore;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


public class HighScoresTable implements Serializable {
    private int tableSize;
    private List<ScoreInfo> HST;
    public byte version = 1;
    public byte count = 0;


    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size is the size of the table.
     */
    public HighScoresTable(int size) {
        this.tableSize = size;
        this.HST = new ArrayList<ScoreInfo>(this.tableSize + 1);
    }

    /**
     * Add a high-score.
     *
     * @param scoreInfo is the score info.
     */
    public void add(ScoreInfo scoreInfo) {
        this.HST.add(scoreInfo);
        /*
         * Override the compere function to support sort of ScoreInfo object.
         *
         * @param left - score info.
         * @param right - score info.
         * @return >0 if the right is bigger, 0 if equals and <0 otherwise.
         */
        Comparator<ScoreInfo> comparator = new Comparator<ScoreInfo>() {
            @Override
            public int compare(ScoreInfo left, ScoreInfo right) {
                return right.getScore() - left.getScore();
            }
        };

        Collections.sort(this.HST, comparator);
        if (this.HST.size() > tableSize) {
            this.HST.remove(tableSize);
        }
    }
    /*
    public void sort() {
        ScoreInfoComparator comparator = new ScoreInfoComparator();
        //case HST.2.score < HST.3.score
        if (comparator.compare(this.HST.get(2), this.HST.get(3)) == 1) {
            Collections.swap(this.HST, 2, 3); //Swap the 3rd and the 4th places
            this.HST.remove(3);
            //case HST.1.score < HST.2.score
            if (comparator.compare(this.HST.get(1), this.HST.get(2)) == 1) {
                Collections.swap(this.HST, 2, 3); //Swap the 3rd and the 4th places
            }
        }
    }*/

    /**
     * @return table size.
     */
    public int size() {
        return this.tableSize;
    }

    /**
     * @return the current high scores.
     * The list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.HST;
    }

    /**
     * Checking if a score is good enough to ente the high scores table.
     *
     * @param score is the score.
     * @return the rank of the current score.
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     */
    public int getRank(int score) {
        if (this.HST.isEmpty()) {
            return 1;
        }
        int i;
        for (i = 0; i < this.tableSize; i++) {
            if (this.HST.get(i).getScore() < score) {
                return i + 1;
            }
        }
        if (i < this.tableSize) {
            return i + 1;
        }
        return this.tableSize + 1;

    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.HST.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared
     *
     * @param filename is the file
     * @throws IOException when failed finding the class.
     */
    public void load(File filename) throws IOException {
        this.tableSize = HighScoresTable.loadFromFile(filename).size();
        this.HST = HighScoresTable.loadFromFile(filename).getHighScores();
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename is the file.
     * @throws IOException when failed saving or closing file.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving file");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename is the file that load the table.
     * @return the high score table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream ois = null;
        HighScoresTable scoreTable = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            scoreTable = (HighScoresTable) ois.readObject();
            return scoreTable;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename);
            return new HighScoresTable(5);
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: " + filename);
            return new HighScoresTable(10);
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return new HighScoresTable(10);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
}
