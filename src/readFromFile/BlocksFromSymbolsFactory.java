package readFromFile;

import accessories.Block;

import java.util.Map;
import java.util.TreeMap;

/**
 * A BlocksFromSymbolsFactory class.
 *
 * @author Omer Wolf.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<>();
        this.blockCreators = new TreeMap<>();
    }

    /**
     * @param s Is a String indicate symbol.
     * @return True if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
        /*
        String spaceArr[] = checkedString.split(" ");
        // spacers definition is at least include "sdef" ,key ,value.
        if (spaceArr.length < 3) {
            return false;
        }
        //check if the first string is "sdef".
        if (!spaceArr[0].equals("sdef")) {
            return false;
        }
        //check if the key provide with "symbol" string.
        String keyArr[] = spaceArr[1].split(":");
        if (!keyArr[0].equals("symbol")) {
            return false;
        }
        return true;
        */
    }

    /**
     * @param s Is a String represent block symbol.
     * @return True if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * returning block located at position (xpos, ypos) according to 's'.
     *
     * @param s    Is String represent block symbole.
     * @param xpos Is the x position to set to the block.
     * @param ypos Is the y position to set to the block.
     * @return Block according to the definitions associated with symbol s.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * @param s Is a string represent space symbol.
     * @return The width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s){
        return this.spacerWidths.get(s);
    }
}