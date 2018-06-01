package menu;

/**
 * MenuOptionInfo class.
 */
public class MenuOptionInfo<T> {
    private String key;
    private String lineToPrint;
    private T retValue;

    public MenuOptionInfo(String k, String line, T val) {
        this.key = k;
        this.lineToPrint = line;
        this.retValue = val;
    }

    public String getKey() {
        return this.key;
    }

    public String getLineToPrint() {
        return this.lineToPrint;
    }

    public T getValue() {
        return this.retValue;
    }
}