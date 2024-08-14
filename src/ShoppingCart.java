public class ShoppingCart {

    public String products;

    public ShoppingCart(String products) {
        this.products = products;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products='" + products + '\'' +
                '}';
    }
}
