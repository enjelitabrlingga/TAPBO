import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CafeSystemApp extends Application {
    private static ArrayList<MenuItem> menuItems;
    private static Cart cart;

    public static void main(String[] args) {
        menuItems = new ArrayList<>();
        cart = new Cart();
        addMenuItems();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #F4E1D2;");

        Label welcomeLabel = new Label("Selamat Datang di Cafe Kami!");
        welcomeLabel.setFont(new Font("Arial", 30));
        root.getChildren().add(welcomeLabel);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);

        HBox menuLayout = new HBox(20);
        menuLayout.setStyle("-fx-padding: 20px;");

        VBox leftMenu = new VBox(10);
        VBox rightMenu = new VBox(10);

        int halfSize = menuItems.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            int finalI = i;
            Button button = new Button(menuItems.get(finalI).getName() + " (Rp " + menuItems.get(finalI).getPrice() + ")");
            button.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
            button.setOnAction(e -> addToCart(menuItems.get(finalI)));
            leftMenu.getChildren().add(button);
        }

        for (int i = halfSize; i < menuItems.size(); i++) {
            int finalI = i;
            Button button = new Button(menuItems.get(finalI).getName() + " (Rp " + menuItems.get(finalI).getPrice() + ")");
            button.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
            button.setOnAction(e -> addToCart(menuItems.get(finalI)));
            rightMenu.getChildren().add(button);
        }

        menuLayout.getChildren().addAll(leftMenu, rightMenu);
        root.getChildren().add(menuLayout);

        Label paymentMethodLabel = new Label("Pilih Metode Pembayaran:");
        ComboBox<String> paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getItems().addAll("Tunai", "ShopeePay", "Dana", "Transfer antar Bank", "QRIS");
        paymentMethodComboBox.setValue("Tunai");
        root.getChildren().add(paymentMethodLabel);
        root.getChildren().add(paymentMethodComboBox);

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        checkoutButton.setOnAction(e -> checkout(paymentMethodComboBox.getValue()));
        root.getChildren().add(checkoutButton);

        Scene scene = new Scene(scrollPane, 800, 600);
        primaryStage.setTitle("Sistem Pemesanan Kafe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void addMenuItems() {
        // Makanan
        menuItems.add(new FoodItem("Nasi Goreng", 25000));
        menuItems.add(new FoodItem("Mie Goreng", 23000));
        menuItems.add(new FoodItem("Sate Ayam", 30000));
        menuItems.add(new FoodItem("Pizza Margherita", 45000));
        menuItems.add(new FoodItem("Burger", 35000));
        menuItems.add(new FoodItem("Spaghetti Bolognese", 38000));
        menuItems.add(new FoodItem("Lasagna", 40000));
        menuItems.add(new FoodItem("Pasta Carbonara", 39000));
        menuItems.add(new FoodItem("Kari Ayam", 30000));
        menuItems.add(new FoodItem("Ayam Penyet", 28000));
        menuItems.add(new FoodItem("Taco", 30000));
        menuItems.add(new FoodItem("Dimsum Ayam", 20000));
        menuItems.add(new FoodItem("Steak Daging", 60000));
        menuItems.add(new FoodItem("Roti Bakar", 15000));
        menuItems.add(new FoodItem("Bubur Ayam", 20000));
        menuItems.add(new FoodItem("Ayam Goreng Crispy", 25000));
        menuItems.add(new FoodItem("Spaghetti Aglio Olio", 28000));
        menuItems.add(new FoodItem("Curry Puff", 18000));
        menuItems.add(new FoodItem("Kwetiau Siram", 32000));
        menuItems.add(new FoodItem("Nasi Kuning", 23000));
        menuItems.add(new FoodItem("Nasi Uduk", 24000));
        menuItems.add(new FoodItem("Soto Ayam", 27000));
        menuItems.add(new FoodItem("Gado-Gado", 15000));
        menuItems.add(new FoodItem("Pempek", 22000));
        menuItems.add(new FoodItem("Bakso", 25000));
        menuItems.add(new FoodItem("Sop Buntut", 45000));
        menuItems.add(new FoodItem("Ikan Bakar", 55000));
        menuItems.add(new FoodItem("Ayam Bakar", 30000));
        menuItems.add(new FoodItem("Pecel Lele", 19000));

        // Minuman
        menuItems.add(new DrinkItem("Es Teh Manis", 10000));
        menuItems.add(new DrinkItem("Es Kopi Susu", 20000));
        menuItems.add(new DrinkItem("Smoothie Mangga", 25000));
        menuItems.add(new DrinkItem("Milkshake Coklat", 30000));
        menuItems.add(new DrinkItem("Cappuccino", 25000));
        menuItems.add(new DrinkItem("Teh Tarik", 15000));
        menuItems.add(new DrinkItem("Jus Alpukat", 22000));
        menuItems.add(new DrinkItem("Matcha Latte", 27000));
        menuItems.add(new DrinkItem("Iced Coffee", 25000));
        menuItems.add(new DrinkItem("Lemon Tea", 12000));
        menuItems.add(new DrinkItem("Soda Gembira", 13000));
        menuItems.add(new DrinkItem("Frozen Lemonade", 18000));
        menuItems.add(new DrinkItem("Strawberry Mojito", 22000));
        menuItems.add(new DrinkItem("Iced Matcha", 24000));
        menuItems.add(new DrinkItem("Es Jeruk", 12000));
        menuItems.add(new DrinkItem("Hot Chocolate", 25000));
        menuItems.add(new DrinkItem("Black Tea", 15000));
        menuItems.add(new DrinkItem("Bubble Tea", 22000));
        menuItems.add(new DrinkItem("Fanta Orange", 10000));
        menuItems.add(new DrinkItem("Coca Cola", 12000));
        menuItems.add(new DrinkItem("Sprite", 11000));
        menuItems.add(new DrinkItem("Peach Tea", 16000));
        menuItems.add(new DrinkItem("Green Tea", 14000));
        menuItems.add(new DrinkItem("Taro Milk Tea", 21000));
        menuItems.add(new DrinkItem("Sarsaparilla", 18000));
        menuItems.add(new DrinkItem("Iced Latte", 23000));
        menuItems.add(new DrinkItem("Raspberry Lemonade", 17000));
        menuItems.add(new DrinkItem("Pineapple Juice", 15000));
        menuItems.add(new DrinkItem("Watermelon Juice", 13000));
        menuItems.add(new DrinkItem("Ginger Ale", 14000));
        menuItems.add(new DrinkItem("Tropical Punch", 19000));
    }

    private static void addToCart(MenuItem item) {
        cart.add(item);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Item Ditambahkan");
        alert.setHeaderText(null);
        alert.setContentText("Menu ditambahkan ke keranjang: " + item.getName());
        alert.showAndWait();
    }

    private static void checkout(String paymentMethod) {
        if (cart.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Keranjang Kosong");
            alert.setHeaderText(null);
            alert.setContentText("Keranjang Anda kosong!");
            alert.showAndWait();
            return;
        }

        Receipt receipt = new Receipt(cart);
        String receiptText = receipt.generateReceipt();

        saveToFile(receiptText, paymentMethod);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checkout Sukses");
        alert.setHeaderText(null);
        alert.setContentText(receiptText + "\nMetode Pembayaran: " + paymentMethod);
        alert.showAndWait();

        cart.clear();
    }

    private static void saveToFile(String receipt, String paymentMethod) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pembelian.txt", true))) {
            writer.write(receipt);
            writer.write("Metode Pembayaran: " + paymentMethod + "\n");
            writer.write("========================================\n");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Terjadi kesalahan");
            alert.setContentText("Tidak dapat menyimpan data: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
