import java.util.Date;

public class Electronics extends Product {

    String brand;
    int warrantyPeriod;
    public Electronics(String productID, String productName, int availableAmount, double price,String brand,int warrantyPeriod) {
        super(productID, productName, availableAmount, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
