import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class Cart extends JFrame {
    private static DefaultTableModel cartTableModel;
    private JTable cartTable;
    private static JLabel totalPriceLabel;

    public Cart() {
        setTitle("Shopping Cart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JPanel for the title and center the title
        JPanel semiTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel semiTitleLabel = new JLabel("Shopping Cart");
        semiTitlePanel.add(semiTitleLabel);
        add(semiTitlePanel, BorderLayout.NORTH);

        JPanel bodyPanel = new JPanel(new BorderLayout());

        // Set up the cart table
        cartTableModel = new DefaultTableModel(new String[]{"Product", "Quantity", "Price"}, 0);
        cartTable = new JTable(cartTableModel);
        JScrollPane cartTableScrollPane = new JScrollPane(cartTable);
        cartTableScrollPane.setPreferredSize(new Dimension(400, 300));
        bodyPanel.add(cartTableScrollPane, BorderLayout.CENTER);

        totalPriceLabel = new JLabel("Total Price: $0.00");
        bodyPanel.add(totalPriceLabel, BorderLayout.SOUTH);

        add(bodyPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    //Methods to perform in shopping cart

    public static void updateCartTable() {
        cartTableModel.setRowCount(0);
        HashMap<String, Integer> productCount = new HashMap<>();
        HashMap<String, Double> productPrice = new HashMap<>();
        for (Product product : GUI.shoppingCart) {
            productCount.put(product.getProductID(), productCount.getOrDefault(product.getProductID(), 0) + 1);
            productPrice.put(product.getProductID(), product.getPrice());
        }
        for (String productId : productCount.keySet()) {
            cartTableModel.addRow(new Object[]{productId, productCount.get(productId), productPrice.get(productId)});
        }
    }

    public static void updateTotalPrice() {
        double totalPrice = 0;
        for (Product product : GUI.shoppingCart) {
            totalPrice += product.getPrice();
        }
        totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
    }
}
