/** Class for counter. */
public class Counter {
    private int value;

    /**
     * standard constructor. intialize value to 0.
     */
    public Counter() {
        value = 0;
    }

    /**
     * add number to current count.
     *
     * @param number
     */
    void increase(int number) {
        value += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number
     */
    void decrease(int number) {
        value -= number;
    }

    /**
     * @return current count.
     */
    int getValue() {
        return value;
    }
}