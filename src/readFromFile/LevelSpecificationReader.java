package readFromFile;

import accessories.Block;
import accessories.Velocity;
import backgrounds.Background;
import levels.LevelInformation;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A LevelSpecificationReader class.
 *
 * @author Omer Wolf.
 */
public class LevelSpecificationReader extends Checkable{
    //Members.
    private BlocksFromSymbolsFactory blocks = null;

    /**
     * Create list of levels according to files that read.
     *
     * @param reader Is a java.io.Reader to reaf from file.
     * @return List of levels information.
     */
    public List<LevelInformation> levelReader (Reader reader){

        BufferedReader buffer = new BufferedReader(reader);
        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        Map<String, String> levelsInfo = new LinkedHashMap<String, String>();
        String line = null;
        int linesCounter = 0;
        try {
            // Reading lines until the end of the file.
            while ((line = buffer.readLine()) != null) {
                // Ignorance empty line and comments.
                if (!(line.isEmpty()) && !(line.startsWith("#"))) {
                    if (line.equals("START_LEVEL") || line.equals("END_LEVEL")) {
                        continue;
                    }
                    else if (line.equals("START_BLOCKS")) {
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
     * @param buffer           Is a buffer reader.
     * @param linesCounter Is counter of lines of blocks.
     * @param levelsList   Is a List of levels to be set in the new level
     */
    public void readBlocks(Map<String, String> levelsInfo, BufferedReader buffer,
                           int linesCounter, List<LevelInformation> levelsList) {
        //Valid definitions of block.
        String[] definitions = {"level_name","ball_velocities", "background", "paddle_speed", "paddle_width",
                "block_definitions", "blocks_start_x", "blocks_start_y", "row_height", "num_blocks"};
        InputStreamReader is = null;
        String line = null;
        try {
            //Check if all definitions are exist.
            checkDefinitions(levelsInfo, definitions);
            //Create new level according to level information.
            LevelOrganizator newLevel = this.initLevel(levelsInfo);
            is = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(
                    levelsInfo.get("block_definitions")));
            this.blocks = BlocksDefinitionReader.fromReader(is);
            int yPosition = Integer.parseInt(levelsInfo.get("row_height"));
            line = buffer.readLine();
            while (!(line.equals("END_BLOCKS"))) {
                //Adding blocks to level.
                if (!(line.isEmpty()) && !(line.startsWith("#"))) {
                    newLevel.addBlocks(this.createBlocks(line, Integer.parseInt(levelsInfo.get("blocks_start_x")),
                            Integer.parseInt(levelsInfo.get("blocks_start_y"))
                                    + linesCounter * yPosition));
                    linesCounter++;
                }
                line = buffer.readLine();
            }
            //Insert level to the level list.
            levelsList.add(newLevel);
        } catch (Exception exp) {
            exp.printStackTrace(System.err);
            System.exit(1);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
    }



    /**
     * Create level according to definitions from map.
     *
     * @param level Is a map of level definitions,
     * @return new Level according to keys and value from map
     */
    public LevelOrganizator initLevel(Map<String, String> level) throws Exception{
        //Setting the parameters of new level according to Map.
        List<Velocity> ballsVel = this.readVelocity(level.get("ball_velocities"));
        int paddleSpeed = Integer.parseInt(level.get("paddle_speed"));
        int paddleWidth = Integer.parseInt(level.get("paddle_width"));
        String levelName = level.get("level_name");
        Background background = checkBackground(level.get("background"));
        int numBlocks = Integer.parseInt(level.get("num_blocks"));


        return new LevelOrganizator(ballsVel,paddleSpeed, paddleWidth, levelName, background,numBlocks);
    }

    public List<Velocity> readVelocity(String velocitiesString)  throws Exception{
            List<Velocity> velocitiesList = new ArrayList<Velocity>();
            // Splitting between definition and his data.
            String[] splitLine = velocitiesString.split(" ");
            for (String vel : splitLine) {
                String[] splitVel = velocitiesString.split(",");
                double angle = Double.valueOf(splitVel[0]);
                double speed = Double.valueOf(splitVel[1]);
                Velocity newVel = Velocity.fromAngleAndSpeed(angle, speed);
                velocitiesList.add(newVel);
            }
            return velocitiesList;
    }
    /**
     * Getting String represent line of block,goes over the string check and create blocks and spacers according
     * to BlocksFromSymbolsFactory.
     *
     * @param s Is String represent line of block/spacers.
     * @param x Is the x position to set line of blocks.
     * @param y Is the y position to set line of blocks.
     * @return List of blocks according to String line(s).
     */
    public List<Block> createBlocks(String s, int x, int y) {
        List<Block> blocksList = new ArrayList<Block>();
        //Count the width of every block/spacer
        double widthCount = 0;
        //Loop goes over all chars in string.
        for (char ch : s.toCharArray()) {
            String stringValue = String.valueOf(ch);
            //Check if symbol is blocks symbol or space symbol.
            if (blocks.isBlockSymbol(stringValue)) {
                blocksList.add(blocks.getBlock(stringValue, x + (int) widthCount, y));
                widthCount += blocks.getBlock(stringValue, 0, 0).getWidth();
            } else if (blocks.isSpaceSymbol(stringValue)) {
                widthCount += blocks.getSpaceWidth(stringValue);
            }
        }
        return blocksList;

    }



    /**
     * Create list of levels according to files that read.
     *
     * @param reader Is a java.io.Reader to reaf from file.
     * @return List of levels information.
     */
    /*
    public List<LevelInformation> fromReader(Reader reader) throws IOException {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        List<ArrayList<String>> levelsInfo = this.levelReader(reader);
        for (ArrayList<String> levelInfo : levelsInfo) {
            LevelOrganizator level = (LevelOrganizator) this.setLevelInfo(levelInfo);
            levels.add(level);
        }
        return levels;
    }


    public LevelInformation setLevelInfo(List<String> levelText) throws Exception {
        //Valid definitions of block.
        String[] definitions = {"level_name", "background", "paddle_speed", "paddle_width",
                "block_definitions", "blocks_start_x", "blocks_start_y", "row_height", "num_blocks"};
        checkDefinitions(levelText, definitions);

    }
    */



}