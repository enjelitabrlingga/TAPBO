import java.util.ArrayList;

public class Cart {
    private ArrayList<MenuItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(MenuItem item) {
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }
}
