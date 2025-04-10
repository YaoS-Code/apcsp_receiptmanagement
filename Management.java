import java.util.Scanner;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;

public class Management {
    public static void main(String[] args) {

        readFile();
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
                    printAllReceipts();
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
                return; // Exit the loop
            }
        }
    }
    
    private static void printAllReceipts() {
        System.out.println(Receipt.getReceiptList());
        menu();
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

        writeToFile(receipt);
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
            File myObj = new File("./assets/database.txt");
            Scanner myReader = new Scanner(myObj);
            int receiptNumber=0;
            String receiptDate="";
            String receiptName="";
            String receiptType="";
            String receiptDescription="";
            double receiptAmount=0;
            double receiptTip=0;
            String receiptStatus="";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                switch(data.split(":")[0]) {
                    case "receiptNumber":
                        receiptNumber = Integer.parseInt(data.split(":")[1]);
                        break;
                    case "receiptDate":
                        receiptDate = data.split(":")[1];
                        break;
                    case "receiptName":
                        receiptName = data.split(":")[1];
                        break;
                    case "receiptType":
                        receiptType = data.split(":")[1];
                        break;
                    case "receiptDescription":
                        receiptDescription = data.split(":")[1];
                        break;
                    case "receiptAmount":
                        receiptAmount = Double.parseDouble(data.split(":")[1]);
                        break;
                    case "receiptTip":
                        receiptTip = Double.parseDouble(data.split(":")[1]);
                        break;
                    case "receiptStatus":
                        receiptStatus = data.split(":")[1];
                        break;
                    default:
                        break;
                }
                if (data.equals("-------------------------")) {
                    Receipt receipt = new Receipt(receiptDate, receiptName, receiptType, receiptDescription, receiptAmount, receiptTip);
                    receipt.setReceiptNumber(receiptNumber);
                    receipt.setReceiptStatus(receiptStatus);
                }
        }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void writeToFile(Receipt receipt) {
        try {
            FileWriter myWriter = new FileWriter("./assets/database.txt", true);
            myWriter.write(receipt.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
