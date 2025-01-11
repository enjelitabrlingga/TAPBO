import java.util.ArrayList;

public class Receipt {
    private Cart cart;

    public Receipt(Cart cart) {
        this.cart = cart;
    }

    public String generateReceipt() {
        ArrayList<MenuItem> items = cart.getItems();
        StringBuilder receipt = new StringBuilder("Pesanan Anda:\n");

        int total = 0;
        for (MenuItem item : items) {
            receipt.append("- ").append(item.getName()).append(" (Rp ").append(item.getPrice()).append(")\n");
            total += item.getPrice();
        }

        receipt.append("Total: Rp ").append(total).append("\n");
        return receipt.toString();
    }
}
