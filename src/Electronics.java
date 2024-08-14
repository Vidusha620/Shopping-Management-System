import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private static final long serialVersionUID = 1L; // Adding serialVersionUID
    //Attributes

    private String brand;
    private String warrantyPeriod;

    //Constructor

    public Electronics(String productID, String productName, int availableAmount, double price, String brand, int warrantyPeriod) {
        super(productID, productName, availableAmount, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getters and Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
