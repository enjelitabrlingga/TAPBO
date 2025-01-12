import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Receipt {
    private Cart cart;

    public Receipt(Cart cart) {
        this.cart = cart;
    }

    public void generateReceipt(String paymentMethod) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("receipt.txt", true))) {
            writer.println("--- Struk Pembelian ---");
            writer.println("Tanggal: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            for (MenuItem item : cart.getItems()) {
                writer.println(item.getName() + " - Rp " + item.getPrice());
            }
            writer.println("Total: Rp " + cart.getTotalPrice());
            writer.println("Metode Pembayaran: " + paymentMethod);
            writer.println("========================\n");
        } catch (IOException e) {
            System.out.println("Gagal membuat struk: " + e.getMessage());
        }
    }
}
