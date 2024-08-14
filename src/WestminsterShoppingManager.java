
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static ArrayList<Product> stock = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;


    public void Menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1.Add product to the system.");
        System.out.println("2.Remove product from the system.");
        System.out.println("3.Print list of products available in the system.");
        System.out.println("4.Save details of products available in the system in a file.");
        System.out.println("5.Exit from the system");
        System.out.println("Please enter an option: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                addProduct();
                Menu();
                break;
            case 2:
                removeProduct();
                Menu();
            case 3:
                printList();
                Menu();
            case 4:
                saveFile();
                Menu();
            case 5:
                exit = true;
                break;
            default:
                System.out.println("Invalid entry.Try again.");
        }
    }

    @Override
    public void addProduct() {
        if(stock.size() >= 50){
            System.out.println("Error!Out of storage.");
        }
        category();

    }

    @Override
    public void removeProduct() {
        System.out.println("Enter product ID of the item to be removed: ");
        String removeItem = scanner.nextLine();

        for(Product product : stock){
            if(product.getProductID().equals(removeItem)) {
                stock.remove(product);
                System.out.println("Product " + removeItem + " is removed.");
                break;
            }
            else{
                System.out.println("Product ID not found.Try again.");
                removeProduct();
            }
        }
    }

    @Override
    public void printStock() {

    }

    @Override

    public void printList() {
        if(stock != null) {
            for (Product product : stock) {
                System.out.println("Item details: ");
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Product name: " + product.getProductName());
                System.out.println("No of available items: " + product.getAvailableAmount());
                System.out.println("Price: " + product.getPrice());
                if (product instanceof Electronics) {
                    System.out.println("Brand: " + ((Electronics) product).getBrand());
                    System.out.println("Warranty period: " + ((Electronics) product).getWarrantyPeriod());
                } else if (product instanceof Clothing) {
                    System.out.println("Size: " + ((Clothing) product).getSize());
                    System.out.println("Colour: " + ((Clothing) product).getColour());
                }
            }
        }
        else {
            System.out.println("List is empty.");
        }

    }
    @Override
    public void saveFile() {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("file"))){
            objectOutputStream.writeObject(stock);
            System.out.println("Information saved to file.");
        }
        catch(IOException e){
            System.err.println("Error saving: "+e.getMessage());
        }
    }

    @Override
    public void loadFile(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("file"))){
            stock = (ArrayList<Product>)objectInputStream.readObject();
            System.out.println("Information read from file.");
        }
        catch (IOException | ClassNotFoundException e){
            System.err.println("Error reading from "+e.getMessage());
        }
    }

    private void category() {
        System.out.println("Press 1 if it an electronic item and press 2 if it is a clothing item.");
        int categoryNO = scanner.nextInt();

        if (categoryNO == 1) {
            System.out.println("Enter the product ID: ");
            String productID = scanner.nextLine();
            System.out.println("Enter the product name: ");
            String productName = scanner.nextLine();
            System.out.println("Enter no of items: ");
            int noOfItems = scanner.nextInt();
            System.out.println("Enter the product price: ");
            double price = scanner.nextDouble();
            System.out.println("Enter the product brand: ");
            String brand = scanner.nextLine();
            System.out.println("Enter the product's warranty period: ");
            int warrantyPeriod = scanner.nextInt();

            Electronics electronics = new Electronics(productID, productName, noOfItems, price, brand, warrantyPeriod);
            stock.add(electronics);


        } else if (categoryNO == 2) {
            System.out.println("Enter the product ID: ");
            String productID = scanner.nextLine();
            System.out.println("Enter the product name: ");
            String productName = scanner.nextLine();
            System.out.println("Enter no of items: ");
            int noOfItems = scanner.nextInt();
            System.out.println("Enter the product price: ");
            double price = scanner.nextDouble();
            System.out.println("Enter the product size from S,M,L,XL,XXL: ");
            String size = scanner.next().toUpperCase();
            System.out.println("Enter the product colour: ");
            String colour = scanner.nextLine();

            Clothing clothing = new Clothing(productID, productName, noOfItems, price, size, colour);
            stock.add(clothing);

        }
        else {
            System.out.println("Invalid category number.Choose 1 or 2.");
            category();
        }
    }


}
