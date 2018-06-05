package indicators;
/**
 * A Counter class.
 * @author Omer Wolf.
 */
public class Counter {
    private int val;
    /**
     *initialize counter with 0 value.
     */
    public Counter() {
        this.val = 0;
    }
    /**
     * initialize counter with initval value.
     * @param initVal the value.
     */
    public Counter(int initVal) {
        this.val = initVal;
    }
    /**
     * add number to current count.
     * @param number the number.
     */
    public void increase(int number) {
        this.val += number;
    }
    /**
     * add 1 to current count.
     */
    public void increase() {
        this.val += 1;
    }
    /**
     * subtract number from current count.
     * @param number the number.
     */
    public void decrease(int number) {
        this.val -= number;
    }
    /**
     * subtract 1 from current count.
     */
    public void decrease() {
        this.val -= 1;
    }
    /**
     * get current count.
     * @return the value.
     */
    public int getValue() {
        return val;
    }
}