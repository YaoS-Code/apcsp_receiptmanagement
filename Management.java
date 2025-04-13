import java.util.Scanner;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;

public class Management {
    public static void main(String[] args) {
        readFile(); // read the file from the text file, create receipt objects 
        app(); // start the app.
    }

    public static void app() {
        boolean isRunning = true; // main loop
        while (isRunning) {
            printMenu();
            isRunning = userOpitons();
        }
    }

    public static void printMenu() {
        System.out.println("1. Create Receipt");
        System.out.println("2. Search Receipt by Name");
        System.out.println("3. List Receipts by Date Range");
        System.out.println("4. List Receipts by Type");
        System.out.println("5. Print All Receipts");
        System.out.println("6. Receipt Other");
        System.out.println("7. Exit");
    }

    public static boolean userOpitons(){
        System.out.print("Enter your choice: ");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            input.nextLine(); // Consume newline left-over
            boolean isRunning = true;
            switch (choice) {
                case 1:
                    createReceipt();
                    System.out.println("Receipt Created!");
                    break;
                case 2:
                    searchReceipt();
                    break;
                case 3:
                    listReceiptsByDateRange();
                    break;
                case 4:
                    listReceiptByType();
                    break;
                case 5:
                    printAllReceipts();
                    break;
                case 6:
                    System.out.print("Receipt Other!");
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    isRunning = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid choice!\n");
                    break;
                }
            isRunning = isRunning();
                return isRunning;
    }

    public static boolean isRunning(){
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Do you want to continue? (y/n)");
        String continueChoice = input.nextLine();
        if (continueChoice.toLowerCase().equals("n")) {
            System.out.println("Goodbye!");
            isRunning = false; // Exit the loop
        }
    
        return isRunning;
    }
    
    // List receipts by type
    // This method lists all receipts of a specific type
    public static void listReceiptByType(){
        Scanner input = new Scanner(System.in);
        double total = 0;
        int count = 0;
        double max = 0;
        double min = 99999;
        System.out.println("Enter Receipt Type to search: ");
        String receiptType = input.nextLine();
        boolean found = false;
        for (Receipt receipt : Receipt.getReceiptList()) {
            if (receipt.getReceiptType().equals(receiptType) ) {
                System.out.println(receipt);
                found = true;
                total += receipt.getReceiptTotal();
                count++;
                if (receipt.getReceiptTotal() > max) {
                    max = receipt.getReceiptTotal();
                }
                if (receipt.getReceiptTotal() < min) {
                    min = receipt.getReceiptTotal();
                }
            }
        }
        if (!found) {
            System.out.println("Receipt not found.");
        } else {

            System.out.println("Total number of receipts of type " + receiptType + ": " + count);
            System.out.println("Total amount of receipts of type " + receiptType + ": " + total);
            System.out.println("Maximum amount of receipts of type " + receiptType + ": " + max);
            System.out.println("Minimum amount of receipts of type " + receiptType + ": " + min);
            System.out.println("Average amount of receipts of type " + receiptType + ": " + (total/count));
        }
    }


    // List receipts by date range
    // This method lists all receipts in a specific date range
    private static void listReceiptsByDateRange() {
        Scanner input = new Scanner(System.in);
        double total = 0;
        int count = 0;
        double max = 0;
        double min = 99999;
        System.out.println("Enter start date (YYYY-MM-DD): ");
        String startDate = input.nextLine();
        System.out.println("Enter end date (YYYY-MM-DD): ");
        String endDate = input.nextLine();
        boolean found = false; 
        for (Receipt receipt : Receipt.getReceiptList()) {
            if (receipt.getReceiptDate().compareTo(startDate) >= 0 && receipt.getReceiptDate().compareTo(endDate) <= 0) {
                System.out.println(receipt);
                found = true;
                total += receipt.getReceiptTotal();
                count++;
                if (receipt.getReceiptTotal() > max) {
                    max = receipt.getReceiptTotal();
                }
                if (receipt.getReceiptTotal() < min) {
                    min = receipt.getReceiptTotal();
                }
            }
        }
        if (!found) {
            System.out.println("No receipts found in the given date range.");
        } else {
            System.out.println("Total number of receipts in the date range: " + count);
            System.out.println("Total amount of receipts in the date range: " + total);
            System.out.println("Maximum amount of receipts in the date range: " + max);
            System.out.println("Minimum amount of receipts in the date range: " + min);
            System.out.println("Average amount of receipts in the date range: " + (total/count));
        }
    
    }

    private static void printAllReceipts() {
        System.out.println(Receipt.getReceiptList());
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
        }
    
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
