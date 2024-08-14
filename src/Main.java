public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.loadFile(); // Load saved data before showing the menu
        manager.Menu();//Display menu
    }
}
