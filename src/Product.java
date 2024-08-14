import java.io.Serializable;

public abstract class Product implements Serializable {
    private static final long serialVersionUID = 1L; // Adding serialVersionUID

    //Attributes

    private String productID;
    private String productName;
    private int availableAmount;
    private double price;

    //Constructor

    public Product(String productID, String productName, int availableAmount, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableAmount = availableAmount;
        this.price = price;
    }

    // Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
