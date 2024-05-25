public class Clothing extends Product {
    String size;
    String colour;
    public Clothing(String productID, String productName, int availableAmount, double price,String size,String colour) {
        super(productID, productName, availableAmount, price);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
