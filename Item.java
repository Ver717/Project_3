/**
 * Item represents something that can be interacted with on the locations of a campus. it can be picked up or put down
 */
public class Item {
    private String name;
    private String message;

    /**
     * initializes name and item to test values
     */
    public Item() {
        this.name = "test";
        this.message = "this is a test item?";
    }

    /**
     * sets name and message fields to n and m
     * @param n the name of the item to be: String
     * @param m the message or description of the item to be: String
     */
    public Item(String n, String m) {
        this.name = n;
        this.message = m;
    }

    /**
     * gets the name of the item
     * @return  the name of the item: String
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the message of the item
     * @return  the message of the item: String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * sets the name of the item
     * @param name  the name of the item: String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the message of the item
     * @param message the message of the item: String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**    (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.name;
    }
}