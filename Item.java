public class Item {
    private String name;
    private String message;

    public Item() {

    }

    public Item(String n, String m) {
        this.name = n;
        this.message = m;
    }

    public String getName() {
        return this.name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return this.name;
    }
}