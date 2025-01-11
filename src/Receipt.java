public class Receipt {
    private Cart cart;

    public Receipt(Cart cart) {
        this.cart = cart;
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder("Struk Pembelian:\n");
        for (MenuItem item : cart.getItems()) {
            receipt.append(item.getName()).append(" - Rp ").append(item.getPrice()).append("\n");
        }
        receipt.append("Total: Rp ").append(cart.getTotalPrice()).append("\n");
        return receipt.toString();
    }
}
