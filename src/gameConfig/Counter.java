//Elad Vizenblit
package gameConfig;


/**
 * This is a counter class.
 */

public class Counter {

    private int counter;

    /**
     * Constructor.
     *
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     * @param number number to add to counter.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number number to subtract
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets current count.
     * @return current counter
     */
    public int getValue() {
        return this.counter;
    }
}