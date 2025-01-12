import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class CafeSystemApp extends Application {
    private List<MenuItem> menuItems = new ArrayList<>();
    private Cart cart = new Cart();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        menuItems.add(new FoodItem("Chicken Nugget", 20000));
        menuItems.add(new FoodItem("Cheesecake", 35000));
        menuItems.add(new FoodItem("Spaghetti Bolognese", 30000));
        menuItems.add(new DrinkItem("Es Teh Manis", 10000));
        menuItems.add(new DrinkItem("Cappuccino", 27000));
        menuItems.add(new FoodItem("Brownies Cokelat", 25000));
        menuItems.add(new FoodItem("Donat Cokelat", 15000));
        menuItems.add(new DrinkItem("Milkshake Coklat", 30000));
        menuItems.add(new DrinkItem("Smoothie Mangga", 25000));
        menuItems.add(new DrinkItem("Jus Alpukat", 25000));
        menuItems.add(new DrinkItem("Matcha Latte", 35000));
        menuItems.add(new DrinkItem("Es Lemon Tea", 15000));
        menuItems.add(new DrinkItem("Lemonade Fresh", 22000));
        menuItems.add(new DrinkItem("Es Jeruk", 12000));
        menuItems.add(new FoodItem("Mini Pizza", 25000));
        menuItems.add(new DrinkItem("Latte", 25000));
        menuItems.add(new FoodItem("Tiramisu", 40000));
        menuItems.add(new DrinkItem("Kopi Hitam", 12000));
        menuItems.add(new DrinkItem("Hot Chocolate", 30000));
        menuItems.add(new DrinkItem("Chai Tea", 25000));
        menuItems.add(new FoodItem("Chicken Wings", 30000));
        menuItems.add(new FoodItem("Burger Special", 40000));
        menuItems.add(new FoodItem("Hotdog", 25000));
        menuItems.add(new DrinkItem("Smoothie Berry", 32000));
        menuItems.add(new FoodItem("Waffle Strawberry", 35000));
        menuItems.add(new DrinkItem("Kopi Susu", 20000));
        menuItems.add(new FoodItem("Salad Buah", 25000));
        menuItems.add(new FoodItem("Kentang Goreng", 20000));
        menuItems.add(new FoodItem("Ayam Geprek", 30000));
        menuItems.add(new DrinkItem("Soda Gembira", 18000));
        menuItems.add(new DrinkItem("Lemon Squash", 20000));
        menuItems.add(new FoodItem("Dimsum Ayam", 24000));
        menuItems.add(new FoodItem("Roti Bakar Keju", 15000));
        menuItems.add(new FoodItem("Lasagna", 40000));
        menuItems.add(new DrinkItem("Bubble Tea", 23000));
        menuItems.add(new DrinkItem("Thai Tea", 25000));
        menuItems.add(new FoodItem("Pancake", 30000));
        menuItems.add(new FoodItem("Risoles", 12000));
        menuItems.add(new DrinkItem("Green Tea Latte", 28000));
        menuItems.add(new DrinkItem("Es Kopi Susu", 22000));
        menuItems.add(new FoodItem("Pizza Margherita", 45000));
        menuItems.add(new FoodItem("Nasi Goreng", 25000));
        menuItems.add(new FoodItem("Mie Ayam", 20000));
        menuItems.add(new DrinkItem("Es Coklat", 15000));
        menuItems.add(new FoodItem("Kebab Ayam", 30000));
        menuItems.add(new DrinkItem("Sparkling Water", 18000));
        menuItems.add(new FoodItem("Steak Sapi", 60000));
        menuItems.add(new DrinkItem("Frappuccino", 35000));

        primaryStage.setTitle("Sistem Pemesanan Kafe");

        Label header = new Label("Selamat Datang di Cafe Kami!");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: white;");

        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.setPadding(new Insets(10));

        int col = 0;
        int row = 0;
        for (MenuItem item : menuItems) {
            Button button = new Button(item.toString());
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(e -> {
                cart.addItem(item);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Ditambahkan");
                alert.setHeaderText(null);
                alert.setContentText(item.getName() + " berhasil ditambahkan ke keranjang. Oke!");
                alert.showAndWait();
            });
            menuGrid.add(button, col, row);
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }

        ComboBox<String> paymentMethods = new ComboBox<>();
        paymentMethods.getItems().addAll("QRIS", "Transfer Bank BRI", "Transfer Bank BCA", "Transfer Bank BSI", "ShopeePay", "Dana", "Tunai");
        paymentMethods.setValue("QRIS");

        VBox transferBox = new VBox(10);
        transferBox.setVisible(false);
        transferBox.setPadding(new Insets(10));
        transferBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        TextField cardNumber = new TextField();
        cardNumber.setPromptText("Nomor Kartu");

        PasswordField pinField = new PasswordField();
        pinField.setPromptText("PIN");

        TextField nominalField = new TextField();
        nominalField.setPromptText("Nominal");

        transferBox.getChildren().addAll(new Label("Transfer Antar Bank"), cardNumber, pinField, nominalField);

        paymentMethods.setOnAction(e -> {
            if (paymentMethods.getValue().startsWith("Transfer")) {
                transferBox.setVisible(true);
            } else {
                transferBox.setVisible(false);
            }
        });

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        checkoutButton.setOnAction(e -> {
            if (cart.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Keranjang Kosong");
                alert.setHeaderText(null);
                alert.setContentText("Silakan tambahkan item ke keranjang sebelum checkout.");
                alert.showAndWait();
            } else {
                if (paymentMethods.getValue().startsWith("Transfer")) {
                    if (cardNumber.getText().isEmpty() || pinField.getText().isEmpty() || nominalField.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Data Tidak Lengkap");
                        alert.setHeaderText(null);
                        alert.setContentText("Silakan lengkapi semua data transfer.");
                        alert.showAndWait();
                        return;
                    }
                }
                Receipt receipt = new Receipt(cart);
                receipt.generateReceipt(paymentMethods.getValue());
                cart.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Checkout Berhasil");
                alert.setHeaderText(null);
                alert.setContentText("Pembayaran berhasil dengan metode: " + paymentMethods.getValue() + ". Oke!");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10, header, menuGrid, paymentMethods, transferBox, checkoutButton);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: #2c3e50;");

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
