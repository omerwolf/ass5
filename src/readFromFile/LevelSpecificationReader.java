package readFromFile;

import levels.LevelInformation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A LevelSpecificationReader class.
 *
 * @author Omer Wolf.
 */
public class LevelSpecificationReader extends Checkable {

    /**
     * Create list of levels according to files that read.
     *
     * @param reader Is a java.io.Reader to reaf from file.
     * @return List of levels information.
     */
    public List<LevelInformation> fromReader(Reader reader) {
        BufferedReader buffer = new BufferedReader(reader);
        String line;
        Map<String, String> levelsInfo = new LinkedHashMap<String, String>();
        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        int linesCounter = 0;
        try {
            // Reading lines until the end of the file.
            while ((line = buffer.readLine()) != null) {
                // check that line is not empty or comment
                if (!(line.isEmpty()) && !(line.startsWith("#"))) {
                    if (line.equals("START_LEVEL") || line.equals("END_LEVEL")) {
                        continue;
                    } else if (line.equals("START_BLOCKS")) {
                        // Read lines associated with blocks.
                        this.readBlocks(levelsInfo, buffer, linesCounter, levelsList);
                    } else {
                        // Splitting between definition and his data.
                        String[] splitLine = line.split(":");
                        levelsInfo.put(splitLine[0], splitLine[1]);
                    }

                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading file" + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (buffer != null) {
                    // Closing file.
                    buffer.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        return levelsList;
    }

    /**
     * Read lines with definitions of blocks and add  new block to new level.
     * Note: check validity(if exist) of definitions.
     *
     * @param levelsInfo   Is map of levels information.
     * @param br           Is a buffer reader.
     * @param linesCounter Is counter of lines of blocks.
     * @param levelsList   Is a List of levels to be set in the new level
     */
    public void readBlocks(Map<String, String> levelsInfo, BufferedReader br,
                           int linesCounter, List<LevelInformation> levelsList) {
        //Valid definitions of block.
        String[] definitions = {"level_name", "background", "paddle_speed", "paddle_width",
                "block_definitions", "blocks_start_x", "blocks_start_y", "row_height", "num_blocks"};
        try {
            //Check if all definitions are exist.
            checkDefinitions(levelsInfo, definitions);


        } catch (Exception exp) {
            exp.printStackTrace(System.err);
            System.exit(1);
    }
}