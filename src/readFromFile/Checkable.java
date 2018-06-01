package readFromFile;

import java.util.Map;

public abstract class Checkable {
    /**
     * Check if level map contains all string in check string array.
     *
     * @param levelsInfo Is map with level definitions.
     * @param definitions Is array with string to check that contains in the map.
     * @throws Exception If there is string from check that mission.
     */
    public  void checkDefinitions(Map<String, String> levelsInfo, String[] definitions)  throws Exception{
        for (int i = 0; i < definitions.length; i++) {
            if (!levelsInfo.containsKey(definitions[i])) {
                throw new Exception(definitions[i] + " is missing! add this definition.");
            }
        }
    }
}
