package readFromFile;

import backgrounds.Background;
import backgrounds.ImageBackground;
import backgrounds.OneColorBackground;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public abstract class Checkable {
    /**
     * Check if level map contains all string in check string array.
     *
     * @param level Is map with level definitions.
     * @param check Is array with string to ce check that contains in the map.
     * @throws Exception If there is string from check that missin.
     */
    public void checkDefinitions(Map<String, String> level, String[] check) throws Exception {
        for (int i = 0; i < check.length; i++) {
            if (!level.containsKey(check[i])) {
                throw new Exception(check[i] + " is missing! add this definition.");
            }
        }
    }

    /**
     * Define according to string which background to return.
     *
     * @param s Is String representing color/image.
     * @return Background.
     */
    public Background checkBackground(String s) {
        // Check if the backgrounds image or color.
        if (s.startsWith("color")) {
            Color color = null;
            //Define color.
            ColorsParser cp = new ColorsParser();
            try {
                color = cp.colorFromString(s);
            } catch (Exception exp) {
                exp.printStackTrace(System.err);
                System.exit(1);
            }
            //Return background as color.
            return new OneColorBackground(color);
        } else {
            int startIndex = s.indexOf("image(");
            //Return background as image.
            return new ImageBackground(s.substring(startIndex + 6, s.length() - 1));
        }
    }

    /**
     * Check if numbers are positive.
     *
     * @param check       Is array of integers to check.
     * @param definitions Is array of definitions of block.
     * @throws Exception if number is negaive or zero.
     */
    public static void checkPositiveInteger(Integer[] check, String[] definitions) throws Exception {
        for (int i = 0; i < check.length; i++) {
            if (check[i] <= 0) {
                throw new Exception(" insert Positive Value for" + definitions[i]);
            }
        }
    }
}
