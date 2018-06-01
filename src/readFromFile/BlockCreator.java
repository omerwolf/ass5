package readFromFile;

import accessories.Block;
/**
 * A BlockCreator interface.
 *
 * @author Omer Wolf
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos x place
     * @param ypos y place
     * @return block
     */
    Block create(int xpos, int ypos);
}