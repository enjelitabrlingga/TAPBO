import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> items;

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

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
