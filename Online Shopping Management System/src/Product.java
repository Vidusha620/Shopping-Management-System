public abstract class Product {
    String productID;
    String productName;
    int availableAmount;
    double price;


    public Product(String productID, String productName, int availableAmount, double price) {
        this.productID = this.productID;
        this.productName = this.productName;
        this.availableAmount = this.availableAmount;
        this.price = this.price;
    }

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