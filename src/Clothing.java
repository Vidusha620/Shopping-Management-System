import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private static final long serialVersionUID = 1L; // Adding serialVersionUID

    //Attributes

    private String size;
    private String colour;

    //Constructor

    public Clothing(String productID, String productName, int availableAmount, double price, String size, String colour) {
        super(productID, productName, availableAmount, price);
        this.size = size;
        this.colour = colour;
    }

    // Getters and Setters
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
