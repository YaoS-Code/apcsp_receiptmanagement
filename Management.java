import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Management {
    public static void main(String[] args) {
        readFile();
        app();
    }

    public static void app() {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            isRunning = userOpitons();
        }
    }

    public static void printMenu() {
        System.out.println("1. Create Receipt");
        System.out.println("2. Return Receipt");
        System.out.println("3. Display Receipts");
        System.out.println("4. Sort Receipts");
        System.out.println("5. Exit");
    }

    public static boolean userOpitons(){
        System.out.print("Enter your choice: ");
            Scanner input = new Scanner(System.in);
            int choice;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                choice = 6;
            }
            input.nextLine();
            boolean isRunning = true;
            switch (choice) {
                case 1:
                    createReceipt();
                    System.out.println("Receipt Created!");
                    isRunning = isRunning();
                    break;
                case 2:
                    searchReceipt();
                    isRunning = isRunning();
                    break;
                case 3:
                    System.out.print("Display Receipts");
                    displayReceipts();
                    isRunning = isRunning();
                    break;
                case 4:
                    System.out.println("Sort Receipts");
                    sortReceipts();
                    isRunning = isRunning();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice!\n");
                    break;
                }
                
                return isRunning;
    }

    private static void sortReceipts() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Sort by Date");
        System.out.println("2. Sort by Amount");
        System.out.println("3. Sort by Receipt Number");
        System.out.print("Enter your choice: ");
        int choice;
        try {
            choice = input.nextInt();
        } catch (Exception e) {
            choice = 6; 
        }
        input.nextLine();
        switch (choice) {
            case 1:
                sortByDate();
                break;
            case 2:
                sortByAmount();
                break;
            case 3:
                readFile();
                System.out.println("Receipts sorted by receipt number. Done.");
                break;
            default:
                System.out.println("Invalid choice!\n");
                break;
        }
    }

    private static void sortByAmount() {
        for (int i = 0; i < Receipt.getReceiptList().size(); i++) {
            for (int j = 0; j < Receipt.getReceiptList().size() - 1; j++) {
                if (Receipt.getReceiptList().get(j).getReceiptTotal() > Receipt.getReceiptList().get(j + 1).getReceiptTotal()) {
                    Receipt temp = Receipt.getReceiptList().get(j);
                    Receipt.getReceiptList().set(j, Receipt.getReceiptList().get(j + 1));
                    Receipt.getReceiptList().set(j + 1, temp);
                }
            }
        }
        System.out.println("Receipts sorted by amount. Done.");
    }

    private static void sortByDate() {
        for (int i = 0; i < Receipt.getReceiptList().size(); i++) {
            for (int j = 0; j < Receipt.getReceiptList().size() - 1; j++) {
                if (Receipt.getReceiptList().get(j).getReceiptDate().compareTo(Receipt.getReceiptList().get(j + 1).getReceiptDate()) > 0) {
                    Receipt temp = Receipt.getReceiptList().get(j);
                    Receipt.getReceiptList().set(j, Receipt.getReceiptList().get(j + 1));
                    Receipt.getReceiptList().set(j + 1, temp);
                }
            }
        }
        System.out.println("Receipts sorted by date. Done.");
    }

    private static void displayReceipts() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n1. List Receipts by Type");
        System.out.println("2. List Receipts by Date Range");
        System.out.println("3. List All Receipts");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                listReceiptByType();
                break;
            case 2:
                listReceiptsByDateRange();
                break;
            case 3:
                printAllReceipts();
                break;
            default:
                System.out.println("Invalid choice!\n");
                break;
        }
    }

    public static boolean isRunning(){
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Do you want to continue? (y/n)");
        String continueChoice = input.nextLine();
        if (continueChoice.toLowerCase().equals("n")) {
            System.out.println("Goodbye!");
            isRunning = false;
        }
    
        return isRunning;
    }
    
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
                System.out.println("Receipt found!");
                returnReceipt(receipt);
                break;
            }
        }
        if (!found) {
            System.out.println("Receipt not found.");
        }
    }

    private static void returnReceipt(Receipt receipt) {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to return this receipt? (y/n)");
        String choice = input.nextLine();
        if (choice.toLowerCase().equals("n")) {
            System.out.println("Receipt not returned.");
            return;
        }
        receipt.setReceiptStatus("returned");
        System.out.println("Receipt returned!");
        System.out.println(receipt);
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
        input.nextLine();
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
