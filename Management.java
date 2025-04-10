import java.util.Scanner;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;

public class Management {
    public static void main(String[] args) {

        // readFile();
        menu();
    }

    public static void menu() {

        while (true) {
            System.out.println("Welcome to Management System");
            System.out.println("----------------------------");
            System.out.println("1. Record a Receipt");
            System.out.println("2. Search a Receipt");
            System.out.println("3. List Receipts");
            System.out.println("4. Other");
            System.out.println("5. Exit");
            System.out.println("----------------------------");
            System.out.print("Enter your choice: ");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    createReceipt();
                    break;
                case 2:
                    searchReceipt();
                    break;
                case 3:
                    System.out.print("Receipt Listed!");
                    break;
                case 4:
                    System.out.print("Receipt Other!");
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice!\n");
                    continue;
            }
            System.out.println("Do you want to continue? (y/n)");
            String continueChoice = input.next();
            if (continueChoice.toLowerCase().equals("n")) {
                System.out.println("Goodbye!");
                break; // Exit the loop
            }
        }
    }
    
    private static void searchReceipt() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Receipt Name to search: ");
        String receiptName = input.nextLine();
        boolean found = false;
        for (Receipt receipt : Receipt.getReceiptList()) {
            if (receipt.getReceiptName().equals(receiptName) ) {
                System.out.println(receipt);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Receipt not found.");
        }
        menu();
    }

    public static void createReceipt() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Receipt Date (YYYY-MM-DD): ");
        String date = input.nextLine();
        System.out.println("Enter Receipt Name: ");
        String name = input.nextLine();
        System.out.println("Enter Receipt Type: ");
        String type = input.nextLine();
        System.out.println("Enter Receipt Description: ");
        String description = input.nextLine();
        System.out.println("Enter Receipt Amount: ");
        double amount = input.nextDouble();
        System.out.println("Enter Receipt Tip: ");
        double tip = input.nextDouble();
        input.nextLine(); // Consume newline left-over
        Receipt receipt = new Receipt(date, name, type, description, amount, tip);
        System.out.println("Receipt Created!");

        writeToFile(receipt.saveToDatabase());
        System.out.println("Receipt saved to file.");

        System.out.println(receipt);
        System.out.println("Do you want to create another receipt? (y/n)");
        String another = input.nextLine();
        if (another.toLowerCase().equals("y")) {
            createReceipt();
        } else {
            System.out.println("Returning to menu...");
            menu();
        }
        input.close();
    
    }

    public static void readFile() {
        try {
            File myObj = new File("database.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
        }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void writeToFile(String receipt) {
        try {
            FileWriter myWriter = new FileWriter("./assets/database.txt", true);
            myWriter.write(receipt.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
