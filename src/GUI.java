import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JComboBox<String> categoryComboBox;
    private static JTable productTable;
    private static DefaultTableModel productTableModel;
    private JTextArea productDetailsTextArea;

    public static WestminsterShoppingManager shoppingManager;
    public static ArrayList<Product> shoppingCart;

    public GUI(WestminsterShoppingManager westminsterShoppingManager) {
        shoppingManager = westminsterShoppingManager;
        shoppingManager.loadFile();
        shoppingCart = new ArrayList<>();

        setTitle("Westminster Shopping Centre");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JPanel for the title and center the title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Westminster Shopping Centre");
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Create the top panel for product category selection
        JPanel topPanel = new JPanel(new BorderLayout());
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        categoryComboBox.addActionListener(e -> filterProducts());
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        categoryPanel.add(new JLabel("Select Product Category:"));
        categoryPanel.add(categoryComboBox);
        topPanel.add(categoryPanel, BorderLayout.WEST);

        // Add the "Shopping Cart" button
        JButton cartButton = new JButton("Shopping Cart");
        cartButton.addActionListener(e -> new Cart());
        topPanel.add(cartButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Set up the product table
        productTableModel = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price", "Available", "Info"}, 0);
        productTable = new JTable(productTableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getSelectionModel().addListSelectionListener(e -> showProductDetails());
        JScrollPane productTableScrollPane = new JScrollPane(productTable);
        productTableScrollPane.setPreferredSize(new Dimension(400, 300));

        // Set up the center panel with the product table and details area
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(productTableScrollPane, BorderLayout.NORTH);

        productDetailsTextArea = new JTextArea(5, 30);
        productDetailsTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(productDetailsTextArea), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Add the "Add to Shopping Cart" button
        JPanel addToCartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(e -> addToCart());
        addToCartPanel.add(addToCartButton);
        add(addToCartPanel, BorderLayout.SOUTH);

        loadProducts();
        setVisible(true);
    }

    //Methods to perform in GUI

    public static void loadProducts() {
        productTableModel.setRowCount(0);
        for (Product product : shoppingManager.getStock()) {
            String details = getProductDetails(product);
            productTableModel.addRow(new Object[]{product.getProductID(), product.getProductName(), product.getClass().getSimpleName(), product.getPrice(), product.getAvailableAmount(), details});
        }
        colorLowStockItems();
    }

    private void filterProducts() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        productTableModel.setRowCount(0);
        for (Product product : shoppingManager.getStock()) {
            if ("All".equals(selectedCategory) || product.getClass().getSimpleName().equals(selectedCategory)) {
                String details = getProductDetails(product);
                productTableModel.addRow(new Object[]{product.getProductID(), product.getProductName(), product.getClass().getSimpleName(), product.getPrice(), product.getAvailableAmount(), details});
            }
        }
        colorLowStockItems();
    }

    private static String getProductDetails(Product product) {
        StringBuilder details = new StringBuilder();
        details.append("Product ID: ").append(product.getProductID()).append("\n");
        details.append("Name: ").append(product.getProductName()).append("\n");
        details.append("Price: $").append(product.getPrice()).append("\n");
        details.append("Items Available: ").append(product.getAvailableAmount()).append("\n");

        if (product instanceof Electronics) {
            details.append("Brand: ").append(((Electronics) product).getBrand()).append("\n");
            details.append("Warranty: ").append(((Electronics) product).getWarrantyPeriod());
        } else if (product instanceof Clothing) {
            details.append("Size: ").append(((Clothing) product).getSize()).append("\n");
            details.append("Color: ").append(((Clothing) product).getColour());
        }

        return details.toString();
    }

    private static void colorLowStockItems() {
        for (int i = 0; i < productTableModel.getRowCount(); i++) {
            int availability = (int) productTableModel.getValueAt(i, 4);
            if (availability < 3) {
                productTable.getCellRenderer(i, 4).getTableCellRendererComponent(productTable, availability, false, false, i, 4).setForeground(Color.RED);
            } else {
                productTable.getCellRenderer(i, 4).getTableCellRendererComponent(productTable, availability, false, false, i, 4).setForeground(Color.BLACK);
            }
        }
    }

    private void showProductDetails() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            String productId = (String) productTableModel.getValueAt(selectedRow, 0);
            for (Product product : shoppingManager.getStock()) {
                if (product.getProductID().equals(productId)) {
                    String details = getProductDetails(product);
                    productDetailsTextArea.setText(details);
                    break;
                }
            }
        }
    }

    private void addToCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            String productId = (String) productTableModel.getValueAt(selectedRow, 0);
            for (Product product : shoppingManager.getStock()) {
                if (product.getProductID().equals(productId)) {
                    shoppingCart.add(product);
                    Cart.updateCartTable();
                    Cart.updateTotalPrice();
                    break;
                }
            }
        }
    }

    // Method to add product from GUI
    public void addProductFromGUI(Product product) {
        shoppingManager.addProductFromGUI(product);
        loadProducts();
    }

    // Method to remove product from GUI
    public void removeProductFromGUI(String productId) {
        shoppingManager.removeProductFromGUI(productId);
        loadProducts();
    }
}
