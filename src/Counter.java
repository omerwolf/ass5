public class Counter {
    private int val;

    public Counter() {
        this.val = 0;
    }
    public Counter(int initVal) {
        this.val = initVal;
    }
    // add number to current count.
    public void increase(int number) {
        this.val += number;
    }
    public void increase() {
        this.val += 1;
    }
    // subtract number from current count.
    public void decrease(int number) {
        this.val -= number;
    }
    public void decrease() {
        this.val -= 1;
    }
    // get current count.
    public int getValue() {
        return val;
    }
}