package GameCore;

/**
 * @author Leonardo Rodin 207377151
 * usefull counter class
 */
public class Counter {

    //fields
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        counter = 0;
    }

    /**
     * a builder with a given value.
     *
     * @param number starts counter from it
     */
    public Counter(int number) {
        counter = number;
    }

    /**
     * adds a number from current count.
     *
     * @param number the number which needs to be added to count
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract a number from current count.
     *
     * @param number the number which needs to be subtracted from count
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * @return the current count.
     */
    public int getValue() {
        return counter;
    }
}
