package readFromFile;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A BlocksDefinitionReader class.
 *
 * @author Omer Wolf.
 */
public class BlocksDefinitionReader extends Checkable {
    /**
     * Reading a block-definitions file and returning a BlocksFromSymbolsFactory object.
     *
     * @param reader Is a java io reader.
     * @return BlocksFromSymbolsFactory object.
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        //Members.
        Map<String, String> defaultInfo = new TreeMap<String, String>(); // Map of default information.
        Map<String, String> bdefInfo = new TreeMap<String, String>(); // Map of block information.
        Map<String, Integer> spacersInfo = new TreeMap<String, Integer>(); // Map of spacers.
        BlocksFromSymbolsFactory blocks = new BlocksFromSymbolsFactory();

        BufferedReader buffer = new BufferedReader(reader);

        try {
            LineNumberReader lineReader = new LineNumberReader(reader);
            String line;

            //Reading lines until the end of the file..
            while ((line = lineReader.readLine()) != null) {
                //Ignorance enpty lines and cooments.
                if (!(line.isEmpty()) && !(line.startsWith("#"))) {

                    //Mappind definitions and spacers according to start of line.
                    if (line.startsWith("default")) {
                        readDef(line.substring("default".length() + 1), defaultInfo);
                    } else if (line.startsWith("bdef")) {
                        readDef(line.substring("bdef".length() + 1), bdefInfo);
                        Map<String,String> mergedList = mergeDefinition(defaultInfo, bdefInfo);
                        blocks.addBlock(mergedList);
                        bdefInfo.clear();
                    } else if (line.startsWith("sdef")) {
                        readSpacers(line.substring("sdef".length() + 1), spacersInfo);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading file" + ", message:"
                    + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        blocks.addSpacers(spacersInfo);
        return blocks;

    }
    /**
     * Read definitions from string and adding them to map.
     *
     * @param s    Is a string indicate a line of definitions,
     * @param retMap Is a map of definitions(strings).
     */
    public static void readDef(String s, Map<String, String> retMap) {
        String[] splitLine = s.split(" ");
        List<String> container = Arrays.asList(splitLine);
        String[] definitions = {"symbol", "height", "width", "stroke", "hit_points", "fill:", "fill-"};
        for (int i = 0; i < container.size(); i++) {
            String def = container.get(i);
            if (def.contains(definitions[0])) {
                retMap.put(definitions[0], def.substring(definitions[0].length() + 1));
            } else if (def.contains(definitions[1])) {
                retMap.put(definitions[1], def.substring(definitions[1].length() + 1));
            } else if (def.contains(definitions[2])) {
                retMap.put(definitions[2], def.substring(definitions[2].length() + 1));
            } else if (def.contains(definitions[3])) {
                retMap.put(definitions[3], def.substring(7, def.length()));
            } else if (def.contains(definitions[4])) {
                retMap.put(definitions[4], def.substring(definitions[4].length() + 1));
            } else if (def.contains(definitions[5])) {
                retMap.put(definitions[5], def.substring(definitions[5].length()));
            } else {
                retMap.put(def.substring(0, 7), def.substring(7));
            }
        }

    }


    /**
     * Merge between default definition and block definition and returning new merge Map.
     * Note: block definition overwrites default if there is match.
     *
     * @param defualt Is map of default definitions of blocks in level.
     * @param bdef    Is map of specific definitions of block.
     * @return Map of definitions after merge and get default definitions as required.
     */
    public static Map<String, String> mergeDefinition(Map<String, String> defualt, Map<String, String> bdef) {
        Map<String, String> merged = new TreeMap<String, String>(defualt);
        //Adding block definitions.
        merged.putAll(bdef);
        return merged;
    }

    /**
     * Read spacers from string and adding them to map if the width of the spacer is positive.
     *
     * @param s       is a String.
     * @param spacers Is map of spacers.
     */
    public static void readSpacers(String s, Map<String, Integer> spacers) {
        try {
            Integer[] check = {Integer.parseInt(s.substring(15))};
            String[] space = {"spacer"};
            checkPositiveInteger(check, space);
            spacers.put(s.substring(7, 8), check[0]);
        } catch (Exception exp) {
            exp.printStackTrace(System.err);
            System.exit(1);
        }
    }
}