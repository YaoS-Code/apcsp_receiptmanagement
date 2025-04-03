public class Management {
    public static void main(String[] args) {
        System.out.println("Welcome to Management System");
        Receipt demoReceipt = new Receipt("2025-04-02", "Haidilao", "Food & Drink", 100, "With friends.", 0.13, 20);
        Receipt demoReceipt1 = new Receipt("2024-04-02", "Bestbuy", "Office", 2000, "Aliens Laptop", 0.13, 0);
        new Receipt("2024-01-02", "Bestbuy", "Office", 2000, "Aliens Laptop", 0.13, 0);
        new Receipt("2024-02-02", "Staples", "Office", 2000, "Aliens Laptop", 0.13, 0);
        new Receipt("2024-03-02", "COmputer Store", "Office", 2000, "Aliens Laptop", 0.13, 0);
        System.out.println(Receipt.getReceiptList());
    }
}
